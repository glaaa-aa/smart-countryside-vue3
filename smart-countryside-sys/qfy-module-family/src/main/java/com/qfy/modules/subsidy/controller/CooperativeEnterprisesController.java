package com.qfy.modules.subsidy.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qfy.modules.subsidy.entity.CooperativeEnterprises;
import com.qfy.modules.subsidy.service.ICooperativeEnterprisesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Arrays;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.query.QueryGenerator;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

 /**
 * @Description: 产业帮扶企业
 * @Author: jeecg-boot
 * @Date:   2022-11-16
 * @Version: V1.0
 */
@Api(tags="产业帮扶企业")
@RestController
@RequestMapping("/subsidy/enterprises")
@Slf4j
public class CooperativeEnterprisesController extends JeecgController<CooperativeEnterprises, ICooperativeEnterprisesService> {
	@Resource
	private ICooperativeEnterprisesService enterprisesService;

	/**
	 * 分页列表查询
	 *
	 * @param enterprises
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "产业帮扶企业-分页列表查询")
	@ApiOperation(value="产业帮扶企业-分页列表查询", notes="产业帮扶企业-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<CooperativeEnterprises>> queryPageList(CooperativeEnterprises enterprises,
															   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
															   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
															   HttpServletRequest req) {
		QueryWrapper<CooperativeEnterprises> queryWrapper = QueryGenerator.initQueryWrapper(enterprises, req.getParameterMap());
		Page<CooperativeEnterprises> page = new Page<CooperativeEnterprises>(pageNo, pageSize);
		IPage<CooperativeEnterprises> pageList = enterprisesService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	/**
	 *   添加
	 *
	 * @param enterprises
	 * @return
	 */
	@AutoLog(value = "产业帮扶企业-添加")
	@ApiOperation(value="产业帮扶企业-添加", notes="产业帮扶企业-添加")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody CooperativeEnterprises enterprises) {
		enterprisesService.save(enterprises);
		return Result.OK("添加成功！");
	}

	/**
	 *  编辑
	 *
	 * @param enterprises
	 * @return
	 */
	@AutoLog(value = "产业帮扶企业-编辑")
	@ApiOperation(value="产业帮扶企业-编辑", notes="产业帮扶企业-编辑")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody CooperativeEnterprises enterprises) {
		enterprisesService.updateById(enterprises);
		return Result.OK("编辑成功!");
	}

	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "产业帮扶企业-通过id删除")
	@ApiOperation(value="产业帮扶企业-通过id删除", notes="产业帮扶企业-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		enterprisesService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "产业帮扶企业-批量删除")
	@ApiOperation(value="产业帮扶企业-批量删除", notes="产业帮扶企业-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.enterprisesService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "产业帮扶企业-通过id查询")
	@ApiOperation(value="产业帮扶企业-通过id查询", notes="产业帮扶企业-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<CooperativeEnterprises> queryById(@RequestParam(name="id",required=true) String id) {
		CooperativeEnterprises cooperativeEnterprises = enterprisesService.getById(id);
		if(cooperativeEnterprises==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(cooperativeEnterprises);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param cooperativeEnterprises
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, CooperativeEnterprises cooperativeEnterprises) {
        return super.exportXls(request, cooperativeEnterprises, CooperativeEnterprises.class, "产业帮扶企业");
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
        return super.importExcel(request, response, CooperativeEnterprises.class);
    }

}
