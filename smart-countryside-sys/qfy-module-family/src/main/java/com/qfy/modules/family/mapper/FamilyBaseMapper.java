package com.qfy.modules.family.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qfy.modules.family.entity.FamilyBase;
import com.qfy.modules.family.entity.VillageRanking;
import com.qfy.modules.family.model.FamilyBasePage;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @Description: qfy_family_base
 * @Author: jeecg-boot
 * @Date:   2022-06-02
 * @Version: V1.0
 */
public interface FamilyBaseMapper extends BaseMapper<FamilyBase> {

    /**
     * 获取最大编号
     * @return
     */
    @Select("select max(family_number) from qfy_family_base")
    public String getMaxNumber();


    @Select("select * from qfy_family_base where family_number = #{familyNumber}")
    public FamilyBase getByfamilyNumber(String familyNumber);

    /**
     * 唯一查询
     * @param familyNumber
     * @param year 年
     * @return
     */
//    public FamilyBasePage queryFamilyBy(@Param("familyNumber") String familyNumber,String year);

    /**
     * 户综合查询
     * @param familyBase
     * @return
     */
    public List<FamilyBasePage> queryFamilyBase(Page<FamilyBasePage> page, @Param("familyBase") FamilyBase familyBase);

    List<VillageRanking> selectIndexChartStatics();

    List<VillageRanking> selectIndexVillageRanking();
}
