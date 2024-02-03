package com.qfy.modules.appauth.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qfy.modules.appauth.entity.AppRole;
import com.qfy.modules.appauth.service.IAppRoleService;
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
 * @Description: 角色管理
 * @Author: jeecg-boot
 * @Date:   2023-04-14
 * @Version: V1.0
 */
@Api(tags="角色管理")
@RestController
@RequestMapping("/appauth/appRole")
@Slf4j
public class AppRoleController extends JeecgController<AppRole, IAppRoleService> {
	@Autowired
	private IAppRoleService appRoleService;

	/**
	 * 分页列表查询
	 *
	 * @param appRole
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "角色管理-分页列表查询")
	@ApiOperation(value="角色管理-分页列表查询", notes="角色管理-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<AppRole>> queryPageList(AppRole appRole,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="20") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<AppRole> queryWrapper = QueryGenerator.initQueryWrapper(appRole, req.getParameterMap());
		Page<AppRole> page = new Page<AppRole>(pageNo, pageSize);
		IPage<AppRole> pageList = appRoleService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	/**
	 *   添加
	 *
	 * @param appRole
	 * @return
	 */
	@AutoLog(value = "角色管理-添加")
	@ApiOperation(value="角色管理-添加", notes="角色管理-添加")
	//@RequiresPermissions("org.jeecg.modules.demo:qfy_app_role:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody AppRole appRole) {
		appRoleService.save(appRole);
		return Result.OK("添加成功！");
	}

	/**
	 *  编辑
	 *
	 * @param appRole
	 * @return
	 */
	@AutoLog(value = "角色管理-编辑")
	@ApiOperation(value="角色管理-编辑", notes="角色管理-编辑")
	//@RequiresPermissions("org.jeecg.modules.demo:qfy_app_role:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody AppRole appRole) {
		appRoleService.updateById(appRole);
		return Result.OK("编辑成功!");
	}

	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "角色管理-通过id删除")
	@ApiOperation(value="角色管理-通过id删除", notes="角色管理-通过id删除")
	//@RequiresPermissions("org.jeecg.modules.demo:qfy_app_role:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		appRoleService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "角色管理-批量删除")
	@ApiOperation(value="角色管理-批量删除", notes="角色管理-批量删除")
	//@RequiresPermissions("org.jeecg.modules.demo:qfy_app_role:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.appRoleService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "角色管理-通过id查询")
	@ApiOperation(value="角色管理-通过id查询", notes="角色管理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<AppRole> queryById(@RequestParam(name="id",required=true) String id) {
		AppRole appRole = appRoleService.getById(id);
		if(appRole==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(appRole);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param appRole
    */
    //@RequiresPermissions("org.jeecg.modules.demo:qfy_app_role:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, AppRole appRole) {
        return super.exportXls(request, appRole, AppRole.class, "角色管理");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    //@RequiresPermissions("qfy_app_role:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, AppRole.class);
    }

}
