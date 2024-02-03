package com.qfy.modules.wxmini.vo;/**
 * @title: WxLoginVo
 * @projectName qfy-jeecg-system
 * @description: TODO
 * @author 马强
 * @date 2022/6/13 15:54
 */

import lombok.Data;

/**
 * @author     ：马强
 * @date       ：Created in 2022/6/13 15:54
 * @description：
 * @modified By：
 * @version:
 */
@Data
public class WxLoginVo {
    /**
     * 账号
     */
    private String username;
    /**
     * 密码
     */
    private String password;

    /**
     * 微信返回的code
     */
    private String code;

    private String openid;
    /**
     * 非敏感的用户信息
     */
    private String rawData;
    /**
     * 签名信息
     */
    private String signature;
    /**
     * 加密的数据
     */
    private String encryptedData;
    /**
     * 加密密钥
     */
    private String iv;

    /**
     * 第三方登录类型
     */
    private String thirdType;

    public String getUsername() {
        if(username == null){
            return null;
        }
        return username.trim();
    }

    public String getPassword() {
        if(username == null){
            return null;
        }
        return password.trim();
    }
}
