package com.qfy.modules.apppage.page.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qfy.modules.apppage.page.entity.AppPage;
import com.qfy.modules.apppage.page.service.IAppPageService;
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
 * @Description: 移动端网站页面
 * @Author: jeecg-boot
 * @Date:   2024-01-25
 * @Version: V1.0
 */
@Api(tags="移动端网站页面")
@RestController
@RequestMapping("/apppage/page/appPage")
@Slf4j
public class AppPageController extends JeecgController<AppPage, IAppPageService> {
	@Autowired
	private IAppPageService appPageService;
	
	/**
	 * 分页列表查询
	 *
	 * @param appPage
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "移动端网站页面-分页列表查询")
	@ApiOperation(value="移动端网站页面-分页列表查询", notes="移动端网站页面-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<AppPage>> queryPageList(AppPage appPage,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<AppPage> queryWrapper = QueryGenerator.initQueryWrapper(appPage, req.getParameterMap());
		Page<AppPage> page = new Page<AppPage>(pageNo, pageSize);
		IPage<AppPage> pageList = appPageService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param appPage
	 * @return
	 */
	@AutoLog(value = "移动端网站页面-添加")
	@ApiOperation(value="移动端网站页面-添加", notes="移动端网站页面-添加")
//	@RequiresPermissions("apppagepage:qfy_app_page:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody AppPage appPage) {
		appPageService.save(appPage);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param appPage
	 * @return
	 */
	@AutoLog(value = "移动端网站页面-编辑")
	@ApiOperation(value="移动端网站页面-编辑", notes="移动端网站页面-编辑")
//	@RequiresPermissions("apppage.page:qfy_app_page:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody AppPage appPage) {
		appPageService.updateById(appPage);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "移动端网站页面-通过id删除")
	@ApiOperation(value="移动端网站页面-通过id删除", notes="移动端网站页面-通过id删除")
//	@RequiresPermissions("apppage.page:qfy_app_page:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		appPageService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "移动端网站页面-批量删除")
	@ApiOperation(value="移动端网站页面-批量删除", notes="移动端网站页面-批量删除")
//	@RequiresPermissions("apppage.page:qfy_app_page:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.appPageService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "移动端网站页面-通过id查询")
	@ApiOperation(value="移动端网站页面-通过id查询", notes="移动端网站页面-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<AppPage> queryById(@RequestParam(name="id",required=true) String id) {
		AppPage appPage = appPageService.getById(id);
		if(appPage==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(appPage);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param appPage
    */
//    @RequiresPermissions("apppage.page:qfy_app_page:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, AppPage appPage) {
        return super.exportXls(request, appPage, AppPage.class, "移动端网站页面");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
//    @RequiresPermissions("apppage.page:qfy_app_page:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, AppPage.class);
    }

}
