package com.qfy.modules.smartform.api.controller;

import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title: Controller
 * @Description: 用户管理接口
 * @author MildYak
 * @date 2019-12-29 16:41:46
 * @version V1.0
 *
 */
@RestController
@RequestMapping("/userController")
@Api(value = "用户管理", description = "关于用户的接口", tags = "sysUserAPI")
public class RestUserController {

	private static final Logger logger = LoggerFactory.getLogger(RestUserController.class);

//	@Autowired
//	private SystemService systemService;
//	@Autowired
//	private UserService userService;
//
//	@ApiOperation(value = "当前登录用户信息", produces = "application/json", httpMethod = "POST")
//	@RequestMapping(value="/info", method = RequestMethod.POST)
//	@ResponseBody
//	public ResponseMessage<?> list(HttpServletRequest request, HttpServletResponse response) {
//		//获取用户基本信息
//		String userName = (String) request.getAttribute(JwtConstants.CURRENT_USER_NAME);//获取当前登录用户名
//		UserInfo userInfo = userService.getWebUserInfo(userName);
//		return Result.success(userInfo);
//	}
//
//
//	/**
//	 * 查询当前登录用户所有用户
//	 * @param param
//	 * @param request
//	 * @param response
//	 * @return
//	 */
//	@ApiOperation(value = "用户列表", produces = "application/json", httpMethod = "POST")
//	@RequestMapping(value="/list", method = RequestMethod.POST)
//	@ResponseBody
//	public ResponseMessage<?> list(@RequestBody Map param, HttpServletRequest request, HttpServletResponse response) {
//
//		//分页设置
//		DataGrid dataGrid = JwtQueryUtils.getDataGrid(param);
//
//		//获取用户基本信息
//		String userName = (String) request.getAttribute(JwtConstants.CURRENT_USER_NAME);//获取当前登录用户名
//		UserInfo userInfo = userService.getWebUserInfo(userName);//获取用户基本信息
//
//		CriteriaQuery query = new CriteriaQuery(VUser.class, dataGrid);
//		JwtQueryUtils.addOrgByUser(query, userInfo);
//		query.add();
//		this.systemService.getDataGridReturn(query, true);
//
//		return JwtQueryUtils.returnPage(dataGrid);
//	}




}
