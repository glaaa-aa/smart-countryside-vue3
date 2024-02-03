package com.qfy.modules.city3d.service.impl;

import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.qfy.modules.city3d.mapper.KeyPeopleMapperC;
import com.qfy.modules.city3d.po.KeyPeople;
import com.qfy.modules.city3d.po.KeyPeopleExample;
import com.qfy.modules.city3d.po.KeyPeopleExample.Criteria;
import com.qfy.modules.city3d.service.KeyPeopleService;
import org.springframework.stereotype.Service;

@Service
public class KeyPeopleServiceImpl implements KeyPeopleService {
	@Autowired
	private KeyPeopleMapperC keyPeopleMapperC;

	@Override
	public List<HashMap<String, String>> getKeypeople() {
		List<HashMap<String, String>> result = keyPeopleMapperC.getIDandPOI();
		return result;
	}

	@Override
	public List<KeyPeople> getKeypeopleAll() {
		List<KeyPeople> result = keyPeopleMapperC.selectByExample(null);
		return result;
	}

	@Override
	public List<KeyPeople> getKeypeopleNoPic() {
		List<KeyPeople> result = keyPeopleMapperC.getAllNoPic();
		return result;
	}

	@Override
	public KeyPeople getKeypeopleById(String keyId) {
		KeyPeople result = keyPeopleMapperC.selectKeypeopleById(keyId);
		return result;
	}

	@Override
	public List<KeyPeople> queryKeypeoplebyExample(KeyPeople keyPeople) {
		KeyPeopleExample example = new KeyPeopleExample();
		Criteria criteriaResult = example.or();
		if(keyPeople.getName()!=null){criteriaResult=criteriaResult.andNameLike("%"+keyPeople.getName()+"%");};
		if(keyPeople.getIdentity()!=null){criteriaResult=criteriaResult.andIdentityLike("%"+keyPeople.getIdentity()+"%");};
		if(keyPeople.getNation()!=null){criteriaResult=criteriaResult.andNationLike("%"+keyPeople.getNation()+"%");};
		if(keyPeople.getType()!=null){criteriaResult=criteriaResult.andTypeLike("%"+keyPeople.getType()+"%");};
		if(keyPeople.getJob()!=null){criteriaResult=criteriaResult.andJobLike("%"+keyPeople.getJob()+"%");};
		if(keyPeople.getTel()!=null){criteriaResult=criteriaResult.andTelLike("%"+keyPeople.getTel()+"%");};
		if(keyPeople.getBirthday()!=null){criteriaResult=criteriaResult.andBirthdayLike("%"+keyPeople.getBirthday()+"%");};
		List<KeyPeople> result = keyPeopleMapperC.selectByExampleC(example);
		return result;
	}

	@Override
	public KeyPeople queryKeypeopleByIdentity(String identity) {
		KeyPeople result = keyPeopleMapperC.queryKeypeopleByIdentity(identity);
		return result;
	}

	@Override
	public int insert(KeyPeople keyPeople){
		int result = keyPeopleMapperC.insert(keyPeople);
		return result;
	}

	@Override
	public int updateByPrimaryKey(KeyPeople keyPeople){
		int result = keyPeopleMapperC.updateByPrimaryKey(keyPeople);
		return result;
	}

	@Override
	public int deleteByPrimaryKey(String kpId){
		int result = keyPeopleMapperC.deleteByPrimaryKey(kpId);
		return result;
	}
}
