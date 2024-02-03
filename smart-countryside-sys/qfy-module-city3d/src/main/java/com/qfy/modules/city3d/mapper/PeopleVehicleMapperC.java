package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qfy.modules.city3d.po.PeopleVehicle;
import java.util.List;

@DS("postgresql")
public interface PeopleVehicleMapperC extends PeopleVehicleMapper {
	public List<PeopleVehicle> selectPeopleVehicleAll();
	public int batchDelete(List<String> vehicleIds);
}
