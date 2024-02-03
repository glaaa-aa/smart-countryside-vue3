package com.qfy.modules.config;

/**
 * @author 马强
 * @title: ThirdTypeEnum
 * @projectName qfy-project-task-system
 * @description: TODO
 * @date 2022/12/9 11:24
 */
public enum ThirdAppEnum {

    MINI_WEIXIN("weixin","微信小程序"),
    DINGTALK("dingtalk","钉钉应用"),
    WECHAT_ENTERPRISE("wechatEnterprise","微信企业号");

    private String code;
    private String alias;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    ThirdAppEnum(String code, String alias){
        this.alias = alias;
        this.code = code;
    }
}
