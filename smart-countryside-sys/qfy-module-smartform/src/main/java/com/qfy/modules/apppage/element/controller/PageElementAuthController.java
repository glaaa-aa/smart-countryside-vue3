package com.qfy.modules.apppage.element.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qfy.modules.apppage.element.entity.PageElementAuth;
import com.qfy.modules.apppage.element.service.IPageElementAuthService;
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
 * @Description: 元素权限管理
 * @Author: jeecg-boot
 * @Date:   2024-01-25
 * @Version: V1.0
 */
@Api(tags="元素权限管理")
@RestController
@RequestMapping("/apppage/element/pageElementAuth")
@Slf4j
public class PageElementAuthController extends JeecgController<PageElementAuth, IPageElementAuthService> {
	@Autowired
	private IPageElementAuthService pageElementAuthService;
	
	/**
	 * 分页列表查询
	 *
	 * @param pageElementAuth
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "元素权限管理-分页列表查询")
	@ApiOperation(value="元素权限管理-分页列表查询", notes="元素权限管理-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<PageElementAuth>> queryPageList(PageElementAuth pageElementAuth,
														@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
														@RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
														HttpServletRequest req) {
		QueryWrapper<PageElementAuth> queryWrapper = QueryGenerator.initQueryWrapper(pageElementAuth, req.getParameterMap());
		Page<PageElementAuth> page = new Page<PageElementAuth>(pageNo, pageSize);
		IPage<PageElementAuth> pageList = pageElementAuthService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param pageElementAuth
	 * @return
	 */
	@AutoLog(value = "元素权限管理-添加")
	@ApiOperation(value="元素权限管理-添加", notes="元素权限管理-添加")
//	@RequiresPermissions("apppage.element:qfy_app_page_element_auth:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody PageElementAuth pageElementAuth) {
		pageElementAuthService.save(pageElementAuth);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param pageElementAuth
	 * @return
	 */
	@AutoLog(value = "元素权限管理-编辑")
	@ApiOperation(value="元素权限管理-编辑", notes="元素权限管理-编辑")
//	@RequiresPermissions("apppage.element:qfy_app_page_element_auth:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody PageElementAuth pageElementAuth) {
		pageElementAuthService.updateById(pageElementAuth);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "元素权限管理-通过id删除")
	@ApiOperation(value="元素权限管理-通过id删除", notes="元素权限管理-通过id删除")
//	@RequiresPermissions("apppage.element:qfy_app_page_element_auth:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		pageElementAuthService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "元素权限管理-批量删除")
	@ApiOperation(value="元素权限管理-批量删除", notes="元素权限管理-批量删除")
//	@RequiresPermissions("apppage.element:qfy_app_page_element_auth:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.pageElementAuthService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "元素权限管理-通过id查询")
	@ApiOperation(value="元素权限管理-通过id查询", notes="元素权限管理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<PageElementAuth> queryById(@RequestParam(name="id",required=true) String id) {
		PageElementAuth pageElementAuth = pageElementAuthService.getById(id);
		if(pageElementAuth==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(pageElementAuth);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param pageElementAuth
    */
//    @RequiresPermissions("apppage.element:qfy_app_page_element_auth:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, PageElementAuth pageElementAuth) {
        return super.exportXls(request, pageElementAuth, PageElementAuth.class, "元素权限管理");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
//    @RequiresPermissions("apppage.element:qfy_app_page_element_auth:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, PageElementAuth.class);
    }

}
