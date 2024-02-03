package com.qfy.modules.city3d.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qfy.modules.city3d.mapper.SysRoleBimMapper;
import com.qfy.modules.city3d.po.SysRoleBim;
import com.qfy.modules.city3d.po.SysRoleBimExample;
import com.qfy.modules.city3d.service.SysRoleBimService;
import org.springframework.stereotype.Service;

@Service
public class SysRoleBimServiceImpl extends ServiceImpl<SysRoleBimMapper, SysRoleBim> implements SysRoleBimService {

	@Override
	public int deleteSysRoleBim(String roleId) {
		SysRoleBimExample example = new SysRoleBimExample();
		example.or().andRoleIdEqualTo(roleId);
		int result = baseMapper.deleteByExample(example);
		return result;
	}

	@Override
	public int insertSysRoleBim(SysRoleBim sysRoleBim) {
		int result = baseMapper.insert(sysRoleBim);
		return result;
	}

}
