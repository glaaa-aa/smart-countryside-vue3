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
 * @Description: 维修工单记录表
 * @Author: jeecg-boot
 * @Date:   2024-01-30
 * @Version: V1.0
 */
@Data
@TableName("qfy_andon_repair_mo_log")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="qfy_andon_repair_mo_log对象", description="维修工单记录表")
public class AndonRepairMoLog implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private String id;
	/**纠纷编号*/
	@Excel(name = "纠纷编号", width = 15)
    @ApiModelProperty(value = "维修单号")
    private String moCode;
	/**处处治人员*/
	@Excel(name = "处处治人员", width = 15)
    @ApiModelProperty(value = "处处治人员")
    private String optId;
	/**处处治人员*/
	@Excel(name = "处处治人员", width = 15)
    @ApiModelProperty(value = "处处治人员")
    private String optName;
	/**日志内容*/
	@Excel(name = "日志内容", width = 15)
    @ApiModelProperty(value = "日志内容")
    private String optContent;
    /**处理时间*/
    @Excel(name = "处理时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "处理时间")
    private Date handleDate;
	/**参数*/
	@Excel(name = "参数", width = 15)
    @ApiModelProperty(value = "参数")
    private String optDataJson;
	/**反馈内容*/
	@Excel(name = "反馈内容", width = 15)
    @ApiModelProperty(value = "反馈内容")
    private String resultMsg;
	/**相关材料附件*/
	@Excel(name = "相关材料附件", width = 15)
    @ApiModelProperty(value = "相关材料附件")
    private String s1;
	/**扩展字段2*/
	@Excel(name = "扩展字段2", width = 15)
    @ApiModelProperty(value = "扩展字段2")
    private String s2;
	/**扩展字段3*/
	@Excel(name = "扩展字段3", width = 15)
    @ApiModelProperty(value = "扩展字段3")
    private String s3;
	/**扩展字段4*/
	@Excel(name = "扩展字段4", width = 15)
    @ApiModelProperty(value = "扩展字段4")
    private String s4;
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
	/**所属部门*/
    @ApiModelProperty(value = "所属部门")
    private String sysOrgCode;
	/**remarks*/
	@Excel(name = "remarks", width = 15)
    @ApiModelProperty(value = "remarks")
    private String remarks;
}
