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
 * @Description: 单据管理表
 * @Author: jeecg-boot
 * @Date:   2023-06-09
 * @Version: V1.0
 */
@Data
@TableName("qfy_base_danju_rule")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="qfy_base_danju_rule对象", description="单据管理表")
public class DanjuRule implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键ID")
    private String id;
	/**规则名称*/
	@Excel(name = "规则名称", width = 15)
    @ApiModelProperty(value = "规则名称")
    private String ruleName;
	/**规则Code 单据类型*/
	@Excel(name = "规则Code 单据类型", width = 15)
    @ApiModelProperty(value = "规则Code 单据类型")
    private String ruleType;
	/**单据前缀*/
	@Excel(name = "单据前缀", width = 15)
    @ApiModelProperty(value = "单据前缀")
    private String rulePrefix;
	/**规则实现类*/
	@Excel(name = "规则实现类", width = 15)
    @ApiModelProperty(value = "规则实现类")
    private String ruleClass;
	/**规则参数*/
	@Excel(name = "规则参数", width = 15)
    @ApiModelProperty(value = "规则参数")
    private String ruleParams;
	/**单据格式*/
	@Excel(name = "单据格式", width = 15)
    @ApiModelProperty(value = "单据格式")
    private String ruleFormat;
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
	/**是否需要审核*/
	@Excel(name = "是否需要审核", width = 15)
    @ApiModelProperty(value = "是否需要审核")
    private Integer isSh;
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
