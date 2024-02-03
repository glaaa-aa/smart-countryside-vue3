package com.qfy.modules.family.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qfy.modules.family.entity.FamilyBaseHouse;
import com.qfy.modules.family.mapper.FamilyBaseHouseMapper;
import com.qfy.modules.family.service.IFamilyBaseHouseService;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * @Description: 家庭成员住房信息
 * @Author: jeecg-boot
 * @Date:   2022-06-02
 * @Version: V1.0
 */
@Service
public class FamilyBaseHouseServiceImpl extends ServiceImpl<FamilyBaseHouseMapper, FamilyBaseHouse> implements IFamilyBaseHouseService {

    @Override
    public List<FamilyBaseHouse> selectHouseByMainId(String mainId) {
        return baseMapper.selectHouseByMainId(mainId);
    }
}
