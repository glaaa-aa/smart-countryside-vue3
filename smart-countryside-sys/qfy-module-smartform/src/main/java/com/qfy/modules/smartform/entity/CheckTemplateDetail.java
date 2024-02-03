package com.qfy.modules.smartform.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableLogic;
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
 * @Description: 模板权限表
 * @Author: jeecg-boot
 * @Date:   2024-01-22
 * @Version: V1.0
 */
@Data
@TableName("qfy_check_template_detail")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="qfy_check_template_detail对象", description="模板权限表")
public class CheckTemplateDetail implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private String id;
	/**创建人*/
    @ApiModelProperty(value = "创建人")
    private String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建日期")
    private Date createTime;
	/**更新人*/
    @ApiModelProperty(value = "更新人")
    private String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新日期")
    private Date updateTime;
	/**所属部门*/
    @ApiModelProperty(value = "所属部门")
    private String sysOrgCode;
	/**关联模板id*/
	@Excel(name = "关联模板id", width = 15)
    @ApiModelProperty(value = "关联模板id")
    private String templateId;
	/**每台电脑或手机只能答一次*/
	@Excel(name = "每台电脑或手机只能答一次", width = 15)
    @ApiModelProperty(value = "每台电脑或手机只能答一次")
    private Integer effective;
	/**有效性间隔时间*/
	@Excel(name = "有效性间隔时间", width = 15)
    @ApiModelProperty(value = "有效性间隔时间")
    private Integer effectiveTime;
	/**每个IP只能答一次 1是 0否*/
	@Excel(name = "每个IP只能答一次 1是 0否", width = 15)
    @ApiModelProperty(value = "每个IP只能答一次 1是 0否")
    private Integer effectiveIp;
	/**防刷新  1启用 0不启用*/
	@Excel(name = "防刷新  1启用 0不启用", width = 15)
    @ApiModelProperty(value = "防刷新  1启用 0不启用")
    private String refresh;
	/**refresh=1设置*/
	@Excel(name = "refresh=1设置", width = 15)
    @ApiModelProperty(value = "refresh=1设置")
    private String refreshNum;
	/**调查规则   1公开, 2私有, 3令牌*/
	@Excel(name = "调查规则   1公开, 2私有, 3令牌", width = 15)
    @ApiModelProperty(value = "调查规则   1公开, 2私有, 3令牌")
    private String rule;
	/**令牌*/
	@Excel(name = "令牌", width = 15)
    @ApiModelProperty(value = "令牌")
    private String ruleCode;
	/**结束方式1手动结束   2依据结束时间  3依据收到的份数*/
	@Excel(name = "结束方式1手动结束   2依据结束时间  3依据收到的份数", width = 15)
    @ApiModelProperty(value = "结束方式1手动结束   2依据结束时间  3依据收到的份数")
    private String endType;
	/**是否依据收到的份数结束*/
	@Excel(name = "是否依据收到的份数结束", width = 15)
    @ApiModelProperty(value = "是否依据收到的份数结束")
    private String ynEndNum;
	/**收到的份数*/
	@Excel(name = "收到的份数", width = 15)
    @ApiModelProperty(value = "收到的份数")
    private String endNum;
	/**是否依据设置的时间结束*/
	@Excel(name = "是否依据设置的时间结束", width = 15)
    @ApiModelProperty(value = "是否依据设置的时间结束")
    private String ynEndTime;
	/**结束时间*/
	@Excel(name = "结束时间", width = 15)
    @ApiModelProperty(value = "结束时间")
    private String endTime;
	/**问卷说明*/
	@Excel(name = "问卷说明", width = 15)
    @ApiModelProperty(value = "问卷说明")
    private String surveyNote;
	/**问卷下面有多少题目数*/
	@Excel(name = "问卷下面有多少题目数", width = 15)
    @ApiModelProperty(value = "问卷下面有多少题目数")
    private String surveyQuNum;
	/**可以回答的最少选项数目*/
	@Excel(name = "可以回答的最少选项数目", width = 15)
    @ApiModelProperty(value = "可以回答的最少选项数目")
    private String anitemLeastNum;
	/**可以回答的最多选项数目*/
	@Excel(name = "可以回答的最多选项数目", width = 15)
    @ApiModelProperty(value = "可以回答的最多选项数目")
    private String anitemMostNum;
	/**只有邮件邀请唯一链接的受访者可回答  1启用 0不启用*/
	@Excel(name = "只有邮件邀请唯一链接的受访者可回答  1启用 0不启用", width = 15)
    @ApiModelProperty(value = "只有邮件邀请唯一链接的受访者可回答  1启用 0不启用")
    private String mailonly;
	/**显示分享*/
	@Excel(name = "显示分享", width = 15)
    @ApiModelProperty(value = "显示分享")
    private String showShareSurvey;
	/**静态HTML保存路径*/
	@Excel(name = "静态HTML保存路径", width = 15)
    @ApiModelProperty(value = "静态HTML保存路径")
    private String showAnswerDa;
}
