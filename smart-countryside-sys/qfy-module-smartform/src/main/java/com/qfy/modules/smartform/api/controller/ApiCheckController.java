package com.qfy.modules.smartform.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qfy.modules.smartform.api.common.AjaxJson;
import com.qfy.modules.smartform.api.common.DataGrid;
import com.qfy.modules.smartform.entity.CheckItem;
import com.qfy.modules.smartform.service.ICheckItemService;
import com.qfy.modules.smartform.service.ICheckPlanService;
import com.qfy.modules.smartform.service.ICheckPointService;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.jeecg.common.system.query.QueryGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title: Controller
 * @Description: 二维码表
 * @author onlineGenerator
 * @date 2019-04-17 15:53:21
 * @version V1.0
 *
 */
@RestController
@RequestMapping("/api/checkController")
public class ApiCheckController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApiCheckController.class);

//	@Autowired
//	private SystemService systemService;
	@Autowired
	private ICheckPlanService checkPlanService;
	@Autowired
	private ICheckPointService checkPointService;
	@Autowired
	private ICheckItemService checkItemService;
//	@Autowired
//	private IDeviceInfoService deviceInfoService;

	/**
	 * 今日计划
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "todayPlan")
	@ResponseBody
	public AjaxJson todayPlan(HttpServletRequest request) {

//		String message = null;
		AjaxJson j = new AjaxJson();
//		message = "访问成功";
//
//		String token = ValidTokenUtil.checkReq(request);
//		if (StringUtils.isEmpty(token)) {
//			j.setMsg("API认证失败");
//			j.setSuccess(false);
//			return j;
//		}
//
//		TSBaseUser baseUser = this.systemService.getEntity(TSBaseUser.class, token);
//
//		try {
//			List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
//			// 日计划
//			List<Map<String, Object>> dayList = checkPlanService.planListByDay(baseUser.getUserName());
//
//			// 月计划
//			List<Map<String, Object>> monthList = checkPlanService.planListByMonth(baseUser.getUserName());
//			result.addAll(dayList);
//			result.addAll(monthList);
//			j.setObj(result);
//		} catch (Exception e) {
//			e.printStackTrace();
//			message = "访问失败";
//			throw new BusinessException(e.getMessage());
//		}
//		j.setMsg(message);
		return j;
	}

	/**
	 * 今日计划 紧急任务处理的集合
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "todayPlanJinji")
	@ResponseBody
	public AjaxJson todayPlanJinji(HttpServletRequest request) {

		String message = null;
		AjaxJson j = new AjaxJson();
		message = "访问成功";

//		String token = ValidTokenUtil.checkReq(request);
//		if (StringUtil.isEmpty(token)) {
//			j.setMsg("API认证失败");
//			j.setSuccess(false);
//			return j;
//		}
//
//		TSBaseUser baseUser = this.systemService.getEntity(TSBaseUser.class, token);
//
////		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
//		try {
//			Map<String, Object> map = new HashMap<String, Object>();
//			// 日计划
//			Integer count = 0;
//			List<Map<String, Object>> dayList = checkPlanService.planListByDay(baseUser.getUserName());
//
//			// 月计划
//			List<Map<String, Object>> monthList = checkPlanService.planListByMonth(baseUser.getUserName());
//			List<Map<String, Object>> result = checkPlanService.planListByDayJinji(dayList, monthList);
//			if (dayList != null && dayList.size() > 0) {
//				count = dayList.size();
//			}
//			if (monthList != null && monthList.size() > 0) {
//				count += monthList.size();
//			}
//			map.put("count", count);
//			j.setObj(result);
//			j.setAttributes(map);
//		} catch (Exception e) {
//			e.printStackTrace();
//			message = "访问失败";
//			throw new BusinessException(e.getMessage());
//		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 今日计划详情
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "todayPlanDetails")
	@ResponseBody
	public AjaxJson todayPlanDetails(HttpServletRequest request) {
		String token = request.getHeader("token");
		String planid = request.getParameter("planid");
		String planDate = request.getParameter("planDate");
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "访问成功";
//		try {
//			Map<String, Object> resultMap = new HashMap<>();
//			if (StringUtil.isNotEmpty(planDate)) {
////				resultMap = this.inspectionService.planDetails(Integer.valueOf(planid), planDate);
//			} else {
//				resultMap = this.checkPlanService.planDetails(Integer.valueOf(planid), null);
//			}
//			j.setAttributes(resultMap);
//		} catch (Exception e) {
//			e.printStackTrace();
//			message = "访问失败";
//			throw new BusinessException(e.getMessage());
//		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 根据检查点ID查询检查项配置信息
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "pointDeatils")
	@ResponseBody
	public AjaxJson pointDeatils(HttpServletRequest request) {
		String token = request.getHeader("token");
		String serialno = request.getParameter("serialno");
		String chkRouteId = request.getParameter("chkRouteId");
		String chkPlanId = request.getParameter("chkPlanId");
		String sortno = request.getParameter("sortno");
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "访问成功";
//		try {
//
//			String inspectype = "move";
//			// 判断记录类型
//			if (StringUtil.isNotEmpty(chkRouteId)) {
//				if (StringUtil.isNotEmpty(chkPlanId)) {// 计划巡检
//					inspectype = "plan";
//
//				} else {// 计划外路线巡检
//					inspectype = "route";
//				}
//			}
//
//			// 查询详细配置
//			Map<String, Object> resultMap = this.checkPointService.detailsByNo(serialno);
//			resultMap.put("chkRouteId", chkRouteId);
//			resultMap.put("chkPlanId", chkPlanId);
//			resultMap.put("sortno", sortno);
//			resultMap.put("inspectype", inspectype);
//			j.setAttributes(resultMap);
//		} catch (Exception e) {
//			e.printStackTrace();
//			message = "访问失败";
//			throw new BusinessException(e.getMessage());
//		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 巡检计划
	 *
	 * @param checkPlan
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @return
	 */
