package com.qfy.modules.appauth.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 用户部门关系
 * @Author: jeecg-boot
 * @Date:   2023-04-14
 * @Version: V1.0
 */
@Data
public class UserDepratBean implements Serializable {

    /**
     * 用户名
     */
    private String username;
    /**
     * 对应分管领导
     */
    private String fenguanLeader;
    /**
     * 对应负责部门
     */
    private String departUser;

}
