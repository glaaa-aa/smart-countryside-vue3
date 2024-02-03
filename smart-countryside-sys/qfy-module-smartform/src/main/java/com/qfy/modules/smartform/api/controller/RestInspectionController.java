package com.qfy.modules.smartform.api.controller;

import com.alibaba.fastjson.JSON;
import com.qfy.modules.smartform.api.dto.InspectionDto;
import com.qfy.modules.smartform.service.IInspectionPointService;
import com.qfy.modules.smartform.service.IInspectionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.api.ISysBaseAPI;
import org.jeecg.common.system.vo.LoginUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author MildYak
 * @version V1.0
 * @Title: Controller
 * @Description: 任务接口
 * @date 2019-12-29 16:41:46
 */
@RestController
@RequestMapping("/rest/inspectionController")
@Api(value = "巡检接口", description = "关于巡检的接口", tags = "sysCheckAPI")
public class RestInspectionController {
	private static final Logger logger = LoggerFactory.getLogger(RestInspectionController.class);

	@Autowired
	private ISysBaseAPI baseAPI;

//	@Autowired
//	private UserService userService;
//	@Autowired
//	private SystemService systemService;

	@Autowired
	private IInspectionService inspectionService;

	@Autowired
	private IInspectionPointService inspectionPointService;
//
//	/**
//	 * 根据巡检点编号获取该点巡检的历史记录。
//	 *
//	 * @param request
//	 * @return
//	 */
//	@ApiOperation(value = "巡检历史记录", produces = "application/json", httpMethod = "POST")
//	@SuppressWarnings("rawtypes")
//	@RequestMapping(value = "/inspectionPointList", method = RequestMethod.POST)
//	@ResponseBody
//	public ResponseMessage<?> inspectionPointList(@RequestBody Map param, HttpServletRequest request) {
//
//		String serialno = (String) param.get("serialno");
//
//		DataGrid dataGrid = JwtQueryUtils.getDataGrid(param);
//		CommonParamsDto paramsDto = new CommonParamsDto();
//		paramsDto.setPage(dataGrid.getPage());
//		paramsDto.setRows(dataGrid.getRows());
//
//		if (StringUtil.isNotEmpty(serialno)) {
//			paramsDto.setSerialno(serialno);
//		}
//
//		try {
//
//			MiniDaoPage<InspectionPointEntity> daoPage = inspectionPointService.queryInspectionPoints(paramsDto);
//			return Result.success(daoPage);
//		} catch (Exception e) {
//			logger.error("查询巡检记录异常：", e);
//			return Result.error("未知错误，请联系管理员");
//		}
//
////		try {
////			// 分页设置
////			DataGrid dataGrid = JwtQueryUtils.getDataGrid(param);
////			String serialno = (String) param.get("serialno");
////			CriteriaQuery cq = new CriteriaQuery(InspectionPointEntity.class, dataGrid);
////			// 获取用户基本信息
//////            String userName = (String) request.getAttribute(JwtConstants.CURRENT_USER_NAME);//获取当前登录用户名
//////            UserInfo userInfo = userService.getWebUserInfo(userName);//获取用户基本信息
//////            JwtQueryUtils.addOrgByUser(cq, userInfo);//防止点编号重复，添加企业限制。
////			cq.add(Property.forName("serialno").eq(serialno));
////			cq.addOrder("checktime", SortDirection.desc);
////			cq.add();
////			this.systemService.getDataGridReturn(cq, true);
////
////			return JwtQueryUtils.returnPage(dataGrid);
////		} catch (Exception e) {
////			e.printStackTrace();
////			return Result.error("未知错误，请联系管理员");
////		}
//	}
//
//	/**
//	 * 返回当前登录用户所在部门所有异常项
//	 *
//	 * @param request
//	 * @return
//	 */
//	@ApiOperation(value = "巡检异常列表", produces = "application/json", httpMethod = "POST")
//	@SuppressWarnings("rawtypes")
//	@RequestMapping(value = "/myException", method = RequestMethod.POST)
//	@ResponseBody
//	public ResponseMessage<?> myException(@RequestBody Map param, HttpServletRequest request) {
//		try {
//			// 获取用户基本信息
//			String userName = (String) request.getAttribute(JwtConstants.CURRENT_USER_NAME);// 获取当前登录用户名
//			UserInfo userInfo = userService.getWebUserInfo(userName);// 获取用户基本信息
//
//			CriteriaQuery cq = new CriteriaQuery(VInspectionPoint.class);
//			JwtQueryUtils.addOrgByUser(cq, userInfo);
//
////			cq.add(Property.forName("state").eqN(0));// 还没创建任务cre
//			cq.add(Property.forName("state").ne(9));// 过滤 已处理完成的巡检点
//			cq.add(Property.forName("isvalid").eq(0));// 不合格
//			cq.addOrder("checktime", SortDirection.desc);
//			List<VInspectionPoint> list = systemService.getListByCriteriaQuery(cq, false);
//
//			List<Map<String, Object>> result = new ArrayList<>();
//			for (VInspectionPoint temp : list) {
//				Map<String, Object> map = new HashMap<>();
//
//				MyBeanUtils.copyBean2Map(map, temp);
//				map.put("type", "inspection");// 当前项目只会有巡检异常，巡检点
////                map.put("type", "proposal");//随手拍
//				map.remove("class");
//				// 查询本次检查发现的异常项
////                if("inspection".equals(temp.getType())){
//				CriteriaQuery cq1 = new CriteriaQuery(CheckListEntity.class);
//				cq1.add(Property.forName("inspecPoiId").eq(Integer.valueOf(temp.getId())));
//				cq1.add(Property.forName("isvalid").eq(0));
//				List<CheckListEntity> checkListEntities = this.systemService.getListByCriteriaQuery(cq1, false);
//
//				String[] actDesc = new String[checkListEntities.size()];
//				for (int i = 0; i < checkListEntities.size(); i++) {
//					CheckListEntity entity1 = checkListEntities.get(i);
//					String results = entity1.getResults();
//					if ("picture".equals(entity1.getType())) {
//						results = "不合格";
//					}
//					actDesc[i] = entity1.getItemName() + "(" + results + ")";
//				}
//				map.put("actDesc", actDesc);
////                }
//				if (StringUtil.isNotEmpty(temp.getAttachId())) {
//					String[] ids = temp.getAttachId().split(",");
//					map.put("attachList", inspectionPointService.findAttachhments(ids));
//				}
//
//				result.add(map);
//			}
//			return Result.success(result);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return Result.error("未知错误，请联系管理员");
//		}
//	}
//
//	/**
//	 * 根据巡检记录ID获取巡检结果明细
//	 *
//	 * @param request
//	 * @return
//	 */
//	@SuppressWarnings("rawtypes")
//	@ApiOperation(value = "巡检结果", produces = "application/json", httpMethod = "POST")
//	@RequestMapping(value = "/pointDetails", method = RequestMethod.POST)
//	@ResponseBody
//	public ResponseMessage<?> pointDetails(@RequestBody Map param, HttpServletRequest request) {
//		try {
//			String id = (String) param.get("inspecPoiId");
//			Map<String, Object> resultMap = this.inspectionPointService.details(Integer.valueOf(id));
//			return Result.success(resultMap);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return Result.error("未知错误，请联系管理员");
//		}
//	}
//
//	/**
//	 * 查询当前用户计划巡检的记录
//	 *
//	 * @param request
//	 * @return
//	 */
//	@SuppressWarnings("rawtypes")
//	@ApiOperation(value = "计划巡检记录", produces = "application/json", httpMethod = "POST")
//	@RequestMapping(value = "/planList", method = RequestMethod.POST)
//	@ResponseBody
//	public ResponseMessage<?> planList(@RequestBody Map param, HttpServletRequest request) {
//		// 分页设置
//		DataGrid dataGrid = JwtQueryUtils.getDataGrid(param);
//
//		try {
//			String userName = (String) request.getAttribute(JwtConstants.CURRENT_USER_NAME);// 获取当前登录用户名
//			Map<String, Object> resultMap = this.inspectionService.myPlanList(dataGrid.getPage(), dataGrid.getRows(), userName);
//			return Result.success(resultMap);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return Result.error("未知错误，请联系管理员");
//		}
//	}
//
	/**
	 * 新增巡检记录
	 *
	 * @param request
	 * @return
	 */
	@ApiOperation(value = "新增巡检记录", produces = "application/json", httpMethod = "POST")
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/doAdd", method = RequestMethod.POST)
	public Result doAdd(@RequestBody Map param, HttpServletRequest request) {
		try {
			// 获取用户基本信息
//			String token = TokenUtils.getTokenByRequest(request);// 获取当前请求token
			LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
			// 如果需要用户登录，而请求带有token
//			if (StringUtils.isNotBlank(token)){
//				String username = JwtUtil.getUsername(token);// 获取当前登录用户名
//				loginUser = baseAPI.getUserByUserName(username); // 获取用户基本信息
//			}

			String jsonStr = (String) param.get("objstr");
			logger.info("新增巡检记录报文=>{}", jsonStr);
			InspectionDto dto = JSON.parseObject(jsonStr, InspectionDto.class);
			dto.setStatus(1);// 已巡检
			inspectionService.addMain(dto, loginUser);
			return Result.OK();
		} catch (Exception e) {
			logger.error("保存巡检记录异常:", e);
			return Result.error("未知错误，请联系管理员");
		}
	}

//	/**
//	 * 计划详情-巡检记录版
//	 *
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping(value = "/planDetails", method = RequestMethod.POST)
//	@ResponseBody
//	public ResponseMessage<?> planDetails(@RequestBody Map<String, String> params) {
//		String id = params.get("id");// 巡检记录id
//		if (StringUtil.isEmpty(id)){
//			return Result.error("缺少必要参数，请检查！");
//		}
//		try {
//			Integer inspectId = Integer.parseInt(id);
//			Map<String, Object> resultMap = this.inspectionService.planDetails(inspectId);
//			return Result.success(resultMap);
//		} catch (Exception e) {
//			logger.error("获取计划详情-巡检记录版异常：", e);
//			return Result.error("未知错误，请联系管理员");
//
//		}
//	}

}
