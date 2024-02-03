package com.qfy.modules.city3d.service.impl;

import com.qfy.modules.city3d.mapper.SysRoleMapperB;
import com.qfy.modules.city3d.po.SysRole;
import com.qfy.modules.city3d.service.RoleService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
	@Resource
	private SysRoleMapperB sysRoleMapperB;

	@Override
	public List<SysRole> getRole() {
		List<SysRole> result = sysRoleMapperB.getAllandFunc();
		return result;
	}

	@Override
	public int insert(SysRole sysRole) {
		int result = sysRoleMapperB.insert(sysRole);
		return result;
	}

	@Override
	public int updateByPrimaryKey(SysRole sysRole) {
		int result = sysRoleMapperB.updateByPrimaryKey(sysRole);
		return result;
	}

	@Override
	public int deleteByPrimaryKey(String roleId) {
		int result = sysRoleMapperB.deleteById(roleId);//sysRoleMapperB.deleteByPrimaryKey(roleId);
		return result;
	}

	@Override
	public SysRole getAllandFuncById(String roleId) {
		SysRole result = sysRoleMapperB.getAllandFuncById(roleId);
		return result;
	}

	@Override
	public SysRole getAllandFuncByIdandSrc(SysRole sysRole) {
		SysRole result = sysRoleMapperB.getAllandFuncByIdandSrc(sysRole);
		return result;
	}

	@Override
	public SysRole getAllandTiles3dById(String roleId) {
		SysRole result = sysRoleMapperB.getAllandTiles3dById(roleId);
		return result;
	}

	@Override
	public SysRole getAllandBimById(String roleId) {
		SysRole result = sysRoleMapperB.getAllandBimById(roleId);
		return result;
	}
}
