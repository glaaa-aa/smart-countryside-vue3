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
import com.qfy.modules.smartform.entity.CheckPointGroup;
import com.qfy.modules.smartform.service.ICheckPointGroupService;

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
 * @Description: 巡检点分组（检查点组）
 * @Author: jeecg-boot
 * @Date:   2022-04-08
 * @Version: V1.0
 */
@Api(tags="巡检点分组（检查点组）")
@RestController
@RequestMapping("/smartform/checkPointGroup")
@Slf4j
public class CheckPointGroupController extends JeecgController<CheckPointGroup, ICheckPointGroupService> {
	@Autowired
	private ICheckPointGroupService checkPointGroupService;
	
	/**
	 * 分页列表查询
	 *
	 * @param checkPointGroup
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "巡检点分组（检查点组）-分页列表查询")
	@ApiOperation(value="巡检点分组（检查点组）-分页列表查询", notes="巡检点分组（检查点组）-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(CheckPointGroup checkPointGroup,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<CheckPointGroup> queryWrapper = QueryGenerator.initQueryWrapper(checkPointGroup, req.getParameterMap());
		Page<CheckPointGroup> page = new Page<CheckPointGroup>(pageNo, pageSize);
		IPage<CheckPointGroup> pageList = checkPointGroupService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param checkPointGroup
	 * @return
	 */
	@AutoLog(value = "巡检点分组（检查点组）-添加")
	@ApiOperation(value="巡检点分组（检查点组）-添加", notes="巡检点分组（检查点组）-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody CheckPointGroup checkPointGroup) {
		checkPointGroupService.save(checkPointGroup);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param checkPointGroup
	 * @return
	 */
	@AutoLog(value = "巡检点分组（检查点组）-编辑")
	@ApiOperation(value="巡检点分组（检查点组）-编辑", notes="巡检点分组（检查点组）-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody CheckPointGroup checkPointGroup) {
		checkPointGroupService.updateById(checkPointGroup);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "巡检点分组（检查点组）-通过id删除")
	@ApiOperation(value="巡检点分组（检查点组）-通过id删除", notes="巡检点分组（检查点组）-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		checkPointGroupService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "巡检点分组（检查点组）-批量删除")
	@ApiOperation(value="巡检点分组（检查点组）-批量删除", notes="巡检点分组（检查点组）-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.checkPointGroupService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "巡检点分组（检查点组）-通过id查询")
	@ApiOperation(value="巡检点分组（检查点组）-通过id查询", notes="巡检点分组（检查点组）-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		CheckPointGroup checkPointGroup = checkPointGroupService.getById(id);
		if(checkPointGroup==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(checkPointGroup);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param checkPointGroup
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, CheckPointGroup checkPointGroup) {
        return super.exportXls(request, checkPointGroup, CheckPointGroup.class, "巡检点分组（检查点组）");
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
        return super.importExcel(request, response, CheckPointGroup.class);
    }

}
