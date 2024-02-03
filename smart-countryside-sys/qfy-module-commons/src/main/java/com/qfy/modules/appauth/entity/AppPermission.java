package com.qfy.modules.appauth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.qfy.modules.commons.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;

import java.io.Serializable;

/**
 * @Description: 移动端按钮权限控制
 * @Author: jeecg-boot
 * @Date:   2023-04-14
 * @Version: V1.0
 */
@Data
@TableName("qfy_app_permission")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="qfy_app_permission对象", description="移动端按钮权限控制")
public class AppPermission extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键id*/
	@TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "主键id")
    private Integer id;
	/**父id*/
	@Excel(name = "父id", width = 15)
    @ApiModelProperty(value = "父id")
    private Integer parentId;

    /**租户ID*/
    @Excel(name = "租户ID", width = 15)
    @ApiModelProperty(value = "租户ID")
    private Integer tenantId;

	/**菜单标题*/
	@Excel(name = "菜单标题", width = 15)
    @ApiModelProperty(value = "菜单标题")
    private String name;
	/**路径*/
	@Excel(name = "路径", width = 15)
    @ApiModelProperty(value = "路径")
    private String url;
	/**组件*/
	@Excel(name = "组件", width = 15)
    @ApiModelProperty(value = "组件")
    private String component;
	/**菜单类型(0:一级菜单; 1:子菜单:2:按钮权限)*/
	@Excel(name = "菜单类型(0:一级菜单; 1:子菜单:2:按钮权限)", width = 15)
    @ApiModelProperty(value = "菜单类型(0:一级菜单; 1:子菜单:2:按钮权限)")
    private String menuType;
	/**权限策略1显示2禁用*/
	@Excel(name = "权限策略1显示2禁用", width = 15)
    @ApiModelProperty(value = "权限策略1显示2禁用")
    private String permsType;
	/**菜单权限编码*/
	@Excel(name = "菜单权限编码", width = 15)
    @ApiModelProperty(value = "菜单权限编码")
    private String perms;
	/**菜单排序*/
	@Excel(name = "菜单排序", width = 15)
    @ApiModelProperty(value = "菜单排序")
    private Double sortNo;
	/**菜单图标*/
	@Excel(name = "菜单图标", width = 15)
    @ApiModelProperty(value = "菜单图标")
    private String icon;
	/**按钮权限状态(0无效1有效)*/
	@Excel(name = "按钮权限状态(0无效1有效)", width = 15)
    @ApiModelProperty(value = "按钮权限状态(0无效1有效)")
    private String status;


}
