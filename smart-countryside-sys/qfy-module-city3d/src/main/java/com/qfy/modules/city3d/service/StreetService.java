package com.qfy.modules.city3d.service;

import java.util.List;

import com.qfy.modules.city3d.po.Street;

public interface StreetService {
	public List<Street> getStreetAll();
	public int insert(Street street);
	public int updateByPrimaryKey(Street street);
	public int deleteByPrimaryKey(String streetId);
	public int batchDelete(List<String> streetIds);
}
