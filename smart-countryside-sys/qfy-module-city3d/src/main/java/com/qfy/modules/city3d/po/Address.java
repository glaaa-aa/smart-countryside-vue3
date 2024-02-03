package com.qfy.modules.city3d.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.qfy.modules.city3d.openmodule.model.ElectronicCommerceModel;
import com.qfy.modules.city3d.openmodule.model.FamilyBaseModel;
import com.qfy.modules.city3d.openmodule.model.TrafficRoadModel;
import com.qfy.modules.city3d.openmodule.model.WaterTankModel;

@Data
@TableName("address")
public class Address {

    @TableId
    private String addId;

    private String addName;

    private String province;

    private String subdistrict;

    private String communities;

    private String street;

    private String houseNumPrefix;

    private String houseNum;

    private String houseNumPostfix;

    private String auxiliaryNum;

    private String auxiliaryNumPostfix;

    private String communityName;

    private String buildingNumPrefix;

    private String buildingNum;

    private String buildingNumPostfix;

    private String unitNum;

    private String roomNum;

    private String roomNumPostfix;

    private String subsidiaryAdd;

    private String remark;

    private String poi;

    private String camPoi;

    private String camHeading;

    private String camPitch;

    private String addType;

    private String floorHeight;

    private String floorPoi;

    private String miniheading;

    private String floorHeading;

    private String pid;

    private String addDate;

    @TableField(exist = false)
    private House house;

    @TableField(exist = false)
    private Net net;

    @TableField(exist = false)
    private String fid;

    /** 关联供水水柜 **/
    @TableField(exist = false)
    private WaterTankModel waterTank;

    /** 关联道路交通 **/
    @TableField(exist = false)
    private TrafficRoadModel road;

    /** 关联家庭户 **/
    @TableField(exist = false)
    private FamilyBaseModel familyBase;

    /** 关联电商网点 **/
    @TableField(exist = false)
    private ElectronicCommerceModel commerce;
}
