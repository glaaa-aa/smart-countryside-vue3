package com.qfy.modules.appauth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 角色管理
 * @Author: jeecg-boot
 * @Date:   2023-04-14
 * @Version: V1.0
 */
@Data
@TableName("qfy_app_role")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="qfy_app_role对象", description="角色管理")
public class AppRole implements Serializable {
    private static final long serialVersionUID = 1L;
    //角色固定 暂时不能更改
	/**主键id*/
	@TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "主键id")
    private Integer id;
	/**角色名称*/
	@Excel(name = "角色名称", width = 15)
    @ApiModelProperty(value = "角色名称")
    private String roleName;
	/**角色编码*/
	@Excel(name = "角色编码", width = 15)
    @ApiModelProperty(value = "角色编码")
    private String roleCode;

    @Excel(name = "角色排序", width = 15)
    @ApiModelProperty(value = "角色编码")
    private Integer roleSort;
	/**描述*/
	@Excel(name = "描述", width = 15)
    @ApiModelProperty(value = "描述")
    private String description;
	/**创建人*/
    @ApiModelProperty(value = "创建人")
    private String createBy;
	/**创建时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
	/**更新人*/
    @ApiModelProperty(value = "更新人")
    private String updateBy;
	/**更新时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
	/**tenantId*/
	@Excel(name = "tenantId", width = 15)
    @ApiModelProperty(value = "tenantId")
    private Integer tenantId;
}
