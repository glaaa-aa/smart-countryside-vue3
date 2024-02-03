package com.qfy.modules.dissension.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.qfy.modules.commons.entity.BaseEntity;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: 纠纷负责处理人员表
 * @Author: jeecg-boot
 * @Date:   2023-12-26
 * @Version: V1.0
 */
@ApiModel(value="q_repair_dissension_man对象", description="纠纷负责处理人员表")
@Data
@TableName("qfy_andon_repair_mo_man")
public class AndonRepairMoMan extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private String id;
    /**矛盾纠纷类型*/
    @Excel(name = "矛盾纠纷类型", width = 15)
    @ApiModelProperty(value = "矛盾纠纷类型")
    private String sendType;
	/**矛盾纠纷编号*/
    @Excel(name = "矛盾纠纷编号", width = 15)
    @ApiModelProperty(value = "矛盾纠纷编号")
    private String moCode;
    /**负责人员部门*/
    @Excel(name = "负责人员部门", width = 15)
    @ApiModelProperty(value = "负责人员部门")
    private String dutyDepart;
	/**人员id*/
	@Excel(name = "人员id", width = 15)
    @ApiModelProperty(value = "人员id")
    private String dutyManId;
	/**责任人*/
	@Excel(name = "责任人", width = 15)
    @ApiModelProperty(value = "责任人")
    private String dutyMan;
    /**开始处理时间*/
	@Excel(name = "开始处理时间", width = 15)
    @ApiModelProperty(value = "开始处理时间")
    private String weixStdate;
    /**开始处理时间*/
	@Excel(name = "完成处理时间", width = 15)
    @ApiModelProperty(value = "完成处理时间")
    private String weixEddate;
    /**处理用时(分钟) 完成时间-开始时间*/
	@Excel(name = "处理用时(分钟) 完成时间-开始时间", width = 15)
    @ApiModelProperty(value = "处理用时(分钟) 完成时间-开始时间")
    private Integer weixTimes;
    /**实际处理工时 独立核算时，可能用得到*/
	@Excel(name = "实际处理工时 独立核算时，可能用得到", width = 15)
    @ApiModelProperty(value = "实际处理工时 独立核算时，可能用得到")
    private Integer stopTimes;
    /**维修费用 如委外或独立核算时，可能需要计算费用*/
    @Excel(name = "维修费用 如委外或独立核算时，可能需要计算费用", width = 15)
    @ApiModelProperty(value = "维修费用 如委外或独立核算时，可能需要计算费用")
    private Double weixAmount;
    /**内容或步骤 该人维修动作的内容或步骤*/
    @Excel(name = "内容或步骤 该人维修动作的内容或步骤", width = 15)
    @ApiModelProperty(value = "内容或步骤 该人维修动作的内容或步骤")
    private Double weixContent;
	/**单位*/
	@Excel(name = "单位", width = 15)
    @ApiModelProperty(value = "单位")
    private String unit;
	/**单位地点*/
	@Excel(name = "单位地点", width = 15)
    @ApiModelProperty(value = "单位地点")
    private String address;
	/**联系电话*/
	@Excel(name = "联系电话", width = 15)
    @ApiModelProperty(value = "联系电话")
    private String phone;
    /**联系电话*/
	@Excel(name = "主要负责人 0：否，1：是", width = 15)
    @ApiModelProperty(value = "主要负责人 0：否，1：是")
    private Integer princiPal;
	/**租户id*/
	@Excel(name = "租户id", width = 15)
    @ApiModelProperty(value = "租户id")
    private Integer tenantId;


}
