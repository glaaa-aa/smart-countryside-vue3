package com.qfy.modules.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qfy.modules.api.domain.FamilyRoster;
import com.qfy.modules.api.mapper.FamilyRosterMapper;
import com.qfy.modules.api.service.IFamilyRosterService;
import com.qfy.modules.family.entity.FamilyMember;
import com.qfy.modules.family.mapper.FamilyMemberMapper;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author lanls
 * @version v1.0.0
 * @since 2023/11/18 15:50
 */
@Service
public class FamilyRosterServiceImpl extends ServiceImpl<FamilyRosterMapper, FamilyRoster> implements IFamilyRosterService {

    @Resource
    private FamilyMemberMapper familyMemberMapper;
    @Override
    public IPage<FamilyRoster> queryList(IPage<FamilyRoster> page, String name, String orgCode) {
        QueryWrapper<FamilyRoster> wrapper = new QueryWrapper<>();
        wrapper.apply("a.family_number = b.family_number");
        wrapper.apply("a.id_card = b.id_card");
        if (StringUtils.isNotBlank(name)) {
            wrapper.like("b.name", name);
        }
        if (StringUtils.isNotBlank(orgCode)) {
            wrapper.likeRight("a.org_code", orgCode);
        }
        wrapper.orderByAsc("a.family_number");
        IPage<FamilyRoster> pageList = baseMapper.selectFamilyRoster(page, wrapper);
        return pageList;
    }

    @Override
    public IPage<FamilyRoster> queryList(IPage<FamilyRoster> page, String name, String orgCode, String idCard) {
        QueryWrapper<FamilyRoster> wrapper = new QueryWrapper<>();
        wrapper.apply("a.family_number = b.family_number");
        if (StringUtils.isNotBlank(name)) {
            wrapper.like("a.name", name);
        }
        if (StringUtils.isNotBlank(orgCode)) {
            wrapper.likeRight("b.org_code", orgCode);
        }
        wrapper.orderByAsc("a.family_number");
        IPage<FamilyRoster> pageList = baseMapper.selectFamilyMembers(page, wrapper);
        return pageList;
    }

    @Override
    public FamilyMember queryByIdCard(String idCard) {
        if (StringUtils.isNotBlank(idCard)) {
            QueryWrapper<FamilyMember> wrapper = new QueryWrapper<>();
            wrapper.eq("id_card", idCard);
            wrapper.last("limit 1");
            FamilyMember familyMember = familyMemberMapper.selectOne(wrapper);
            return familyMember;
        }
        return null;
    }
}
