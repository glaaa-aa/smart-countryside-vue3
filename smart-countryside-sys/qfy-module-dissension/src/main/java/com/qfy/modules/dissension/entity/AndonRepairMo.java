package com.qfy.modules.dissension.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableLogic;
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
 * @Description: 维修工单表
 * @Author: jeecg-boot
 * @Date:   2024-01-29
 * @Version: V1.0
 */
@Data
@TableName("qfy_andon_repair_mo")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="qfy_andon_repair_mo对象", description="维修工单表")
public class AndonRepairMo implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private String id;
	/**所属部门*/
    @ApiModelProperty(value = "所属部门")
    private String sysOrgCode;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
    private String remarks;
	/**目标类型*/
	@Excel(name = "目标类型", width = 15)
    @ApiModelProperty(value = "目标类型")
    private String catalog;
	/**来源单据类别*/
	@Excel(name = "来源单据类别", width = 15)
    @ApiModelProperty(value = "来源单据类别")
    private String srcDocType;
	/**来源单据编号*/
	@Excel(name = "来源单据编号", width = 15)
    @ApiModelProperty(value = "来源单据编号")
    private String srcDocCode;
	/**纠纷编号*/
	@Excel(name = "纠纷编号", width = 15)
    @ApiModelProperty(value = "纠纷编号")
    private String moCode;
	/**设备单号*/
	@Excel(name = "设备单号", width = 15)
    @ApiModelProperty(value = "设备单号")
    private String deviceno;
	/**设备名称*/
	@Excel(name = "设备名称", width = 15)
    @ApiModelProperty(value = "设备名称")
    private String devName;
	/**工作中心名称*/
	@Excel(name = "工作中心名称", width = 15)
    @ApiModelProperty(value = "工作中心名称")
    private String wcName;
	/**工作中心*/
	@Excel(name = "工作中心", width = 15)
    @ApiModelProperty(value = "工作中心")
    private String wcCode;
	/**设备存放位置*/
	@Excel(name = "设备存放位置", width = 15)
    @ApiModelProperty(value = "设备存放位置")
    private String location;
	/**发生时间*/
	@Excel(name = "发生时间", width = 15)
    @ApiModelProperty(value = "发生时间")
    private String recDate;
	/**主要负责人*/
	@Excel(name = "主要负责人", width = 15)
    @ApiModelProperty(value = "主要负责人")
    private String notifier;
	/**主要负责人姓名*/
	@Excel(name = "主要负责人姓名", width = 15)
    @ApiModelProperty(value = "主要负责人姓名")
    private String notifierName;
	/**负责人电话*/
	@Excel(name = "负责人电话", width = 15)
    @ApiModelProperty(value = "负责人电话")
    private String notifierPhone;
	/**部门*/
	@Excel(name = "部门", width = 15)
    @ApiModelProperty(value = "部门")
    private String noticeDepart;
	/**故障等级*/
	@Excel(name = "故障等级", width = 15)
    @ApiModelProperty(value = "故障等级")
    private String abnormalLevel;
	/**故障类型名称*/
	@Excel(name = "故障类型名称", width = 15)
    @ApiModelProperty(value = "故障类型名称")
    private String abnormalName;
	/**故障类型*/
	@Excel(name = "故障类型", width = 15)
    @ApiModelProperty(value = "故障类型")
    private String abnormalCode;
	/**故障部位*/
	@Excel(name = "故障部位", width = 15)
    @ApiModelProperty(value = "故障部位")
    private String happenPos;
	/**故障原因*/
	@Excel(name = "故障原因", width = 15)
    @ApiModelProperty(value = "故障原因")
    private String faultCode;
	/**维修组 责任部门*/
	@Excel(name = "维修组 责任部门", width = 15)
    @ApiModelProperty(value = "维修组 责任部门")
    private String dutyDepart;
	/**填报人员*/
	@Excel(name = "填报人员", width = 15)
    @ApiModelProperty(value = "填报人员")
    private String dutyMan;
	/**填报人员uid*/
	@Excel(name = "填报人员uid", width = 15)
    @ApiModelProperty(value = "填报人员uid")
    private String dutyManId;
	/**紧急程度 紧急、高、中、低*/
	@Excel(name = "紧急程度 紧急、高、中、低", width = 15)
    @ApiModelProperty(value = "紧急程度 紧急、高、中、低")
    @Dict(dicCode = "urgency")
    private String criticalLevel;
	/**是否停机*/
	@Excel(name = "是否停机", width = 15)
    @ApiModelProperty(value = "是否停机")
    private String beStop;
	/**故障描述*/
	@Excel(name = "故障描述", width = 15)
    @ApiModelProperty(value = "故障描述")
    private String abnContent;
	/**要求截止时间*/
	@Excel(name = "要求截止时间", width = 15)
    @ApiModelProperty(value = "要求截止时间")
    private String resolutionTime;
	/**上传照片*/
	@Excel(name = "上传照片", width = 15)
    @ApiModelProperty(value = "上传照片")
    private String abnFiles;
	/**审批用户*/
	@Excel(name = "审批用户", width = 15)
    @ApiModelProperty(value = "审批用户")
    private String shenpMan;
	/**审批用户*/
	@Excel(name = "审批用户", width = 15)
    @ApiModelProperty(value = "审批用户")
    private String shenpManId;
	/**审批截止时间*/
	@Excel(name = "审批截止时间", width = 15)
    @ApiModelProperty(value = "审批截止时间")
    private String shenpjzTime;
	/**调解时间*/
	@Excel(name = "调解时间", width = 15)
    @ApiModelProperty(value = "调节时间")
    private Date shenpTime;
	/**审批内容*/
	@Excel(name = "审批内容", width = 15)
    @ApiModelProperty(value = "审批内容")
    private String shenpContent;
    /**处理结果*/
    @Excel(name = "处理结果", width = 15)
    @ApiModelProperty(value = "处理结果")
    private String shenpResult;
	/**处理状态*/
	@Excel(name = "处理状态", width = 15)
    @ApiModelProperty(value = "处理状态")
    @Dict(dicCode = "disputes_state")
    private String weixState;
	/**紧急程度*/
	@Excel(name = "紧急程度", width = 15)
    @ApiModelProperty(value = "紧急程度")
    private String weixLevel;
	/**达成协议*/
	@Excel(name = "达成协议", width = 15)
    @ApiModelProperty(value = "达成协议")
    @Dict(dicCode = "protocol_state")
    private String weixType;
	/**维修开始时间*/
	@Excel(name = "维修开始时间", width = 15)
    @ApiModelProperty(value = "维修开始时间")
    private String weixStdate;
	/**维修完成时间*/
	@Excel(name = "维修完成时间", width = 15)
    @ApiModelProperty(value = "维修完成时间")
    private String weixEddate;
	/**维修时长(分钟) 完成时间-开始时间*/
	@Excel(name = "维修时长(分钟) 完成时间-开始时间", width = 15)
    @ApiModelProperty(value = "维修时长(分钟) 完成时间-开始时间")
    private Integer weixTimes;
	/**停机时长(分钟)*/
	@Excel(name = "停机时长(分钟)", width = 15)
    @ApiModelProperty(value = "停机时长(分钟)")
    private Integer stopTimes;
	/**维修费用*/
	@Excel(name = "维修费用", width = 15)
    @ApiModelProperty(value = "维修费用")
    private Double weixAmount;
	/**维修执行关联附件列表*/
	@Excel(name = "维修执行关联附件列表", width = 15)
    @ApiModelProperty(value = "维修执行关联附件列表")
    private String weixFiles;
	/**保修期限*/
	@Excel(name = "保修期限", width = 15)
    @ApiModelProperty(value = "保修期限")
    private Integer baoxItemLimit;
	/**维修工作描述*/
	@Excel(name = "维修工作描述", width = 15)
    @ApiModelProperty(value = "维修工作描述")
    private String weixContent;
	/**工单状态*/
	@Excel(name = "工单状态", width = 15)
    @ApiModelProperty(value = "工单状态")
    private Integer moState;
	/**是否委外*/
	@Excel(name = "是否委外", width = 15)
    @ApiModelProperty(value = "是否委外")
    private Integer isOuts;
	/**委外企业*/
	@Excel(name = "委外企业", width = 15)
    @ApiModelProperty(value = "委外企业")
    private String outsName;
	/**委外企业id*/
	@Excel(name = "委外企业id", width = 15)
    @ApiModelProperty(value = "委外企业id")
    private String outsId;
	/**委外企业名称*/
	@Excel(name = "委外企业名称", width = 15)
    @ApiModelProperty(value = "委外企业名称")
    private String outsTel;
	/**工单是否完结*/
	@Excel(name = "工单是否完结", width = 15)
    @ApiModelProperty(value = "工单是否完结")
    private Integer isFinish;
	/**实付费用*/
	@Excel(name = "实付费用", width = 15)
    @ApiModelProperty(value = "实付费用")
    private Double shifAmount;
	/**零件费用*/
	@Excel(name = "零件费用", width = 15)
    @ApiModelProperty(value = "零件费用")
    private Double partsFee;
	/**外协费用*/
	@Excel(name = "外协费用", width = 15)
    @ApiModelProperty(value = "外协费用")
    private Double assistFee;
	/**其他费用*/
	@Excel(name = "其他费用", width = 15)
    @ApiModelProperty(value = "其他费用")
    private Double otherFee;
	/**s1*/
	@Excel(name = "参与人员", width = 15)
    @ApiModelProperty(value = "参与人员")
    private String s1;
	/**调解次数*/
	@Excel(name = "调解次数", width = 15)
    @ApiModelProperty(value = "调解次数")
    private String s2;
	/**s3*/
	@Excel(name = "s3", width = 15)
    @ApiModelProperty(value = "s3")
    private String s3;
	/**s4*/
	@Excel(name = "s4", width = 15)
    @ApiModelProperty(value = "s4")
    private String s4;
	/**s5*/
	@Excel(name = "s5", width = 15)
    @ApiModelProperty(value = "s5")
    private String s5;
	/**i1*/
	@Excel(name = "i1", width = 15)
    @ApiModelProperty(value = "i1")
    private Integer i1;
	/**i2*/
	@Excel(name = "i2", width = 15)
    @ApiModelProperty(value = "i2")
    private Integer i2;
	/**i3*/
	@Excel(name = "i3", width = 15)
    @ApiModelProperty(value = "i3")
    private Integer i3;
	/**d1*/
	@Excel(name = "d1", width = 15)
    @ApiModelProperty(value = "d1")
    private Double d1;
	/**d2*/
	@Excel(name = "d2", width = 15)
    @ApiModelProperty(value = "d2")
    private Double d2;
	/**d3*/
	@Excel(name = "d3", width = 15)
    @ApiModelProperty(value = "d3")
    private Double d3;
	/**创建人*/
    @ApiModelProperty(value = "创建人")
    private String createBy;
	/**创建时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
	/**修改人*/
    @ApiModelProperty(value = "修改人")
    private String updateBy;
	/**修改时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;
	/**0表示未删除,1表示删除*/
	@Excel(name = "0表示未删除,1表示删除", width = 15)
    @ApiModelProperty(value = "0表示未删除,1表示删除")
    @TableLogic
    private String delFlag;
	/**签到人*/
	@Excel(name = "签到人", width = 15)
    @ApiModelProperty(value = "签到人")
    private String signManId;
	/**签到人姓名*/
	@Excel(name = "签到人姓名", width = 15)
    @ApiModelProperty(value = "签到人姓名")
    private String signMan;
	/**响应截止时间*/
	@Excel(name = "响应截止时间", width = 15)
    @ApiModelProperty(value = "响应截止时间")
    private String signTime;
}
