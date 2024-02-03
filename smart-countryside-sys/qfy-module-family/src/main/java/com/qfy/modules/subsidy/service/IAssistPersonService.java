package com.qfy.modules.subsidy.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qfy.modules.subsidy.entity.AssistPerson;

/**
 * @Description: 帮扶联系人
 * @Author: jeecg-boot
 * @Date:   2022-12-04
 * @Version: V1.0
 */
public interface IAssistPersonService extends IService<AssistPerson> {

    AssistPerson selectOne(QueryWrapper wrapper);
}
