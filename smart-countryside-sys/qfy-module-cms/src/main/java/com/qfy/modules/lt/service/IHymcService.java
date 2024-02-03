package com.qfy.modules.lt.service;

import com.qfy.modules.lt.entity.Hymc;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 会议管理
 * @Author: jeecg-boot
 * @Date:   2022-04-05
 * @Version: V1.0
 */
public interface IHymcService extends IService<Hymc> {
    // 获取当前生效的会议
    public Hymc getCurrentHy();
}
