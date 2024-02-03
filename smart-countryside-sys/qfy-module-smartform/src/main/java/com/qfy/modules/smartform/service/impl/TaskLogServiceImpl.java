package com.qfy.modules.smartform.service.impl;

import com.qfy.modules.smartform.entity.TaskLog;
import com.qfy.modules.smartform.mapper.TaskLogMapper;
import com.qfy.modules.smartform.service.ITaskLogService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 任务日志
 * @Author: jeecg-boot
 * @Date:   2022-04-08
 * @Version: V1.0
 */
@Service
public class TaskLogServiceImpl extends ServiceImpl<TaskLogMapper, TaskLog> implements ITaskLogService {

}
