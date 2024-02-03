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
import com.qfy.modules.smartform.entity.CheckTask;
import com.qfy.modules.smartform.service.ICheckTaskService;

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
 * @Description: 巡检任务表
 * @Author: jeecg-boot
 * @Date:   2022-04-08
 * @Version: V1.0
 */
@Api(tags="巡检任务表")
@RestController
@RequestMapping("/smartform/checkTask")
@Slf4j
public class CheckTaskController extends JeecgController<CheckTask, ICheckTaskService> {
	@Autowired
	private ICheckTaskService checkTaskService;
	
	/**
	 * 分页列表查询
	 *
	 * @param checkTask
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "巡检任务表-分页列表查询")
	@ApiOperation(value="巡检任务表-分页列表查询", notes="巡检任务表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(CheckTask checkTask,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<CheckTask> queryWrapper = QueryGenerator.initQueryWrapper(checkTask, req.getParameterMap());
		Page<CheckTask> page = new Page<CheckTask>(pageNo, pageSize);
		IPage<CheckTask> pageList = checkTaskService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param checkTask
	 * @return
	 */
	@AutoLog(value = "巡检任务表-添加")
	@ApiOperation(value="巡检任务表-添加", notes="巡检任务表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody CheckTask checkTask) {
		checkTaskService.save(checkTask);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param checkTask
	 * @return
	 */
	@AutoLog(value = "巡检任务表-编辑")
	@ApiOperation(value="巡检任务表-编辑", notes="巡检任务表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody CheckTask checkTask) {
		checkTaskService.updateById(checkTask);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "巡检任务表-通过id删除")
	@ApiOperation(value="巡检任务表-通过id删除", notes="巡检任务表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		checkTaskService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "巡检任务表-批量删除")
	@ApiOperation(value="巡检任务表-批量删除", notes="巡检任务表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.checkTaskService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "巡检任务表-通过id查询")
	@ApiOperation(value="巡检任务表-通过id查询", notes="巡检任务表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		CheckTask checkTask = checkTaskService.getById(id);
		if(checkTask==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(checkTask);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param checkTask
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, CheckTask checkTask) {
        return super.exportXls(request, checkTask, CheckTask.class, "巡检任务表");
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
        return super.importExcel(request, response, CheckTask.class);
    }

}
