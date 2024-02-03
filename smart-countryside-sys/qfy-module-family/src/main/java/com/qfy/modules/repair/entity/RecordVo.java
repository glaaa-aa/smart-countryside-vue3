package com.qfy.modules.repair.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import java.util.List;
import lombok.Data;
import org.jeecg.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;


/**
 * @author admin
 * @date 2021/11/17
 */
@Data
public class RecordVo {
    /**id*/
    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "报修类型")
    private String callType; // 枚举：AndonCallTypeEnum
    @ApiModelProperty(value = "报修单号")
    private String callCode;
    @ApiModelProperty(value = "报修单号")
    private String moCode;
    @ApiModelProperty(value = "设备单号")
    private String deviceno;
    @ApiModelProperty(value = "设备名称")
    private String devName;
    @ApiModelProperty(value = "设备存放位置")
    private String location;

    @ApiModelProperty(value = "发生时间")
    private String recDate;

    @ApiModelProperty(value = "工作中心")
    private String wcCode;

    @ApiModelProperty(value = "工作中心名称")
    private String wcName;

    @ApiModelProperty(value = "报障人")
    private String notifier;

    @ApiModelProperty(value = "报障人")
    private String notifierName;
    @ApiModelProperty(value = "报障人电话")
    private  String notifierPhone;
    @ApiModelProperty(value = "报障部门")
    private String noticeDepart;

    @ApiModelProperty(value = "故障等级")
    private String abnormalLevel;

    @ApiModelProperty(value = "故障类型名称")
    private String abnormalName;

    @ApiModelProperty(value = "故障类型")
    private String abnormalCode;

    @ApiModelProperty(value = "故障部位")
    private String happenPos;


    @ApiModelProperty(value = "故障原因")
    private String faultCode;

    @ApiModelProperty(value = "维修组 责任部门")
    private String dutyDepart;

    @ApiModelProperty(value = "维修责任人")
    private String dutyMan;

    @ApiModelProperty(value = "维修人")
    private String dutyManId;

    @ApiModelProperty(value = "紧急程度")
    private String criticalLevel;

    @ApiModelProperty(value = "是否停机")
    private String beStop;

    @ApiModelProperty(value = "故障描述")
    private String abnContent;

    @ApiModelProperty(value = "关联附件列表")
    private String abnFiles;

    @ApiModelProperty(value = "维修开始时间")
    private String weixStdate;

    @ApiModelProperty(value = "维修完成时间")
    private String weixEddate;

    @ApiModelProperty(value = "维修时长(分钟) 完成时间-开始时间")
    private Integer weixTimes;

    @ApiModelProperty(value = "维修执行关联附件列表")
    private String weixFiles;

    @ApiModelProperty(value = "维修状态")
    private String weixState;

    @ApiModelProperty(value = "维修工作描述")
    private String weixContent;

    @ApiModelProperty(value = "停机时长(分钟)")
    private Integer stopTimes;

    @ApiModelProperty(value = "工单状态")
    private Integer callState;

    @ApiModelProperty(value = "解决办法")
    private String finishMethod;

    @ApiModelProperty(value = "响应时长 单位分钟")
    private Integer responseTime;

    @ApiModelProperty(value = "签到人")
    private String signManId;

    @ApiModelProperty(value = "签到人姓名")
    private String signMan;

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
    @ApiModelProperty(value = "签到时间")
    private Date signTime;


    @ApiModelProperty(value = "s1")
    @Dict(dicCode = "record_table_type")
    private String s1;

    @ApiModelProperty(value = "s2")
    private String s2;

    @ApiModelProperty(value = "s3")
    private String s3;

    @ApiModelProperty(value = "s4")
    private String s4;

    @ApiModelProperty(value = "s5")
    private String s5;

    /**所属部门*/
    @ApiModelProperty(value = "所属部门")
    private String sysOrgCode;

    /**创建人名称*/
    @ApiModelProperty(value = "创建人名称")
    @TableField(exist = false)
    private String createName;
    /**创建人登录名称*/
    @ApiModelProperty(value = "创建人登录名称")
    private String createBy;
    /**创建日期*/
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建日期")
    private Date createTime;

    /**更新人名称*/
    @Excel(name = "更新人名称", width = 15)
    @ApiModelProperty(value = "更新人名称")
    @TableField(exist = false)
    private String updateName;
    /**更新人登录名称*/
    @ApiModelProperty(value = "更新人登录名称")
    private String updateBy;
    /**更新日期*/
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新日期")
    private Date updateTime;

    /**备注*/
    @Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
    private String remarks;

    @Dict(dicCode = "mo_state")
    private String moState;
    private Integer isFinish;

    /**审批用户*/
    @Excel(name = "审批用户", width = 15)
    @ApiModelProperty(value = "审批用户")
    private String shenpMan;
    /**审批用户*/
    @Excel(name = "审批用户", width = 15)
    @ApiModelProperty(value = "审批用户")
    private String shenpManId;
    /**审批时间*/
    @Excel(name = "审批时间", width = 15)
    @ApiModelProperty(value = "审批时间")
    private String shenpTime;
    /**审批结果*/
    @Excel(name = "审批结果", width = 15)
    @ApiModelProperty(value = "审批结果")
    private String shenpResult;
    /**审批内容*/
    @Excel(name = "审批内容", width = 15)
    @ApiModelProperty(value = "审批内容")
    private String shenpContent;

    /**是否接受 1接收 0拒绝 **/
    private  Integer IsAccept;

    /**开始时间*/
    @ApiModelProperty(value = "beginTime")
    private String beginTime;
    /**结束时间*/
    @ApiModelProperty(value = "endTime")
    private String endTime;
    /**隐患code*/
    @ApiModelProperty(value = "causeCode")
    private String causeCode;
    /**综合查询参数*/
    @ApiModelProperty(value = "queryParam")
    private String queryParam;
    /** 删除状态：0：逻辑删除，1：正常使用 */
//    @Excel(name = "数据状态 0：逻辑删除，1：正常可用状态", width = 15)
    @ApiModelProperty(value = "数据状态 0：正常可用状态，1：逻辑删除")
    @Dict(dicCode = "data_del_state")
    private Integer delFlag;
    /** 小程序行政区域编号 */
    @TableField(exist = false)
    private String areaCode;


    @TableField(exist = false)
    private List<String> wcCodeList;

    @TableField(exist = false)
    private String bigTypeName;

}
