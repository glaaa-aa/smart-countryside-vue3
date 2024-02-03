package com.qfy.modules.subsidy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.qfy.modules.commons.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecg.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Description: 集中供水水柜
 * @Author: lanls
 * @Date:   2022-08-26
 * @Version: V1.0
 */
@Data
@TableName("qfy_water_tank")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="qfy_water_tank对象", description="集中供水水柜")
public class WaterTank extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
    private String id;
	/**水柜名称*/
	@Excel(name = "水柜名称", width = 15)
    @ApiModelProperty(value = "水柜名称")
    private String name;
	/**蓄水容积(立方)*/
	@Excel(name = "蓄水容积(立方)", width = 15)
    @ApiModelProperty(value = "蓄水容积(立方)")
    private Integer capacity;
	/**建造材料（泥土、砂石、水泥硬化）*/
	@Excel(name = "建造材料", width = 15, dicCode = "buildMaterialDict")
    @ApiModelProperty(value = "建造材料（泥土、砂石、水泥硬化）")
    @Dict(dicCode = "buildMaterialDict")
    private Integer buildMaterial;
	/**建造单位*/
	@Excel(name = "建造单位", width = 15)
    @ApiModelProperty(value = "建造单位")
    private String buildUnit;
	/**建造时间*/
	@Excel(name = "建造时间", width = 15, format = "yyyy-MM")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM")
    @DateTimeFormat(pattern="yyyy-MM")
    @ApiModelProperty(value = "建造时间")
    private Date buildTime;
	/**建造资金（万元）*/
	@Excel(name = "建造资金（万元）", width = 15)
    @ApiModelProperty(value = "建造资金（万元）")
    private Double buildFunds;
	/**相关合同及审核材料附件*/
	@Excel(name = "相关材料照片", width = 15, type = 2)
    @ApiModelProperty(value = "相关合同及审核材料附件")
    private String archivalMaterial;
	/**地图定位坐标地址（id）*/
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
}
