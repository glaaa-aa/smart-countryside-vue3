package com.qfy.modules.commons.model;

import lombok.Data;

/**
 * 包含 SysUser 和 SysDepart 的 Model
 *
 * @author sunjianlei
 */
@Data
public class SystemUserSysDepartModel {

    private String id;
    private String userName;
    private String realname;
    private String workNo;
    private String post;
    private String telephone;
    private String email;
    private String phone;
    private String departId;
    private String departName;
    private String avatar;

    private Integer tenantId;
    private String tenantName;
    private String orgCode;

    /**
     * 身份（10领导  20 政委  40:部门主管   60:员工/干警）
     */
    private Integer userIdentity;

}
