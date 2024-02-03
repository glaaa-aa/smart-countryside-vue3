<template>
  <div class="login">
    <div class="loginImg">
      <image class="Img" :src="main_logo"></image>
      <div
        class="logo"
        style="
          position: absolute;
          display: flex;
          flex-direction: column;
          padding: 20px 0px 10px;
          align-items: center;
          top: 22%;
          color: white;
          font-size: 20px;
          width: 100%;
          justify-content: center;
        "
      >
        <!-- <div class="logo_title" style="font-weight: bold">
          数智乡村综合管理平台
        </div> -->
      </div>
      <!-- <div class="btnBox">
        <div class="btnBox1">
          <u-button
            type="primary"
            @click="goPhoneRegister"
            text="去登陆"
          ></u-button>
        </div>
      </div> -->
    </div>
    	<!-- 隐私授权弹窗 -->
	    <u-modal :show="ysShow" :title="ysTitle">
	      <template slot="default">
	        <div style="font-size: 15px; line-height: 25px">
	          <div>欢迎使用南丹县社会基层治理小程序！</div>
	          <div>
	            我们非常重视您的个人信息安全和隐私保护。依据最新法律要求，使用我们的产品前请仔细阅读并同意<span
	              style="color: #1990ff"
	              @click="goUserAgreement('yszc')"
	              >《隐私政策》</span
	            >，以便我们向您提供优质的服务，感谢您的信任！
	          </div>
	        </div>
	      </template>
	      <template slot="confirmButton">
	        <div class="agreeBtn" style="margin-bottom: 5px">
	          <u-button
	            type="primary"
	            @click="goLogin"
	            text="同意并继续"
	          ></u-button>
	        </div>
	        <div class="cancelBtn">
	          <u-button
	            type="primary"
	            color="#bfbdbd"
	            :hairline="false"
	            :plain="true"
	            text="不同意"
	            @click="cancelBtn"
	          ></u-button>
	        </div>
	      </template>
	    </u-modal>
  </div>
