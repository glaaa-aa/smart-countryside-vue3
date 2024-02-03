/*
 * 路由表对象：
 * 该文件挂载在Vue原型中 $mRoutesConfig
 * 作用：调用$mRouter对象的方法 传入以下对应的路由对象，详细见common目录下的router.js
 * 示例：this.$mRouter.push({route:this.$mRoutesConfig.main,query:{a:1}})
 * 注意：所有在pages目录下新建的页面都必须在"路由表"中进行声明，并且在框架的pages.json注册。
 *
 * 配置参数项说明：
 * name:可选配置 （路由名称）
 * path:必填配置 （路由地址）
 * requiresAuth:可选配置 （是否权限路由）
 */

export default {
  // 权限路由
  index: {
    name: "首页",
    path: "/pages/index/index",
    requiresAuth: true,
  },
  user: {
    name: "个人中心",
    path: "/pages/personal/personal",
    requiresAuth: true,
  },
  alumniCertificate: {
    name: "校友认证",
    path: "/pages/authen/alumniCertificate",
    requiresAuth: true,
  },
  alumniCertificate2: {
    name: "邀请校友认证",
    path: "/pages/authen/alumniCertificate2",
    requiresAuth: true,
  },
  cardRead: {
    name: "校友卡申请办理须知",
    path: "/pages/personal/card/cardRead",
    requiresAuth: true,
  },
  myCard: {
    name: "校友卡申请",
    path: "/pages/personal/card/myCard",
    requiresAuth: true,
  },
  findAlumni: {
    name: "发现校友",
    path: "/pages/myFriend/findAlumni",
    requiresAuth: true,
  },
  myFriend: {
    name: "我的好友",
    path: "/pages/myFriend/myFriend",
    requiresAuth: true,
  },
  organization: {
    name: "校友组织",
    path: "/pages/myFriend/organization",
    requiresAuth: true,
  },
  management: {
    name: "学籍管理",
    path: "/pages/personal/management",
    requiresAuth: true,
  },
  Message: {
    name: "校庆专题",
    path: "/pages/cms/Message",
    requiresAuth: true,
  },
  schoolMemory: {
    name: "校园拾忆",
    path: "/pages/cms/schoolMemory",
    requiresAuth: true,
  },
  newsList: {
    name: "新闻咨询",
    path: "/pages/cms/newsList",
    requiresAuth: true,
  },
  activityList: {
    name: "活动专题",
    path: "/pages/activity/activityList",
    requiresAuth: true,
  },
  juankuan: {
    name: "捐款",
    path: "/pages/juankuan/juankuan",
    requiresAuth: true,
  },

  // 非权限路由
  // main: {
  // 	name: "首页",
  // 	path: "/pages/index/main"
  // },
  login: {
    name: "登录",
    path: "/pages/index/main",
  },
};
