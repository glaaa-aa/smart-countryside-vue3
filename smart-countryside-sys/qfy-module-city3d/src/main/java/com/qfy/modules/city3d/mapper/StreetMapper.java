package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qfy.modules.city3d.po.Street;
import com.qfy.modules.city3d.po.StreetExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@DS("postgresql")
public interface StreetMapper {
    int countByExample(StreetExample example);

    int deleteByExample(StreetExample example);

    int deleteByPrimaryKey(String streetId);

    int insert(Street record);

    int insertSelective(Street record);

    List<Street> selectByExample(StreetExample example);

    Street selectByPrimaryKey(String streetId);

    int updateByExampleSelective(@Param("record") Street record, @Param("example") StreetExample example);

    int updateByExample(@Param("record") Street record, @Param("example") StreetExample example);

    int updateByPrimaryKeySelective(Street record);

    int updateByPrimaryKey(Street record);
}
