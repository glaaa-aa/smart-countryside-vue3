package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qfy.modules.city3d.po.SysUser;
import java.util.List;

@DS("postgresql")
public interface SysUserMapperC extends PGSysUserMapper {
	List<SysUser> selectUserAll();
}
