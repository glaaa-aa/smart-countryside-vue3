package com.qfy.modules.subsidy.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qfy.modules.subsidy.entity.SubsidyCombined;
import com.qfy.modules.subsidy.service.ISubsidyCombinedService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
 * @Description: 综合保障扶持信息
 * @Author: jeecg-boot
 * @Date:   2022-07-01
 * @Version: V1.0
 */
@Api(tags="综合保障扶持信息")
@RestController
@RequestMapping("/subsidy/subsidyCombined")
@Slf4j
public class SubsidyCombinedController extends JeecgController<SubsidyCombined, ISubsidyCombinedService> {
	@Autowired
	private ISubsidyCombinedService subsidyCombinedService;

	/**
	 * 分页列表查询
	 *
	 * @param subsidyCombined
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "综合保障扶持信息-分页列表查询")
	@ApiOperation(value="综合保障扶持信息-分页列表查询", notes="综合保障扶持信息-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<SubsidyCombined>> queryPageList(SubsidyCombined subsidyCombined,
														@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
														@RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
														HttpServletRequest req) {
		QueryWrapper<SubsidyCombined> queryWrapper = QueryGenerator.initQueryWrapper(subsidyCombined, req.getParameterMap());
		Page<SubsidyCombined> page = new Page<SubsidyCombined>(pageNo, pageSize);
		IPage<SubsidyCombined> pageList = subsidyCombinedService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	/**
	 *   添加
	 *
	 * @param subsidyCombined
	 * @return
	 */
	@AutoLog(value = "综合保障扶持信息-添加")
	@ApiOperation(value="综合保障扶持信息-添加", notes="综合保障扶持信息-添加")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody SubsidyCombined subsidyCombined) {
		subsidyCombinedService.save(subsidyCombined);
		return Result.OK("添加成功！");
	}

	/**
	 *  编辑
	 *
	 * @param subsidyCombined
	 * @return
	 */
	@AutoLog(value = "综合保障扶持信息-编辑")
	@ApiOperation(value="综合保障扶持信息-编辑", notes="综合保障扶持信息-编辑")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody SubsidyCombined subsidyCombined) {
		subsidyCombinedService.updateById(subsidyCombined);
		return Result.OK("编辑成功!");
	}

	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "综合保障扶持信息-通过id删除")
	@ApiOperation(value="综合保障扶持信息-通过id删除", notes="综合保障扶持信息-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		subsidyCombinedService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "综合保障扶持信息-批量删除")
	@ApiOperation(value="综合保障扶持信息-批量删除", notes="综合保障扶持信息-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.subsidyCombinedService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "综合保障扶持信息-通过id查询")
	@ApiOperation(value="综合保障扶持信息-通过id查询", notes="综合保障扶持信息-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<SubsidyCombined> queryById(@RequestParam(name="id",required=true) String id) {
		SubsidyCombined subsidyCombined = subsidyCombinedService.getById(id);
		if(subsidyCombined==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(subsidyCombined);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param subsidyCombined
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, SubsidyCombined subsidyCombined) {
        return super.exportXls(request, subsidyCombined, SubsidyCombined.class, "综合保障扶持信息");
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
        return super.importExcel(request, response, SubsidyCombined.class);
    }

}
