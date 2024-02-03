package com.qfy.modules.city3d.service;

import java.util.List;

import com.qfy.modules.city3d.po.Police;

public interface PoliceService {
	public List<Police> getPolice();
	public int insert(Police police);
	public int updateByPrimaryKey(Police police);
	public int deleteByPrimaryKey(String policeId);
	public List<Police> findPoliceByPuid(String deviceNum);
	public List<Police> getPoliceRealDateWithManPos();
	public Police getPoliceAndEquipmentsByPuid(String deviceNum);
}
