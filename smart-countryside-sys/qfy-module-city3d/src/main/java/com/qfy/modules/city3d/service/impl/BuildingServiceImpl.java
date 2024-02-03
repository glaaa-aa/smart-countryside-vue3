package com.qfy.modules.city3d.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.qfy.modules.city3d.mapper.BuildingMapperC;
import com.qfy.modules.city3d.po.Building;
import com.qfy.modules.city3d.po.BuildingExample;
import com.qfy.modules.city3d.po.BuildingExample.Criteria;
import com.qfy.modules.city3d.service.BuildingService;
import org.springframework.stereotype.Service;

@Service
public class BuildingServiceImpl implements BuildingService {
	@Autowired
	private BuildingMapperC buildingMapper;

	@Override
	public List<Building> getBuilding() {
		List<Building> result = buildingMapper.selectByExample(null);
		return result;
	}

	@Override
	public Building getBuildingById(String buildingId) {
		Building result = buildingMapper.selectByPrimaryKey(buildingId);
		return result;
	}

	@Override
	public int insert(Building building) {
		int result = buildingMapper.insert(building);
		return result;
	}

	@Override
	public int updateByPrimaryKey(Building building) {
		int result = buildingMapper.updateByPrimaryKey(building);
		return result;
	}

	@Override
	public int deleteByPrimaryKey(String buildingId) {
		int result = buildingMapper.deleteByPrimaryKey(buildingId);
		return result;
	}

	@Override
	public List<Building> queryBuilding(Building building) {
		BuildingExample example = new BuildingExample();
		Criteria criteriaResult = example.or();
		if(building.getDbnum()!=null) {criteriaResult=criteriaResult.andDbnumLike("%"+building.getDbnum()+"%");}
		if(building.getTdxz()!=null) {criteriaResult=criteriaResult.andTdxzLike("%"+building.getTdxz()+"%");}
		if(building.getYxgs()!=null) {criteriaResult=criteriaResult.andYxgsLike("%"+building.getYxgs()+"%");}
		if(building.getFwcs()!=null) {criteriaResult=criteriaResult.andFwcsLike("%"+building.getFwcs()+"%");}
		if(building.getFwjg()!=null) {criteriaResult=criteriaResult.andFwjgLike("%"+building.getFwjg()+"%");}
		if(building.getZdh()!=null) {criteriaResult=criteriaResult.andZdhLike("%"+building.getZdh()+"%");}
		if(building.getFczh()!=null) {criteriaResult=criteriaResult.andFczhLike("%"+building.getFczh()+"%");}
		List<Building> result = buildingMapper.selectByExample(example);
		return result;
	}

	@Override
	public int batchDelete(List<String> buildingIds) {
		int result = buildingMapper.batchDelete(buildingIds);
		return result;
	}
}
