package com.qfy.modules.commons.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecg.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * TODO 基础公共字段实体
 *
 * @author lanls
 * @version 1.0
 * @date 2021/11/10 11:52
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BaseEntity {
    /**创建人名称*/
//    @Excel(name = "创建人名称", width = 15)
    @ApiModelProperty(value = "创建人名称")
    @TableField(exist = false)
    private String createName;
    /**创建人登录名称*/
    @ApiModelProperty(value = "创建人登录名称")
    @Dict(dicCode = "username",dictTable="sys_user",dicText="realname")
    private String createBy;
    /**创建日期*/
//    @Excel(name = "创建日期", width = 15, format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建日期")
    private Date createTime;

    /**更新人名称*/
//    @Excel(name = "更新人名称", width = 15)
    @ApiModelProperty(value = "更新人名称")
    @TableField(exist = false)
    private String updateName;
    /**更新人登录名称*/
    @ApiModelProperty(value = "更新人登录名称")
    private String updateBy;
    /**更新日期*/
//    @Excel(name = "更新日期", width = 15, format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新日期")
    private Date updateTime;
    /**所属部门*/
    @ApiModelProperty(value = "所属部门")
    private String sysOrgCode;

    /**备注*/
    @Excel(name = "备注", width = 15, orderNum = "999")
    @ApiModelProperty(value = "备注")
    private String remarks;

    /** 删除状态：0：逻辑删除，1：正常使用 */
//    @Excel(name = "数据状态 0：逻辑删除，1：正常可用状态", width = 15)
    @ApiModelProperty(value = "数据状态 0：正常可用状态，1：逻辑删除")
    @Dict(dicCode = "data_del_state")
    private Integer delFlag;

}
