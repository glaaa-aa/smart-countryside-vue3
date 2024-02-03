package com.qfy.modules.smartform.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Title: Controller
 * @Description: 二维码表
 * @author onlineGenerator
 * @date 2019-04-17 15:53:21
 * @version V1.0
 *
 */
@Controller
@RequestMapping("/api/reportController")
public class ApiReportController {

	private static final Logger logger = LoggerFactory.getLogger(ApiReportController.class);

//	@Autowired
//	private SystemService systemService;
//	@Autowired
//	private InspectionServiceI inspectionService;
//	@Autowired
//	private CheckListServiceI checkListService;
//
//
//	/**
//	 * 近七日-日计划执行情况统计
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping(params = "planRunsByDay")
//	@ResponseBody
//	public AjaxJson planRunsByDay(HttpServletRequest request) {
//		String message = null;
//		AjaxJson j = new AjaxJson();
//		message = "访问成功";
//
//		String token = ValidTokenUtil.checkReq(request);
//		if(StringUtil.isEmpty(token)){
//			j.setMsg("API认证失败");
//			j.setSuccess(false);
//			return j;
//		}
//
//		List<Map<String, Object>> result = inspectionService.reportPlanRunsByDay(token, 1, 7);
//		j.setObj(result);
//		return j;
//	}
//
//	/**
//	 * 巡检点异常月统计
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping(params = "pointRunsByMonth")
//	@ResponseBody
//	public AjaxJson pointRunsByMonth(HttpServletRequest request) {
//		String message = null;
//		AjaxJson j = new AjaxJson();
//		message = "访问成功";
//
//		String token = ValidTokenUtil.checkReq(request);
//		if(StringUtil.isEmpty(token)){
//			j.setMsg("API认证失败");
//			j.setSuccess(false);
//			return j;
//		}
//
//		Date mDate = new Date();
//		String month = request.getParameter("month");
//		if (StringUtil.isNotEmpty(month)) {
//			try {
//				mDate = DateUtils.parseDate(month, "yyyy年MM月");
//			} catch (ParseException e) {
//				e.printStackTrace();
//			}
//		}
//
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTime(mDate);
//		//当月第一天
//		calendar.set(Calendar.DAY_OF_MONTH, 1);
//		calendar.set(11, 0);
//		calendar.set(12, 0);
//		calendar.set(13, 0);
//		String sttime = formatter.format(calendar.getTime());
//		//当月最后一天
//		int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
//		calendar.set(Calendar.DAY_OF_MONTH, lastDay);
//		calendar.set(11, 23);
//		calendar.set(12, 59);
//		calendar.set(13, 59);
//		String edtime = formatter.format(calendar.getTime());
//		DataGrid dataGrid = new DataGrid();
//		dataGrid.setPage(1);
//		dataGrid.setRows(100);
//		inspectionService.reportPointRunsByTime(dataGrid, sttime, edtime, null);
//		j.setObj(dataGrid.getResults());
//		return j;
//	}
//
//	/**
//	 * 任务完成情况月统计
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping(params = "taskRunsByMonth")
//	@ResponseBody
//	public AjaxJson taskRunsByMonth(HttpServletRequest request) {
//		String message = null;
//		AjaxJson j = new AjaxJson();
//		message = "访问成功";
//
//		String token = ValidTokenUtil.checkReq(request);
//		if(StringUtil.isEmpty(token)){
//			j.setMsg("API认证失败");
//			j.setSuccess(false);
//			return j;
//		}
//
//		Date mDate = new Date();
//		String month = request.getParameter("month");
//		if (StringUtil.isNotEmpty(month)) {
//			try {
//				mDate = DateUtils.parseDate(month, "yyyy年MM月");
//			} catch (ParseException e) {
//				e.printStackTrace();
//			}
//		}
//
//		String time = DateUtils.formatDate(mDate, "yyyy-MM");
//
//		CriteriaQuery cq = new CriteriaQuery(VtaskMonth.class);
//		cq.add(Property.forName("time").eq(time));
//		cq.add(Property.forName("reviewer").eq(token));
//		List<VtaskMonth> result = this.systemService.getListByCriteriaQuery(cq, false);
//		j.setObj(result);
//		return j;
//	}
//
//	@RequestMapping(params = "reportAllCount")
//	@ResponseBody
//	public AjaxJson reportAllCount(HttpServletRequest request) {
//		String stdate = request.getParameter("stdate");
//		String eddate = request.getParameter("eddate");
//
//		String message = null;
//		AjaxJson j = new AjaxJson();
//		message = "访问成功";
//
//		List<Map<String, Object>> list = this.checkListService.reportAllCount(stdate, eddate);
//
//		j.setObj(list);
//		return j;
//	}
//
//	@RequestMapping(params = "echartDemo")
//	public ModelAndView echartDemo(HttpServletRequest req) {
//		return new ModelAndView("com/jeecg/demo/report_echartsDemo");
//	}
//
//	/**
//	 * 饼图2
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping(params = "PieChart2")
//	public ModelAndView PieChart2(HttpServletRequest request) {
//		return new ModelAndView("com/jeecg/demo/echartsDemo/report_PieChart2");
//	}
//
//	/**
//	 * 柱状图3
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping(params = "BarGraph3")
//	public ModelAndView BarGraph3(HttpServletRequest request) {
//		return new ModelAndView("com/jeecg/demo/echartsDemo/report_BarGraph3");
//	}
}
