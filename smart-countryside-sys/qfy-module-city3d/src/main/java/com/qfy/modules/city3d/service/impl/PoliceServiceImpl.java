package com.qfy.modules.city3d.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.qfy.modules.city3d.mapper.PoliceMapperC;
import com.qfy.modules.city3d.po.Police;
import com.qfy.modules.city3d.po.PoliceExample;
import com.qfy.modules.city3d.service.PoliceService;
import org.springframework.stereotype.Service;

@Service
public class PoliceServiceImpl implements PoliceService {
	@Autowired
	private PoliceMapperC policeMapperC;

	@Override
	public List<Police> getPolice(){
		List<Police> result = policeMapperC.selectByExample(null);
		return result;
	}

	@Override
	public int insert(Police police) {
		int result = policeMapperC.insert(police);
		return result;
	}

	@Override
	public int updateByPrimaryKey(Police police) {
		int result = policeMapperC.updateByPrimaryKey(police);
		return result;
	}

	@Override
	public int deleteByPrimaryKey(String policeId) {
		int result = policeMapperC.deleteByPrimaryKey(policeId);
		return result;
	}

	@Override
	public List<Police> findPoliceByPuid(String deviceNum) {
		PoliceExample example = new PoliceExample();
		example.or().andDeviceNumEqualTo(deviceNum);
		List<Police> result = policeMapperC.selectByExample(example);
		return result;
	}

	@Override
	public List<Police> getPoliceRealDateWithManPos() {
		List<Police> result = policeMapperC.getPoliceRealDateWithManPos();
		return result;
	}

	@Override
	public Police getPoliceAndEquipmentsByPuid(String deviceNum) {
		Police result = policeMapperC.getPoliceAndEquipmentsByPuid(deviceNum);
		return result;
	}
}
