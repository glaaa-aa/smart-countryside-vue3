package com.qfy.modules.family.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qfy.modules.family.entity.FamilyBaseProduction;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Description: 家庭生产信息
 * @Author: jeecg-boot
 * @Date:   2022-06-02
 * @Version: V1.0
 */
public interface FamilyBaseProductionMapper extends BaseMapper<FamilyBaseProduction> {

    @Delete("DELETE FROM qfy_family_base_production WHERE family_number = #{mainId}")
    public boolean deleteByMainId(String mainId);

    /**
     * 通过主表订单外键查询住房信息
     * @param mainId 订单id
     * @return 返回住房信息
     */
    @Select("SELECT * FROM qfy_family_base_production WHERE family_number = #{mainId}")
    public List<FamilyBaseProduction> selectProductionByMainId(String mainId);

    /**
     * 根据户编号和年 获取本年家庭生产信息 唯一数据
     * @param familyNumber
     * @param year
     * @return
     */
    public FamilyBaseProduction queryProduction(String familyNumber, String year);

    /**
     * 产业、就业--查询鸡鸭牛猪及其他的数量
     * @return
     */
    @Select("select sum(total_pig) AS total_pig,sum(total_cattle) AS total_cattle, sum(total_chicken) AS total_chicken,sum(total_sheep) AS total_sheep," +
            "sum(total_other) AS total_other ," +
            "(select count(*) from qfy_family_member where s9 =1)AS qwWorkTotal," +
            "(select count(*) from qfy_family_member where s9 =0)AS qnWorkTotal " +
            "from  qfy_family_base_production" )
    FamilyBaseProduction queryIndustryCount();

    /**
     * 根据需求联表分页查询产业就业表和基本表信息
     * @param queryWrapper
     * @return
     */
    @Select("select m.*,base.add_id,base.id as base_id from qfy_family_base_production m left join qfy_family_base base on base.family_number = m.family_number"+
            " ${ew.customSqlSegment} ")
    List<FamilyBaseProduction> productionAndAddresslist(@Param("ew") QueryWrapper<FamilyBaseProduction> queryWrapper);
}
