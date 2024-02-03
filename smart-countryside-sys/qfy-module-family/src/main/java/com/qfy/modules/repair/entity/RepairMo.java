package com.qfy.modules.repair.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.qfy.modules.commons.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecg.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @Description: 维修工单
 * @Author: jeecg-boot
 * @Date:   2021-11-02
 * @Version: V1.0
 */
@Data
@TableName("qfy_andon_repair_mo")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="q_repair_mo对象", description="维修工单")
public class  RepairMo  extends BaseEntity {

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
	private java.lang.String id;

	/**任务处理类型 1现场直接处理 2指派人员处理 */
//	@Excel(name = "目标类型", width = 15)
    @ApiModelProperty(value = "目标类型")
	private java.lang.String catalog;
	/**来源单据类别*/
//	@Excel(name = "来源单据类别", width = 15)
    @ApiModelProperty(value = "来源单据类别")
	@Dict(dicCode = "faultType")
	private java.lang.String srcDocType;
	/**来源单据编号*/
//	@Excel(name = "来源单据编号", width = 15)
    @ApiModelProperty(value = "来源单据编号")
	private java.lang.String srcDocCode;
	/**维修单号*/
	@Excel(name = "工单号", width = 15)
    @ApiModelProperty(value = "维修单号")
	private java.lang.String moCode;
	/**设备单号*/
//	@Excel(name = "设备单号", width = 15)
    @ApiModelProperty(value = "设备单号")
	private java.lang.String deviceno;
	/**设备名称*/
//	@Excel(name = "设备名称", width = 15)
    @ApiModelProperty(value = "设备名称")
	private java.lang.String devName;
	/**工作中心名称*/
//	@Excel(name = "工作中心名称", width = 15)
    @ApiModelProperty(value = "工作中心名称")
	private java.lang.String wcName;
	/**工作中心*/
//	@Excel(name = "工作中心", width = 15)
    @ApiModelProperty(value = "工作中心")
	private java.lang.String wcCode;
	/**设备存放位置*/
//	@Excel(name = "设备存放位置", width = 15)
    @ApiModelProperty(value = "设备存放位置")
	private java.lang.String location;
	/**发生时间*/
	@Excel(name = "上报时间", width = 15)
    @ApiModelProperty(value = "发生时间")
	private java.lang.String recDate;
	/**报障人*/
	@Excel(name = "上报人", width = 15, dictTable = "sys_user",dicCode = "username", dicText = "realname")
    @ApiModelProperty(value = "报障人")
	@Dict(dictTable = "sys_user",dicCode = "username", dicText = "realname")
	private java.lang.String notifier;
	/**报障人*/
//	@Excel(name = "报障人", width = 15)
    @ApiModelProperty(value = "报障人")
	private java.lang.String notifierName;
	@Excel(name = "报障人电话", width = 15)
	@ApiModelProperty(value = "报障人电话")
	private  java.lang.String notifierPhone;
	/**报障部门*/
//	@Excel(name = "报障部门", width = 15)
    @ApiModelProperty(value = "报障部门")
	private java.lang.String noticeDepart;
	/**故障等级*/
//	@Excel(name = "故障等级", width = 15)
    @ApiModelProperty(value = "故障等级")
	private java.lang.String abnormalLevel;
	/**故障类型名称*/
//	@Excel(name = "故障类型名称", width = 15)
    @ApiModelProperty(value = "故障类型名称")
	private java.lang.String abnormalName;
	/**故障类型*/
//	@Excel(name = "故障类型", width = 15)
    @ApiModelProperty(value = "故障类型")
	private java.lang.String abnormalCode;
	/**故障部位*/
//	@Excel(name = "故障部位", width = 15)
//	@Excel(name = "故障部位", width = 15)
    @ApiModelProperty(value = "故障部位")
	private java.lang.String happenPos;
	/**故障原因*/
	@Excel(name = "隐患信息", width = 15, dictTable = "qfy_andon_fault_cause", dicCode = "fault_code",
			dicText = "fault_resons")
    @ApiModelProperty(value = "故障原因")
	@Dict(dictTable = "qfy_andon_fault_cause", dicCode = "fault_code", dicText = "fault_resons")
	private java.lang.String faultCode;
	/**维修组 责任部门*/
//	@Excel(name = "维修组 责任部门", width = 15)
    @ApiModelProperty(value = "维修组 责任部门")
	private java.lang.String dutyDepart;
	/**维修人 责任人*/
//	@Excel(name = "维修人 责任人", width = 15)
    @ApiModelProperty(value = "维修人 责任人")
	private java.lang.String dutyMan;
	/**维修人*/
	@Excel(name = "责任人", width = 15,dictTable = "sys_user",dicCode = "username", dicText = "realname")
    @ApiModelProperty(value = "维修人")
	@Dict(dictTable = "sys_user",dicCode = "username", dicText = "realname")
	private java.lang.String dutyManId;
	/**紧急程度 紧急、高、中、低*/
//	@Excel(name = "紧急程度", width = 15)
    @ApiModelProperty(value = "紧急程度 紧急、高、中、低")
	private java.lang.String criticalLevel;
	/**是否停机*/
//	@Excel(name = "是否停机", width = 15)
    @ApiModelProperty(value = "是否停机")
	private java.lang.String beStop;
	/**故障描述*/
//	@Excel(name = "故障描述", width = 15)
    @ApiModelProperty(value = "故障描述")
	private java.lang.String abnContent;
	/**要求截止时间*/
	@Excel(name = "处理截止时间", width = 15)
    @ApiModelProperty(value = "要求截止时间")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
	private java.lang.String resolutionTime;
	/**关联附件列表*/
//	@Excel(name = "关联附件列表", width = 15)
    @ApiModelProperty(value = "关联附件列表")
	private java.lang.String abnFiles;
	/**审批用户*/
//	@Excel(name = "审批用户", width = 15)
    @ApiModelProperty(value = "审批用户")
	private java.lang.String shenpMan;
	/**审批用户*/
//	@Excel(name = "审批用户", width = 15)
    @ApiModelProperty(value = "审批用户")
	private java.lang.String shenpManId;
	/**审批时间*/
//	@Excel(name = "审批时间", width = 15)
    @ApiModelProperty(value = "审批时间")
	private java.lang.String shenpTime;

