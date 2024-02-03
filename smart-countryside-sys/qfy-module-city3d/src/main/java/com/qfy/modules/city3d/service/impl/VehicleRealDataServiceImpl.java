package com.qfy.modules.city3d.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.qfy.modules.city3d.mapper.VehicleRealDataMapperC;
import com.qfy.modules.city3d.po.VehicleRealData;
import com.qfy.modules.city3d.service.VehicleRealDataService;
import org.springframework.stereotype.Service;

@Service
public class VehicleRealDataServiceImpl implements VehicleRealDataService {
	@Autowired
	private VehicleRealDataMapperC vehicleRealDataMapperC;

	@Override
	public VehicleRealData getVehicleByPhone(Map<String, Object> params) {
		VehicleRealData result = vehicleRealDataMapperC.selectByPhone(params);
		return result;
	}

	@Override
	public List<VehicleRealData> getVehiclePartByPhone(List<String> simNos) {
		List<VehicleRealData> result = vehicleRealDataMapperC.getVehiclePartByPhone(simNos);
		return result;
	}
}
