package com.qfy.modules.config.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecg.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;

import java.io.Serializable;

/**
 * @Description: 第三方对接参数设置
 * @Author: jeecg-boot
 * @Date:   2022-12-08
 * @Version: V1.0
 */
@Data
@TableName("qfy_system_third_config")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="qfy_common_third_config对象", description="第三方对接参数设置")
public class ThirdConfig implements Serializable {
    private static final long serialVersionUID = 1L;
	/**id*/
	@TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "id")
    private Integer id;
	/**租户*/
	@Excel(name = "租户", width = 15)
    @ApiModelProperty(value = "租户")
    private Integer tenantId;
	/**类型*/
	@Excel(name = "类型", width = 15)
    @ApiModelProperty(value = "类型")
    private String thirdType;
	/**agent_id*/
	@Excel(name = "agent_id", width = 15)
    @ApiModelProperty(value = "agent_id")
    private String agentId;

    @Excel(name = "自建应用秘钥（新版企微需要配置）", width = 15)
    private String agentAppSecret;
	/**appid */
	@Excel(name = "appid ", width = 15)
    @ApiModelProperty(value = "appid ")
    private String clientId;
	/**app_secret*/
	@Excel(name = "app_secret", width = 15)
    @ApiModelProperty(value = "app_secret")
    private String clientSecret;
	/**aesToken*/
	@Excel(name = "aesToken", width = 15)
    @ApiModelProperty(value = "aesToken")
    private String aesToken;
	/**aesKey*/
	@Excel(name = "aesKey", width = 15)
    @ApiModelProperty(value = "aesKey")
    private String aesKey;

    @Excel(name = "后台java地址", width = 15)
    @ApiModelProperty(value = "后台java地址")
    private String domain;

	/**访问地址*/
	@Excel(name = "访问地址", width = 15)
    @ApiModelProperty(value = "访问地址")
    private String thirdMain;
	/**corpId*/
	@Excel(name = "corpId", width = 15)
    @ApiModelProperty(value = "corpId")
    private String corpId;
	/**商户号*/
	@Excel(name = "商户号", width = 15)
    @ApiModelProperty(value = "商户号")
    private String mchId;
	/**商户支付密钥*/
	@Excel(name = "商户支付密钥", width = 15)
    @ApiModelProperty(value = "商户支付密钥")
    private String mchKey;
	/**商户支付证书地址p12证书的位置，可以指定绝对路径，也可以指定类路径（以classpath:开头:classpath:cert/apiclient_cert.p12）*/
	@Excel(name = "商户支付证书地址p12证书的位置", width = 15)
    @ApiModelProperty(value = "商户支付证书地址p12证书的位置")
    private String mchKeyPath;
	/**是否启用*/
	@Excel(name = "是否启用", width = 15)
    @ApiModelProperty(value = "是否启用")
    private Boolean isEnabled;
    /**备注*/
    @Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
    private String remarks;

    @Excel(name = "群模版id 从钉钉平台设置", width = 15)
    @ApiModelProperty(value = "群模版id 从钉钉平台设置")
    private String chatTemplateId;

    @Excel(name = "是否创建群聊", width = 15)
    @ApiModelProperty(value = "是否创建群聊")
    @Dict(dicCode = "yn")
    private Integer isCreateChat;

    @Excel(name = "是否发送消息", width = 15)
    @ApiModelProperty(value = "是否发送消息")
    @Dict(dicCode = "yn")
    private Integer isSendMsg;

    @Excel(name = "是否有效", width = 15)
    @Dict(dicCode = "yn")
    private Integer status;

}
