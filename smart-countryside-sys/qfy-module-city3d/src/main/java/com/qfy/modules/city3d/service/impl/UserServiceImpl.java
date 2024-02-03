package com.qfy.modules.city3d.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.qfy.modules.city3d.mapper.SysUserMapperC;
import com.qfy.modules.city3d.po.SysUser;
import com.qfy.modules.city3d.po.SysUserExample;
import com.qfy.modules.city3d.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	public static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private SysUserMapperC sysUserMapper;

	@Override
	public List<SysUser> getUser() {
		List<SysUser> result = sysUserMapper.selectUserAll();
		return result;
	}

	@Override
	public int insert(SysUser sysUser) {
		int result = sysUserMapper.insert(sysUser);
		return result;
	}

	@Override
	public int updateByPrimaryKey(SysUser sysUser) {
		int result = sysUserMapper.updateByPrimaryKey(sysUser);
		return result;
	}

	@Override
	public int deleteByPrimaryKey(String userId) {
		int result = sysUserMapper.deleteByPrimaryKey(userId);
		return result;
	}

	@Override
	public SysUser findByLoginNameAndPwd(String loginName, String pwd) {
		SysUserExample example = new SysUserExample();
		example.createCriteria().andLoginNameEqualTo(loginName).andPwdEqualTo(pwd);
		try {
			List<SysUser> result = sysUserMapper.selectByExample(example);
			return result.get(0);
		} catch (Exception e) {
			logger.error("查询异常：", e);
			return null;
		}
	}

	@Override
	public List<SysUser> findByLoginName(String loginName) {
		SysUserExample example = new SysUserExample();
		example.createCriteria().andLoginNameEqualTo(loginName);
		List<SysUser> result = sysUserMapper.selectByExample(example);
		return result;
	}

}
