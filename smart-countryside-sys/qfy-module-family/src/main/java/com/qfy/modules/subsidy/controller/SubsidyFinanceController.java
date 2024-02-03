package com.qfy.modules.subsidy.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qfy.modules.subsidy.entity.SubsidyFinance;
import com.qfy.modules.subsidy.service.ISubsidyFinanceService;
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

 /**
 * @Description: 医疗信息表
 * @Author: jeecg-boot
 * @Date:   2022-07-01
 * @Version: V1.0
 */
@Api(tags="医疗信息表")
@RestController
@RequestMapping("/subsidy/subsidyFinance")
@Slf4j
public class SubsidyFinanceController extends JeecgController<SubsidyFinance, ISubsidyFinanceService> {
	@Autowired
	private ISubsidyFinanceService subsidyFinanceService;

	/**
	 * 分页列表查询
	 *
	 * @param subsidyFinance
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "医疗信息表-分页列表查询")
	@ApiOperation(value="医疗信息表-分页列表查询", notes="医疗信息表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<SubsidyFinance>> queryPageList(SubsidyFinance subsidyFinance,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<SubsidyFinance> queryWrapper = QueryGenerator.initQueryWrapper(subsidyFinance, req.getParameterMap());
		Page<SubsidyFinance> page = new Page<SubsidyFinance>(pageNo, pageSize);
		IPage<SubsidyFinance> pageList = subsidyFinanceService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	/**
	 *   添加
	 *
	 * @param subsidyFinance
	 * @return
	 */
	@AutoLog(value = "医疗信息表-添加")
	@ApiOperation(value="医疗信息表-添加", notes="医疗信息表-添加")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody SubsidyFinance subsidyFinance) {
		subsidyFinanceService.save(subsidyFinance);
		return Result.OK("添加成功！");
	}

	/**
	 *  编辑
	 *
	 * @param subsidyFinance
	 * @return
	 */
	@AutoLog(value = "医疗信息表-编辑")
	@ApiOperation(value="医疗信息表-编辑", notes="医疗信息表-编辑")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody SubsidyFinance subsidyFinance) {
		subsidyFinanceService.updateById(subsidyFinance);
		return Result.OK("编辑成功!");
	}

	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "医疗信息表-通过id删除")
	@ApiOperation(value="医疗信息表-通过id删除", notes="医疗信息表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		subsidyFinanceService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "医疗信息表-批量删除")
	@ApiOperation(value="医疗信息表-批量删除", notes="医疗信息表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.subsidyFinanceService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "医疗信息表-通过id查询")
	@ApiOperation(value="医疗信息表-通过id查询", notes="医疗信息表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<SubsidyFinance> queryById(@RequestParam(name="id",required=true) String id) {
		SubsidyFinance subsidyFinance = subsidyFinanceService.getById(id);
		if(subsidyFinance==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(subsidyFinance);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param subsidyFinance
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, SubsidyFinance subsidyFinance) {
        return super.exportXls(request, subsidyFinance, SubsidyFinance.class, "医疗信息表");
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
        return super.importExcel(request, response, SubsidyFinance.class);
    }

}
