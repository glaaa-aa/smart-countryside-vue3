package com.qfy.modules.smartform.service.impl;

import com.qfy.modules.smartform.entity.Task;
import com.qfy.modules.smartform.mapper.TaskMapper;
import com.qfy.modules.smartform.service.ITaskService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 定时任务
 * @Author: jeecg-boot
 * @Date:   2022-04-08
 * @Version: V1.0
 */
@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements ITaskService {

}
