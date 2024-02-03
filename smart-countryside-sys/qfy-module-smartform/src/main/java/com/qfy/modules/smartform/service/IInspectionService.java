package com.qfy.modules.smartform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qfy.modules.smartform.api.dto.InspectionDto;
import com.qfy.modules.smartform.entity.Inspection;
import org.jeecg.common.system.vo.LoginUser;

/**
 * @Description: 巡检结果-主表
 * @Author: jeecg-boot
 * @Date:   2022-04-08
 * @Version: V1.0
 */
public interface IInspectionService extends IService<Inspection> {

    void addMain(InspectionDto dto, LoginUser user) throws Exception;
}
