import indexConfig from "./index.config.js";

const PATH = indexConfig.assetsPath;

/*
 * 图片静态资源表，所有图片资源路径在这统一管理，不应该写死在页面中，该数据挂载到Vue原型中。
 * 页面使用：this.$mAssetsPath.grid_1
 * CSS背景：应尽量使用:style="" 行内样式设置背景图
 * PATH说明：本地路径或者服务器路径
 *
 * 举例：<image :src="grid_1">  需要在data中映射 grid_1: this.$mAssetsPath.grid_1
 * 特别注意：经测试小程序中不支持 <image :src="$mAssetsPath.grid_1"> 该用法
 */

export default {
	
	
	main_logo: require("@/static/img/login.jpg"),
	
  // 首页grid的图标
  // grid_1: require("@/static/index/index_1.png"),
  // grid_2: require("@/static/index/index_2.png"),
  // grid_3: require("@/static/index/index_3.png"),
  // grid_4: require("@/static/index/index_4.png"),
  // grid_5: require("@/static/index/index_5.png"),
  // grid_6: require("@/static/index/index_6.png"),
  // grid_7: require("@/static/index/index_7.png"),
  // grid_8: require("@/static/index/index_8.png"),
  // grid_9: require("@/static/index/index_9.png"),
  // grid_10: require("@/static/index/index_10.png"),
  // grid_11: require("@/static/index/index_11.png"),

  // shuxian: require("@/static/index/shuxian.png"),
  // personal_5: require("@/static/index/personal_5.png"),

  index_bj: PATH + "dgr_mini/index_bj.png",

  index_logo: PATH + "dgr_mini/logo.png",
  touxiang: PATH + "dgr_mini/touxiang.png",
  card_1: PATH + "dgr_mini/card_1.png",
  card_2: PATH + "dgr_mini/card_2.png",
  head_bj: PATH + "dgr_mini/head_bj.png",
  huikui_bj2: PATH + "dgr_mini/huikui_bj2.png",
  xiaoqing_bj1: PATH + "dgr_mini/xiaoqing_bj1.png",
  xiaoqing_img1: PATH + "dgr_mini/xiaoqing_img1.png",
  xiaoqing_img2: PATH + "dgr_mini/xiaoqing_img2.png",
  xiaoqing_img3: PATH + "dgr_mini/xiaoqing_img3.png",
  zhengshu: PATH + "dgr_mini/zhengshu.png",
  xiaoyouka: PATH + "dgr_mini/xiaoyouka.png",
  xiaoqing_bj: PATH + "dgr_mini/xiaoqing_bj.png",
  yunbaodao: PATH + "dgr_mini/yunbaodao.jpg",
  yunzhufu: PATH + "dgr_mini/yunzhufu.png",
  //注册引导页
  login_bj: PATH + "dgr_mini/login_bj.png",
  zhufu: PATH + "dgr_mini/zhufu.png",
  zhufu1: PATH + "dgr_mini/zhufu1.png",
};
