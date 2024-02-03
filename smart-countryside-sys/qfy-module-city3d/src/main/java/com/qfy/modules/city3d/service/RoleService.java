package com.qfy.modules.city3d.service;

import java.util.List;
import com.qfy.modules.city3d.po.SysRole;

public interface RoleService {
	public List<SysRole> getRole();
	public int insert(SysRole sysRole);
	public int updateByPrimaryKey(SysRole sysRole);
	public int deleteByPrimaryKey(String roleId);
	public SysRole getAllandFuncById(String roleId);
	public SysRole getAllandFuncByIdandSrc(SysRole sysRole);
	public SysRole getAllandTiles3dById(String roleId);
	public SysRole getAllandBimById(String roleId);
}
