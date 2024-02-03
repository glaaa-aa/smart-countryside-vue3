package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qfy.modules.city3d.po.KeyPeople;
import com.qfy.modules.city3d.po.KeyPeopleExample;
import java.util.HashMap;
import java.util.List;

@DS("postgresql")
public interface KeyPeopleMapperC extends KeyPeopleMapper {
	List<HashMap<String, String>> getIDandPOI();

	List<KeyPeople> getAllNoPic();

	KeyPeople selectKeypeopleById(String kpId);

	List<KeyPeople> selectByExampleC(KeyPeopleExample example);

	public KeyPeople queryKeypeopleByIdentity(String identity);
}
