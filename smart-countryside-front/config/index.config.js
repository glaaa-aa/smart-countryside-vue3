const CONFIG = {
  //开发环境配置
  development: {
    loginTitleTxt: "美丽数智乡村", // 登录页标题
    copyrightTxt: "美丽数智乡村v1.0", // 版本信息
    // baseUrl: "http://127.0.0.1:4523/m1/3535831-0-default",
    // assetsPath: "http://127.0.0.1:4523/m1/3535831-0-default/", // 静态资源路径
    assetsPath: "http://192.168.0.118:8080/qfy-boot-system/sys/common/static/", // 静态资源路径
    baseUrl: "http://192.168.0.118:8080/qfy-boot-system",
    tokenKey: "WECHAT_TRADE", // 登录标识
    testOpenId: "oEjRxwy7jL9PgG0kWeb4VcDTZEas", //小程序测试openId
    forcedLogin: false, // touristMode游客模式下APP是否强制用户登录 场景：当用户进入登录页面后无法后退。
    touristMode: true, // APP是否开启游客模式， 游客模式true开启：APP打开后可以进入首页和无权限的页面，游客模式false关闭：APP打开后首先需要登录才能进入， 此时forcedLogin配置项失效。
  },

  //生产环境配
  production: {
    loginTitleTxt: "美丽数智乡村", // 登录页标题
    copyrightTxt: "美丽数智乡村v1.0", // 版本信息
    // assetsPath: "https://dg.tsingfy.cn/qfy-dagz-system/sys/common/static/", // 静态资源路径
    // baseUrl: "https://dg.tsingfy.cn/qfy-dagz-system",
    tokenKey: "WECHAT_TRADE", // 登录标识
    testOpenId: "oEjRxwy7jL9PgG0kWeb4VcDTZEas", // 小程序测试openId
    forcedLogin: false, // touristMode游客模式下APP是否强制用户登录 场景：当用户进入登录页面后无法后退。
    touristMode: true, // APP是否开启游客模式， 游客模式true开启：APP打开后可以进入首页和无权限的页面，游客模式false关闭：APP打开后首先需要登录才能进入， 此时forcedLogin配置项失效。
  },
};
export default CONFIG[process.env.NODE_ENV];
