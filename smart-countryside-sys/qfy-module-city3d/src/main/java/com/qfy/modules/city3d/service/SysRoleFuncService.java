package com.qfy.modules.city3d.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qfy.modules.city3d.po.SysRoleFunc;

public interface SysRoleFuncService extends IService<SysRoleFunc> {
	public int deleteSysRoleFunc(String roleId);
	public int insertSysRoleFunc(SysRoleFunc sysRoleFunc);
	void saveRolePermission(String roleId, String permissionIds, String lastPermissionIds);
}
