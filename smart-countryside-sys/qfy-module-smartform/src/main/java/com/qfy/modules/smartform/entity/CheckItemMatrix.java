package com.qfy.modules.smartform.entity;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * 表单项-数字矩阵
 *
 * @author lanls
 * @version 1.0
 * @date 2022/5/16 22:24
 */
@Data
//@TableName("qfy_check_item_matrix")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class CheckItemMatrix implements Serializable {

    private static final long serialVersionUID = 8175690741239826826L;

    /**编码id*/
//    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "id")
    private Integer id;
    /**检查项表单id*/
    @Excel(name = "检查项表单id", width = 15)
    @ApiModelProperty(value = "表单项id")
    private Integer chkid;

    /**
     * 默认值
     */
    @ApiModelProperty(value = "默认值")
    private Double defaultValue = 0d;

    /**
     * 最小值
     */
    @ApiModelProperty(value = "最小值")
    private Double minimum = 0d;

    /**
     * 最大值
     */
    @ApiModelProperty(value = "最大值")
    private Double maximum;
}
