package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qfy.modules.city3d.po.FireControl;
import com.qfy.modules.city3d.po.FireControlExample;
import java.util.HashMap;
import java.util.List;

@DS("postgresql")
public interface FireControlMapperC extends FireControlMapper {
	List<HashMap<String, String>> getIDandPOI();

	List<FireControl> getAllNoPic();

	FireControl selectFireControlById(String fcId);

	List<FireControl> selectByExampleC(FireControlExample example);
}
