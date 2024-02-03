package com.qfy.modules.commons.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qfy.modules.commons.entity.SystemUser;
import com.qfy.modules.commons.mapper.SystemUserMapper;
import com.qfy.modules.commons.model.SystemUserSysDepartModel;
import com.qfy.modules.commons.service.ISystemUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @Author: scott
 * @Date: 2018-12-20
 */
@Service
@Slf4j
public class SystemUserServiceImpl extends ServiceImpl<SystemUserMapper, SystemUser> implements ISystemUserService {

	@Override
	public SystemUser getUserByName(String username) {
		return baseMapper.getUserByName(username);
	}

	@Override
	public SystemUser getById(Serializable id) {
		return baseMapper.getById(id);
	}

	/**
	 * 清富源 马强2022-11-17 添加
	 * 查询用户信息  用户信息和所属部门列表
	 * @param userParams
	 * @return
	 */
	@Override
	public List<SystemUserSysDepartModel> getUserByQfy(SystemUserSysDepartModel userParams){
		List<SystemUserSysDepartModel> list = baseMapper.getUserByQfy(userParams);
		return list;
	}

	@Override
	public SystemUserSysDepartModel getUserInfoByQfy(String username) {
		return baseMapper.getUserByQfy2(username);
	}


	@Override
	public List selectLeaderOrgCode(String username,Integer tenantId) {
		return baseMapper.selectLeaderOrgCode(username,tenantId);
	}

	@Override
	public List selectDpetOrgCode(String userId) {
		return baseMapper.selectDpetOrgCode(userId);
	}

	@Override
	public Map<String, Object> selectUserDepart(String userName) {
		List<Map<String, Object>> list = baseMapper.selectUserDepart(userName);
		if(list == null || list.size() <=0){
			return null;
		}
		Map<String, Object> _map = new HashMap<>();
		for (Map<String,Object> map : list){
			_map.put(map.get("key").toString(),map.get("orgCode"));
		}
		return _map;
	}

	@Override
	public List<String> selectAllTenntUser(Integer tenantId){
		return baseMapper.selectAllTenntUser(tenantId);
	}
}
