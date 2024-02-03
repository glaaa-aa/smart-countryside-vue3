package com.qfy.modules.cms.api.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * TODO
 *
 * @author lanls
 * @version 1.0
 * @date 2021/11/17 10:04
 */
@ApiModel(value = "栏目实体")
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SectionDetailsResult implements Serializable {

    private static final long serialVersionUID = -8687957316938689239L;

    /**编码*/
    @ApiModelProperty(value = "编码")
    private String id;
    /**qyid*/
//    @ApiModelProperty(value = "qyid")
//    private Integer qyId;
    /**name标示 唯一标示*/
    @ApiModelProperty(value = "栏目编码")
    private String nameCode;
    /**栏目名称*/
    @ApiModelProperty(value = "栏目名称")
    private String name;
    /**上级id*/
    @ApiModelProperty(value = "上级栏目id")
    private Integer pid;
    /**访问路径*/
//    @ApiModelProperty(value = "访问路径")
//    private String accessPath;
    /**简介*/
    @ApiModelProperty(value = "简介")
    private String introduction;
    /**Meta标题*/
//    @ApiModelProperty(value = "Meta标题")
//    private String metaTitle;
//    /**Meta关键字*/
//    @ApiModelProperty(value = "Meta关键字")
//    private String metaKeyword;
//    /**Meta描述*/
//    @ApiModelProperty(value = "Meta描述")
//    private String metaDesc;
    /**排序*/
    @Excel(name = "排序", width = 15)
    @ApiModelProperty(value = "排序")
    private Integer sort;
    /**是否在新窗口打开*/
//    @ApiModelProperty(value = "是否在新窗口打开")
//    private Integer isBlank;
    /**是否显示*/
    @ApiModelProperty(value = "是否显示")
    private Integer isShow;
    /**浏览权限*/
//    @ApiModelProperty(value = "浏览权限")
//    private String browseAuth;
    /**是否允许顶和踩*/
    @ApiModelProperty(value = "是否允许评论")
    private Integer isComment;
    /**标题图*/
    @ApiModelProperty(value = "标题图")
    private String titleImg;
    /**内容图*/
    @ApiModelProperty(value = "内容图")
    private String contentImgs;
    /**描述*/
    @ApiModelProperty(value = "描述")
    private String description;
    /**备注*/
    @ApiModelProperty(value = "备注")
    private String remarks;
    /**分类*/
    @ApiModelProperty(value = "栏目分类")
    private Integer type;
}