	//	@Excel(name = "审批时间", width = 15)
	@ApiModelProperty(value = "审批截止时间")
	private java.lang.String shenpjzTime;
	/**审批结果*/
//	@Excel(name = "审批结果", width = 15)
    @ApiModelProperty(value = "审批结果")
	private java.lang.String shenpResult;
	/**审批内容*/
//	@Excel(name = "审批内容", width = 15)
    @ApiModelProperty(value = "审批内容")
	private java.lang.String shenpContent;
	/**维修状态*/
//	@Excel(name = "维修状态", width = 15)
    @ApiModelProperty(value = "维修状态")
	private java.lang.String weixState;
	/**维修级别*/
//	@Excel(name = "维修级别", width = 15)
    @ApiModelProperty(value = "维修级别")
	private java.lang.String weixLevel;
	/**维修类别*/
//	@Excel(name = "维修类别", width = 15)
    @ApiModelProperty(value = "维修类别")
	private java.lang.String weixType;
	/**维修开始时间*/
//	@Excel(name = "维修开始时间", width = 15)
    @ApiModelProperty(value = "维修开始时间")
	private java.lang.String weixStdate;
	/**维修完成时间*/
//	@Excel(name = "维修完成时间", width = 15)
    @ApiModelProperty(value = "维修完成时间")
	private java.lang.String weixEddate;
	/**维修时长(分钟) 完成时间-开始时间*/
//	@Excel(name = "维修时长(分钟) 完成时间-开始时间", width = 15)
    @ApiModelProperty(value = "维修时长(分钟) 完成时间-开始时间")
	private java.lang.Integer weixTimes;
	/**停机时长(分钟)*/
//	@Excel(name = "停机时长(分钟)", width = 15)
    @ApiModelProperty(value = "停机时长(分钟)")
	private java.lang.Integer stopTimes;
	/**维修费用*/


//	@Excel(name = "维修费用", width = 15)
    @ApiModelProperty(value = "维修费用")
	private java.lang.Double weixAmount;
	/**维修执行关联附件列表*/
//	@Excel(name = "维修执行关联附件列表", width = 15)
    @ApiModelProperty(value = "维修执行关联附件列表")
	private java.lang.String weixFiles;
	/**保修期限*/
//	@Excel(name = "保修期限", width = 15)
    @ApiModelProperty(value = "保修期限")
	private java.lang.Integer baoxItemLimit;
	/**维修工作描述*/
//	@Excel(name = "维修工作描述", width = 15)
    @ApiModelProperty(value = "维修工作描述")
	private java.lang.String weixContent;
	/**工单状态*/
	@Excel(name = "工单状态", width = 15, dicCode = "mo_state")
    @ApiModelProperty(value = "工单状态")
	@Dict(dicCode = "mo_state")
	private java.lang.Integer moState;
	/**是否委外*/
//	@Excel(name = "是否委外", width = 15)
    @ApiModelProperty(value = "是否委外")
	private java.lang.Integer isOuts;
	/**委外企业*/
//	@Excel(name = "委外企业", width = 15)
    @ApiModelProperty(value = "委外企业")
	private java.lang.String outsName;
	/**委外企业id*/
//	@Excel(name = "委外企业id", width = 15)
    @ApiModelProperty(value = "委外企业id")
	private java.lang.String outsId;
	/**委外企业名称*/
//	@Excel(name = "委外企业名称", width = 15)
    @ApiModelProperty(value = "委外企业名称")
	private java.lang.String outsTel;
	/**工单是否完结*/
//	@Excel(name = "工单是否完结", width = 15)
    @ApiModelProperty(value = "工单是否完结")
	private java.lang.Integer isFinish;
	/**实付费用*/
//	@Excel(name = "实付费用", width = 15)
    @ApiModelProperty(value = "实付费用")
	private java.lang.Double shifAmount;
	/**零件费用*/
//	@Excel(name = "零件费用", width = 15)
    @ApiModelProperty(value = "零件费用")
	private java.lang.Double partsFee;
	/**外协费用*/
//	@Excel(name = "外协费用", width = 15)
    @ApiModelProperty(value = "外协费用")
	private java.lang.Double assistFee;
	/**其他费用*/
//	@Excel(name = "其他费用", width = 15)
    @ApiModelProperty(value = "其他费用")
	private java.lang.Double otherFee;

