package com.qfy.modules.lt.entity;

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
 * @Description: 会议管理
 * @Author: jeecg-boot
 * @Date:   2022-04-05
 * @Version: V1.0
 */
@Data
@TableName("qfy_lt_hymc")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="qfy_lt_hymc对象", description="会议管理")
public class Hymc implements Serializable {
    private static final long serialVersionUID = 1L;

	/**编码*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "编码")
    private java.lang.String id;
	/**会议名称*/
	@Excel(name = "会议名称", width = 15)
    @ApiModelProperty(value = "会议名称")
    private java.lang.String hyname;
	/**会议地点*/
	@Excel(name = "会议地点", width = 15)
    @ApiModelProperty(value = "会议地点")
    private java.lang.String hyAddress;
	/**会议日期*/
	@Excel(name = "会议日期", width = 15)
    @ApiModelProperty(value = "会议日期")
    private java.lang.String hydate;
	/**会议组委会*/
	@Excel(name = "会议组委会", width = 15)
    @ApiModelProperty(value = "会议组委会")
    private java.lang.String hyZwh;
	/**会议简介*/
	@Excel(name = "会议简介", width = 15)
    @ApiModelProperty(value = "会议简介")
    private java.lang.String hyJj;
	/**会议简介标题*/
	@Excel(name = "会议简介标题", width = 15)
    @ApiModelProperty(value = "会议简介标题")
    private java.lang.String hyJjTitle;
	/**会议议程*/
	@Excel(name = "会议议程", width = 15)
    @ApiModelProperty(value = "会议议程")
    private java.lang.String hyYc;
	/**会议议程标题*/
	@Excel(name = "会议议程标题", width = 15)
    @ApiModelProperty(value = "会议议程标题")
    private java.lang.String hyYcTitle;
	/**会议指南*/
	@Excel(name = "会议指南", width = 15)
    @ApiModelProperty(value = "会议指南")
    private java.lang.String hyZn;
	/**会议指南标题*/
	@Excel(name = "会议指南标题", width = 15)
    @ApiModelProperty(value = "会议指南标题")
    private java.lang.String hyZnTitle;
	/**会议日程安排*/
	@Excel(name = "会议日程安排", width = 15)
    @ApiModelProperty(value = "会议日程安排")
    private java.lang.String hyRc;
	/**日程安排内容*/
	@Excel(name = "日程安排内容", width = 15)
    @ApiModelProperty(value = "日程安排内容")
    private java.lang.String hyRcTitle;
	/**会议主题*/
	@Excel(name = "会议主题", width = 15)
    @ApiModelProperty(value = "会议主题")
    private java.lang.String hyZt;
	/**会议主题内容*/
	@Excel(name = "会议主题内容", width = 15)
    @ApiModelProperty(value = "会议主题内容")
    private java.lang.String hyZtTitle;
	/**子主题*/
	@Excel(name = "子主题", width = 15)
    @ApiModelProperty(value = "子主题")
    private java.lang.String hyZzt;
	/**参会费用*/
	@Excel(name = "参会费用", width = 15)
    @ApiModelProperty(value = "参会费用")
    private java.lang.String hyFy;
	/**住宿费用类型*/
	@Excel(name = "住宿费用类型", width = 15)
    @ApiModelProperty(value = "住宿费用类型")
    private java.lang.String hyZsfy;
	/**征稿截止日期*/
	@Excel(name = "征稿截止日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "征稿截止日期")
    private java.util.Date zgEndDate;
	/**会议导航图片*/
	@Excel(name = "会议导航图片", width = 15)
    @ApiModelProperty(value = "会议导航图片")
    private java.lang.String hyImg;
	/**参会代表*/
	@Excel(name = "参会代表", width = 15)
    @ApiModelProperty(value = "参会代表")
    private java.lang.String hyDb;
	/**是否关闭报名*/
	@Excel(name = "是否关闭报名", width = 15, dicCode = "yn")
	@Dict(dicCode = "yn")
    @ApiModelProperty(value = "是否关闭报名")
    private java.lang.Integer bmState;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
    private java.lang.String cdesc;
	/**部门编码*/
    @ApiModelProperty(value = "部门编码")
    private java.lang.String sysOrgCode;
	/**添加时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "添加时间")
    private java.util.Date createTime;
	/**添加人*/
    @ApiModelProperty(value = "添加人")
    private java.lang.String createBy;
	/**修改时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "修改时间")
    private java.util.Date updateTime;
	/**修改人*/
    @ApiModelProperty(value = "修改人")
    private java.lang.String updateBy;
	/**是否删除*/
	@Excel(name = "是否删除", width = 15)
    @ApiModelProperty(value = "是否删除")
    private java.lang.Integer delFlag;
}
