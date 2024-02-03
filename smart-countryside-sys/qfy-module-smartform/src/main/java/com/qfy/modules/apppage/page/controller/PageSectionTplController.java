package com.qfy.modules.apppage.page.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qfy.modules.apppage.page.entity.PageSectionTpl;
import com.qfy.modules.apppage.page.service.IPageSectionTplService;
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
 * @Description: 页面模块模板表
 * @Author: jeecg-boot
 * @Date:   2024-01-25
 * @Version: V1.0
 */
@Api(tags="页面模块模板表")
@RestController
@RequestMapping("/apppage/page/pageSectionTpl")
@Slf4j
public class PageSectionTplController extends JeecgController<PageSectionTpl, IPageSectionTplService> {
	@Autowired
	private IPageSectionTplService pageSectionTplService;
	
	/**
	 * 分页列表查询
	 *
	 * @param pageSectionTpl
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "页面模块模板表-分页列表查询")
	@ApiOperation(value="页面模块模板表-分页列表查询", notes="页面模块模板表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<PageSectionTpl>> queryPageList(PageSectionTpl pageSectionTpl,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<PageSectionTpl> queryWrapper = QueryGenerator.initQueryWrapper(pageSectionTpl, req.getParameterMap());
		Page<PageSectionTpl> page = new Page<PageSectionTpl>(pageNo, pageSize);
		IPage<PageSectionTpl> pageList = pageSectionTplService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param pageSectionTpl
	 * @return
	 */
	@AutoLog(value = "页面模块模板表-添加")
	@ApiOperation(value="页面模块模板表-添加", notes="页面模块模板表-添加")
//	@RequiresPermissions("apppage.page:qfy_app_page_section_tpl:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody PageSectionTpl pageSectionTpl) {
		pageSectionTplService.save(pageSectionTpl);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param pageSectionTpl
	 * @return
	 */
	@AutoLog(value = "页面模块模板表-编辑")
	@ApiOperation(value="页面模块模板表-编辑", notes="页面模块模板表-编辑")
//	@RequiresPermissions("apppage.page:qfy_app_page_section_tpl:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody PageSectionTpl pageSectionTpl) {
		pageSectionTplService.updateById(pageSectionTpl);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "页面模块模板表-通过id删除")
	@ApiOperation(value="页面模块模板表-通过id删除", notes="页面模块模板表-通过id删除")
//	@RequiresPermissions("apppage.page:qfy_app_page_section_tpl:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		pageSectionTplService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "页面模块模板表-批量删除")
	@ApiOperation(value="页面模块模板表-批量删除", notes="页面模块模板表-批量删除")
//	@RequiresPermissions("apppage.page:qfy_app_page_section_tpl:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.pageSectionTplService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "页面模块模板表-通过id查询")
	@ApiOperation(value="页面模块模板表-通过id查询", notes="页面模块模板表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<PageSectionTpl> queryById(@RequestParam(name="id",required=true) String id) {
		PageSectionTpl pageSectionTpl = pageSectionTplService.getById(id);
		if(pageSectionTpl==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(pageSectionTpl);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param pageSectionTpl
    */
//    @RequiresPermissions("apppage.page:qfy_app_page_section_tpl:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, PageSectionTpl pageSectionTpl) {
        return super.exportXls(request, pageSectionTpl, PageSectionTpl.class, "页面模块模板表");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
//    @RequiresPermissions("apppage.page:qfy_app_page_section_tpl:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, PageSectionTpl.class);
    }

}
