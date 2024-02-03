package com.qfy.modules.danju.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: 单据审批表
 * @Author: jeecg-boot
 * @Date:   2023-06-09
 * @Version: V1.0
 */
@Data
@TableName("qfy_base_danju_spjl")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="qfy_base_danju_spjl对象", description="单据审批表")
public class DanjuSpjl implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
    private String id;
	/**业务ID*/
	@Excel(name = "业务ID", width = 15)
    @ApiModelProperty(value = "业务ID")
    private String ywid;
	/**业务类型*/
	@Excel(name = "业务类型", width = 15)
    @ApiModelProperty(value = "业务类型")
    private String ywlx;
	/**业务名称*/
	@Excel(name = "业务名称", width = 15)
    @ApiModelProperty(value = "业务名称")
    private String ywname;
	/**审批日期*/
	@Excel(name = "审批日期", width = 15)
    @ApiModelProperty(value = "审批日期")
    private String sptime;
	/**审批结果*/
	@Excel(name = "审批结果", width = 15)
    @ApiModelProperty(value = "审批结果")
    private String spjg;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
    private String cdesc;
	/**骤步名称*/
	@Excel(name = "骤步名称", width = 15)
    @ApiModelProperty(value = "骤步名称")
    private String stepName;
	/**第N步*/
	@Excel(name = "第N步", width = 15)
    @ApiModelProperty(value = "第N步")
    private Integer step;
	/**第N步骤*/
	@Excel(name = "第N步骤", width = 15)
    @ApiModelProperty(value = "第N步骤")
    private Integer stepIndex;
	/**步骤状态：0未处理 1已处理*/
	@Excel(name = "步骤状态：0未处理 1已处理", width = 15)
    @ApiModelProperty(value = "步骤状态：0未处理 1已处理")
    private Integer status;
	/**0未提交 1通过 -1拒绝 9待处理 */
	@Excel(name = "0未提交 1通过 -1拒绝 9待处理 ", width = 15)
    @ApiModelProperty(value = "0未提交 1通过 -1拒绝 9待处理 ")
    private Integer ywzt;
	/**发起时间*/
	@Excel(name = "发起时间", width = 15)
    @ApiModelProperty(value = "发起时间")
    private String fqsj;
	/**务业审核部门*/
	@Excel(name = "务业审核部门", width = 15)
    @ApiModelProperty(value = "务业审核部门")
    private String orgCode;
	/**务业审核部门*/
	@Excel(name = "务业审核部门", width = 15)
    @ApiModelProperty(value = "务业审核部门")
    private String orgName;
	/**fqr*/
	@Excel(name = "fqr", width = 15)
    @ApiModelProperty(value = "fqr")
    private String fqr;
	/**发起人姓名*/
	@Excel(name = "发起人姓名", width = 15)
    @ApiModelProperty(value = "发起人姓名")
    private String fqrxm;
	/**是否指定审批人 0不指定 1指定*/
	@Excel(name = "是否指定审批人 0不指定 1指定", width = 15)
    @ApiModelProperty(value = "是否指定审批人 0不指定 1指定")
    private Integer isreadonly;
	/**审批模板 0模板 1审批记录*/
	@Excel(name = "审批模板 0模板 1审批记录", width = 15)
    @ApiModelProperty(value = "审批模板 0模板 1审批记录")
    private Integer spmb;
	/**审批人*/
	@Excel(name = "审批人", width = 15)
    @ApiModelProperty(value = "审批人")
    private String spr;
	/**审批人姓名*/
	@Excel(name = "审批人姓名", width = 15)
    @ApiModelProperty(value = "审批人姓名")
    private String sprxm;
	/**审批业务的具体名称*/
	@Excel(name = "审批业务的具体名称", width = 15)
    @ApiModelProperty(value = "审批业务的具体名称")
    private String ywTitle;
	/**流程结束*/
	@Excel(name = "流程结束", width = 15)
    @ApiModelProperty(value = "流程结束")
    private Integer finished;
	/**s1*/
	@Excel(name = "s1", width = 15)
    @ApiModelProperty(value = "s1")
    private String s1;
	/**s2*/
	@Excel(name = "s2", width = 15)
    @ApiModelProperty(value = "s2")
    private String s2;
	/**s3*/
	@Excel(name = "s3", width = 15)
    @ApiModelProperty(value = "s3")
    private String s3;
	/**s4*/
	@Excel(name = "s4", width = 15)
    @ApiModelProperty(value = "s4")
    private String s4;
	/**s5*/
	@Excel(name = "s5", width = 15)
    @ApiModelProperty(value = "s5")
    private String s5;
	/**i1*/
	@Excel(name = "i1", width = 15)
    @ApiModelProperty(value = "i1")
    private Integer i1;
	/**i2*/
	@Excel(name = "i2", width = 15)
    @ApiModelProperty(value = "i2")
    private Integer i2;
	/**i3*/
	@Excel(name = "i3", width = 15)
    @ApiModelProperty(value = "i3")
    private Integer i3;
	/**创建人*/
    @ApiModelProperty(value = "创建人")
    private String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建日期")
    private Date createTime;
	/**更新人*/
    @ApiModelProperty(value = "更新人")
    private String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "更新日期")
    private Date updateTime;
	/**所属部门*/
    @ApiModelProperty(value = "所属部门")
    private String sysOrgCode;
	/**删除标记*/
	@Excel(name = "删除标记", width = 15)
    @ApiModelProperty(value = "删除标记")
    private Integer delFlag;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
    private String remarks;
	/**租户*/
	@Excel(name = "租户", width = 15)
    @ApiModelProperty(value = "租户")
    private Integer tenantId;
}