	//	@Excel(name = "联系电话", width = 15, orderNum = "5")
	@ApiModelProperty(value = "联系电话")
	private String signManId;
	/**车辆号牌*/
	@Excel(name = "车辆号牌", width = 15, orderNum = "4")
	@ApiModelProperty(value = "车辆号牌")
	private String signMan;
	/**s1*/
//	@Excel(name = "s1", width = 15)
    @ApiModelProperty(value = "s1")
	@Dict(dicCode = "record_table_type")
	private java.lang.String s1;
	/**s2*/
//	@Excel(name = "s2", width = 15)
    @ApiModelProperty(value = "s2")
	private java.lang.String s2;
	/**s3*/
//	@Excel(name = "s3", width = 15)
    @ApiModelProperty(value = "s3")
	private java.lang.String s3;
	/**s4*/
//	@Excel(name = "s4", width = 15)
    @ApiModelProperty(value = "s4")
	private java.lang.String s4;
	/**s5*/
//	@Excel(name = "奖励积分", width = 15)
    @ApiModelProperty(value = "奖励积分")
	private java.lang.String s5;
	/**i1*/
//	@Excel(name = "i1", width = 15)
    @ApiModelProperty(value = "i1")
	private java.lang.Integer i1;
	/**i2*/
//	@Excel(name = "i2", width = 15)
    @ApiModelProperty(value = "i2")
	private java.lang.Integer i2;
	/**i3*/
//	@Excel(name = "i3", width = 15)
    @ApiModelProperty(value = "i3")
	private java.lang.Integer i3;
	/**d1*/
//	@Excel(name = "d1", width = 15)
    @ApiModelProperty(value = "d1")
	private java.lang.Double d1;
	/**d2*/
//	@Excel(name = "d2", width = 15)
    @ApiModelProperty(value = "d2")
	private java.lang.Double d2;
	/**d3*/
//	@Excel(name = "d3", width = 15)
    @ApiModelProperty(value = "d3")
	private java.lang.Double d3;

	/**备注*/
//	@Excel(name = "备注", width = 15)
	@ApiModelProperty(value = "备注")
	private String remarks;

	@TableField(exist = false)
	private Integer maxJifen;

	@TableField(exist = false)
	private String[] abnFilesArr;
	public String[] getAbnFilesArr() {
		if(!StringUtils.isEmpty(abnFiles)){
			return abnFiles.split(",");
		}
		return null;
	}

	@TableField(exist = false)
	private String[] weixFilesArr;

	public String[] getWeixFilesArr() {
		if(!StringUtils.isEmpty(weixFiles)){
			return weixFiles.split(",");
		}
		return null;
	}

	@TableField(exist = false)
	private List<String> wcCodeList;

	/**综合查询参数*/
	@TableField(exist = false)
	private String queryParam;

	@TableField(exist = false)
	private String sbbeginTime;
	/**结束时间*/
	@TableField(exist = false)
	private String sbendTime;


	@TableField(exist = false)
	private String wcbeginTime;
	/**结束时间*/
	@TableField(exist = false)
	private String wcendTime;

}