//	@RequestMapping(params = "checkPlanList")
//	@ResponseBody
//	public AjaxJson checkPlanList(CheckPlanEntity checkPlan, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
//		String message = null;
//		AjaxJson j = new AjaxJson();
//		message = "访问成功";
//
//		String token = ValidTokenUtil.checkReq(request);
//		if (StringUtil.isEmpty(token)) {
//			j.setMsg("API认证失败");
//			j.setSuccess(false);
//			return j;
//		}
//
//		CriteriaQuery cq = new CriteriaQuery(CheckPlanEntity.class, dataGrid);
//
//		// 查询条件组装器
//		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, checkPlan, request.getParameterMap());
//		try {
//			// 自定义追加查询条件
//			Field[] fields = TagUtil.getFiled(CheckPlanEntity.class);
//			StringBuffer sb = new StringBuffer();
//			for (Field field : fields) {
//				sb.append(field.getName()).append(",");
//			}
//			dataGrid.setField(sb.toString());
//			cq.add(Property.forName("incharge").eq(token));
//			this.checkPlanService.getDataGridReturn(cq, true);
//
//		} catch (Exception e) {
//			throw new BusinessException(e.getMessage());
//		}
//		Map<String, Object> resultMap = new HashMap<>();
//		resultMap.put("rows", dataGrid.getRows());
//		resultMap.put("page", dataGrid.getPage());
//		resultMap.put("total", dataGrid.getTotal());
//		resultMap.put("results", dataGrid.getResults());
//		j.setAttributes(resultMap);
//		return j;
//	}

	/**
	 * 巡检项
	 *
	 * @param checkItem
	 * @param request
	 * @param dataGrid
	 * @return
	 */
	@RequestMapping(params = "checkItemList")
	public AjaxJson checkItemList(CheckItem checkItem, HttpServletRequest request, DataGrid dataGrid) {
		AjaxJson result = new AjaxJson();
		QueryWrapper<CheckItem> query = QueryGenerator.initQueryWrapper(checkItem, request.getParameterMap());
		Page<CheckItem> page = new Page<CheckItem>(dataGrid.getPage(), dataGrid.getRows());
		IPage<CheckItem> pageList = checkItemService.page(page, query);
		// 组织返回数据格式
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("rows", dataGrid.getRows());
		resultMap.put("page", dataGrid.getPage());
		resultMap.put("total", pageList.getTotal());
		resultMap.put("results", pageList.getRecords());
		result.setAttributes(resultMap);
		return result;
	}

	/**
	 * 巡检路线
	 *
	 * @param checkRoute
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @return
	 */
