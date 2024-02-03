package com.qfy.modules.hr.info.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.qfy.modules.commons.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.jeecg.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 人员档案
 * @Author: jeecg-boot
 * @Date:   2021-12-17
 * @Version: V1.0
 */
@ApiModel(value="qfy_hr_person", description="人员档案")
@Data
@TableName("qfy_hr_person")
public class HrPerson  extends BaseEntity implements Serializable {
	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
    private String id;
	/**员工号*/
	@Excel(name = "员工号", width = 15)
    @ApiModelProperty(value = "员工号")
    private String personNo;
	/**姓名*/
	@Excel(name = "姓名", width = 15)
    @ApiModelProperty(value = "姓名")
    private String cname;
	/**ID卡号*/
	@Excel(name = "ID卡号", width = 15)
    @ApiModelProperty(value = "ID卡号")
    private String cardNo;
	/**英文名*/
    @ApiModelProperty(value = "英文名")
    private String ename;
	/**性别*/
	@Excel(name = "性别", width = 15, dicCode = "sex")
    @Dict(dicCode = "sex")
    @ApiModelProperty(value = "性别")
    private Integer sex;
	/**身份证号*/
	@Excel(name = "身份证号", width = 15)
    @ApiModelProperty(value = "身份证号")
    private String idcard;
	/**身份证生效日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "身份证生效日期")
    private Date effdate;
	/**身份证失效日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "身份证失效日期")
    private Date expdate;
	/**身份证头像照*/
    @ApiModelProperty(value = "身份证头像照")
    private String sfzTxImg;
	/**身份证国徽照*/
    @ApiModelProperty(value = "身份证国徽照")
    private String sfzGhImg;
	/**国籍*/
    @ApiModelProperty(value = "国籍")
    private String guoji;
	/**籍贯*/
	@Excel(name = "籍贯", width = 15)
    @ApiModelProperty(value = "籍贯")
    private String jiguan;
	/**民族*/
	@Excel(name = "民族", width = 15)
    @ApiModelProperty(value = "民族")
    private String minzu;
	/**出生日期*/
	@Excel(name = "出生日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "出生日期")
    private Date birth;
	/**年龄*/
	@Excel(name = "年龄", width = 15)
    @ApiModelProperty(value = "年龄")
    private String age;
	/**婚姻状况*/
	@Excel(name = "婚姻状况", width = 15, dicCode = "hy")
    @Dict(dicCode = "hy")
    @ApiModelProperty(value = "婚姻状况")
    private String married;
	/**政治面貌*/
	@Excel(name = "政治面貌", width = 15)
    @ApiModelProperty(value = "政治面貌")
    private String zhengzhi;
	/**文化程度*/
	@Excel(name = "文化程度", width = 15, dicCode = "whcd")
    @Dict(dicCode = "whcd")
    @ApiModelProperty(value = "文化程度")
    private String wenhua;
	/**联系电话*/
	@Excel(name = "联系电话", width = 15)
    @ApiModelProperty(value = "联系电话")
    private String phone;
	/**手机*/
	@Excel(name = "手机", width = 15)
    @ApiModelProperty(value = "手机")
    private String mobile;
	/**电子信箱*/
	@Excel(name = "电子信箱", width = 15)
    @ApiModelProperty(value = "电子信箱")
    private String email;
	/**其它联系方式*/
    @ApiModelProperty(value = "其它联系方式")
    private String otheraddr;
	/**参加工作日期*/
    @ApiModelProperty(value = "参加工作日期")
    private String fstjobd;
	/**本单位入职日期*/
    @ApiModelProperty(value = "本单位入职日期")
    private String joinDate;
	/**总工龄*/
	@Excel(name = "总工龄", width = 15)
    @ApiModelProperty(value = "总工龄")
    private String gongling1;
	/**本单位工龄*/
	@Excel(name = "本单位工龄", width = 15)
    @ApiModelProperty(value = "本单位工龄")
    private String gongling2;
	/**本单位到职日期*/
	@Excel(name = "本单位到职日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "本单位到职日期")
    private Date dzDate;
	/**特长*/
    @ApiModelProperty(value = "特长")
    private String techang;
	/**紧急联系人*/
	@Excel(name = "紧急联系人", width = 15)
    @ApiModelProperty(value = "紧急联系人")
    private String jinjiLink;
	/**紧急联系电话*/
	@Excel(name = "紧急联系电话", width = 15)
    @ApiModelProperty(value = "紧急联系电话")
    private String jinjiPhone;
	/**紧急联系地址*/
	@Excel(name = "紧急联系地址", width = 15)
    @ApiModelProperty(value = "紧急联系地址")
    private String jinjiAddr;
	/**紧急联系人关系*/
	@Excel(name = "紧急联系人关系", width = 15)
    @ApiModelProperty(value = "紧急联系人关系")
    private String jinjiGx;
	/**照片*/
    @ApiModelProperty(value = "照片")
    private String photo;
	/**在职状态*/
	@Excel(name = "在职状态", width = 15, dicCode = "zzt")
    @Dict(dicCode = "zzt")
    @ApiModelProperty(value = "在职状态")
    private String zaizhi;
	/**档案所在地*/
    @ApiModelProperty(value = "档案所在地")
    private String dangan;
	/**户口所在地*/
    @ApiModelProperty(value = "户口所在地")
    private String hukoudi;
	/**户口类型*/
    @ApiModelProperty(value = "户口类型")
    private String hukoutype;
	/**公积金号码*/
    @ApiModelProperty(value = "公积金号码")
    private String gjjNo;
	/**社保号码*/
    @ApiModelProperty(value = "社保号码")
    private String shebaoNo;
	/**备用字段1*/
    @ApiModelProperty(value = "备用字段1")
    private String c1;
	/**备用字段2*/
    @ApiModelProperty(value = "备用字段2")
    @Dict(dicCode = "yn")
    private String c2;
	/**备用字段3*/
    @ApiModelProperty(value = "备用字段3")
    @Dict(dicCode = "yn")
    private String c3;
	/**备用字段4*/
    @ApiModelProperty(value = "备用字段4")
    private String c4;
	/**备用字段5*/
    @ApiModelProperty(value = "备用字段5")
    private String c5;
	/**备用字段6*/
    @ApiModelProperty(value = "备用字段6")
    private String n1;
	/**备用字段7*/
    @ApiModelProperty(value = "备用字段7")
    private String n2;
	/**备用字段8*/
    @ApiModelProperty(value = "备用字段8")
    private String n3;
	/**备用字段9*/
    @ApiModelProperty(value = "备用字段9")
    private String d1;
	/**备用字段10*/
    @ApiModelProperty(value = "备用字段10")
    private String d2;
	/**用户表id*/
    @ApiModelProperty(value = "用户表id")
    private String userId;
	/**职务*/
	@Excel(name = "职务", width = 15)
    @ApiModelProperty(value = "职务")
    private String post;

    /**家庭地址*/
    @Excel(name = "家庭地址", width = 15)
    @ApiModelProperty(value = "家庭地址")
    private String homeaddr;

    @ApiModelProperty(value = "行政区划")
    @Dict(dictTable = "qfy_sys_organization", dicText = "name", dicCode = "code")
    private String orgCode;
	/**行政区划  对应qfy_sys_organization*/
	@Excel(name = "行政区划", width = 15)
    @ApiModelProperty(value = "行政区划")
    private String orgName;

    /**所属部门 对应sys_depart*/
    @ApiModelProperty(value = "部门")
    @Dict(dictTable = "sys_depart", dicText = "depart_name", dicCode = "org_code")
    private String departCode;
    /**所属部门*/
    @ApiModelProperty(value = "所属组织机构")
    @Excel(name = "所属组织机构", width = 15)
    private String departName;

    @ApiModelProperty(value = "所属租户")
    private Integer tenantId;

}
