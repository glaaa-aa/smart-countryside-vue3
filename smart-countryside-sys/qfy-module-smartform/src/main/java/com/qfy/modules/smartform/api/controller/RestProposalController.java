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
@RequestMapping("/proposalController")
@Api(value = "巡检接口", description = "关于巡检的接口", tags = "sysCheckAPI")
public class RestProposalController {

	private static final Logger logger = LoggerFactory.getLogger(RestProposalController.class);

//	@Autowired
//	private UserService userService;
//	@Autowired
//	private SystemService systemService;
//	@Autowired
//	private InspectionPointServiceI inspectionPointService;
//	@Autowired
//	private ProposalServiceI proposalService;
//
//	@ApiOperation(value = "巡检计划列表", produces = "application/json", httpMethod = "POST")
//	@RequestMapping(value = "/doAdd", method = RequestMethod.POST)
//	@ResponseBody
//	public ResponseMessage<?> doAdd(@RequestBody Map param, HttpServletRequest request) {
//
//		try {
//			String jsonStr = (String) param.get("objstr");
//			String userName = (String) request.getAttribute(JwtConstants.CURRENT_USER_NAME);// 获取当前登录用户名
//			UserInfo userInfo = userService.getWebUserInfo(userName);// 获取用户基本信息
//			ProposalDto dto = JSON.parseObject(jsonStr, ProposalDto.class);
//			proposalService.addMain(dto, userInfo);
//			return Result.success();
//		} catch (Exception e) {
//			e.printStackTrace();
//			return Result.error("未知错误，请联系管理员");
//		}
//	}
//
//	/**
//	 * 提案类型
//	 *
//	 * @param request
//	 * @return
//	 */
//	@ApiOperation(value = "提案类型", produces = "application/json", httpMethod = "POST")
//	@RequestMapping(value = "/proposalType", method = RequestMethod.POST)
//	@ResponseBody
//	public ResponseMessage<?> proposalType() {
//
//		try {
//			List<ProposalTypeEntity> list = this.systemService.getList(ProposalTypeEntity.class);
//
//			List<Map<String, Object>> results = new ArrayList<>();
//			for (ProposalTypeEntity temp : list) {
//				Map<String, Object> obj = new HashMap<>();
//				obj.put("id", temp.getId());
//				obj.put("name", temp.getName());
//				obj.put("remark", temp.getRemark());
//				results.add(obj);
//			}
//			return Result.success(results);
//		} catch (Exception e) {
//			logger.error("系统异常：",e);
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
//	@RequestMapping(value = "/details", method = RequestMethod.POST)
//	@ResponseBody
//	public ResponseMessage<?> details(@RequestBody Map param, HttpServletRequest request) {
//
//		String id = (String) param.get("id");
//		try {
//			Map<String, Object> resultMap = this.proposalService.details(Integer.valueOf(id));
//			return Result.success(resultMap);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return Result.error("未知错误，请联系管理员");
//		}
//	}
//
//	/**
//	 * 查询当前用户所有随手拍和巡检记录
//	 *
//	 * @param request
//	 * @return
//	 */
//	@ApiOperation(value = "综合记录", produces = "application/json", httpMethod = "POST")
//	@RequestMapping(value = "/index", method = RequestMethod.POST)
//	@ResponseBody
//	public ResponseMessage<?> index(@RequestBody Map<String, String> param, HttpServletRequest request) {
//		String checktime = param.get("checktime");
//		String isvalid = param.get("isvalid");
//		String type = param.get("type");
//		String state = param.get("state");
//
//		try {
//			DataGrid dataGrid = JwtQueryUtils.getDataGrid(param);
//
//			CriteriaQuery cq = new CriteriaQuery(VinspectionProposal.class, dataGrid);
//			String userName = (String) request.getAttribute(JwtConstants.CURRENT_USER_NAME);// 获取当前登录用户名
//			UserInfo userInfo = userService.getWebUserInfo(userName);// 获取用户基本信息
//			JwtQueryUtils.addOrgByUser(cq, userInfo);// 过滤非本公司和部门
//			try {
//				if (StringUtil.isNotEmpty(checktime)) {
//					Date date = DateUtil.convert(checktime);
//					cq.add(Property.forName("submitTime").ge(DateUtil.convert2StartDate(date)));
//					cq.add(Property.forName("submitTime").le(DateUtil.convert2EndDate(date)));
//				}
//				if (StringUtil.isNotEmpty(type)) {
//					cq.add(Property.forName("type").eq(type));
//				}
//				if (StringUtil.isNotEmpty(isvalid)) {
//					cq.add(Property.forName("isvalid").eq(Integer.valueOf(isvalid)));
//				}
//				if (StringUtil.isNotEmpty(state)) {
//					cq.add(Property.forName("state").eq(Integer.valueOf(state)));
//				}
//				// 自定义追加查询条件
//				cq.addOrder("submitTime", SortDirection.desc);
//			} catch (Exception e) {
//				logger.error("系统异常：",e);
//				throw new BusinessException(e.getMessage());
//			}
//			cq.add();
//			this.systemService.getDataGridReturn(cq, true);
//
//			List<Map<String, Object>> list = new ArrayList<>();
//			for (Object temp : dataGrid.getResults()) {
//				VinspectionProposal entity = (VinspectionProposal) temp;
//				Map<String, Object> map = new HashMap<>();
//				MyBeanUtils.copyBean2Map(map, temp);
//				map.remove("class");
//				map.remove("ascDesc");
//				map.put("createDate", entity.getSubmitTime());
//
//				if (StringUtil.isNotEmpty(entity.getAttachId())) {
//					String[] ids = entity.getAttachId().split(",");
//					map.put("attachList", inspectionPointService.findAttachhments(ids));
//				}
//				list.add(map);
//			}
//
//			return JwtQueryUtils.returnPage(dataGrid, list);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return Result.error("未知错误，请联系管理员");
//		}
//	}

}
