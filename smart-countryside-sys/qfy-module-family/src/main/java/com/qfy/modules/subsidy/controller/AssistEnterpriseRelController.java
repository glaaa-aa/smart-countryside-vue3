package com.qfy.modules.subsidy.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import com.qfy.modules.subsidy.entity.AssistEnterpriseRel;
import com.qfy.modules.subsidy.service.IAssistEnterpriseRelService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: 企业帮扶关系
 * @Author: jeecg-boot
 * @Date:   2022-12-05
 * @Version: V1.0
 */
@Api(tags="企业帮扶关系")
@RestController
@RequestMapping("/family/assistEnterpriseRel")
@Slf4j
public class AssistEnterpriseRelController extends JeecgController<AssistEnterpriseRel, IAssistEnterpriseRelService> {
	@Autowired
	private IAssistEnterpriseRelService assistEnterpriseRelService;

	/**
	 * 分页列表查询
	 *
	 * @param assistEnterpriseRel
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "企业帮扶关系-分页列表查询")
	@ApiOperation(value="企业帮扶关系-分页列表查询", notes="企业帮扶关系-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<AssistEnterpriseRel>> queryPageList(AssistEnterpriseRel assistEnterpriseRel,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<AssistEnterpriseRel> queryWrapper = QueryGenerator.initQueryWrapper(assistEnterpriseRel, req.getParameterMap());
		Page<AssistEnterpriseRel> page = new Page<AssistEnterpriseRel>(pageNo, pageSize);
		IPage<AssistEnterpriseRel> pageList = assistEnterpriseRelService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	/**
	 *   添加
	 *
	 * @param assistEnterpriseRel
	 * @return
	 */
	@AutoLog(value = "企业帮扶关系-添加")
	@ApiOperation(value="企业帮扶关系-添加", notes="企业帮扶关系-添加")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody AssistEnterpriseRel assistEnterpriseRel) {
		assistEnterpriseRelService.save(assistEnterpriseRel);
		return Result.OK("添加成功！");
	}

	/**
	 *  编辑
	 *
	 * @param assistEnterpriseRel
	 * @return
	 */
	@AutoLog(value = "企业帮扶关系-编辑")
	@ApiOperation(value="企业帮扶关系-编辑", notes="企业帮扶关系-编辑")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody AssistEnterpriseRel assistEnterpriseRel) {
		assistEnterpriseRelService.updateById(assistEnterpriseRel);
		return Result.OK("编辑成功!");
	}

	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "企业帮扶关系-通过id删除")
	@ApiOperation(value="企业帮扶关系-通过id删除", notes="企业帮扶关系-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		assistEnterpriseRelService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "企业帮扶关系-批量删除")
	@ApiOperation(value="企业帮扶关系-批量删除", notes="企业帮扶关系-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.assistEnterpriseRelService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "企业帮扶关系-通过id查询")
	@ApiOperation(value="企业帮扶关系-通过id查询", notes="企业帮扶关系-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<AssistEnterpriseRel> queryById(@RequestParam(name="id",required=true) String id) {
		AssistEnterpriseRel assistEnterpriseRel = assistEnterpriseRelService.getById(id);
		if(assistEnterpriseRel==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(assistEnterpriseRel);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param assistEnterpriseRel
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, AssistEnterpriseRel assistEnterpriseRel) {
        return super.exportXls(request, assistEnterpriseRel, AssistEnterpriseRel.class, "企业帮扶关系");
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
        return super.importExcel(request, response, AssistEnterpriseRel.class);
    }

}
