package com.qfy.modules.city3d.service;

import java.util.List;

import com.qfy.modules.city3d.po.PeopleVehicle;

public interface PeopleVehicleService {
	public List<PeopleVehicle> selectPeopleVehicleAll();
	public List<PeopleVehicle> findByPpId(String ppId);
	public int insert(PeopleVehicle entity);
	public int updateByPrimaryKey(PeopleVehicle entity);
	public int deleteByPrimaryKey(String id);
	public int batchDelete(List<String> vehicleIds);
}
