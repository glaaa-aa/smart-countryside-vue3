package com.qfy.modules.repair.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.qfy.modules.commons.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecg.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;

import java.io.Serializable;

/**
 * @Description: 维修工单日志
 * @Author: jeecg-boot
 * @Date:   2021-11-02
 * @Version: V1.0
 */
@Data
@TableName("qfy_andon_repair_mo_log")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="q_repair_mo_log对象", description="维修工单日志")
public class RepairMoLog extends BaseEntity implements Serializable {

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
	private java.lang.String id;

	/**维修单号*/
	@Excel(name = "维修单号", width = 15)
    @ApiModelProperty(value = "维修单号")
	private java.lang.String moCode;
	/**操作人*/
	@Excel(name = "操作人", width = 15)
    @ApiModelProperty(value = "操作人")
	private java.lang.String optId;
	/**操作人*/
	@Excel(name = "操作人", width = 15)
    @ApiModelProperty(value = "操作人")
	private java.lang.String optName;
	/**日志内容*/
	@Excel(name = "日志内容", width = 15)
    @ApiModelProperty(value = "日志内容")
	private java.lang.String optContent;
	/**日志类型*/
	@Excel(name = "日志类型", width = 15)
    @ApiModelProperty(value = "日志类型")
	@Dict(dicCode = "opt_type")
	private java.lang.String optType;
	/**参数*/
	@Excel(name = "参数", width = 15)
    @ApiModelProperty(value = "参数")
	private java.lang.Object optDataJson;

	@Excel(name = "结果", width = 15)
	@ApiModelProperty(value = "结果")
	private  String resultMsg;
	/**任务节点状态*/
	@Excel(name = "s1", width = 15)
    @ApiModelProperty(value = "s1")
	private java.lang.String s1;
	/**任务附件(图片)*/
	@Excel(name = "s2", width = 15)
    @ApiModelProperty(value = "s2")
	private java.lang.String s2;
	/** 驳回原因/拒绝原因/审核意见 */
	@Excel(name = "s3", width = 15)
    @ApiModelProperty(value = "s3")
	private java.lang.String s3;
	/**s4*/
	@Excel(name = "s4", width = 15)
    @ApiModelProperty(value = "s4")
	private java.lang.String s4;


}
