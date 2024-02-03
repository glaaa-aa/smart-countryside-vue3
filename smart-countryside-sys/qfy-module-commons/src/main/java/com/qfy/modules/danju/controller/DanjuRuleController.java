package com.qfy.modules.danju.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qfy.modules.danju.entity.DanjuRule;
import com.qfy.modules.danju.service.IDanjuRuleService;
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
 * @Description: 单据管理表
 * @Author: jeecg-boot
 * @Date:   2023-06-09
 * @Version: V1.0
 */
@Api(tags="单据管理表")
@RestController
@RequestMapping("/base/danjuRule")
@Slf4j
public class DanjuRuleController extends JeecgController<DanjuRule, IDanjuRuleService> {
	@Autowired
	private IDanjuRuleService danjuRuleService;

	/**
	 * 分页列表查询
	 *
	 * @param danjuRule
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "单据管理表-分页列表查询")
	@ApiOperation(value="单据管理表-分页列表查询", notes="单据管理表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<DanjuRule>> queryPageList(DanjuRule danjuRule,
												  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
												  @RequestParam(name="pageSize", defaultValue="20") Integer pageSize,
												  HttpServletRequest req) {
		QueryWrapper<DanjuRule> queryWrapper = QueryGenerator.initQueryWrapper(danjuRule, req.getParameterMap());
		Page<DanjuRule> page = new Page<DanjuRule>(pageNo, pageSize);
		IPage<DanjuRule> pageList = danjuRuleService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	/**
	 *   添加
	 *
	 * @param danjuRule
	 * @return
	 */
	@AutoLog(value = "单据管理表-添加")
	@ApiOperation(value="单据管理表-添加", notes="单据管理表-添加")
	//@RequiresPermissions("org.jeecg.modules.demo:qfy_base_danju_rule:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody DanjuRule danjuRule) {
		danjuRuleService.save(danjuRule);
		return Result.OK("添加成功！");
	}

	/**
	 *  编辑
	 *
	 * @param danjuRule
	 * @return
	 */
	@AutoLog(value = "单据管理表-编辑")
	@ApiOperation(value="单据管理表-编辑", notes="单据管理表-编辑")
	//@RequiresPermissions("org.jeecg.modules.demo:qfy_base_danju_rule:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody DanjuRule danjuRule) {
		danjuRuleService.updateById(danjuRule);
		return Result.OK("编辑成功!");
	}

	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "单据管理表-通过id删除")
	@ApiOperation(value="单据管理表-通过id删除", notes="单据管理表-通过id删除")
	//@RequiresPermissions("org.jeecg.modules.demo:qfy_base_danju_rule:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		danjuRuleService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "单据管理表-批量删除")
	@ApiOperation(value="单据管理表-批量删除", notes="单据管理表-批量删除")
	//@RequiresPermissions("org.jeecg.modules.demo:qfy_base_danju_rule:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.danjuRuleService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "单据管理表-通过id查询")
	@ApiOperation(value="单据管理表-通过id查询", notes="单据管理表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<DanjuRule> queryById(@RequestParam(name="id",required=true) String id) {
		DanjuRule danjuRule = danjuRuleService.getById(id);
		if(danjuRule==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(danjuRule);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param danjuRule
    */
    //@RequiresPermissions("org.jeecg.modules.demo:qfy_base_danju_rule:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, DanjuRule danjuRule) {
        return super.exportXls(request, danjuRule, DanjuRule.class, "单据管理表");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    //@RequiresPermissions("qfy_base_danju_rule:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, DanjuRule.class);
    }

}
