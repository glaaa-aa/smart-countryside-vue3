package com.qfy.modules.appauth.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.qfy.modules.appauth.entity.AppPermission;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description: 分组树，封装树结构
 * @author: jeecg-boot
 */
@Data
public class AppPermissionTree implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String key;
	private String title;
	private Integer tenantId;
	/**
	 * 上级id
	 */
	private String parentId;

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

	private String createBy;
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	private String updateBy;
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
	private String remarks;

	private boolean isLeaf;
	// 以下所有字段均与SysDepart相同

	private List<AppPermissionTree> children = new ArrayList<>();

	public AppPermissionTree() {
	}

	public AppPermissionTree(AppPermission appPermission) {
		this.key = appPermission.getId().toString();
		this.id = appPermission.getId().toString();
		this.parentId = appPermission.getParentId().toString();
		this.title = appPermission.getName();
		this.tenantId = appPermission.getTenantId();
		this.name = appPermission.getName();
		this.perms = appPermission.getPerms();
		this.sortNo = appPermission.getSortNo();
		this.menuType = appPermission.getMenuType();
		this.permsType = appPermission.getPermsType();
		this.component = appPermission.getComponent();
		this.icon = appPermission.getIcon();
		this.status = appPermission.getStatus();
		this.url = appPermission.getUrl();
		this.createBy = appPermission.getCreateBy();
		this.createTime = appPermission.getCreateTime();
		this.remarks = appPermission.getRemarks();
	}

}
