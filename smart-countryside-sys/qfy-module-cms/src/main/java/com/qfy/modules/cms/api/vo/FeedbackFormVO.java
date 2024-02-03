package com.qfy.modules.cms.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

/**
 * 意见反馈内容实体
 *
 * @author lanls
 * @version 1.0
 * @date 2021/11/29 10:51
 */
@Data
@ApiModel(value = "留言-意见反馈表单对象", description = "留言-意见反馈表单对象")
public class FeedbackFormVO implements Serializable {

    private static final long serialVersionUID = 3013246895480105742L;

    /**主键Id*/
//    @ApiModelProperty(value = "id,如果是对意见反馈的回复，则填入主意见id，也可以把主意见id填入pid字段")
//    private java.lang.String id;
    /**用户id*/
//    @ApiModelProperty(value = "用户id,与用户账号二选一", required = true)
//    private java.lang.String userId;
//    /**用户账号*/
//    @ApiModelProperty(value = "用户账号,与用户id二选一", required = true)
//    private java.lang.String userAccount;
    /**用户昵称*/
    @ApiModelProperty(value = "用户昵称")
    private java.lang.String nickname;
    /**联系电话*/
    @ApiModelProperty(value = "联系电话")
    private java.lang.String telephone;
    /**处理状态：0，待处理，1：处理中，2：已处理*/
//    @ApiModelProperty(value = "处理状态：0，待处理，1：处理中，2：已处理")
//    private java.lang.Integer state;
    /**意见内容*/
    @ApiModelProperty(value = "意见内容")
    private java.lang.String content;
    /**图片*/
    @ApiModelProperty(value = "反馈的图片")
    private java.lang.String pictures;
    /**其它附件，如文档，视频，音频*/
    @ApiModelProperty(value = "其它附件，如文档，视频，音频")
    private java.lang.String attachment;
    /**处理时长（分钟）*/
//    @ApiModelProperty(value = "处理时长（分钟）")
//    private java.lang.Integer duration;
    /**提交时间*/
//    @ApiModelProperty(value = "提交时间")
//    private java.util.Date submitTime;
    /**意见类型 1：平台系统意见，2：政务服务意见，3：活动意见，4：学习考核意见，5：求助留言，6：其它意见*/
    @ApiModelProperty(value = "意见类型 1：平台系统意见，2：政务服务意见，3：活动意见，4：学习考核意见，5：求助留言，6：其它意见")
    private java.lang.Integer type;
    /**意见来源：app，小程序，PC端*/
    @ApiModelProperty(value = "意见来源：app，miniProgram，pc")
    private java.lang.String origin;
    /**联系地址*/
    @ApiModelProperty(value = "联系地址")
    private java.lang.String address;
    /**经度*/
    @ApiModelProperty(value = "经度")
    private java.math.BigDecimal lng;
    /**纬度*/
    @ApiModelProperty(value = "纬度")
    private java.math.BigDecimal lat;
    /**父级意见*/
    @ApiModelProperty(value = "回复的原意见id，如果是提交意见而不是回复，则为0", required = true)
    private java.lang.String pid;
    /**是否有回复意见*/
//    @ApiModelProperty(value = "是否有回复意见")
//    private java.lang.String hasChild;
}
