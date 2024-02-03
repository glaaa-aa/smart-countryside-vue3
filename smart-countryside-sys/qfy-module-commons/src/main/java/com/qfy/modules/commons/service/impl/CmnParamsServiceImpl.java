package com.qfy.modules.commons.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qfy.modules.commons.SystemConstant;
import com.qfy.modules.commons.entity.CmnParams;
import com.qfy.modules.commons.mapper.CmnParamsMapper;
import com.qfy.modules.commons.service.ICmnParamsService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 系统全局参数
 * @Author: jeecg-boot
 * @Date:   2022-12-08
 * @Version: V1.0
 */
@Service
public class CmnParamsServiceImpl extends ServiceImpl<CmnParamsMapper, CmnParams> implements ICmnParamsService {

    @Override
    public void reload() {
        List<CmnParams> list = list();
        SystemConstant.sysMapValue.clear();
        if (list != null){
            for (CmnParams cmnParams : list) {
                SystemConstant.sysMapValue.put(cmnParams.getParamkey(),cmnParams.getParamvalue());
            }
        }
    }
}
