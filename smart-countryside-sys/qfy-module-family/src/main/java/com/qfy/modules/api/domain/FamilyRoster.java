package com.qfy.modules.api.domain;

import lombok.Data;

/**
 * 居民（村民）家庭成员花名册
 * @author lanls
 * @version v1.0.0
 * @since 2023/11/18 15:38
 */
@Data
public class FamilyRoster {
    private String uid;
    private String username;
    private String realname;
    private String phone;
    private String orgCode;
    private String familyNo;
    private String sex;
    private String address;
}
