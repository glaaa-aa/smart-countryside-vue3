package com.qfy.modules.city3d.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.qfy.modules.city3d.mapper.PoliceNetMapperC;
import com.qfy.modules.city3d.po.PoliceNet;
import com.qfy.modules.city3d.po.PoliceNetExample;
import com.qfy.modules.city3d.po.PoliceNetExample.Criteria;
import com.qfy.modules.city3d.service.PoliceNetService;
import org.springframework.stereotype.Service;

@Service
public class PoliceNetServiceImpl implements PoliceNetService {
	@Autowired
	private PoliceNetMapperC policeNetMapper;

	@Override
	public List<PoliceNet> getPoliceNet() {
		List<PoliceNet> result = policeNetMapper.selectByExample(null);
		return result;
	}

	@Override
	public List<PoliceNet> queryPoliceNet(PoliceNet policeNet) {
		PoliceNetExample example = new PoliceNetExample();
		Criteria criteriaResult = example.or();
		if(policeNet.getNetName()!=null){criteriaResult=criteriaResult.andNetNameLike("%"+policeNet.getNetName()+"%");}
		if(policeNet.getNetDep()!=null){criteriaResult=criteriaResult.andNetDepLike("%"+policeNet.getNetDep()+"%");}
		List<PoliceNet> result = policeNetMapper.selectByExample(example);
		return result;
	}

	@Override
	public int insert(PoliceNet policeNet) {
		int result = policeNetMapper.insert(policeNet);
		return result;
	}

	@Override
	public int updateByPrimaryKey(PoliceNet policeNet) {
		int result = policeNetMapper.updateByPrimaryKey(policeNet);
		return result;
	}

	@Override
	public int deleteByPrimaryKey(String netId) {
		int result = policeNetMapper.deleteByPrimaryKey(netId);
		return result;
	}

	@Override
	public int batchDelete(List<String> netIds) {
		int result = policeNetMapper.batchDelete(netIds);
		return result;
	}
}
