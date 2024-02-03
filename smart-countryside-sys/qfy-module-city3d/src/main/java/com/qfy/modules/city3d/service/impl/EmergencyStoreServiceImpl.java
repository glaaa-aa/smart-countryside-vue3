package com.qfy.modules.city3d.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.qfy.modules.city3d.mapper.EmergencyStoreMapperC;
import com.qfy.modules.city3d.po.EmergencyStoreExample.Criteria;
import com.qfy.modules.city3d.po.EmergencyStore;
import com.qfy.modules.city3d.po.EmergencyStoreExample;
import com.qfy.modules.city3d.service.EmergencyStoreService;
import org.springframework.stereotype.Service;

@Service
public class EmergencyStoreServiceImpl implements EmergencyStoreService {
	@Autowired
	private EmergencyStoreMapperC emergencyStoreMapperC;

	@Override
	public List<HashMap<String, String>> getEmergencyStore() {
		List<HashMap<String, String>> result = emergencyStoreMapperC.getIDandPOI();
		return result;
	}

	@Override
	public List<EmergencyStore> getEmergencyStoreAll() {
		List<EmergencyStore> result = emergencyStoreMapperC.selectByExample(null);
		return result;
	}

	@Override
	public EmergencyStore getEmergencyStoreById(String storeId) {
		EmergencyStore result = emergencyStoreMapperC.selectEmergencyStoreById(storeId);
		return result;
	}

	@Override
	public List<EmergencyStore> queryEmergencyStore(EmergencyStore emergencyStore) {
		EmergencyStoreExample example = new EmergencyStoreExample();
		Criteria criteriaResult = example.or();
		if(emergencyStore.getType()!=null){criteriaResult=criteriaResult.andTypeLike("%"+emergencyStore.getType()+"%");};
		List<EmergencyStore> result = emergencyStoreMapperC.selectByExampleC(example);
		return result;
	}
	@Override
	public int insert(EmergencyStore emergencyStore){
		int result = emergencyStoreMapperC.insert(emergencyStore);
		return result;
	}
	@Override
	public int updateByPrimaryKey(EmergencyStore emergencyStore){
		int result = emergencyStoreMapperC.updateByPrimaryKey(emergencyStore);
		return result;
	}
	@Override
	public int deleteByPrimaryKey(String storeId){
		int result = emergencyStoreMapperC.deleteByPrimaryKey(storeId);
		return result;
	}
}
