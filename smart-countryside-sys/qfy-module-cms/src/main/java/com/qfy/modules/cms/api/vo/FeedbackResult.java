package com.qfy.modules.cms.api.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * TODO
 *
 * @author lanls
 * @version 1.0
 * @date 2021/11/30 8:54
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "留言-意见反馈结果对象")
public class FeedbackResult implements Serializable {

    private static final long serialVersionUID = -8313737264133168149L;

    @ApiModelProperty(value = "id")
    private java.lang.String id;
    /**用户id*/
    @ApiModelProperty(value = "用户id")
    private java.lang.String userId;
    /**用户账号*/
    @ApiModelProperty(value = "用户名")
    private java.lang.String userAccount;
    /**用户昵称*/
    @ApiModelProperty(value = "用户昵称")
    private java.lang.String nickname;
    /**联系电话*/
    @ApiModelProperty(value = "联系电话")
    private java.lang.String telephone;
    /**处理状态：0，待处理，1：处理中，2：已处理*/
    @ApiModelProperty(value = "处理状态：0，待处理，1：处理中，2：已处理")
    private java.lang.Integer state;
    /**意见内容*/
    @ApiModelProperty(value = "留言-反馈意见内容")
    private java.lang.String content;
    /**图片*/
    @ApiModelProperty(value = "图片")
    private java.lang.String pictures;
    /**其它附件，如文档，视频，音频*/
    @ApiModelProperty(value = "其它附件")
    private java.lang.String attachment;
    /**处理时长（分钟）*/
    @ApiModelProperty(value = "处理时长")
    private java.lang.Integer duration;
    /**提交时间*/
    @ApiModelProperty(value = "留言-反馈意见提交或回复时间")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
    private java.util.Date submitTime;
    /**意见类型 1：平台系统意见，2：政务服务意见，3：活动意见，4：学习考核意见，5：求助留言，6：其它意见*/
    @ApiModelProperty(value = "意见类型 1：平台系统意见，2：政务服务意见，3：活动意见，4：学习考核意见，5：求助留言，6：其它意见")
    private java.lang.Integer type;
    /**联系地址*/
    @ApiModelProperty(value = "联系地址")
    private java.lang.String address;
    /**经度*/
    /**父级意见*/
    @ApiModelProperty(value = "父级留言-反馈意见id")
    private java.lang.String pid;
    @ApiModelProperty(value = "子级留言-反馈意见回复列表")
    private List<FeedbackResult> children = new ArrayList<>();
}
