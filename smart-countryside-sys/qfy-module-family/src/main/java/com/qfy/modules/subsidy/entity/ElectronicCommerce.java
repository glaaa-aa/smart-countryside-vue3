package com.qfy.modules.subsidy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.qfy.modules.city3d.po.Address;
import com.qfy.modules.commons.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Description: 电商营业点
 * @Author: lanls
 * @Date:   2022-08-26
 * @Version: V1.0
 */
@Data
@TableName("qfy_electronic_commerce")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="qfy_electronic_commerce对象", description="电商营业点")
public class ElectronicCommerce extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
    private String id;
	/**电商名称*/
	@Excel(name = "电商网点名称", width = 15)
    @ApiModelProperty(value = "电商网点名称")
    private String name;
	/**归属单位*/
	@Excel(name = "归属单位", width = 15)
    @ApiModelProperty(value = "归属单位")
    private String buildUnit;
	/**建立时间*/
	@Excel(name = "建立时间", width = 15, format = "yyyy-MM")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM")
    @DateTimeFormat(pattern="yyyy-MM")
    @ApiModelProperty(value = "建立时间")
    private Date buildTime;
	/**投资资金（万元）*/
	@Excel(name = "投资资金（万元）", width = 15)
    @ApiModelProperty(value = "投资资金（万元）")
    private Double buildFunds;
	/**相关合同及审核材料附件*/
	@Excel(name = "相关材料照片", width = 15, type = 2)
    @ApiModelProperty(value = "相关材料照片")
    private String archivalMaterial;
	/**地图定位坐标地址（id）*/
//	@Excel(name = "地图定位坐标地址（id）", width = 15)
    @ApiModelProperty(value = "地图定位坐标地址（id）")
    private String addId;
	/**地图定位坐标地址*/
	@Excel(name = "地图定位坐标地址", width = 15)
    @ApiModelProperty(value = "地图定位坐标地址")
    private String s1;
	/**预留字段*/
//	@Excel(name = "预留字段", width = 15)
    @ApiModelProperty(value = "预留字段")
    private String s2;
	/**预留字段*/
//	@Excel(name = "预留字段", width = 15)
    @ApiModelProperty(value = "预留字段")
    private String s3;
	/**预留字段*/
//	@Excel(name = "预留字段", width = 15)
    @ApiModelProperty(value = "预留字段")
    private String s4;
	/**预留字段*/
//	@Excel(name = "预留字段", width = 15)
    @ApiModelProperty(value = "预留字段")
    private String s5;

    @TableField(exist = false)
    Address mapAddress;
}
