package com.qfy.modules.repair.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.qfy.modules.commons.entity.BaseEntity;
import java.io.Serializable;
import lombok.Data;
import org.jeecg.common.aspect.annotation.Dict;

/**
 * @author lanls
 * @version v1.0.0
 * @since 2023/12/20 13:21
 */
@Data
public class HiddenDangerView extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 6904525775510923643L;

    /**主键*/
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    /**报修类型*/
    @Dict(dicCode = "faultType")
    private String callType;
    /**报修单号*/
    private String callCode;
    /**身份证号*/
    private String deviceno;
    /**设备名称*/
    private String devName;
    /**设备存放位置*/
    private String location;
    /**发生时间*/
    private String recDate;
    /**工作中心*/
    @Dict(dictTable = "qfy_sys_organization", dicCode = "code", dicText = "name")
    private String wcCode;
    /**工作中心名称*/
    private String wcName;
    /**报障人*/
    @Dict(dictTable = "sys_user", dicCode = "username", dicText = "realname")
    private String notifier;
    /**报障人*/
    private String notifierName;
    /**报障人电话*/
    private String notifierPhone;
    /**报障部门*/
//    @Dict(dictTable = "sys_depart", dicCode = "org_code", dicText = "depart_name")
    private String noticeDepart;
    /**故障等级*/
    private String abnormalLevel;
    /**故障类型名称*/
    private String abnormalName;
    /**故障类型*/
    private String abnormalCode;
    /**故障部位*/
    @Dict(dicCode = "driving_car_type")
    private String happenPos;
    /**故障原因*/
    @Dict(dictTable = "qfy_andon_fault_cause", dicCode = "fault_code", dicText = "fault_resons")
    private String faultCode;
    /**维修组 责任部门*/
    private String dutyDepart;
    /**维修责任人*/
    private String dutyMan;
    /**维修人*/
    private String dutyManId;
    /**紧急程度 紧急、高、中、低*/
    @Dict(dicCode = "critical_level")
    private String criticalLevel;
    /**是否停机*/
    private String beStop;
    /**故障描述*/
    private String abnContent;
    /**关联附件列表*/
    private String abnFiles;
    /**维修开始时间*/
    private String weixStdate;
    /**维修完成时间*/
    private String weixEddate;
    /**维修时长(分钟) 完成时间-开始时间*/
    private Integer weixTimes;
    /**停机时长(分钟)*/
    private Integer stopTimes;
    /**工单状态*/
    @Dict(dicCode = "call_state")
    private Integer callState;
    /**解决办法*/
    private String finishMethod;
    /**响应时长 单位分钟*/
    private Integer responseTime;
    /**驾驶人联系方式*/
    private String signManId;
    /**签到人姓名*/
    @Dict(dictTable = "sys_user", dicCode = "username", dicText = "realname")
    private String signMan;
    /**签到时间*/
    private String signTime;

    @Dict(dicCode = "yn")
    private Integer isXyjs;

    @Dict(dicCode = "yn")
    private Integer isCljs;

    @Dict(dicCode = "yn")
    private Integer isShjs;

    private String yhjzTime;

    private String s1;
    /**s2-经纬度*/
    private String s2;
    /**s3*/
    private String s3;
    /**性别*/
    @Dict(dicCode = "sex")
    private String s4;
    /**年龄*/
    private String s5;
}
