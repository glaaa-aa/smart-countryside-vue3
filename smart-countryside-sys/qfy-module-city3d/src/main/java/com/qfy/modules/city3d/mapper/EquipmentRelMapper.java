package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qfy.modules.city3d.po.EquipmentRel;
import com.qfy.modules.city3d.po.EquipmentRelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
@DS("postgresql")
public interface EquipmentRelMapper {
    int countByExample(EquipmentRelExample example);

    int deleteByExample(EquipmentRelExample example);

    int deleteByPrimaryKey(String equipRelId);

    int insert(EquipmentRel record);

    int insertSelective(EquipmentRel record);

    List<EquipmentRel> selectByExample(EquipmentRelExample example);

    EquipmentRel selectByPrimaryKey(String equipRelId);

    int updateByExampleSelective(@Param("record") EquipmentRel record, @Param("example") EquipmentRelExample example);

    int updateByExample(@Param("record") EquipmentRel record, @Param("example") EquipmentRelExample example);

    int updateByPrimaryKeySelective(EquipmentRel record);

    int updateByPrimaryKey(EquipmentRel record);
}
