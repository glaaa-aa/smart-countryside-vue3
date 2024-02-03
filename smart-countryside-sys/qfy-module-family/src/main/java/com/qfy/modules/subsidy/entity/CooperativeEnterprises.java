package com.qfy.modules.subsidy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
 * @Description: 产业帮扶企业
 * @Author: jeecg-boot
 * @Date:   2022-11-16
 * @Version: V1.0
 */
@Data
@TableName("qfy_cooperative_enterprises")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="产业帮扶企业", description="产业帮扶企业")
public class CooperativeEnterprises extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
    private java.lang.String id;
	/**企业名称*/
	@Excel(name = "企业名称", width = 15)
    @ApiModelProperty(value = "企业名称")
    private java.lang.String name;
	/**企业Logo*/
	@Excel(name = "企业Logo", width = 15)
    @ApiModelProperty(value = "企业Logo")
    private java.lang.String logo;
	/**企业法人*/
	@Excel(name = "企业法人", width = 15)
    @ApiModelProperty(value = "企业法人")
    private java.lang.String legalPerson;
	/**企业执照*/
	@Excel(name = "企业执照", width = 15)
    @ApiModelProperty(value = "企业执照")
    private java.lang.String licenseImage;
	/**是否龙头企业*/
	@Excel(name = "是否龙头企业", width = 15)
    @ApiModelProperty(value = "是否龙头企业")
    @Dict(dicCode = "yn")
    private java.lang.Integer isLeader;
    /** 联系电话 */
    @Excel(name = "联系电话", width = 15)
	private String telephone;
    /** 联系地址 */
    @Excel(name = "联系地址", width = 15)
	private String address;
    /** 经营范围 */
    @Excel(name = "经营范围", width = 15)
	private String businessScope;
}
