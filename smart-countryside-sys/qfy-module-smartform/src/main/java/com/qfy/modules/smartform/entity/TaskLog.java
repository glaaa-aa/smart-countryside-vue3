package com.qfy.modules.smartform.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: 任务日志
 * @Author: jeecg-boot
 * @Date:   2022-04-08
 * @Version: V1.0
 */
@Data
@TableName("qfy_task_log")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="qfy_task_log对象", description="任务日志")
public class TaskLog implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
    private java.lang.Integer id;
	/**创建人姓名*/
	@Excel(name = "创建人姓名", width = 15)
    @ApiModelProperty(value = "创建人姓名")
    private java.lang.String createName;
	/**创建人登录名称*/
    @ApiModelProperty(value = "创建人登录名称")
    private java.lang.String createBy;
	/**创建时间*/
	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
    private java.util.Date createDate;
	/**更新人名称*/
	@Excel(name = "更新人名称", width = 15)
    @ApiModelProperty(value = "更新人名称")
    private java.lang.String updateName;
	/**更新人登录名称*/
    @ApiModelProperty(value = "更新人登录名称")
    private java.lang.String updateBy;
	/**更新时间*/
	@Excel(name = "更新时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "更新时间")
    private java.util.Date updateDate;
	/**createAttachId*/
	@Excel(name = "createAttachId", width = 15)
    @ApiModelProperty(value = "createAttachId")
    private java.lang.String createAttachId;
	/**任务类型*/
	@Excel(name = "任务类型", width = 15)
    @ApiModelProperty(value = "任务类型")
    private java.lang.Integer taskId;
	/**处理状态 1 待分配 2 处理中 3 拒绝返回 4 处理完毕 5 再次提交 6 再次审核*/
	@Excel(name = "处理状态 1 待分配 2 处理中 3 拒绝返回 4 处理完毕 5 再次提交 6 再次审核", width = 15)
    @ApiModelProperty(value = "处理状态 1 待分配 2 处理中 3 拒绝返回 4 处理完毕 5 再次提交 6 再次审核")
    private java.lang.Integer checkStep;
	/**任务内容*/
	@Excel(name = "任务内容", width = 15)
    @ApiModelProperty(value = "任务内容")
    private java.lang.String taskcontent;
}
