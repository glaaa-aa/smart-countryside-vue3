package com.qfy.modules.city3d.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.qfy.modules.city3d.po.Bim;
import com.qfy.modules.city3d.po.SysRole;
import com.qfy.modules.city3d.po.SysRoleBim;
import com.qfy.modules.city3d.po.SysRoleFunc;
import com.qfy.modules.city3d.po.SysRoleTiles3d;
import com.qfy.modules.city3d.po.Tiles3d;
import com.qfy.modules.city3d.service.BimService;
import com.qfy.modules.city3d.service.RoleService;
import com.qfy.modules.city3d.service.SysRoleBimService;
import com.qfy.modules.city3d.service.SysRoleFuncService;
import com.qfy.modules.city3d.service.SysRoleTiles3dService;
import com.qfy.modules.city3d.service.Tiles3DService;
import com.qfy.modules.city3d.util.CharacterUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/role")
public class RoleController extends CrossOriginController {
	@Autowired
	private RoleService roleService;

	@Autowired
	private SysRoleFuncService sysRoleFuncService;

	@Autowired
	private SysRoleTiles3dService sysRoleTiles3dService;

	@Autowired
	private SysRoleBimService sysRoleBimService;

	@Resource
	private Tiles3DService tiles3DService;

	@Resource
	private BimService bimService;

	@RequestMapping("/getRole")
	public @ResponseBody List<SysRole> getRole() throws Exception {
		List<SysRole> result = roleService.getRole();
		return result;
	}

	@RequestMapping("/insert")
	public @ResponseBody int insert(@RequestBody SysRole sysRole) throws Exception {
		CharacterUtils characterUtils = new CharacterUtils();
		String roleId = characterUtils.getUUID();
		sysRole.setRoleId(roleId);
		int result = roleService.insert(sysRole);
		if (CollectionUtils.isNotEmpty(sysRole.getSysRoleFunc())){
			List<SysRoleFunc> funcList = new ArrayList<>();
			for(SysRoleFunc sysRoleFunc: sysRole.getSysRoleFunc()) {
				if (StringUtils.isBlank(sysRoleFunc.getFuncId())){
					continue;
				}
				sysRoleFunc.setRoleId(roleId);
				funcList.add(sysRoleFunc);
			}
			sysRoleFuncService.saveBatch(funcList);
		}

		if (CollectionUtils.isNotEmpty(sysRole.getSysRoleTiles3d())){
			List<SysRoleTiles3d> tiles3dList = new ArrayList<>();
			for(SysRoleTiles3d sysRoleTiles3d: sysRole.getSysRoleTiles3d()) {
				if (StringUtils.isBlank(sysRoleTiles3d.getTilesId())){
					continue;
				}
				sysRoleTiles3d.setRoleId(roleId);
				tiles3dList.add(sysRoleTiles3d);
			}
			sysRoleTiles3dService.saveBatch(tiles3dList);
		}

		if (CollectionUtils.isNotEmpty(sysRole.getSysRoleBim())){
			List<SysRoleBim> roleBimList = new ArrayList<>();
			for(SysRoleBim sysRoleBim: sysRole.getSysRoleBim()) {
				if (StringUtils.isBlank(sysRoleBim.getTilesId())){
					continue;
				}
				sysRoleBim.setRoleId(roleId);
				roleBimList.add(sysRoleBim);
			}
			sysRoleBimService.saveBatch(roleBimList);
		}
		return result;
	}

	@RequestMapping("/update")
	public @ResponseBody int updateByPrimaryKey(@RequestBody SysRole sysRole) throws Exception {
		String roleId = sysRole.getRoleId();
		UpdateWrapper wrapper = new UpdateWrapper();
		wrapper.eq("role_id", roleId);
		sysRoleFuncService.remove(wrapper);
		sysRoleTiles3dService.remove(wrapper);
		sysRoleBimService.remove(wrapper);
		int result = 1;//roleService.updateByPrimaryKey(sysRole); // 不更新角色
		if (CollectionUtils.isNotEmpty(sysRole.getSysRoleFunc())){
			List<SysRoleFunc> funcList = new ArrayList<>();
			for(SysRoleFunc sysRoleFunc: sysRole.getSysRoleFunc()) {
				if (StringUtils.isBlank(sysRoleFunc.getFuncId())){
					continue;
				}
				sysRoleFunc.setRoleId(roleId);
				funcList.add(sysRoleFunc);
			}
			sysRoleFuncService.saveBatch(funcList);
		}

		if (CollectionUtils.isNotEmpty(sysRole.getSysRoleTiles3d())){
			List<SysRoleTiles3d> tiles3dList = new ArrayList<>();
			for(SysRoleTiles3d sysRoleTiles3d: sysRole.getSysRoleTiles3d()) {
				if (StringUtils.isBlank(sysRoleTiles3d.getTilesId())){
					continue;
				}
				sysRoleTiles3d.setRoleId(roleId);
				tiles3dList.add(sysRoleTiles3d);
			}
			sysRoleTiles3dService.saveBatch(tiles3dList);
		}

		if (CollectionUtils.isNotEmpty(sysRole.getSysRoleBim())){
			List<SysRoleBim> roleBimList = new ArrayList<>();
			for(SysRoleBim sysRoleBim: sysRole.getSysRoleBim()) {
				if (StringUtils.isBlank(sysRoleBim.getTilesId())){
					continue;
				}
				sysRoleBim.setRoleId(roleId);
				roleBimList.add(sysRoleBim);
			}
			sysRoleBimService.saveBatch(roleBimList);
		}
		return result;
	}

