package com.qfy.modules.api.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.Data;

/**
 * 积分记录对象
 * @author lanls
 * @version v1.0.0
 * @since 2023/11/18 9:48
 */
@Data
public class PointsRecord {
    private String id;
    private String familyNo;
    private String chkId;
    private Integer category;
    private String categoryName;
    private Float score;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date date;
    private String reason;

}
