package com.qfy.modules.family.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qfy.modules.family.entity.FamilyIntegral;
import org.apache.ibatis.annotations.Delete;

/**
 * @Description: 农户积分手册
 * @Author: jeecg-boot
 * @Date:   2022-12-01
 * @Version: V1.0
 */
public interface FamilyIntegralMapper extends BaseMapper<FamilyIntegral> {

    @Delete("DELETE FROM qfy_family_integral WHERE family_number = #{mainId}")
    boolean deleteByMainId(String mainId);

}
