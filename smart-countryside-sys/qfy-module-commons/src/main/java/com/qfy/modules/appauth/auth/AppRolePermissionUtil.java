package com.qfy.modules.appauth.auth;/**
 * @title: AppRolePermissionUtil
 * @projectName qfy-project-task-system
 * @description: TODO
 * @author 马强
 * @date 2023/4/19 17:01
 */

import com.qfy.modules.appauth.model.AppAuthBean;
import com.qfy.modules.appauth.model.UserDepratBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jeecg.common.util.RedisUtil;
import org.jeecg.common.util.SpringContextUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author     ：马强
 * @date       ：Created in 2023/4/19 17:01
 * @description：获取应用角色权限
 * @modified By：
 * @version:
 */
@Slf4j
public class AppRolePermissionUtil {

    @Autowired
    private static RedisUtil redisUtil;
    static{
        redisUtil = SpringContextUtils.getBean(RedisUtil.class);
    }

    /**
     *
     * @param appAuth 项目数据参数
     * @return
     */
    public static String getProjectAuth(AppAuthBean appAuth){

            StringBuffer auth = new StringBuffer();
        if(appAuth.isOwner()){
            auth.append(getAuth(appAuth.getProjectId(), AppRolePermissionEnum.projectCreate.name())).append(",");
        }
        if(appAuth.isExecutor()){
            auth.append(getAuth(appAuth.getProjectId(), AppRolePermissionEnum.projectManager.name())).append(",");
            log.info(auth.toString());
        }
        if(appAuth.isAdmin()){
            auth.append(getAuth(appAuth.getProjectId(), AppRolePermissionEnum.projectFenguan.name())).append(",");
            log.info(auth.toString());
        }
        if(StringUtils.isNotEmpty(appAuth.getOrgCode())){
            //如果项目部门不为空， 判断项目所在部门权限
            UserDepratBean userDepratBean = UserDepartFactory.userDepart(appAuth.getUsername());
            if(userDepratBean.getFenguanLeader() != null && userDepratBean.getFenguanLeader().indexOf(appAuth.getOrgCode()) >= 0 ){
                auth.append(getAuth(appAuth.getProjectId(), AppRolePermissionEnum.projectFenguan.name())).append(",");
            }
            //如果项目部门不为空， 判断项目所在部门权限
            if(userDepratBean.getDepartUser() != null && userDepratBean.getDepartUser().indexOf(appAuth.getOrgCode()) >= 0 ){
                auth.append(getAuth(appAuth.getProjectId(), AppRolePermissionEnum.projectDepart.name())).append(",");
            }
            log.info(auth.toString());
        }
        //如果项目部门不为空， 判断项目所在部门权限
        if(appAuth.isMember()){
            auth.append(getAuth(appAuth.getProjectId(), AppRolePermissionEnum.projectMember.name())).append(",");
        }
        log.info(auth.toString());
        String[] arr = auth.toString().split(",");
        Set<String> set = new HashSet<>();
        set.addAll(Arrays.asList(arr));
        set.remove("null");
        return String.join(",",set);
    }
    /**
     * 获取任务权限
     * @param appAuth
     * @return
     */
    public static String getTaskAuth(AppAuthBean appAuth){

        StringBuffer auth = new StringBuffer();
        if(appAuth.isOwner()){
            auth.append(getAuth(appAuth.getProjectId(), AppRolePermissionEnum.taskCreate.name())).append(",");
        }
        if(appAuth.isExecutor()){
            auth.append(getAuth(appAuth.getProjectId(), AppRolePermissionEnum.taskManager.name())).append(",");
        }
        if(appAuth.isAdmin()){
            auth.append(getAuth(appAuth.getProjectId(), AppRolePermissionEnum.taskFenguan.name())).append(",");
        }

        if(StringUtils.isNotEmpty(appAuth.getOrgCode())){
            //如果项目部门不为空， 判断项目所在部门权限
            UserDepratBean userDepratBean = UserDepartFactory.userDepart(appAuth.getUsername());
            if(userDepratBean.getFenguanLeader() != null && userDepratBean.getFenguanLeader().indexOf(appAuth.getOrgCode()) >= 0 ){
                auth.append(getAuth(appAuth.getProjectId(), AppRolePermissionEnum.taskFenguan.name())).append(",");
            }
            //如果项目部门不为空， 判断项目所在部门权限
            if(userDepratBean.getDepartUser() != null && userDepratBean.getDepartUser().indexOf(appAuth.getOrgCode()) >= 0 ){
                auth.append(getAuth(appAuth.getProjectId(), AppRolePermissionEnum.taskDepart.name())).append(",");
            }
        }
        //如果项目部门不为空， 判断项目所在部门权限
        if(appAuth.isMember()){
            auth.append(getAuth(appAuth.getProjectId(), AppRolePermissionEnum.taskMember.name())).append(",");
        }
        String[] arr = auth.toString().split(",");
        Set<String> set = new HashSet<>();
        set.addAll(Arrays.asList(arr));
        set.remove("null");
        return String.join(",",set);
    }


    private static String getAuth(String projectId,String appRolePermission){
        Object _auth = redisUtil.get(projectId +"_" + appRolePermission);
        if(_auth == null){
            return null;
        }
        return _auth.toString();
    }


}
