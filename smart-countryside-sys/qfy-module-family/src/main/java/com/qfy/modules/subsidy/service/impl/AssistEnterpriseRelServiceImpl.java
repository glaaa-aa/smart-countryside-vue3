package com.qfy.modules.subsidy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qfy.modules.subsidy.entity.AssistEnterpriseRel;
import com.qfy.modules.subsidy.mapper.AssistEnterpriseRelMapper;
import com.qfy.modules.subsidy.service.IAssistEnterpriseRelService;
import org.springframework.stereotype.Service;

/**
 * @Description: 企业帮扶关系
 * @Author: jeecg-boot
 * @Date:   2022-12-05
 * @Version: V1.0
 */
@Service
public class AssistEnterpriseRelServiceImpl extends ServiceImpl<AssistEnterpriseRelMapper, AssistEnterpriseRel> implements IAssistEnterpriseRelService {

    /*@Override
    public List<AssistEnterpriseRel> selectList(QueryWrapper wrapper) {
        return baseMapper.selectList(wrapper);
    }*/
}
