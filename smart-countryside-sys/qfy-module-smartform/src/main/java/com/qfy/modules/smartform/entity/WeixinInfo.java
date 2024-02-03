package com.qfy.modules.smartform.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: 微信信息
 * @Author: jeecg-boot
 * @Date:   2022-04-08
 * @Version: V1.0
 */
@Data
@TableName("qfy_weixin_info")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="qfy_weixin_info对象", description="微信信息")
public class WeixinInfo implements Serializable {
    private static final long serialVersionUID = 1L;

	/**编码*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "编码")
    private java.lang.String id;
	/**企业ID*/
	@Excel(name = "企业ID", width = 15)
    @ApiModelProperty(value = "企业ID")
    private java.lang.String qyid;
	/**帐号类型 1服务号   3小程序*/
	@Excel(name = "帐号类型 1服务号   3小程序", width = 15)
    @ApiModelProperty(value = "帐号类型 1服务号   3小程序")
    private java.lang.String type;
	/**微信号*/
	@Excel(name = "微信号", width = 15)
    @ApiModelProperty(value = "微信号")
    private java.lang.String wxid;
	/**帐号参数*/
	@Excel(name = "帐号参数", width = 15)
    @ApiModelProperty(value = "帐号参数")
    private java.lang.String appid;
	/**帐号参数*/
	@Excel(name = "帐号参数", width = 15)
    @ApiModelProperty(value = "帐号参数")
    private java.lang.String appsecret;
	/**开发者接口*/
	@Excel(name = "开发者接口", width = 15)
    @ApiModelProperty(value = "开发者接口")
    private java.lang.String url;
	/**验证参数*/
	@Excel(name = "验证参数", width = 15)
    @ApiModelProperty(value = "验证参数")
    private java.lang.String token;
	/**欢迎问候语*/
	@Excel(name = "欢迎问候语", width = 15)
    @ApiModelProperty(value = "欢迎问候语")
    private java.lang.String greetings;
	/**创建时间*/
	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
    private java.util.Date createtime;
	/**编辑时间*/
	@Excel(name = "编辑时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "编辑时间")
    private java.util.Date modtime;
	/**描述*/
	@Excel(name = "描述", width = 15)
    @ApiModelProperty(value = "描述")
    private java.lang.String remark;
	/**0 上墙关闭 1打开*/
	@Excel(name = "0 上墙关闭 1打开", width = 15)
    @ApiModelProperty(value = "0 上墙关闭 1打开")
    private java.lang.Integer isonethewall;
	/**bdelete*/
	@Excel(name = "bdelete", width = 15)
    @ApiModelProperty(value = "bdelete")
    private java.lang.Integer bdelete;
	/**域名地址*/
	@Excel(name = "域名地址", width = 15)
    @ApiModelProperty(value = "域名地址")
    private java.lang.String website;
	/**微信账号名称*/
	@Excel(name = "微信账号名称", width = 15)
    @ApiModelProperty(value = "微信账号名称")
    private java.lang.String wxname;
	/**微信账号二维码*/
	@Excel(name = "微信账号二维码", width = 15)
    @ApiModelProperty(value = "微信账号二维码")
    private java.lang.String wxlogo;
	/**微信支付商户号*/
	@Excel(name = "微信支付商户号", width = 15)
    @ApiModelProperty(value = "微信支付商户号")
    private java.lang.String mchid;
	/**微信支付秘钥*/
	@Excel(name = "微信支付秘钥", width = 15)
    @ApiModelProperty(value = "微信支付秘钥")
    private java.lang.String mchkey;
	/**微信支付证书地址*/
	@Excel(name = "微信支付证书地址", width = 15)
    @ApiModelProperty(value = "微信支付证书地址")
    private java.lang.String cartpath;
	/**s1*/
	@Excel(name = "s1", width = 15)
    @ApiModelProperty(value = "s1")
    private java.lang.String s1;
	/**s2*/
	@Excel(name = "s2", width = 15)
    @ApiModelProperty(value = "s2")
    private java.lang.String s2;
	/**s3*/
	@Excel(name = "s3", width = 15)
    @ApiModelProperty(value = "s3")
    private java.lang.String s3;
}
