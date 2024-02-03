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
 * @Description: 检查项-单选-多选
 * @Author: jeecg-boot
 * @Date:   2022-04-08
 * @Version: V1.0
 */
@Data
@TableName("qfy_check_item_choice")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="选项对象", description="检查项-单选-多选")
public class CheckItemChoice implements Serializable {
    private static final long serialVersionUID = 1L;

	/**编码id*/
	@TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "编码id")
    private java.lang.Integer id;
	/**检查项表单id*/
	@Excel(name = "检查项表单id", width = 15)
    @ApiModelProperty(value = "检查项表单id")
    private java.lang.Integer chkid;
	/**选项标签*/
	@Excel(name = "选项标签", width = 15)
    @ApiModelProperty(value = "选项标签")
    private java.lang.String label;
	/**选项分值*/
	@Excel(name = "选项分值", width = 15)
    @ApiModelProperty(value = "选项分值")
    private java.lang.Double score;
	/**顺序号*/
	@Excel(name = "顺序号", width = 15)
    @ApiModelProperty(value = "顺序号")
    private java.lang.Integer priority;
	/**是否默认选项*/
	@Excel(name = "是否默认选项", width = 15)
    @ApiModelProperty(value = "是否默认选项")
    private java.lang.Integer isdefault;
	/**是否合格*/
	@Excel(name = "是否合格", width = 15)
    @ApiModelProperty(value = "是否合格")
    private java.lang.Integer isvalid;
	/**标准示意图*/
	@Excel(name = "标准示意图", width = 15)
    @ApiModelProperty(value = "标准示意图")
    private java.lang.String vtandardPic;

	@TableField(exist = false)
    private Integer voteCount = 0;// 单个选项的投票次数
}
