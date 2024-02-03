package com.qfy.modules.city3d.service;

import java.util.List;

import com.qfy.modules.city3d.po.PolicePoint;

public interface PolicePointService {
	public List<PolicePoint> queryPolicePoint(PolicePoint policePoint);
	public List<PolicePoint> getPolicePoint();
	PolicePoint findPolicePointById(String poiId);
	public int insert(PolicePoint policePoint);
	public int updateByPrimaryKey(PolicePoint policePoint);
	public int deleteByPrimaryKey(String poiId);
	public int batchDelete(List<String> poiIds);
}
