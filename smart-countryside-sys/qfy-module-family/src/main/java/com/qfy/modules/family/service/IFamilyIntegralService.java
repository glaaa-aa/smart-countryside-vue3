package com.qfy.modules.family.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qfy.modules.family.entity.FamilyIntegral;
import java.util.Map;

/**
 * @Description: 农户积分手册
 * @Author: jeecg-boot
 * @Date:   2022-12-01
 * @Version: V1.0
 */
public interface IFamilyIntegralService extends IService<FamilyIntegral> {

    /**
     * 方法描述：农户基础积分账户同步
     * @param params
     * @return: void
     * @author: lanls
     * @date: 2023/8/14 10:59
     */
    void sync(Map<String,Object> params);
}
