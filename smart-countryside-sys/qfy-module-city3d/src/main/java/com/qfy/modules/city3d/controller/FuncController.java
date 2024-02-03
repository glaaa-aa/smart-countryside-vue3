package com.qfy.modules.city3d.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qfy.modules.city3d.po.SysFunc;
import com.qfy.modules.city3d.po.SysRole;
import com.qfy.modules.city3d.service.FuncService;
import com.qfy.modules.city3d.service.SysRoleFuncService;
import com.qfy.modules.city3d.util.CharacterUtils;
import com.qfy.modules.commons.model.TreeSelectModel;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.constant.CommonConstant;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.modules.base.service.BaseCommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/func")
public class FuncController extends JeecgController<SysFunc, FuncService> {

	public static final Logger logger = LoggerFactory.getLogger(FuncController.class);

	@Resource
	private FuncService funcService;

	@Resource
	private SysRoleFuncService roleFuncService;

	@Resource
	private BaseCommonService baseCommonService;

	@RequestMapping("/getFunc")
	public @ResponseBody List<SysFunc> getFunc() throws Exception {
		List<SysFunc> result = funcService.getFunc();
		return result;
	}

	@RequestMapping("/insert")
	public @ResponseBody int insert(@RequestBody SysFunc sysFunc) throws Exception{
		CharacterUtils characterUtils = new CharacterUtils();
		String uid = characterUtils.getUUID();
		sysFunc.setFuncId(uid);
		int result = funcService.insert(sysFunc);
		return result;
	}

	@RequestMapping("/update")
	public @ResponseBody int updateByPrimaryKey(@RequestBody SysFunc sysFunc) throws Exception{
		int result = funcService.updateByPrimaryKey(sysFunc);
		return result;
	}

	@RequestMapping("/delete")
	public @ResponseBody int deleteByPrimaryKey(@RequestBody SysFunc sysFunc) throws Exception{
		int result = funcService.deleteByPrimaryKey(sysFunc.getFuncId());
		return result;
	}

	@RequestMapping("/getFuncByLevel")
	public @ResponseBody List<SysFunc> getFuncByLevel(@RequestBody SysFunc sysFunc) throws Exception {
		List<SysFunc> result = funcService.getFuncByLevel(sysFunc);
		return result;
	}

	@RequestMapping("/getFuncAndRel")
	public @ResponseBody List<SysFunc> getFuncAndRel(@RequestBody SysRole sysRole) throws Exception {
		List<SysFunc> result = funcService.getFuncAndRel(sysRole.getRoleId());
		return result;
	}

	@RequestMapping("/getFuncBySrc")
	public @ResponseBody List<SysFunc> getFuncBySrc(@RequestBody SysFunc sysFunc) throws Exception {
		List<SysFunc> result = funcService.getFuncBySrc(sysFunc.getFuncSrc());
		return result;
	}

