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

import com.qfy.modules.subsidy.entity.SubsidyHealthy;
import com.qfy.modules.subsidy.service.ISubsidyHealthyService;
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
 * @Description: 健康扶持信息表
 * @Author: jeecg-boot
 * @Date:   2022-07-01
 * @Version: V1.0
 */
@Api(tags="健康扶持信息表")
@RestController
@RequestMapping("/subsidy/subsidyHealthy")
@Slf4j
public class SubsidyHealthyController extends JeecgController<SubsidyHealthy, ISubsidyHealthyService> {
	@Autowired
	private ISubsidyHealthyService subsidyHealthyService;

	/**
	 * 分页列表查询
	 *
	 * @param subsidyHealthy
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "健康扶持信息表-分页列表查询")
	@ApiOperation(value="健康扶持信息表-分页列表查询", notes="健康扶持信息表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<SubsidyHealthy>> queryPageList(SubsidyHealthy subsidyHealthy,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<SubsidyHealthy> queryWrapper = QueryGenerator.initQueryWrapper(subsidyHealthy, req.getParameterMap());
		Page<SubsidyHealthy> page = new Page<SubsidyHealthy>(pageNo, pageSize);
		IPage<SubsidyHealthy> pageList = subsidyHealthyService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	/**
	 *   添加
	 *
	 * @param subsidyHealthy
	 * @return
	 */
	@AutoLog(value = "健康扶持信息表-添加")
	@ApiOperation(value="健康扶持信息表-添加", notes="健康扶持信息表-添加")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody SubsidyHealthy subsidyHealthy) {
		subsidyHealthyService.save(subsidyHealthy);
		return Result.OK("添加成功！");
	}

	/**
	 *  编辑
	 *
	 * @param subsidyHealthy
	 * @return
	 */
	@AutoLog(value = "健康扶持信息表-编辑")
	@ApiOperation(value="健康扶持信息表-编辑", notes="健康扶持信息表-编辑")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody SubsidyHealthy subsidyHealthy) {
		subsidyHealthyService.updateById(subsidyHealthy);
		return Result.OK("编辑成功!");
	}

	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "健康扶持信息表-通过id删除")
	@ApiOperation(value="健康扶持信息表-通过id删除", notes="健康扶持信息表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		subsidyHealthyService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "健康扶持信息表-批量删除")
	@ApiOperation(value="健康扶持信息表-批量删除", notes="健康扶持信息表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.subsidyHealthyService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "健康扶持信息表-通过id查询")
	@ApiOperation(value="健康扶持信息表-通过id查询", notes="健康扶持信息表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<SubsidyHealthy> queryById(@RequestParam(name="id",required=true) String id) {
		SubsidyHealthy subsidyHealthy = subsidyHealthyService.getById(id);
		if(subsidyHealthy==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(subsidyHealthy);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param subsidyHealthy
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, SubsidyHealthy subsidyHealthy) {
        return super.exportXls(request, subsidyHealthy, SubsidyHealthy.class, "健康扶持信息表");
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
        return super.importExcel(request, response, SubsidyHealthy.class);
    }

}