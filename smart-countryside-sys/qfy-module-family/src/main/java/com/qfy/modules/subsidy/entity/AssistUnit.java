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
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 帮扶单位
 * @Author: jeecg-boot
 * @Date:   2022-12-04
 * @Version: V1.0
 */
@Data
@TableName("qfy_assist_unit")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="qfy_assist_unit对象", description="帮扶单位")
public class AssistUnit extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

	/**编码id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "编码id")
    private String id;

	/**单位名称*/
	@Excel(name = "单位名称", width = 15)
    @ApiModelProperty(value = "单位名称")
    private String name;
	/**单位负责人*/
	@Excel(name = "单位负责人", width = 15)
    @ApiModelProperty(value = "单位负责人")
    private String manager;
	/**联系电话*/
	@Excel(name = "联系电话", width = 15)
    @ApiModelProperty(value = "联系电话")
    private String linkPhone;
	/**预留扩展字段1*/
//	@Excel(name = "预留扩展字段1", width = 15)
    @ApiModelProperty(value = "预留扩展字段1")
    private String ext1;
	/**预留扩展字段2*/
//	@Excel(name = "预留扩展字段2", width = 15)
    @ApiModelProperty(value = "预留扩展字段2")
    private String ext2;
	/**预留扩展字段3*/
//	@Excel(name = "预留扩展字段3", width = 15)
    @ApiModelProperty(value = "预留扩展字段3")
    private String ext3;

    /**备注*/
    @Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
    private String remarks;
}