</template>
<script>
import { getImgUrl } from "@/common/utils.js";
export default {
  data() {
    return {
      main_logo: null,
      isShowBtn: false,
      //隐私政策
      ysShow: false,
      ysTitle: "隐私政策",
      // 确认提示框
      tsTitle: "温馨提示",
      tsContent: "若不同意,将无法为您提供服务,是否退出?",
    };
  },
  onLoad() {
    this.main_logo = this.$mAssetsPath.main_logo;
    //默认加载
    this.loadUser();
  },
  methods: {
    getImgUrl,
    loadUser() {
      uni.showLoading({
        title: "加载中",
      });

      let that = this;
      uni.getProvider({
        service: "oauth",
        success: async (res) => {
          // console.log(res, "res111");
          if (res.provider.indexOf("weixin") >= 0) {
            //获取code
            await uni.login({
              provider: "weixin",
              scopes: "auth_user",
              success: async (loginRes) => {
                console.log("刷新tokenCode响应", loginRes);
                await that.$http.post(`/sys/thirdApp/refreshToken/weixin`, {
                    code: loginRes.code,
                    thirdType: "weixin",
                  })
                  .then(async (r) => {
                    console.log("微信请求后", r);
                    if (r.code == -1) {
                      //未注册
                      uni.reLaunch({
                        url: "/pages/login/login",
                        success: (res) => {
                          // uni.$u.toast(r.message);
                        },
                      });
                    } else {
                      let result = r.result;
                      that.$store.commit("SET_TOKEN", result.token);
                      let userInfo = result.userInfo;
                      //清除存储
                      uni.removeStorageSync("userId");
                      uni.removeStorageSync("userInfo");
                      that.$store.commit("SET_USERID", userInfo.username);
                      that.$store.commit("SET_USERINFO", userInfo);
                      that.$store.commit("SET_CODE", userInfo.districtCode);

                      uni.reLaunch({
                        url: "/pages/index/index",
                      });
                    }
                  });
              },
              fail: (err) => {
                reject(err);
              },
            });
          } else {
            //没有平台供应商   默认H5
            uni.showToast({
              title: "仅支持微信小程序登录",
              icon: "none",
              duration: 2000,
            });
            // uni.reLaunch({
            //   url: "/pages/login/login",
            // });
          }
        },
      });
    },
     //阅读用户协议
     goUserAgreement(nameCode) {
      uni.navigateTo({
        url: "/pages/login/modules/readDeal?flag=" + nameCode,
      });
    },
    // 点击去登录
    goPhoneRegister() {
      this.ysShow = true;
    },
    // 点击同意
    goLogin() {
      uni.redirectTo({
        url: "/pages/login/login",
      });
    },
    //点击不同意
    cancelBtn() {
      this.ysShow = false;
      wx.showModal({
        title: this.tsTitle, // 提示的标题
        content: this.tsContent, // 提示的内容
        showCancel: true, // 是否显示取消按钮，默认true
        cancelText: "我再想想", // 取消按钮的文字，最多4个字符
        cancelColor: "#000000", // 取消按钮的文字颜色，必须是16进制格式的颜色字符串
        confirmText: "退出应用", // 确认按钮的文字，最多4个字符
        confirmColor: "#576B95", // 确认按钮的文字颜色，必须是 16 进制格式的颜色字符串
        success: function (res) {
          console.log("接口调用成功的回调函数");

          if (res.confirm) {
            console.log("用户点击确定");
            //在需要退出小程序的地方调用添加下面代码即可(js文件中)
            wx.exitMiniProgram({ success: (res) => {} });
          } else if (res.cancel) {
            console.log("用户点击取消");
          }
        },
        fail: function () {
          console.log("接口调用失败的回调函数");
        },
        complete: function () {
          console.log("接口调用结束的回调函数（调用成功、失败都会执行）");
        },
      });
    },
    //綁定手机号
    onGetPhoneNumber(e) {
      var that = this;
      let msg = e.detail.errMsg;
      if (msg == "getPhoneNumber:ok") {
        //这里表示获取授权成功
        //加密格式
        let iv = e.detail.iv;
        let encry = e.detail.encryptedData;
        console.log("onGetPhoneNumber！", e.detail);

        uni.getProvider({
          service: "oauth",
          success: async (res) => {
            if (res.provider.indexOf("weixin") >= 0) {
              //获取code
              await uni.login({
                provider: "weixin",
                scopes: "auth_base",
                success: async (loginRes) => {
                  // console.log("刷新tokenCOde 响应", loginRes)
                  await that.$http
                    .post(`/sys/thirdApp/bindAccount/weixin`, {
                      code: loginRes.code,
                      thirdType: "weixin",
                      encryptedData: encry,
                      iv: iv,
                    })
                    .then(async (r) => {
                      console.log("微信绑定手机号请求结果=>", r);
                      if (r.success) {
                        let result = r.result;
                        that.$store.commit("SET_TOKEN", result.token);
                        // that.$store.commit("SET_USERID", result.userInfo.username);
                        that.$store.commit("SET_USERID", null); //暂时不存储userId
                        that.$store.commit("SET_USERINFO", result.userInfo);

                        uni.redirectTo({
                          url: "/pages/login/register",
                        });
                      }
                    });
                },
                fail: (err) => {
                  reject(err);
                },
              });
            } else {
              //没有平台供应商   默认H5
              uni.showToast({
                title: "暂未有其他平台绑定",
                icon: "none",
                duration: 1000,
              });
            }
          },
        });
      }
    },
  },
};
</script>
<style lang="less" scoped>
.login {
  width: 100%;
  height: 100%;
  position: relative;
  .loginImg {
    width: 100%;
    height: 100%;
    .Img {
      width: 100% !important;
      height: 100% !important;
    }
  }
  // .login_img {
  // 	background-image: url('');
  // 	background-size: 100% 100%;
  // 	background-repeat: no-repeat;
  // }
  .title {
    // font-weight: bold;
    font-size: 24px;
    color: #333333;
    position: absolute;
    top: 10%;
    left: 42%;
    font-family: serif;
    font-family: "微软雅黑", sans-serif;
  }
  .text {
    width: 100%;
    position: absolute;
    bottom: 360rpx;
    text-align: center;
    font-size: 36rpx;
    color: #000;
  }
  .login_btn {
    width: 140px;
    height: 44px;
    position: absolute;
    left: 50%;
    transform: translate(-50%);
    bottom: 13%;
    box-shadow: rgba(0, 0, 0, 0.16) 0px 3px 6px, rgba(0, 0, 0, 0.23) 0px 3px 6px;
  }
}
.btnBox {
  position: absolute;
  z-index: 999;
  bottom: 26%;
  left: 50%;
  width: 192px;
  transform: translate(-50%);
}
.btnBox1 /deep/.u-button--primary {
  color: #fff;
  background-color: #7868ff;
  border-color: white;
  border-width: 1px;
  border-style: solid;
  font-weight: bold;
  margin-top: 15px;
}
.btnBox2 /deep/.u-button--primary {
  background: rgba(255, 255, 255, 0.7);
  color: red;
  border: 1px solid #fff;
  border-width: 1px;
  border-style: solid;
  font-weight: bold;
  margin-top: 15px;
}
.btnBox /deep/ .u-button--square {
  border-bottom-left-radius: 20px !important;
  border-bottom-right-radius: 20px !important;
  border-top-left-radius: 20px !important;
  border-top-right-radius: 20px !important;
}
.login /deep/.u-fade-enter-active,
.u-fade-leave-active{
  width: 100%;
  height: 100%;
}
//隐私弹出框
.login /deep/ .u-modal__title {
  font-size: 17px;
  color: #000;
}
.agreeBtn /deep/ .u-button--primary {
  border-bottom-left-radius: 20px !important;
  border-bottom-right-radius: 20px !important;
  border-top-left-radius: 20px !important;
  border-top-right-radius: 20px !important;
}
.cancelBtn /deep/ .u-button--plain {
  background-color: #fff;
  border-style: none !important;
  border-bottom-left-radius: 20px !important;
  border-bottom-right-radius: 20px !important;
  border-top-left-radius: 20px !important;
  border-top-right-radius: 20px !important;
}

.agreeBtn /deep/ .u-button__text {
  font-size: 16px !important;
}
.cancelBtn /deep/ .u-button__text {
  font-size: 16px !important;
}
</style>
