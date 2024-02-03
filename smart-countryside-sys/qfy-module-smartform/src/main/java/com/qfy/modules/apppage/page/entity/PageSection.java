package com.qfy.modules.apppage.page.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;

import java.io.Serializable;

/**
 * @Description: 页面模块管理
 * @Author: jeecg-boot
 * @Date:   2024-01-25
 * @Version: V1.0
 */
@Data
@TableName("qfy_app_page_section")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="qfy_app_page_section对象", description="页面模块管理")
public class PageSection implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "id")
    private Integer id;
	/**租户id*/
	@Excel(name = "租户id", width = 15)
    @ApiModelProperty(value = "租户id")
    private Integer tenantId;
	/**网站首页id*/
	@Excel(name = "网站首页id", width = 15)
    @ApiModelProperty(value = "网站首页id")
    private Integer pageId;
	/**网站模板名称*/
	@Excel(name = "网站模板名称", width = 15)
    @ApiModelProperty(value = "网站模板名称")
    private String title;
	/**摘要*/
	@Excel(name = "摘要", width = 15)
    @ApiModelProperty(value = "摘要")
    private String digest;
	/**标题图*/
	@Excel(name = "标题图", width = 15)
    @ApiModelProperty(value = "标题图")
    private String titleimg;
	/**排序*/
	@Excel(name = "排序", width = 15)
    @ApiModelProperty(value = "排序")
    private Integer sortno;
	/**几行*/
	@Excel(name = "几行", width = 15)
    @ApiModelProperty(value = "几行")
    private Integer dataRow;
	/**几列*/
	@Excel(name = "几列", width = 15)
    @ApiModelProperty(value = "几列")
    private Integer dataCol;
	/**数据总量*/
	@Excel(name = "数据总量", width = 15)
    @ApiModelProperty(value = "数据总量")
    private Integer dataTotal;
	/**关联模板类型*/
	@Excel(name = "关联模板类型", width = 15)
    @ApiModelProperty(value = "关联模板类型")
    private String refSrc;
	/**关联数据编码*/
	@Excel(name = "关联数据编码", width = 15)
    @ApiModelProperty(value = "关联数据编码")
    private String refData;
	/**模板类型id*/
	@Excel(name = "模板类型id", width = 15)
    @ApiModelProperty(value = "模板类型id")
    private Integer sectionTplId;
}
