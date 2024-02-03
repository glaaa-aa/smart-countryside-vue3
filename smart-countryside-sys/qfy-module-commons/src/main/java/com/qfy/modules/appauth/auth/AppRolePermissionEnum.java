package com.qfy.modules.appauth.auth;

import lombok.Getter;

/**
 * @author 马强
 * @title: AppRolePermissionEnum
 * @projectName qfy-project-task-system
 * @description: TODO
 * @date 2023/04/19 10:03
 */
@Getter
public enum AppRolePermissionEnum {
        //项目角色
        projectCreate,  //项目拥有者
        projectManager,//项目负责人
        projectMember,  //项目成员
        projectDepart,//项目部门领导
        projectFenguan,//项目分管领导

        //任务角色
        taskCreate, //任务拥有者
        taskManager, //任务负责人
        taskMember, //任务成员
        taskDepart, //任务部门领导
        taskFenguan, //任务分管部门

//        pother; //其他人员


}
