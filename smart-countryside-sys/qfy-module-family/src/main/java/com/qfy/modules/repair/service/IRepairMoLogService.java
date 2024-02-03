package com.qfy.modules.repair.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qfy.modules.api.vo.QueryParams;
import com.qfy.modules.repair.entity.RepairMoLog;
import com.qfy.modules.repair.entity.RepairMoLogStatis;
import java.util.List;

/**
 * @Description: 维修工单日志
 * @Author: jeecg-boot
 * @Date:   2021-11-02
 * @Version: V1.0
 */
public interface IRepairMoLogService extends IService<RepairMoLog> {

    List<RepairMoLogStatis> queryByHandleStatis(Page page, QueryParams params);

}
