package com.qfy.modules.family.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qfy.modules.family.entity.FamilyIntegralRecord;
import org.apache.ibatis.annotations.Delete;

/**
 * @Description: 农户积分申请记录
 * @Author: jeecg-boot
 * @Date:   2022-12-01
 * @Version: V1.0
 */
public interface FamilyIntegralRecordMapper extends BaseMapper<FamilyIntegralRecord> {

    @Delete("DELETE FROM qfy_family_integral_record WHERE family_number = #{mainId}")
    boolean deleteByMainId(String mainId);
}
