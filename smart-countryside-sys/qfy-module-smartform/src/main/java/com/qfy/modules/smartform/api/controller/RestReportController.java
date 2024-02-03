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
@RequestMapping("/reportController")
@Api(value = "巡检接口", description = "关于巡检的接口", tags = "sysCheckAPI")
public class RestReportController {

	private static final Logger logger = LoggerFactory.getLogger(RestReportController.class);

//	@Autowired
//	private SystemService systemService;
//	@Autowired
//	private InspectionServiceI inspectionService;
//	@Autowired
//	private CheckListServiceI checkListService;
//
//	@Autowired
//	private UserService userService;
//
//	/**
//	 * 巡检点异常统计
//	 * @param request
//	 * @return
//	 */
//	@ApiOperation(value = "计划执行情况", produces = "application/json", httpMethod = "POST")
//	@RequestMapping(value="/planRunsByDay", method = RequestMethod.POST)
//	@ResponseBody
//	public ResponseMessage<?> planRunsByDay(HttpServletRequest request) {
//
//		try {
//			String userName = (String) request.getAttribute(JwtConstants.CURRENT_USER_NAME);//获取当前登录用户名
////			UserInfo userInfo = userService.getWebUserInfo(userName);// 获取用户基本信息
//			List<Map<String, Object>> result = inspectionService.reportPlanRunsByDay(userName, 1, 7);
//			return Result.success(result);
//		} catch (Exception e) {
//			 logger.error("系统异常：",e);
//			return Result.error("未知错误，请联系管理员");
//		}
//	}
//
//	/**
//	 * 巡检异常
//	 * @param request
//	 * @return
//	 */
//	@ApiOperation(value = "巡检点异常", produces = "application/json", httpMethod = "POST")
//	@RequestMapping(value="/pointRunsByMonth", method = RequestMethod.POST)
//	@ResponseBody
//	public ResponseMessage<?> pointRunsByMonth(HttpServletRequest request) {
//
//		try {
//			String userName = (String) request.getAttribute(JwtConstants.CURRENT_USER_NAME);//获取当前登录用户名
//			UserInfo userInfo = userService.getWebUserInfo(userName);// 获取用户基本信息
//
//			Date mDate = new Date();
//			String month = request.getParameter("month");
//			if (StringUtil.isNotEmpty(month)) {
//				try {
//					mDate = DateUtils.parseDate(month, "yyyy年MM月");
//				} catch (ParseException e) {
//					logger.error("日期转换异常：",e);
//				}
//			}
//
//			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			Calendar calendar = Calendar.getInstance();
//			calendar.setTime(mDate);
//			//当月第一天
//			calendar.set(Calendar.DAY_OF_MONTH, 1);
//			calendar.set(11, 0);
//			calendar.set(12, 0);
//			calendar.set(13, 0);
//			String sttime = formatter.format(calendar.getTime());
//			//当月最后一天
//			int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
//			calendar.set(Calendar.DAY_OF_MONTH, lastDay);
//			calendar.set(11, 23);
//			calendar.set(12, 59);
//			calendar.set(13, 59);
//			String edtime = formatter.format(calendar.getTime());
//			DataGrid dataGrid = new DataGrid();
//			dataGrid.setPage(1);
//			dataGrid.setRows(100);
//			inspectionService.reportPointRunsByTime(dataGrid, sttime, edtime, userInfo);
//			return Result.success(dataGrid.getResults());
//		} catch (Exception e) {
//			logger.error("系统异常：",e);
//			return Result.error("未知错误，请联系管理员");
//		}
//	}
//
//	/**
//	 * 任务执行情况
//	 * @param request
//	 * @return
//	 */
//	@ApiOperation(value = "任务执行情况", produces = "application/json", httpMethod = "POST")
//	@RequestMapping(value="/taskRunsByMonth", method = RequestMethod.POST)
//	@ResponseBody
//	public ResponseMessage<?> taskRunsByMonth(HttpServletRequest request) {
//
//		try {
//			String userName = (String) request.getAttribute(JwtConstants.CURRENT_USER_NAME);//获取当前登录用户名
//			Date mDate = new Date();
//			String month = request.getParameter("month");
//			if (StringUtil.isNotEmpty(month)) {
//				try {
//					mDate = DateUtils.parseDate(month, "yyyy年MM月");
//				} catch (ParseException e) {
//					logger.error("日期转换异常：",e);
//				}
//			}
//
//			String time = DateUtils.formatDate(mDate, "yyyy-MM");
//
//			CriteriaQuery cq = new CriteriaQuery(VtaskMonth.class);
//			cq.add(Property.forName("time").eq(time));
//			cq.add(Property.forName("reviewer").eq(userName));
//			List<VtaskMonth> result = this.systemService.getListByCriteriaQuery(cq, false);
//			return Result.success(result);
//		} catch (Exception e) {
//			logger.error("系统异常：",e);
//			return Result.error("未知错误，请联系管理员");
//		}
//	}

}
