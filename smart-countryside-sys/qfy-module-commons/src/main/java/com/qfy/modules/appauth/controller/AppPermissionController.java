package com.qfy.modules.appauth.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qfy.modules.appauth.entity.AppPermission;
import com.qfy.modules.appauth.model.AppPermissionTree;
import com.qfy.modules.appauth.service.IAppPermissionService;
import com.qfy.modules.appauth.service.IAppRolePermissionService;
import com.qfy.modules.appauth.service.PermissionRedisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.query.QueryGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description: 移动端按钮权限控制
 * @Author: jeecg-boot
 * @Date:   2023-04-14
 * @Version: V1.0
 */
@Api(tags="移动端按钮权限控制")
@RestController
@RequestMapping("/appauth/appPermission")
@Slf4j
public class AppPermissionController extends JeecgController<AppPermission, IAppPermissionService> {
	@Autowired
	private IAppPermissionService appPermissionService;
	@Autowired
	private IAppRolePermissionService rolePermissionService;
	@Autowired
	private PermissionRedisService permissionRedisService;
	@Autowired
	private ThreadPoolTaskExecutor executor;
	/**
	 * 分页列表查询
	 * @param appPermission
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "移动端按钮权限控制-分页列表查询")
	@ApiOperation(value="移动端按钮权限控制-分页列表查询", notes="移动端按钮权限控制-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<AppPermission>> queryPageList(AppPermission appPermission,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="20") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<AppPermission> queryWrapper = QueryGenerator.initQueryWrapper(appPermission, req.getParameterMap());
		Page<AppPermission> page = new Page<AppPermission>(pageNo, pageSize);
		IPage<AppPermission> pageList = appPermissionService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	/**
	 * 查询数据 查出菜单,并以树结构数据格式响应给前端
	 *
	 * @return
	 */
	@RequestMapping(value = "/queryTreeList", method = RequestMethod.GET)
	public Result<List<AppPermissionTree>> queryTreeList(@RequestParam(name = "ids", required = false) String ids,
														 HttpServletRequest request) {
		Result<List<AppPermissionTree>> result = new Result<>();
		try {
			List<AppPermissionTree> list = appPermissionService.queryTreeList();
			result.setResult(list);
			result.setSuccess(true);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return result;
	}

	/**
	 * 系统模板 查询角色授权
	 * @param refData
	 * @param roleId
	 * @return
	 */
	@RequestMapping(value = "/queryAppRolePermission", method = RequestMethod.GET)
	public Result<Map> queryRolePermission(@RequestParam(name = "roleId", required = true) String roleId,
										   @RequestParam(name = "refData", required = true) String refData) {
		Result<Map> result = new Result<>();
		try {
			List<AppPermission> permissionList = appPermissionService.list();
			List allIds = permissionList.stream().map(appPermission -> String.valueOf(appPermission.getId())).collect(Collectors.toList());

			//根据角色和来源查询被授权的菜单
			List selectedIds = rolePermissionService.querySelectedByRef(roleId,refData);
			Map<String,Object> resultMap = new HashMap<>();
			resultMap.put("allPermissionIds",allIds);
			resultMap.put("selectedIds",selectedIds);
			result.setResult(resultMap);
			result.setSuccess(true);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return result;
	}

	/**
	 * 系统模板 保存角色授权
	 *
	 * @return
	 */
	@RequestMapping(value = "/saveRolePermission", method = RequestMethod.POST)
	//@RequiresRoles({ "admin" })
	public Result<String> saveRolePermission(@RequestBody JSONObject json) {
		long start = System.currentTimeMillis();
		Result<String> result = new Result<>();
		try {
			String refData = json.getString("refData");
			String roleId = json.getString("roleId");
			String permissionIds = json.getString("permissionIds");
//			String lastPermissionIds = json.getString("lastpermissionIds");
			this.rolePermissionService.saveRolePermission(roleId, permissionIds, refData);
//			LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
			//重置初始化
			executor.execute(() -> doData());

			result.success("保存成功！");
			log.info("======角色授权成功=====耗时:" + (System.currentTimeMillis() - start) + "毫秒");
		} catch (Exception e) {
			result.error500("授权失败！");
			log.error(e.getMessage(), e);
		}
		return result;
	}

	private void doData(){
		permissionRedisService.initPermissionToRedis();
	}

	/**
	 *   添加
	 * @param appPermission
	 * @return
	 */
	@AutoLog(value = "移动端按钮权限控制-添加")
	@ApiOperation(value="移动端按钮权限控制-添加", notes="移动端按钮权限控制-添加")
	//@RequiresPermissions("org.jeecg.modules.demo:qfy_app_permission:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody AppPermission appPermission) {
		appPermission.setDelFlag(0);
		if(appPermission.getParentId() == null){
			appPermission.setParentId(0);
		}
		appPermissionService.save(appPermission);
		return Result.OK("添加成功！");
	}

	/**
	 *  编辑
	 *
	 * @param appPermission
	 * @return
	 */
	@AutoLog(value = "移动端按钮权限控制-编辑")
	@ApiOperation(value="移动端按钮权限控制-编辑", notes="移动端按钮权限控制-编辑")
	//@RequiresPermissions("org.jeecg.modules.demo:qfy_app_permission:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody AppPermission appPermission) {
		appPermission.setDelFlag(0);
		if(appPermission.getParentId() == null){
			appPermission.setParentId(0);
		}
		if(appPermission.getParentId() != null && appPermission.getParentId() == appPermission.getId()){
			return Result.error("菜单所在父级不能为本身");
		}
		appPermissionService.updateById(appPermission);
		return Result.OK("编辑成功!");
	}

	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "移动端按钮权限控制-通过id删除")
	@ApiOperation(value="移动端按钮权限控制-通过id删除", notes="移动端按钮权限控制-通过id删除")
	//@RequiresPermissions("org.jeecg.modules.demo:qfy_app_permission:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		appPermissionService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "移动端按钮权限控制-批量删除")
	@ApiOperation(value="移动端按钮权限控制-批量删除", notes="移动端按钮权限控制-批量删除")
	//@RequiresPermissions("org.jeecg.modules.demo:qfy_app_permission:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.appPermissionService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

	/**
	 * 通过id查询
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "移动端按钮权限控制-通过id查询")
	@ApiOperation(value="移动端按钮权限控制-通过id查询", notes="移动端按钮权限控制-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<AppPermission> queryById(@RequestParam(name="id",required=true) String id) {
		AppPermission appPermission = appPermissionService.getById(id);
		if(appPermission==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(appPermission);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param appPermission
    */
    //@RequiresPermissions("org.jeecg.modules.demo:qfy_app_permission:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, AppPermission appPermission) {
        return super.exportXls(request, appPermission, AppPermission.class, "移动端按钮权限控制");
    }

    /**
      * 通过excel导入数据
    * @param request
    * @param response
    * @return
    */
    //@RequiresPermissions("qfy_app_permission:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, AppPermission.class);
    }

}
