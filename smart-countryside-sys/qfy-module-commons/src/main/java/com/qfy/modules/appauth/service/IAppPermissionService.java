package com.qfy.modules.appauth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qfy.modules.appauth.entity.AppPermission;
import com.qfy.modules.appauth.model.AppPermissionTree;

import java.util.List;

/**
 * @Description: 移动端按钮权限控制
 * @Author: jeecg-boot
 * @Date:   2023-04-14
 * @Version: V1.0
 */
public interface IAppPermissionService extends IService<AppPermission> {


    /**
     * 查询所有分组,并分节点进行显示
     * @return
     */
    List<AppPermissionTree> queryTreeList();
}
