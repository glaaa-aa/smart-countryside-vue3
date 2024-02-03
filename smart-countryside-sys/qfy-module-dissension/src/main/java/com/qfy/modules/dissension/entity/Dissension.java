package com.qfy.modules.dissension.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.qfy.modules.commons.entity.BaseEntity;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: 矛盾纠纷
 * @Author: jeecg-boot
 * @Date:   2023-12-26
 * @Version: V1.0
 */
@ApiModel(value="qfy_dissension对象", description="矛盾纠纷")
@Data
@TableName("qfy_dissension")
public class Dissension extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private String id;
	/**矛盾纠纷编号*/
	@Excel(name = "矛盾纠纷编号", width = 15)
    @ApiModelProperty(value = "矛盾纠纷编号")
    private String code;
	/**矛盾纠纷名称*/
	@Excel(name = "矛盾纠纷名称", width = 15)
    @ApiModelProperty(value = "矛盾纠纷名称")
    private String title;
	/**矛盾纠纷内容*/
	@Excel(name = "矛盾纠纷内容", width = 15)
    @ApiModelProperty(value = "矛盾纠纷内容")
    private String content;
	/**相关材料附件(文件路径，多个以逗号隔开)*/
	@Excel(name = "相关材料附件(文件路径，多个以逗号隔开)", width = 15)
    @ApiModelProperty(value = "相关材料附件(文件路径，多个以逗号隔开)")
    private String dataFiles;
	/**发生时间*/
	@Excel(name = "发生时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "发生时间")
    private Date happenDate;

    @Excel(name = "矛盾纠纷所在地网格编号", width = 15)
    @ApiModelProperty(value = "矛盾纠纷所在地网格编号")
    private String orgCode;
    @Excel(name = "矛盾纠纷所在全地址", width = 15)
    @ApiModelProperty(value = "矛盾纠纷所在全地址")
    private String orgFullName;
    @Excel(name = "矛盾纠纷所在地址", width = 15)
    @ApiModelProperty(value = "矛盾纠纷所在地址)")
    private String orgName;

    /**受理时间*/
	@Excel(name = "受理时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "受理时间")
    private Date acceptDate;
	/**涉及人员*/
	@Excel(name = "涉及人员", width = 15)
    @ApiModelProperty(value = "涉及人员")
    private String persons;
	/**涉及人员总数*/
	@Excel(name = "涉及人员总数", width = 15)
    @ApiModelProperty(value = "涉及人员总数")
    private Integer numbers;
	/**发生地点*/
	@Excel(name = "发生地点", width = 15)
    @ApiModelProperty(value = "发生地点")
    private String address;
	/**经纬度*/
	@Excel(name = "经纬度", width = 15)
    @ApiModelProperty(value = "经纬度")
    private String lnglat;
	/**矛盾纠纷类型*/
	@Excel(name = "矛盾纠纷类型", width = 15)
    @ApiModelProperty(value = "矛盾纠纷类型")
    @Dict(dicCode = "disputes_type")
    private String category;
	/**矛盾纠纷状态*/
	@Excel(name = "矛盾纠纷状态", width = 15)
    @ApiModelProperty(value = "矛盾纠纷状态")
    @Dict(dicCode = "disputes_state")
    private String state;
	/**管辖单位*/
	@Excel(name = "管辖单位", width = 15)
    @ApiModelProperty(value = "管辖单位")
    private String adminUnit;
	/**责任单位*/
	@Excel(name = "责任单位", width = 15)
    @ApiModelProperty(value = "责任单位")
    private String respUnit;
	/**负责人员*/
	@Excel(name = "负责人员", width = 15)
    @ApiModelProperty(value = "负责人员")
    private String respPersons;
	/**协议（协商）方式*/
	@Excel(name = "协议（协商）方式", width = 15)
    @ApiModelProperty(value = "协议（协商）方式")
    @Dict(dicCode = "protocol_state")
    private String protocolState;
	/**紧急程度*/
	@Excel(name = "紧急程度", width = 15)
    @ApiModelProperty(value = "紧急程度")
    @Dict(dicCode = "urgency")
    private String emergency;
	/**反馈内容*/
	@Excel(name = "反馈内容", width = 15)
    @ApiModelProperty(value = "反馈内容")
    private String results;
	/**反馈材料相关附件(文件路径，多个以逗号隔开)*/
	@Excel(name = "反馈材料相关附件(文件路径，多个以逗号隔开)", width = 15)
    @ApiModelProperty(value = "反馈材料相关附件(文件路径，多个以逗号隔开)")
    private String attachments;
	/**租户id*/
	@Excel(name = "租户id", width = 15)
    @ApiModelProperty(value = "租户id")
    private Integer tenantId;
    /**s1*/
	@Excel(name = "s1", width = 15)
    @ApiModelProperty(value = "s1")
    private String s1;
    /**s2*/
    @Excel(name = "s2", width = 15)
    @ApiModelProperty(value = "s2")
    private String s2;


}