	@RequestMapping("/delete")
	public @ResponseBody int deleteByPrimaryKey(@RequestBody SysRole sysRole) throws Exception {
		int result = roleService.deleteByPrimaryKey(sysRole.getRoleId());
		int result0 = sysRoleFuncService.deleteSysRoleFunc(sysRole.getRoleId());
		int result1 = sysRoleTiles3dService.deleteSysRoleTiles3d(sysRole.getRoleId());
		int result2 = sysRoleBimService.deleteSysRoleBim(sysRole.getRoleId());
		return result;
	}

	@RequestMapping("/getAllandFuncById")
	public @ResponseBody SysRole getAllandFuncById(@RequestBody SysRole sysRole) throws Exception {
		SysRole result = roleService.getAllandFuncById(sysRole.getRoleId());
		return result;
	}

	@RequestMapping("/getAllandFuncByIdandSrc")
	public @ResponseBody SysRole getAllandFuncByIdandSrc(@RequestBody SysRole sysRole) throws Exception {
		SysRole result = roleService.getAllandFuncByIdandSrc(sysRole);
		return result;
	}

	@RequestMapping("/getAllandTiles3dById")
	public @ResponseBody SysRole getAllandTiles3dById(@RequestBody SysRole sysRole) throws Exception {
		QueryWrapper<SysRoleTiles3d> wrapper = new QueryWrapper();
		wrapper.lambda().eq(SysRoleTiles3d::getRoleId, sysRole.getRoleId());
		List<SysRoleTiles3d> roleTiles3dList = sysRoleTiles3dService.list(wrapper);
		sysRole.setSysRoleTiles3d(roleTiles3dList);

		if (CollectionUtils.isNotEmpty(roleTiles3dList)){
			List<String> idList = roleTiles3dList.stream().map(SysRoleTiles3d::getTilesId).collect(Collectors.toList());
			QueryWrapper<Tiles3d> query = new QueryWrapper();
			query.lambda().in(Tiles3d::getTilesId, idList);
			List<Tiles3d> tiles3dList = tiles3DService.list(query);
			for (SysRoleTiles3d sysRoleTiles3d : roleTiles3dList) {
				for (Tiles3d tiles3d : tiles3dList) {
					if (StringUtils.equals(tiles3d.getTilesId(), sysRoleTiles3d.getTilesId())){
						sysRoleTiles3d.setTiles3d(tiles3d);
					}
				}
			}
		}

		return sysRole;
	}

	@RequestMapping("/getAllandBimById")
	public @ResponseBody SysRole getAllandBimById(@RequestBody SysRole sysRole) throws Exception {
		QueryWrapper<SysRoleBim> wrapper = new QueryWrapper();
		wrapper.lambda().eq(SysRoleBim::getRoleId, sysRole.getRoleId());
		List<SysRoleBim> roleBimList = sysRoleBimService.list(wrapper);
		sysRole.setSysRoleBim(roleBimList);

		if (CollectionUtils.isNotEmpty(roleBimList)){
			List<String> idList = roleBimList.stream().map(SysRoleBim::getTilesId).collect(Collectors.toList());
			QueryWrapper<Bim> query = new QueryWrapper();
			query.lambda().in(Bim::getTilesId, idList);
			List<Bim> bimList = bimService.list(query);
			for (SysRoleBim roleBim : roleBimList) {
				for (Bim bim : bimList) {
					if (StringUtils.equals(bim.getTilesId(), bim.getTilesId())){
						roleBim.setBim(bim);
					}
				}
			}
		}

		return sysRole;
	}
}
