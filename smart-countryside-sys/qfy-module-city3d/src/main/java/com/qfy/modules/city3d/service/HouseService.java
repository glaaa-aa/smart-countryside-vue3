package com.qfy.modules.city3d.service;

import java.util.List;

import com.qfy.modules.city3d.po.House;

public interface HouseService {
	public House findHouseById(String houseId);
	public List<House> queryHouse(House house);
	public List<House> getHouseByAddId(String addId);
	public int insert(House house);
	public int updateByPrimaryKey(House house);
	public int deleteByPrimaryKey(String houseId);
	public int batchDelete(List<String> houseIds);
}
