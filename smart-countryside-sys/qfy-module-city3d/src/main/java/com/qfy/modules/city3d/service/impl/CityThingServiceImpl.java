package com.qfy.modules.city3d.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.qfy.modules.city3d.mapper.CityThingMapperC;
import com.qfy.modules.city3d.po.CityThing;
import com.qfy.modules.city3d.po.CityThingExample;
import com.qfy.modules.city3d.service.CityThingService;
import org.springframework.stereotype.Service;

@Service
public class CityThingServiceImpl implements CityThingService {
	@Autowired
	private CityThingMapperC cityThingMapper;

	@Override
	public List<CityThing> getCityThing() {
		List<CityThing> result = cityThingMapper.selectByExample(null);
		return result;
	}

	@Override
	public List<CityThing> getCityThingByPid(String thingPid){
		CityThingExample example = new CityThingExample();
		example.or().andThingPidEqualTo(thingPid);
		List<CityThing> result = cityThingMapper.selectByExample(example);
		return result;
	}

	@Override
	public int insert(CityThing cityThing) {
		int result = cityThingMapper.insert(cityThing);
		return result;
	}

	@Override
	public int updateByPrimaryKey(CityThing cityThing) {
		int result = cityThingMapper.updateByPrimaryKey(cityThing);
		return result;
	}

	@Override
	public int deleteByPrimaryKey(String thingId) {
		int result = cityThingMapper.deleteByPrimaryKey(thingId);
		return result;
	}

	@Override
	public int batchDelete(List<String> thingIds) {
		int result = cityThingMapper.batchDelete(thingIds);
		return result;
	}
}
