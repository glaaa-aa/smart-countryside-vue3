package com.qfy.modules.family.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.qfy.modules.city3d.po.Address;
import com.qfy.modules.commons.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.sql.Date;
import java.util.Calendar;
import lombok.Data;
import org.jeecg.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Description: 家庭成员管理
 * @Author: jeecg-boot
 * @Date:   2022-06-02
 * @Version: V1.0
 */
@ApiModel(value="qfy_family_member对象", description="家庭成员管理")
@Data
@TableName("qfy_family_member")
public class FamilyMember extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键ID")
    private String id;

	/**tenantId*/
//	@Excel(name = "tenantId", width = 15)
    @ApiModelProperty(value = "tenantId")
    private Integer tenantId;
//	/**createName*/
//	@Excel(name = "createName", width = 15)
//    @ApiModelProperty(value = "createName")
//    private String createName;
//	/**updateName*/
//	@Excel(name = "updateName", width = 15)
//    @ApiModelProperty(value = "updateName")
//    private String updateName;
	/**户编号*/
	@Excel(name = "户编号", width = 15, orderNum = "0")
    @ApiModelProperty(value = "户编号")
    private String familyNumber;
	/**与户主关系*/
	@Excel(name = "与户主关系", width = 15, dicCode = "familyRelDict", orderNum = "1")
    @ApiModelProperty(value = "与户主关系")
    @Dict(dicCode = "familyRelDict")
    private String familyRel;
	/**姓名*/
	@Excel(name = "姓名", width = 15, orderNum = "2")
    @ApiModelProperty(value = "姓名")
    private String name;
	/**性别*/
	@Excel(name = "性别", width = 15, dicCode = "sex", orderNum = "3")
    @ApiModelProperty(value = "性别")
    @Dict(dicCode = "sex")
    private String sex;
	/**身份证*/
	@Excel(name = "身份证", width = 15, orderNum = "4")
    @ApiModelProperty(value = "身份证")
    private String idCard;
	/**民族*/
	@Excel(name = "民族", width = 15, dicCode = "people_nation", orderNum = "6")
    @ApiModelProperty(value = "民族")
    @Dict(dicCode = "people_nation")
    private String nation;
	/**联系电话*/
	@Excel(name = "联系电话", width = 15, orderNum = "7")
    @ApiModelProperty(value = "联系电话")
    private String telephone;
	/**在校生情况*/
	@Excel(name = "在校生情况", width = 15, dicCode = "inSchoolDict", orderNum = "8")
    @ApiModelProperty(value = "在校生情况")
    @Dict(dicCode = "inSchoolDict")
    private Integer inSchool;
	/**政治面貌*/
	@Excel(name = "政治面貌", width = 15, dicCode = "zhengzhiMmDict", orderNum = "9")
    @ApiModelProperty(value = "政治面貌")
    @Dict(dicCode = "zhengzhiMmDict")
    private String zhengzhiMm;
	/**文化程度*/
	@Excel(name = "文化程度", width = 15, dicCode = "wenhuaCcDict", orderNum = "10")
    @ApiModelProperty(value = "文化程度")
    @Dict(dicCode = "wenhuaCcDict")
    private String wenhuaCc;
	/**0否 1是*/
	@Excel(name = "健康状况", width = 15, dicCode = "healthDict", orderNum = "11")
    @ApiModelProperty(value = "健康状况")
    @Dict(dicCode = "healthDict")
    private Integer health;
	/**残疾属性*/
	@Excel(name = "残疾属性", width = 15, dicCode = "disabilityDict", orderNum = "12")
    @ApiModelProperty(value = "残疾属性")
    @Dict(dicCode = "disabilityDict")
    private String disability;
	/**劳动技能*/
	@Excel(name = "劳动技能", width = 15, dicCode = "laborSkillsDict", orderNum = "13")
    @ApiModelProperty(value = "劳动技能")
    @Dict(dicCode = "laborSkillsDict")
    private String laborSkills;
	/**务工地点*/
	@Excel(name = "务工地点", width = 15, orderNum = "14")
    @ApiModelProperty(value = "务工地点")
    private String workingAddress;
	/**务工单位*/
	@Excel(name = "务工单位", width = 15, orderNum = "15")
    @ApiModelProperty(value = "务工单位")
    private String workingDepart;
	/**务工时间*/
	@Excel(name = "务工时间", width = 15, orderNum = "16")
    @ApiModelProperty(value = "务工时间")