//	@RequestMapping(params = "checkRouteList")
//	@ResponseBody
//	public AjaxJson checkRouteList(CheckRouteEntity checkRoute, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
//		String message = null;
//		AjaxJson j = new AjaxJson();
//		message = "访问成功";
//
//		CriteriaQuery cq = new CriteriaQuery(CheckRouteEntity.class, dataGrid);
//		// 查询条件组装器
//		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, checkRoute, request.getParameterMap());
//		try {
//			// 自定义追加查询条件
//			Field[] fields = TagUtil.getFiled(CheckItemEntity.class);
//			StringBuffer sb = new StringBuffer();
//			for (Field field : fields) {
//				sb.append(field.getName()).append(",");
//			}
//			dataGrid.setField(sb.toString());
//		} catch (Exception e) {
//			throw new BusinessException(e.getMessage());
//		}
//		cq.add();
//		this.systemService.getDataGridReturn(cq, true);
//
//		// 组织返回数据格式
//		Map<String, Object> resultMap = new HashMap<>();
//		resultMap.put("rows", dataGrid.getRows());
//		resultMap.put("page", dataGrid.getPage());
//		resultMap.put("total", dataGrid.getTotal());
//		resultMap.put("results", dataGrid.getResults());
//		j.setAttributes(resultMap);
//		return j;
//	}

	/**
	 * 巡检点
	 *
	 * @param checkPoint
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @return
	 */
//	@RequestMapping(params = "checkPointList")
//	@ResponseBody
//	public AjaxJson checkPointList(CheckPointEntity checkPoint, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
//		String message = null;
//		AjaxJson j = new AjaxJson();
//		message = "访问成功";
//
//		CriteriaQuery cq = new CriteriaQuery(CheckPointEntity.class, dataGrid);
//		// 查询条件组装器
//		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, checkPoint, request.getParameterMap());
//		try {
//			// 自定义追加查询条件
//			Field[] fields = TagUtil.getFiled(CheckItemEntity.class);
//			StringBuffer sb = new StringBuffer();
//			for (Field field : fields) {
//				sb.append(field.getName()).append(",");
//			}
//			dataGrid.setField(sb.toString());
//		} catch (Exception e) {
//			throw new BusinessException(e.getMessage());
//		}
//		cq.add();
//		this.systemService.getDataGridReturn(cq, true);
//
//		// 组织返回数据格式
//		Map<String, Object> resultMap = new HashMap<>();
//		resultMap.put("rows", dataGrid.getRows());
//		resultMap.put("page", dataGrid.getPage());
//		resultMap.put("total", dataGrid.getTotal());
//		resultMap.put("results", dataGrid.getResults());
//		j.setAttributes(resultMap);
//		return j;
//	}

//	@RequestMapping(params = "checkPlanDetail")
//	@ResponseBody
//	public AjaxJson checkPlanDetail(HttpServletRequest request) {
//		String message = null;
//		AjaxJson j = new AjaxJson();
//		message = "访问成功";
//		String id = request.getParameter("id");
//		CheckPlanEntity entity = this.checkPlanService.get(CheckPlanEntity.class, Integer.valueOf(id));
//
//		// 组织返回数据格式
//		Map<String, Object> resultMap = new HashMap<>();
//		if (entity != null) {
//			MyBeanUtils.copyBean2Map(resultMap, entity);
//			resultMap.remove("class");
//			// 路线名称
//			CheckRouteEntity routeEntity = this.systemService.getEntity(CheckRouteEntity.class, entity.getChkRouteId());
//			resultMap.put("chkRouteName", routeEntity.getName());
//			// 负责人名称
//			TSBaseUser baseUser = this.systemService.getEntity(TSBaseUser.class, entity.getIncharge());
//			if (baseUser != null) {
//				resultMap.put("incharge", baseUser.getRealName());
//			}
//		}
//		j.setAttributes(resultMap);
//		return j;
//	}

//	@RequestMapping(params = "checkRouteDetail")
//	@ResponseBody
//	public AjaxJson checkRouteDetail(HttpServletRequest request) {
//		String message = null;
//		AjaxJson j = new AjaxJson();
//		message = "访问成功";
//		String id = request.getParameter("id");
//		CheckRouteEntity entity = this.checkPlanService.get(CheckRouteEntity.class, Integer.valueOf(id));
//
//		// 组织返回数据格式
//		Map<String, Object> resultMap = new HashMap<>();
//		if (entity != null) {
//			List<CheckPointGroup> list = checkPointService.getPointsByRouteId(entity.getId());
//
//			// 第二步、查询所有检查选项巡检点
//			// CriteriaQuery cq = new CriteriaQuery(CheckPointEntity.class);
//			//// 查询 子表查询
//			// CriteriaQuery subCq = new CriteriaQuery(CheckRoutePointEntity.class);
//			// subCq.setProjection(Property.forName("chkPoiId"));
//			// subCq.eq("chkRouteId", entity.getId());
//			// subCq.add();
//			// cq.add(Property.forName("id").in(subCq.getDetachedCriteria()));
//			// List<CheckPointEntity> list = this.systemService.getListByCriteriaQuery(cq,
//			// false);
//
//			// List<Map<String, Object>> pointList = new ArrayList<>();
//			// for(CheckPointEntity pointEntity : list ){
//			// Map<String, Object> item = new HashMap<>();
//			// MyBeanUtils.copyBean2Map(item, pointEntity);
//			// item.remove("class");
//			// pointList.add(item);
//			// }
//
//			MyBeanUtils.copyBean2Map(resultMap, entity);
//			resultMap.remove("class");
//
//			resultMap.put("groups", list);
//		}
//		j.setAttributes(resultMap);
//		return j;
//	}

	/**
	 * 查询巡检点-巡检项配置
	 *
	 * @param request
	 * @return
	 */
