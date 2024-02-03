package com.qfy.modules.smartform.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qfy.modules.smartform.entity.CheckTemplate;
import com.qfy.modules.smartform.service.ICheckTemplateService;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;

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
import org.apache.shiro.authz.annotation.RequiresPermissions;

 /**
 * @Description: 模板/问卷
 * @Author: jeecg-boot
 * @Date:   2024-01-22
 * @Version: V1.0
 */
@Api(tags="模板/问卷")
@RestController
@RequestMapping("/smartform/checkTemplate")
@Slf4j
public class CheckTemplateController extends JeecgController<CheckTemplate, ICheckTemplateService> {
	@Autowired
	private ICheckTemplateService checkTemplateService;
	
	/**
	 * 分页列表查询
	 *
	 * @param checkTemplate
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "模板/问卷-分页列表查询")
	@ApiOperation(value="模板/问卷-分页列表查询", notes="模板/问卷-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<CheckTemplate>> queryPageList(CheckTemplate checkTemplate,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<CheckTemplate> queryWrapper = QueryGenerator.initQueryWrapper(checkTemplate, req.getParameterMap());
		Page<CheckTemplate> page = new Page<CheckTemplate>(pageNo, pageSize);
		IPage<CheckTemplate> pageList = checkTemplateService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param checkTemplate
	 * @return
	 */
	@AutoLog(value = "模板/问卷-添加")
	@ApiOperation(value="模板/问卷-添加", notes="模板/问卷-添加")
//	@RequiresPermissions("smartform:qfy_check_template:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody CheckTemplate checkTemplate) {
		checkTemplateService.save(checkTemplate);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param checkTemplate
	 * @return
	 */
	@AutoLog(value = "模板/问卷-编辑")
	@ApiOperation(value="模板/问卷-编辑", notes="模板/问卷-编辑")
//	@RequiresPermissions("smartform:qfy_check_template:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody CheckTemplate checkTemplate) {
		checkTemplateService.updateById(checkTemplate);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "模板/问卷-通过id删除")
	@ApiOperation(value="模板/问卷-通过id删除", notes="模板/问卷-通过id删除")
//	@RequiresPermissions("smartform:qfy_check_template:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		checkTemplateService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "模板/问卷-批量删除")
	@ApiOperation(value="模板/问卷-批量删除", notes="模板/问卷-批量删除")
//	@RequiresPermissions("smartform:qfy_check_template:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.checkTemplateService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "模板/问卷-通过id查询")
	@ApiOperation(value="模板/问卷-通过id查询", notes="模板/问卷-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<CheckTemplate> queryById(@RequestParam(name="id",required=true) String id) {
		CheckTemplate checkTemplate = checkTemplateService.getById(id);
		if(checkTemplate==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(checkTemplate);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param checkTemplate
    */
//    @RequiresPermissions("smartform:qfy_check_template:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, CheckTemplate checkTemplate) {
        return super.exportXls(request, checkTemplate, CheckTemplate.class, "模板/问卷");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
//    @RequiresPermissions("smartform:qfy_check_template:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, CheckTemplate.class);
    }

}
