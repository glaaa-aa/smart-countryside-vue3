package com.qfy.modules.city3d.service;

import java.util.List;

import com.qfy.modules.city3d.po.HousePanorama;

public interface HousePanoramaService {
	public List<HousePanorama> getHousePanoramaByHosueId(String houseId);
	public int insert(HousePanorama housePanorama);
	public int deleteByPrimaryKey(String panoramaId);
}
