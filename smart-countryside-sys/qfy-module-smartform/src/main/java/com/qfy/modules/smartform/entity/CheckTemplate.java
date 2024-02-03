package com.qfy.modules.smartform.entity;

import java.beans.Transient;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import java.util.List;

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
 * @Description: 模板/问卷
 * @Author: jeecg-boot
 * @Date:   2024-01-22
 * @Version: V1.0
 */
@Data
@TableName("qfy_check_template")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="qfy_check_template对象", description="模板/问卷")
public class CheckTemplate extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private String id;
	/**编号*/
	@Excel(name = "编号", width = 15)
    @ApiModelProperty(value = "编号")
    private String serialno;
	/**名称*/
	@Excel(name = "名称", width = 15)
    @ApiModelProperty(value = "名称")
    private String name;
	/**模板类型*/
	@Excel(name = "模板类型", width = 15)
    @ApiModelProperty(value = "模板类型")
    private String ttype;
	/**是否需绑定巡检点执行*/
	@Excel(name = "是否需绑定巡检点执行", width = 15)
    @ApiModelProperty(value = "是否需绑定巡检点执行")
    @Dict(dicCode = "yn")
    private String toCheckpoint;
	/**是否计分*/
	@Excel(name = "是否计分", width = 15)
    @Dict(dicCode = "yn")
    @ApiModelProperty(value = "是否计分")
    private String bscore;
	/**基础分*/
	@Excel(name = "基础分", width = 15)
    @ApiModelProperty(value = "基础分")
    private String baseScore;
	/**分值类型*/
	@Excel(name = "分值类型", width = 15)
    @Dict(dicCode = "score_type")
    @ApiModelProperty(value = "分值类型")
    private Integer scoreType;
	/**报告模板名称*/
	@Excel(name = "报告模板名称", width = 15)
    @ApiModelProperty(value = "报告模板名称")
    private String docName;
	/**限制用户提交次数、投票次数*/
	@Excel(name = "限制用户提交次数、投票次数", width = 15)
    @ApiModelProperty(value = "限制用户提交次数、投票次数")
    private Integer submitLimit;
	/**模板有效期限、开始时间*/
	@Excel(name = "模板有效期限、开始时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "模板有效期限、开始时间")
    private Date startTime;
	/**模板有效期限、截止时间*/
	@Excel(name = "模板有效期限、截止时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "模板有效期限、截止时间")
    private Date endTime;
	/**是否需要登录,0否，1是*/
	@Excel(name = "是否需要登录,0否，1是", width = 15)
    @ApiModelProperty(value = "是否需要登录,0否，1是")
    private Integer loginRequired;
	/**模板类型*/
	@Excel(name = "模板类型", width = 15, dicCode = "template_type")
	@Dict(dicCode = "template_type")
    @ApiModelProperty(value = "模板类型")
    private String dirType;
	/**题目数*/
	@Excel(name = "题目数", width = 15)
    @ApiModelProperty(value = "题目数")
    private String surveyQunum;

    /**短链接id*/
    @Excel(name = "短链接id", width = 15)
    @ApiModelProperty(value = "短链接id")
    private String Sid;
	/**回答次数*/
	@Excel(name = "回答次数", width = 15)
    @ApiModelProperty(value = "回答次数")
    private String answerNum;
	/**可以回答的最少选项数目*/
	@Excel(name = "可以回答的最少选项数目", width = 15)
    @ApiModelProperty(value = "可以回答的最少选项数目")
    private String anitemLeastNum;
	/**问卷状态  0默认设计状态   1执行中 2结束*/
	@Excel(name = "问卷状态  0默认设计状态   1执行中 2结束", width = 15)
    @ApiModelProperty(value = "问卷状态  0默认设计状态   1执行中 2结束")
    private String surveyState;
	/**是否显示  1显示 0不显示*/
	@Excel(name = "是否显示  1显示 0不显示", width = 15)
    @ApiModelProperty(value = "是否显示  1显示 0不显示")
    private String visibility;
	/**问卷标识 默认 0待审核   1审核通过  2审核未通过*/
	@Excel(name = "问卷标识 默认 0待审核   1审核通过  2审核未通过", width = 15)
    @ApiModelProperty(value = "问卷标识 默认 0待审核   1审核通过  2审核未通过")
    private String surveyTag;
	/**静态HTML保存路径*/
	@Excel(name = "静态HTML保存路径", width = 15)
    @ApiModelProperty(value = "静态HTML保存路径")
    private String htmlPath;
	/**静态HTML保存路径*/
	@Excel(name = "静态HTML保存路径", width = 15)
    @ApiModelProperty(value = "静态HTML保存路径")
    private String jsonPath;
	/**是否公开结果  0不  1公开*/
	@Excel(name = "是否公开结果  0不  1公开", width = 15)
    @ApiModelProperty(value = "是否公开结果  0不  1公开")
    private String viewAnswer;
	/**是否共享问卷  0不共享 1共享*/
	@Excel(name = "是否共享问卷  0不共享 1共享", width = 15)
    @ApiModelProperty(value = "是否共享问卷  0不共享 1共享")
    private String isShare;

    /**是否共享问卷  0不共享 1共享*/
    @Excel(name = "问卷说明", width = 15)
    @ApiModelProperty(value = "问卷说明")
    private String surveyNote;

	/**引用次数*/
	@Excel(name = "引用次数", width = 15)
    @ApiModelProperty(value = "引用次数")
    private String excerptNum;


    @ApiModelProperty(value = "模板/问卷控制逻辑")
    @TableField(exist = false)
    private CheckTemplateDetail checkTemplateDetail;


    @ApiModelProperty(value = "题目项集合")
    @TableField(exist = false)
    private List<CheckItem> questions;


}
