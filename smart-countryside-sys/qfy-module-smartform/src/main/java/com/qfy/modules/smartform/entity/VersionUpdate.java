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
 * @Description: 版本更新
 * @Author: jeecg-boot
 * @Date:   2022-04-08
 * @Version: V1.0
 */
@Data
@TableName("qfy_version_update")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="qfy_version_update对象", description="版本更新")
public class VersionUpdate implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
    private java.lang.Integer id;
	/**创建人名称*/
	@Excel(name = "创建人名称", width = 15)
    @ApiModelProperty(value = "创建人名称")
    private java.lang.String createName;
	/**创建人登录名称*/
    @ApiModelProperty(value = "创建人登录名称")
    private java.lang.String createBy;
	/**创建日期*/
	@Excel(name = "创建日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建日期")
    private java.util.Date createDate;
	/**更新人名称*/
	@Excel(name = "更新人名称", width = 15)
    @ApiModelProperty(value = "更新人名称")
    private java.lang.String updateName;
	/**更新人登录名称*/
    @ApiModelProperty(value = "更新人登录名称")
    private java.lang.String updateBy;
	/**更新日期*/
	@Excel(name = "更新日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "更新日期")
    private java.util.Date updateDate;
	/**所属部门*/
    @ApiModelProperty(value = "所属部门")
    private java.lang.String sysOrgCode;
	/**所属公司*/
	@Excel(name = "所属公司", width = 15)
    @ApiModelProperty(value = "所属公司")
    private java.lang.String sysCompanyCode;
	/**版本名称*/
	@Excel(name = "版本名称", width = 15)
    @ApiModelProperty(value = "版本名称")
    private java.lang.String verName;
	/**软件名称*/
	@Excel(name = "软件名称", width = 15)
    @ApiModelProperty(value = "软件名称")
    private java.lang.String appName;
	/**补丁名称*/
	@Excel(name = "补丁名称", width = 15)
    @ApiModelProperty(value = "补丁名称")
    private java.lang.String patchName;
	/**APK名称*/
	@Excel(name = "APK名称", width = 15)
    @ApiModelProperty(value = "APK名称")
    private java.lang.String apkName;
	/**描述*/
	@Excel(name = "描述", width = 15)
    @ApiModelProperty(value = "描述")
    private java.lang.String remark;
	/**附件ID*/
	@Excel(name = "附件ID", width = 15)
    @ApiModelProperty(value = "附件ID")
    private java.lang.String attchId;
	/**二维码ID*/
	@Excel(name = "二维码ID", width = 15)
    @ApiModelProperty(value = "二维码ID")
    private java.lang.String qrid;
	/**版本编码*/
	@Excel(name = "版本编码", width = 15)
    @ApiModelProperty(value = "版本编码")
    private java.lang.Integer verCode;
}
