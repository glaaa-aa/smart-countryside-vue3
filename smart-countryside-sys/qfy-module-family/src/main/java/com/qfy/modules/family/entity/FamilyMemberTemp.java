package com.qfy.modules.family.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.qfy.modules.commons.entity.BaseEntity;
import java.io.Serializable;
import java.sql.Date;
import java.util.Calendar;
import lombok.Data;
import org.jeecg.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Description: 家庭成员表-临时表
 * @Author: jeecg-boot
 * @Date:   2022-06-02
 * @Version: V1.0
 */
@Data
@TableName("qfy_family_member_temp")
public class FamilyMemberTemp extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键ID*/
	@TableId(type = IdType.ASSIGN_ID)
    private String id;

    private Integer tenantId = 1;

	/**户编号*/
	@Excel(name = "户编号", width = 15, orderNum = "0")
    private String familyNumber;
	/**与户主关系*/
	@Excel(name = "与户主关系", width = 15, dicCode = "familyRelDict", orderNum = "1")
    @Dict(dicCode = "familyRelDict")
    private String familyRel;

	/**姓名*/
	@Excel(name = "姓名", width = 15, orderNum = "2")
    private String name;

	/**性别*/
	@Excel(name = "性别", width = 15, dicCode = "sex", orderNum = "3")
    @Dict(dicCode = "sex")
    private String sex;
	/**身份证*/
	@Excel(name = "身份证", width = 15, orderNum = "4")
    private String idCard;

	/**民族*/
	@Excel(name = "民族", width = 15, dicCode = "people_nation", orderNum = "6")
    @Dict(dicCode = "people_nation")
    private String nation;
	/**联系电话*/
	@Excel(name = "联系电话", width = 15, orderNum = "7")
    private String telephone;
	/**在校生情况*/
	@Excel(name = "在校生情况", width = 15, dicCode = "inSchoolDict", orderNum = "8")
    @Dict(dicCode = "inSchoolDict")
    private Integer inSchool;
	/**政治面貌*/
	@Excel(name = "政治面貌", width = 15, dicCode = "zhengzhiMmDict", orderNum = "9")
    @Dict(dicCode = "zhengzhiMmDict")
    private String zhengzhiMm;
	/**文化程度*/
	@Excel(name = "文化程度", width = 15, dicCode = "wenhuaCcDict", orderNum = "10")
    @Dict(dicCode = "wenhuaCcDict")
    private String wenhuaCc;
	/**0否 1是*/
	@Excel(name = "健康状况", width = 15, dicCode = "healthDict", orderNum = "11")
    @Dict(dicCode = "healthDict")
    private Integer health;
	/**残疾属性*/
	@Excel(name = "残疾属性", width = 15, dicCode = "disabilityDict", orderNum = "12")
    @Dict(dicCode = "disabilityDict")
    private String disability;
	/**劳动技能*/
	@Excel(name = "劳动技能", width = 15, dicCode = "laborSkillsDict", orderNum = "13")
    @Dict(dicCode = "laborSkillsDict")
    private String laborSkills;
	/**务工地点*/
	@Excel(name = "务工地点", width = 15, orderNum = "14")
    private String workingAddress;
	/**务工单位*/
	@Excel(name = "务工单位", width = 15, orderNum = "15")
    private String workingDepart;
	/**务工时间*/
	@Excel(name = "务工时间", width = 15, orderNum = "16")
    private String workingDate;
	/**是否参加城乡居民基本医疗保险*/
	@Excel(name = "是否参加居民医保", width = 20, dicCode = "yn", orderNum = "18")
    @Dict(dicCode = "yn")
    private Integer townYiliao;
	/**是否参加城镇职工基本医疗保险*/
	@Excel(name = "是否参加职工医保", width = 20, dicCode = "yn", orderNum = "19")
    @Dict(dicCode = "yn")
    private Integer cityYiliao;
	/**是否参加大病医疗保险*/
	@Excel(name = "是否参加大病医疗", width = 15, dicCode = "yn", orderNum = "22")
    @Dict(dicCode = "yn")
    private Integer dabingYiliao;
	/**是否参加城乡居民基本养老保险*/
	@Excel(name = "是否参加居民养老", width = 20, dicCode = "yn", orderNum = "23")
    @Dict(dicCode = "yn")
    private Integer townYanglao;
	/**是否参加城镇职工基本养老保险*/
	@Excel(name = "是否参加职工养老", width = 20, dicCode = "yn", orderNum = "24")
    @Dict(dicCode = "yn")
    private Integer cityYanglao;
	/**是否享受城乡居民最低生活保障*/
	@Excel(name = "是否享受低保", width = 20, dicCode = "yn", orderNum = "27")
    @Dict(dicCode = "yn")
    private Integer townMinBaozheng;
	/**享受城乡居民最低生活保障档次*/
	@Excel(name = "低保档次", width = 20, dicCode = "minBaozhengLevelDict", orderNum = "29")
    @Dict(dicCode = "minBaozhengLevelDict")
    private Integer minBaozhengLevel;
	/**开始享受城乡居民最低生活保障时间*/
	@Excel(name = "开始享受低保时间", width = 20, orderNum = "28")
    private String minBaozhengDate;
	/**开始获得特困供养时间*/
	@Excel(name = "开始获得特困供养时间", width = 20, orderNum = "31")
    private String supportDate;
	/**是否特困供养人员*/
	@Excel(name = "是否特困供养人员", width = 15, dicCode = "yn", orderNum = "30")
    @Dict(dicCode = "yn")
    private Integer supportMember;
	/**年份*/
	@Excel(name = "数据年份", width = 15, orderNum = "34")
    private String year;
	/**出生日期*/
	@Excel(name = "出生日期", width = 15, orderNum = "5")
    private String s1;
	/**是否健在*/
	@Excel(name = "是否健在", width = 15, dicCode = "yn", orderNum = "31")
    @Dict(dicCode = "yn")
    private String s2;
	/**死亡时间*/
	@Excel(name = "死亡时间", width = 15, orderNum = "32")
    private String s3;
	/**基本医疗参保时间*/
	@Excel(name = "医保参保时间", width = 15, orderNum = "20")
    private String s4;
	/**基本医疗参保凭据（图片）*/
//	@Excel(name = "基本医疗参保凭据（图片）", width = 15, orderNum = "21")
    private String s5;
	/**基本养老参保时间*/
	@Excel(name = "养老参保时间", width = 15, orderNum = "25")
    private String s6;
    /** 是否首次参保 */
    @Excel(name = "是否首次参保", width = 15, dicCode = "yn", orderNum = "26")
    @Dict(dicCode = "yn")
    private Integer firstJoin = 0;
	/**基本养老参保凭据*/
//	@Excel(name = "基本养老参保凭据（图片）", width = 15, orderNum = "26")
    private String s7;
	/**年龄*/
	@Excel(name = "年龄", width = 15 , orderNum = "5")
    private String s8;
	/**区外就业*/
	@Excel(name = "区外就业", width = 15, dicCode = "yn", orderNum = "17")
    @Dict(dicCode = "yn")
    private String s9;
	/**s10*/
//	@Excel(name = "s10", width = 15)
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
}
