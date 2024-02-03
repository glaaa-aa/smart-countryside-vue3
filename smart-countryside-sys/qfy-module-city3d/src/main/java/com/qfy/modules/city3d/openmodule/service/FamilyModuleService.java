package com.qfy.modules.city3d.openmodule.service;/**
 * @title: OpenModuleService
 * @projectName smart-countryside-sys
 * @description: TODO
 * @author 马强
 * @date 2023/12/19 14:45
 */

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.qfy.modules.city3d.openmodule.model.ElectronicCommerceModel;
import com.qfy.modules.city3d.openmodule.model.FamilyBaseModel;
import com.qfy.modules.city3d.openmodule.model.TrafficRoadModel;
import com.qfy.modules.city3d.openmodule.model.WaterTankModel;

import java.util.List;

/**
 * @author     ：马强
 * @date       ：Created in 2023/12/19 14:45
 * @description：对外Service
 * @modified By：
 * @version:
 */
public interface FamilyModuleService {

    /**
     * 通过地址id查询水柜
     * @param wrapper
     * @author: lanls
     * @date: 2022/9/1 20:33
     * @description:
     */
    List<WaterTankModel> queryWaterTank(Wrapper wrapper);

    /**
     * 通过地址id查询道路交通
     * @param wrapper
     * @author: lanls
     * @date: 2022/9/1 20:33
     * @description:
     */
    List<TrafficRoadModel> queryTrafficRoad(Wrapper wrapper);

    /**
     * 通过地址id查询电商网点
     * @param wrapper
     * @author: lanls
     * @date: 2022/9/1 20:33
     * @description:
     */
    List<ElectronicCommerceModel> queryEcommerce(Wrapper wrapper);

    /**
     * 通过地址id查询家庭户基本信息
     * @param wrapper
     * @author: lanls
     * @date: 2022/9/1 20:33
     * @description:
     */
    List<FamilyBaseModel> queryFamilyBase(Wrapper wrapper);
}
