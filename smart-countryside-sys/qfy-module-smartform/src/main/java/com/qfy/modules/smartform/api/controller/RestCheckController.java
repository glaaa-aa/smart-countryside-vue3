package com.qfy.modules.smartform.api.controller;

import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title: Controller
 * @Description: 巡检接口
 * @author MildYak
 * @date 2019-12-29 16:41:46
 * @version V1.0
 *
 */
@RestController
@RequestMapping("/checkController")
@Api(value = "巡检接口", description = "关于巡检的接口", tags = "sysCheckAPI")
public class RestCheckController{

	private static final Logger logger = LoggerFactory.getLogger(RestCheckController.class);

//	@Autowired
//	private UserService userService;
//	@Autowired
//	private SystemService systemService;
//	@Autowired
//	private CheckPlanServiceI checkPlanService;
//	@Autowired
//	private CheckPointServiceI checkPointService;
//	@Autowired
//	private DeviceInfoServiceI deviceInfoService;
//
//	@Autowired
//	private CheckItemServiceI checkItemService;
//
//	@ApiOperation(value = "巡检点", produces = "application/json", httpMethod = "POST")
//	@SuppressWarnings("rawtypes")
//	@RequestMapping(value = "/checkPointList", method = RequestMethod.POST)
//	@ResponseBody
//	public ResponseMessage<?> checkPointList(@RequestBody Map param, HttpServletRequest request, HttpServletResponse response) {
//		DataGrid dataGrid = JwtQueryUtils.getDataGrid(param);// 分页设置
//		String userName = (String) request.getAttribute(JwtConstants.CURRENT_USER_NAME);// 获取当前登录用户名
//		UserInfo userInfo = userService.getWebUserInfo(userName);// 获取用户基本信息
//
//		CriteriaQuery query = new CriteriaQuery(CheckPointEntity.class, dataGrid);
//		JwtQueryUtils.addOrgByUser(query, userInfo);// 过滤非本公司和部门
//		query.add();
//		this.systemService.getDataGridReturn(query, true);
//
//		return JwtQueryUtils.returnPage(dataGrid);
//	}
//
//	/**
//	 * 查询当前用户负责的路线
//	 *
//	 * @param param
//	 * @param request
//	 * @param response
//	 * @return
//	 */
//	@ApiOperation(value = "我负责的路线", produces = "application/json", httpMethod = "POST")
//	@SuppressWarnings("rawtypes")
//	@RequestMapping(value = "/myRouteList", method = RequestMethod.POST)
//	@ResponseBody
//	public ResponseMessage<?> myRouteList(@RequestBody Map param, HttpServletRequest request, HttpServletResponse response) {
//
//		DataGrid dataGrid = JwtQueryUtils.getDataGrid(param);
//		CriteriaQuery cq = new CriteriaQuery(CheckRouteEntity.class, dataGrid);
//		String userName = (String) request.getAttribute(JwtConstants.CURRENT_USER_NAME);// 获取当前登录用户名
//		cq.add(Property.forName("incharge").eq(userName));
//		List<CheckRouteEntity> list = this.systemService.getListByCriteriaQuery(cq, false);
//
//		List<Map<String, Object>> results = new ArrayList<>();
//		String today = DateUtils.formatDate(new Date(), "yyyy-MM-dd");
//		for (CheckRouteEntity temp : list) {
//			Map<String, Object> map = new HashMap<>();
//			MyBeanUtils.copyBean2Map(map, temp);
//			map.remove("class");
//			// 点数量
//			Long count = this.systemService.getCountForJdbcParam("select count(*) from check_route_point where chk_route_id =?", temp.getId());
//			map.put("dayrate", count);
//			// 完成情况
//			Long dayrateok = 0l;
//			String sql = "select * from inspection where inspectype = 'route' and chk_route_id = ? and sortno =? and plan_date = ? ORDER BY create_date desc LIMIT 1";
//			Map<String, Object> result = this.systemService.findOneForJdbc(sql, temp.getId(), 1, today);
//			if (result != null) {
//				if (!"9".equals(result.get("state").toString())) {
//					dayrateok = this.systemService.getCountForJdbcParam("select count(*) from inspection_point where inspection_id =? and DATE_FORMAT(checktime,'%Y-%m-%d') = ?", Integer.valueOf(result.get("id").toString()), today);
//				}
//			}
//			map.put("dayrateok", dayrateok);
//
//			results.add(map);
//		}
//
//		Map<String, Object> resultMap = new HashMap<>();
//		resultMap.put("results", results);
//		return Result.success(resultMap);
//	}
//
//	@ApiOperation(value = "巡检计划列表", produces = "application/json", httpMethod = "POST")
//	@SuppressWarnings("rawtypes")
//	@RequestMapping(value = "/list", method = RequestMethod.POST)
//	@ResponseBody
//	public ResponseMessage<?> list(@RequestBody Map param, HttpServletRequest request, HttpServletResponse response) {
//
//		DataGrid dataGrid = JwtQueryUtils.getDataGrid(param);// 分页设置
//		// 获取用户基本信息
//		String userName = (String) request.getAttribute(JwtConstants.CURRENT_USER_NAME);// 获取当前登录用户名
//		UserInfo userInfo = userService.getWebUserInfo(userName);// 获取用户基本信息
//
//		CriteriaQuery query = new CriteriaQuery(CheckPlanEntity.class, dataGrid);
//		JwtQueryUtils.addOrgByUser(query, userInfo);// 过滤非本公司和部门
//		query.add();
//		systemService.getDataGridReturn(query, true);
//		return JwtQueryUtils.returnPage(dataGrid);
//	}
//
//	@ApiOperation(value = "巡检项", produces = "application/json", httpMethod = "POST")
//	@SuppressWarnings("rawtypes")
//	@RequestMapping(value = "/checkItemList", method = RequestMethod.POST)
//	@ResponseBody
//	public ResponseMessage<?> checkItemList(@RequestBody Map param, HttpServletRequest request) {
//
//		DataGrid dataGrid = JwtQueryUtils.getDataGrid(param);// 分页设置
//
//		// 获取用户基本信息
//		String userName = (String) request.getAttribute(JwtConstants.CURRENT_USER_NAME);// 获取当前登录用户名
//		UserInfo userInfo = userService.getWebUserInfo(userName);// 获取用户基本信息
//
//		CriteriaQuery query = new CriteriaQuery(CheckItemEntity.class, dataGrid);
//		JwtQueryUtils.addOrgByUser(query, userInfo);
//		query.add();
//		systemService.getDataGridReturn(query, true);
//
//		return JwtQueryUtils.returnPage(dataGrid);
//	}
//
//	@RequestMapping(value = "/checkItemDetail", method = RequestMethod.POST)
//	@ResponseBody
//	public ResponseMessage<?> checkItemDetail(@RequestBody Map<String,String> param) {
//
//		String id = param.get("id");
//		if (StringUtils.isBlank(id)){
//			return  Result.error("缺少必要的参数！");
//		}
//
//		CheckItemEntity checkItem = this.systemService.getEntity(CheckItemEntity.class, Integer.valueOf(id));
//		// 组织返回数据格式
//		Map<String, Object> resultMap = new HashMap<>();
//		if (null != checkItem) {
//			resultMap.putAll(this.checkItemService.toMapBean(checkItem));
//		}
//		return Result.success(resultMap);
//	}
//
//	/**
//	 * 返回当前用户所在部门所有巡检路线
//	 *
//	 * @param param
//	 * @param request
//	 * @param response
//	 * @return
//	 */
//	@ApiOperation(value = "巡检路线列表", produces = "application/json", httpMethod = "POST")
//	@SuppressWarnings("rawtypes")
//	@RequestMapping(value = "/checkRouteList", method = RequestMethod.POST)
//	@ResponseBody
//	public ResponseMessage<?> checkRouteList(@RequestBody Map param, HttpServletRequest request, HttpServletResponse response) {
//
//		DataGrid dataGrid = JwtQueryUtils.getDataGrid(param);// 分页设置
//
//		// 获取用户基本信息
//		String userName = (String) request.getAttribute(JwtConstants.CURRENT_USER_NAME);// 获取当前登录用户名
//		UserInfo userInfo = userService.getWebUserInfo(userName);// 获取用户基本信息
//
//		CriteriaQuery query = new CriteriaQuery(CheckRouteEntity.class, dataGrid);
//		JwtQueryUtils.addOrgByUser(query, userInfo);// 过滤非本公司和部门
//		query.add();
//
//		this.systemService.getDataGridReturn(query, true);
//		return JwtQueryUtils.returnPage(dataGrid);
//	}
//
//	/**
//	 * 计划巡检紧急计划
//	 *
//	 * @param request
//	 * @return
//	 */
//	@ApiOperation(value = "巡检计划列表", produces = "application/json", httpMethod = "POST")
//	@RequestMapping(value = "/todayPlanJinji", method = RequestMethod.POST)
//	@ResponseBody
//	public ResponseMessage<?> todayPlanJinji(HttpServletRequest request) {
//
//		try {
//			String userName = (String) request.getAttribute(JwtConstants.CURRENT_USER_NAME);// 获取当前登录用户名
//			Map<String, Object> map = new HashMap<String, Object>();
//			// 日计划
////			Integer count = 0;
////			List<Map<String, Object>> dayList = checkPlanService.planListByDay(userName);
////
////			// 月计划
////			List<Map<String, Object>> monthList = checkPlanService.planListByMonth(userName);
////			List<Map<String, Object>> result = checkPlanService.planListByDayJinji(dayList, monthList);
////			if (dayList != null && dayList.size() > 0) {
////				count = dayList.size();
////			}
////			if (monthList != null && monthList.size() > 0) {
////				count += monthList.size();
////			}
//			checkPlanService.planListByDayJinji(userName, map);
////			map.put("unfinishtask", count);
////			map.put("result", result);
//			return Result.success(map);
//		} catch (Exception e) {
//			logger.error("获取紧急巡检计划异常：", e);
//			return Result.error("未知错误，请联系管理员");
//		}
//	}
//
//	/**
//	 * 今日计划
//	 *
//	 * @param request
//	 * @return
//	 */
//	@ApiOperation(value = "今日计划", produces = "application/json", httpMethod = "POST")
//	@RequestMapping(value = "/todayPlan", method = RequestMethod.POST)
//	@ResponseBody
//	public ResponseMessage<?> todayPlan(HttpServletRequest request) {
//		try {
//			String userName = (String) request.getAttribute(JwtConstants.CURRENT_USER_NAME);// 获取当前登录用户名
////			List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
//			// 日计划
////			List<Map<String, Object>> dayList = checkPlanService.planListByDay(userName);
//			// 月计划
////			List<Map<String, Object>> monthList = checkPlanService.planListByMonth(userName);
////			result.addAll(dayList);
////			result.addAll(monthList);
//			Map<String, Object> result = checkPlanService.CountPlanListByDay(userName);
//			return Result.success(result);
//		} catch (Exception e) {
//			logger.error("获取今日巡检计划异常：", e);
//			return Result.error("未知错误，请联系管理员");
//		}
//	}
//
//	/**
//	 * 今日计划
//	 * @param request
//	 * @return
//	 */
//	@ApiOperation(value = "公司所有用户今日计划", produces = "application/json", httpMethod = "POST")
//	@RequestMapping(value = "/todayPlanForCompany", method = RequestMethod.POST)
//	@ResponseBody
//	public ResponseMessage<?> todayPlanForCompany(HttpServletRequest request) {
//		try {
//			String userName = (String) request.getAttribute(JwtConstants.CURRENT_USER_NAME);// 获取当前登录用户名
//			UserInfo userInfo = userService.getWebUserInfo(userName);// 获取用户基本信息
//			Map<String, Object> result =  checkPlanService.CounttodayPlanForCompany(userInfo.getCompanyCode());
//			return Result.success(result);
//		} catch (Exception e) {
//			logger.error("获取今日巡检计划异常：", e);
//			return Result.error("未知错误，请联系管理员");
//		}
//	}
//
//	/**
//	 * 今日计划 紧急任务处理的集合
//	 *
//	 * @param request
//	 * @return
//	 */
//	@ApiOperation(value = "巡检计划列表", produces = "application/json", httpMethod = "POST")
//	@SuppressWarnings("rawtypes")
//	@RequestMapping(value = "/checkPlanDetail", method = RequestMethod.POST)
//	@ResponseBody
//	public ResponseMessage<?> checkPlanDetail(@RequestBody Map param, HttpServletRequest request) {
//
//		try {
//			String id = (String) param.get("id");
////			String userName = (String) request.getAttribute(JwtConstants.CURRENT_USER_NAME);// 获取当前登录用户名
//			CheckPlanEntity entity = this.checkPlanService.get(CheckPlanEntity.class, Integer.valueOf(id));
//
//			// 组织返回数据格式
//			Map<String, Object> resultMap = new HashMap<>();
//			if (entity != null) {
//				MyBeanUtils.copyBean2Map(resultMap, entity);
//				resultMap.remove("class");
//				// 路线名称
//				CheckRouteEntity routeEntity = this.systemService.getEntity(CheckRouteEntity.class, entity.getChkRouteId());
//				resultMap.put("chkRouteName", routeEntity.getName());
//				// 负责人名称
//				TSBaseUser baseUser = this.systemService.getEntity(TSBaseUser.class, entity.getIncharge());
//				if (baseUser != null) {
//					resultMap.put("incharge", baseUser.getRealName());
//				}
//			}
//			return Result.success(resultMap);
//		} catch (Exception e) {
//			logger.error("获取巡检计划详情异常：", e);
//			return Result.error("未知错误，请联系管理员");
//		}
//	}
//
//	@ApiOperation(value = "巡检路线计划详情", produces = "application/json", httpMethod = "POST")
//	@SuppressWarnings("rawtypes")
//	@RequestMapping(value = "/checkRouteDetail", method = RequestMethod.POST)
//	@ResponseBody
//	public ResponseMessage<?> checkRouteDetail(@RequestBody Map param, HttpServletRequest request) {
//
//		try {
//			String id = (String) param.get("id");
//			CheckRouteEntity entity = this.checkPlanService.get(CheckRouteEntity.class, Integer.valueOf(id));
//
//			// 组织返回数据格式
//			Map<String, Object> resultMap = new HashMap<>();
//			if (entity != null) {
//				List<CheckPointGroup> list = checkPointService.getPointsByRouteId(entity.getId());
//				MyBeanUtils.copyBean2Map(resultMap, entity);
//				resultMap.remove("class");
//				resultMap.put("groups", list);
//			}
//			return Result.success(resultMap);
//		} catch (Exception e) {
//			logger.error("获取巡检计划列表异常：", e);
//			return Result.error("未知错误，请联系管理员");
//		}
//	}
//
//	@ApiOperation(value = "巡检计划详情", produces = "application/json", httpMethod = "POST")
//	@SuppressWarnings("rawtypes")
//	@RequestMapping(value = "/todayPlanDetails", method = RequestMethod.POST)
//	@ResponseBody
//	public ResponseMessage<?> todayPlanDetails(@RequestBody Map<String, String> param) {
//
//		try {
//			String inspectId = param.get("id");// 任务id---inspectId
//			Map<String, Object> resultMap = null;
//			if (StringUtils.isNotBlank(inspectId)) {
//				resultMap = this.checkPlanService.planDetails(Integer.parseInt(inspectId));
//			}else {
//				logger.error("巡检计划详情接口传参：任务Id为空！");
//				resultMap = Collections.emptyMap();
//			}
//
//			return Result.success(resultMap);
//		} catch (Exception e) {
//			logger.error("获取巡检计划详情异常:", e);
//			return Result.error("未知错误，请联系管理员");
//		}
//	}
//
//	/**
//	 * 根据巡检点编号获取巡检点基本信息
//	 *
//	 * @param param
//	 * @param request
//	 * @return
//	 */
//	@ApiOperation(value = "巡检点基本信息", produces = "application/json", httpMethod = "POST")
//	@SuppressWarnings("rawtypes")
//	@RequestMapping(value = "/checkPointDetail", method = RequestMethod.POST)
//	@ResponseBody
//	public ResponseMessage<?> checkPointDetail(@RequestBody Map param, HttpServletRequest request) {
//
//		try {
//			String serialno = (String) param.get("serialno");
//			// 查询详细配置
//			Map<String, Object> checkPoint = this.checkPointService.detailsByNo(serialno);
//			Map<String, Object> deviceInfo = deviceInfoService.details(serialno);
//
//			Map<String, Object> resultMap = new HashMap<>();
//			resultMap.put("checkPoint", checkPoint);
//			resultMap.put("deviceInfo", deviceInfo);
//			return Result.success(resultMap);
//		} catch (Exception e) {
//			logger.error("获取巡检点基本信息异常：", e);
//			return Result.error("未知错误，请联系管理员");
//		}
//	}
//
//	/**
//	 * 根据巡检点编号获取明细
//	 *
//	 * @param param
//	 * @param request
//	 * @return
//	 */
//	@ApiOperation(value = "二维码扫描", produces = "application/json", httpMethod = "POST")
//	@SuppressWarnings("rawtypes")
//	@RequestMapping(value = "/scanPointDetail", method = RequestMethod.POST)
//	@ResponseBody
//	public ResponseMessage<?> scanPointDetail(@RequestBody Map<String, String> param, HttpServletRequest request) {
//
//		try {
//			String serialno = param.get("serialno");
//			String chkRouteId = param.get("chkRouteId");
//			String taskId = param.get("taskId");
//			if (StringUtils.isBlank(serialno) || StringUtils.isBlank(chkRouteId)){
//				return Result.error("缺少必要的请求参数！");
//			}
//
//			Map<String, Object> resultMap = new HashMap<>();
//			// 获取用户基本信息
//			String userName = (String) request.getAttribute(JwtConstants.CURRENT_USER_NAME);// 获取当前登录用户名
////			UserInfo userInfo = userService.getWebUserInfo(userName);// 获取用户基本信息
//			// 判断是否是设备
//			int isDevice = 0;
//			DeviceInfoEntity deviceInfo = this.systemService.findUniqueByProperty(DeviceInfoEntity.class, "deviceno", serialno);
//			if (deviceInfo != null) {
//				isDevice = 1;
//			}
//			// 判断是否巡检点
//			int isPoint = 1;
//			CheckPointEntity pointEntity = this.systemService.findUniqueByProperty(CheckPointEntity.class, "serialno", serialno);
//			if (pointEntity == null) {
//				isPoint = 0;
//			} else {
//				Map<String, Object> objMap = new HashMap<>();
//				// 查询今日要执行的计划
//				if (StringUtils.isNotBlank(taskId)) {// 计划巡检
//					// 日计划
//					List<Map<String, Object>> dayList = checkPlanService.planListByDay(userName);
//					objMap = scanResultMap(serialno, dayList);
//					// 月计划
//					if (objMap == null) {
//						List<Map<String, Object>> monthList = checkPlanService.planListByMonth(userName);
//						objMap = scanResultMap(serialno, monthList);
//					}
//				} else {
//					objMap.put("chkRouteId", chkRouteId);
//					objMap.put("sortno", 1);
//					objMap.put("inspectype", "route");
//				}
//
//				// 组装返回数据-查询巡检项信息
//				resultMap = this.checkPointService.detailsByNo_v2(serialno);
//
//				if (objMap != null) {
//					if (objMap.containsKey("chkPlanId")) {
//						resultMap.put("chkPlanId", objMap.get("chkPlanId"));
//					}
//					resultMap.put("chkRouteId", objMap.get("chkRouteId"));
//					resultMap.put("sortno", objMap.get("sortno"));
//					resultMap.put("inspectype", objMap.get("inspectype"));
//				} else {
//					resultMap.put("sortno", 1);
//					resultMap.put("inspectype", "move");
//				}
//			}
//
//			resultMap.put("isPoint", isPoint);// 是否巡检点 0否 1 是
//			resultMap.put("isDevice", isDevice);// 是否设备 0否 1 是
//			resultMap.put("serialno", serialno);// 编码
//			return Result.success(resultMap);
//		} catch (Exception e) {
//			logger.error("获取二维码扫描异常：", e);
//			return Result.error("未知错误，请联系管理员");
//		}
//	}
//
//	@SuppressWarnings({ "rawtypes", "unchecked" })
//	private Map<String, Object> scanResultMap(String serialno, List<Map<String, Object>> dayList) {
//		String inspectype = "move";
//		Integer chkRouteId = null;
//		Integer chkPlanId = null;
//		Integer sortno = 0;
//		for (Map<String, Object> map : dayList) {
//			// 查询计划下的巡检点集合，判断是否包含并未完成
//			chkPlanId = Integer.valueOf(map.get("chkPlanId").toString());
//			Map<String, Object> planDetails = this.checkPlanService.planDetails(chkPlanId, null);
//			List childrens = (List) planDetails.get("childrens");
//			if (childrens != null && childrens.size() > 0) {
//				Map<String, Object> planMap = (Map) childrens.get(0);
//				Integer pointnum = (Integer) planMap.get("pointnum");
//				Integer pointnumok = (Integer) planMap.get("pointnumok");
//				if (pointnumok < pointnum) {// 还有点未完成
//					String unCheckPoints = planMap.get("unCheckPoints").toString();
//					if (unCheckPoints.contains(serialno)) {// 未巡检 并且 编码相同
//						// 计划巡检
//						inspectype = "plan";
//						sortno = Integer.valueOf(planMap.get("sortno").toString());
//						chkRouteId = Integer.valueOf(planMap.get("chkRouteId").toString());
//						break;// 已经取到计划结束循环
//					}
//				}
//			}
//		}
//
//		if ("plan".equals(inspectype)) {
//			Map<String, Object> resultMap = new HashMap<>();
//			resultMap.put("chkRouteId", chkRouteId);
//			resultMap.put("chkPlanId", chkPlanId);
//			resultMap.put("sortno", sortno);
//			resultMap.put("inspectype", inspectype);
//			return resultMap;
//		}
//
//		return null;
//	}
//
//	/**
//	 * 路线详情
//	 *
//	 * @param request
//	 * @return
//	 */
//	@ApiOperation(value = "路线详情", produces = "application/json", httpMethod = "POST")
//	@SuppressWarnings("rawtypes")
//	@RequestMapping(value = "/routeDetails", method = RequestMethod.POST)
//	@ResponseBody
//	public ResponseMessage<?> routeDetails(@RequestBody Map param, HttpServletRequest request) {
//		try {
//			String chkRouteId = (String) param.get("chkRouteId");
//			Map<String, Object> resultMap = this.checkPlanService.routeDetails(Integer.valueOf(chkRouteId));
//			return Result.success(resultMap);
//		} catch (Exception e) {
//			logger.error("获取路线详情异常：", e);
//			return Result.error("系统错误");
//		}
//	}

}
