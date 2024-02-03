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
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import com.qfy.modules.smartform.entity.CheckPlan;
import com.qfy.modules.smartform.service.ICheckPlanService;

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
 * @Description: 巡检计划（检查计划）
 * @Author: jeecg-boot
 * @Date:   2022-04-08
 * @Version: V1.0
 */
@Api(tags="巡检计划（检查计划）")
@RestController
@RequestMapping("/smartform/checkPlan")
@Slf4j
public class CheckPlanController extends JeecgController<CheckPlan, ICheckPlanService> {
	@Autowired
	private ICheckPlanService checkPlanService;
	
	/**
	 * 分页列表查询
	 *
	 * @param checkPlan
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "巡检计划（检查计划）-分页列表查询")
	@ApiOperation(value="巡检计划（检查计划）-分页列表查询", notes="巡检计划（检查计划）-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(CheckPlan checkPlan,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<CheckPlan> queryWrapper = QueryGenerator.initQueryWrapper(checkPlan, req.getParameterMap());
		Page<CheckPlan> page = new Page<CheckPlan>(pageNo, pageSize);
		IPage<CheckPlan> pageList = checkPlanService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param checkPlan
	 * @return
	 */
	@AutoLog(value = "巡检计划（检查计划）-添加")
	@ApiOperation(value="巡检计划（检查计划）-添加", notes="巡检计划（检查计划）-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody CheckPlan checkPlan) {
		checkPlanService.save(checkPlan);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param checkPlan
	 * @return
	 */
	@AutoLog(value = "巡检计划（检查计划）-编辑")
	@ApiOperation(value="巡检计划（检查计划）-编辑", notes="巡检计划（检查计划）-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody CheckPlan checkPlan) {
		checkPlanService.updateById(checkPlan);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "巡检计划（检查计划）-通过id删除")
	@ApiOperation(value="巡检计划（检查计划）-通过id删除", notes="巡检计划（检查计划）-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		checkPlanService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "巡检计划（检查计划）-批量删除")
	@ApiOperation(value="巡检计划（检查计划）-批量删除", notes="巡检计划（检查计划）-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.checkPlanService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "巡检计划（检查计划）-通过id查询")
	@ApiOperation(value="巡检计划（检查计划）-通过id查询", notes="巡检计划（检查计划）-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		CheckPlan checkPlan = checkPlanService.getById(id);
		if(checkPlan==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(checkPlan);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param checkPlan
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, CheckPlan checkPlan) {
        return super.exportXls(request, checkPlan, CheckPlan.class, "巡检计划（检查计划）");
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
        return super.importExcel(request, response, CheckPlan.class);
    }

}
