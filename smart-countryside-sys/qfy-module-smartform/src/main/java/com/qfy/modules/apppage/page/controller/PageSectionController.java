package com.qfy.modules.apppage.page.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qfy.modules.apppage.page.entity.PageSection;
import com.qfy.modules.apppage.page.service.IPageSectionService;
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
 * @Description: 页面模块管理
 * @Author: jeecg-boot
 * @Date:   2024-01-25
 * @Version: V1.0
 */
@Api(tags="页面模块管理")
@RestController
@RequestMapping("/apppage/page/pageSection")
@Slf4j
public class PageSectionController extends JeecgController<PageSection, IPageSectionService> {
	@Autowired
	private IPageSectionService pageSectionService;
	
	/**
	 * 分页列表查询
	 *
	 * @param pageSection
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "页面模块管理-分页列表查询")
	@ApiOperation(value="页面模块管理-分页列表查询", notes="页面模块管理-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<PageSection>> queryPageList(PageSection pageSection,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<PageSection> queryWrapper = QueryGenerator.initQueryWrapper(pageSection, req.getParameterMap());
		Page<PageSection> page = new Page<PageSection>(pageNo, pageSize);
		IPage<PageSection> pageList = pageSectionService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param pageSection
	 * @return
	 */
	@AutoLog(value = "页面模块管理-添加")
	@ApiOperation(value="页面模块管理-添加", notes="页面模块管理-添加")
//	@RequiresPermissions("apppage.page:qfy_app_page_section:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody PageSection pageSection) {
		pageSectionService.save(pageSection);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param pageSection
	 * @return
	 */
	@AutoLog(value = "页面模块管理-编辑")
	@ApiOperation(value="页面模块管理-编辑", notes="页面模块管理-编辑")
//	@RequiresPermissions("apppage.page:qfy_app_page_section:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody PageSection pageSection) {
		pageSectionService.updateById(pageSection);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "页面模块管理-通过id删除")
	@ApiOperation(value="页面模块管理-通过id删除", notes="页面模块管理-通过id删除")
//	@RequiresPermissions("apppage.page:qfy_app_page_section:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		pageSectionService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "页面模块管理-批量删除")
	@ApiOperation(value="页面模块管理-批量删除", notes="页面模块管理-批量删除")
//	@RequiresPermissions("apppage.page:qfy_app_page_section:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.pageSectionService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "页面模块管理-通过id查询")
	@ApiOperation(value="页面模块管理-通过id查询", notes="页面模块管理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<PageSection> queryById(@RequestParam(name="id",required=true) String id) {
		PageSection pageSection = pageSectionService.getById(id);
		if(pageSection==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(pageSection);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param pageSection
    */
//    @RequiresPermissions("apppage.page:qfy_app_page_section:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, PageSection pageSection) {
        return super.exportXls(request, pageSection, PageSection.class, "页面模块管理");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
//    @RequiresPermissions("apppage.page:qfy_app_page_section:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, PageSection.class);
    }

}
