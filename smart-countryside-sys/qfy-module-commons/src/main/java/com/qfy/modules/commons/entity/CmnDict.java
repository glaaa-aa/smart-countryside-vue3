package com.qfy.modules.commons.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecg.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;

import java.io.Serializable;

/**
 * @Description: 关联类数据字典
 * @Author: jeecg-boot
 * @Date:   2023-04-14
 * @Version: V1.0
 */
@Data
@TableName("qfy_system_dict")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="qfy_system_dict对象", description="关联类数据字典")
public class CmnDict implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "id")
    private Integer id;
    /**租户id*/
    @Excel(name = "租户id", width = 15)
    @ApiModelProperty(value = "租户id")
    private Integer tenantId;
	/**字典项文本*/
	@Excel(name = "字典项文本", width = 15)
    @ApiModelProperty(value = "字典项文本")
    private String itemText;
	/**字典项值*/
	@Excel(name = "字典项值", width = 15)
    @ApiModelProperty(value = "字典项值")
    private String itemValue;
	/**描述*/
	@Excel(name = "描述", width = 15)
    @ApiModelProperty(value = "描述")
    private String description;
	/**排序*/
	@Excel(name = "排序", width = 15)
    @ApiModelProperty(value = "排序")
    private Integer sortOrder;
	/**状态（1启用 0不启用）*/
	@Excel(name = "是否有效", width = 15,dicCode = "yn")
    @ApiModelProperty(value = "状态（1启用 0不启用）")
    @Dict(dicCode = "yn")
    private Integer status;

    @Excel(name = "状态颜色", width = 15)
    @ApiModelProperty(value = "状态颜色")
    private String bgColor;
	/**关联类型*/
	@Excel(name = "关联类型", width = 15, dicCode = "DictRefEnum")
    @ApiModelProperty(value = "关联类型")
    //使用枚举和字典 DictRefEnum
    @Dict(dicCode = "DictRefEnum")
    private String refSrc;
	/**关联id*/
	@Excel(name = "关联id", width = 15)
    @ApiModelProperty(value = "关联id")
    private String refData;
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
}
