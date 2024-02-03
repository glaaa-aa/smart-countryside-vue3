package com.qfy.modules.family.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qfy.modules.family.entity.FamilyBaseWater;
import com.qfy.modules.family.entity.FamilyMember;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @Description: 家庭成员管理
 * @Author: jeecg-boot
 * @Date:   2022-06-02
 * @Version: V1.0
 */
public interface FamilyMemberMapper extends BaseMapper<FamilyMember> {

    /**
     * 通过主表id删除子表数据
     *
     * @param mainId 主表id
     * @return boolean
     */
    public boolean deleteByMainId(@Param("mainId") String mainId);

    /**
     * 通过主表id查询子表数据
     *
     * @param mainId 主表id
     * @return List<FamilyMember>
     */
    public List<FamilyMember> selectByMainId(@Param("mainId") String mainId);

    @Select("select a.* from (select * from qfy_family_member where family_number = #{familyNumber}" +
            "  order by year desc limit 1000) a group by id_card")
//            "  order by year desc limit 1000) a ")
        List<FamilyMember> selectByGroupByIdCard(@Param("familyNumber") String familyNumber);

    /**
     * 根据需求联表分页查询成员表和基本表信息
     * @param page
     * @param queryWrapper
     * @return
     */
    @Select("select m.*,base.add_id,base.id as base_id from qfy_family_member m left join qfy_family_base base on base.family_number = m.family_number"+
            " ${ew.customSqlSegment} ")
    IPage<FamilyMember> queryPageListByParam(Page<FamilyMember> page, @Param("ew") Wrapper<FamilyMember> queryWrapper);

    /**
     * 根据需求联表分页查询家庭饮水安全信息表和基本表信息
     * @param page
     * @param queryWrapper
     * @return
     */
    @Select("select m.*,base.add_id,base.id as base_id from qfy_family_base_water m left join qfy_family_base base on base.family_number = m.family_number"+
            " ${ew.customSqlSegment} ")
    IPage<FamilyBaseWater> queryWaterAndBasePageList(Page<FamilyBaseWater> page, @Param("ew") Wrapper<FamilyBaseWater> queryWrapper);


    @Select("select a.*,b.org_code AS village,b.atural_code AS atural_tun,b.s1 AS tun_zone " +
            "from qfy_family_member a,qfy_family_base b ${ew.customSqlSegment}")
    IPage<FamilyMember> selectPage(Page<FamilyMember> page, @Param("ew") Wrapper<FamilyMember> queryWrapper);
}
