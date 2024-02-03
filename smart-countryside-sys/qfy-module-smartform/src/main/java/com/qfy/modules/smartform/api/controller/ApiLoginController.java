package com.qfy.modules.smartform.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title: Controller
 * @Description: 认证接口
 * @author onlineGenerator
 * @date 2019-04-17 15:53:21
 * @version V1.0
 *
 */
@RestController
@RequestMapping("/api/loginController")
public class ApiLoginController {

	private static final Logger logger = LoggerFactory.getLogger(ApiLoginController.class);

//	@Autowired
//	private ISysBaseAPI baseAPI;
//	@Autowired
//	private UserService userService;
//
	/**
	 * 登录接口
	 * @param request
	 * @return
	 */
//	@RequestMapping(params = "checkUser")
//	public AjaxJson checkUser(HttpServletRequest request) {
//		String mobile = request.getParameter("mobile");
//		String password = request.getParameter("pwd");
//		String message = "访问成功";
//		AjaxJson result = new AjaxJson();
////		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
//		try {
//			QueryWrapper wrapper = new QueryWrapper();
//			wrapper.eq("", mobile);
//			LoginUser loginUser = baseAPI.queryByCustomParams();
//			//用户登录验证逻辑
//			TSUser u = userService.findUniqueByProperty(TSUser.class, "mobilePhone", mobile);
//
//			if(u == null || !u.getPassword().equals(PasswordUtil.encrypt(u.getUserName(),password, PasswordUtil.getStaticSalt()))){
//				result.setMsg(MutiLangUtil.getLang("common.username.or.password.error"));
//				result.setSuccess(false);
//				return result;
//			}
//
//			if (u != null && u.getStatus() != 0) {
//
//				if(u.getDeleteFlag()==1){
//					result.setMsg(MutiLangUtil.getLang("common.username.or.password.error"));
//					result.setSuccess(false);
//					return result;
//				}
//
//				if("2".equals(u.getUserType())){
//					result.setMsg(MutiLangUtil.getLang("common.user.interfaceUser"));
//					result.setSuccess(false);
//					return result;
//				}
//
//				// 处理用户有多个组织机构的情况，以弹出框的形式让用户选择
////				Map<String, Object> attrMap = new HashMap<String, Object>();
//				// 没有传组织机构参数，则获取当前用户的组织机构
//				Long orgNum = systemService.getCountForJdbcParam("select count(1) from t_s_user_org where user_id = ?",u.getId());
//				List<Map<String, Object>> orgList = systemService.findForJdbc("select org_id as orgId from t_s_user_org where user_id=?", u.getId());
//				Map<String, Object> userOrgMap = null;
//				if (orgNum > 1) {
//					userOrgMap = orgList.get(0);
//				} else {
//					userOrgMap = systemService.findOneForJdbc("select org_id as orgId from t_s_user_org where user_id=?", u.getId());
//				}
//				TSDepart depart = systemService.getEntity(TSDepart.class, userOrgMap.get("orgId").toString());
//				u.setCurrentDepart(depart);
//				u.setDepartid(userOrgMap.get("orgId").toString());
//				this.systemService.saveOrUpdate(u);
//
//				Map<String, Object> info = new HashMap<>();
//				info.put("realName", u.getRealName());
//				info.put("departname", depart.getDepartname());
//				info.put("orgIds", depart.getId());
//				info.put("sex", u.getSex());
//
//				info.put("isReadonly",  0);//只读
//				List<TSRoleUser> rUsers = this.systemService.findByProperty(TSRoleUser.class, "TSUser.id", u.getId());
//				for (TSRoleUser ru : rUsers) {
//					TSRole role = ru.getTSRole();
//					if("safety".equals(role.getRoleCode())){//安全员
//						info.put("isReadonly",  1);
//					}
//				}
//				result.setAttributes(info);
//				result.setObj(u.getId());
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			message = "访问失败";
//		}
//		result.setMsg(message);
//		return result;
//	}

//	@RequestMapping(params = "datagrid")
//	@ResponseBody
//	public AjaxJson datagrid(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
//
//		String message = null;
//		AjaxJson j = new AjaxJson();
//		message = "访问成功";
//		String token = ValidTokenUtil.checkReq(request);
//		if(StringUtil.isEmpty(token)){
//			j.setMsg("API认证失败");
//			j.setSuccess(false);
//			return j;
//		}
//
//		CriteriaQuery cq = new CriteriaQuery(TSUser.class, dataGrid);
//		//查询条件组装器
////		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, user);
//
//		Short[] userstate = new Short[]{Globals.User_Normal, Globals.User_ADMIN, Globals.User_Forbidden};
//		cq.in("status", userstate);
//		cq.eq("deleteFlag", Globals.Delete_Normal);
//		cq.eq("userType", Globals.USER_TYPE_SYSTEM);//用户列表不显示接口类型的用户
//
//		Map<String, Object> userOrgMap = systemService.findOneForJdbc("select org_id as orgId from t_s_user_org where user_id=?", token);
//		// 获取 当前组织机构的用户信息
//		if (userOrgMap !=null && StringUtil.isNotEmpty(userOrgMap.get("orgId"))) {
//			CriteriaQuery subCq = new CriteriaQuery(TSUserOrg.class);
//			subCq.setProjection(Property.forName("tsUser.id"));
//			subCq.eq("tsDepart.id", userOrgMap.get("orgId").toString());
//			subCq.add();
//			cq.add(Property.forName("id").in(subCq.getDetachedCriteria()));
//		}
//		try {
//			Field[] fields = TagUtil.getFiled(TSUser.class);
//			StringBuffer sb = new StringBuffer();
//			for(Field field : fields){
//				sb.append(field.getName()).append(",");
//			}
//			dataGrid.setField(sb.toString());
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//
//		cq.add();
//		this.systemService.getDataGridReturn(cq, true);
//		List<Map<String, Object>> cfeList = new ArrayList<>();
//		for (Object o : dataGrid.getResults()) {
//			if (o instanceof TSUser) {
//				TSUser cfe = (TSUser) o;
//				Map<String, Object> item = new HashMap<>();
//				item.put("id", cfe.getId());
//				item.put("realName", cfe.getRealName());
//				item.put("mobile", cfe.getMobilePhone());
//				cfeList.add(item);
//			}
//		}
//		Map<String, Object> resultMap = new HashMap<>();
//		resultMap.put("rows", dataGrid.getRows());
//		resultMap.put("page", dataGrid.getPage());
//		resultMap.put("total", dataGrid.getTotal());
//		resultMap.put("results", cfeList);
//		j.setAttributes(resultMap);
//
//		return j;
//	}

}
