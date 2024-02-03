package com.qfy.modules.city3d.service;

import java.util.List;
import java.util.Map;

import com.qfy.modules.city3d.po.VehicleRealData;

public interface VehicleRealDataService {
	public VehicleRealData getVehicleByPhone(Map<String, Object> params);
	public List<VehicleRealData> getVehiclePartByPhone(List<String> simNos);
}
