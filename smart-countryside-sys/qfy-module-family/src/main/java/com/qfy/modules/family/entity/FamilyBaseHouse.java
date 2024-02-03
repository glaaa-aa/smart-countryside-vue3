package com.qfy.modules.family.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.qfy.modules.commons.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecg.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;

import java.io.Serializable;

/**
 * @Description: 家庭住房信息
 * @Author: jeecg-boot
 * @Date:   2022-06-02
 * @Version: V1.0
 */
@Data
@TableName("qfy_family_base_house")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="qfy_family_base_house对象", description="家庭住房信息")
public class FamilyBaseHouse extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键ID")
    private String id;

	/**tenantId*/
//	@Excel(name = "tenantId", width = 15)
    @ApiModelProperty(value = "tenantId")
    private Integer tenantId;

	/**户编号*/
	@Excel(name = "户编号", width = 15)
    @ApiModelProperty(value = "户编号")
    private String familyNumber;
	/**姓名*/
	@Excel(name = "户主姓名", width = 15)
    @ApiModelProperty(value = "姓名")
    private String name;
	/**身份证*/
	@Excel(name = "户主身份证", width = 20)
    @ApiModelProperty(value = "身份证")
    private String idCard;
	/**住房面积*/
	@Excel(name = "住房面积（平米）", width = 15)
    @ApiModelProperty(value = "住房面积（平米）")
    private Double zfArea;
	/**层数*/
	@Excel(name = "层数", width = 15)
    @ApiModelProperty(value = "层数")
    private Integer zfFloor;
	/**住房等级*/
	@Excel(name = "住房等级", width = 15, dicCode = "zfLevelDict")
    @ApiModelProperty(value = "住房等级")
    @Dict(dicCode = "zfLevelDict")
    private Integer zfLevel;
	/**是否危房改造户*/
	@Excel(name = "是否危房改造户", width = 15, dicCode = "yn")
    @ApiModelProperty(value = "是否危房改造户")
    @Dict(dicCode = "yn")
    private Integer isReform;
	/**危房改造时间*/
	@Excel(name = "危房改造时间", width = 15)
    @ApiModelProperty(value = "危房改造时间")
    private String reformDate;
	/**危房改造资金*/
	@Excel(name = "危房改造资金(元)", width = 15)
    @ApiModelProperty(value = "危房改造资金(元)")
    private String reformAmount;
	/**年份*/
	@Excel(name = "数据年份", width = 15)
    @ApiModelProperty(value = "年份")
    private String year;
	/**s1*/
	@Excel(name = "建造时间", width = 15)
    @ApiModelProperty(value = "建造时间")
    private String s1;
	/**s2*/
	@Excel(name = "房屋结构", width = 15, dicCode = "houseStructureDict")
    @ApiModelProperty(value = "房屋结构")
    @Dict(dicCode = "houseStructureDict")
    private String s2;
	/**s3*/
//	@Excel(name = "s3", width = 15)
    @ApiModelProperty(value = "s3")
    private String s3;
	/**s4*/
//	@Excel(name = "s4", width = 15)
    @ApiModelProperty(value = "s4")
    private String s4;
	/**s5*/
//	@Excel(name = "s5", width = 15)
    @ApiModelProperty(value = "s5")
    private String s5;
}
