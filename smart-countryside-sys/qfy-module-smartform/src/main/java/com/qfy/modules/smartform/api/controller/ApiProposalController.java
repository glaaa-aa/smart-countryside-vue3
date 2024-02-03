package com.qfy.modules.smartform.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Title: Controller
 * @Description: 随手拍APi
 * @author onlineGenerator
 * @date 2019-04-17 15:53:21
 * @version V1.0
 *
 */
@Controller
@RequestMapping("/api/proposalController")
public class ApiProposalController {

	private static final Logger logger = LoggerFactory.getLogger(ApiProposalController.class);

//	@Autowired
//	private SystemService systemService;
//	@Autowired
//	private ProposalServiceI proposalService;
//	@Autowired
//	private ProposalCommentServiceI commentService;
//	@Autowired
//	private InspectionPointServiceI inspectionPointService;
//
//	@RequestMapping(params = "index")
//	@ResponseBody
//	public AjaxJson index(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
//		String message = null;
//		AjaxJson j = new AjaxJson();
//		message = "访问成功";
//		String checktime = request.getParameter("checktime");
//		String isvalid = request.getParameter("isvalid");
//		String type = request.getParameter("type");
//		String state = request.getParameter("state");
//		try {
//			CriteriaQuery cq = new CriteriaQuery(VinspectionProposal.class, dataGrid);
//			//查询条件组装器
////			org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, inspectionPoint, request.getParameterMap());
//			try{
//				if(StringUtil.isNotEmpty(checktime)){
//					Map<String, String> map = DateUtil.dateStrToStr(checktime);
//					Date date = DateUtil.convert(checktime);
//
//					cq.add(Property.forName("submitTime").ge(DateUtil.convert2StartDate(date)));
//					cq.add(Property.forName("submitTime").le(DateUtil.convert2EndDate(date)));
//				}
//				if(StringUtil.isNotEmpty(type)){
//					cq.add(Property.forName("type").eq(type));
//				}
//				if(StringUtil.isNotEmpty(isvalid)){
//					cq.add(Property.forName("isvalid").eq(Integer.valueOf(isvalid)));
//				}
//				if(StringUtil.isNotEmpty(state)){
//					cq.add(Property.forName("state").eq(Integer.valueOf(state)));
//				}
//				//自定义追加查询条件
//				cq.addOrder("submitTime", SortDirection.desc);
//			}catch (Exception e) {
//				throw new BusinessException(e.getMessage());
//			}
//			cq.add();
//			this.systemService.getDataGridReturn(cq, true);
//			Map<String, Object> resultMap = new HashMap<>();
//
//			List<Map<String, Object>> list = new ArrayList<>();
//			for(Object temp : dataGrid.getResults() ){
//				VinspectionProposal entity = (VinspectionProposal)temp;
//				Map<String, Object> map = new HashMap<>();
//				MyBeanUtils.copyBean2Map(map, temp);
//				map.remove("class");
//				map.remove("ascDesc");
//				map.put("createDate", entity.getSubmitTime());
//
//				if(StringUtil.isNotEmpty(entity.getAttachId())){
//					String[] ids = entity.getAttachId().split(",");
//					map.put("attachList", inspectionPointService.findAttachhments(ids));
//				}
//				list.add(map);
//			}
//
//			resultMap.put("results", list);
//			resultMap.put("total", dataGrid.getTotal());
//			resultMap.put("rows", dataGrid.getRows());
//			resultMap.put("page", dataGrid.getPage());
//
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
//	@RequestMapping(params = "proposalType")
//	@ResponseBody
//	public AjaxJson proposalType(HttpServletRequest request, HttpServletResponse response) {
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
//		List<ProposalTypeEntity> list = this.systemService.getList(ProposalTypeEntity.class);
//
//		List<Map<String, Object>> results = new ArrayList<>();
//		for(ProposalTypeEntity temp : list){
//			Map<String, Object> obj = new HashMap<>();
//			obj.put("id", temp.getId());
//			obj.put("name", temp.getName());
//			obj.put("remark", temp.getRemark());
//			results.add(obj);
//		}
//
//		//组织返回数据格式
//		j.setObj(results);
//		return j;
//	}
//
//
//	/**
//	 * 新增提案
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping(params = "doAdd")
//	@ResponseBody
//	public AjaxJson doAdd(HttpServletRequest request) throws IOException {
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
//		String jsonStr = request.getParameter("objstr");
////		System.out.println(jsonStr);
////		String jsonStr = "{\"updateDate\":null,\"actDesc\":\"1\",\"sysCompanyCode\":null,\"updateName\":null,\"createBy\":null,\"updateBy\":null,\"chkArea\":\"一楼楼梯\",\"sysOrgCode\":null,\"expecDate\":\"2019-06-10 11:09:48\",\"id\":1,\"propType\":\"1\",\"sn\":\"1\",\"state\":\"0\",\"attachId\":null,\"communication\":\"1\",\"createName\":null,\"createDate\":null}";
//		if(StringUtil.isEmpty(jsonStr)){
//			j.setSuccess(false);
//			j.setMsg("参数为空");
//			return j;
//		}
//		try {
//			ProposalDto dto = JSON.parseObject(jsonStr, ProposalDto.class);
////			proposalService.addMain(dto, token);
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
//	 * 详情
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping(params = "details")
//	@ResponseBody
//	public AjaxJson details(HttpServletRequest request){
//
//		String message = null;
//		AjaxJson j = new AjaxJson();
//		message = "访问成功";
//		String id =  request.getParameter("id");
//		try {
//			Map<String, Object> resultMap = this.proposalService.details(Integer.valueOf(id));
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
//	 * 评论列表
//	 * @param request
//	 * @param response
//	 * @param dataGrid
//	 * @return
//	 */
//	@RequestMapping(params = "commentList")
//	@ResponseBody
//	public AjaxJson commentList(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
//		String message = null;
//		AjaxJson j = new AjaxJson();
//		message = "访问成功";
//		String id = request.getParameter("proposalId");
//		try {
//			CriteriaQuery cq = new CriteriaQuery(ProposalCommentEntity.class, dataGrid);
//			//查询条件组装器
////			org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, inspectionPoint, request.getParameterMap());
//			try{
//				//自定义追加查询条件
//				cq.add(Property.forName("proposalId").eq(id));
//				cq.addOrder("createDate", SortDirection.desc);
//			}catch (Exception e) {
//				throw new BusinessException(e.getMessage());
//			}
//			cq.add();
//			this.systemService.getDataGridReturn(cq, true);
//			Map<String, Object> resultMap = new HashMap<>();
//
//			List<Map<String, Object>> list = new ArrayList<>();
//			for(Object entity : dataGrid.getResults() ){
//				Map<String, Object> map = new HashMap<>();
//				MyBeanUtils.copyBean2Map(map, entity);
//				map.remove("class");
//				list.add(map);
//			}
//
//			resultMap.put("results", list);
//			resultMap.put("total", dataGrid.getTotal());
//			resultMap.put("rows", dataGrid.getRows());
//			resultMap.put("page", dataGrid.getPage());
//
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
//	 * 新增提案评论
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping(params = "doComment")
//	@ResponseBody
//	public AjaxJson doComment(HttpServletRequest request) throws IOException {
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
////		String jsonStr = request.getParameter("objstr");
//				String jsonStr = "{\"comment\":\"asdf\",\"proposal_Id\":2}";
//
//		if(StringUtil.isEmpty(jsonStr)){
//			j.setSuccess(false);
//			j.setMsg("参数为空");
//			return j;
//		}
//		try {
//			CommentDto dto = JSON.parseObject(jsonStr, CommentDto.class);
//			commentService.addMain(dto, token);
//		} catch (Exception e) {
//			e.printStackTrace();
//			message = "访问失败";
//			throw new BusinessException(e.getMessage());
//		}
//		j.setMsg(message);
//		return j;
//	}


}
