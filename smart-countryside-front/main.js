import Vue from "vue";
import App from "./App";
import store from "./store";
import * as $apis from "./apis/api.js";
import $mRouter from "./common/router.js";
import $mUtils from "./common/utils.js";
import $mConfig from "./config/index.config.js";
import $mAssetsPath from "./config/assets.config.js";
import $mRoutesConfig from "./config/routes.config.js";
import $modalHelper from "./common/modalHelper.js";
import $http from "./apis/http.js";
// import $cityData from "@/common/cityData2";
import "@/static/iconfont/iconfont.css";

Vue.config.productionTip = false;

Vue.prototype.$store = store;

Vue.prototype.$apis = $apis;

Vue.prototype.$mRouter = $mRouter;

Vue.prototype.$mUtils = $mUtils;

Vue.prototype.$mConfig = $mConfig;

Vue.prototype.$mAssetsPath = $mAssetsPath;

Vue.prototype.$mRoutesConfig = $mRoutesConfig;

Vue.prototype.$modalHelper = $modalHelper;
Vue.prototype.$http = $http;
// Vue.prototype.$cityData = $cityData;


import uView from "uview-ui";
Vue.use(uView);


$mRouter.beforeEach((navType, to) => {
  if (to.route === undefined)
    throw "路由钩子函数中没有找到to.route对象，路由信息:" + JSON.stringify(to);
  console.log(
    "路由钩子函数中to.route对象==>",
    navType,
    to.route.path,
    to.route.hasLogin,
    store.getters.hasLogin
  );
  if (to.route.path === $mRoutesConfig.login.path && store.getters.hasLogin) {
    uni.redirectTo({
      url: $mUtils.objParseUrlAndParam($mRoutesConfig.index.path, to.query),
    });
    return;
  }

  // 过滤需要权限的页面
  if (to.route.requiresAuth) {
    if (store.getters.hasLogin) {
      // 已经登录
      uni[navType]({
        url: $mUtils.objParseUrlAndParam(to.route.path, to.query),
      });
    } else {
      // 登录成功后的重定向地址和参数
      let query = {
        redirectUrl: to.route.path,
        ...to.query,
      };
      // 没有登录 是否强制登录?
      if (store.state.forcedLogin) {
        uni.redirectTo({
          url: $mUtils.objParseUrlAndParam($mRoutesConfig.login.path, query),
        });
      } else {
        uni.navigateTo({
          url: $mUtils.objParseUrlAndParam($mRoutesConfig.login.path, query),
        });
      }
    }
  } else {
    uni[navType]({
      url: $mUtils.objParseUrlAndParam(to.route.path, to.query),
    });
  }
});

// #ifndef VUE3
Vue.config.productionTip = false;
App.mpType = "app";
const app = new Vue({
  store,
  ...App,
});
app.$mount();
// #endif

// #ifdef VUE3
import { createSSRApp } from "vue";
export function createApp() {
  const app = createSSRApp(App);
  return {
    app,
  };
}
// #endif



Vue.prototype.setData = function (obj) {
  let that = this;
  let keys = [];
  let val, data;
  Object.keys(obj).forEach(function (key) {
    keys = key.split(".");
    val = obj[key];
    data = that.$data;
    keys.forEach(function (key2, index) {
      if (index + 1 == keys.length) {
        that.$set(data, key2, val);
      } else {
        if (!data[key2]) {
          that.$set(data, key2, {});
        }
      }
      data = data[key2];
    });
  });
};

Date.prototype.Format = function (fmt) {
  undefined;
  var o = {
    "M+": this.getMonth() + 1, //月份
    "d+": this.getDate(), //日
    "h+": this.getHours() % 12 == 0 ? 12 : this.getHours() % 12, //小时
    "H+": this.getHours(), //小时
    "m+": this.getMinutes(), //分
    "s+": this.getSeconds(), //秒
    "q+": Math.floor((this.getMonth() + 3) / 3), //季度
    S: this.getMilliseconds(), //毫秒
  };
  if (/(y+)/.test(fmt))
    fmt = fmt.replace(
      RegExp.$1,
      (this.getFullYear() + "").substr(4 - RegExp.$1.length)
    );
  for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt))
      fmt = fmt.replace(
        RegExp.$1,
        RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length)
      );
  return fmt;
};
