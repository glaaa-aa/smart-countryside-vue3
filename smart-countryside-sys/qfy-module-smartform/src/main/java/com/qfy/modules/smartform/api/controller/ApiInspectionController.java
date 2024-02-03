package com.qfy.modules.smartform.api.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qfy.modules.smartform.api.common.AjaxJson;
import com.qfy.modules.smartform.api.common.DataGrid;
import com.qfy.modules.smartform.api.common.ValidTokenUtil;
import com.qfy.modules.smartform.api.dto.InspectionDto;
import com.qfy.modules.smartform.entity.InspectionPoint;
import com.qfy.modules.smartform.service.IInspectionPointService;
import com.qfy.modules.smartform.service.IInspectionService;
import com.xkcoding.http.util.StringUtil;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.system.api.ISysBaseAPI;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.vo.LoginUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("/api/inspectionController")
public class ApiInspectionController {

	private static final Logger logger = LoggerFactory.getLogger(ApiInspectionController.class);

//	@Autowired
//	private UserService userService;
//	@Autowired
//	private SystemService systemService;

	@Autowired
	private ISysBaseAPI baseAPI;

	@Autowired
	private IInspectionService inspectionService;

	@Autowired
	private IInspectionPointService inspectionPointService;

//	/**
//	 * 我的巡检异常记录
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping(params = "myException")
//	@ResponseBody
//	public AjaxJson myException(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
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
//		try {
//			CriteriaQuery cq = new CriteriaQuery(VinspectionProposal.class);
//			Integer[] states = {0,1};
//
//			cq.add(Property.forName("state").isNull());//还没创建任务cre
//			cq.add(Property.forName("isvalid").eq(0));//不合格
//			cq.addOrder("submitTime", SortDirection.desc);
//			List<VinspectionProposal> list = systemService.getListByCriteriaQuery(cq, false);
//
//			List<Map<String, Object>> result = new ArrayList<>();
//			for(VinspectionProposal temp : list){
//				Map<String, Object> map = new HashMap<>();
//				MyBeanUtils.copyBean2Map(map, temp);
//				map.remove("class");
//
//				if("inspection".equals(temp.getType())){
//					CriteriaQuery cq1 = new CriteriaQuery(CheckListEntity.class);
//					cq1.add(Property.forName("inspecPoiId").eq(Integer.valueOf(temp.getId())));
//					cq1.add(Property.forName("isvalid").eq(0));
//					List<CheckListEntity> checkListEntities = this.systemService.getListByCriteriaQuery(cq1,false);
//
//					String[] actDesc = new String[checkListEntities.size()];
//					for(int i =0; i< checkListEntities.size(); i++){
//						CheckListEntity entity1 = checkListEntities.get(i);
//						String results = entity1.getResults();
//						if("picture".equals(entity1.getType())){
//							results = "不合格";
//						}
//						actDesc[i] = entity1.getItemName() + "("+results+")";
//					}
//					map.put("actDesc", actDesc);
//				}
//				if(StringUtil.isNotEmpty(temp.getAttachId())){
//					String[] ids = temp.getAttachId().split(",");
//					map.put("attachList", inspectionPointService.findAttachhments(ids));
//				}
//				result.add(map);
//			}
//
//			j.setObj(result);
//		} catch (Exception e) {
//			e.printStackTrace();
//			message = "访问失败";
//			throw new BusinessException(e.getMessage());
//		}
//		j.setMsg(message);
//		return j;
//	}

