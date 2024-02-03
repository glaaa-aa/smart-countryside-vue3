package com.qfy.modules.repair.service.impl;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qfy.modules.api.vo.QueryParams;
import com.qfy.modules.repair.entity.RepairMoLog;
import com.qfy.modules.repair.entity.RepairMoLogStatis;
import com.qfy.modules.repair.mapper.RepairMoLogMapper;
import com.qfy.modules.repair.service.IRepairMoLogService;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * @Description: 维修工单日志
 * @Author: jeecg-boot
 * @Date:   2021-11-02
 * @Version: V1.0
 */
@Service
public class RepairMoLogServiceImpl extends ServiceImpl<RepairMoLogMapper, RepairMoLog> implements IRepairMoLogService {

    @Override
    public List<RepairMoLogStatis> queryByHandleStatis(Page page, QueryParams params) {
        return null;
    }
}
