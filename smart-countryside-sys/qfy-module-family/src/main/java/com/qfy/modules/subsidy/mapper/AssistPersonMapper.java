package com.qfy.modules.subsidy.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.qfy.modules.subsidy.entity.AssistPerson;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @Description: 帮扶联系人
 * @Author: jeecg-boot
 * @Date:   2022-12-04
 * @Version: V1.0
 */
public interface AssistPersonMapper extends BaseMapper<AssistPerson> {

    @Select("select a.id,a.realname,a.telephone,a.card_number,a.unit,b.name as ext1 from qfy_assist_person a " +
            "left join qfy_assist_unit b on a.unit = b.id " +
            "left join qfy_assist_rel c on a.id = c.user_id ${ew.customSqlSegment}")
    AssistPerson selectOneByCustom(@Param(Constants.WRAPPER) Wrapper wrapper);

}
