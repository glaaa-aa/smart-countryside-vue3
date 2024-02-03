package com.qfy.modules.city3d.openmodule.model;

import lombok.Data;

import java.util.Date;

/**
 * @author lanls
 * @version 1.0
 * @description:
 * @date 2022/9/1 20:21
 */
@Data
public class WaterTankModel {

    private String id;
    /**水柜名称*/
    private String name;
    /**蓄水容积(立方)*/
    private Integer capacity;
    /**建造材料（泥土、砂石、水泥硬化）*/
    private Integer buildMaterial;
    /**建造单位*/
    private String buildUnit;
    /**建造时间*/
    private Date buildTime;
    /**建造资金（万元）*/
    private Double buildFunds;
    /**相关合同及审核材料附件*/
    private String archivalMaterial;
    /**地图定位坐标地址（id）*/
    private String addId;
    /** 地图定位坐标地址名称 **/
    private String s1;
}
