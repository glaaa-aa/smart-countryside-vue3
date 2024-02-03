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
import org.jeecg.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Description: 道路交通
 * @Author: lanls
 * @Date:   2022-08-26
 * @Version: V1.0
 */
@Data
@TableName("qfy_traffic_road")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="qfy_traffic_road对象", description="道路交通")
public class TrafficRoad extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
    private String id;
	/**道路名称*/
	@Excel(name = "道路名称", width = 15)
    @ApiModelProperty(value = "道路名称")
    private String name;
	/**道路长度(米)*/
	@Excel(name = "道路长度(米)", width = 15)
    @ApiModelProperty(value = "道路长度(米)")
    private Integer roadLength;
	/**道路材料（泥土、砂石、水泥硬化）*/
	@Excel(name = "建造材料", width = 15, dicCode = "buildMaterialDict")
    @ApiModelProperty(value = "道路材料（泥土、砂石、水泥硬化）")
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
	@Excel(name = "建造总资金（万元）", width = 15)
    @ApiModelProperty(value = "建造资金（万元）")
    private Double buildFunds;
	/**相关合同及审核材料附件*/
	@Excel(name = "相关材料照片", width = 15, type = 2)
    @ApiModelProperty(value = "相关合同及审核材料附件")
    private String archivalMaterial;
	/**地图定位坐标地址（id）*/
//	@Excel(name = "地图定位坐标地址（id）", width = 15)
    @ApiModelProperty(value = "地图定位坐标地址（id）")
    private String addId;
	/**地图定位坐标地址*/
	@Excel(name = "地图定位坐标地址", width = 15)
    @ApiModelProperty(value = "地图定位坐标地址")
    private String s1;
	/**是否有护栏*/
	@Excel(name = "是否有护栏", width = 15, dicCode = "yn")
    @ApiModelProperty(value = "预留字段")
    @Dict(dicCode = "yn")
    private String s2;
	/**护栏长度*/
	@Excel(name = "护栏总长度", width = 15)
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

    /**一期硬化时间*/
    @Excel(name = "一期硬化时间", width = 15, format = "yyyy-MM")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM")
    @DateTimeFormat(pattern="yyyy-MM")
    @ApiModelProperty(value = "一期硬化时间")
    private Date harden1Time;

    /**二期硬化时间*/
    @Excel(name = "二期硬化时间", width = 15, format = "yyyy-MM")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM")
    @DateTimeFormat(pattern="yyyy-MM")
    @ApiModelProperty(value = "二期硬化时间")
    private Date harden2Time;

    /**三期硬化时间*/
    @Excel(name = "三期硬化时间", width = 15, format = "yyyy-MM")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM")
    @DateTimeFormat(pattern="yyyy-MM")
    @ApiModelProperty(value = "三期硬化时间")
    private Date harden3Time;

    /**一期硬化资金*/
    @Excel(name = "一期硬化资金", width = 15)
    @ApiModelProperty(value = "一期硬化资金")
    private Double harden1Money;

    /**二期硬化资金*/
    @Excel(name = "二期硬化资金", width = 15)
    @ApiModelProperty(value = "二期硬化资金")
    private Double harden2Money;

    /**三期硬化资金*/
    @Excel(name = "三期硬化资金", width = 15)
    @ApiModelProperty(value = "三期硬化资金")
    private Double harden3Money;

    /**一期护栏建设时间*/
    @Excel(name = "一期护栏建设时间", width = 15, format = "yyyy-MM")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM")
    @DateTimeFormat(pattern="yyyy-MM")
    @ApiModelProperty(value = "一期护栏建设时间")
    private Date rail1BuildTime;

    /**二期护栏建设时间*/
    @Excel(name = "二期护栏建设时间", width = 15, format = "yyyy-MM")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM")
    @DateTimeFormat(pattern="yyyy-MM")
    @ApiModelProperty(value = "二期护栏建设时间")
    private Date rail2BuildTime;

    /**三期护栏建设时间*/
    @Excel(name = "三期护栏建设时间", width = 15, format = "yyyy-MM")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM")
    @DateTimeFormat(pattern="yyyy-MM")
    @ApiModelProperty(value = "三期护栏建设时间")
    private Date rail3BuildTime;

    /**四期护栏建设时间*/
    @Excel(name = "一期护栏建设时间", width = 15, format = "yyyy-MM")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM")
    @DateTimeFormat(pattern="yyyy-MM")
    @ApiModelProperty(value = "四期护栏建设时间")
    private Date rail4BuildTime;

    /**一期护栏建造资金*/
    @Excel(name = "一期护栏建造资金", width = 15)
    @ApiModelProperty(value = "一期护栏建造资金")
    private Double rail1BuildMoney;

    /**二期护栏建造资金*/
    @Excel(name = "二期护栏建造资金", width = 15)
    @ApiModelProperty(value = "二期护栏建造资金")
    private Double rail2BuildMoney;

    /**三期护栏建造资金*/
    @Excel(name = "三期护栏建造资金", width = 15)
    @ApiModelProperty(value = "三期护栏建造资金")
    private Double rail3BuildMoney;

    /**四期护栏建造资金*/
    @Excel(name = "四期护栏建造资金", width = 15)
    @ApiModelProperty(value = "四期护栏建造资金")
    private Double rail4BuildMoney;

    @TableField(exist = false)
    Address mapAddress;

}