//    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM")
//    @DateTimeFormat(pattern="yyyy-MM")
    private String workingDate;
	/**是否参加城乡居民基本医疗保险*/
	@Excel(name = "是否参加居民医保", width = 20, dicCode = "yn", orderNum = "18")
    @ApiModelProperty(value = "是否参加城乡居民基本医疗保险")
    @Dict(dicCode = "yn")
    private Integer townYiliao;
	/**是否参加城镇职工基本医疗保险*/
	@Excel(name = "是否参加职工医保", width = 20, dicCode = "yn", orderNum = "19")
    @ApiModelProperty(value = "是否参加城镇职工基本医疗保险")
    @Dict(dicCode = "yn")
    private Integer cityYiliao;
	/**是否参加大病医疗保险*/
	@Excel(name = "是否参加大病医疗", width = 15, dicCode = "yn", orderNum = "22")
    @ApiModelProperty(value = "是否参加大病医疗保险")
    @Dict(dicCode = "yn")
    private Integer dabingYiliao;
	/**是否参加城乡居民基本养老保险*/
	@Excel(name = "是否参加居民养老", width = 20, dicCode = "yn", orderNum = "23")
    @ApiModelProperty(value = "是否参加城乡居民基本养老保险")
    @Dict(dicCode = "yn")
    private Integer townYanglao;
	/**是否参加城镇职工基本养老保险*/
	@Excel(name = "是否参加职工养老", width = 20, dicCode = "yn", orderNum = "24")
    @ApiModelProperty(value = "是否参加城镇职工基本养老保险")
    @Dict(dicCode = "yn")
    private Integer cityYanglao;
	/**是否享受城乡居民最低生活保障*/
	@Excel(name = "是否享受低保", width = 20, dicCode = "yn", orderNum = "27")
    @ApiModelProperty(value = "是否享受城乡居民最低生活保障")
    @Dict(dicCode = "yn")
    private Integer townMinBaozheng;
	/**享受城乡居民最低生活保障档次*/
	@Excel(name = "低保档次", width = 20, dicCode = "minBaozhengLevelDict", orderNum = "29")
    @ApiModelProperty(value = "享受城乡居民最低生活保障档次")
    @Dict(dicCode = "minBaozhengLevelDict")
    private Integer minBaozhengLevel;
	/**开始享受城乡居民最低生活保障时间*/
	@Excel(name = "开始享受低保时间", width = 20, orderNum = "28")
    @ApiModelProperty(value = "开始享受城乡居民最低生活保障时间")
//    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM")
//    @DateTimeFormat(pattern="yyyy-MM")
    private String minBaozhengDate;
	/**开始获得特困供养时间*/
	@Excel(name = "开始获得特困供养时间", width = 20, orderNum = "31")
    @ApiModelProperty(value = "开始获得特困供养时间")
//    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM")
//    @DateTimeFormat(pattern="yyyy-MM")
    private String supportDate;
	/**是否特困供养人员*/
	@Excel(name = "是否特困供养人员", width = 15, dicCode = "yn", orderNum = "30")
    @ApiModelProperty(value = "是否特困供养人员")
    @Dict(dicCode = "yn")
    private Integer supportMember;
	/**年份*/
	@Excel(name = "数据年份", width = 15, orderNum = "34")
    @ApiModelProperty(value = "年份")
    private String year;
	/**出生日期*/
	@Excel(name = "出生日期", width = 15, orderNum = "5")
    @ApiModelProperty(value = "出生日期")
    private String s1;
	/**是否健在*/
	@Excel(name = "是否健在", width = 15, dicCode = "yn", orderNum = "31")
    @ApiModelProperty(value = "是否健在,0：否，1：是")
    @Dict(dicCode = "yn")
    private String s2;
	/**死亡时间*/
	@Excel(name = "死亡时间", width = 15, orderNum = "32")
    @ApiModelProperty(value = "死亡时间")
    private String s3;
	/**基本医疗参保时间*/
	@Excel(name = "医保参保时间", width = 15, orderNum = "20")
    @ApiModelProperty(value = "基本医疗参保时间")
    private String s4;
	/**基本医疗参保凭据（图片）*/
