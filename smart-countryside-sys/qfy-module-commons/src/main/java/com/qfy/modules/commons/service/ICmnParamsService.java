package com.qfy.modules.commons.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.qfy.modules.commons.entity.CmnParams;

/**
 * @Description: 系统全局参数
 * @Author: jeecg-boot
 * @Date:   2022-12-08
 * @Version: V1.0
 */
public interface ICmnParamsService extends IService<CmnParams> {

    /**
     * 数据初始化
     */
    public void reload();
}
