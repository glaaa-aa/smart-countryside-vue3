package com.qfy.modules.family.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.qfy.modules.commons.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecg.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Description: 家庭户临时表
 * @Author: jeecg-boot
 * @Date:   2022-06-02
 * @Version: V1.0
 */
@Data
@TableName("qfy_family_temp")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class FamilyTemp extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
	/**主键ID*/
	@TableId(type = IdType.ASSIGN_ID)
    private String id;
    /**tenantId*/
//    @Excel(name = "tenantId", width = 15)
    private Integer tenantId;
	/**行政村编号*/
	@Excel(name = "行政村",width = 15,dictTable = "qfy_sys_organization",dicCode = "code",dicText = "name")
    @Dict(dictTable = "qfy_sys_organization", dicCode = "code", dicText = "name")
    private String orgCode;
	/**行政村*/
//	@Excel(name = "行政村", width = 15)
    private String orgName;
	/**自然村屯编号*/
	@Excel(name = "自然村屯", width = 15,dictTable = "qfy_sys_organization",dicCode = "code",dicText="name",orderNum="2")
    @Dict(dictTable = "qfy_sys_organization", dicCode = "code", dicText = "name")
    private String aturalCode;
	/**自然村屯*/
//	@Excel(name = "自然村屯", width = 15)
    private String aturalName;

    @Excel(name = "门牌号", width = 15,orderNum="4")
    private String houseNumber;

    @Excel(name = "户主编号", width = 15,orderNum="5")
    private String familyNumber;
	/**户主姓名*/
	@Excel(name = "户主姓名", width = 15,orderNum="5")
    private String name;
	/**性别*/
	@Excel(name = "性别", width = 15,dicCode = "sex",orderNum="7")
    @Dict(dicCode = "sex")
    private String sex;
	/**身份证号*/
	@Excel(name = "身份证号", width = 20,orderNum="8")
    @Dict(dictTable = "qfy_family_member", dicCode = "id_card", dicText = "name")
    private String idCard;
	/**民族*/
	@Excel(name = "民族", width = 15,dicCode = "people_nation",orderNum="9")
    @Dict(dicCode = "people_nation")
    private String nation;
	/**家庭人口总数*/
	@Excel(name = "家庭人口", width = 15,orderNum="10")
    private Integer totalPerson;
	/**务工人数*/
//	@Excel(name = "务工人数", width = 15,orderNum="11")
    private Integer totalWorker;
	/**在校人数*/
//	@Excel(name = "在校人数", width = 15,orderNum="12")
    private Integer totalInSchool;
	/**家庭联系电话*/
	@Excel(name = "家庭联系电话", width = 15,orderNum="13")
    private String telephone;
	/**家庭地址*/
	@Excel(name = "家庭地址", width = 15,orderNum="14")
    private String address;
	/**是否搬迁户*/
	@Excel(name = "是否搬迁户", width = 15,dicCode = "yn",orderNum="15")
    @Dict(dicCode = "yn")
    private Integer isRelocate;
	/**是否居住在大中型搬迁安置区*/
	@Excel(name = "居住搬迁安置区", width = 15,dicCode = "yn",orderNum="16")
    @Dict(dicCode = "yn")
    private Integer isBigRelocate;
	/**安置区机构*/
//	@Excel(name = "安置区机构", width = 15)
    private String relocateOrgName;
	/**relocateOrgCode*/
	@Excel(name="安置区机构",width = 15,dictTable = "qfy_sys_organization",dicCode = "code",dicText="name",orderNum="17")
    @Dict(dictTable = "qfy_sys_organization", dicCode = "code", dicText = "name")
    private String relocateOrgCode;
	/**安置区住址*/
	@Excel(name = "安置区住址", width = 15,orderNum="18")
    private String relocateAddress;
	/**搬迁时间*/
	@Excel(name = "搬迁时间", width = 15, format = "yyyy-MM-dd",orderNum="19")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date relocateDate;
	/**户类型*/
	@Excel(name = "家庭类型", width = 15,dicCode = "family_type",orderNum="20")
    @Dict(dicCode = "family_type")
    private String familyType;
	/**脱贫年度*/
	@Excel(name = "脱贫年度", width = 15,orderNum="21")
    private String tuopinYear;
	/**易返贫致贫户类型*/
	@Excel(name = "易返贫致贫户类型", width = 15,orderNum="22")
    private String monitorType;
	/**风险识别时间 年月*/
	@Excel(name = "风险识别时间", width = 15,orderNum="23")
    private String discernDate;
	/**风险是否消除*/
	@Excel(name = "风险是否消除", width = 15,dicCode = "yn",orderNum="24")
    @Dict(dicCode = "yn")
    private Integer isEliminate;
	/**风险消除时间*/
	@Excel(name = "风险消除时间", width = 15,orderNum="25")
    private String eliminateDate;
	/**维度*/
//	@Excel(name = "维度", width = 15)
    private String lat;
	/**经度*/
//	@Excel(name = "经度", width = 15)
    private String lon;
	/**s1*/
	@Excel(name = "片区", width = 15,dictTable = "qfy_sys_organization",dicCode = "code",dicText = "name",orderNum = "3")
    @Dict(dictTable = "qfy_sys_organization", dicCode = "code", dicText = "name")
    private String s1;
	/**s2*/
	@Excel(name = "户主", width = 15, dictTable = "qfy_family_member", dicCode = "id", dicText = "name",orderNum="26")
    @Dict(dictTable = "qfy_family_member", dicCode = "id", dicText = "name")
    private String s2;
	/**s3*/
//	@Excel(name = "s3", width = 15)
    private String s3;
	/**s4*/
//	@Excel(name = "s4", width = 15)
    private String s4;
	/**s5*/
//	@Excel(name = "s5", width = 15)
    private String s5;
	/**s6*/
//	@Excel(name = "s6", width = 15)
    private String s6;
	/**s7*/
//	@Excel(name = "s7", width = 15)
    private String s7;
	/**s8*/
//	@Excel(name = "s8", width = 15)
    private String s8;
	/**s9*/
//	@Excel(name = "s9", width = 15)
    private String s9;
	/**s10*/
//	@Excel(name = "s10", width = 15)
    private String s10;
}
