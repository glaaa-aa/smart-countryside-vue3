package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qfy.modules.city3d.po.Roam;
import com.qfy.modules.city3d.po.RoamExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@DS("postgresql")
public interface RoamMapper {
    int countByExample(RoamExample example);

    int deleteByExample(RoamExample example);

    int deleteByPrimaryKey(String roamId);

    int insert(Roam record);

    int insertSelective(Roam record);

    List<Roam> selectByExample(RoamExample example);

    Roam selectByPrimaryKey(String roamId);

    int updateByExampleSelective(@Param("record") Roam record, @Param("example") RoamExample example);

    int updateByExample(@Param("record") Roam record, @Param("example") RoamExample example);

    int updateByPrimaryKeySelective(Roam record);

    int updateByPrimaryKey(Roam record);
}
