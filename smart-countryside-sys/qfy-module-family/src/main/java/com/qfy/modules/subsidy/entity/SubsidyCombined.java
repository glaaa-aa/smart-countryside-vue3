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
 * @Description: 综合保障扶持信息
 * @Author: jeecg-boot
 * @Date:   2022-07-01
 * @Version: V1.0
 */
@Data
@TableName("qfy_family_subsidy_combined")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="qfy_family_subsidy_combined对象", description="综合保障扶持信息")
public class SubsidyCombined extends BaseEntity implements Serializable {
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
	/**低保*/
	@Excel(name = "低保", width = 15)
    @ApiModelProperty(value = "低保")
    private String bzDibao;
	/**特困供养*/
	@Excel(name = "特困供养", width = 15)
    @ApiModelProperty(value = "特困供养")
    private String bzTekunGy;
	/**养老金*/
	@Excel(name = "养老金", width = 15)
    @ApiModelProperty(value = "养老金")
    private String bzYanglaojin;
	/**临时救助*/
	@Excel(name = "临时救助", width = 15)
    @ApiModelProperty(value = "临时救助")
    private String bzLinshiJz;
	/**残疾人补贴*/
	@Excel(name = "残疾人补贴", width = 15)
    @ApiModelProperty(value = "残疾人补贴")
    private String bzCanjirBt;
	/**残疾人补贴*/
	@Excel(name = "残疾人补贴", width = 15)
    @ApiModelProperty(value = "残疾人补贴")
    private String bzFangpinBx;
	/**其他*/
	@Excel(name = "其他", width = 15)
    @ApiModelProperty(value = "其他")
    private String jyOther;
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
