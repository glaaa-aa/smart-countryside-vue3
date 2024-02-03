package com.qfy.modules.repair.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qfy.modules.repair.entity.HiddenDangerView;
import com.qfy.modules.repair.entity.RepairMo;
import com.qfy.modules.repair.mapper.HiddenDangerViewMapper;
import com.qfy.modules.repair.service.IHiddenDangerViewService;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * @author lanls
 * @version v1.0.0
 * @since 2023/12/20 13:32
 */
@Service
public class HiddenDangerViewServiceImpl extends ServiceImpl<HiddenDangerViewMapper, HiddenDangerView> implements IHiddenDangerViewService {
    @Override
    public List<HiddenDangerView> selectList(RepairMo repairMo) {
        return baseMapper.selectList(repairMo);
    }

    @Override
    public HiddenDangerView selectById(String id) {
        return baseMapper.selectById(id);
    }

    @Override
    public Long selectCount(RepairMo repairMo) {
        return baseMapper.selectCount(repairMo);
    }
}
