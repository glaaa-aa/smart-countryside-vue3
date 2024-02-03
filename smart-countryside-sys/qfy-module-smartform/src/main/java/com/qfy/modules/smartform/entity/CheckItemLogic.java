package com.qfy.modules.smartform.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.*;
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
 * @Description: 题目逻辑设置
 * @Author: jeecg-boot
 * @Date:   2024-01-23
 * @Version: V1.0
 */
@Data
@TableName("qfy_check_item_logic")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="qfy_check_item_logic对象", description="题目逻辑设置")
public class CheckItemLogic extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private String id;

	/**回答选择的题I*/
	@Excel(name = "回答选择的题I", width = 15)
    @ApiModelProperty(value = "回答选择的题I")
    private String ckQuid;
	/**回答选择题的选项ID*/
	@Excel(name = "回答选择题的选项ID", width = 15)
    @ApiModelProperty(value = "回答选择题的选项ID")
    private String cgQuitemid;
	/**要跳转的题  (end1提前结束-计入结果  end2提前结束-不计结果)*/
	@Excel(name = "要跳转的题  (end1提前结束-计入结果  end2提前结束-不计结果)", width = 15)
    @ApiModelProperty(value = "要跳转的题  (end1提前结束-计入结果  end2提前结束-不计结果)")
    private String skQuid;
	/**逻辑类型  (1=跳转,2显示)*/
	@Excel(name = "逻辑类型  (1=跳转,2显示)", width = 15)
    @ApiModelProperty(value = "逻辑类型  (1=跳转,2显示)")
    private String logicType;
	/**评分题 ge大于，le小于*/
	@Excel(name = "评分题 ge大于，le小于", width = 15)
    @ApiModelProperty(value = "评分题 ge大于，le小于")
    private String gele;
	/**得分*/
	@Excel(name = "得分", width = 15)
    @ApiModelProperty(value = "得分")
    private String scorenum;
	/**是否显示  1显示 0不显示*/
	@Excel(name = "是否显示  1显示 0不显示", width = 15)
    @ApiModelProperty(value = "是否显示  1显示 0不显示")
    private String visibility;


    @TableField(exist = false)
    private String title;

}
