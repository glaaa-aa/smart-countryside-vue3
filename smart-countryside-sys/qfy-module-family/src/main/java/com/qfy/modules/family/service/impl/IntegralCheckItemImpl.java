package com.qfy.modules.family.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qfy.modules.family.entity.IntegralCheckItem;
import com.qfy.modules.family.mapper.IntegralCheckItemMapper;
import com.qfy.modules.family.service.IIntegralCheckItemService;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * @author lanls
 * @version v1.0.0
 * @since 2023/8/30 11:03
 */
@Service
public class IntegralCheckItemImpl extends ServiceImpl<IntegralCheckItemMapper, IntegralCheckItem> implements IIntegralCheckItemService {
    @Override
    public List<IntegralCheckItem> selectList(String tplId) {
        return baseMapper.selectList(tplId);
    }
}
