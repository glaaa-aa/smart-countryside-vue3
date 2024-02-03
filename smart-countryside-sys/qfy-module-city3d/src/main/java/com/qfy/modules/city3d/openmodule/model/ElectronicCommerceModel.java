package com.qfy.modules.city3d.openmodule.model;

import lombok.Data;

import java.util.Date;

/**
 * @author lanls
 * @version 1.0
 * @description:
 * @date 2022/9/1 20:28
 */
@Data
public class ElectronicCommerceModel {

    private String id;
    /**电商名称*/
    private String name;
    /**归属单位*/
    private String buildUnit;
    /**建立时间*/
    private Date buildTime;
    /**投资资金（万元）*/
    private Double buildFunds;
    /**相关合同及审核材料附件*/
    private String archivalMaterial;
    /**地图定位坐标地址（id）*/
    private String addId;
    /** 地图定位坐标地址名称 **/
    private String s1;
}
