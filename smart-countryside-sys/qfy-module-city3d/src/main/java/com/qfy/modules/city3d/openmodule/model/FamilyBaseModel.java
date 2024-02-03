package com.qfy.modules.city3d.openmodule.model;

import lombok.Data;

import java.util.Date;

/**
 * @author lanls
 * @version 1.0
 * @description:
 * @date 2022/9/1 20:37
 */
@Data
public class FamilyBaseModel {

    private String id;
    /**tenantId*/
    private Integer tenantId;
    /**行政村编号*/
    private String orgCode;
    /**行政村*/
    private String orgName;
    /**自然村屯编号*/
    private String aturalCode;
    /**自然村屯*/
    private String aturalName;

    private String houseNumber;

    private String familyNumber;
    /**户主姓名*/
    private String name;
    /**性别*/
    private String sex;
    /**身份证号*/
    private String idCard;
    /**民族*/
    private String nation;
    /**家庭人口总数*/
    private Integer totalPerson;
    /**务工人数*/
    private Integer totalWorker;
    /**在校人数*/
    private Integer totalInSchool;
    /**家庭联系电话*/
    private String telephone;
    /**家庭地址*/
    private String address;
    /**是否搬迁户*/
    private Integer isRelocate;
    /**是否居住在大中型搬迁安置区*/
    private Integer isBigRelocate;
    /**安置区机构*/
    private String relocateOrgName;
    /**relocateOrgCode*/
    private String relocateOrgCode;
    /**安置区住址*/
    private String relocateAddress;
    /**搬迁时间*/
    private Date relocateDate;
    /**户类型*/
    private String familyType;
    /**脱贫年度*/
    private String tuopinYear;
    /**易返贫致贫户类型*/
    private String monitorType;
    /**风险识别时间 年月*/
    private String discernDate;
    /**风险是否消除*/
    private Integer isEliminate;
    /**风险消除时间*/
    private String eliminateDate;
    /**维度*/
    private String lat;
    /**经度*/
    private String lon;
    /**片区*/
    private String s1;
    /**户主id*/
    private String s2;
    /**s3*/
    private String s3;
    /**s4*/
    private String s4;
    /**s5*/
    private String s5;
    /**s6*/
    private String s6;
    /**s7*/
    private String s7;
    /**s8*/
    private String s8;
    /**s9*/
    private String s9;
    /**s10*/
    private String s10;

    /** 地址id **/
    private String addId;
}
