package com.qfy.modules.family.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qfy.modules.family.entity.FamilyMemberTemp;
import org.apache.ibatis.annotations.Param;

/**
 * @author lanls
 * @version 1.0
 */
public interface FamilyMemberTempMapper extends BaseMapper<FamilyMemberTemp> {

    void batchUpdateByUsername(@Param("username") String username);
}
