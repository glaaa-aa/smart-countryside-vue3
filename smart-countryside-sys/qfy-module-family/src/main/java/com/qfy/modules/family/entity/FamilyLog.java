package com.qfy.modules.family.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.qfy.modules.commons.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;

import java.io.Serializable;

/**
 * @Description: 家庭日志管理
 * @Author: jeecg-boot
 * @Date:   2022-06-02
 * @Version: V1.0
 */
@Data
@TableName("qfy_family_log")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="qfy_family_log对象", description="qfy_family_log")
public class FamilyLog extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键ID")
    private String id;

	/**tenantId*/
	@Excel(name = "tenantId", width = 15)
    @ApiModelProperty(value = "tenantId")
    private Integer tenantId;
	/**createName*/
//	@Excel(name = "createName", width = 15)
//    @ApiModelProperty(value = "createName")
//    private String createName;
//	/**updateName*/
//	@Excel(name = "updateName", width = 15)
//    @ApiModelProperty(value = "updateName")
//    private String updateName;

	/**户编号*/
	@Excel(name = "户编号", width = 15)
    @ApiModelProperty(value = "户编号")
    private String familyNumber;
	/**姓名*/
	@Excel(name = "姓名", width = 15)
    @ApiModelProperty(value = "姓名")
    private String name;
	/**身份证*/
	@Excel(name = "身份证", width = 15)
    @ApiModelProperty(value = "身份证")
    private String idCard;
	/**变更内容*/
	@Excel(name = "变更内容", width = 15)
    @ApiModelProperty(value = "变更内容")
    private String logContent;
	/**变更日期*/
	@Excel(name = "变更日期", width = 15)
    @ApiModelProperty(value = "变更日期")
    private String logDate;
	/**变更数据类型*/
	@Excel(name = "变更数据类型", width = 15)
    @ApiModelProperty(value = "变更数据类型")
    private String logDataType;
	/**变更后数据*/
	@Excel(name = "变更后数据", width = 15)
    @ApiModelProperty(value = "变更后数据")
    private String aftreData;
	/**变更前数据*/
	@Excel(name = "变更前数据", width = 15)
    @ApiModelProperty(value = "变更前数据")
    private String befourData;
	/**年份*/
	@Excel(name = "年份", width = 15)
    @ApiModelProperty(value = "年份")
    private String year;
	/**s1*/
	@Excel(name = "s1", width = 15)
    @ApiModelProperty(value = "s1")
    private String s1;
	/**s2*/
	@Excel(name = "s2", width = 15)
    @ApiModelProperty(value = "s2")
    private String s2;
}
