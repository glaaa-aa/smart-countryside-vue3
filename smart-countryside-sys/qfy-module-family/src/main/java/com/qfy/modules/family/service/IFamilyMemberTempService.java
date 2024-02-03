package com.qfy.modules.family.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qfy.modules.family.entity.FamilyMemberTemp;

/**
 * @author lanls
 * @version 1.0
 *
 */
public interface IFamilyMemberTempService extends IService<FamilyMemberTemp> {

    /**
     * 方法描述：批量更新指定用户创建的数据
     * @param username
     * @return: void
     * @author: lanls
     * @date: 2023/8/3 22:13
     */
    void batchUpdateByUsername(String username);
}
