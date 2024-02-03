package com.qfy.modules.api.domain;

import java.io.Serializable;
import lombok.Data;

/**
 * 积分排行榜
 * @author lanls
 * @version v1.0.0
 * @since 2023/11/17 16:24
 */
@Data
public class PointsRanking implements Serializable {

    private static final long serialVersionUID = -8587189916785792081L;

    private String uid;
    private String username;
    private String realname;
    private Long baseScore = 100L;
    private Long totalAmount;
    private Long plusAmount;
    private Long reduceAmount;
    private Long sort;
    private Integer code;
    private String phone;
    private String address;
}
