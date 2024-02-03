package com.qfy.modules.smartform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @Description: 检查项-数字
 * @Author: jeecg-boot
 * @Date:   2022-04-08
 * @Version: V1.0
 */
@Data
@TableName("qfy_check_item_number")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="qfy_check_item_number对象", description="检查项-数字")
public class CheckItemNumber implements Serializable {
    private static final long serialVersionUID = 1L;

	/**编码id*/
	@TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "编码id")
    private java.lang.Integer id;
	/**检查项表单id*/
	@Excel(name = "检查项表单id", width = 15)
    @ApiModelProperty(value = "检查项表单id")
    private java.lang.Integer chkid;

    /**
     * 选项名称(matrix矩阵选项名称)
     */
	private String itemName;

	/**小数位数*/
	@Excel(name = "小数位数", width = 15)
    @ApiModelProperty(value = "小数位数")
    private java.math.BigDecimal deccount;
	/**有效值上限*/
	@Excel(name = "有效值上限", width = 15)
    @ApiModelProperty(value = "有效值上限")
    private java.lang.Double ulvalidnumb;
	/**有效值下限*/
	@Excel(name = "有效值下限", width = 15)
    @ApiModelProperty(value = "有效值下限")
    private java.lang.Double llvalidnumb;
	/**合格值上限*/
	@Excel(name = "合格值上限", width = 15)
    @ApiModelProperty(value = "合格值上限")
    private java.lang.Double ulqualified;
	/**合格值下限*/
	@Excel(name = "合格值下限", width = 15)
    @ApiModelProperty(value = "合格值下限")
    private java.lang.Double llqualified;

	@TableField(exist = false)
    private String checkValue;
}
