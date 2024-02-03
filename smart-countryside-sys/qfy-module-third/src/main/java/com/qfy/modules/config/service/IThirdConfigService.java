package com.qfy.modules.config.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qfy.modules.config.entity.ThirdConfig;

import java.util.List;

/**
 * @Description: 第三方对接参数设置
 * @Author: jeecg-boot
 * @Date:   2022-12-08
 * @Version: V1.0
 */
public interface IThirdConfigService extends IService<ThirdConfig> {

    /**
     * 系统初始化
     */
    public void reload();

    /**
     * 根据租户id获取钉钉/企业微信配置
     * @param tenantId
     * @return
     */
    List<ThirdConfig> getThirdConfigListByThirdType(Integer tenantId);

    /**
     * 根据租户id和第三方类别获取第三方配置
     * @param tenantId
     * @param thirdType
     * @return
     */
    ThirdConfig getThirdConfigByThirdType(Integer tenantId, String thirdType);

    /**
     * 根据应用key获取第三方表配置
     * @param clientId
     */
    List<ThirdConfig> getThirdAppConfigByClientId(String clientId);
}
