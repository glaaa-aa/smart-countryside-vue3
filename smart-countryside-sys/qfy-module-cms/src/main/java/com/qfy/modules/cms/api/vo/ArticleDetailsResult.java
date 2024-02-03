package com.qfy.modules.cms.api.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * TODO 文章详情实体
 *
 * @author lanls
 * @version 1.0
 * @date 2021/11/17 10:19
 */
@ApiModel(value = "文章详情实体")
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticleDetailsResult implements Serializable {

    private static final long serialVersionUID = 7491315845023643761L;

    /**编码*/
    @ApiModelProperty(value = "id")
    private String id;
    /**qyid*/
//    @ApiModelProperty(value = "qyid")
//    private Integer qyId;
    /**模块id*/
    @ApiModelProperty(value = "栏目id")
    private String secId;
    /**标题*/
    @ApiModelProperty(value = "标题")
    private String title;
    /**简短标题*/
    @ApiModelProperty(value = "简短标题")
    private String shortTitle;
    /**标题颜色*/
//    @ApiModelProperty(value = "标题颜色")
//    private String titleColor;
//    /**标题是否加粗*/
//    @ApiModelProperty(value = "标题是否加粗")
//    private Integer isBold;
    /**是否外联*/
//    @ApiModelProperty(value = "是否外联")
//    private Integer isExterior;
//    /**外部链接*/
//    @ApiModelProperty(value = "外部链接")
//    private String exteriorPath;
//    /**tag标签*/
//    @ApiModelProperty(value = "tag标签")
//    private String tagLabel;
    /**摘要*/
    @ApiModelProperty(value = "摘要")
    private String digest;
    /**作者*/
    @ApiModelProperty(value = "作者")
    private String author;
    /**发布时间*/
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
    @ApiModelProperty(value = "发布时间")
    private Date publishTime;
    /**是否置顶*/
//    @ApiModelProperty(value = "是否置顶")
//    private Integer isTop;
    /**是否显示到首页*/
//    @ApiModelProperty(value = "是否显示到首页")
//    private Integer isShowIndex;
    /**是否显示*/
    @ApiModelProperty(value = "是否显示")
    private Integer isShow;
    /**阅读次数*/
    @ApiModelProperty(value = "阅读次数")
    private Integer readyCount;
    /**内容类型*/
    @ApiModelProperty(value = "内容类型")
    private String articleType;
    /**文章内容*/
    @ApiModelProperty(value = "文章内容")
    private String detailContent;
    /**标题图*/
    @ApiModelProperty(value = "标题图")
    private String titleImg;
    /**内容图(图片集)*/
    @ApiModelProperty(value = "内容图(图片集)")
    private String contentImgs;
    /**上传的附件*/
    @ApiModelProperty(value = "上传的附件")
    private String fileIds;
    /**多媒体（视频）*/
    @ApiModelProperty(value = "多媒体（视频）")
    private String mediaIds;
    /**是否发布*/
    @ApiModelProperty(value = "是否发布")
    private Integer isPublish;
    /**发布的路径*/
//    @ApiModelProperty(value = "发布的路径")
//    private String publishUrl;
    /**排序*/
    @ApiModelProperty(value = "排序")
    private Integer sort;
    /**备注*/
    @ApiModelProperty(value = "备注")
    private String remarks;
}
