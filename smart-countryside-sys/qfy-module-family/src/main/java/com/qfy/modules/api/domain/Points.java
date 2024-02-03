package com.qfy.modules.api.domain;

import java.io.Serializable;
import lombok.Data;

/**
 * @author lanls
 * @version v1.0.0
 * @since 2023/11/18 21:18
 */
@Data
public class Points implements Serializable {

    private static final long serialVersionUID = 5515430953725531966L;

    private String id;
    private String uid;
    private String orgCode;
    private String realname;
    private String familyNo;
    private String phone;
    private String category;
    private String ckId;
    private String ckName;
    private String reason;
    private String score;
    private String workImgs;
    private String year;
}
