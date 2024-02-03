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

import com.qfy.modules.smartform.entity.CheckItemRadio;
import com.qfy.modules.smartform.service.ICheckItemRadioService;
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
 * @Description: 单选题/下拉题
 * @Author: jeecg-boot
 * @Date:   2024-01-23
 * @Version: V1.0
 */
@Api(tags="单选题/下拉题")
@RestController
@RequestMapping("/smartform/checkItemRadio")
@Slf4j
public class CheckItemRadioController extends JeecgController<CheckItemRadio, ICheckItemRadioService> {
	@Autowired
	private ICheckItemRadioService checkItemRadioService;
	
	/**
	 * 分页列表查询
	 *
	 * @param checkItemRadio
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "单选题/下拉题-分页列表查询")
	@ApiOperation(value="单选题/下拉题-分页列表查询", notes="单选题/下拉题-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<CheckItemRadio>> queryPageList(CheckItemRadio checkItemRadio,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<CheckItemRadio> queryWrapper = QueryGenerator.initQueryWrapper(checkItemRadio, req.getParameterMap());
		Page<CheckItemRadio> page = new Page<CheckItemRadio>(pageNo, pageSize);
		IPage<CheckItemRadio> pageList = checkItemRadioService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param checkItemRadio
	 * @return
	 */
	@AutoLog(value = "单选题/下拉题-添加")
	@ApiOperation(value="单选题/下拉题-添加", notes="单选题/下拉题-添加")
	@RequiresPermissions("smartform:qfy_check_item_radio:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody CheckItemRadio checkItemRadio) {
		checkItemRadioService.save(checkItemRadio);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param checkItemRadio
	 * @return
	 */
	@AutoLog(value = "单选题/下拉题-编辑")
	@ApiOperation(value="单选题/下拉题-编辑", notes="单选题/下拉题-编辑")
	@RequiresPermissions("smartform:qfy_check_item_radio:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody CheckItemRadio checkItemRadio) {
		checkItemRadioService.updateById(checkItemRadio);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "单选题/下拉题-通过id删除")
	@ApiOperation(value="单选题/下拉题-通过id删除", notes="单选题/下拉题-通过id删除")
	@RequiresPermissions("smartform:qfy_check_item_radio:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		checkItemRadioService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "单选题/下拉题-批量删除")
	@ApiOperation(value="单选题/下拉题-批量删除", notes="单选题/下拉题-批量删除")
	@RequiresPermissions("smartform:qfy_check_item_radio:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.checkItemRadioService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "单选题/下拉题-通过id查询")
	@ApiOperation(value="单选题/下拉题-通过id查询", notes="单选题/下拉题-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<CheckItemRadio> queryById(@RequestParam(name="id",required=true) String id) {
		CheckItemRadio checkItemRadio = checkItemRadioService.getById(id);
		if(checkItemRadio==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(checkItemRadio);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param checkItemRadio
    */
    @RequiresPermissions("smartform:qfy_check_item_radio:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, CheckItemRadio checkItemRadio) {
        return super.exportXls(request, checkItemRadio, CheckItemRadio.class, "单选题/下拉题");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequiresPermissions("smartform:qfy_check_item_radio:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, CheckItemRadio.class);
    }

}
