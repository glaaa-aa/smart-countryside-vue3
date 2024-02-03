package com.qfy.modules.appauth.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.qfy.modules.appauth.entity.AppRolePermission;
import org.jeecg.common.exception.JeecgBootException;

import java.util.List;

/**
 * @Description: 角色权限控制
 * @Author: jeecg-boot
 * @Date:   2023-04-14
 * @Version: V1.0
 */
public interface IAppRolePermissionService extends IService<AppRolePermission> {

    /**
     * 根据角色和refData关联id 查询授权的菜单ID
     * @param roleId
     * @param refData
     * @return
     */
    public List<String> querySelectedByRef(String roleId,String refData);
    /**
     * 保存授权 将上次的权限和这次作比较 差异处理提高效率
     * @param roleId
     * @param permissionIds
     * @param refData
     */
    public void saveRolePermission(String roleId,String permissionIds,String refData) throws JeecgBootException;

}
