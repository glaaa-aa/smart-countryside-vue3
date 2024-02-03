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
 * @Description: 维修人员工时
 * @Author: jeecg-boot
 * @Date:   2021-11-02
 * @Version: V1.0
 */
@Data
@TableName("q_andon_repair_mo_man")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="q_repair_mo_man对象", description="维修人员工时")
public class RepairMoMan {

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
	/**维修单编号*/
	@Excel(name = "维修单编号", width = 15)
    @ApiModelProperty(value = "维修单编号")
	private java.lang.String moCode;
	/**维修组 责任部门*/
	@Excel(name = "维修组 责任部门", width = 15)
    @ApiModelProperty(value = "维修组 责任部门")
	private java.lang.String dutyDepart;
	/**dutyManId*/
	@Excel(name = "dutyManId", width = 15)
    @ApiModelProperty(value = "dutyManId")
	private java.lang.String dutyManId;
	/**维修人 责任人*/
	@Excel(name = "维修人 责任人", width = 15)
    @ApiModelProperty(value = "维修人 责任人")
	private java.lang.String dutyMan;
	/**维修开始时间*/
	@Excel(name = "维修开始时间", width = 15)
    @ApiModelProperty(value = "维修开始时间")
	private java.lang.String weixStdate;
	/**维修完成时间  */
	@Excel(name = "维修完成时间  ", width = 15)
    @ApiModelProperty(value = "维修完成时间  ")
	private java.lang.String weixEddate;
	/**维修用时(分钟) 完成时间-开始时间*/
	@Excel(name = "维修用时(分钟) 完成时间-开始时间", width = 15)
    @ApiModelProperty(value = "维修用时(分钟) 完成时间-开始时间")
	private java.lang.Integer weixTimes;
	/**实际维修工时 独立核算时，可能用得到*/
	@Excel(name = "实际维修工时 独立核算时，可能用得到", width = 15)
    @ApiModelProperty(value = "实际维修工时 独立核算时，可能用得到")
	private java.lang.Integer stopTimes;
	/**维修费用 如委外或独立核算时，可能需要计算费用*/
	@Excel(name = "维修费用 如委外或独立核算时，可能需要计算费用", width = 15)
    @ApiModelProperty(value = "维修费用 如委外或独立核算时，可能需要计算费用")
	private java.lang.Double weixAmount;
	/**内容或步骤 该人维修动作的内容或步骤*/
	@Excel(name = "内容或步骤 该人维修动作的内容或步骤", width = 15)
    @ApiModelProperty(value = "内容或步骤 该人维修动作的内容或步骤")
	private java.lang.String weixContent;
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
