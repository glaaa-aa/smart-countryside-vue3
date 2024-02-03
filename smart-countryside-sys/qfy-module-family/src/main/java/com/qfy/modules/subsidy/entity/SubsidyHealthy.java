package com.qfy.modules.subsidy.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.qfy.modules.commons.entity.BaseEntity;
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
 * @Description: 健康扶持信息表
 * @Author: jeecg-boot
 * @Date:   2022-07-01
 * @Version: V1.0
 */
@Data
@TableName("qfy_family_subsidy_healthy")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="qfy_family_subsidy_healthy对象", description="健康扶持信息表")
public class SubsidyHealthy extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键ID")
    private String id;
    /**tenantId*/
    @Excel(name = "tenantId", width = 15)
    @ApiModelProperty(value = "tenantId")
    private Integer tenantId;
	/**户编号*/
	@Excel(name = "户编号", width = 15)
    @ApiModelProperty(value = "户编号")
    private String familyNumber;
	/**姓名*/
	@Excel(name = "姓名", width = 15)
    @ApiModelProperty(value = "姓名")
    private String name;
	/**身份证*/
	@Excel(name = "身份证", width = 15)
    @ApiModelProperty(value = "身份证")
    private String idCard;
	/**参加城乡居民基本医疗保险个人缴费补贴*/
	@Excel(name = "参加城乡居民基本医疗保险个人缴费补贴", width = 15)
    @ApiModelProperty(value = "参加城乡居民基本医疗保险个人缴费补贴")
    private String jkYiliaoBxBt;
	/**医疗救助*/
	@Excel(name = "医疗救助", width = 15)
    @ApiModelProperty(value = "医疗救助")
    private String jkYiliaoJz;
	/**办理慢性病卡*/
	@Excel(name = "办理慢性病卡", width = 15)
    @ApiModelProperty(value = "办理慢性病卡")
    private String jkBanliMxbK;
	/**签约家庭医生*/
	@Excel(name = "签约家庭医生", width = 15)
    @ApiModelProperty(value = "签约家庭医生")
    private String jkFamilyDoctor;
	/**住院医疗报销*/
	@Excel(name = "住院医疗报销", width = 15)
    @ApiModelProperty(value = "住院医疗报销")
    private String jkZhuyuanYlBx;
	/**其他*/
	@Excel(name = "其他", width = 15)
    @ApiModelProperty(value = "其他")
    private String jkOther;
	/**年份*/
	@Excel(name = "年份", width = 15)
    @ApiModelProperty(value = "年份")
    private String year;
	/**s1*/
	@Excel(name = "s1", width = 15)
    @ApiModelProperty(value = "s1")
    private String s1;
	/**s2*/
	@Excel(name = "s2", width = 15)
    @ApiModelProperty(value = "s2")
    private String s2;
	/**s3*/
	@Excel(name = "s3", width = 15)
    @ApiModelProperty(value = "s3")
    private String s3;
	/**s4*/
	@Excel(name = "s4", width = 15)
    @ApiModelProperty(value = "s4")
    private String s4;
	/**s5*/
	@Excel(name = "s5", width = 15)
    @ApiModelProperty(value = "s5")
    private String s5;
}