//	@RequestMapping(params = "checkPointDetail")
//	@ResponseBody
//	public AjaxJson checkPointDetail(HttpServletRequest request) {
//		String message = null;
//		AjaxJson j = new AjaxJson();
//		message = "访问成功";
//		String serialno = request.getParameter("serialno");
//		// 查询详细配置
//
//		Map<String, Object> checkPoint = this.checkPointService.detailsByNo(serialno);
//		Map<String, Object> deviceInfo = deviceInfoService.details(serialno);
//
//		Map<String, Object> resultMap = new HashMap<>();
//		resultMap.put("checkPoint", checkPoint);
//		resultMap.put("deviceInfo", deviceInfo);
//		j.setAttributes(resultMap);
//		return j;
//	}

	/**
	 * 巡检项详情
	 *
	 * @return
	 */
//	@RequestMapping(params = "checkItemDetail")
//	public AjaxJson checkItemDetail(@RequestParam("id") Integer id) {
//		AjaxJson result = new AjaxJson();
//
//		CheckItem checkItem = checkItemService.getById(id);
//		// 组织返回数据格式
//		Map<String, Object> resultMap = new HashMap<>();
//		checkItemService.toMap(checkItem, resultMap);
//		result.setAttributes(resultMap);
//		return result;
//	}

	/**
	 * 二维码扫描返回检查点详情
	 *
	 * @param request
	 * @param serialno
	 * @return
	 */
//	@RequestMapping(params = "scanPointDetail")
//	@ResponseBody
//	public AjaxJson scanPointDetail(HttpServletRequest request, @RequestParam(required = true) String serialno) {
//		String message = null;
//		AjaxJson j = new AjaxJson();
//		message = "访问成功";
//
//		String token = ValidTokenUtil.checkReq(request);
//		if (StringUtil.isEmpty(token)) {
//			j.setMsg("API认证失败");
//			j.setSuccess(false);
//			return j;
//		}
//		Map<String, Object> resultMap = new HashMap<>();
//
//		// 判断是否是设备
//		int isDevice = 0;
//		DeviceInfoEntity deviceInfo = this.systemService.findUniqueByProperty(DeviceInfoEntity.class, "deviceno", serialno);
//		if (deviceInfo != null) {
//			isDevice = 1;
//		}
//		// 判断是否巡检点
//		int isPoint = 1;
//		CheckPointEntity pointEntity = this.systemService.findUniqueByProperty(CheckPointEntity.class, "serialno", serialno);
//		if (pointEntity == null) {
//			isPoint = 0;
//		} else {
//			Map<String, Object> objMap = new HashMap<>();
//			// 查询今日要执行的计划
//			String chkRouteId = request.getParameter("chkRouteId");
//			if (StringUtil.isEmpty(chkRouteId)) {// 计划巡检
//				TSBaseUser baseUser = this.systemService.getEntity(TSBaseUser.class, token);
//				// 日计划
//				List<Map<String, Object>> dayList = checkPlanService.planListByDay(baseUser.getUserName());
//				objMap = scanResultMap(serialno, dayList);
//				// 月计划
//				if (objMap == null) {
//					List<Map<String, Object>> monthList = checkPlanService.planListByMonth(baseUser.getUserName());
//					objMap = scanResultMap(serialno, monthList);
//				}
//			} else {
//				objMap.put("chkRouteId", chkRouteId);
//				objMap.put("sortno", 1);
//				objMap.put("inspectype", "route");
//			}
//
//			// 组装返回数据-查询巡检项信息
//			resultMap = this.checkPointService.detailsByNo_v2(serialno);
//
//			if (objMap != null) {
//				if (objMap.containsKey("chkPlanId")) {
//					resultMap.put("chkPlanId", objMap.get("chkPlanId"));
//				}
//				resultMap.put("chkRouteId", objMap.get("chkRouteId"));
//				resultMap.put("sortno", objMap.get("sortno"));
//				resultMap.put("inspectype", objMap.get("inspectype"));
//			} else {
//				resultMap.put("sortno", 1);
//				resultMap.put("inspectype", "move");
//			}
//		}
//
//		resultMap.put("isPoint", isPoint);// 是否巡检点 0否 1 是
//		resultMap.put("isDevice", isDevice);// 是否设备 0否 1 是
//		resultMap.put("serialno", serialno);// 编码
//		j.setAttributes(resultMap);
//		LOGGER.info("响应结果：{}", JSONUtils.valueToString(resultMap));
//		return j;
//	}

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

	/**
	 * 巡检路线
	 *
	 * @param checkRoute
	 * @param request
	 * @param response
	 * @return
	 */
