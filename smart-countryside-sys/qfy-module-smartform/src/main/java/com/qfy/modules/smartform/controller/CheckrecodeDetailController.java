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
import com.qfy.modules.smartform.entity.CheckrecodeDetail;
import com.qfy.modules.smartform.service.ICheckrecodeDetailService;

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
 * @Description: 检查记录详情
 * @Author: jeecg-boot
 * @Date:   2022-04-08
 * @Version: V1.0
 */
@Api(tags="检查记录详情")
@RestController
@RequestMapping("/smartform/checkrecodeDetail")
@Slf4j
public class CheckrecodeDetailController extends JeecgController<CheckrecodeDetail, ICheckrecodeDetailService> {
	@Autowired
	private ICheckrecodeDetailService checkrecodeDetailService;
	
	/**
	 * 分页列表查询
	 *
	 * @param checkrecodeDetail
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "检查记录详情-分页列表查询")
	@ApiOperation(value="检查记录详情-分页列表查询", notes="检查记录详情-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(CheckrecodeDetail checkrecodeDetail,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<CheckrecodeDetail> queryWrapper = QueryGenerator.initQueryWrapper(checkrecodeDetail, req.getParameterMap());
		Page<CheckrecodeDetail> page = new Page<CheckrecodeDetail>(pageNo, pageSize);
		IPage<CheckrecodeDetail> pageList = checkrecodeDetailService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param checkrecodeDetail
	 * @return
	 */
	@AutoLog(value = "检查记录详情-添加")
	@ApiOperation(value="检查记录详情-添加", notes="检查记录详情-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody CheckrecodeDetail checkrecodeDetail) {
        checkrecodeDetail.setBpmStatus("1");
		checkrecodeDetailService.save(checkrecodeDetail);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param checkrecodeDetail
	 * @return
	 */
	@AutoLog(value = "检查记录详情-编辑")
	@ApiOperation(value="检查记录详情-编辑", notes="检查记录详情-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody CheckrecodeDetail checkrecodeDetail) {
		checkrecodeDetailService.updateById(checkrecodeDetail);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "检查记录详情-通过id删除")
	@ApiOperation(value="检查记录详情-通过id删除", notes="检查记录详情-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		checkrecodeDetailService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "检查记录详情-批量删除")
	@ApiOperation(value="检查记录详情-批量删除", notes="检查记录详情-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.checkrecodeDetailService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "检查记录详情-通过id查询")
	@ApiOperation(value="检查记录详情-通过id查询", notes="检查记录详情-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		CheckrecodeDetail checkrecodeDetail = checkrecodeDetailService.getById(id);
		if(checkrecodeDetail==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(checkrecodeDetail);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param checkrecodeDetail
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, CheckrecodeDetail checkrecodeDetail) {
        return super.exportXls(request, checkrecodeDetail, CheckrecodeDetail.class, "检查记录详情");
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
        return super.importExcel(request, response, CheckrecodeDetail.class);
    }

}
