package com.qfy.modules.subsidy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 帮扶结对关系
 * @Author: jeecg-boot
 * @Date:   2022-12-05
 * @Version: V1.0
 */
@Data
@TableName("qfy_assist_rel")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="qfy_assist_rel对象", description="帮扶结对关系")
public class AssistRel implements Serializable {
    private static final long serialVersionUID = 1L;

	/**编码id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "编码id")
    private String id;
	/**帮扶联系人id*/
	@Excel(name = "帮扶联系人id", width = 15)
    @ApiModelProperty(value = "帮扶联系人id")
    private String userId;
	/**帮扶农户编号*/
	@Excel(name = "帮扶农户编号", width = 15)
    @ApiModelProperty(value = "帮扶农户编号")
    private String familyNumber;
	/**数据年份*/
	@Excel(name = "数据年份", width = 15)
    @ApiModelProperty(value = "数据年份")
    private String years;
	/**预留扩展字段1*/
	@Excel(name = "预留扩展字段1", width = 15)
    @ApiModelProperty(value = "预留扩展字段1")
    private String ext1;
	/**预留扩展字段2*/
	@Excel(name = "预留扩展字段2", width = 15)
    @ApiModelProperty(value = "预留扩展字段2")
    private String ext2;
	/**预留扩展字段3*/
	@Excel(name = "预留扩展字段3", width = 15)
    @ApiModelProperty(value = "预留扩展字段3")
    private String ext3;
}
