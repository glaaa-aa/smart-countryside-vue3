package com.qfy.modules.city3d.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qfy.modules.city3d.po.SysRoleTiles3d;

public interface SysRoleTiles3dService extends IService<SysRoleTiles3d> {
	public int deleteSysRoleTiles3d(String roleId);
	public int insertSysRoleTiles3d(SysRoleTiles3d sysRoleTiles3d);
}
