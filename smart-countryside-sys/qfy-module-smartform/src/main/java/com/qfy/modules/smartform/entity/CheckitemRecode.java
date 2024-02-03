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
 * @Description: 检查项记录
 * @Author: jeecg-boot
 * @Date:   2022-04-08
 * @Version: V1.0
 */
@Data
@TableName("qfy_checkitem_recode")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="qfy_checkitem_recode对象", description="检查项记录")
public class CheckitemRecode implements Serializable {
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
	/**流程状态*/
	@Excel(name = "流程状态", width = 15)
    @ApiModelProperty(value = "流程状态")
    private java.lang.String bpmStatus;
	/**检查点名称*/
	@Excel(name = "检查点名称", width = 15)
    @ApiModelProperty(value = "检查点名称")
    private java.lang.String name;
	/**任务编号*/
	@Excel(name = "任务编号", width = 15)
    @ApiModelProperty(value = "任务编号")
    private java.lang.String taskId;
	/**合格数量*/
	@Excel(name = "合格数量", width = 15)
    @ApiModelProperty(value = "合格数量")
    private java.lang.String qualified;
	/**不合格数量*/
	@Excel(name = "不合格数量", width = 15)
    @ApiModelProperty(value = "不合格数量")
    private java.lang.String unqualified;
}
