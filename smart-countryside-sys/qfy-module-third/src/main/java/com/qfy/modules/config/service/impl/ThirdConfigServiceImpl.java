package com.qfy.modules.config.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qfy.modules.config.ThirdConfigFactory;
import com.qfy.modules.config.entity.ThirdConfig;
import com.qfy.modules.config.mapper.ThirdConfigMapper;
import com.qfy.modules.config.service.IThirdConfigService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 第三方对接参数设置
 * @Author: jeecg-boot
 * @Date:   2022-12-08
 * @Version: V1.0
 */
@Service
public class ThirdConfigServiceImpl extends ServiceImpl<ThirdConfigMapper, ThirdConfig> implements IThirdConfigService {

    @Override
    public void reload() {
        List<ThirdConfig> list = list();
        ThirdConfigFactory.thirdConfigMap.clear();
        if(list != null) {
            list.forEach(item->{
                Map<String, ThirdConfig> vauleMap = ThirdConfigFactory.thirdConfigMap.get(item.getTenantId());
                if(vauleMap == null){
                    vauleMap = new HashMap<>();
                }
                vauleMap.put(item.getThirdType(),item);
                ThirdConfigFactory.thirdConfigMap.put(item.getTenantId(),vauleMap);
            });
        }
    }



    @Override
    public List<ThirdConfig> getThirdConfigListByThirdType(Integer tenantId) {
        return baseMapper.getThirdConfigListByThirdType(tenantId);
    }

    @Override
    public ThirdConfig getThirdConfigByThirdType(Integer tenantId, String thirdType) {
        return baseMapper.getThirdConfigByThirdType(tenantId,thirdType);
    }

    @Override
    public List<ThirdConfig> getThirdAppConfigByClientId(String clientId) {
        LambdaQueryWrapper<ThirdConfig> query = new LambdaQueryWrapper<>();
        query.eq(ThirdConfig::getClientId,clientId);
        List<ThirdConfig> sysThirdAppConfigs = baseMapper.selectList(query);
        return sysThirdAppConfigs;
    }

}
