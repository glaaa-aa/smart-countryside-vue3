package com.qfy.modules.family.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.qfy.modules.city3d.po.Address;
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
 * @Description: 家庭饮水安全信息
 * @Author: jeecg-boot
 * @Date:   2022-06-02
 * @Version: V1.0
 */
@Data
@TableName("qfy_family_base_water")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="qfy_family_base_water对象", description="家庭饮水安全信息")
public class FamilyBaseWater extends BaseEntity implements Serializable {
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
	/**是否饮水出现安全问题*/
	@Excel(name = "是否存在饮水安全", width = 15,dicCode = "yn")
    @ApiModelProperty(value = "是否存在饮水安全")
    private Integer isWaterSafe;
	/**饮水来源*/
	@Excel(name = "饮水来源", width = 15,dicCode = "waterSourceDict")
    @ApiModelProperty(value = "饮水来源")
    @Dict(dicCode = "waterSourceDict")
    private Integer waterSource;
	/**是否获得家庭水柜补助*/
	@Excel(name = "是否获得家庭水柜补助", width = 20,dicCode = "yn")
    @ApiModelProperty(value = "是否获得家庭水柜补助")
    @Dict(dicCode = "yn")
    private Integer isSubsidy;
	/**家庭水柜建设时间*/
	@Excel(name = "家庭水柜建设时间", width = 15)
    @ApiModelProperty(value = "家庭水柜建设时间")
    private String subsidyDate;
	/**家庭水柜补助金额*/
	@Excel(name = "家庭水柜补助金额", width = 15)
    @ApiModelProperty(value = "家庭水柜补助金额")
    private Double subsidyAmount;
	/**年份*/
	@Excel(name = "数据年份", width = 15)
    @ApiModelProperty(value = "年份")
    private String year;
	/**s1*/
//	@Excel(name = "s1", width = 15)
    @ApiModelProperty(value = "s1")
    private String s1;
	/**s2*/
//	@Excel(name = "s2", width = 15)
    @ApiModelProperty(value = "s2")
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

    @TableField(exist = false)
    private String addId;

    @TableField(exist = false)
    Address mapAddress;

    @TableField(exist = false)
    String  baseId;
}
