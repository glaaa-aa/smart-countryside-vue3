package com.qfy.modules.commons.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qfy.modules.commons.entity.SystemUser;
import com.qfy.modules.commons.model.SystemUserSysDepartModel;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 * @Author scott
 * @since 2018-12-20
 */
public interface SystemUserMapper extends BaseMapper<SystemUser> {

	/**
	 * 通过Id查询用户信息
	 * @param id
	 * @return
	 */
	public SystemUser getById(Serializable id);
	/**
	  * 通过用户账号查询用户信息
	 * @param username
	 * @return
	 */
	public SystemUser getUserByName(@Param("username") String username);

	/**
	 *  根据部门Id查询用户信息
	 * @param page
	 * @param departId
	 * @return
	 */
	IPage<SystemUser> getUserByDepId(Page page, @Param("departId") String departId, @Param("username") String username);

	/**
	 *  根据用户Ids,查询用户所属部门名称信息
	 * @param userIds
	 * @return
	 */
	List<SystemUser> getDepNamesByUserIds(@Param("userIds")List<String> userIds);

	/**
	 *  根据部门Ids,查询部门下用户信息
	 * @param page
	 * @param departIds
	 * @return
	 */
	IPage<SystemUser> getUserByDepIds(Page page, @Param("departIds") List<String> departIds, @Param("username") String username);

	/**
	 * 根据角色Id查询用户信息
	 * @param page
	 * @param
	 * @return
	 */
	IPage<SystemUser> getUserByRoleId(Page page, @Param("roleId") String roleId, @Param("username") String username);

	/**
	 * 根据用户名设置部门ID
	 * @param username
	 * @param orgCode
	 */
	void updateUserDepart(@Param("username") String username,@Param("orgCode") String orgCode);

	/**
	 * 根据手机号查询用户信息
	 * @param phone
	 * @return
	 */
	public SystemUser getUserByPhone(@Param("phone") String phone);


	/**
	 * 根据邮箱查询用户信息
	 * @param email
	 * @return
	 */
	public SystemUser getUserByEmail(@Param("email")String email);

	/**
	 * 根据 orgCode 查询用户，包括子部门下的用户
	 *
	 * @param page 分页对象, xml中可以从里面进行取值,传递参数 Page 即自动分页,必须放在第一位(你可以继承Page实现自己的分页对象)
	 * @param orgCode
	 * @param userParams 用户查询条件，可为空
	 * @return
	 */
	List<SystemUserSysDepartModel> getUserByOrgCode(IPage page, @Param("orgCode") String orgCode, @Param("userParams") SystemUser userParams);


    /**
     * 查询 getUserByOrgCode 的Total
     *
     * @param orgCode
     * @param userParams 用户查询条件，可为空
     * @return
     */
    Integer getUserByOrgCodeTotal(@Param("orgCode") String orgCode, @Param("userParams") SystemUser userParams);

    /**
     * @Author scott
     * @Date 2019/12/13 16:10
     * @Description: 批量删除角色与用户关系
     */
	void deleteBathRoleUserRelation(@Param("roleIdArray") String[] roleIdArray);

    /**
     * @Author scott
     * @Date 2019/12/13 16:10
     * @Description: 批量删除角色与权限关系
     */
	void deleteBathRolePermissionRelation(@Param("roleIdArray") String[] roleIdArray);

	/**
	 * 查询被逻辑删除的用户
	 */
	List<SystemUser> selectLogicDeleted(@Param(Constants.WRAPPER) Wrapper<SystemUser> wrapper);

	/**
	 * 还原被逻辑删除的用户
	 */
	int revertLogicDeleted(@Param("userIds") String userIds, @Param("entity") SystemUser entity);

	/**
	 * 彻底删除被逻辑删除的用户
	 */
	int deleteLogicDeleted(@Param("userIds") String userIds);

    /** 更新空字符串为null【此写法有sql注入风险，禁止随便用】 */
    @Deprecated
    int updateNullByEmptyString(@Param("fieldName") String fieldName);

	/**
	 *  根据部门Ids,查询部门下用户信息
	 * @param departIds
	 * @return
	 */
	List<SystemUser> queryByDepIds(@Param("departIds")List<String> departIds, @Param("username") String username);

	/**
	 * 清富源 马强2022-11-17 添加
	 * 查询用户信息  用户信息和所属部门列表
	 * @param userParams
	 * @return
	 */
	List<SystemUserSysDepartModel> getUserByQfy(@Param("userParams") SystemUserSysDepartModel userParams);


	SystemUserSysDepartModel getUserByQfy2(@Param("userName") String userName);

	/**
	 * 根据租户和用户名查询 用户的分管领导
	 * @param username
	 * @param tenantId
	 * @return
	 */
	@InterceptorIgnore(tenantLine = "true")
	public List selectLeaderOrgCode(@Param("userName") String username,@Param("tenantId") Integer tenantId);

	/**
	 * 根据用户查询用户所属部门
	 * @param
	 * @return
	 */
	@InterceptorIgnore(tenantLine = "true")
	public List selectDpetOrgCode(@Param("userId") String userId);

	/**
	 * 根据用户账号查询分管部门和负责部门
	 * @param userName
	 * @return
	 */
	@InterceptorIgnore(tenantLine = "true")
	public List<Map<String,Object>> selectUserDepart(@Param("userName") String userName);

	/**
	 * 查询所有租户用户
	 * @return
	 */
	@Select("select username from sys_user where tenant_id >1")
	public List<String> selectAllTenntUser(@Param("tenantId") Integer tenantId);


	Integer insertUser(SystemUser byUser);
}
