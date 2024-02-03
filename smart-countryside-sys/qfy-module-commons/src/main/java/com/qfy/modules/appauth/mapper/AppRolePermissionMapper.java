package com.qfy.modules.appauth.mapper;


import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qfy.modules.appauth.entity.AppRolePermission;
import com.qfy.modules.appauth.model.AppRolePermissionVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @Description: 角色权限控制
 * @Author: jeecg-boot
 * @Date:   2023-04-14
 * @Version: V1.0
 */
public interface AppRolePermissionMapper extends BaseMapper<AppRolePermission> {

    /**
     * 查询所有项目
     * @return
     */
    @InterceptorIgnore(tenantLine = "true")
    @Select("select id,project_name,tenant_id from qfy_team_project where del_flag=0 ")
    public List<Map<String,Object>> queryAllProjectBySys();

    /**
     * 根据角色和refData关联id 查询授权的菜单ID
     * @param roleId
     * @param refData
     * @return
     */
    public List<AppRolePermission> querySelectedByRef(@Param("roleId") String roleId, @Param("refData") String refData);


    /**
     * 根据refData查询refData下面的所有授权菜单
     * @param refData
     * @param refData
     * @return
     */
    @InterceptorIgnore(tenantLine = "true")
    public List<AppRolePermissionVO> selectAllRolePermission(@Param("refData") String refData,@Param("tenantId") Integer tenantId);

    /**
     * 根据角色删除 授权菜单
     * @param roleId
     * @param refData
     * @return
     */
    @Delete("delete from qfy_app_role_permission where role_id = #{roleId} and ref_data = #{refData}")
    public int removeByRoleId(@Param("roleId") String roleId,@Param("refData") String refData);
}
