package com.qfy.modules.family.model;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.qfy.modules.commons.entity.BaseEntity;
import com.qfy.modules.family.entity.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecg.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class FamilyBaseVo extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //年份
    @ApiModelProperty(value = "年份")
    private String year;

    /**主键ID*/
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键ID")
    private String id;
    /**tenantId*/
    @Excel(name = "tenantId", width = 15)
    @ApiModelProperty(value = "tenantId")
    private Integer tenantId;
    /**行政村编号*/
    @Excel(name = "行政村编号", width = 15)
    @ApiModelProperty(value = "行政村编号")
    private String orgCode;
    /**行政村*/
    @Excel(name = "行政村", width = 15)
    @ApiModelProperty(value = "行政村")
    private String orgName;
    /**自然村屯编号*/
    @Excel(name = "自然村屯编号", width = 15)
    @ApiModelProperty(value = "自然村屯编号")
    private String aturalCode;
    /**自然村屯*/
    @Excel(name = "自然村屯", width = 15)
    @ApiModelProperty(value = "自然村屯")
    private String aturalName;

    @Excel(name = "门牌号", width = 15)
    @ApiModelProperty(value = "门牌号")
    private String houseNumber;

    @Excel(name = "户主编号", width = 15)
    @ApiModelProperty(value = "户主编号")
    private String familyNumber;
    /**户主姓名*/
    @Excel(name = "户主姓名", width = 15)
    @ApiModelProperty(value = "户主姓名")
    private String name;
    /**性别*/
    @Excel(name = "性别", width = 15)
    @ApiModelProperty(value = "性别")
    @Dict(dicCode = "sex")
    private String sex;
    /**身份证号*/
    @Excel(name = "身份证号", width = 15)
    @ApiModelProperty(value = "身份证号")
    private String idCard;
    /**民族*/
    @Excel(name = "民族", width = 15)
    @ApiModelProperty(value = "民族")
    private String nation;
    /**家庭人口总数*/
    @Excel(name = "家庭人口总数", width = 15)
    @ApiModelProperty(value = "家庭人口总数")
    private Integer totalPerson;
    /**务工人数*/
    @Excel(name = "务工人数", width = 15)
    @ApiModelProperty(value = "务工人数")
    private Integer totalWorker;
    /**在校人数*/
    @Excel(name = "在校人数", width = 15)
    @ApiModelProperty(value = "在校人数")
    private Integer totalInSchool;
    /**家庭联系电话*/
    @Excel(name = "家庭联系电话", width = 15)
    @ApiModelProperty(value = "家庭联系电话")
    private String telephone;
    /**家庭地址*/
    @Excel(name = "家庭地址", width = 15)
    @ApiModelProperty(value = "家庭地址")
    private String address;
    /**是否搬迁户*/
    @Excel(name = "是否搬迁户", width = 15)
    @ApiModelProperty(value = "是否搬迁户")
    @Dict(dicCode = "yn")
    private Integer isRelocate;
    /**是否居住在大中型搬迁安置区*/
    @Excel(name = "是否居住在大中型搬迁安置区", width = 15)
    @ApiModelProperty(value = "是否居住在大中型搬迁安置区")
    @Dict(dicCode = "yn")
    private Integer isBigRelocate;
    /**安置区机构*/
    @Excel(name = "安置区机构", width = 15)
    @ApiModelProperty(value = "安置区机构")
    private String relocateOrgName;
    /**relocateOrgCode*/
    @Excel(name = "安置区机构编码", width = 15)
    @ApiModelProperty(value = "relocateOrgCode")
    private String relocateOrgCode;
    /**安置区住址*/
    @Excel(name = "安置区住址", width = 15)
    @ApiModelProperty(value = "安置区住址")
    private String relocateAddress;
    /**搬迁时间*/
    @Excel(name = "搬迁时间", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "搬迁时间")
    private Date relocateDate;
    /**户类型*/
    @Excel(name = "家庭类型", width = 15)
    @ApiModelProperty(value = "户类型")
    @Dict(dicCode = "family_type")
    private String familyType;
    /**脱贫年度*/
    @Excel(name = "脱贫年度", width = 15)
    @ApiModelProperty(value = "脱贫年度")
    private String tuopinYear;
    /**易返贫致贫户类型*/
    @Excel(name = "易返贫致贫户类型", width = 15)
    @ApiModelProperty(value = "易返贫致贫户类型")
    private String monitorType;
    /**风险识别时间 年月*/
    @Excel(name = "风险识别时间 年月", width = 15)
    @ApiModelProperty(value = "风险识别时间 年月")
    private String discernDate;
    /**风险是否消除*/
    @Excel(name = "风险是否消除", width = 15)
    @ApiModelProperty(value = "风险是否消除")
    @Dict(dicCode = "yn")
    private Integer isEliminate;
    /**风险消除时间*/
    @Excel(name = "风险消除时间", width = 15)
    @ApiModelProperty(value = "风险消除时间")
    private String eliminateDate;
    /**维度*/
    @Excel(name = "维度", width = 15)
    @ApiModelProperty(value = "维度")
    private String lat;
    /**经度*/
    @Excel(name = "经度", width = 15)
    @ApiModelProperty(value = "经度")
    private String lon;
    /**s1*/
    @Excel(name = "s1", width = 15)
    @ApiModelProperty(value = "s1")
    private String s1;
    /**s2*/
    @Excel(name = "s2", width = 15)
    @ApiModelProperty(value = "s2")
    private String s2;
    /**s3*/
    @Excel(name = "s3", width = 15)
    @ApiModelProperty(value = "s3")
    private String s3;
    /**s4*/
    @Excel(name = "s4", width = 15)
    @ApiModelProperty(value = "s4")
    private String s4;
    /**s5*/
    @Excel(name = "s5", width = 15)
    @ApiModelProperty(value = "s5")
    private String s5;
    /**s6*/
    @Excel(name = "s6", width = 15)
    @ApiModelProperty(value = "s6")
    private String s6;
    /**s7*/
    @Excel(name = "s7", width = 15)
    @ApiModelProperty(value = "s7")
    private String s7;
    /**s8*/
    @Excel(name = "s8", width = 15)
    @ApiModelProperty(value = "s8")
    private String s8;
    /**s9*/
    @Excel(name = "s9", width = 15)
    @ApiModelProperty(value = "s9")
    private String s9;
    /**s10*/
    @Excel(name = "s10", width = 15)
    @ApiModelProperty(value = "s10")
    private String s10;
    //家庭成员
    @ExcelCollection(name="家庭成员管理")
    @ApiModelProperty(value = "家庭成员管理")
    private List<FamilyMember> familyMemberList;




}
