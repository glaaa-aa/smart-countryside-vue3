package com.qfy.modules.family.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qfy.modules.family.entity.FamilyTemp;
import com.qfy.modules.family.mapper.FamilyTempMapper;
import com.qfy.modules.family.service.IFamilyTempService;
import org.springframework.stereotype.Service;

/**
 * 方法描述：
 * @author: lanls
 * @date: 2023/8/13 10:30
 */
@Service
public class FamilyTempServiceImpl extends ServiceImpl<FamilyTempMapper, FamilyTemp> implements IFamilyTempService {

    @Override
    public void batchUpdateByUsername(String username) {
        baseMapper.batchUpdateByUsername(username);
    }
}
