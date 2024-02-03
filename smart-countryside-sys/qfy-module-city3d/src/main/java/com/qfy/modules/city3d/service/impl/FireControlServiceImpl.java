package com.qfy.modules.city3d.service.impl;

import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.qfy.modules.city3d.mapper.FireControlMapperC;
import com.qfy.modules.city3d.po.FireControl;
import com.qfy.modules.city3d.po.FireControlExample;
import com.qfy.modules.city3d.po.FireControlExample.Criteria;
import com.qfy.modules.city3d.service.FireControlService;
import org.springframework.stereotype.Service;

@Service
public class FireControlServiceImpl implements FireControlService {
	@Autowired
	private FireControlMapperC fireControlMapperC;

	@Override
	public List<HashMap<String, String>> getFireControl() {
		List<HashMap<String, String>> result = fireControlMapperC.getIDandPOI();
		return result;
	}

	@Override
	public List<FireControl> getFireControlNoPic() {
		List<FireControl> result = fireControlMapperC.getAllNoPic();
		return result;
	}

	@Override
	public FireControl getFireControlById(String fireCtlId) {
		FireControl result = fireControlMapperC.selectFireControlById(fireCtlId);
		return result;
	}

	@Override
	public List<FireControl> queryFireControl(FireControl fireControl) {
		FireControlExample example = new FireControlExample();
		Criteria criteriaResult = example.or();
		if(fireControl.getFireCtlName()!=null){criteriaResult=criteriaResult.andFireCtlNameLike("%"+fireControl.getFireCtlName()+"%");};
		if(fireControl.getFireCtlPerson()!=null){criteriaResult=criteriaResult.andFireCtlPersonLike("%"+fireControl.getFireCtlPerson()+"%");};
		if(fireControl.getFireCtlLevel()!=null){criteriaResult=criteriaResult.andFireCtlLevelLike("%"+fireControl.getFireCtlLevel()+"%");};
		List<FireControl> result = fireControlMapperC.selectByExampleC(example);
		return result;
	}

	@Override
	public int insert(FireControl fireControl){
		int result = fireControlMapperC.insert(fireControl);
		return result;
	}

	@Override
	public int updateByPrimaryKey(FireControl fireControl){
		int result = fireControlMapperC.updateByPrimaryKey(fireControl);
		return result;
	}

	@Override
	public int deleteByPrimaryKey(String fireCtlId){
		int result = fireControlMapperC.deleteByPrimaryKey(fireCtlId);
		return result;
	}
}
