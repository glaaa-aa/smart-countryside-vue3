package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qfy.modules.city3d.po.VehicleRealData;
import java.util.List;
import java.util.Map;

@DS("postgresql")
public interface VehicleRealDataMapperC extends VehicleRealDataMapper {
	VehicleRealData selectByPhone(Map<String, Object> map);
	public List<VehicleRealData> getVehiclePartByPhone(List<String> simNos);
}
