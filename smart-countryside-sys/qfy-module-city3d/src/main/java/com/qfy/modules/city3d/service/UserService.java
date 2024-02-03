package com.qfy.modules.city3d.service;

import com.qfy.modules.city3d.po.SysUser;
import java.util.List;

public interface UserService {
	public List<SysUser> getUser();
	public int insert(SysUser sysUser);
	public int updateByPrimaryKey(SysUser sysUser);
	public int deleteByPrimaryKey(String userId);
	public SysUser findByLoginNameAndPwd(String loginName, String pwd);
	public List<SysUser> findByLoginName(String loginName);
}
