package com.qfy.modules.smartform.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Title: Controller
 * @Description: 任务APi
 * @author onlineGenerator
 * @date 2019-04-17 15:53:21
 * @version V1.0
 *
 */
@Controller
@RequestMapping("/api/taskController")
public class ApiTaskController {

	private static final Logger logger = LoggerFactory.getLogger(ApiTaskController.class);

//	@Autowired
//	private SystemService systemService;
//	@Autowired
//	private TaskServiceI taskService;
//	@Autowired
//	private TaskFeedbackServiceI taskFeedbackService;
//
//	@RequestMapping(params = "index")
//	@ResponseBody
//	public AjaxJson index(HttpServletRequest request, HttpServletResponse response) {
//		String message = null;
//		AjaxJson j = new AjaxJson();
//		message = "访问成功";
//		String token = ValidTokenUtil.checkReq(request);
//		if(StringUtil.isEmpty(token)){
//			j.setMsg("API认证失败");
//			j.setSuccess(false);
//			return j;
//		}
//
//		CriteriaQuery cq = new CriteriaQuery(TaskEntity.class);
//		//查询条件组装器
////		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, checkRoute, request.getParameterMap());
//		try{
//
//			cq.eq("createuser",token);//我发起的任务
//			cq.eq("reviewer",token);//该我的处理的任务
//			Integer[] checkSteps = {0,2};
//			cq.in("checkStep",checkSteps);
//			cq.eq("checkStep",1);
//
//			cq.or(cq.and(cq,1,2), cq.and(cq,0,3));
//		}catch (Exception e) {
//			throw new BusinessException(e.getMessage());
//		}
//		List<TaskEntity> list = this.systemService.getListByCriteriaQuery(cq, false);
//
//		//查询关联的用户信息
//		List<TSBaseUser> baseUserList = new ArrayList<>();
//		if(list != null && list.size()>0){
//			List<String> ids = new ArrayList<>();
//			for(TaskEntity temp : list){
////				if(!ids.contains(temp.getCreateuser())){
////					ids.add(temp.getCreateuser());
////				}
//				if(!ids.contains(temp.getReviewer())){
//					ids.add(temp.getReviewer());
//				}
//			}
//			CriteriaQuery cq1 = new CriteriaQuery(TSBaseUser.class);
//			cq1.add(Property.forName("id").in(ids));
//			baseUserList = this.systemService.getListByCriteriaQuery(cq1, false);
//		}
//
//		List<Map<String, Object>> results = new ArrayList<>();
//		for(TaskEntity temp : list){
//			Map<String, Object> obj = new HashMap<>();
//			MyBeanUtils.copyBean2Map(obj, temp);
//			obj.remove("class");
//			obj.put("isSchedule", TaskUtils.isSchedule(temp));
//
//			int i = 0;
//			for(TSBaseUser baseUser : baseUserList){
//				if(i>2){i=0;break;}
////				if(temp.getCreateuser().equals(baseUser.getId())){
////					obj.put("createuserName", baseUser.getRealName());
////					i++;
////				}
//				if(temp.getReviewer().equals(baseUser.getId())){
//					obj.put("reviewerName", baseUser.getRealName());
//					i++;
//				}
//			}
//			results.add(obj);
//		}
//
//		//组织返回数据格式
//		j.setObj(results);
//		return j;
//	}
//
//
//
//
//
//	@RequestMapping(params = "datagrid")
//	@ResponseBody
//	public AjaxJson datagrid(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
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
//		String serialno = request.getParameter("serialno");
//		String type = request.getParameter("type");
//		String checkStep = request.getParameter("checkStep");
//		CriteriaQuery cq = new CriteriaQuery(TaskEntity.class, dataGrid);
//		//查询条件组装器
////		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, checkRoute, request.getParameterMap());
//		try{
//			//自定义追加查询条件
//			if(StringUtil.isNotEmpty(serialno)){
//				cq.add(Property.forName("serialno").eq(serialno));//设备号
//			}
//			if(StringUtil.isNotEmpty(type) && "0".equals(type)){
//				cq.add(Property.forName("createuser").eq(token));//我发起的任务
//			}
//			if(StringUtil.isNotEmpty(type) && "1".equals(type)){
//				cq.add(Property.forName("reviewer").eq(token));//该我的处理的任务
//			}
//			if(StringUtil.isNotEmpty(checkStep)){
//                List<Integer> checkSteps = new ArrayList<>();
//                for(String str : checkStep.split(",")){
//                    checkSteps.add(Integer.valueOf(str));
//                }
//                cq.add(Property.forName("checkStep").in(checkSteps));
//			}
//		}catch (Exception e) {
//			throw new BusinessException(e.getMessage());
//		}
//		cq.add();
//		this.systemService.getDataGridReturn(cq, true);
//		List<TaskEntity> list = dataGrid.getResults();
//		List<String> ids = new ArrayList<>();
//		for(TaskEntity temp : list){
////			if(!ids.contains(temp.getCreateuser())){
////				ids.add(temp.getCreateuser());
////			}
//			if(!ids.contains(temp.getReviewer())){
//				ids.add(temp.getReviewer());
//			}
//		}
//
//		//获取用户信息
//		List<TSBaseUser> baseUserList = new ArrayList<>();
//		if(ids.size() >0){
//			CriteriaQuery cq1 = new CriteriaQuery(TSBaseUser.class);
//			cq1.add(Property.forName("id").in(ids));
//			baseUserList = this.systemService.getListByCriteriaQuery(cq1, false);
//		}
//		List<Map<String, Object>> results = new ArrayList<>();
//		for(TaskEntity temp : list){
//			Map<String, Object> obj = new HashMap<>();
//			MyBeanUtils.copyBean2Map(obj, temp);
//			obj.remove("class");
//			obj.put("isSchedule", TaskUtils.isSchedule(temp));
//			int i = 0;
//			for(TSBaseUser baseUser : baseUserList){
//				if(i>2){i=0;break;}
////				if(temp.getCreateuser().equals(baseUser.getId())){
////					obj.put("createuserName", baseUser.getRealName());
////					i++;
////				}
//				if(temp.getReviewer().equals(baseUser.getId())){
//					obj.put("reviewerName", baseUser.getRealName());
//					i++;
//				}
//			}
//			results.add(obj);
//		}
//
//		//组织返回数据格式
//		Map<String, Object> resultMap = new HashMap<>();
//		resultMap.put("rows", dataGrid.getRows());
//		resultMap.put("page", dataGrid.getPage());
//		resultMap.put("total", dataGrid.getTotal());
//		resultMap.put("results", results);
//
//		j.setAttributes(resultMap);
//		return j;
//	}
//
//	/**
//	 * 新增任务
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping(params = "doAddList")
//	@ResponseBody
//	public AjaxJson doAddList(HttpServletRequest request) throws IOException {
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
//		String jsonStr = request.getParameter("objstr");
//		if(StringUtil.isEmpty(jsonStr)){
//			j.setSuccess(false);
//			j.setMsg("参数为空");
//			return j;
//		}
//		try {
//			List<TaskDto> dtos = JSON.parseArray(jsonStr, TaskDto.class);
////			taskService.addMainList(dtos, token);
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
//	 * 新增任务
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
//
//		String jsonStr = request.getParameter("objstr");
//		if(StringUtil.isEmpty(jsonStr)){
//			j.setSuccess(false);
//			j.setMsg("参数为空");
//			return j;
//		}
//		try {
//			TaskDto dto = JSON.parseObject(jsonStr, TaskDto.class);
////			taskService.addMain(dto, token);
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
//	 * 任务完成 保存任务结果信息
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping(params = "doUpdate")
//	@ResponseBody
//	public AjaxJson doUpdate(HttpServletRequest request) throws IOException {
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
//		String jsonStr = request.getParameter("objstr");
//		if(StringUtil.isEmpty(jsonStr)){
//			j.setSuccess(false);
//			j.setMsg("参数为空");
//			return j;
//		}
//		try {
//			//保存任务结果
//			TaskDto dto = JSON.parseObject(jsonStr, TaskDto.class);
//			TaskEntity entity = this.taskFeedbackService.getEntity(TaskEntity.class, dto.getId());
//			MyBeanUtils.copyBeanNotNull2Bean(dto, entity);
//			entity.setCheckStep(1);
////			entity.setModifydate(new Date());
////			entity.setModifyuser(token);
//
//			TSBaseUser baseUser = this.systemService.getEntity(TSBaseUser.class, token);
//			entity.setUpdateDate(new Date());
//			entity.setUpdateBy(baseUser.getUserName());
//			entity.setUpdateName(baseUser.getRealName());
//			systemService.saveOrUpdate(entity);
//			taskService._saveTaskLog(entity,"处理任务");
//
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
//	 * 任务评审
//	 * @param request
//	 * @return
//	 * @throws IOException
//	 */
//	@RequestMapping(params = "doAddFeedBack")
//	@ResponseBody
//	public AjaxJson doAddFeedBack(HttpServletRequest request)  {
//		String message = null;
//		AjaxJson j = new AjaxJson();
//		message = "访问成功";
//		String token = ValidTokenUtil.checkReq(request);
//		if(StringUtil.isEmpty(token)){
//			j.setMsg("API认证失败");
//			j.setSuccess(false);
//			return j;
//		}
//
//		String jsonStr = request.getParameter("objstr");
//		if(StringUtil.isEmpty(jsonStr)){
//			j.setSuccess(false);
//			j.setMsg("参数为空");
//			return j;
//		}
//		try {
//			TaskFeedbackDto dto = JSON.parseObject(jsonStr, TaskFeedbackDto.class);
//			taskFeedbackService.addMain(dto, token);
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
//	 * 任务详情
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping(params = "taskDetails")
//	@ResponseBody
//	public AjaxJson taskDetails(HttpServletRequest request) {
//		String message = null;
//		AjaxJson j = new AjaxJson();
//		message = "访问成功";
//		String token = ValidTokenUtil.checkReq(request);
//		if(StringUtil.isEmpty(token)){
//			j.setMsg("API认证失败");
//			j.setSuccess(false);
//			return j;
//		}
//
//		String id = request.getParameter("id");
//		if(StringUtil.isEmpty(id)){
//			j.setSuccess(false);
//			j.setMsg("参数为空");
//			return j;
//		}
//		try {
//
//			TaskEntity entity = systemService.getEntity(TaskEntity.class, Integer.valueOf(id));
//			Map<String, Object> resultMap = taskService.details(entity);
//			if("proposal".equals(entity.getTasktype()) && StringUtil.isNotEmpty(entity.getInspecPoiId())){
//				ProposalEntity proposalEntity = this.systemService.getEntity(ProposalEntity.class, entity.getInspecPoiId());
//				//返回数据
//				Map<String, Object> proposalMap = new HashMap<>();
//				MyBeanUtils.copyBean2Map(proposalMap, proposalEntity );
//				proposalMap.remove("class");
//				resultMap.put("proposalEntity", proposalMap);
//			}
//			j.setAttributes(resultMap);
//		} catch (Exception e) {
//			message = "访问失败";
//			throw new BusinessException(e.getMessage());
//		}
//		j.setMsg(message);
//		return j;
//	}
//
//	@RequestMapping(params = "waitDoTasks")
//	@ResponseBody
//	public AjaxJson waitDoTasks(HttpServletRequest request, HttpServletResponse response) {
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
//		List<TaskEntity> listmap = taskService.handleTask(token);
//
//		Map<String, Object> resultMap = new HashMap<String, Object>();
//		int disposalnum = taskService.unTaksByMe(token);//待我处理
//		int confirmnum = taskService.confirmTaksByMe(token);//待我确认
//		int rejectnum = taskService.backTaksByMe(token);//被驳回
//		resultMap.put("disposalnum", disposalnum);
//		resultMap.put("confirmnum", confirmnum);
//		resultMap.put("rejectnum", rejectnum);
//		if(disposalnum > 0){
//			resultMap.put("results", listmap);
//		}
//		resultMap.put("results", listmap);
//
//		j.setObj(listmap);
//		j.setAttributes(resultMap);
//		return j;
//	}


}
