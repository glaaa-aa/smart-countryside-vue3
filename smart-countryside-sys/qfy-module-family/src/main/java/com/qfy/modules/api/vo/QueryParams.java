package com.qfy.modules.api.vo;

import lombok.Data;

/**
 * @author lanls
 * @version 1.0
 * @description:
 * @date 2023/3/31 11:51
 */
@Data
public class QueryParams {
    /**
     * 主键id
     */
    private String id;
    /**
     * 姓名/名称
     */
    private String name;
    /**
     * 身份证号(选填)
     */
    private String idCard;
    /**
     * 隐患类型,驾驶人-1，车辆-2，道路-3，企业-4
     */
    private String faultType;
    /**
     * 隐患信息编码
     */
    private String faultCode;
    /**
     * 行政区划
     */
    private String wcCode;
    /**
     * 开始时间
     */
    private String beginTime;
    /**
     * 结束时间
     */
    private String endTime;
    /**
     * 道路位置坐标（经纬度）
     */
    private String location;

}
