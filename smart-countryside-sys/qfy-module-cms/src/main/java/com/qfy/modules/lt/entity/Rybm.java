package com.qfy.modules.lt.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
 * @Description: 人员报名信息
 * @Author: jeecg-boot
 * @Date:   2022-04-05
 * @Version: V1.0
 */
@Data
@TableName("qfy_lt_rybm")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="qfy_lt_rybm对象", description="人员报名信息")
public class Rybm implements Serializable {
    private static final long serialVersionUID = 1L;

	/**编码*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "编码")
    private String id;
	/**会议名称*/
//	@Excel(name = "会议名称", width = 15, dictTable = "qfy_lt_hymc", dicText = "hyname", dicCode = "id")
	@Dict(dictTable = "qfy_lt_hymc", dicText = "hyname", dicCode = "id")
    @ApiModelProperty(value = "会议名称")
    private String hyid;
	/**会议名称*/
	@Excel(name = "会议名称", width = 15)
    @ApiModelProperty(value = "会议名称")
    private String hymc;
	/**姓名*/
	@Excel(name = "姓名", width = 15)
    @ApiModelProperty(value = "姓名")
    private String cname;
	/**性别*/
	@Excel(name = "性别", width = 15, dicCode = "sex")
//	@Dict(dicCode = "sex")
    @ApiModelProperty(value = "性别")
    private String sex;


    /**报名时间*/
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
    @ApiModelProperty(value = "报名时间")
    @Excel(name = "报名时间", width = 15, format = "yyyy-MM-dd HH:mm")
    private Date createTime;

	/**参会类型*/
	@Excel(name = "参会类型", width = 15, dicCode = "hycjType")
	@Dict(dicCode = "hycjType")
    @ApiModelProperty(value = "参会类型")
    private String chlx;

    /**单位*/
    @Excel(name = "单位", width = 15)
    @ApiModelProperty(value = "单位")
    private String dw;

    @Excel(name = "具体机构名称", width = 15)
    private String orgName; //具体机构名称


	/**部门*/
	@Excel(name = "部门", width = 15)
    @ApiModelProperty(value = "部门")
    private String bm;
	/**职务职称*/
	@Excel(name = "职务职称", width = 15)
    @ApiModelProperty(value = "职务职称")
    private String zwzc;
	/**电话*/
	@Excel(name = "电话", width = 15)
    @ApiModelProperty(value = "电话")
    private String phone;
	/**email*/
	@Excel(name = "email", width = 15)
    @ApiModelProperty(value = "email")
    private String emal;
    /**邮编*/
    @Excel(name = "邮编", width = 15)
    @ApiModelProperty(value = "邮编")
    private String yb;

    @Excel(name = "是否住宿", width = 15,dicCode = "yn")
    @Dict(dicCode = "yn")
    private Integer isZhusu; //是否住宿
    /**住宿类型*/
    @Excel(name = "住宿类型", width = 15)
    @ApiModelProperty(value = "住宿类型")
    private String zs;
    /**人数*/
//	@Excel(name = "人数", width = 15)
//    @ApiModelProperty(value = "人数")
    private Integer pcount;
    /**计划到达日期*/
    @Excel(name = "报到日期", width = 15)
    @ApiModelProperty(value = "报到日期")
    private String jhddsj1;

    /**预计到达及离开日期*/
    @Excel(name = "预计到达及离开日期", width = 15)
    @ApiModelProperty(value = "预计到达及离开日期")
    private String jhddsj2;

    /**计划离开日期*/
    @Excel(name = "计划离开日期", width = 15)
    @ApiModelProperty(value = "计划离开日期")
    private String jhlksj1;

    /**计划离开时段*/
    @Excel(name = "计划离开时段", width = 15)
    @ApiModelProperty(value = "计划离开时段")
    private String jhlksj2;

    /**入住天数*/
//	@Excel(name = "入住天数", width = 15)
    @ApiModelProperty(value = "入住天数")
    private Integer rzday;


	/**发票抬头*/
	@Excel(name = "发票抬头", width = 15)
    @ApiModelProperty(value = "发票抬头")
    private String fptt;
	/**纳税人识别号*/
	@Excel(name = "纳税人识别号", width = 15)
    @ApiModelProperty(value = "纳税人识别号")
    private String nsrsbh;
	/**发票邮寄地址*/
	@Excel(name = "发票邮寄地址", width = 15)
    @ApiModelProperty(value = "发票邮寄地址")
    private String fpyjdz;

    @Excel(name = "发票备注", width = 15)
	private String fpRemark; //发票备注

    @Excel(name = "是否缴费", width = 15)
    @Dict(dicCode = "yn")
    private Integer isJf;
    @Excel(name = "缴费金额", width = 15)
    private Double jfJe;

    /**备注*/
    @Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
    private String cdesc;

	/**部门编码*/
    @ApiModelProperty(value = "部门编码")
    private String sysOrgCode;
	/**添加人*/
    @ApiModelProperty(value = "添加人")
    private String createBy;
	/**修改时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;
	/**修改人*/
    @ApiModelProperty(value = "修改人")
    private String updateBy;
	/**是否删除*/

    @ApiModelProperty(value = "是否删除")
    private Integer delFlag;




}
