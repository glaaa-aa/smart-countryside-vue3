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
 * @Description: 产业扶持信息表
 * @Author: jeecg-boot
 * @Date:   2022-07-01
 * @Version: V1.0
 */
@Data
@TableName("qfy_family_subsidy_industry")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="qfy_family_subsidy_industry对象", description="产业扶持信息表")
public class SubsidyIndustry extends BaseEntity implements Serializable {
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
	/**种苗、化肥等物资帮扶*/
	@Excel(name = "种苗、化肥等物资帮扶", width = 15)
    @ApiModelProperty(value = "种苗、化肥等物资帮扶")
    private Double cyWuziBf;
	/**产业奖补*/
	@Excel(name = "产业奖补", width = 15)
    @ApiModelProperty(value = "产业奖补")
    private Double cyChangyeBz;
	/**致富带头人、合作社、龙头企业带动*/
	@Excel(name = "致富带头人、合作社、龙头企业带动", width = 15)
    @ApiModelProperty(value = "致富带头人、合作社、龙头企业带动")
    private Double cyDaitouBz;
	/**乡村旅游*/
	@Excel(name = "乡村旅游", width = 15)
    @ApiModelProperty(value = "乡村旅游")
    private Double cyLvyou;
	/**入股分红*/
	@Excel(name = "入股分红", width = 15)
    @ApiModelProperty(value = "入股分红")
    private Double cyRuguFh;
	/**消费帮扶*/
	@Excel(name = "消费帮扶", width = 15)
    @ApiModelProperty(value = "消费帮扶")
    private Double cyXiaofeiBf;
	/**其他*/
	@Excel(name = "其他", width = 15)
    @ApiModelProperty(value = "其他")
    private String cyOther;
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
