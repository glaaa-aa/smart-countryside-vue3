package com.qfy.modules.city3d.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qfy.modules.city3d.po.SysRoleBim;

public interface SysRoleBimService extends IService<SysRoleBim> {
	public int deleteSysRoleBim(String roleId);
	public int insertSysRoleBim(SysRoleBim sysRoleBim);
}
