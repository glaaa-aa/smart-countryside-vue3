package com.qfy.modules.family.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.qfy.modules.city3d.po.Address;
import com.qfy.modules.commons.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecg.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 家庭生产信息
 * @Author: jeecg-boot
 * @Date:   2022-06-02
 * @Version: V1.0
 */
@Data
@TableName("qfy_family_base_production")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="qfy_family_base_production对象", description="家庭生产信息")
public class FamilyBaseProduction extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键ID")
    private String id;
    /**tenantId*/
//    @Excel(name = "tenantId", width = 15)
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
	@Excel(name = "身份证", width = 20)
    @ApiModelProperty(value = "身份证")
    private String idCard;
	/**耕地面积（亩）*/
	@Excel(name = "旱地面积（亩）", width = 15)
    @ApiModelProperty(value = "旱地面积（亩）")
    private Double gengdArea;
	/**林地面积（亩）*/
	@Excel(name = "林地面积（亩）", width = 15)
    @ApiModelProperty(value = "林地面积（亩）")
    private Double lindArea;
	/**养殖猪数量*/
	@Excel(name = "养殖猪（头）", width = 15)
    @ApiModelProperty(value = "养殖猪数量")
    private Integer totalPig;
	/**养殖牛数量*/
	@Excel(name = "养殖牛（头）", width = 15)
    @ApiModelProperty(value = "养殖牛数量")
    private Integer totalCattle;
	/**养殖羊数量*/
	@Excel(name = "养殖羊（只）", width = 15)
    @ApiModelProperty(value = "养殖羊数量")
    private Integer totalSheep;
	/**养殖鸡数量*/
	@Excel(name = "养殖鸡（只）", width = 15)
    @ApiModelProperty(value = "养殖鸡数量")
    private Integer totalChicken;
	/**其它*/
	@Excel(name = "其它", width = 15)
    @ApiModelProperty(value = "其它")
    private String totalOther;
	/**年份*/
	@Excel(name = "年份", width = 15)
    @ApiModelProperty(value = "年份")
    private String year;
    /**水田面积（亩）*/
    @Excel(name = "水田面积（亩）", width = 15)
    private Double paddyArea;
    /**水田实种面积*/
    @Excel(name = "水田实种面积（亩）", width = 15)
    private Double paddyPlantArea;
    /**旱地实种面积*/
    @Excel(name = "旱地实种面积（亩）", width = 15)
    private Double dryLandPlantArea;
    /**贷款类型*/
    @Excel(name = "贷款类型", width = 15, dicCode = "loanType")
    @Dict(dicCode = "loanType")
    private Integer loanType;
    /**贷款金额*/
    @Excel(name = "贷款金额（元）", width = 15)
    private Double loanAmount;
    /**生态林面积*/
    @Excel(name = "生态林面积（亩）", width = 15)
    private Double ecologicalForestArea;
    /**水果类种植面积*/
    @Excel(name = "水果类种植面积（亩）", width = 15)
    private Double fruitArea;
    /**油茶面积*/
    @Excel(name = "油茶面积（亩）", width = 15)
    private Double teaOilArea;
    /**帮扶企业*/
    @Excel(name = "帮扶企业", width = 30,dictTable = "qfy_cooperative_enterprises", dicCode = "id", dicText = "name")
    @Dict(dictTable = "qfy_cooperative_enterprises", dicCode = "id", dicText = "name")
    private String cooperative;

    /**金融服务*/
    @Dict(dicCode = "product_finance")
    private String finance;
    /**销售产品*/
    @Dict(dicCode = "product_sale")
    private String sale;
    /**代种代养*/
    @Dict(dicCode = "product_plantBreed")
    private String plantBreed;
    /**技术培训*/
    @Dict(dicCode = "product_technicalTraining")
    private String technicalTraining;
    /**品种改良*/
    @Dict(dicCode = "product_varietyImprovement")
    private String varietyImprovement;
    /**社会化服务*/
    @Dict(dicCode = "product_socializationService")
    private String socializationService;
    /**设施建设*/
    @Dict(dicCode = "product_facilityConstruction")
    private String facilityConstruction;
    /**以奖代补*/
    @Dict(dicCode = "product_rewardCompensate")
    private String rewardCompensate;
    /**入股分红*/
    @Dict(dicCode = "product_stockDividends")
    private String stockDividends;
    /**收益分配*/
    @Dict(dicCode = "product_incomeDistribution")
    private String incomeDistribution;
    /**务工*/
    @Dict(dicCode = "product_inWorks")
    private String inWorks;
    /**土地流转*/
    @Dict(dicCode = "product_land")
    private String land;
    /**边贸政策*/
    @Dict(dicCode = "product_trade")
    private String trade;
    /**其它帮扶*/
    @Dict(dicCode = "product_others")
    private String others;

    /**金融服务*/
    private String financeOthers;
    /**销售产品*/
    private String saleOthers;
    /**代种代养*/
    private String plantBreedOthers;
    /**技术培训*/
    private String technicalTrainingOthers;
    /**品种改良*/
    private String varietyImprovementOthers;
    /**社会化服务*/
    private String socializationServiceOthers;
    /**设施建设*/
    private String facilityConstructionOthers;
    /**以奖代补*/
    private String rewardCompensateOthers;
    /**入股分红*/
    private String stockDividendsOthers;
    /**收益分配*/
    private String incomeDistributionOthers;
    /**务工*/
    private String inWorksOthers;
    /**土地流转*/
    private String landOthers;
    /**边贸政策*/
    private String tradeOthers;
    /**其它帮扶*/
    private String othersOthers;

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

    @TableField(exist = false)
    String  qwWorkTotal;

    @TableField(exist = false)
    String  qnWorkTotal;
}
