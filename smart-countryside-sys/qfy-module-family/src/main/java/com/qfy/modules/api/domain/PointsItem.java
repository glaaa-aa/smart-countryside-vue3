package com.qfy.modules.api.domain;

import lombok.Data;

/**
 * 积分项
 *
 * @author lanli
 * @version 1.0
 * @date 2023/11/19 23:49
 */
@Data
public class PointsItem {
    private String chkId; // 积分项ID
    private String name;// 积分项名称
    private String alias;// 积分项别名
    private String content;// 加分或扣分说明、原因或者事由
    private Float score; // 积分值
    private Integer category;// 积分类别，1：加分，2：减分
    private Integer needConfirm;// 是否需要确认，证明材料，0：不需要，1：需要
    private String images;// 图片证明，多个用逗号隔开
    private String attachments;// 其它附件证明，多个用逗号隔开
    private String remark;// 备注
}
