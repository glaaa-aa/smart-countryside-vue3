package com.qfy.modules.config.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qfy.modules.config.entity.ThirdConfig;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @Description: 第三方对接参数设置
 * @Author: jeecg-boot
 * @Date:   2022-12-08
 * @Version: V1.0
 */
public interface ThirdConfigMapper extends BaseMapper<ThirdConfig> {

    /**
     * 根据租户id获取钉钉/企业微信配置
     * @param tenantId
     * @return
     */
    List<ThirdConfig> getThirdConfigListByThirdType(@Param("tenantId") int tenantId);

    /**
     * 根据租户id和第三方类别获取第三方配置
     * @param tenantId
     * @param thirdType
     * @return
     */
    ThirdConfig getThirdConfigByThirdType(@Param("tenantId") int tenantId, @Param("thirdType") String thirdType);

}
