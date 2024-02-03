package com.qfy.modules.family.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qfy.modules.family.entity.IntegralCheckItem;
import java.util.List;

/**
 * @author lanls
 * @version v1.0.0
 * @since 2023/8/30 11:03
 */
public interface IIntegralCheckItemService extends IService<IntegralCheckItem> {
    /**
     * 方法描述：根据模板id查询全部的积分表单项
     * @param tplId 模板id
     * @return: java.util.List<com.qfy.modules.family.entity.IntegralCheckItem>
     * @author: lanls
     * @date: 2023/8/30 11:16
     */
    List<IntegralCheckItem> selectList(String tplId);
}
