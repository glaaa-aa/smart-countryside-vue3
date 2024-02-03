package com.qfy.modules.smartform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Description: 检查记录详情
 * @Author: jeecg-boot
 * @Date:   2022-04-08
 * @Version: V1.0
 */
@Data
@TableName("qfy_checkrecode_detail")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="qfy_checkrecode_detail对象", description="检查记录详情")
public class CheckrecodeDetail implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private java.lang.String id;
	/**流程状态*/
	@Excel(name = "流程状态", width = 15)
    @ApiModelProperty(value = "流程状态")
    private java.lang.String bpmStatus;
	/**创建人名称*/
	@Excel(name = "创建人名称", width = 15)
    @ApiModelProperty(value = "创建人名称")
    private java.lang.String createName;
	/**创建人登录名称*/
    @ApiModelProperty(value = "创建人登录名称")
    private java.lang.String createBy;
	/**更新人名称*/
	@Excel(name = "更新人名称", width = 15)
    @ApiModelProperty(value = "更新人名称")
    private java.lang.String updateName;
	/**更新人登录名称*/
    @ApiModelProperty(value = "更新人登录名称")
    private java.lang.String updateBy;
	/**所属部门*/
    @ApiModelProperty(value = "所属部门")
    private java.lang.String sysOrgCode;
	/**所属公司*/
	@Excel(name = "所属公司", width = 15)
    @ApiModelProperty(value = "所属公司")
    private java.lang.String sysCompanyCode;
	/**创建日期*/
	@Excel(name = "创建日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建日期")
    private java.util.Date createTime;
	/**更新日期*/
	@Excel(name = "更新日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "更新日期")
    private java.util.Date updateTime;
	/**项目名称*/
	@Excel(name = "项目名称", width = 15)
    @ApiModelProperty(value = "项目名称")
    private java.lang.String itemName;
	/**项目分类*/
	@Excel(name = "项目分类", width = 15)
    @ApiModelProperty(value = "项目分类")
    private java.lang.String itemType;
	/**标准下限*/
	@Excel(name = "标准下限", width = 15)
    @ApiModelProperty(value = "标准下限")
    private java.lang.String itemMinvalue;
	/**检测值*/
	@Excel(name = "检测值", width = 15)
    @ApiModelProperty(value = "检测值")
    private java.lang.String itemValue;
	/**标准上限*/
	@Excel(name = "标准上限", width = 15)
    @ApiModelProperty(value = "标准上限")
    private java.lang.String itemMaxvalue;
	/**参考图片*/
	@Excel(name = "参考图片", width = 15)
    @ApiModelProperty(value = "参考图片")
    private java.lang.String itemStandardPhoto;
	/**是否合格*/
	@Excel(name = "是否合格", width = 15)
    @ApiModelProperty(value = "是否合格")
    private java.lang.String itemIsQualified;
	/**现场图片*/
	@Excel(name = "现场图片", width = 15)
    @ApiModelProperty(value = "现场图片")
    private java.lang.String itemPhoto;
	/**是否整改*/
	@Excel(name = "是否整改", width = 15)
    @ApiModelProperty(value = "是否整改")
    private java.lang.String itemIsAdjust;
	/**整改后图片*/
	@Excel(name = "整改后图片", width = 15)
    @ApiModelProperty(value = "整改后图片")
    private java.lang.String itemAdjustedPhoto;
	/**是否审核*/
	@Excel(name = "是否审核", width = 15)
    @ApiModelProperty(value = "是否审核")
    private java.lang.String itemIsCheck;
	/**参照标准*/
	@Excel(name = "参照标准", width = 15)
    @ApiModelProperty(value = "参照标准")
    private java.lang.String itemStandard;
	/**执行结果*/
	@Excel(name = "执行结果", width = 15)
    @ApiModelProperty(value = "执行结果")
    private java.lang.String itemResult;
	/**检查时间*/
	@Excel(name = "检查时间", width = 15)
    @ApiModelProperty(value = "检查时间")
    private java.lang.String itemCheckTime;
	/**权重*/
	@Excel(name = "权重", width = 15)
    @ApiModelProperty(value = "权重")
    private java.lang.String itemWeigth;
	/**外键*/
	@Excel(name = "外键", width = 15)
    @ApiModelProperty(value = "外键")
    private java.lang.String itemMainid;
}
