package com.qfy.modules.commons.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * 公共的结果封装类
 *
 * @author lanls
 * @version 1.0
 * @date 2021/11/23 21:20
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)// 忽略为Null值的属性不进行系列化
@ApiModel(value = "公用结果对象", description = "公用结果对象")
public class CommonResult implements Serializable {

    private static final long serialVersionUID = -8679500600495456654L;

    @ApiModelProperty(value = "id")
    private String id; // 活动id
    @ApiModelProperty(value = "主题")
    private String title; // 主题
    @ApiModelProperty(value = "开始时间")
    private String subtitle; // 活动开始时间
    @ApiModelProperty(value = "简介")
    private String info; // 简介
    @ApiModelProperty(value = "描述")
    private String context; // 活动描述
    @ApiModelProperty(value = "状态文字")
    private Integer state; // 状态文字
    @ApiModelProperty(value = "背景图片")
    private String backgroundImage; // 背景图片
    @ApiModelProperty(value = "头像")
    private String avatar; // 头像
    @ApiModelProperty(value = "子级对象列表")
    private List<CommonResult> children;
    @ApiModelProperty(value = "是否允许评论")
    private Integer isComment; // 是否允许评论
    @ApiModelProperty(value = "文章是否已读,0未读，1已读")
    private Integer isRead = 0;
}
