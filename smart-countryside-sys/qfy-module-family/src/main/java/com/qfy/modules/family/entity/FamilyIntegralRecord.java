package com.qfy.modules.family.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecg.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Description: 农户积分申请记录
 * @Author: jeecg-boot
 * @Date:   2022-12-01
 * @Version: V1.0
 */
@Data
@TableName("qfy_family_integral_record")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="qfy_family_integral_record对象", description="农户积分申请记录")
public class FamilyIntegralRecord implements Serializable {
    private static final long serialVersionUID = 1L;
    /** 审核通过 */
    public static final int PASS = 1;
    /** 已拒绝 */
    public static final int REJECT = 2;
    /** 撤销 */
    public static final int CANCEL = 3;

	/**编码id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "编码id")
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
	@Excel(name = "户主", width = 15,dictTable = "qfy_family_member", dicCode = "id_card", dicText = "name")
    @ApiModelProperty(value = "户主")
    @Dict(dictTable = "qfy_family_member", dicCode = "id_card", dicText = "name")
    private String familyManager;
	/**行政村*/
	@Excel(name = "行政村", width = 15,dictTable = "qfy_sys_organization", dicCode = "code", dicText = "name")
    @ApiModelProperty(value = "行政村")
    @Dict(dictTable = "qfy_sys_organization", dicCode = "code", dicText = "name")
    private String village;
	/**自然屯*/
	@Excel(name = "片区", width = 15,dictTable = "qfy_sys_organization", dicCode = "code", dicText = "name")
    @ApiModelProperty(value = "自然屯")
    @Dict(dictTable = "qfy_sys_organization", dicCode = "code", dicText = "name")
    private String naturalTun;
	/**村民小组/生产队*/
	@Excel(name = "自然屯", width = 15,dictTable = "qfy_sys_organization", dicCode = "code", dicText = "name")
    @ApiModelProperty(value = "村民小组/生产队")
    @Dict(dictTable = "qfy_sys_organization", dicCode = "code", dicText = "name")
    private String team;
	/**积分项id（加分项）*/
	@Excel(name = "加分项", width = 15, dictTable = "qfy_check_item", dicCode = "id", dicText = "name")
    @ApiModelProperty(value = "积分项id（加分项）")
    @Dict(dictTable = "qfy_check_item", dicCode = "id", dicText = "name")
    private String chkidPlus;
	/**加分事项*/
	@Excel(name = "加分事项", width = 30)
    @ApiModelProperty(value = "加分事项")
    private String itemPlus;
	/**加分分值*/
	@Excel(name = "加分分值", width = 15)
    @ApiModelProperty(value = "加分分值")
    private Float scorePlus;
	/**积分项id（减分项）*/
	@Excel(name = "减分项", width = 15, dictTable = "qfy_check_item", dicCode = "id", dicText = "name")
    @ApiModelProperty(value = "积分项id（减分项）")
    @Dict(dictTable = "qfy_check_item", dicCode = "id", dicText = "name")
    private String chkidReduce;
	/**减分事项*/
	@Excel(name = "减分事项", width = 30)
    @ApiModelProperty(value = "减分事项")
    private String itemReduce;
	/**减分分值*/
	@Excel(name = "减分分值", width = 15)
    @ApiModelProperty(value = "减分分值")
    private Float scoreReduce;
	/**审核状态 0待审核，1审核通过，2已撤销*/
//	@Excel(name = "审核状态", width = 15,dicCode = "integral_check_state")
    @ApiModelProperty(value = "审核状态 0待审核，1审核通过，2已拒绝，3已撤销")
    @Dict(dicCode = "integral_check_state")
    private Integer state;
	/**审核时间*/
//	@Excel(name = "审核时间", width = 15, format = "yyyy-MM-dd HH:mm")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
    @ApiModelProperty(value = "审核时间")
    private Date checkTime;
	/**审核人*/
//	@Excel(name = "审核人", width = 15)
    @ApiModelProperty(value = "审核人")
    @Dict(dictTable = "sys_user", dicCode = "username", dicText = "realname")
    private String checker;
	/**审核意见*/
//	@Excel(name = "审核意见", width = 15)
    @ApiModelProperty(value = "审核意见")
    private String checkRemarks;

    /**撤销时间*/
//	@Excel(name = "审核时间", width = 15, format = "yyyy-MM-dd HH:mm")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
    @ApiModelProperty(value = "撤销时间")
    private Date cancelTime;
    /**撤销人*/
//	@Excel(name = "撤销人", width = 15)
    @ApiModelProperty(value = "撤销人")
    @Dict(dictTable = "sys_user", dicCode = "username", dicText = "realname")
    private String cancelOperator;
    /**撤销原因*/
//	@Excel(name = "撤销原因", width = 15)
    @ApiModelProperty(value = "撤销原因")
    private String cancelRemarks;

	/**是否扣除基础分,0否，1是*/
//	@Excel(name = "是否扣除基础分", width = 15,dicCode = "yn")
//    @ApiModelProperty(value = "是否扣除基础分,0否，1是")
    @Dict(dicCode = "yn")
    private Integer baseReduce = 0;

    /**是否当年积分清零,0否，1是*/
//    @Excel(name = "当年积分清零", width = 15,dicCode = "yn")
    @Dict(dicCode = "yn")
    private Integer totalClear = 0;

    /** 数据年份 */
    @Excel(name = "数据年份", width = 15,dicCode = "family_year")
    @Dict(dicCode = "family_year")
    private String dataYear;

    /**备注*/
    @Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
    private String remarks;
	/** 户主姓名 */
//	@Excel(name = "预留扩展字段1", width = 15)
    @ApiModelProperty(value = "预留扩展字段1")
    private String ext1;
	/** 附件文件相对路径 */
//	@Excel(name = "预留扩展字段2", width = 15)
    @ApiModelProperty(value = "预留扩展字段2")
    private String ext2;
	/** 评分类别 1：加分；2：扣分 */
//	@Excel(name = "预留扩展字段3", width = 15)
    @ApiModelProperty(value = "预留扩展字段3")
    private String ext3;
}
