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

import com.qfy.modules.smartform.entity.CheckItemLogic;
import com.qfy.modules.smartform.service.ICheckItemLogicService;
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
 * @Description: 题目逻辑设置
 * @Author: jeecg-boot
 * @Date:   2024-01-23
 * @Version: V1.0
 */
@Api(tags="题目逻辑设置")
@RestController
@RequestMapping("/smartform/checkItemLogic")
@Slf4j
public class CheckItemLogicController extends JeecgController<CheckItemLogic, ICheckItemLogicService> {
	@Autowired
	private ICheckItemLogicService checkItemLogicService;
	
	/**
	 * 分页列表查询
	 *
	 * @param checkItemLogic
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "题目逻辑设置-分页列表查询")
	@ApiOperation(value="题目逻辑设置-分页列表查询", notes="题目逻辑设置-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<CheckItemLogic>> queryPageList(CheckItemLogic checkItemLogic,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<CheckItemLogic> queryWrapper = QueryGenerator.initQueryWrapper(checkItemLogic, req.getParameterMap());
		Page<CheckItemLogic> page = new Page<CheckItemLogic>(pageNo, pageSize);
		IPage<CheckItemLogic> pageList = checkItemLogicService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param checkItemLogic
	 * @return
	 */
	@AutoLog(value = "题目逻辑设置-添加")
	@ApiOperation(value="题目逻辑设置-添加", notes="题目逻辑设置-添加")
	@RequiresPermissions("smartform:qfy_check_item_logic:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody CheckItemLogic checkItemLogic) {
		checkItemLogicService.save(checkItemLogic);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param checkItemLogic
	 * @return
	 */
	@AutoLog(value = "题目逻辑设置-编辑")
	@ApiOperation(value="题目逻辑设置-编辑", notes="题目逻辑设置-编辑")
	@RequiresPermissions("smartform:qfy_check_item_logic:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody CheckItemLogic checkItemLogic) {
		checkItemLogicService.updateById(checkItemLogic);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "题目逻辑设置-通过id删除")
	@ApiOperation(value="题目逻辑设置-通过id删除", notes="题目逻辑设置-通过id删除")
	@RequiresPermissions("smartform:qfy_check_item_logic:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		checkItemLogicService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "题目逻辑设置-批量删除")
	@ApiOperation(value="题目逻辑设置-批量删除", notes="题目逻辑设置-批量删除")
	@RequiresPermissions("smartform:qfy_check_item_logic:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.checkItemLogicService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "题目逻辑设置-通过id查询")
	@ApiOperation(value="题目逻辑设置-通过id查询", notes="题目逻辑设置-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<CheckItemLogic> queryById(@RequestParam(name="id",required=true) String id) {
		CheckItemLogic checkItemLogic = checkItemLogicService.getById(id);
		if(checkItemLogic==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(checkItemLogic);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param checkItemLogic
    */
    @RequiresPermissions("smartform:qfy_check_item_logic:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, CheckItemLogic checkItemLogic) {
        return super.exportXls(request, checkItemLogic, CheckItemLogic.class, "题目逻辑设置");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequiresPermissions("smartform:qfy_check_item_logic:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, CheckItemLogic.class);
    }

}
