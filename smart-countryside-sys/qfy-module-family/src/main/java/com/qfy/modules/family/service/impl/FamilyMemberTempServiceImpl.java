package com.qfy.modules.family.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qfy.modules.family.entity.FamilyMemberTemp;
import com.qfy.modules.family.mapper.FamilyMemberTempMapper;
import com.qfy.modules.family.service.IFamilyMemberTempService;
import org.springframework.stereotype.Service;

/**
 * @author lanls
 * @version 1.0
 */
@Service
public class FamilyMemberTempServiceImpl extends ServiceImpl<FamilyMemberTempMapper,FamilyMemberTemp> implements IFamilyMemberTempService {

    @Override
    public void batchUpdateByUsername(String username) {
        baseMapper.batchUpdateByUsername(username);
    }
}