	/**
	 * 巡检点巡检记录
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "inspectionPointList")
	public AjaxJson inspectionPointList(HttpServletRequest request, DataGrid dataGrid) {
		String message = "访问成功";
		AjaxJson result = new AjaxJson();
		String isvalid = request.getParameter("isvalid");
		String checktime = request.getParameter("checktime");
		String serialno = request.getParameter("serialno");

		QueryWrapper<InspectionPoint> wrapper = QueryGenerator.initQueryWrapper(new InspectionPoint(), request.getParameterMap());
		wrapper.lambda().orderByDesc(InspectionPoint::getChecktime);
		if(StringUtil.isNotEmpty(checktime)){
			Date date = new Date(System.currentTimeMillis());
			try {
				date = DateFormatUtils.ISO_8601_EXTENDED_DATE_FORMAT.parse(checktime);
			} catch(ParseException e) {
			    logger.error(checktime + "=>日期转换错误：", e);
			}
			String format = DateFormatUtils.format(date, "yyyyMMdd");
			wrapper.apply("DATE_FORMAT(checktime,'%Y%m%d') = '" + format + "'");
		}

		if(StringUtil.isNotEmpty(isvalid)){
			wrapper.lambda().eq(InspectionPoint::getIsvalid, Integer.parseInt(isvalid));
		}

		if(StringUtil.isNotEmpty(serialno)){
			wrapper.lambda().eq(InspectionPoint::getSerialno, Integer.parseInt(serialno));
		}

		Page<InspectionPoint> page = new Page<>(dataGrid.getPage(), dataGrid.getRows());
		IPage<InspectionPoint> pointList = inspectionPointService.page(page,wrapper);
		Map<String, Object> resultMap = new HashMap<>();

		resultMap.put("results", pointList.getRecords());
		resultMap.put("total", pointList.getTotal());
		resultMap.put("rows", dataGrid.getRows());
		resultMap.put("page", dataGrid.getPage());

		result.setObj(resultMap);
		result.setMsg(message);
		return result;
	}

	/**
	 * 新增巡检记录
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	public AjaxJson doAdd(HttpServletRequest request) throws IOException {
		String message = "访问成功";
		AjaxJson result = new AjaxJson();

		String token = ValidTokenUtil.checkReq(request);
		if(StringUtil.isEmpty(token)){
			result.setMsg("API认证失败");
			result.setSuccess(false);
			return result;
		}

		String jsonStr = request.getParameter("objstr");
		InspectionDto dto = JSON.parseObject(jsonStr, InspectionDto.class);
		if(dto == null){
			result.setSuccess(false);
			result.setMsg("参数为空");
			return result;
		}
		try {
			//获取用户基本信息
//			String userName = (String) request.getAttribute(JwtConstants.CURRENT_USER_NAME);//获取当前登录用户名
//			LoginUser loginUser = baseAPI.getUserByUserName(userName);
			LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
			inspectionService.addMain(dto, loginUser);
		} catch (Exception e) {
			logger.error("保存表单提交结果异常：", e);
			message = "访问失败";
		}
		result.setMsg(message);
		return result;
	}

//	/**
//	 * 按计划巡检记录
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping(params = "planList")
//	@ResponseBody
//	public AjaxJson planList(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
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
//		try {
//			Map<String, Object> resultMap = this.inspectionService.myPlanList(dataGrid.getPage(), dataGrid.getRows(), token);
//			j.setObj(resultMap);
//		} catch (Exception e) {
//			e.printStackTrace();
//			message = "访问失败";
//			throw new BusinessException(e.getMessage());
//		}
//		j.setMsg(message);
//		return j;
//	}
//
//	/**
//	 * 计划详情-巡检记录版
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping(params = "planDetails")
//	@ResponseBody
//	public AjaxJson planDetails(HttpServletRequest request) {
//		String id = request.getParameter("id");
//		String message = null;
//		AjaxJson j = new AjaxJson();
//		message = "访问成功";
//		try{
//			Map<String, Object> resultMap = this.inspectionService.planDetails(Integer.valueOf(id));
//			j.setAttributes(resultMap);
//		} catch (Exception e) {
//			e.printStackTrace();
//			message = "访问失败";
//			throw new BusinessException(e.getMessage());
//		}
//		j.setMsg(message);
//		return j;
//	}
//
//	/**
//	 * 巡检点记录详情
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping(params = "pointDetails")
//	@ResponseBody
//	public AjaxJson pointDetails(HttpServletRequest request){
//
//		String message = null;
//		AjaxJson j = new AjaxJson();
//		message = "访问成功";
//		String id =  request.getParameter("inspecPoiId");
//		try {
//			Map<String, Object> resultMap = this.inspectionPointService.details(Integer.valueOf(id));
//			j.setObj(resultMap);
//		} catch (Exception e) {
//			e.printStackTrace();
//			message = "访问失败";
//			throw new BusinessException(e.getMessage());
//		}
//		j.setMsg(message);
//		return j;
//	}
//
//
//	/**
//	 *
//	 */
//	@RequestMapping(params = "refreshPlan")
//	@ResponseBody
//	public AjaxJson refreshPlan(){
//		String message = null;
//		AjaxJson j = new AjaxJson();
//		message = "访问成功";
//		try {
//			inspectionService.planToInspection();
//		} catch (Exception e) {
//			e.printStackTrace();
//			message = "访问失败";
//			throw new BusinessException(e.getMessage());
//		}
//		j.setMsg(message);
//		return j;
//	}
}
