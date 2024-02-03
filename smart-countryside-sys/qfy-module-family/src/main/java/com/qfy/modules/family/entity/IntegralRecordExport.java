package com.qfy.modules.family.entity;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import org.jeecg.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 积分记录导出实体对象
 * @author lanls
 * @version v1.0.0
 * @since 2023/11/27 15:31
 */
@Data
public class IntegralRecordExport implements Serializable {

    private static final long serialVersionUID = -1004630081210421605L;

    private String id;

    /**户编号*/
    @Excel(name = "户编号", width = 15)
    private String familyNumber;

    /**户主*/
    @Excel(name = "户主", width = 15,dictTable = "qfy_family_member", dicCode = "id_card", dicText = "name")
    @Dict(dictTable = "qfy_family_member", dicCode = "id_card", dicText = "name")
    private String familyManager;

    /**行政村*/
    @Excel(name = "行政村", width = 15,dictTable = "qfy_sys_organization", dicCode = "code", dicText = "name")
    @Dict(dictTable = "qfy_sys_organization", dicCode = "code", dicText = "name")
    private String village;

    /**自然屯*/
    @Excel(name = "片区", width = 15,dictTable = "qfy_sys_organization", dicCode = "code", dicText = "name")
    @Dict(dictTable = "qfy_sys_organization", dicCode = "code", dicText = "name")
    private String naturalTun;

    /**村民小组/生产队*/
    @Excel(name = "自然屯", width = 15,dictTable = "qfy_sys_organization", dicCode = "code", dicText = "name")
    @Dict(dictTable = "qfy_sys_organization", dicCode = "code", dicText = "name")
    private String team;

    /** 评分类别-1：加分；2：扣分 */
    @Excel(name = "评分类别", width = 15, dicCode = "score_category")
    @Dict(dicCode = "score_category")
    private String category;

    /**积分项id（加(扣)分项）*/
    @Excel(name = "加（扣）分项", width = 15, dictTable = "qfy_check_item", dicCode = "id", dicText = "name")
    @Dict(dictTable = "qfy_check_item", dicCode = "id", dicText = "name")
    private String chkId;

    /**加分事项*/
    @Excel(name = "加（扣）分事由", width = 30)
    private String reason;

    /**加分分值*/
    @Excel(name = "加（扣）分分值", width = 15)
    private Float score;

    /** 登记日期 */
    @Excel(name = "登记日期", width = 15, format = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date entryDate;

    /** 数据年份 */
    @Excel(name = "数据年份", width = 15)
    private String dataYear;

    /** 户主姓名 */
    private String name;

    /** 附件文件路径，以逗号分隔 */
    private String attachment;

    /**备注*/
    @Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
    private String remarks;
}
