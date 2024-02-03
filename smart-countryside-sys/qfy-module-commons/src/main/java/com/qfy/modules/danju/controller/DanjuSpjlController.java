package com.qfy.modules.danju.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qfy.modules.danju.entity.DanjuSpjl;
import com.qfy.modules.danju.service.IDanjuSpjlService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.query.QueryGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

 /**
 * @Description: 单据审批表
 * @Author: jeecg-boot
 * @Date:   2023-06-09
 * @Version: V1.0
 */
@Api(tags="单据审批表")
@RestController
@RequestMapping("/base/danjuSpjl")
@Slf4j
public class DanjuSpjlController extends JeecgController<DanjuSpjl, IDanjuSpjlService> {
	@Autowired
	private IDanjuSpjlService danjuSpjlService;

	/**
	 * 分页列表查询
	 *
	 * @param danjuSpjl
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "单据审批表-分页列表查询")
	@ApiOperation(value="单据审批表-分页列表查询", notes="单据审批表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<DanjuSpjl>> queryPageList(DanjuSpjl danjuSpjl,
												  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
												  @RequestParam(name="pageSize", defaultValue="20") Integer pageSize,
												  HttpServletRequest req) {
		QueryWrapper<DanjuSpjl> queryWrapper = QueryGenerator.initQueryWrapper(danjuSpjl, req.getParameterMap());
		Page<DanjuSpjl> page = new Page<DanjuSpjl>(pageNo, pageSize);
		IPage<DanjuSpjl> pageList = danjuSpjlService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	/**
	 *   添加
	 *
	 * @param danjuSpjl
	 * @return
	 */
	@AutoLog(value = "单据审批表-添加")
	@ApiOperation(value="单据审批表-添加", notes="单据审批表-添加")
	//@RequiresPermissions("org.jeecg.modules.demo:qfy_base_danju_spjl:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody DanjuSpjl danjuSpjl) {
		danjuSpjlService.save(danjuSpjl);
		return Result.OK("添加成功！");
	}

	/**
	 *  编辑
	 *
	 * @param danjuSpjl
	 * @return
	 */
	@AutoLog(value = "单据审批表-编辑")
	@ApiOperation(value="单据审批表-编辑", notes="单据审批表-编辑")
	//@RequiresPermissions("org.jeecg.modules.demo:qfy_base_danju_spjl:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody DanjuSpjl danjuSpjl) {
		danjuSpjlService.updateById(danjuSpjl);
		return Result.OK("编辑成功!");
	}

	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "单据审批表-通过id删除")
	@ApiOperation(value="单据审批表-通过id删除", notes="单据审批表-通过id删除")
	//@RequiresPermissions("org.jeecg.modules.demo:qfy_base_danju_spjl:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		danjuSpjlService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "单据审批表-批量删除")
	@ApiOperation(value="单据审批表-批量删除", notes="单据审批表-批量删除")
	//@RequiresPermissions("org.jeecg.modules.demo:qfy_base_danju_spjl:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.danjuSpjlService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "单据审批表-通过id查询")
	@ApiOperation(value="单据审批表-通过id查询", notes="单据审批表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<DanjuSpjl> queryById(@RequestParam(name="id",required=true) String id) {
		DanjuSpjl danjuSpjl = danjuSpjlService.getById(id);
		if(danjuSpjl==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(danjuSpjl);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param danjuSpjl
    */
    //@RequiresPermissions("org.jeecg.modules.demo:qfy_base_danju_spjl:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, DanjuSpjl danjuSpjl) {
        return super.exportXls(request, danjuSpjl, DanjuSpjl.class, "单据审批表");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    //@RequiresPermissions("qfy_base_danju_spjl:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, DanjuSpjl.class);
    }

}
