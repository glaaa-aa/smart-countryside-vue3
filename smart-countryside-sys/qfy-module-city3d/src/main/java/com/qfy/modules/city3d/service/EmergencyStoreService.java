package com.qfy.modules.city3d.service;

import java.util.HashMap;
import java.util.List;

import com.qfy.modules.city3d.po.EmergencyStore;

public interface EmergencyStoreService {
	public List<HashMap<String, String>> getEmergencyStore();
	public List<EmergencyStore> getEmergencyStoreAll();
	public EmergencyStore getEmergencyStoreById(String storeId);
	public List<EmergencyStore> queryEmergencyStore(EmergencyStore emergencyStore);
	public int insert(EmergencyStore emergencyStore);
	public int updateByPrimaryKey(EmergencyStore emergencyStore);
	public int deleteByPrimaryKey(String storeId);
}
