package com.qfy.modules.cms.entity;

import java.io.Serializable;
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
import java.io.UnsupportedEncodingException;

/**
 * @Description: 栏目管理
 * @Author: jeecg-boot
 * @Date:   2022-04-05
 * @Version: V1.0
 */
@Data
@TableName("qfy_cms_section")
@ApiModel(value="qfy_cms_section对象", description="栏目管理")
public class CmsSection extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
    private String id;
    /**qyid*/
    @Excel(name = "租户id", width = 15)
    @ApiModelProperty(value = "租户id")
    private Integer tenantId;
	/**栏目名称*/
	@Excel(name = "栏目名称", width = 15)
    @ApiModelProperty(value = "栏目名称")
    private String name;
	/**栏目编码*/
	@Excel(name = "栏目编码", width = 15)
    @ApiModelProperty(value = "栏目编码")
    private String nameCode;
	/**上级栏目*/
	@Excel(name = "上级栏目", width = 15)
    @ApiModelProperty(value = "上级栏目")
    private String pid;
	/**栏目分类*/
	@Excel(name = "栏目分类", width = 15, dicCode = "section_type")
	@Dict(dicCode = "section_type")
    @ApiModelProperty(value = "栏目分类")
    private String type;
	/**排序*/
	@Excel(name = "排序", width = 15)
    @ApiModelProperty(value = "排序")
    private Integer sort;
	/**是否显示*/
	@Excel(name = "是否显示", width = 15, dicCode = "yn")
	@Dict(dicCode = "yn")
    @ApiModelProperty(value = "是否显示")
    private Integer isShow;
	/**是否导航*/
	@Excel(name = "是否导航", width = 15, dicCode = "yn")
	@Dict(dicCode = "yn")
    @ApiModelProperty(value = "是否导航")
    private Integer isShowIndex;
	/**显示类型*/
	@Excel(name = "显示类型", width = 15, dicCode = "showType")
	@Dict(dicCode = "showType")
    @ApiModelProperty(value = "显示类型")
    private String showType;
	/**外部链接*/
	@Excel(name = "外部链接", width = 15)
    @ApiModelProperty(value = "外部链接")
    private String exteriorPath;
	/**新窗口中打开*/
	@Excel(name = "新窗口中打开", width = 15, dicCode = "yn")
	@Dict(dicCode = "yn")
    @ApiModelProperty(value = "新窗口中打开")
    private Integer isBlank;
	/**关键字*/
	@Excel(name = "关键字", width = 15)
    @ApiModelProperty(value = "关键字")
    private String metaKeyword;
	/**缩略图*/
	@Excel(name = "缩略图", width = 15)
    @ApiModelProperty(value = "缩略图")
    private String titleImg;
	/**访问路径*/
	@Excel(name = "访问路径", width = 15)
    @ApiModelProperty(value = "访问路径")
    private String accessPath;
	/**简介*/
	@Excel(name = "简介", width = 15)
    @ApiModelProperty(value = "简介")
    private String introduction;
	/**Meta标题*/
	@Excel(name = "Meta标题", width = 15)
    @ApiModelProperty(value = "Meta标题")
    private String metaTitle;
	/**Meta描述*/
	@Excel(name = "Meta描述", width = 15)
    @ApiModelProperty(value = "Meta描述")
    private String metaDesc;
	/**模板路径*/
	@Excel(name = "模板路径", width = 15)
    @ApiModelProperty(value = "模板路径")
    private String templatePath;
	/**浏览权限*/
	@Excel(name = "浏览权限", width = 15)
    @ApiModelProperty(value = "浏览权限")
    private String browseAuth;
	/**是否允许顶和踩*/
	@Excel(name = "是否允许顶和踩", width = 15)
    @ApiModelProperty(value = "是否允许顶和踩")
    private Integer isComment;
	/**内容图*/
	@Excel(name = "内容图", width = 15)
    @ApiModelProperty(value = "内容图")
    private String contentImgs;
	/**描述*/
	@Excel(name = "描述", width = 15)
    @ApiModelProperty(value = "描述")
    private String description;
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
    private Date checkTime;

	/**是否有子节点*/
	@Excel(name = "是否有子节点", width = 15)
    @ApiModelProperty(value = "是否有子节点")
    private String hasChild;
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
