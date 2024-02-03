package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qfy.modules.city3d.po.PeopleVehicle;
import com.qfy.modules.city3d.po.PeopleVehicleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@DS("postgresql")
public interface PeopleVehicleMapper {
    int countByExample(PeopleVehicleExample example);

    int deleteByExample(PeopleVehicleExample example);

    int deleteByPrimaryKey(String vehicleId);

    int insert(PeopleVehicle record);

    int insertSelective(PeopleVehicle record);

    List<PeopleVehicle> selectByExample(PeopleVehicleExample example);

    PeopleVehicle selectByPrimaryKey(String vehicleId);

    int updateByExampleSelective(@Param("record") PeopleVehicle record, @Param("example") PeopleVehicleExample example);

    int updateByExample(@Param("record") PeopleVehicle record, @Param("example") PeopleVehicleExample example);

    int updateByPrimaryKeySelective(PeopleVehicle record);

    int updateByPrimaryKey(PeopleVehicle record);
}
