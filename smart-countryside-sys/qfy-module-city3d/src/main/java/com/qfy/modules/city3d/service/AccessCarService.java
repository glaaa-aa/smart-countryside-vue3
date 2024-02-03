package com.qfy.modules.city3d.service;

import com.qfy.modules.city3d.po.AccessCar;
import java.util.List;

public interface AccessCarService {
	public List<AccessCar> getAccessCar();
	public int deleteByPrimaryKey(String accessCarId);
	public int batchDelete(List<String> accessCarIds);
}
