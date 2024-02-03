package com.qfy.modules.commons.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qfy.modules.commons.entity.CmnParams;
import com.qfy.modules.commons.service.ICmnParamsService;
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
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

 /**
 * @Description: 系统全局参数
 * @Author: jeecg-boot
 * @Date:   2022-12-08
 * @Version: V1.0
 */
@Api(tags="系统全局参数")
@RestController
@RequestMapping("/common/cmnParams")
@Slf4j
public class CmnParamsController extends JeecgController<CmnParams, ICmnParamsService> {
	@Autowired
	private ICmnParamsService cmnParamsService;

	/**
	 * 分页列表查询
	 *
	 * @param cmnParams
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "系统全局参数-分页列表查询")
	@ApiOperation(value="系统全局参数-分页列表查询", notes="系统全局参数-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<CmnParams>> queryPageList(CmnParams cmnParams,
												  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
												  @RequestParam(name="pageSize", defaultValue="20") Integer pageSize,
												  HttpServletRequest req) {
		QueryWrapper<CmnParams> queryWrapper = QueryGenerator.initQueryWrapper(cmnParams, req.getParameterMap());
		Page<CmnParams> page = new Page<CmnParams>(pageNo, pageSize);
		IPage<CmnParams> pageList = cmnParamsService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	/**
	 *   添加
	 *
	 * @param cmnParams
	 * @return
	 */
	@AutoLog(value = "系统全局参数-添加")
	@ApiOperation(value="系统全局参数-添加", notes="系统全局参数-添加")
	//@RequiresPermissions("org.jeecg.modules.demo:qfy_common_params:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody CmnParams cmnParams) {
		cmnParamsService.save(cmnParams);
		cmnParamsService.reload();
		return Result.OK("添加成功！");
	}

	/**
	 *  编辑
	 *
	 * @param cmnParams
	 * @return
	 */
	@AutoLog(value = "系统全局参数-编辑")
	@ApiOperation(value="系统全局参数-编辑", notes="系统全局参数-编辑")
	//@RequiresPermissions("org.jeecg.modules.demo:qfy_common_params:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody CmnParams cmnParams) {
		cmnParamsService.updateById(cmnParams);
		cmnParamsService.reload();
		return Result.OK("编辑成功!");
	}

	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "系统全局参数-通过id删除")
	@ApiOperation(value="系统全局参数-通过id删除", notes="系统全局参数-通过id删除")
	//@RequiresPermissions("org.jeecg.modules.demo:qfy_common_params:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		cmnParamsService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "系统全局参数-批量删除")
	@ApiOperation(value="系统全局参数-批量删除", notes="系统全局参数-批量删除")
	//@RequiresPermissions("org.jeecg.modules.demo:qfy_common_params:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.cmnParamsService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "系统全局参数-通过id查询")
	@ApiOperation(value="系统全局参数-通过id查询", notes="系统全局参数-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<CmnParams> queryById(@RequestParam(name="id",required=true) String id) {
		CmnParams cmnParams = cmnParamsService.getById(id);
		if(cmnParams==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(cmnParams);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param cmnParams
    */
    //@RequiresPermissions("org.jeecg.modules.demo:qfy_common_params:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, CmnParams cmnParams) {
        return super.exportXls(request, cmnParams, CmnParams.class, "系统全局参数");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    //@RequiresPermissions("qfy_common_params:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, CmnParams.class);
    }

}
