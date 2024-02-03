package com.qfy.modules.family.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.qfy.modules.smartform.entity.CheckItemGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecg.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Description: 农户积分手册
 * @Author: jeecg-boot
 * @Date:   2022-12-01
 * @Version: V1.0
 */
@Data
@TableName("qfy_family_integral")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="qfy_family_integral对象", description="农户积分手册")
public class FamilyIntegral implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
    private String id;
	/**创建人登录名称*/
    @ApiModelProperty(value = "创建人登录名称")
    private String createBy;
	/**创建时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
	/**更新人登录名称*/
    @ApiModelProperty(value = "更新人登录名称")
    private String updateBy;
	/**更新时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
	/**所属部门*/
    @ApiModelProperty(value = "所属部门")
    private String sysOrgCode;

	/**是否删除 0否，1是*/
//	@Excel(name = "是否删除 0否，1是", width = 15)
    @ApiModelProperty(value = "是否删除 0否，1是")
    private Integer delFlag;
	/**户编号*/
	@Excel(name = "户编号", width = 15)
    @ApiModelProperty(value = "户编号")
    private String familyNumber;
	/**户主*/
	@Excel(name = "户主", width = 15,dictTable = "qfy_family_member", dicCode = "id", dicText = "name")
    @ApiModelProperty(value = "户主")
    @Dict(dictTable = "qfy_family_member", dicCode = "id_card", dicText = "name")
    private String familyManager;
	/**行政村*/
	@Excel(name = "行政村", width = 15,dictTable = "qfy_sys_organization", dicCode = "code", dicText = "name")
    @ApiModelProperty(value = "行政村")
    @Dict(dictTable = "qfy_sys_organization", dicCode = "code", dicText = "name")
    private String village;
	/**片区*/
	@Excel(name = "片区", width = 15,dictTable = "qfy_sys_organization", dicCode = "code", dicText = "name")
    @ApiModelProperty(value = "自然屯")
    @Dict(dictTable = "qfy_sys_organization", dicCode = "code", dicText = "name")
    private String naturalTun;
	/**村民小组/生产队*/
	@Dict(dictTable = "qfy_sys_organization", dicCode = "code", dicText = "name")
	@Excel(name = "自然屯", width = 15,dictTable = "qfy_sys_organization", dicCode = "code", dicText = "name")
    @ApiModelProperty(value = "村民小组/生产队")
    private String team;
	/**积分模板id*/
//	@Excel(name = "积分模板", width = 15,dictTable = "qfy_check_template", dicCode = "id", dicText = "name")
//    @ApiModelProperty(value = "积分模板id")
    @Dict(dictTable = "qfy_check_template", dicCode = "id", dicText = "name")
    private String templateId;
	/**数据年份*/
	@Excel(name = "年份", width = 15)
    @ApiModelProperty(value = "数据年份")
    private String dataYear;
	/**基础分*/
	@Excel(name = "基础分", width = 15)
    @ApiModelProperty(value = "基础分")
    private Float base;
	/**总积分*/
	@Excel(name = "总积分", width = 15,isColumnHidden = true)
    @ApiModelProperty(value = "总积分")
    private Float total;

    /** 是否已盘点 0：未盘点，1：已盘点*/
    @Dict(dicCode = "yn")
    private Integer isStockTake;

	/**户主姓名*/
//	@Excel(name = "预留字段1", width = 15)
//    @ApiModelProperty(value = "户主姓名")
    private String ext1;
	/**预留字段2*/
//	@Excel(name = "预留字段2", width = 15)
    @ApiModelProperty(value = "预留字段2")
    private String ext2;
	/**预留字段3*/
//	@Excel(name = "预留字段3", width = 15)
    @ApiModelProperty(value = "预留字段3")
    private String ext3;

    /**备注*/
    @Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
    private String remarks;

    @TableField(exist = false)
    private List<CheckItemGroup> groupList;
}
