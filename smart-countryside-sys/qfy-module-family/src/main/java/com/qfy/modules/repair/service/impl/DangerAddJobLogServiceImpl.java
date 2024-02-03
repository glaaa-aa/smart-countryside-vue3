package com.qfy.modules.repair.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qfy.modules.repair.job.DangerAddJobLog;
import com.qfy.modules.repair.mapper.DangerAddJobLogMapper;
import com.qfy.modules.repair.service.IDangerAddJobLogService;
import org.springframework.stereotype.Service;

/**
 * @author lanls
 * @version 1.0
 * @date 2023/5/16 23:27
 */
@Service
public class DangerAddJobLogServiceImpl extends ServiceImpl<DangerAddJobLogMapper, DangerAddJobLog> implements IDangerAddJobLogService {
}
