package com.qfy.modules.apppage.page.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.qfy.modules.commons.entity.BaseEntity;
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
 * @Description: 移动端网站页面
 * @Author: jeecg-boot
 * @Date:   2024-01-25
 * @Version: V1.0
 */
@Data
@TableName("qfy_app_page")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="qfy_app_page对象", description="移动端网站页面")
public class AppPage extends BaseEntity implements Serializable {
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
	/**发布时间*/
	@Excel(name = "发布时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "发布时间")
    private Date publishTime;
	/**摘要*/
	@Excel(name = "摘要", width = 15)
    @ApiModelProperty(value = "摘要")
    private String digest;
	/**是否发布 0否 1是*/
	@Excel(name = "是否发布 0否 1是", width = 15, dicCode = "yn")
	@Dict(dicCode = "yn")
    @ApiModelProperty(value = "是否发布 0否 1是")
    private Integer isfb;
	/**页面类型  首页或者 跳转页面  1首页   2跳转页面*/
	@Excel(name = "页面类型  首页或者 跳转页面  1首页   2跳转页面", width = 15)
    @ApiModelProperty(value = "页面类型  首页或者 跳转页面  1首页   2跳转页面")
    private String webType;
	/**页面头部颜色 用于小程序设置头部header字体颜色*/
	@Excel(name = "页面头部颜色 用于小程序设置头部header字体颜色", width = 15)
    @ApiModelProperty(value = "页面头部颜色 用于小程序设置头部header字体颜色")
    private String frontColor;
	/**页面头部颜色 用于小程序设置头部header背景颜色*/
	@Excel(name = "页面头部颜色 用于小程序设置头部header背景颜色", width = 15)
    @ApiModelProperty(value = "页面头部颜色 用于小程序设置头部header背景颜色")
    private String headerBgcolor;
	/**页面访问地址*/
	@Excel(name = "页面访问地址", width = 15)
    @ApiModelProperty(value = "页面访问地址")
    private String pageUrl;

}
