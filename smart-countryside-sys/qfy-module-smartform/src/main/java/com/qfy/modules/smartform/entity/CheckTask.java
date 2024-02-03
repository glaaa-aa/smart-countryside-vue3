package com.qfy.modules.smartform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Description: 巡检任务表
 * @Author: jeecg-boot
 * @Date:   2022-04-08
 * @Version: V1.0
 */
@Data
@TableName("qfy_check_task")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="qfy_check_task对象", description="巡检任务表")
public class CheckTask implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
    private java.lang.String id;
	/**创建人名称*/
	@Excel(name = "创建人名称", width = 15)
    @ApiModelProperty(value = "创建人名称")
    private java.lang.String createName;
	/**创建人登录名称*/
    @ApiModelProperty(value = "创建人登录名称")
    private java.lang.String createBy;
	/**创建日期*/
	@Excel(name = "创建日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建日期")
    private java.util.Date createTime;
	/**更新人名称*/
	@Excel(name = "更新人名称", width = 15)
    @ApiModelProperty(value = "更新人名称")
    private java.lang.String updateName;
	/**更新人登录名称*/
    @ApiModelProperty(value = "更新人登录名称")
    private java.lang.String updateBy;
	/**更新日期*/
	@Excel(name = "更新日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "更新日期")
    private java.util.Date updateTime;
	/**所属部门*/
    @ApiModelProperty(value = "所属部门")
    private java.lang.String sysOrgCode;
	/**所属公司*/
	@Excel(name = "所属公司", width = 15)
    @ApiModelProperty(value = "所属公司")
    private java.lang.String sysCompanyCode;
	/**计划执行日期*/
	@Excel(name = "计划执行日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "计划执行日期")
    private java.util.Date planDate;
	/**计划执行时间段*/
	@Excel(name = "计划执行时间段", width = 15)
    @ApiModelProperty(value = "计划执行时间段")
    private java.lang.String planTime;
	/**所属计划*/
	@Excel(name = "所属计划", width = 15)
    @ApiModelProperty(value = "所属计划")
    private java.lang.String chkPlanId;
	/**负责人或部门*/
	@Excel(name = "负责人或部门", width = 15)
    @ApiModelProperty(value = "负责人或部门")
    private java.lang.String depid;
	/**所属路线*/
	@Excel(name = "所属路线", width = 15)
    @ApiModelProperty(value = "所属路线")
    private java.lang.String chkRouteId;
	/**任务状态*/
	@Excel(name = "任务状态", width = 15)
    @ApiModelProperty(value = "任务状态")
    private java.lang.String state;
	/**任务处理人*/
	@Excel(name = "任务处理人", width = 15)
    @ApiModelProperty(value = "任务处理人")
    private java.lang.String handle;
	/**应检点数*/
	@Excel(name = "应检点数", width = 15)
    @ApiModelProperty(value = "应检点数")
    private java.lang.String planPoints;
	/**实检点数*/
	@Excel(name = "实检点数", width = 15)
    @ApiModelProperty(value = "实检点数")
    private java.lang.String checkedPoints;
	/**执行结果*/
	@Excel(name = "执行结果", width = 15)
    @ApiModelProperty(value = "执行结果")
    private java.lang.String handleResult;
}