	/**
	 * 分页列表查询
	 *
	 * @param sysFunc
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "地图应用菜单权限-分页列表查询")
	@ApiOperation(value="地图应用菜单权限-分页列表查询", notes="地图应用菜单权限-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<SysFunc>> queryPageList(SysFunc sysFunc,
													 @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
													 @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
													 HttpServletRequest req) {
		QueryWrapper<SysFunc> queryWrapper = QueryGenerator.initQueryWrapper(sysFunc, req.getParameterMap());
		Page<SysFunc> page = new Page<SysFunc>(pageNo, pageSize);
		IPage<SysFunc> pageList = service.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	/**
	 *   添加
	 *
	 * @param sysFunc
	 * @return
	 */
	@AutoLog(value = "地图应用菜单权限-添加")
	@ApiOperation(value="地图应用菜单权限-添加", notes="地图应用菜单权限-添加")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody SysFunc sysFunc) {
		service.save(sysFunc);
		return Result.OK("添加成功！");
	}

	/**
	 *  编辑
	 *
	 * @param sysFunc
	 * @return
	 */
	@AutoLog(value = "地图应用菜单权限-编辑")
	@ApiOperation(value="地图应用菜单权限-编辑", notes="地图应用菜单权限-编辑")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody SysFunc sysFunc) {
		service.updateById(sysFunc);
		return Result.OK("编辑成功!");
	}

	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "地图应用菜单权限-通过id删除")
	@ApiOperation(value="地图应用菜单权限-通过id删除", notes="地图应用菜单权限-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		service.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "地图应用菜单权限-批量删除")
	@ApiOperation(value="地图应用菜单权限-批量删除", notes="地图应用菜单权限-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.service.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "地图应用菜单权限-通过id查询")
	@ApiOperation(value="地图应用菜单权限-通过id查询", notes="地图应用菜单权限-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<SysFunc> queryById(@RequestParam(name="id",required=true) String id) {
		SysFunc sysFunc = service.getById(id);
		if(sysFunc==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(sysFunc);
	}

	/**
	 * 导出excel
	 *
	 * @param request
	 * @param sysFunc
	 */
	@RequestMapping(value = "/exportXls")
	public ModelAndView exportXls(HttpServletRequest request, SysFunc sysFunc) {
		return super.exportXls(request, sysFunc, SysFunc.class, "地图应用菜单权限");
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
		return super.importExcel(request, response, SysFunc.class);
	}

	/**
	 * 用户角色授权功能，查询数据中台及应用前台菜单权限树
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryTreeList", method = RequestMethod.GET)
	public Result<Map<String,Object>> queryTreeList(HttpServletRequest request) {
		Result<Map<String,Object>> result = new Result<>();

		String from = request.getParameter("from");
		try {
			LambdaQueryWrapper<SysFunc> query = new LambdaQueryWrapper<SysFunc>();
			query.eq(SysFunc::getDelFlag, CommonConstant.DEL_FLAG_0);
			query.eq(SysFunc::getFuncSrc, from.equals("1") ? "应用前台" : "数据中台");
			query.orderByAsc(SysFunc::getSort);
			List<SysFunc> list = service.list(query);
			//全部权限ids
			List<String> ids = list.stream().map(SysFunc::getFuncId).collect(Collectors.toList());
			List<TreeSelectModel> treeList = new ArrayList<>();
			getTreeModelList(treeList, list, "0");
			Map<String,Object> resMap = new HashMap(5);
			//全部树节点数据
			resMap.put("treeList", treeList);
			//全部树ids
			resMap.put("ids", ids);
			result.setResult(resMap);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return result;
	}

	/**
	 * 查询角色授权-菜单id列表
	 *
	 * @return
	 */
	@RequestMapping(value = "/queryRoleFunc", method = RequestMethod.GET)
	public Result<List<String>> queryRoleFunc(@RequestParam(name = "roleId") String roleId,
											  @RequestParam(name = "from") String from) {
		Result<List<String>> result = new Result<>();
		try {
			List<SysFunc> list = service.queryFuncByRoleId(roleId,from.equals("1") ? "应用前台" : "数据中台");
			result.setResult(list.stream().map(SysFunc::getFuncId).collect(Collectors.toList()));
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("获取菜单权限列表异常：", e);
		}
		return result;
	}

	/**
	 * 查询所有前台应用和数据中台的菜单列表
	 *
	 * @return
	 */
	@GetMapping(value = "/getAllFuncs")
	public Result getAllFuncs() {
		Result result = new Result<>();
		try {
			List<SysFunc> list = service.list((new QueryWrapper<SysFunc>()).lambda().orderByDesc(SysFunc::getFuncSrc));
			List<SysFunc> funcList = new ArrayList<>();
			formatTree(funcList, list, "0");
			result.setResult(funcList);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("获取菜单权限列表异常：", e);
		}
		return result;
	}

	/**
	 * 查询角色授权列表
	 *
	 * @return
	 */
	@RequestMapping(value = "/getFuncsByRoleId", method = RequestMethod.GET)
	public Result getFuncsByRoleId(@RequestParam(name = "roleId") String roleId,
											  @RequestParam(name = "from", required = false) String from) {
		Result result = new Result<>();
		try {
			if (StringUtils.isNotBlank(from)){
				from = from.equals("1") ? "应用前台" : "数据中台";
			}
			List<SysFunc> list = service.queryFuncByRoleId(roleId,from);
			List<SysFunc> funcList = new ArrayList<>();
			formatTree(funcList, list, "0");
			result.setResult(funcList);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("获取菜单权限列表异常：", e);
		}
		return result;
	}

	private void formatTree(List<SysFunc> funcList, List<SysFunc> metaList, String pid){
		for (SysFunc sysFunc : metaList) {
			if (StringUtils.equals(sysFunc.getFuncPid(), pid)){
				List<SysFunc> children = new ArrayList<>();
				sysFunc.setChildren(children);
				funcList.add(sysFunc);
				formatTree(children, metaList, sysFunc.getFuncId());
			}
		}

	}

	/**
	 * 保存角色授权
	 *
	 * @return
	 */
	@RequestMapping(value = "/saveRoleFunc", method = RequestMethod.POST)
	//@RequiresRoles({ "admin" })
	public Result<String> saveRoleFunc(@RequestBody JSONObject json) {
		long start = System.currentTimeMillis();
		Result<String> result = new Result<>();
		try {
			String roleId = json.getString("roleId");
			String permissionIds = json.getString("permissionIds");
			String lastPermissionIds = json.getString("lastpermissionIds");
			this.roleFuncService.saveRolePermission(roleId, permissionIds, lastPermissionIds);
			//update-begin---author:wangshuai ---date:20220316  for：[VUEN-234]用户管理角色授权添加敏感日志------------
			LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
			baseCommonService.addLog("修改角色ID: "+roleId+" 的权限配置，操作人： " +loginUser.getUsername() , CommonConstant.LOG_TYPE_2, 2);
			result.success("保存成功！");
			logger.info("======角色授权成功=====耗时:" + (System.currentTimeMillis() - start) + "毫秒");
		} catch (Exception e) {
			result.error500("授权失败！");
			logger.error("授权失败：", e);
		}
		return result;
	}

	private void getTreeModelList(List<TreeSelectModel> treeList, List<SysFunc> metaList, String pid) {
		for (SysFunc func : metaList) {
			if (StringUtils.equals(func.getFuncPid(), pid)){
				boolean isLeaf = pid.equals("0");
				TreeSelectModel tree = new TreeSelectModel(func.getFuncId(), pid, func.getFuncDefaultName(),0,!isLeaf);
				treeList.add(tree);
				getTreeModelList(tree.getChildren(), metaList, tree.getKey());
			}
		}
	}
}
