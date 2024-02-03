package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qfy.modules.city3d.po.EmergencyStore;
import com.qfy.modules.city3d.po.EmergencyStoreExample;
import java.util.HashMap;
import java.util.List;
@DS("postgresql")
public interface EmergencyStoreMapperC extends EmergencyStoreMapper {
	List<HashMap<String, String>> getIDandPOI();

	EmergencyStore selectEmergencyStoreById(String storeId);

	List<EmergencyStore> selectByExampleC(EmergencyStoreExample example);
}
