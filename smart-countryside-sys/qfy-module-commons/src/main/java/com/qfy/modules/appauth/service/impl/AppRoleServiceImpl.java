package com.qfy.modules.appauth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qfy.modules.appauth.entity.AppRole;
import com.qfy.modules.appauth.mapper.AppRoleMapper;
import com.qfy.modules.appauth.service.IAppRoleService;
import org.springframework.stereotype.Service;

/**
 * @Description: 角色管理
 * @Author: jeecg-boot
 * @Date:   2023-04-14
 * @Version: V1.0
 */
@Service
public class AppRoleServiceImpl extends ServiceImpl<AppRoleMapper, AppRole> implements IAppRoleService {

}
