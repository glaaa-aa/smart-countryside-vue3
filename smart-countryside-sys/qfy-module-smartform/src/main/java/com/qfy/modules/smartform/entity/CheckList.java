package com.qfy.modules.smartform.entity;

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
 * @Description: 巡检项记录表
 * @Author: jeecg-boot
 * @Date:   2022-04-08
 * @Version: V1.0
 */
@Data
@TableName("qfy_check_list")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="qfy_check_list对象", description="巡检项记录表")
public class CheckList implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "id")
    private java.lang.Integer id;
	/**检查点记录*/
	@Excel(name = "检查点记录", width = 15)
    @ApiModelProperty(value = "检查点记录")
    private java.lang.Integer inspecPoiId;
	/**表单*/
	@Excel(name = "表单", width = 15)
    @ApiModelProperty(value = "表单")
    private java.lang.Integer chkid;
	/**检查项名称*/
	@Excel(name = "检查项名称", width = 15)
    @ApiModelProperty(value = "检查项名称")
    private java.lang.String itemName;
	/**检查结果*/
	@Excel(name = "检查结果", width = 15)
    @ApiModelProperty(value = "检查结果")
    private java.lang.String results;
	/**类型*/
	@Excel(name = "类型", width = 15)
    @ApiModelProperty(value = "类型")
    private java.lang.String type;
	/**是否合格*/
	@Excel(name = "是否合格", width = 15)
    @ApiModelProperty(value = "是否合格")
    private java.lang.Integer isvalid;
	/**检查得分*/
	@Excel(name = "检查得分", width = 15)
    @ApiModelProperty(value = "检查得分")
    private java.lang.Double score;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
    private java.lang.String remark;
	/**如 -1 为扣分类型，   1 为加分类型*/
	@Excel(name = "如 -1 为扣分类型，   1 为加分类型", width = 15)
    @ApiModelProperty(value = "如 -1 为扣分类型，   1 为加分类型")
    private java.lang.Integer scoreType;
	/**现场图*/
	@Excel(name = "现场图", width = 15)
    @ApiModelProperty(value = "现场图")
    private java.lang.String attachId;
    /** 数据归属 */
	private String belong;
    /** 数据年份 */
	private String years;
}
