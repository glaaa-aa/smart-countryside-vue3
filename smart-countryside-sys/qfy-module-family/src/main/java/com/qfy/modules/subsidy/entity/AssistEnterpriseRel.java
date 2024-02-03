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
import org.jeecg.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 企业帮扶关系
 * @Author: jeecg-boot
 * @Date:   2022-12-05
 * @Version: V1.0
 */
@Data
@TableName("qfy_assist_enterprise_rel")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="qfy_assist_enterprise_rel对象", description="企业帮扶关系")
public class AssistEnterpriseRel implements Serializable {
    private static final long serialVersionUID = 1L;

	/**编码id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "编码id")
    private java.lang.String id;
	/**帮扶农户编号*/
	@Excel(name = "农户编号", width = 15)
    @ApiModelProperty(value = "帮扶农户编号")
    private java.lang.String familyNumber;
	/**帮扶企业id*/
	@Excel(name = "帮扶企业", width = 20, dictTable = "qfy_cooperative_enterprises", dicCode = "id", dicText = "name")
    @ApiModelProperty(value = "帮扶企业id")
    @Dict(dictTable = "qfy_cooperative_enterprises", dicCode = "id", dicText = "name")
    private java.lang.String eid;
	/**联农带农方式*/
	@Excel(name = "联农带农方式", width = 20, dicCode = "assistMethod")
    @ApiModelProperty(value = "联农带农方式")
    @Dict(dicCode = "assistMethod")
    private java.lang.Integer assistMethod;
	/**数据年份*/
	@Excel(name = "数据年份", width = 15)
    @ApiModelProperty(value = "数据年份")
    private java.lang.String years;
	/**预留扩展字段1*/
//	@Excel(name = "预留扩展字段1", width = 15)
    @ApiModelProperty(value = "预留扩展字段1")
    private java.lang.String ext1;
	/**预留扩展字段2*/
//	@Excel(name = "预留扩展字段2", width = 15)
    @ApiModelProperty(value = "预留扩展字段2")
    private java.lang.String ext2;
	/**预留扩展字段3*/
//	@Excel(name = "预留扩展字段3", width = 15)
    @ApiModelProperty(value = "预留扩展字段3")
    private java.lang.String ext3;
}
