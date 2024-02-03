import http from "./interface";
import store from "@/store/index.js";
import indexConfig from "@/config/index.config.js";

module.exports = {
  request: (url, data, method) => {
    // http.config.baseUrl = indexConfig.baseUrl
    //设置请求前拦截器
    http.interceptor.request = (config) => {
      let token = store.state.token;
      // console.log("http.interceptor----------->",token)
      delete config.header["X-Access-Token"];
      if (token) {
        config.header["X-Access-Token"] = token;
      }
      config.header["Http-Source"] = "wxMini";
      config.header["tenant-id"] = 1;
      // config.header['Content-Type'] = "content-type:application/x-www-form-urlencoded";
      config.header["Content-Type"] = "application/json"; // //设置后端需要的常用的格式就好，特殊情况调用的时候单独设置
      config.header["X-AUTH-UUID"] = store.getters.guid;
    };

    //设置请求结束后拦截器
    http.interceptor.response = async (response) => {
      const statusCode = response.statusCode;
      if (statusCode === 401) {
        uni.showToast({
          title: "身份信息过期，请重新登陆",
          duration: 1000,
          success() {
            uni.redirectTo({
              url: "/pages/login/login",
            });
          },
        });
        //返回401，立即token需刷新
        // return new Promise((resolve, reject) => {
        //   let data = refreshToken(response, url);
        //   if (data) {
        //     resolve(data);
        //   } else {
        //     reject(data);
        //   }
        // });
        
        // let data =  await refreshToken(response, url);
        // console.log("立即token需刷新 响应值",data);
        // response.data = data;
        // return  response.data;
      }
      if (statusCode === 422) {
        uni.hideLoading();
        //业务代码...return
      }
      // 不能再刷新时，服务器返回状态码500时,判断是不是刷新token的接口
      if (response.statusCode === 500) {
        if (response.config.url.indexOf("current") == -1) {
          uni.showToast({
            icon: "none",
            title: "网络错误",
          });
        }
      }
      if (
        response.statusCode === 200 ||
        response.statusCode === 201 ||
        response.statusCode === 204
      ) {
        //成功
        uni.hideLoading();
        return response.data;
      } else {
        uni.hideLoading();
		console.log(response)
        uni.showToast({
          title: response.data.message || "请求错误",
          icon: "none",
          duration: 1000,
        });
        return response;
      }
      uni.hideLoading();
    };
    //请求数据
    return http.request({
      url: url,
      data,
      method: method,
    });
  },

  get(url, data) {
    return this.request(url, data, "GET");
  },
  post(url, data, options) {
    return this.request(url, data, "POST");
  },
  put(url, data) {
    return this.request(url, data, "PUT");
  },
  delete(url, data) {
    return this.request(url, data, "DELETE");
  },
};

async function refreshToken(response, url) {
  let userId = store.state.userId || "";
  let resCode = response.data.code || "";
  let baseur = indexConfig.baseUrl;
  
  return new Promise((resolve, reject) => {
    uni.getProvider({
      service: "oauth",
      success: async (res) => {
        if (res.provider.indexOf("weixin") >= 0) {
          //获取code
          await uni.login({
            provider: "weixin",
            scopes: "auth_user",
            success: async (loginRes) => {
              // console.log("刷新tokenCOde响应", loginRes)
              await http
                .post(`/sys/thirdApp/refreshToken/weixin`, {
                  code: loginRes.code,
                  thirdType: "weixin",
                })
                .then(async (r) => {
                  console.log("微信请求后http",r)
                  if (r.code == -1) {
                    //未注册
                    uni.reLaunch({
                      url: "/pages/index/main",
                    });
                  } else {
                    //已经注册
                    let resold = await handleTokenUser(url, response, r.result);
                    // console.log("刷新token后响应值",resold)
                    resolve(resold);
                  }
                });
            },
            fail: (err) => {
              reject(err);
            },
          });
        } else {
          //没有平台供应商   默认H5
          await uni.request({
          	url: baseur + getApp().$apis.url.refreshTokenByUni,
          	method: 'POST',
          	data: {
          		'username': userId,
          		 thirdType:'uniapp'
          	},
              success: async (r) => {
          		let resold = await handleTokenUser(url,response,r.data.result);
          		// console.log("刷新token后响应值",resold)
          		resolve(resold);
             },fail:(err) =>{
          	   reject(err)
             }
          });
        }
      },
    });
  });
}

async function handleTokenUser(url, response, result) {
  // console.log("handleTokenUser=>",url,result)
  store.commit("SET_TOKEN", result.token);
  let userInfo = result.userInfo;
  //清除存储
  uni.removeStorageSync("userId");
  uni.removeStorageSync("userInfo");

  if (userInfo) {
    if (userInfo && userInfo.id) {
      store.commit("SET_USERID", userInfo.username);
      store.commit("SET_USERINFO", userInfo);
    }

    let config = response.config;
    config.header["X-Access-Token"] = result.token;
    config.url = url;
    let resold = await module.exports.request(
      url,
      { ...config.data },
      config.method
    );
    return resold;
  } else {
    //这个时候状态码是500
    // console.log("重新登陆");
    // uni.removeStorageSync("token")
    // window.androidJs.goLogin();
    uni.showToast({
      title: "身份信息过期，请重新登陆",
      duration: 1000,
      success() {
        uni.redirectTo({
          url: "/pages/login/login",
        });
      },
    });
    return false;
  }
}
