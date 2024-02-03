package com.qfy.modules.city3d.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qfy.modules.city3d.mapper.SysRoleTiles3dMapper;
import com.qfy.modules.city3d.po.SysRoleTiles3d;
import com.qfy.modules.city3d.po.SysRoleTiles3dExample;
import com.qfy.modules.city3d.service.SysRoleTiles3dService;
import org.springframework.stereotype.Service;

@Service
public class SysRoleTiles3dServiceImpl extends ServiceImpl<SysRoleTiles3dMapper, SysRoleTiles3d> implements SysRoleTiles3dService {

	@Override
	public int deleteSysRoleTiles3d(String roleId) {
		SysRoleTiles3dExample example = new SysRoleTiles3dExample();
		example.or().andRoleIdEqualTo(roleId);
		int result = baseMapper.deleteByExample(example);
		return result;
	}

	@Override
	public int insertSysRoleTiles3d(SysRoleTiles3d sysRoleTiles3d) {
		int result = baseMapper.insert(sysRoleTiles3d);
		return result;
	}

}
