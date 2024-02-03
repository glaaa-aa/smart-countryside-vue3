package com.qfy.modules.family.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qfy.modules.family.entity.FamilyBaseWater;
import com.qfy.modules.family.mapper.FamilyBaseWaterMapper;
import com.qfy.modules.family.service.IFamilyBaseWaterService;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * @Description: 家庭成员饮水安全信息
 * @Author: jeecg-boot
 * @Date:   2022-06-02
 * @Version: V1.0
 */
@Service
public class FamilyBaseWaterServiceImpl extends ServiceImpl<FamilyBaseWaterMapper, FamilyBaseWater> implements IFamilyBaseWaterService {

    @Override
    public List<FamilyBaseWater> selectWaterByMainId(String mainId) {
        return baseMapper.selectWaterByMainId(mainId);
    }
}