//	@RequestMapping(params = "myRouteList")
//	@ResponseBody
//	public AjaxJson myRouteList(CheckRouteEntity checkRoute, HttpServletRequest request, HttpServletResponse response) {
//		String message = null;
//		AjaxJson j = new AjaxJson();
//		message = "访问成功";
//
//		CriteriaQuery cq = new CriteriaQuery(CheckRouteEntity.class);
//		try {
//			// 自定义追加查询条件
//		} catch (Exception e) {
//			throw new BusinessException(e.getMessage());
//		}
//		cq.add();
//		List<CheckRouteEntity> list = this.systemService.getListByCriteriaQuery(cq, false);
//
//		List<Map<String, Object>> results = new ArrayList<>();
//		for (CheckRouteEntity temp : list) {
//			Map<String, Object> map = new HashMap<>();
//			MyBeanUtils.copyBean2Map(map, temp);
//			map.remove("class");
//			// 点数量
//			Long count = this.systemService.getCountForJdbcParam("select count(*) from check_route_point where chk_route_id =?", temp.getId());
//			map.put("dayrate", count);
//			// 完成情况
//			Long dayrateok = 0l;
//			String sql = "select * from inspection where inspectype = 'route' and chk_route_id = ? and sortno =? ORDER BY create_date desc LIMIT 1";
//			Map<String, Object> result = this.systemService.findOneForJdbc(sql, temp.getId(), 1);
//			if (result != null) {
//				if (!"9".equals(result.get("state").toString())) {
//					dayrateok = this.systemService.getCountForJdbcParam("select count(*) from inspection_point where inspection_id =?", Integer.valueOf(result.get("id").toString()));
//				}
//			}
//			map.put("dayrateok", dayrateok);
//
//			results.add(map);
//		}
//
//		j.setObj(results);
//		return j;
//	}

	/**
	 * 路线详情
	 *
	 * @param request
	 * @return
	 */
//	@RequestMapping(params = "routeDetails")
//	@ResponseBody
//	public AjaxJson routeDetails(HttpServletRequest request) {
//
//		String message = null;
//		AjaxJson j = new AjaxJson();
//		message = "访问成功";
//		String token = ValidTokenUtil.checkReq(request);
//		try {
//			String chkRouteId = request.getParameter("chkRouteId");
//			Map<String, Object> resultMap = this.checkPlanService.routeDetails(Integer.valueOf(chkRouteId));
//			j.setAttributes(resultMap);
//		} catch (Exception e) {
//			e.printStackTrace();
//			message = "访问失败";
//			throw new BusinessException(e.getMessage());
//		}
//		j.setMsg(message);
//		return j;
//	}

	/**
	 * 新增巡检记录
	 *
	 * @param request
	 * @return
	 */
//	@ApiOperation(value = "巡检计划列表", produces = "application/json", httpMethod = "POST")
//	@RequestMapping(value = "/routeDetails", method = RequestMethod.POST)
//	@ResponseBody
//	public ResponseMessage<?> routeDetails(@RequestBody Map param, HttpServletRequest request) {
//		String message = null;
//		AjaxJson j = new AjaxJson();
//		message = "访问成功";
//
//		try {
//			String userName = (String) request.getAttribute(JwtConstants.CURRENT_USER_NAME);// 获取当前登录用户名
//			String chkRouteId = (String) param.get("chkRouteId");
//			Map<String, Object> resultMap = this.checkPlanService.routeDetails(Integer.valueOf(chkRouteId));
//			return Result.success(resultMap);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return Result.error("未知错误，请联系管理员");
//		}
//	}
}
