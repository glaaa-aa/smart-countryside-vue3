package com.qfy.modules.family.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qfy.modules.family.entity.FamilyBaseWater;

import java.util.List;

/**
 * @Description: 家庭成员饮水安全信息
 * @Author: jeecg-boot
 * @Date:   2022-06-02
 * @Version: V1.0
 */
public interface IFamilyBaseWaterService extends IService<FamilyBaseWater> {

    public List<FamilyBaseWater> selectWaterByMainId(String mainId);
}
