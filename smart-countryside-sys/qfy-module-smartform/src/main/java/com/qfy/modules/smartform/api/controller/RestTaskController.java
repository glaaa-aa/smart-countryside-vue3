package com.qfy.modules.smartform.api.controller;

import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author MildYak
 * @version V1.0
 * @Title: Controller
 * @Description: 任务接口
 * @date 2019-12-29 16:41:46
 */
@RestController
@RequestMapping("/taskController")
@Api(value = "巡检接口", description = "关于巡检的接口", tags = "sysCheckAPI")
public class RestTaskController {
	private static final Logger logger = LoggerFactory.getLogger(RestTaskController.class);

//	@Autowired
//	private SystemService systemService;
//	@Autowired
//	private UserService userService;
//	@Autowired
//	private TaskServiceI taskService;
//	@Autowired
//	private TaskFeedbackServiceI taskFeedbackService;
//
//	/**
//	 * 根据巡检点编号获取该点所有任务记录，可分页。
//	 *
//	 * @param param
//	 * @param request
//	 * @param response
//	 * @return
//	 */
//	@SuppressWarnings("unchecked")
//	@ApiOperation(value = "巡检点任务历史", produces = "application/json", httpMethod = "POST")
//	@RequestMapping(value = "/datagrid", method = RequestMethod.POST)
//	@ResponseBody
//	public ResponseMessage<?> datagrid(@RequestBody Map param, HttpServletRequest request,
//			HttpServletResponse response) {
//
//		DataGrid dataGrid = JwtQueryUtils.getDataGrid(param);// 分页设置
//		String serialno = (String) param.get("serialno");
//		Integer type = Integer.valueOf(param.getOrDefault("type", "-1").toString());// 类型 0 我创建的 1我负责的
//		String checkStep = (String) param.get("checkStep"); // 状态
//
//		// 获取用户基本信息
//		String userName = (String) request.getAttribute(JwtConstants.CURRENT_USER_NAME);// 获取当前登录用户名
//		UserInfo userInfo = userService.getWebUserInfo(userName);// 获取用户基本信息
//
//		CommonParamsDto paramsDto = new CommonParamsDto();
//		paramsDto.setPage(dataGrid.getPage());
//		paramsDto.setRows(dataGrid.getRows());
//
//		if (StringUtil.isNotEmpty(serialno)) {
//			paramsDto.setSerialno(serialno);// 设备号
//		}
//
//		if (type != null && 0 == type) {
//			paramsDto.setCreateuser(userName);// 我发起的任务
//		}
//
//		if (type != null && 1 == type) {
//			paramsDto.setReviewer(userInfo.getId());// 该我的处理的任务
//		}
//
//		if (StringUtil.isNotEmpty(checkStep)) {
//			paramsDto.setCheckStep(checkStep);
//		}
//
//		if (!userInfo.getCompanyCode().equals(userInfo.getOrgCode())) {
//			// 该员工为部门员工只能查询本部门下的数据
//			paramsDto.setSysOrgCode(userInfo.getOrgCode());
//		}
//		// 所有人都只能查询当前公司下的数据
//		paramsDto.setSysCompanyCode(userInfo.getCompanyCode());
//
//		List<TaskEntity> list = null;
//		MiniDaoPage<TaskEntity> daoPage = null;
//		try {
//			daoPage = taskService.queryByPage(paramsDto);
//			list = daoPage.getResults();
//		} catch (Exception e) {
//			logger.error("查询任务列表异常：", e);
//			return Result.error("查询任务列表失败.");
//		}
//
//		List<String> ids = new ArrayList<>();
//		for (TaskEntity temp : list) {
//			if (StringUtils.isNotBlank(temp.getReviewer()) && !ids.contains(temp.getReviewer())) {
//				ids.add(temp.getReviewer());
//			}
//		}
//
//		// 获取用户信息
//		List<TSBaseUser> baseUserList = new ArrayList<>();
//		if (ids.size() > 0) {
//			CriteriaQuery cq1 = new CriteriaQuery(TSBaseUser.class);
//			cq1.add(Property.forName("id").in(ids));
//			baseUserList = this.systemService.getListByCriteriaQuery(cq1, false);
//		}
//		List<Map<String, Object>> results = new ArrayList<>();
//		for (TaskEntity temp : list) {
//			Map<String, Object> obj = new HashMap<>();
//			MyBeanUtils.copyBean2Map(obj, temp);
//			obj.remove("class");
//			obj.put("isSchedule", TaskUtils.isSchedule(temp));
//			for (TSBaseUser baseUser : baseUserList) {
//				if (temp.getReviewer() != null && temp.getReviewer().equals(baseUser.getId())) {
//					obj.put("reviewerName", baseUser.getRealName());
//					break;
//				}
//			}
//			results.add(obj);
//		}
//
//		// 组织返回数据格式
////		return JwtQueryUtils.returnPage(dataGrid, results);
//		Map<String, Object> resultMap = new HashMap<>();
//		resultMap.put("rows", daoPage.getRows());
//		resultMap.put("page", daoPage.getPage());
//		resultMap.put("total", daoPage.getTotal());
//		resultMap.put("results", list);
//		return Result.success(resultMap);
//	}
//
//	/**
//	 * 查询当前登录用户 待处理数、待确认数和被驳回未处理数量 紧急任务（预计完成时间20分钟内）
//	 *
//	 * @param request
//	 * @return
//	 */
//	@ApiOperation(value = "我的任务", produces = "application/json", httpMethod = "POST")
//	@RequestMapping(value = "/waitDoTasks", method = RequestMethod.POST)
//	@ResponseBody
//	public ResponseMessage<?> todayPlanJinji(HttpServletRequest request) {
//		try {
//			String userName = (String) request.getAttribute(JwtConstants.CURRENT_USER_NAME);// 获取当前登录用户名
//
//			TSBaseUser user = systemService.findUniqueByProperty(TSBaseUser.class,"userName", userName);
//			if (null != user){
//				userName = user.getId();// 由于task表的reviewer字段保存的是用户id，故在此处将userName改为userId进行传递，保持原有逻辑不变。
//			}
//			List<TaskEntity> listmap = taskService.handleTask(userName);
//
//			Map<String, Object> resultMap = new HashMap<String, Object>();
//			int disposalnum = taskService.unTaksByMe(userName);// 待我处理
//			int confirmnum = taskService.confirmTaksByMe(userName);// 待我确认
//			int rejectnum = taskService.backTaksByMe(userName);// 被驳回
//			resultMap.put("disposalnum", disposalnum);
//			resultMap.put("confirmnum", confirmnum);
//			resultMap.put("rejectnum", rejectnum);
//			if (disposalnum > 0) {
//				resultMap.put("results", listmap);
//			}
//			resultMap.put("results", listmap);
//			return Result.success(resultMap);
//		} catch (Exception e) {
//			logger.error("查询任务异常：", e);
//			return Result.error("未知错误，请联系管理员");
//		}
//	}
//
//	/**
//	 * 任务详情
//	 *
//	 * @param param
//	 * @param request
//	 * @param response
//	 * @return
//	 */
//	@ApiOperation(value = "任务详情", produces = "application/json", httpMethod = "POST")
//	@RequestMapping(value = "/taskDetails", method = RequestMethod.POST)
//	@ResponseBody
//	public ResponseMessage<?> taskDetails(@RequestBody Map param, HttpServletRequest request,
//			HttpServletResponse response) {
//
//		String id = (String) param.get("id");
//		TaskEntity entity = systemService.getEntity(TaskEntity.class, Integer.valueOf(id));
//		Map<String, Object> resultMap = taskService.details(entity);
//		if ("proposal".equals(entity.getTasktype()) && StringUtil.isNotEmpty(entity.getInspecPoiId())) {
//			ProposalEntity proposalEntity = this.systemService.getEntity(ProposalEntity.class, entity.getInspecPoiId());
//			// 返回数据
//			Map<String, Object> proposalMap = new HashMap<>();
//			MyBeanUtils.copyBean2Map(proposalMap, proposalEntity);
//			proposalMap.remove("class");
//			resultMap.put("proposalEntity", proposalMap);
//		}
//		return Result.success(resultMap);
//	}
//
//	@ApiOperation(value = "任务处理", produces = "application/json", httpMethod = "POST")
//	@RequestMapping(value = "/doUpdate", method = RequestMethod.POST)
//	@ResponseBody
//	public ResponseMessage<?> doUpdate(@RequestBody Map param, HttpServletRequest request) {
//
//		try {
//			String jsonStr = (String) param.get("objstr");
//			String userName = (String) request.getAttribute(JwtConstants.CURRENT_USER_NAME);// 获取当前登录用户名
//			// 保存任务结果
//			TaskDto dto = JSON.parseObject(jsonStr, TaskDto.class);
//			TaskEntity entity = this.taskFeedbackService.getEntity(TaskEntity.class, dto.getId());
//			MyBeanUtils.copyBeanNotNull2Bean(dto, entity);
//			entity.setCheckStep(1);
////            entity.setModifydate(new Date());
////            entity.setModifyuser(userName);
//
//			TSBaseUser baseUser = systemService.findUniqueByProperty(TSBaseUser.class,"userName", userName);
//			entity.setUpdateDate(new Date());
//			entity.setUpdateBy(baseUser.getUserName());
//			entity.setUpdateName(baseUser.getRealName());
//			systemService.saveOrUpdate(entity);
//			taskService._saveTaskLog(entity, "处理任务");
//			return Result.success();
//		} catch (Exception e) {
//			logger.error("更新任务异常：", e);
//			return Result.error("未知错误，请联系管理员");
//		}
//	}
//
//	@ApiOperation(value = "巡检计划列表", produces = "application/json", httpMethod = "POST")
//	@RequestMapping(value = "/doAddFeedBack", method = RequestMethod.POST)
//	@ResponseBody
//	public ResponseMessage<?> doAddFeedBack(@RequestBody Map param, HttpServletRequest request) {
//
//		try {
//			String jsonStr = (String) param.get("objstr");
//			String userName = (String) request.getAttribute(JwtConstants.CURRENT_USER_NAME);// 获取当前登录用户名
//			TaskFeedbackDto dto = JSON.parseObject(jsonStr, TaskFeedbackDto.class);
//			taskFeedbackService.addMain(dto, userName);
//			return Result.success();
//		} catch (Exception e) {
//			logger.error("保存意见反馈异常：", e);
//			return Result.error("未知错误，请联系管理员");
//		}
//	}
//
//	@ApiOperation(value = "添加异常任务", produces = "application/json", httpMethod = "POST")
//	@RequestMapping(value = "/doAddList", method = RequestMethod.POST)
//	@ResponseBody
//	public ResponseMessage<?> doAddList(@RequestBody Map param, HttpServletRequest request) {
//
//		try {
//			String jsonStr = (String) param.get("objstr");
//			String userName = (String) request.getAttribute(JwtConstants.CURRENT_USER_NAME);// 获取当前登录用户名
//			UserInfo userInfo = userService.getWebUserInfo(userName);// 获取用户基本信息
//			List<TaskDto> dtos = JSON.parseArray(jsonStr, TaskDto.class);
//			taskService.addMainList(dtos, userInfo);
//			return Result.success();
//		} catch (Exception e) {
//			logger.error("系统异常：",e);
//			return Result.error("未知错误，请联系管理员");
//		}
//	}
//
//	@ApiOperation(value = "新增任务", produces = "application/json", httpMethod = "POST")
//	@RequestMapping(value = "/doAdd", method = RequestMethod.POST)
//	@ResponseBody
//	public ResponseMessage<?> doAdd(@RequestBody Map param, HttpServletRequest request) {
//
//		try {
//			String jsonStr = (String) param.get("objstr");
//			String userName = (String) request.getAttribute(JwtConstants.CURRENT_USER_NAME);// 获取当前登录用户名
//			UserInfo userInfo = userService.getWebUserInfo(userName);// 获取用户基本信息
//			TaskDto dto = JSON.parseObject(jsonStr, TaskDto.class);
//			taskService.addMain(dto, userInfo);
//			return Result.success();
//		} catch (Exception e) {
//			logger.error("保存任务异常：", e);
//			return Result.error("未知错误，请联系管理员");
//		}
//	}
}
