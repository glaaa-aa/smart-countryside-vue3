package com.qfy.modules.city3d.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.qfy.modules.city3d.mapper.PeopleVehicleMapperC;
import com.qfy.modules.city3d.po.PeopleVehicle;
import com.qfy.modules.city3d.po.PeopleVehicleExample;
import com.qfy.modules.city3d.service.PeopleVehicleService;
import org.springframework.stereotype.Service;

@Service
public class PeopleVehicleServiceImpl implements PeopleVehicleService {
	@Autowired
	private PeopleVehicleMapperC peopleVehicleMapperC;

	@Override
	public List<PeopleVehicle> selectPeopleVehicleAll() {
		List<PeopleVehicle> result = peopleVehicleMapperC.selectPeopleVehicleAll();
		return result;
	}

	@Override
	public List<PeopleVehicle> findByPpId(String ppId) {
		PeopleVehicleExample example = new PeopleVehicleExample();
		example.createCriteria().andPpIdEqualTo(ppId);
		List<PeopleVehicle> result = peopleVehicleMapperC.selectByExample(example);
		return result;
	}

	@Override
	public int insert(PeopleVehicle entity) {
		int result = peopleVehicleMapperC.insert(entity);
		return result;
	}

	@Override
	public int updateByPrimaryKey(PeopleVehicle entity) {
		int result = peopleVehicleMapperC.updateByPrimaryKey(entity);
		return result;
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		int result = peopleVehicleMapperC.deleteByPrimaryKey(id);
		return result;
	}

	@Override
	public int batchDelete(List<String> vehicleIds) {
		int result = peopleVehicleMapperC.batchDelete(vehicleIds);
		return result;
	}
}
