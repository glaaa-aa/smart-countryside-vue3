package com.qfy.modules.api.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import org.jeecg.common.aspect.annotation.Dict;

/**
 * @author lanls
 * @version v1.0.0
 * @since 2023/11/24 16:39
 */
@Data
public class PointsHistory implements Serializable {
    private static final long serialVersionUID = -3773783920928990398L;
    private String id;
    private String realName;
    private String familyNo;
    private String telephone;
    @Dict(dictTable = "qfy_sys_organization", dicCode = "code", dicText = "name")
    private String orgCode;
    private String chkId;
    private String itemName;
    private Integer category;
    private String categoryName;
    private Float score;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date submitDate;
    private String reason;
    private String year;
    private String workImgs;
}
