package com.qfy.modules.commons.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qfy.modules.commons.entity.SystemUser;
import com.qfy.modules.commons.model.SystemUserSysDepartModel;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @Author scott
 * @since 2018-12-20
 */
public interface ISystemUserService extends IService<SystemUser> {
	SystemUser getUserByName(String username);

	/**
	 * 清富源 马强2022-11-17 添加
	 * 查询用户信息  用户信息和所属部门列表
	 * @param userParams
	 * @return
	 */
	public List<SystemUserSysDepartModel> getUserByQfy(SystemUserSysDepartModel userParams);


	public SystemUserSysDepartModel getUserInfoByQfy(String username);

	/**
	 * 根据分管领导查询所有部门编码
	 * 根据用户账号查询 用户所管理的分管部门
	 * @param username
	 * @param tenantId
	 * @return
	 */
	public List selectLeaderOrgCode( String username,Integer tenantId);

	/**
	 * 查询用户账号查询用户所在部门
	 * @param userId
	 * @return
	 */
	public List selectDpetOrgCode(String userId);

	public Map<String,Object> selectUserDepart(String userName);
	/**
	 * 查询所有租户用户
	 * @return
	 */
	public List<String> selectAllTenntUser(Integer tenantId);

}
