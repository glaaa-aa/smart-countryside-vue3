package com.qfy.modules.family.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qfy.modules.family.entity.FamilyBaseHouse;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Description: 家庭住房信息
 * @Author: jeecg-boot
 * @Date:   2022-06-02
 * @Version: V1.0
 */
public interface FamilyBaseHouseMapper extends BaseMapper<FamilyBaseHouse> {

    /**
     *  通过主表外键批量删除客户
     * @param mainId
     * @return
     */
    @Delete("DELETE FROM qfy_family_base_house WHERE family_number = #{mainId}")
    public boolean deleteByMainId(String mainId);
    /**
     * 通过主表户编号查询住房信息
     * @param mainId 户编号
     * @return 返回住房信息
     */
    @Select("SELECT * FROM qfy_family_base_house WHERE family_number = #{mainId}")
    public List<FamilyBaseHouse> selectHouseByMainId(String mainId);

    /**
     * 根据户编号和年 获取本年房屋信息 唯一数据
     * @param familyNumber
     * @param year
     * @return
     */
    public FamilyBaseHouse queryHouse(String familyNumber,String year);
}
