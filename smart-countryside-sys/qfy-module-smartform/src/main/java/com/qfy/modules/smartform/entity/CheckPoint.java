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
 * @Description: 巡检点（检查点）
 * @Author: jeecg-boot
 * @Date:   2022-04-08
 * @Version: V1.0
 */
@Data
@TableName("qfy_check_point")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="qfy_check_point对象", description="巡检点（检查点）")
public class CheckPoint implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
    private java.lang.Integer id;
	/**创建人姓名*/
	@Excel(name = "创建人姓名", width = 15)
    @ApiModelProperty(value = "创建人姓名")
    private java.lang.String createName;
	/**创建人登录名称*/
    @ApiModelProperty(value = "创建人登录名称")
    private java.lang.String createBy;
	/**创建时间*/
	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
    private java.util.Date createTime;
	/**更新人名称*/
	@Excel(name = "更新人名称", width = 15)
    @ApiModelProperty(value = "更新人名称")
    private java.lang.String updateName;
	/**更新人登录名称*/
    @ApiModelProperty(value = "更新人登录名称")
    private java.lang.String updateBy;
	/**更新时间*/
	@Excel(name = "更新时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "更新时间")
    private java.util.Date updateTime;
	/**所属部门*/
    @ApiModelProperty(value = "所属部门")
    private java.lang.String sysOrgCode;
	/**所属公司*/
	@Excel(name = "所属公司", width = 15)
    @ApiModelProperty(value = "所属公司")
    private java.lang.String sysCompanyCode;
	/**编号*/
	@Excel(name = "编号", width = 15)
    @ApiModelProperty(value = "编号")
    private java.lang.String serialno;
	/**名称*/
	@Excel(name = "名称", width = 15)
    @ApiModelProperty(value = "名称")
    private java.lang.String name;
	/**点类项*/
	@Excel(name = "点类项", width = 15)
    @ApiModelProperty(value = "点类项")
    private java.lang.String ptype;
	/**是否地理定位*/
	@Excel(name = "是否地理定位", width = 15)
    @ApiModelProperty(value = "是否地理定位")
    private java.lang.Integer isgps;
	/**离线巡检*/
	@Excel(name = "离线巡检", width = 15)
    @ApiModelProperty(value = "离线巡检")
    private java.lang.Integer boffline;
	/**检查说明*/
	@Excel(name = "检查说明", width = 15)
    @ApiModelProperty(value = "检查说明")
    private java.lang.String cdesc;
	/**二维码id*/
	@Excel(name = "二维码id", width = 15)
    @ApiModelProperty(value = "二维码id")
    private java.lang.String qrid;
	/**nfc芯片id*/
	@Excel(name = "nfc芯片id", width = 15)
    @ApiModelProperty(value = "nfc芯片id")
    private java.lang.String nfcid;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
    private java.lang.String remark;
	/**状态*/
	@Excel(name = "状态", width = 15)
    @ApiModelProperty(value = "状态")
    private java.lang.Integer state;
	/**巡检点分组表*/
	@Excel(name = "巡检点分组表", width = 15)
    @ApiModelProperty(value = "巡检点分组表")
    private java.lang.Integer chkPoiGroupId;
	/**负责人*/
	@Excel(name = "负责人", width = 15)
    @ApiModelProperty(value = "负责人")
    private java.lang.String incharge;
	/**负责部门*/
	@Excel(name = "负责部门", width = 15)
    @ApiModelProperty(value = "负责部门")
    private java.lang.String deparment;
	/**负责人id*/
	@Excel(name = "负责人id", width = 15)
    @ApiModelProperty(value = "负责人id")
    private java.lang.String inchargeId;
	/**负责部门id*/
	@Excel(name = "负责部门id", width = 15)
    @ApiModelProperty(value = "负责部门id")
    private java.lang.String departId;
	/**基础分*/
	@Excel(name = "基础分", width = 15)
    @ApiModelProperty(value = "基础分")
    private java.lang.String baseScore;
	/**分值类型 -1为扣分，+1为加分*/
	@Excel(name = "分值类型 -1为扣分，+1为加分", width = 15)
    @ApiModelProperty(value = "分值类型 -1为扣分，+1为加分")
    private java.lang.Integer scoreType;
}
