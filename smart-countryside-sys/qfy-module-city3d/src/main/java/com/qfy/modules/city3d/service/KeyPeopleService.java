package com.qfy.modules.city3d.service;

import java.util.HashMap;
import java.util.List;
import com.qfy.modules.city3d.po.KeyPeople;

public interface KeyPeopleService {
	public List<HashMap<String, String>> getKeypeople();
	public List<KeyPeople> getKeypeopleAll();
	public List<KeyPeople> getKeypeopleNoPic();
	public KeyPeople getKeypeopleById(String keyId);
	public List<KeyPeople> queryKeypeoplebyExample(KeyPeople keyPeople);
	public KeyPeople queryKeypeopleByIdentity(String identity);
	public int insert(KeyPeople keyPeople);
	public int updateByPrimaryKey(KeyPeople keyPeople);
	public int deleteByPrimaryKey(String kpId);
}
