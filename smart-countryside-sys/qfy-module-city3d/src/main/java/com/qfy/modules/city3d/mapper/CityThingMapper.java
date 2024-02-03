package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qfy.modules.city3d.po.CityThing;
import com.qfy.modules.city3d.po.CityThingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@DS("postgresql")
public interface CityThingMapper {
    int countByExample(CityThingExample example);

    int deleteByExample(CityThingExample example);

    int deleteByPrimaryKey(String thingId);

    int insert(CityThing record);

    int insertSelective(CityThing record);

    List<CityThing> selectByExample(CityThingExample example);

    CityThing selectByPrimaryKey(String thingId);

    int updateByExampleSelective(@Param("record") CityThing record, @Param("example") CityThingExample example);

    int updateByExample(@Param("record") CityThing record, @Param("example") CityThingExample example);

    int updateByPrimaryKeySelective(CityThing record);

    int updateByPrimaryKey(CityThing record);
}
