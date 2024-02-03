package com.qfy.modules.apppage.element.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.jeecg.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;

import java.io.Serializable;

/**
 * @Description: 页面元素管理
 * @Author: jeecg-boot
 * @Date:   2024-01-28
 * @Version: V1.0
 */
@Data
@TableName("qfy_app_page_element")
@ApiModel(value="qfy_app_page_element对象", description="页面元素管理")
public class PageElement  implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
    private String id;
	/**上级id*/
	@Excel(name = "上级id", width = 15)
    @ApiModelProperty(value = "上级id")
    private String pid;
	/**租户id*/
	@Excel(name = "租户id", width = 15)
    @ApiModelProperty(value = "租户id")
    private Integer tenantId;
	/**组编号*/
	@Excel(name = "组编号", width = 15)
    @ApiModelProperty(value = "组编号")
    private String groupNo;
	/**标题*/
	@Excel(name = "标题", width = 15)
    @ApiModelProperty(value = "标题")
    private String title;
	/**摘要*/
	@Excel(name = "摘要", width = 15)
    @ApiModelProperty(value = "摘要")
    private String digest;
	/**跳转方式(0是链接 1是图片)*/
	@Excel(name = "跳转方式(0是链接 1是图片)", width = 15, dicCode = "yn")
	@Dict(dicCode = "yn")
    @ApiModelProperty(value = "跳转方式(0是链接 1是图片)")
    private Integer jumpMode;
	/**外部链接或内容图链接*/
	@Excel(name = "外部链接或内容图链接", width = 15)
    @ApiModelProperty(value = "外部链接或内容图链接")
    private String exteriorPath;
	/**标题图*/
	@Excel(name = "标题图", width = 15)
    @ApiModelProperty(value = "标题图")
    private String titleImg;
	/**是否生效 0否 1是*/
	@Excel(name = "是否生效 0否 1是", width = 15)
    @ApiModelProperty(value = "是否生效 0否 1是")
	@Dict(dicCode = "yn")
    private Integer iseffect;
	/**阅读次数*/
	@Excel(name = "阅读次数", width = 15)
    @ApiModelProperty(value = "阅读次数")
    private Integer readyCount;
	/**排序*/
	@Excel(name = "排序", width = 15)
    @ApiModelProperty(value = "排序")
    private Integer sortno;
	/**菜单类型(0是资讯轮播 1是资讯广告 2是商城轮播 3是商城广告）*/
	@Excel(name = "菜单类型(0是资讯轮播 1是资讯广告 2是商城轮播 3是商城广告）", width = 15)
    @ApiModelProperty(value = "菜单类型(0是资讯轮播 1是资讯广告 2是商城轮播 3是商城广告）")
    private String menuType;
	/**背景图*/
	@Excel(name = "背景图", width = 15)
    @ApiModelProperty(value = "背景图")
    private String bgImg;
	/**app/ios 跳转路径*/
	@Excel(name = "app/ios 跳转路径", width = 15)
    @ApiModelProperty(value = "app/ios 跳转路径")
    private String appforward;
	/**是否使用角色权限*/
	@Excel(name = "是否使用角色权限", width = 15)
    @ApiModelProperty(value = "是否使用角色权限")
    private String hasRoleAuth;
	/**是否有子节点*/
	@Excel(name = "是否有子节点", width = 15, dicCode = "yn")
	@Dict(dicCode = "yn")
    @ApiModelProperty(value = "是否有子节点")
    private String hasChild;
}
