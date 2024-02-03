package com.qfy.modules.api.service;/**
 * @title: City3dModuleService
 * @projectName smart-countryside-sys
 * @description: TODO
 * @author 马强
 * @date 2023/12/19 14:47
 */

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.qfy.modules.city3d.openmodule.model.ElectronicCommerceModel;
import com.qfy.modules.city3d.openmodule.model.FamilyBaseModel;
import com.qfy.modules.city3d.openmodule.model.TrafficRoadModel;
import com.qfy.modules.city3d.openmodule.model.WaterTankModel;
import com.qfy.modules.city3d.openmodule.service.FamilyModuleService;
import com.qfy.modules.family.entity.FamilyBase;
import com.qfy.modules.family.service.IFamilyBaseService;
import com.qfy.modules.subsidy.entity.ElectronicCommerce;
import com.qfy.modules.subsidy.entity.TrafficRoad;
import com.qfy.modules.subsidy.entity.WaterTank;
import com.qfy.modules.subsidy.service.IElectronicCommerceService;
import com.qfy.modules.subsidy.service.ITrafficRoadService;
import com.qfy.modules.subsidy.service.IWaterTankService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author     ：马强
 * @date       ：Created in 2023/12/19 14:47
 * @description：city3dModule
 * @modified By：
 * @version:
 */
@Service
public class City3dModuleService  implements FamilyModuleService {
    @Autowired
    private IWaterTankService waterTankService;
    @Autowired
    private ITrafficRoadService roadService;
    @Autowired
    private IElectronicCommerceService commerceService;
    @Autowired
    private IFamilyBaseService familyBaseService;

    @Override
    public List<WaterTankModel> queryWaterTank(Wrapper wrapper) {
        List<WaterTank> waterTankList = waterTankService.list(wrapper);
        List<WaterTankModel> modelList = new ArrayList<>();
        for (WaterTank waterTank : waterTankList) {
            WaterTankModel model = new WaterTankModel();
            BeanUtils.copyProperties(waterTank, model);
            modelList.add(model);
        }
        return modelList;
    }

    @Override
    public List<TrafficRoadModel> queryTrafficRoad(Wrapper wrapper) {
        List<TrafficRoad> roadList = roadService.list(wrapper);
        List<TrafficRoadModel> modelList = new ArrayList<>();
        for (TrafficRoad road : roadList) {
            TrafficRoadModel model = new TrafficRoadModel();
            BeanUtils.copyProperties(road, model);
            modelList.add(model);
        }
        return modelList;
    }

    @Override
    public List<ElectronicCommerceModel> queryEcommerce(Wrapper wrapper) {
        List<ElectronicCommerce> commerceList = commerceService.list(wrapper);
        List<ElectronicCommerceModel> modelList = new ArrayList<>();
        for (ElectronicCommerce commerce : commerceList) {
            ElectronicCommerceModel model = new ElectronicCommerceModel();
            BeanUtils.copyProperties(commerce, model);
            modelList.add(model);
        }
        return modelList;
    }

    @Override
    public List<FamilyBaseModel> queryFamilyBase(Wrapper wrapper) {
        List<FamilyBase> familyBaseList = familyBaseService.list(wrapper);
        List<FamilyBaseModel> modelList = new ArrayList<>();
        for (FamilyBase familyBase : familyBaseList) {
            FamilyBaseModel model = new FamilyBaseModel();
            BeanUtils.copyProperties(familyBase, model);
            modelList.add(model);
        }
        return modelList;
    }
}
