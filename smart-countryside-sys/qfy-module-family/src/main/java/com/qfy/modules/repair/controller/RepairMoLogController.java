package com.qfy.modules.repair.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qfy.modules.repair.entity.RepairMoLog;
import com.qfy.modules.repair.service.IRepairMoLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.aspect.annotation.PermissionData;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.query.QueryGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

 /**
 * @Description: 维修工单日志
 * @Author: jeecg-boot
 * @Date:   2021-11-02
 * @Version: V1.0
 */
@Slf4j
@Api(tags="维修工单日志")
@RestController
@RequestMapping("/repair/repairMoLog")
public class RepairMoLogController extends JeecgController<RepairMoLog, IRepairMoLogService> {
	@Autowired
	private IRepairMoLogService repairMoLogService;



	/**
	 * 分页列表查询
	 *
	 * @param repairMoLog
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "维修工单日志-分页列表查询")
	@ApiOperation(value="维修工单日志-分页列表查询", notes="维修工单日志-分页列表查询")
	@GetMapping(value = "/list")
	@PermissionData(pageComponent = "device/repair/RepairMoLogList")
	public Result<?> queryPageList(RepairMoLog repairMoLog,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<RepairMoLog> queryWrapper = QueryGenerator.initQueryWrapper(repairMoLog, req.getParameterMap());
		Page<RepairMoLog> page = new Page<RepairMoLog>(pageNo, pageSize);

		String srcDocCode = req.getParameter("srcDocCode");
		if (StringUtils.isNotBlank(srcDocCode)){
			queryWrapper.inSql("mo_code", "select mo_code from qfy_andon_repair_mo " +
					"where src_doc_code = '" + srcDocCode + "'");
			queryWrapper.orderByDesc("create_time");
			queryWrapper.orderByDesc("s1");
		} else {
			queryWrapper.orderByDesc("create_time");
		}
		IPage<RepairMoLog> pageList = repairMoLogService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	/**
	 * 添加
	 *
	 * @param repairMoLog
	 * @return
	 */
	@AutoLog(value = "维修工单日志-添加")
	@ApiOperation(value="维修工单日志-添加", notes="维修工单日志-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody RepairMoLog repairMoLog) {
		repairMoLogService.save(repairMoLog);
		return Result.OK("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param repairMoLog
	 * @return
	 */
	@AutoLog(value = "维修工单日志-编辑")
	@ApiOperation(value="维修工单日志-编辑", notes="维修工单日志-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody RepairMoLog repairMoLog) {
		repairMoLogService.updateById(repairMoLog);
		return Result.OK("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "维修工单日志-通过id删除")
	@ApiOperation(value="维修工单日志-通过id删除", notes="维修工单日志-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		repairMoLogService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "维修工单日志-批量删除")
	@ApiOperation(value="维修工单日志-批量删除", notes="维修工单日志-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.repairMoLogService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "维修工单日志-通过id查询")
	@ApiOperation(value="维修工单日志-通过id查询", notes="维修工单日志-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		RepairMoLog repairMoLog = repairMoLogService.getById(id);
		return Result.OK(repairMoLog);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param repairMoLog
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, RepairMoLog repairMoLog) {
      return super.exportXls(request, repairMoLog, RepairMoLog.class, "维修工单日志");
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
      return super.importExcel(request, response, RepairMoLog.class);
  }

	 @GetMapping(value = "/logs")
	 public Result<?> queryList(RepairMoLog repairMoLog,
								HttpServletRequest req) {
		 QueryWrapper<RepairMoLog> queryWrapper = QueryGenerator.initQueryWrapper(repairMoLog, req.getParameterMap());

		 String srcDocCode = req.getParameter("srcDocCode");
		 if (StringUtils.isNotBlank(srcDocCode)) {
			 queryWrapper.inSql("mo_code", "select mo_code from qfy_andon_repair_mo " +
					 "where src_doc_code = '" + srcDocCode + "'");
			 queryWrapper.orderByDesc("create_time");
			 queryWrapper.orderByDesc("s1");
		 } else {
			 queryWrapper.orderByDesc("create_time");
		 }
		 List<RepairMoLog> list = repairMoLogService.list(queryWrapper);
		 return Result.OK(list);
	 }

}
