package com.qfy.modules.subsidy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qfy.modules.subsidy.entity.AssistPerson;
import com.qfy.modules.subsidy.mapper.AssistPersonMapper;
import com.qfy.modules.subsidy.service.IAssistPersonService;
import org.springframework.stereotype.Service;

/**
 * @Description: 帮扶联系人
 * @Author: jeecg-boot
 * @Date:   2022-12-04
 * @Version: V1.0
 */
@Service
public class AssistPersonServiceImpl extends ServiceImpl<AssistPersonMapper, AssistPerson> implements IAssistPersonService {

    @Override
    public AssistPerson selectOne(QueryWrapper wrapper) {
        return baseMapper.selectOneByCustom(wrapper);
    }


}
