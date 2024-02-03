package com.qfy.modules.city3d.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.qfy.modules.city3d.mapper.HouseMapperC;
import com.qfy.modules.city3d.po.House;
import com.qfy.modules.city3d.service.HouseService;
import org.springframework.stereotype.Service;

@Service
public class HouseServiceImpl implements HouseService {
	@Autowired
	private HouseMapperC houseMapperC;

	@Override
	public House findHouseById(String houseId) {
		House result = houseMapperC.selectHouseById(houseId);
		return result;
	}

	@Override
	public List<House> queryHouse(House house) {
		List<House> result = houseMapperC.selectByExampleH(house);
		return result;
	}

	@Override
	public List<House> getHouseByAddId(String addId) {
		List<House> result = houseMapperC.selectHouseByAddId(addId);
		return result;
	}

	@Override
	public int insert(House house){
		int result = houseMapperC.insert(house);
		return result;
	}

	@Override
	public int updateByPrimaryKey(House house){
		int result = houseMapperC.updateByPrimaryKey(house);
		return result;
	}

	@Override
	public int deleteByPrimaryKey(String houseId){
		int result = houseMapperC.deleteByPrimaryKey(houseId);
		return result;
	}

	@Override
	public int batchDelete(List<String> houseIds) {
		int result = houseMapperC.batchDelete(houseIds);
		return result;
	}
}
