package com.qfy.modules.family.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qfy.modules.family.entity.FamilyBaseHouse;

import java.util.List;

/**
 * @Description: 家庭成员住房信息
 * @Author: jeecg-boot
 * @Date:   2022-06-02
 * @Version: V1.0
 */
public interface IFamilyBaseHouseService extends IService<FamilyBaseHouse> {

    /**
     * 通过id查询住房信息
     * @param mainId 订单id
     * @return 住房信息集合
     */
    public List<FamilyBaseHouse> selectHouseByMainId(String mainId);
}
