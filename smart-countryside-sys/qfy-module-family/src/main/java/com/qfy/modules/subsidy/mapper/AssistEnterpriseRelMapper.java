package com.qfy.modules.subsidy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qfy.modules.subsidy.entity.AssistEnterpriseRel;

/**
 * @Description: 企业帮扶关系
 * @Author: jeecg-boot
 * @Date:   2022-12-05
 * @Version: V1.0
 */
public interface AssistEnterpriseRelMapper extends BaseMapper<AssistEnterpriseRel> {

//    @Select("select a.id,a.family_number,a.eid,a.assist_method,b.name from qfy_assist_enterprise_rel a" +
//            " left join qfy_cooperative_enterprises b on a.eid = b.id ${ew.customSqlSegment}")
//    List<AssistEnterpriseRel> selectList(@Param(Constants.WRAPPER) Wrapper wrapper);
}
