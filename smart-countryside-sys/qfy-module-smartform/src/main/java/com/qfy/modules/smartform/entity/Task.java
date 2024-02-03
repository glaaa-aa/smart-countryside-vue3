package com.qfy.modules.smartform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.qfy.modules.commons.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Description: 定时任务
 * @Author: jeecg-boot
 * @Date:   2022-04-08
 * @Version: V1.0
 */
@Data
@TableName("qfy_task")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="qfy_task对象", description="定时任务")
public class Task extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
    private java.lang.Integer id;
	/**检查点记录ID*/
	@Excel(name = "检查点记录ID", width = 15)
    @ApiModelProperty(value = "检查点记录ID")
    private java.lang.Integer inspecPoiId;
	/**任务名称*/
	@Excel(name = "任务名称", width = 15)
    @ApiModelProperty(value = "任务名称")
    private java.lang.String name;
	/**处理状态*/
	@Excel(name = "处理状态", width = 15)
    @ApiModelProperty(value = "处理状态")
    private java.lang.Integer checkStep;
	/**完成时间*/
	@Excel(name = "完成时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "完成时间")
    private java.util.Date completiontime;
	/**责任人*/
	@Excel(name = "责任人", width = 15)
    @ApiModelProperty(value = "责任人")
    private java.lang.String reviewer;
	/**期望完成时间*/
	@Excel(name = "期望完成时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "期望完成时间")
    private java.util.Date expecttime;
	/**提醒时间*/
	@Excel(name = "提醒时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "提醒时间")
    private java.util.Date remindtime;
	/**是否如期*/
	@Excel(name = "是否如期", width = 15)
    @ApiModelProperty(value = "是否如期")
    private java.lang.Integer isSchedule;
	/**任务内容*/
	@Excel(name = "任务内容", width = 15)
    @ApiModelProperty(value = "任务内容")
    private java.lang.String taskcontent;
	/**任务结果*/
	@Excel(name = "任务结果", width = 15)
    @ApiModelProperty(value = "任务结果")
    private java.lang.String results;
	/**确认人*/
	@Excel(name = "确认人", width = 15)
    @ApiModelProperty(value = "确认人")
    private java.lang.String confirmuser;
	/**最后一次  确认时间*/
	@Excel(name = "最后一次  确认时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "最后一次  确认时间")
    private java.util.Date confirmdate;
	/**图片集合*/
	@Excel(name = "图片集合", width = 15)
    @ApiModelProperty(value = "图片集合")
    private java.lang.String attachId;
	/**createAttachId*/
	@Excel(name = "createAttachId", width = 15)
    @ApiModelProperty(value = "createAttachId")
    private java.lang.String createAttachId;
	/**任务类型*/
	@Excel(name = "任务类型", width = 15)
    @ApiModelProperty(value = "任务类型")
    private java.lang.String tasktype;
	/**设备号*/
	@Excel(name = "设备号", width = 15)
    @ApiModelProperty(value = "设备号")
    private java.lang.String serialno;
}
