package com.qfy.modules.repair.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 维修备品更换
 * @Author: jeecg-boot
 * @Date:   2021-11-02
 * @Version: V1.0
 */
@Data
@TableName("q_andon_repair_mo_part_used")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="q_repair_mo_part_used对象", description="维修备品更换")
public class RepairMopartUsed {

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
	private java.lang.String id;
	/**所属部门*/
	@Excel(name = "所属部门", width = 15)
    @ApiModelProperty(value = "所属部门")
	private java.lang.String sysOrgCode;
	/**所属公司*/
	@Excel(name = "所属公司", width = 15)
    @ApiModelProperty(value = "所属公司")
	private java.lang.String sysCompanyCode;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private java.lang.String bz;
	/**任务类型 维修任务  或 保养任务*/
	@Excel(name = "任务类型 维修任务  或 保养任务", width = 15)
    @ApiModelProperty(value = "任务类型 维修任务  或 保养任务")
	private java.lang.String sendType;
	/**维修工单*/
	@Excel(name = "维修工单", width = 15)
    @ApiModelProperty(value = "维修工单")
	private java.lang.String moCode;
	/**备件号*/
	@Excel(name = "备件号", width = 15)
    @ApiModelProperty(value = "备件号")
	private java.lang.String partCode;
	/**备件名称*/
	@Excel(name = "备件名称", width = 15)
    @ApiModelProperty(value = "备件名称")
	private java.lang.String partName;
	/**备件型号/描述*/
	@Excel(name = "备件型号/描述", width = 15)
    @ApiModelProperty(value = "备件型号/描述")
	private java.lang.String partXinghao;
	/**备件规格型号*/
	@Excel(name = "备件规格型号", width = 15)
    @ApiModelProperty(value = "备件规格型号")
	private java.lang.String partSpec;
	/**使用数量*/
	@Excel(name = "使用数量", width = 15)
    @ApiModelProperty(value = "使用数量")
	private java.lang.Integer usedCount;
	/**使用单位*/
	@Excel(name = "使用单位", width = 15)
    @ApiModelProperty(value = "使用单位")
	private java.lang.String unit;
	/**申请数量*/
	@Excel(name = "申请数量", width = 15)
    @ApiModelProperty(value = "申请数量")
	private java.lang.Integer shenlCount;
	/**出库数量*/
	@Excel(name = "出库数量", width = 15)
    @ApiModelProperty(value = "出库数量")
	private java.lang.Integer chukCount;
	/**入库数量*/
	@Excel(name = "入库数量", width = 15)
    @ApiModelProperty(value = "入库数量")
	private java.lang.Integer rukCount;
	/**单价*/
	@Excel(name = "单价", width = 15)
    @ApiModelProperty(value = "单价")
	private java.lang.Double price;
	/**总金额*/
	@Excel(name = "总金额", width = 15)
    @ApiModelProperty(value = "总金额")
	private java.lang.Double tPrice;
	/**数量是否审核通过*/
	@Excel(name = "数量是否审核通过", width = 15)
    @ApiModelProperty(value = "数量是否审核通过")
	private java.lang.Integer isCheck;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
	private java.lang.String createBy;
	/**创建时间*/
	@Excel(name = "创建时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
	private java.util.Date createTime;
	/**修改人*/
	@Excel(name = "修改人", width = 15)
    @ApiModelProperty(value = "修改人")
	private java.lang.String updateBy;
	/**修改时间*/
	@Excel(name = "修改时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "修改时间")
	private java.util.Date updateTime;
	/**0表示未删除,1表示删除*/
	@Excel(name = "0表示未删除,1表示删除", width = 15)
    @ApiModelProperty(value = "0表示未删除,1表示删除")
	private java.lang.String delFlag;
}
