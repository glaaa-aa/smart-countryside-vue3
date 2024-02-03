package com.qfy.modules.appauth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qfy.modules.appauth.entity.AppRole;
import com.qfy.modules.appauth.entity.AppRolePermission;
import com.qfy.modules.appauth.mapper.AppRolePermissionMapper;
import com.qfy.modules.appauth.service.IAppRolePermissionService;
import com.qfy.modules.appauth.service.IAppRoleService;
import org.jeecg.common.exception.JeecgBootException;
import org.jeecg.common.util.RedisUtil;
import org.jeecg.common.util.oConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: 角色权限控制
 * @Author: jeecg-boot
 * @Date:   2023-04-14
 * @Version: V1.0
 */
@Service
public class AppRolePermissionServiceImpl extends ServiceImpl<AppRolePermissionMapper, AppRolePermission> implements IAppRolePermissionService {

    @Autowired
    private IAppRoleService appRoleService;
    @Autowired
    private RedisUtil redisUtil;
    @Override
    public List<String> querySelectedByRef(String roleId, String refData) {
        List<AppRolePermission> _list = baseMapper.querySelectedByRef(roleId,refData);
        if(_list == null || _list.size() <= 0){
            _list = baseMapper.querySelectedByRef(roleId,"0");
        }

        List<String> selectedIds = _list.stream().map(sysRolePermission -> String.valueOf(sysRolePermission.getPermissionId())).collect(Collectors.toList());

        //如果菜单permissionId中包含-1 则未授权
        if(selectedIds.contains("-1")){
            return null;
        }
        return selectedIds;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveRolePermission(String roleId, String permissionIds, String refData) throws JeecgBootException {
        AppRole appRole = appRoleService.getById(Integer.parseInt(roleId));
        baseMapper.removeByRoleId(roleId,refData);

        if(oConvertUtils.isEmpty(permissionIds)) {
            //删除所有授权 并复制-1 （菜单permissionId中包含-1 则未授权）
            AppRolePermission rolepms = new AppRolePermission(Integer.parseInt(roleId), -1);
            rolepms.setRefData(refData);
            rolepms.setRoleCode(appRole.getRoleCode());
            this.save(rolepms);
        }else {
            List<String> permissionIdArr = Arrays.asList(permissionIds.split(","));
            List<AppRolePermission> list = new ArrayList<AppRolePermission>();
            for (String p : permissionIdArr) {
                if(oConvertUtils.isNotEmpty(p)) {
                    AppRolePermission rolepms = new AppRolePermission(Integer.parseInt(roleId), Integer.parseInt(p));
                    rolepms.setRefData(refData);
                    rolepms.setRoleCode(appRole.getRoleCode());
                    list.add(rolepms);
                }
            }
            this.saveBatch(list);
        }
    }

}