//	@Excel(name = "基本医疗参保凭据（图片）", width = 15, orderNum = "21")
    @ApiModelProperty(value = "基本医疗参保凭据（图片）")
    private String s5;
	/**基本养老参保时间*/
	@Excel(name = "养老参保时间", width = 15, orderNum = "25")
    @ApiModelProperty(value = "基本养老参保时间")
    private String s6;
	/**基本养老参保凭据*/
//	@Excel(name = "基本养老参保凭据（图片）", width = 15, orderNum = "26")
    @ApiModelProperty(value = "基本养老参保凭据（图片）")
    private String s7;
	/**年龄*/
	@Excel(name = "年龄", width = 15 , orderNum = "5")
    @ApiModelProperty(value = "年龄")
    private String s8;
	/**区外就业*/
	@Excel(name = "区外就业", width = 15, dicCode = "yn", orderNum = "17")
    @ApiModelProperty(value = "区外就业")
    @Dict(dicCode = "yn")
    private String s9;
	/**s10*/
//	@Excel(name = "s10", width = 15)
    @ApiModelProperty(value = "s10")
    private String s10;

    @Excel(name = "户口是否迁出", width = 15, dicCode = "yn", orderNum = "9")
    /** 户口是否迁出 0：否，1是 */
    @Dict(dicCode = "yn")
    private Integer moveOut;

    @Excel(name = "婚否", width = 15, dicCode = "yn", orderNum = "8")
    /** 婚否 0：否，1是 */
    @Dict(dicCode = "yn")
    private Integer marriage;

    /** 户口登记日期 */
    @Excel(name = "上户口日期", width = 15, dicCode = "yn", orderNum = "7")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date registerDate;

    /** 户口注销日期 */
    @Excel(name = "户口注销日期", width = 15, dicCode = "yn", orderNum = "33")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date logoutDate;

    /** 是否服兵役 0：否，1是 */
    @Excel(name = "是否服兵役", width = 15, dicCode = "yn", orderNum = "11")
    @Dict(dicCode = "yn")
    private Integer enlist;

    /** 是否首次参加居民养老保险缴纳 0：否，1是 */
    @Excel(name = "是否首次参保", width = 15, dicCode = "yn", orderNum = "26")
    @Dict(dicCode = "yn")
    private Integer firstJoin = 0;

    public void setIdCard(String idCard) {
        this.idCard = idCard;
        if (idCard.length() == 18){
            String subStr = idCard.substring(6);
            String year = subStr.substring(0,4);
            String month = subStr.substring(4,6);
            String day = subStr.substring(6,8);
            String sex = subStr.substring(10,11);
            // 判断是否是偶数，其中奇数代表男性，偶数代表女性
            if (Integer.valueOf(sex) % 2 == 0){
                this.sex = String.valueOf(2);
            } else {
                this.sex = String.valueOf(1);
            }
            // 计算出生日期
            this.s1 = year + "-" + month + "-" + day;
            Calendar cal = Calendar.getInstance();
            int age = cal.get(Calendar.YEAR) - Integer.valueOf(year);
            //周岁计算
            if (cal.get(Calendar.MONTH) < (Integer.valueOf(month) - 1) ||
                    (cal.get(Calendar.MONTH) == (Integer.valueOf(month) - 1) &&
                            cal.get(Calendar.DATE) < Integer.valueOf(day))) {
                age--;
            }
            // 计算年龄
            this.s8 = String.valueOf(age);
        }
    }
    @TableField(exist = false)
    private String addId;

    @TableField(exist = false)
    Address  mapAddress;

    @TableField(exist = false)
    String  baseId;

    /** 行政村 */
    @TableField(exist = false)
    @Dict(dictTable = "qfy_sys_organization", dicCode = "code", dicText = "name")
    private String village;

    /** 自然屯片区 */
    @TableField(exist = false)
    @Dict(dictTable = "qfy_sys_organization", dicCode = "code", dicText = "name")
    private String tunZone;

    /** 自然屯 */
    @TableField(exist = false)
    @Dict(dictTable = "qfy_sys_organization", dicCode = "code", dicText = "name")
    private String aturalTun;
}
