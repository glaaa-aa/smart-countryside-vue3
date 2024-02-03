package com.qfy.modules.cms.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qfy.modules.commons.model.TreeSelectModel;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.constant.CommonConstant;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import com.qfy.modules.cms.entity.CmsSection;
import com.qfy.modules.cms.service.ICmsSectionService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: 栏目管理
 * @Author: jeecg-boot
 * @Date:   2022-04-05
 * @Version: V1.0
 */
@Api(tags="Cms管理")
@RestController
@RequestMapping("/cms/cmsSection")
@Slf4j
public class CmsSectionController extends JeecgController<CmsSection, ICmsSectionService>{
	@Autowired
	private ICmsSectionService cmsSectionService;

	/**
	 * 分页列表查询
	 *
	 * @param cmsSection
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "栏目管理-分页列表查询")
	@ApiOperation(value="栏目管理-分页列表查询", notes="栏目管理-分页列表查询")
	@GetMapping(value = "/rootList")
	public Result<?> queryPageList(CmsSection cmsSection,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		String hasQuery = req.getParameter("hasQuery");
        if(hasQuery != null && "true".equals(hasQuery)){
            QueryWrapper<CmsSection> queryWrapper =  QueryGenerator.initQueryWrapper(cmsSection, req.getParameterMap());
            List<CmsSection> list = cmsSectionService.queryTreeListNoPage(queryWrapper);
            IPage<CmsSection> pageList = new Page<>(1, 10, list.size());
            pageList.setRecords(list);
            return Result.OK(pageList);
        }else{
            String parentId = cmsSection.getPid();
            if (oConvertUtils.isEmpty(parentId)) {
                parentId = "0";
            }
            cmsSection.setPid(null);
            QueryWrapper<CmsSection> queryWrapper = QueryGenerator.initQueryWrapper(cmsSection, req.getParameterMap());
            // 使用 eq 防止模糊查询
            queryWrapper.eq("pid", parentId);
            Page<CmsSection> page = new Page<CmsSection>(pageNo, pageSize);
            IPage<CmsSection> pageList = cmsSectionService.page(page, queryWrapper);
            return Result.OK(pageList);
        }
	}

	 /**
	  * 字典树控件 加载节点
	  * @param pid
	  * @param pcode
	  * @param condition
	  * @return
	  */
	 @AutoLog(value = "栏目管理-字典树控件加载节点")
	 @ApiOperation(value="栏目管理-字典树控件加载节点", notes="栏目管理-字典树控件加载节点")
	 @GetMapping(value = "/loadTreeRoot")
	 public Result<List<TreeSelectModel>> loadTreeRoot(@RequestParam(name="pid",required = false) String pid,
												   @RequestParam(name="pcode",required = false) String pcode,
												   @RequestParam(name="condition",required = false) String condition) {
		 Result<List<TreeSelectModel>> result = new Result<List<TreeSelectModel>>();
		 //pid如果传值了 就忽略pcode的作用
		 if(oConvertUtils.isEmpty(pid)){
			 if(oConvertUtils.isEmpty(pcode)){
				 result.setSuccess(false);
				 result.setMessage("加载分类字典树参数有误.[null]!");
				 return result;
			 }else{
				 if(CommonConstant.ROOT_PID_VALUE.equals(pcode)){
					 pid = CommonConstant.ROOT_PID_VALUE;
				 }else{
					 pid = this.service.queryIdByCode(pcode);
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
		 List<TreeSelectModel> ls = service.queryListByPid(pid,query);
		 result.setSuccess(true);
		 result.setResult(ls);
		 return result;
	 }

	 /**
      * 获取子数据
      * @param cmsSection
      * @param req
      * @return
      */
	@AutoLog(value = "栏目管理-获取子数据")
	@ApiOperation(value="栏目管理-获取子数据", notes="栏目管理-获取子数据")
	@GetMapping(value = "/childList")
	public Result<?> queryPageList(CmsSection cmsSection,HttpServletRequest req) {
		QueryWrapper<CmsSection> queryWrapper = QueryGenerator.initQueryWrapper(cmsSection, req.getParameterMap());
		List<CmsSection> list = cmsSectionService.list(queryWrapper);
		IPage<CmsSection> pageList = new Page<>(1, 10, list.size());
        pageList.setRecords(list);
		return Result.OK(pageList);
	}

    /**
      * 批量查询子节点
      * @param parentIds 父ID（多个采用半角逗号分割）
      * @return 返回 IPage
      * @param parentIds
      * @return
      */
	@AutoLog(value = "栏目管理-批量获取子数据")
    @ApiOperation(value="栏目管理-批量获取子数据", notes="栏目管理-批量获取子数据")
    @GetMapping("/getChildListBatch")
    public Result getChildListBatch(@RequestParam("parentIds") String parentIds) {
        try {
            QueryWrapper<CmsSection> queryWrapper = new QueryWrapper<>();
            List<String> parentIdList = Arrays.asList(parentIds.split(","));
            queryWrapper.in("pid", parentIdList);
            List<CmsSection> list = cmsSectionService.list(queryWrapper);
            IPage<CmsSection> pageList = new Page<>(1, 10, list.size());
            pageList.setRecords(list);
            return Result.OK(pageList);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.error("批量查询子节点失败：" + e.getMessage());
        }
    }

	/**
	 *   添加
	 *
	 * @param cmsSection
	 * @return
	 */
	@AutoLog(value = "栏目管理-添加")
	@ApiOperation(value="栏目管理-添加", notes="栏目管理-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody CmsSection cmsSection) {
		cmsSectionService.addCmsSection(cmsSection);
		return Result.OK("添加成功！");
	}

	/**
	 *  编辑
	 *
	 * @param cmsSection
	 * @return
	 */
	@AutoLog(value = "栏目管理-编辑")
	@ApiOperation(value="栏目管理-编辑", notes="栏目管理-编辑")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<?> edit(@RequestBody CmsSection cmsSection) {
		cmsSectionService.updateCmsSection(cmsSection);
		return Result.OK("编辑成功!");
	}

	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "栏目管理-通过id删除")
	@ApiOperation(value="栏目管理-通过id删除", notes="栏目管理-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		cmsSectionService.deleteCmsSection(id);
		return Result.OK("删除成功!");
	}

	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "栏目管理-批量删除")
	@ApiOperation(value="栏目管理-批量删除", notes="栏目管理-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.cmsSectionService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "栏目管理-通过id查询")
	@ApiOperation(value="栏目管理-通过id查询", notes="栏目管理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		CmsSection cmsSection = cmsSectionService.getById(id);
		if(cmsSection==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(cmsSection);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param cmsSection
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, CmsSection cmsSection) {
		return super.exportXls(request, cmsSection, CmsSection.class, "栏目管理");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
		return super.importExcel(request, response, CmsSection.class);
    }

}
