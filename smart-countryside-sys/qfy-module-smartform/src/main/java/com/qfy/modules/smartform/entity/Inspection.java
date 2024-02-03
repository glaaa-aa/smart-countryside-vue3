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
 * @Description: 巡检结果-主表
 * @Author: jeecg-boot
 * @Date:   2022-04-08
 * @Version: V1.0
 */
@Data
@TableName("qfy_inspection")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="qfy_inspection对象", description="巡检结果-主表")
public class Inspection extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "id")
    private java.lang.Integer id;
	/**检查表编号*/
	@Excel(name = "检查表编号", width = 15)
    @ApiModelProperty(value = "检查表编号")
    private java.lang.String sn;
	/**检查类型*/
	@Excel(name = "检查类型", width = 15)
    @ApiModelProperty(value = "检查类型")
    private java.lang.String inspectype;
	/**巡检计划*/
	@Excel(name = "巡检计划", width = 15)
    @ApiModelProperty(value = "巡检计划")
    private java.lang.Integer chkPlanId;
	/**巡检路线*/
	@Excel(name = "巡检路线", width = 15)
    @ApiModelProperty(value = "巡检路线")
    private java.lang.Integer chkRouteId;
	/**状态*/
	@Excel(name = "状态", width = 15)
    @ApiModelProperty(value = "状态")
    private java.lang.Integer state;
	/**最终提交时间*/
	@Excel(name = "最终提交时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "最终提交时间")
    private java.util.Date submitTime;
	/**当日次数*/
	@Excel(name = "当日次数", width = 15)
    @ApiModelProperty(value = "当日次数")
    private java.lang.Integer sortno;
	/**计划执行日期*/
	@Excel(name = "计划执行日期", width = 15)
    @ApiModelProperty(value = "计划执行日期")
    private java.lang.String planDate;
	/**是否如期*/
	@Excel(name = "是否如期", width = 15)
    @ApiModelProperty(value = "是否如期")
    private java.lang.Integer isSchedule;
	/**巡检点评分合计*/
	@Excel(name = "巡检点评分合计", width = 15)
    @ApiModelProperty(value = "巡检点评分合计")
    private java.lang.Double score;
}
