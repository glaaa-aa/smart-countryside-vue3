package com.qfy.modules.api.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qfy.modules.api.domain.FamilyRoster;
import com.qfy.modules.family.entity.FamilyMember;

/**
 * @author lanls
 * @version v1.0.0
 * @since 2023/11/18 15:48
 */
public interface IFamilyRosterService extends IService<FamilyRoster> {

    IPage<FamilyRoster> queryList(IPage<FamilyRoster> page, String name, String orgCode);

    IPage<FamilyRoster> queryList(IPage<FamilyRoster> page, String name, String orgCode, String idCard);

    FamilyMember queryByIdCard(String idCard);
}
