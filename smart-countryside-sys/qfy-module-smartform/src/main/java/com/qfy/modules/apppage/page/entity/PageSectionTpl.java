package com.qfy.modules.apppage.page.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.qfy.modules.commons.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;

import java.io.Serializable;

/**
 * @Description: 页面模块模板表
 * @Author: jeecg-boot
 * @Date:   2024-01-25
 * @Version: V1.0
 */
@Data
@TableName("qfy_app_page_section_tpl")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="qfy_app_page_section_tpl对象", description="页面模块模板表")
public class PageSectionTpl extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "id")
    private Integer id;
	/**租户id*/
	@Excel(name = "租户id", width = 15)
    @ApiModelProperty(value = "租户id")
    private Integer tenantId;
	/**标题*/
	@Excel(name = "标题", width = 15)
    @ApiModelProperty(value = "标题")
    private String title;
	/**作者*/
	@Excel(name = "作者", width = 15)
    @ApiModelProperty(value = "作者")
    private String author;
	/**摘要*/
	@Excel(name = "摘要", width = 15)
    @ApiModelProperty(value = "摘要")
    private String digest;
	/**webh5地址*/
	@Excel(name = "webh5地址", width = 15)
    @ApiModelProperty(value = "webh5地址")
    private String webUrl;
	/**miniUrl*/
	@Excel(name = "miniUrl", width = 15)
    @ApiModelProperty(value = "miniUrl")
    private String miniUrl;
	/**标题图*/
	@Excel(name = "标题图", width = 15)
    @ApiModelProperty(value = "标题图")
    private String titleImg;
	/**是否生效 0否 1是*/
	@Excel(name = "是否生效 0否 1是", width = 15)
    @ApiModelProperty(value = "是否生效 0否 1是")
    private Integer iseffect;
	/**排序*/
	@Excel(name = "排序", width = 15)
    @ApiModelProperty(value = "排序")
    private Integer sortno;
	/**菜单类型(0是产品1是推广 2是优惠券 3是文字）*/
	@Excel(name = "菜单类型(0是产品1是推广 2是优惠券 3是文字）", width = 15)
    @ApiModelProperty(value = "菜单类型(0是产品1是推广 2是优惠券 3是文字）")
    private String tplType;
	/**数据列数*/
	@Excel(name = "数据列数", width = 15)
    @ApiModelProperty(value = "数据列数")
    private Integer dataColumn;
	/**权限type  商户1 平台2  商户和平台3(1+2)*/
	@Excel(name = "权限type  商户1 平台2  商户和平台3(1+2)", width = 15)
    @ApiModelProperty(value = "权限type  商户1 平台2  商户和平台3(1+2)")
    private Integer authtype;

}
