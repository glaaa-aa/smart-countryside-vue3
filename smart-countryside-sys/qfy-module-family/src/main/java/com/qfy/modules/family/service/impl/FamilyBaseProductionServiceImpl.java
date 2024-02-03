package com.qfy.modules.family.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qfy.modules.family.entity.FamilyBaseProduction;
import com.qfy.modules.family.mapper.FamilyBaseProductionMapper;
import com.qfy.modules.family.service.IFamilyBaseProductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 家庭成员生产信息
 * @Author: jeecg-boot
 * @Date:   2022-06-02
 * @Version: V1.0
 */
@Service
public class FamilyBaseProductionServiceImpl extends ServiceImpl<FamilyBaseProductionMapper, FamilyBaseProduction> implements IFamilyBaseProductionService {
    @Autowired
    private FamilyBaseProductionMapper familyBaseProductionMapper;

//    @Override
//    public List<FamilyBaseProduction> selectProductionByMainId(String mainId) {
//        return familyBaseProductionMapper.selectProductionByMainId(mainId);
//    }
}
