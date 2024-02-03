package com.qfy.modules.smartform.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableLogic;
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
 * @Description: 单选题/下拉题
 * @Author: jeecg-boot
 * @Date:   2024-01-23
 * @Version: V1.0
 */
@Data
@TableName("qfy_check_item_radio")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="qfy_check_item_radio对象", description="单选题/下拉题")
public class CheckItemRadio extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private String id;

	/**关联项id*/
	@Excel(name = "关联项id", width = 15)
    @ApiModelProperty(value = "关联项id")
    private String itemId;
	/**标识*/
	@Excel(name = "标识", width = 15)
    @ApiModelProperty(value = "标识")
    private String optionTitle;
	/**选项内容*/
	@Excel(name = "选项内容", width = 15)
    @ApiModelProperty(value = "选项内容")
    private String optionName;
	/**类型加分或者减分*/
	@Excel(name = "类型加分或者减分", width = 15)
    @ApiModelProperty(value = "类型加分或者减分")
    private String type;
	/**分数*/
	@Excel(name = "分数", width = 15)
    @ApiModelProperty(value = "分数")
    private String fraction;

    /**否带说明  0否  1是*/
    @Excel(name = "否带默认选中 0否  1是", width = 15)
    @ApiModelProperty(value = "否带默认选中  0否  1是")
    private String isMr;
	/**排序号*/
	@Excel(name = "排序号", width = 15)
    @ApiModelProperty(value = "排序号")
    private String orderBy;
	/**是否显示 0不显示*/
	@Excel(name = "是否显示 0不显示", width = 15)
    @ApiModelProperty(value = "是否显示 0不显示")
    private String visibility;
	/**说明的验证方式*/
	@Excel(name = "说明的验证方式", width = 15)
    @ApiModelProperty(value = "说明的验证方式")
    private String checkType;
	/**否带说明  0否  1是*/
	@Excel(name = "否带说明  0否  1是", width = 15)
    @ApiModelProperty(value = "否带说明  0否  1是")
    private String isNote;
	/**说明内容是否必填*/
	@Excel(name = "说明内容是否必填", width = 15)
    @ApiModelProperty(value = "说明内容是否必填")
    private String isRequiredFill;
}
