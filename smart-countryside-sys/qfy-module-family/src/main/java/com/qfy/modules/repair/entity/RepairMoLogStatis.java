package com.qfy.modules.repair.entity;

import lombok.Data;
import org.jeecg.common.aspect.annotation.Dict;

/**
 * @author lanls
 * @version 1.0
 * @description:
 * @date 2023/4/10 10:53
 */
@Data
public class RepairMoLogStatis {

    private String id;
    /** 行政区划 */
    @Dict(dictTable = "qfy_sys_organization", dicCode = "code", dicText = "name")
    private String wcCode;
    /** 驾驶人姓名/车主姓名/企业名称 */
    private String name;
    /** 证件号码 */
    private String idNumber;
    /** 隐患信息 */
    @Dict(dictTable = "qfy_andon_fault_cause", dicCode = "fault_code", dicText = "fault_resons")
    private String faultCode;
    /** 处理次数 */
    private Integer total;
    /** 统计日期 */
    private String statisDate;
    /** 地址 */
    private String address;
}
