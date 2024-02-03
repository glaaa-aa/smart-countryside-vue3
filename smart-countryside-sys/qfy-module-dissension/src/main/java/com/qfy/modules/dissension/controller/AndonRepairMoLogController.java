package com.qfy.modules.dissension.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qfy.modules.dissension.entity.AndonRepairMoLog;
import com.qfy.modules.dissension.service.IAndonRepairMoLogService;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.query.QueryGenerator;
import lombok.extern.slf4j.Slf4j;
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
 * @Description: 维修工单记录表
 * @Author: jeecg-boot
 * @Date:   2024-01-30
 * @Version: V1.0
 */
@Api(tags="处治工单记录表")
@RestController
@RequestMapping("/dissension/andonRepairMoLog")
@Slf4j
public class AndonRepairMoLogController extends JeecgController<AndonRepairMoLog, IAndonRepairMoLogService> {
	@Autowired
	private IAndonRepairMoLogService andonRepairMoLogService;
	
	/**
	 * 分页列表查询
	 *
	 * @param andonRepairMoLog
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "维修工单记录表-分页列表查询")
	@ApiOperation(value="维修工单记录表-分页列表查询", notes="维修工单记录表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<AndonRepairMoLog>> queryPageList(AndonRepairMoLog andonRepairMoLog,
														 @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
														 @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
														 HttpServletRequest req) {
		QueryWrapper<AndonRepairMoLog> queryWrapper = QueryGenerator.initQueryWrapper(andonRepairMoLog, req.getParameterMap());
		Page<AndonRepairMoLog> page = new Page<AndonRepairMoLog>(pageNo, pageSize);
		IPage<AndonRepairMoLog> pageList = andonRepairMoLogService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param andonRepairMoLog
	 * @return
	 */
	@AutoLog(value = "维修工单记录表-添加")
	@ApiOperation(value="维修工单记录表-添加", notes="维修工单记录表-添加")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody AndonRepairMoLog andonRepairMoLog) {
		andonRepairMoLogService.save(andonRepairMoLog);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param andonRepairMoLog
	 * @return
	 */
	@AutoLog(value = "维修工单记录表-编辑")
	@ApiOperation(value="维修工单记录表-编辑", notes="维修工单记录表-编辑")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody AndonRepairMoLog andonRepairMoLog) {
		andonRepairMoLogService.updateById(andonRepairMoLog);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "维修工单记录表-通过id删除")
	@ApiOperation(value="维修工单记录表-通过id删除", notes="维修工单记录表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		andonRepairMoLogService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "维修工单记录表-批量删除")
	@ApiOperation(value="维修工单记录表-批量删除", notes="维修工单记录表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.andonRepairMoLogService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "维修工单记录表-通过id查询")
	@ApiOperation(value="维修工单记录表-通过id查询", notes="维修工单记录表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<AndonRepairMoLog> queryById(@RequestParam(name="id",required=true) String id) {
		AndonRepairMoLog andonRepairMoLog = andonRepairMoLogService.getById(id);
		if(andonRepairMoLog==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(andonRepairMoLog);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param andonRepairMoLog
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, AndonRepairMoLog andonRepairMoLog) {
        return super.exportXls(request, andonRepairMoLog, AndonRepairMoLog.class, "维修工单记录表");
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
        return super.importExcel(request, response, AndonRepairMoLog.class);
    }

}
