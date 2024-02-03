package com.qfy.modules.appauth.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;

import java.io.Serializable;

/**
 * @Description: 角色权限控制
 * @Author: jeecg-boot
 * @Date:   2023-04-14
 * @Version: V1.0
 */
@Data
public class AppRolePermissionVO implements Serializable {

    @ApiModelProperty(value = "ID")
    private String id;
	/**权限id*/
	@Excel(name = "权限id", width = 15)
    private Integer permissionId;
    @Excel(name = "菜单标题", width = 15)
    private String permissionName;

    @Excel(name = "权限策略1显示2禁用", width = 15)
    private String permsType;
    /**菜单权限编码*/
    @Excel(name = "菜单权限编码", width = 15)
    private String perms;
	/**角色ID*/
	@Excel(name = "角色ID", width = 15)
    private Integer roleId;
	/**角色编码*/
	@Excel(name = "角色编码", width = 15)
    private String roleCode;

    /**关联id*/
    @Excel(name = "关联id", width = 15)
    private String refData;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getPermsType() {
        return permsType;
    }

    public void setPermsType(String permsType) {
        this.permsType = permsType;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRefData() {
        return refData;
    }

    public void setRefData(String refData) {
        this.refData = refData;
    }
}
