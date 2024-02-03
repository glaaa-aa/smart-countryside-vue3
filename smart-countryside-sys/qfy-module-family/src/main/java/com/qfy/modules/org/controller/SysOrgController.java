package com.qfy.modules.org.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qfy.modules.commons.model.TreeSelectModel;
import com.qfy.modules.org.entity.SysOrg;
import com.qfy.modules.org.service.ISysOrgService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.constant.CommonConstant;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.vo.DictModel;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.common.util.ImportExcelUtil;
import org.jeecg.common.util.oConvertUtils;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

 /**
 * @Description: 家庭村庄组织机构
 * @Author: jeecg-boot
 * @Date:   2022-06-01
 * @Version: V1.0
 */
@Api(tags="家庭村庄组织机构")
@RestController
@RequestMapping("/family/sysOrg")
@Slf4j
public class SysOrgController extends JeecgController<SysOrg, ISysOrgService> {

	@Autowired
	private ISysOrgService sysOrgService;

	private static String parentId = "A02";
	 /**
	  * 分页列表查询
	  * @param sysOrg
	  * @param pageNo
	  * @param pageSize
	  * @param req
	  * @return
	  */
	 @GetMapping(value = "/rootList")
	 public Result<IPage<SysOrg>> queryPageList(SysOrg sysOrg,
					 @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
					 @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
					 HttpServletRequest req) {
		 if(oConvertUtils.isEmpty(sysOrg.getPid())){
			 sysOrg.setPid(parentId); //暂时只查询 拉烈镇
		 }
		 Result<IPage<SysOrg>> result = new Result<IPage<SysOrg>>();

		 QueryWrapper<SysOrg> queryWrapper = QueryGenerator.initQueryWrapper(sysOrg, req.getParameterMap());
		 String name = sysOrg.getName();
		 String code = sysOrg.getCode();
		 if(StringUtils.isBlank(name)&&StringUtils.isBlank(code)){
			 queryWrapper.eq("pid", sysOrg.getPid());
		 }

		 Page<SysOrg> page = new Page<SysOrg>(pageNo, pageSize);
		 IPage<SysOrg> pageList = sysOrgService.page(page, queryWrapper);
		 result.setSuccess(true);
		 result.setResult(pageList);
		 return result;
	 }

	 @GetMapping(value = "/childList")
	 public Result<List<SysOrg>> queryPageList(SysOrg sysOrg,HttpServletRequest req) {
		 Result<List<SysOrg>> result = new Result<List<SysOrg>>();
		 QueryWrapper<SysOrg> queryWrapper = QueryGenerator.initQueryWrapper(sysOrg, req.getParameterMap());
		 List<SysOrg> list = sysOrgService.list(queryWrapper);
		 result.setSuccess(true);
		 result.setResult(list);
		 return result;
	 }


	 /**
	  *   添加
	  * @param SysOrg
	  * @return
	  */
	 @PostMapping(value = "/add")
	 public Result<SysOrg> add(@RequestBody SysOrg SysOrg) {
		 Result<SysOrg> result = new Result<SysOrg>();
		 try {
			 sysOrgService.addSysOrg(SysOrg);
			 result.success("添加成功！");
		 } catch (Exception e) {
			 log.error(e.getMessage(),e);
			 result.error500("操作失败");
		 }
		 return result;
	 }

	 /**
	  *  编辑
	  * @param SysOrg
	  * @return
	  */
	 @RequestMapping(value = "/edit", method = { RequestMethod.PUT,RequestMethod.POST })
	 public Result<SysOrg> edit(@RequestBody SysOrg SysOrg) {
		 Result<SysOrg> result = new Result<SysOrg>();
		 SysOrg SysOrgEntity = sysOrgService.getById(SysOrg.getId());
		 if(SysOrgEntity==null) {
			 result.error500("未找到对应实体");
		 }else {
			 sysOrgService.updateSysOrg(SysOrg);
			 result.success("修改成功!");
		 }
		 return result;
	 }

	 /**
	  *   通过id删除
	  * @param id
	  * @return
	  */
	 @DeleteMapping(value = "/delete")
	 public Result<SysOrg> delete(@RequestParam(name="id",required=true) String id) {
		 Result<SysOrg> result = new Result<SysOrg>();
		 SysOrg SysOrg = sysOrgService.getById(id);
		 if(SysOrg==null) {
			 result.error500("未找到对应实体");
		 }else {
			 this.sysOrgService.deleteSysOrg(id);
			 result.success("删除成功!");
		 }

		 return result;
	 }

