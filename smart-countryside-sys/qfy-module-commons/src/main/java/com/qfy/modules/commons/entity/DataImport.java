package com.qfy.modules.commons.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;

import java.io.Serializable;

/**
 * @Description: 数据导入临时表
 * @Author: jeecg-boot
 * @Date:   2023-05-23
 * @Version: V1.0
 */
@Data
@TableName("qfy_system_data_import")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="qfy_system_data_import对象", description="数据导入临时表")
public class DataImport extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
    private String id;
	/** 关联类型 */
	@Excel(name = " 关联类型 ", width = 15)
    @ApiModelProperty(value = " 关联类型 ")
    private String refSrc;
	/**导入文件名称*/
	@Excel(name = "导入文件名称", width = 15)
    @ApiModelProperty(value = "导入文件名称")
    private String fileName;
	/**状态*/
	@Excel(name = "状态", width = 15)
    @ApiModelProperty(value = "状态")
    private Integer dataStatus;
	/**导入数据*/
	@Excel(name = "导入数据", width = 15)
    @ApiModelProperty(value = "导入数据")
    private String dataJson;
	/**结果状态*/
	@Excel(name = "结果状态", width = 15)
    @ApiModelProperty(value = "结果状态")
    private String resultStatus;
	/**导入消息*/
	@Excel(name = "导入消息", width = 15)
    @ApiModelProperty(value = "导入消息")
    private String resultMsg;

	/** s1 */
//	@Excel(name = " s1 ", width = 15)
    @ApiModelProperty(value = " s1 ")
    private String s1;
	/** s2 */
//	@Excel(name = " s2 ", width = 15)
    @ApiModelProperty(value = " s2 ")
    private String s2;
	/** s3 */
//	@Excel(name = " s3 ", width = 15)
    @ApiModelProperty(value = " s3 ")
    private String s3;
}
