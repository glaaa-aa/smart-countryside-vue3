package com.qfy.modules.api.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qfy.modules.api.domain.FamilyRoster;
import org.apache.ibatis.annotations.Param;

/**
 * @author lanls
 * @version v1.0.0
 * @since 2023/11/18 15:51
 */
public interface FamilyRosterMapper extends BaseMapper<FamilyRoster> {

    IPage<FamilyRoster> selectFamilyRoster(IPage page, @Param("ew") Wrapper wrapper);
    IPage<FamilyRoster> selectFamilyMembers(IPage page, @Param("ew") Wrapper wrapper);

}
