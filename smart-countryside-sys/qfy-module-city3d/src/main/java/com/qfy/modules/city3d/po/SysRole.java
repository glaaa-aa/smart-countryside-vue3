package com.qfy.modules.city3d.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class SysRole implements Serializable {

	private String id;
	/**
	 * 角色名称
	 */
	@Excel(name="角色名",width=15)
	private String roleName;

	/**
	 * 角色编码
	 */
	@Excel(name="角色编码",width=15)
	private String roleCode;

	/**
	 * 描述
	 */
	@Excel(name="描述",width=60)
	private String description;

	/**
	 * 创建人
	 */
	private String createBy;

	/**
	 * 创建时间
	 */
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	/**
	 * 更新人
	 */
	private String updateBy;

	/**
	 * 更新时间
	 */
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date updateTime;

    private String roleId;

    private List<SysRoleFunc> sysRoleFunc;

    private List<SysFunc> sysFunc;

    private List<SysRoleTiles3d> sysRoleTiles3d;

    private List<SysRoleBim> sysRoleBim;

}
