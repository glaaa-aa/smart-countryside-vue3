package com.qfy.modules.repair.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.util.oConvertUtils;
import com.qfy.modules.repair.entity.RepairMopartUsed;
import com.qfy.modules.repair.service.IRepairMopartUsedService;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.system.base.controller.JeecgController;
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
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 维修备品更换
 * @Author: jeecg-boot
 * @Date:   2021-11-02
 * @Version: V1.0
 */
@Slf4j
@Api(tags="维修备品更换")
@RestController
@RequestMapping("/repair/repairMopartUsed")
public class RepairMopartUsedController extends JeecgController<RepairMopartUsed, IRepairMopartUsedService> {
	@Autowired
	private IRepairMopartUsedService repairMopartUsedService;

	/**
	 * 分页列表查询
	 *
	 * @param repairMopartUsed
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "维修备品更换-分页列表查询")
	@ApiOperation(value="维修备品更换-分页列表查询", notes="维修备品更换-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(RepairMopartUsed repairMopartUsed,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<RepairMopartUsed> queryWrapper = QueryGenerator.initQueryWrapper(repairMopartUsed, req.getParameterMap());
		Page<RepairMopartUsed> page = new Page<RepairMopartUsed>(pageNo, pageSize);
		IPage<RepairMopartUsed> pageList = repairMopartUsedService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	/**
	 * 添加
	 *
	 * @param repairMopartUsed
	 * @return
	 */
	@AutoLog(value = "维修备品更换-添加")
	@ApiOperation(value="维修备品更换-添加", notes="维修备品更换-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody RepairMopartUsed repairMopartUsed) {
		repairMopartUsedService.save(repairMopartUsed);
		return Result.OK("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param repairMopartUsed
	 * @return
	 */
	@AutoLog(value = "维修备品更换-编辑")
	@ApiOperation(value="维修备品更换-编辑", notes="维修备品更换-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody RepairMopartUsed repairMopartUsed) {
		repairMopartUsedService.updateById(repairMopartUsed);
		return Result.OK("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "维修备品更换-通过id删除")
	@ApiOperation(value="维修备品更换-通过id删除", notes="维修备品更换-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		repairMopartUsedService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "维修备品更换-批量删除")
	@ApiOperation(value="维修备品更换-批量删除", notes="维修备品更换-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.repairMopartUsedService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "维修备品更换-通过id查询")
	@ApiOperation(value="维修备品更换-通过id查询", notes="维修备品更换-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		RepairMopartUsed repairMopartUsed = repairMopartUsedService.getById(id);
		return Result.OK(repairMopartUsed);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param repairMopartUsed
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, RepairMopartUsed repairMopartUsed) {
      return super.exportXls(request, repairMopartUsed, RepairMopartUsed.class, "维修备品更换");
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
      return super.importExcel(request, response, RepairMopartUsed.class);
  }

}
