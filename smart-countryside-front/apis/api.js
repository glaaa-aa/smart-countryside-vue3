import http from "./http";

//微信登录
// export const wxLogin = (data) => http.post(`/sys/mLogin`, data); //h5登录
export const wxLogin = (data) => http.post(`/sys/thirdApp/wxLogin/weixin`, data); //小程序绑定账号登录
//微信退出
export const goOut = (data) => http.post(`/api/thirdApp/goOut/weixin`, data); //小程序绑定账号登录

// //活动列表
// export const list = (data) => http.get(`/activity/api/newest`, data);
// //活动报名
// export const signUpAdd = (data) => http.post(`/apply/api/signUp/add`, data);

// 上传
// export const upload = `/sys/common/upload`;
export const upload = `/api/uploadify/upload`;

// 矛盾纠纷
//查询矛盾纠纷列表
export const dissensionList = (data) => http.get(`/api/resident/dissension/list`, data);
//查询纠纷类型
export const categoryType = (data) => http.get(`/api/resident/dissension/category/list`, data);
//查询分派人员列表
// export const participants = (data) => http.get(`/api/resident/dissension/participants`, data);
//查询矛盾纠纷详情
export const dissensionDetails = (data) => http.get(`/api/resident/dissension/details`, data);
//添加矛盾纠纷
export const dissensionAdd = (data) => http.post(`/api/resident/dissension/add`, data);
//添加矛盾分派
export const dissensionAssign = (data) => http.post(`/api/resident/dissension/assign`, data);
//矛盾纠纷反馈列表
export const principalList = (data) => http.get(`/api/resident/dissension/principalList`, data);
//提交反馈处理
export const dissensionFeedback = (data) => http.post(`/api/resident/dissension/feedback`, data);


// 积分管理
//查询村民信息列表
export const familyMembers = (data) => http.get(`/api/resident/family/members`, data);
//查询村民信息详情
export const familyMembersDetails = (data) => http.get(`/api/resident/family/members/details`, data);
//查询积分列表
export const integralList = (data) => http.get(`/api/resident/integral/list`, data);
//查询积分明细
export const integralDetails = (data) => http.get(`/api/resident/integral/details`, data);
//查询积分兑换记录
export const integralExchangeList = (data) => http.get(`/api/resident/integral/exchange/list`, data);
//查询行政区划列表
export const departList = (data) => http.get(`/api/sys/district/list`, data);
//查询积分排行
export const ranking = (data) => http.get(`/api/resident/integral/ranking`, data);
//查询全部户主
export const roster = (data) => http.get(`/api/resident/roster`, data);
//查询积分分类事项
export const integralItems = (data) => http.get(`/api/resident/integral/items`, data);
//查询填报历史记录
export const integralHistory = (data) => http.get(`/api/resident/integral/history`, data);
//添加积分填报
export const integralAdd = (data) => http.post(`/api/resident/integral/add`, data);
//查询协议内容
export const sectionList = (data) => http.get(`/apiCms/cms/section/list`, data);
//查询文章列表
export const articleList = (data) => http.get(`/apiCms/cms/article/list`, data);

//查询栏目内容
export const getSectionAll = (data) => http.get(`/apiCms/cms/section/getSectionAll`, data);
export const getArticleId = (data) => http.get(`/apiCms/cms/article/${data}`)



//用户选择组件 专用  根据用户账号或部门分页查询
export const queryUserComponentData = (data) => http.get(`/sys/user/queryUserComponentData`, data);
//矛盾纠纷查询负责人多选组件 专用
export const hrPersonList = (data) => http.get(`/info/hrPerson/list`, data);

//纠纷类型数据字典
export const getDictItems = (data) => http.get(`/sys/api/queryDictItemsByCode?code=${data}`);


//动态Url 需要对Url拼接的地址定义
// const url = {
//   activityDetails: `/activity/api/details`,  
//   article: `/cms/api/article/`,
//   Like: `/cms/api/article/star/`, //拾忆点赞
//   read: `/cms/api/article/ready/`, //资讯阅读
// };

// module.exports.url = url;
