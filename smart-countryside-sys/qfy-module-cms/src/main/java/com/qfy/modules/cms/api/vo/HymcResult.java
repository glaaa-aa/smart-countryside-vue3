package com.qfy.modules.cms.api.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecg.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 会议管理
 * @Author: jeecg-boot
 * @Date:   2022-04-05
 * @Version: V1.0
 */
@Data
@ApiModel(value="hymc对象", description="会议管理")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HymcResult implements Serializable {
    private static final long serialVersionUID = 7491315845023643763L;

	/**编码*/
    @ApiModelProperty(value = "编码")
    private String id;
	/**会议名称*/
	@ApiModelProperty(value = "会议名称")
    private String hyname;
	/**会议地点*/
    @ApiModelProperty(value = "会议地点")
    private String hyAddress;
	/**会议日期*/
    @ApiModelProperty(value = "会议日期")
    private String hydate;
	/**会议组委会*/
    @ApiModelProperty(value = "会议组委会")
    private String hyZwh;
	/**会议简介*/
    @ApiModelProperty(value = "会议简介")
    private String hyJj;
	/**会议简介标题*/
    @ApiModelProperty(value = "会议简介标题")
    private String hyJjTitle;
	/**会议议程*/
    @ApiModelProperty(value = "会议议程")
    private String hyYc;
	/**会议议程标题*/
    @ApiModelProperty(value = "会议议程标题")
    private String hyYcTitle;
	/**会议指南*/
    @ApiModelProperty(value = "会议指南")
    private String hyZn;
	/**会议指南标题*/
    @ApiModelProperty(value = "会议指南标题")
    private String hyZnTitle;
	/**会议日程安排*/
    @ApiModelProperty(value = "会议日程安排")
    private String hyRc;
	/**日程安排内容*/
    @ApiModelProperty(value = "日程安排内容")
    private String hyRcTitle;
	/**会议主题*/
    @ApiModelProperty(value = "会议主题")
    private String hyZt;
	/**会议主题内容*/
    @ApiModelProperty(value = "会议主题内容")
    private String hyZtTitle;
	/**子主题*/
    @ApiModelProperty(value = "子主题")
    private String hyZzt;
	/**参会费用*/
    @ApiModelProperty(value = "参会费用")
    private String hyFy;
	/**住宿费用类型*/
    @ApiModelProperty(value = "住宿费用类型")
    private String hyZsfy;
	/**征稿截止日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "征稿截止日期")
    private Date zgEndDate;
	/**会议导航图片*/
    @ApiModelProperty(value = "会议导航图片")
    private String hyImg;
	/**参会代表*/
    @ApiModelProperty(value = "参会代表")
    private String hyDb;
	/**是否关闭报名*/
	@Dict(dicCode = "yn")
    @ApiModelProperty(value = "是否关闭报名")
    private Integer bmState;
	/**备注*/
    @ApiModelProperty(value = "备注")
    private String cdesc;
}
