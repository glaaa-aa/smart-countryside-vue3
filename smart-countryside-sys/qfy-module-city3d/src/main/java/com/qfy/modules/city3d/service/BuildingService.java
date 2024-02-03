package com.qfy.modules.city3d.service;

import java.util.List;

import com.qfy.modules.city3d.po.Building;

public interface BuildingService {
	public List<Building> getBuilding();
	public Building getBuildingById(String buildingId);
	public int insert(Building building);
	public int updateByPrimaryKey(Building building);
	public int deleteByPrimaryKey(String buildingId);
	public List<Building> queryBuilding(Building building);
	public int batchDelete(List<String> buildingIds);
}
