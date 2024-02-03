package com.qfy.modules.family.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qfy.modules.family.entity.FamilyBaseWater;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Description: 家庭饮水安全信息
 * @Author: jeecg-boot
 * @Date:   2022-06-02
 * @Version: V1.0
 */
public interface FamilyBaseWaterMapper extends BaseMapper<FamilyBaseWater> {

    @Delete("DELETE FROM qfy_family_base_water WHERE family_number = #{mainId}")
    public boolean deleteByMainId(String mainId);

    /**
     * 通过主表订单外键查询住房信息
     * @param mainId 订单id
     * @return 返回住房信息
     */
    @Select("SELECT * FROM qfy_family_base_water WHERE family_number = #{mainId}")
    public List<FamilyBaseWater> selectWaterByMainId(String mainId);

    /**
     * 根据户编号和年 获取本年用水安全 唯一数据
     * @param familyNumber
     * @param year
     * @return
     */
    public FamilyBaseWater queryWater(String familyNumber, String year);
}
