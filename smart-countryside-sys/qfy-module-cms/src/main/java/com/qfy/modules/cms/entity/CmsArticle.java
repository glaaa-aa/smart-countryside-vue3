package com.qfy.modules.cms.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.qfy.modules.commons.entity.BaseEntity;
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
 * @Description: 文章管理
 * @Author: jeecg-boot
 * @Date:   2022-04-05
 * @Version: V1.0
 */
@Data
@TableName("qfy_cms_article")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="qfy_cms_article对象", description="文章管理")
public class CmsArticle extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

	/**编码*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "编码")
    private String id;
	/**qyid*/
	@Excel(name = "租户id", width = 15)
    @ApiModelProperty(value = "租户id")
    private Integer tenantId;
	/**文章标题*/
	@Excel(name = "文章标题", width = 15)
    @ApiModelProperty(value = "文章标题")
    private String title;
	/**文章栏目*/
	@Excel(name = "文章栏目", width = 15, dictTable = "qfy_cms_section", dicText = "name", dicCode = "id")
	@Dict(dictTable = "qfy_cms_section", dicText = "name", dicCode = "id")
    @ApiModelProperty(value = "文章栏目")
    private String secId;
	/**内容类型*/
	@Excel(name = "内容类型", width = 15, dicCode = "article_type")
	@Dict(dicCode = "article_type")
    @ApiModelProperty(value = "内容类型")
    private String articleType;
	/**副标题*/
	@Excel(name = "副标题", width = 15)
    @ApiModelProperty(value = "副标题")
    private String shortTitle;
	/**作者*/
	@Excel(name = "作者", width = 15)
    @ApiModelProperty(value = "作者")
    private String author;
	/**是否外链*/
	@Excel(name = "是否外链", width = 15, dicCode = "yn")
	@Dict(dicCode = "yn")
    @ApiModelProperty(value = "是否外链")
    private Integer isExterior;
	/**外部链接*/
	@Excel(name = "外部链接", width = 15)
    @ApiModelProperty(value = "外部链接")
    private String exteriorPath;
	/**是否发布*/
	@Excel(name = "是否发布", width = 15, dicCode = "yn")
	@Dict(dicCode = "yn")
    @ApiModelProperty(value = "是否发布")
    private Integer isPublish;
	/**发布时间*/
	@Excel(name = "发布时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "发布时间")
    private java.util.Date publishTime;
	/**是否显示*/
	@Excel(name = "是否显示", width = 15, dicCode = "yn")
	@Dict(dicCode = "yn")
    @ApiModelProperty(value = "是否显示")
    private Integer isShow;
	/**排序*/
	@Excel(name = "排序", width = 15)
    @ApiModelProperty(value = "排序")
    private Integer sort;
	/**是否置顶*/
	@Excel(name = "是否置顶", width = 15, dicCode = "yn")
	@Dict(dicCode = "yn")
    @ApiModelProperty(value = "是否置顶")
    private Integer isTop;
	/**标题颜色*/
	@Excel(name = "标题颜色", width = 15)
    @ApiModelProperty(value = "标题颜色")
    private String titleColor;
	/**摘要*/
	@Excel(name = "摘要", width = 15)
    @ApiModelProperty(value = "摘要")
    private String digest;
	/**标题是否加粗*/
	@Excel(name = "标题是否加粗", width = 15)
    @ApiModelProperty(value = "标题是否加粗")
    private Integer isBold;
	/**tag标签*/
	@Excel(name = "tag标签", width = 15)
    @ApiModelProperty(value = "tag标签")
    private String tagLabel;
	/**模板路径*/
	@Excel(name = "模板路径", width = 15)
    @ApiModelProperty(value = "模板路径")
    private String templatePath;
	/**缩略图*/
	@Excel(name = "缩略图", width = 15)
    @ApiModelProperty(value = "缩略图")
    private String titleImg;
	/**是否显示到首页*/
	@Excel(name = "是否显示到首页", width = 15)
    @ApiModelProperty(value = "是否显示到首页")
    private Integer isShowIndex;
	/**阅读次数*/
	@Excel(name = "阅读次数", width = 15)
    @ApiModelProperty(value = "阅读次数")
    private Integer readyCount;
	/**文章内容*/
	@Excel(name = "文章内容", width = 15)
    @ApiModelProperty(value = "文章内容")
    private String detailContent;
	/**内容图(图片集)*/
	@Excel(name = "内容图(图片集)", width = 15)
    @ApiModelProperty(value = "内容图(图片集)")
    private String contentImgs;
	/**上传的附件*/
	@Excel(name = "上传的附件", width = 15)
    @ApiModelProperty(value = "上传的附件")
    private String fileIds;
	/**多媒体（视频）*/
	@Excel(name = "多媒体（视频）", width = 15)
    @ApiModelProperty(value = "多媒体（视频）")
    private String mediaIds;
	/**发布的路径*/
	@Excel(name = "发布的路径", width = 15)
    @ApiModelProperty(value = "发布的路径")
    private String publishUrl;
	/**是否审核*/
	@Excel(name = "是否审核", width = 15)
    @ApiModelProperty(value = "是否审核")
    private Integer isCheck;
	/**审核状态*/
	@Excel(name = "审核状态", width = 15)
    @ApiModelProperty(value = "审核状态")
    private String checkState;
	/**审核人*/
	@Excel(name = "审核人", width = 15)
    @ApiModelProperty(value = "审核人")
    private String checkPerson;
	/**审核时间*/
	@Excel(name = "审核时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "审核时间")
    private java.util.Date checkTime;

	/**是否有积分奖励,0：否，1：是*/
	@Excel(name = "是否有积分奖励,0：否，1：是", width = 15)
    @ApiModelProperty(value = "是否有积分奖励,0：否，1：是")
    private Integer isReward;
	/**积分奖励值*/
	@Excel(name = "积分奖励值", width = 15)
    @ApiModelProperty(value = "积分奖励值")
    private java.math.BigDecimal integral;
	/**c1*/
	@Excel(name = "c1", width = 15)
    @ApiModelProperty(value = "c1")
    private String c1;
	/**c2*/
	@Excel(name = "c2", width = 15)
    @ApiModelProperty(value = "c2")
    private String c2;
	/**c3*/
	@Excel(name = "c3", width = 15)
    @ApiModelProperty(value = "c3")
    private String c3;
	/**c4*/
	@Excel(name = "c4", width = 15)
    @ApiModelProperty(value = "c4")
    private String c4;
	/**c5*/
	@Excel(name = "c5", width = 15)
    @ApiModelProperty(value = "c5")
    private String c5;
}
