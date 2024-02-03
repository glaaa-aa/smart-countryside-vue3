package com.qfy.modules.repair.job;

import com.qfy.modules.commons.entity.BaseEntity;
import java.io.Serializable;
import lombok.Data;

/**
 * 隐患信息(驾驶人、车辆、道路、企业)
 * @author lanls
 * @version 1.0
 * @date 2023/5/16 22:25
 */
@Data
public class HiddenDanger extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 5223265936536901904L;

    /**主键*/
    private String id;
    /**报修类型*/
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
    private String wcCode;
    /**工作中心名称*/
    private String wcName;
    /**报障人*/
    private String notifier;
    /**报障人*/
    private String notifierName;
    /**报障人电话*/
    private String notifierPhone;
    /**报障部门*/
    private String noticeDepart;
    /**故障等级*/
    private String abnormalLevel;
    /**故障类型名称*/
    private String abnormalName;
    /**故障类型*/
    private String abnormalCode;
    /**故障部位*/
    private String happenPos;
    /**故障原因*/
    private String faultCode;
    /**维修组 责任部门*/
    private String dutyDepart;
    /**维修责任人*/
    private String dutyMan;
    /**维修人*/
    private String dutyManId;
    /**紧急程度 紧急、高、中、低*/
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
    private Integer callState;
    /**解决办法*/
    private String finishMethod;
    /**响应时长 单位分钟*/
    private Integer responseTime;
    /**驾驶人联系方式*/
    private String signManId;
    /**签到人姓名*/
    private String signMan;
    /**签到时间*/
    private String signTime;
    private Integer isXyjs;
    private Integer isCljs;
    private Integer isShjs;
    private String yhjzTime;
    /**备注*/
    private String s1;
    /**s2-经纬度*/
    private String s2;
    /**s3*/
    private String s3;
    /**性别*/
    private String s4;
    /**年龄*/
    private String s5;
}