	 /**
	  *  批量删除
	  * @param ids
	  * @return
	  */
	 @DeleteMapping(value = "/deleteBatch")
	 public Result<SysOrg> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		 Result<SysOrg> result = new Result<SysOrg>();
		 if(ids==null || "".equals(ids.trim())) {
			 result.error500("参数不识别！");
		 }else {
			 this.sysOrgService.deleteSysOrg(ids);
			 result.success("删除成功!");
		 }
		 return result;
	 }

	 /**
	  * 通过id查询
	  * @param id
	  * @return
	  */
	 @GetMapping(value = "/queryById")
	 public Result<SysOrg> queryById(@RequestParam(name="id",required=true) String id) {
		 Result<SysOrg> result = new Result<SysOrg>();
		 SysOrg SysOrg = sysOrgService.getById(id);
		 if(SysOrg==null) {
			 result.error500("未找到对应实体");
		 }else {
			 result.setResult(SysOrg);
			 result.setSuccess(true);
		 }
		 return result;
	 }

	 /**
	  * 导出excel
	  *
	  * @param request
	  */
	 @RequestMapping(value = "/exportXls")
	 public ModelAndView exportXls(HttpServletRequest request, SysOrg SysOrg) {
		 // Step.1 组装查询条件查询数据
		 QueryWrapper<SysOrg> queryWrapper = QueryGenerator.initQueryWrapper(SysOrg, request.getParameterMap());
		 List<SysOrg> pageList = sysOrgService.list(queryWrapper);
		 // Step.2 AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 // 过滤选中数据
		 String selections = request.getParameter("selections");
		 if(oConvertUtils.isEmpty(selections)) {
			 mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
		 }else {
			 List<String> selectionList = Arrays.asList(selections.split(","));
			 List<SysOrg> exportList = pageList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
			 mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
		 }
		 //导出文件名称
		 mv.addObject(NormalExcelConstants.FILE_NAME, "分类字典列表");
		 mv.addObject(NormalExcelConstants.CLASS, SysOrg.class);
		 LoginUser user = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("分类字典列表数据", "导出人:"+user.getRealname(), "导出信息"));
		 return mv;
	 }

	 /**
	  * 通过excel导入数据
	  *
	  * @param request
	  * @param response
	  * @return
	  */
	 @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
	 public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) throws IOException {
		 MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		 Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		 // 错误信息
		 List<String> errorMessage = new ArrayList<>();
		 int successLines = 0, errorLines = 0;
		 for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			 // 获取上传文件对象
			 MultipartFile file = entity.getValue();
			 ImportParams params = new ImportParams();
			 params.setTitleRows(2);
			 params.setHeadRows(1);
			 params.setNeedSave(true);
			 try {
				 List<SysOrg> listSysOrgs = ExcelImportUtil.importExcel(file.getInputStream(), SysOrg.class, params);
				 //按照编码长度排序
				 Collections.sort(listSysOrgs);
				 log.info("排序后的list====>",listSysOrgs);
				 for (int i = 0; i < listSysOrgs.size(); i++) {
					 SysOrg SysOrgExcel = listSysOrgs.get(i);
					 String code = SysOrgExcel.getCode();
					 if(code.length()>3){
						 String pCode = SysOrgExcel.getCode().substring(0,code.length()-3);
						 log.info("pCode====>",pCode);
						 String pId=sysOrgService.queryIdByCode(pCode);
						 log.info("pId====>",pId);
						 if(StringUtils.isNotBlank(pId)){
							 SysOrgExcel.setPid(pId);
						 }
					 }else{
						 SysOrgExcel.setPid("0");
					 }
					 try {
						 sysOrgService.save(SysOrgExcel);
						 successLines++;
					 } catch (Exception e) {
						 errorLines++;
						 String message = e.getMessage().toLowerCase();
						 int lineNumber = i + 1;
						 // 通过索引名判断出错信息
						 if (message.contains(CommonConstant.SQL_INDEX_UNIQ_CATEGORY_CODE)) {
							 errorMessage.add("第 " + lineNumber + " 行：分类编码已经存在，忽略导入。");
						 }  else {
							 errorMessage.add("第 " + lineNumber + " 行：未知错误，忽略导入");
							 log.error(e.getMessage(), e);
						 }
					 }
				 }
			 } catch (Exception e) {
				 errorMessage.add("发生异常：" + e.getMessage());
				 log.error(e.getMessage(), e);
			 } finally {
				 try {
					 file.getInputStream().close();
				 } catch (IOException e) {
					 e.printStackTrace();
				 }
			 }
		 }
		 return ImportExcelUtil.imporReturnRes(errorLines,successLines,errorMessage);
	 }



	 /**
	  * 加载单个数据 用于回显
	  */
	 @RequestMapping(value = "/loadOne", method = RequestMethod.GET)
	 public Result<SysOrg> loadOne(@RequestParam(name="field") String field,@RequestParam(name="val") String val) {
		 Result<SysOrg> result = new Result<SysOrg>();
		 try {

			 QueryWrapper<SysOrg> query = new QueryWrapper<SysOrg>();
			 query.eq(field, val);
			 List<SysOrg> ls = this.sysOrgService.list(query);
			 if(ls==null || ls.size()==0) {
				 result.setMessage("查询无果");
				 result.setSuccess(false);
			 }else if(ls.size()>1) {
				 result.setMessage("查询数据异常,["+field+"]存在多个值:"+val);
				 result.setSuccess(false);
			 }else {
				 result.setSuccess(true);
				 result.setResult(ls.get(0));
			 }
		 } catch (Exception e) {
			 e.printStackTrace();
			 result.setMessage(e.getMessage());
			 result.setSuccess(false);
		 }
		 return result;
	 }

	 /**
	  * 加载节点的子数据
	  */
	 @RequestMapping(value = "/loadTreeChildren", method = RequestMethod.GET)
	 public Result<List<TreeSelectModel>> loadTreeChildren(@RequestParam(name="pid") String pid) {
		 Result<List<TreeSelectModel>> result = new Result<List<TreeSelectModel>>();
		 try {
			 List<TreeSelectModel> ls = this.sysOrgService.queryListByPid(pid);
			 result.setResult(ls);
			 result.setSuccess(true);
		 } catch (Exception e) {
			 e.printStackTrace();
			 result.setMessage(e.getMessage());
			 result.setSuccess(false);
		 }
		 return result;
	 }

	 /**
	  * 加载一级节点/如果是同步 则所有数据
	  */
	 @RequestMapping(value = "/loadTreeRoot", method = RequestMethod.GET)
	 public Result<List<TreeSelectModel>> loadTreeRoot(@RequestParam(name="async") Boolean async,@RequestParam(name="pcode") String pcode) {
		 Result<List<TreeSelectModel>> result = new Result<List<TreeSelectModel>>();
		 try {
			 List<TreeSelectModel> ls = this.sysOrgService.queryListByCode(pcode);
			 if(!async) {
				 loadAllCategoryChildren(ls);
			 }
			 result.setResult(ls);
			 result.setSuccess(true);
		 } catch (Exception e) {
			 e.printStackTrace();
			 result.setMessage(e.getMessage());
			 result.setSuccess(false);
		 }
		 return result;
	 }

	 /**
	  * 递归求子节点 同步加载用到
	  */
	 private void loadAllCategoryChildren(List<TreeSelectModel> ls) {
		 for (TreeSelectModel tsm : ls) {
			 List<TreeSelectModel> temp = this.sysOrgService.queryListByPid(tsm.getKey());
			 if(temp!=null && temp.size()>0) {
				 tsm.setChildren(temp);
				 loadAllCategoryChildren(temp);
			 }
		 }
	 }

	 /**
	  * 校验编码
	  * @param pid
	  * @param code
	  * @return
	  */
	 @GetMapping(value = "/checkCode")
	 public Result<?> checkCode(@RequestParam(name="pid",required = false) String pid,@RequestParam(name="code",required = false) String code) {
		 if(oConvertUtils.isEmpty(code)){
			 return Result.error("错误,类型编码为空!");
		 }
		 if(oConvertUtils.isEmpty(pid)){
			 return Result.ok();
		 }
		 SysOrg parent = this.sysOrgService.getById(pid);
		 if(code.startsWith(parent.getCode())){
			 return Result.ok();
		 }else{
			 return Result.error("编码不符合规范,须以\""+parent.getCode()+"\"开头!");
		 }

	 }


	 /**
	  * 分类字典树控件 加载节点
	  * @param pid
	  * @param pcode
	  * @param condition
	  * @return
	  */
	 @RequestMapping(value = "/loadTreeData", method = RequestMethod.GET)
	 public Result<List<TreeSelectModel>> loadDict(@RequestParam(name="pid",required = false) String pid,@RequestParam(name="pcode",required = false) String pcode, @RequestParam(name="condition",required = false) String condition) {
		 Result<List<TreeSelectModel>> result = new Result<List<TreeSelectModel>>();
		 //pid如果传值了 就忽略pcode的作用
		 if(oConvertUtils.isEmpty(pid)){
			 if(oConvertUtils.isEmpty(pcode)){
				 result.setSuccess(false);
				 result.setMessage("加载分类字典树参数有误.[null]!");
				 return result;
			 }else{
				 if(ISysOrgService.ROOT_PID_VALUE.equals(pcode)){
					 pid = ISysOrgService.ROOT_PID_VALUE;
				 }else{
					 pid = this.sysOrgService.queryIdByCode(pcode);
				 }
				 if(oConvertUtils.isEmpty(pid)){
					 result.setSuccess(false);
					 result.setMessage("加载分类字典树参数有误.[code]!");
					 return result;
				 }
			 }
		 }
		 Map<String, String> query = null;
		 if(oConvertUtils.isNotEmpty(condition)) {
			 query = JSON.parseObject(condition, Map.class);
		 }
		 List<TreeSelectModel> ls = sysOrgService.queryListByPid(pid,query);
		 result.setSuccess(true);
		 result.setResult(ls);
		 return result;
	 }

	 /**
	  * 分类字典控件数据回显[表单页面]
	  *
	  * @param ids
	  * @param delNotExist 是否移除不存在的项，默认为true，设为false如果某个key不存在数据库中，则直接返回key本身
	  * @return
	  */
	 @RequestMapping(value = "/loadDictItem", method = RequestMethod.GET)
	 public Result<List<String>> loadDictItem(@RequestParam(name = "ids") String ids, @RequestParam(name = "delNotExist", required = false, defaultValue = "true") boolean delNotExist) {
		 Result<List<String>> result = new Result<>();
		 // 非空判断
		 if (StringUtils.isBlank(ids)) {
			 result.setSuccess(false);
			 result.setMessage("ids 不能为空");
			 return result;
		 }
		 // 查询数据
		 List<String> textList = sysOrgService.loadDictItem(ids, delNotExist);
		 result.setSuccess(true);
		 result.setResult(textList);
		 return result;
	 }

	 /**
	  * [列表页面]加载分类字典数据 用于值的替换
	  * @param code
	  * @return
	  */
	 @RequestMapping(value = "/loadAllData", method = RequestMethod.GET)
	 public Result<List<DictModel>> loadAllData(@RequestParam(name="code",required = true) String code) {
		 Result<List<DictModel>> result = new Result<List<DictModel>>();
		 LambdaQueryWrapper<SysOrg> query = new LambdaQueryWrapper<SysOrg>();
		 if(oConvertUtils.isNotEmpty(code) && !"0".equals(code)){
			 query.likeRight(SysOrg::getCode,code);
		 }
		 List<SysOrg> list = this.sysOrgService.list(query);
		 if(list==null || list.size()==0) {
			 result.setMessage("无数据,参数有误.[code]");
			 result.setSuccess(false);
			 return result;
		 }
		 List<DictModel> rdList = new ArrayList<DictModel>();
		 for (SysOrg c : list) {
			 rdList.add(new DictModel(c.getId(),c.getName()));
		 }
		 result.setSuccess(true);
		 result.setResult(rdList);
		 return result;
	 }

	 /**
	  * 根据父级id批量查询子节点
	  * @param parentIds
	  * @return
	  */
	 @GetMapping("/getChildListBatch")
	 public Result getChildListBatch(@RequestParam("parentIds") String parentIds) {
		 try {
			 QueryWrapper<SysOrg> queryWrapper = new QueryWrapper<>();
			 List<String> parentIdList = Arrays.asList(parentIds.split(","));
			 queryWrapper.in("pid", parentIdList);
			 List<SysOrg> list = sysOrgService.list(queryWrapper);
			 IPage<SysOrg> pageList = new Page<>(1, 10, list.size());
			 pageList.setRecords(list);
			 return Result.OK(pageList);
		 } catch (Exception e) {
			 log.error(e.getMessage(), e);
			 return Result.error("批量查询子节点失败：" + e.getMessage());
		 }
	 }

 }
