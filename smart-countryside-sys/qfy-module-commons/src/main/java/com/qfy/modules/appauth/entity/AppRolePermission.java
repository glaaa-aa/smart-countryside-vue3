package com.qfy.modules.appauth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecg.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;

import java.io.Serializable;

/**
 * @Description: 角色权限控制
 * @Author: jeecg-boot
 * @Date:   2023-04-14
 * @Version: V1.0
 */
@Data
@TableName("qfy_app_role_permission")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="qfy_app_role_permission对象", description="角色权限控制")
public class AppRolePermission implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String templateRefData = "0";

    /****************************************
     *  如果菜单permissionId中包含-1 则未授权
     */

    /**ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "ID")
    private String id;
	/**权限id*/
	@Excel(name = "权限id", width = 15)
    @ApiModelProperty(value = "权限id")
    private Integer permissionId;
	/**角色ID*/
	@Excel(name = "角色ID", width = 15)
    @ApiModelProperty(value = "角色ID")
    private Integer roleId;
	/**角色编码*/
	@Excel(name = "角色编码", width = 15)
    @ApiModelProperty(value = "角色编码")
    private String roleCode;

    /**关联类型*/
    @Excel(name = "关联类型", width = 15, dicCode = "DictRefEnum")
    @ApiModelProperty(value = "关联类型")
    //使用枚举和字典 DictRefEnum
    @Dict(dicCode = "DictRefEnum")
    private String refSrc;
    /**关联id*/
    @Excel(name = "关联id", width = 15)
    @ApiModelProperty(value = "关联id")
    private String refData;


    public AppRolePermission() {
    }

    public AppRolePermission(Integer roleId, Integer permissionId) {
        this.roleId = roleId;
        this.permissionId = permissionId;
    }

}
