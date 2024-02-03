package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qfy.modules.city3d.po.PoliceNet;
import com.qfy.modules.city3d.po.PoliceNetExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@DS("postgresql")
public interface PoliceNetMapper {
    int countByExample(PoliceNetExample example);

    int deleteByExample(PoliceNetExample example);

    int deleteByPrimaryKey(String netId);

    int insert(PoliceNet record);

    int insertSelective(PoliceNet record);

    List<PoliceNet> selectByExample(PoliceNetExample example);

    PoliceNet selectByPrimaryKey(String netId);

    int updateByExampleSelective(@Param("record") PoliceNet record, @Param("example") PoliceNetExample example);

    int updateByExample(@Param("record") PoliceNet record, @Param("example") PoliceNetExample example);

    int updateByPrimaryKeySelective(PoliceNet record);

    int updateByPrimaryKey(PoliceNet record);
}
