package com.qfy.modules.dissension.api.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.qfy.modules.dissension.entity.DissensionInvolvedUser;
import com.qfy.modules.dissension.entity.DissensionRecords;
import com.qfy.modules.dissension.entity.AndonRepairMoMan;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.jeecg.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@ApiModel(value="DissensionLogListVo对象", description="矛盾纠纷")
public class DissensionDetailsResult {

    /**主键*/
    @ApiModelProperty(value = "主键")
    private String id;
    /**矛盾纠纷编号*/
    @ApiModelProperty(value = "矛盾纠纷编号")
    private String code;
    /**矛盾纠纷名称*/
    @ApiModelProperty(value = "矛盾纠纷名称")
    private String title;
    /**矛盾纠纷内容*/
    @ApiModelProperty(value = "矛盾纠纷内容")
    private String content;
    /**相关材料附件(文件路径，多个以逗号隔开)*/
    @ApiModelProperty(value = "相关材料附件(文件路径，多个以逗号隔开)")
    private String dataFiles;
    /**发生时间*/
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
    @Excel(name = "矛盾纠纷所在地", width = 15)
    @ApiModelProperty(value = "矛盾纠纷所在地")
    private String orgName;

    /**受理时间*/
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "受理时间")
    private Date acceptDate;
    /**涉及人员*/
    @ApiModelProperty(value = "涉及人员")
    private String persons;
    /**涉及人员总数*/
    @ApiModelProperty(value = "涉及人员总数")
    private Integer numbers;
    /**发生地点*/
    @ApiModelProperty(value = "发生地点")
    private String address;
    /**经纬度*/
    @ApiModelProperty(value = "经纬度")
    private String lnglat;
    /**矛盾纠纷类型*/
    @ApiModelProperty(value = "矛盾纠纷类型")
    @Dict(dicCode = "disputes_type")
    private String category;
    /**矛盾纠纷状态*/
    @ApiModelProperty(value = "矛盾纠纷状态")
    @Dict(dicCode = "disputes_state")
    private String state;
    /**管辖单位*/
    @ApiModelProperty(value = "管辖单位")
    private String adminUnit;
    /**责任单位*/
    @ApiModelProperty(value = "责任单位")
    private String respUnit;
    /**负责人员*/
    @ApiModelProperty(value = "负责人员")
    private String respPersons;
    /**协议（协商）方式*/
    @ApiModelProperty(value = "协议（协商）方式")
    @Dict(dicCode = "protocol_state")
    private String protocolState;
    /**紧急程度*/
    @ApiModelProperty(value = "紧急程度")
    @Dict(dicCode = "urgency")
    private String emergency;
    /**反馈内容*/
    @ApiModelProperty(value = "反馈内容")
    private String results;
    /**反馈材料相关附件(文件路径，多个以逗号隔开)*/
    @ApiModelProperty(value = "反馈材料相关附件(文件路径，多个以逗号隔开)")
    private String attachments;
    /**租户id*/
    @ApiModelProperty(value = "租户id")
    private Integer tenantId;
    /**s1*/
    @ApiModelProperty(value = "s1")
    private String s1;
    /**s2*/
    @ApiModelProperty(value = "s2")
    private String s2;
    /**phone*/
    @ApiModelProperty(value = "phone")
    private String phone;

    @Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "涉及人员")
    private List<DissensionInvolvedUser> involvedUsers;

    @ApiModelProperty(value = "分派人员")
    private List<AndonRepairMoMan> responsibleUsers;

    @ApiModelProperty(value = "动态日志")
    private List<DissensionRecords> dissensionLog;

}
