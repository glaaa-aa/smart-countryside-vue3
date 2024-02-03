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
 * @Description: 巡检结果详情
 * @Author: jeecg-boot
 * @Date:   2022-04-08
 * @Version: V1.0
 */
@Data
@TableName("qfy_inspection_point")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="qfy_inspection_point对象", description="巡检结果详情")
public class InspectionPoint implements Serializable {
    private static final long serialVersionUID = 1L;

	/**编码id*/
	@TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "编码id")
    private java.lang.Integer id;
	/**巡检路线*/
	@Excel(name = "巡检路线", width = 15)
    @ApiModelProperty(value = "巡检路线")
    private java.lang.Integer chkRouteId;
	/**巡检点*/
	@Excel(name = "巡检点", width = 15)
    @ApiModelProperty(value = "巡检点")
    private java.lang.Integer chkPoiId;
	/**编号*/
	@Excel(name = "编号", width = 15)
    @ApiModelProperty(value = "编号")
    private java.lang.String serialno;
	/**巡检点名称*/
	@Excel(name = "巡检点名称", width = 15)
    @ApiModelProperty(value = "巡检点名称")
    private java.lang.String name;
	/**点类项*/
	@Excel(name = "点类项", width = 15)
    @ApiModelProperty(value = "点类项")
    private java.lang.String ptype;
	/**gps经度*/
	@Excel(name = "gps经度", width = 15)
    @ApiModelProperty(value = "gps经度")
    private java.lang.String gpsjd;
	/**gps纬度*/
	@Excel(name = "gps纬度", width = 15)
    @ApiModelProperty(value = "gps纬度")
    private java.lang.String gpswd;
	/**是否合格*/
	@Excel(name = "是否合格", width = 15)
    @ApiModelProperty(value = "是否合格")
    private java.lang.Integer isvalid;
	/**该点得分*/
	@Excel(name = "该点得分", width = 15)
    @ApiModelProperty(value = "该点得分")
    private java.lang.Double score;
	/**二维码id*/
	@Excel(name = "二维码id", width = 15)
    @ApiModelProperty(value = "二维码id")
    private java.lang.String qrid;
	/**nfc芯片id*/
	@Excel(name = "nfc芯片id", width = 15)
    @ApiModelProperty(value = "nfc芯片id")
    private java.lang.String nfcid;
	/**实例ID*/
	@Excel(name = "实例ID", width = 15)
    @ApiModelProperty(value = "实例ID")
    private java.lang.String inspectionId;
	/**检查时间*/
	@Excel(name = "检查时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "检查时间")
    private java.util.Date checktime;
	/**图片ID*/
	@Excel(name = "图片ID", width = 15)
    @ApiModelProperty(value = "图片ID")
    private java.lang.String attachId;
	/**标记 0 待处理 1 处理中 2处理完成*/
	@Excel(name = "标记 0 待处理 1 处理中 2处理完成", width = 15)
    @ApiModelProperty(value = "标记 0 待处理 1 处理中 2处理完成")
    private java.lang.Integer state;
	/**基础分 基础为0*/
	@Excel(name = "基础分 基础为0", width = 15)
    @ApiModelProperty(value = "基础分 基础为0")
    private java.lang.Integer baseScore;
	/**是否巡检 0为未巡检 1 是已经巡检*/
	@Excel(name = "是否巡检 0为未巡检 1 是已经巡检", width = 15)
    @ApiModelProperty(value = "是否巡检 0为未巡检 1 是已经巡检")
    private java.lang.Integer status;
	/**模板ID*/
	@Excel(name = "模板ID", width = 15)
    @ApiModelProperty(value = "模板ID")
    private java.lang.String templateId;
}
