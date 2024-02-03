package com.qfy.modules.city3d.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.qfy.modules.city3d.mapper.PoliceLineMapperC;
import com.qfy.modules.city3d.po.PoliceLine;
import com.qfy.modules.city3d.po.PoliceLineExample;
import com.qfy.modules.city3d.po.PoliceLineExample.Criteria;
import com.qfy.modules.city3d.service.PoliceLineService;
import org.springframework.stereotype.Service;

@Service
public class PoliceLineServiceImpl implements PoliceLineService {
	@Autowired
	private PoliceLineMapperC policeLineMapper;

	@Override
	public List<PoliceLine> queryPoliceLine(PoliceLine policeLine) {
		PoliceLineExample example = new PoliceLineExample();
		Criteria criteriaResult = example.or();
		if(policeLine.getlName()!=null){criteriaResult=criteriaResult.andLNameLike("%"+policeLine.getlName()+"%");}
		if(policeLine.getlType()!=null){criteriaResult=criteriaResult.andLTypeLike("%"+policeLine.getlType()+"%");}
		List<PoliceLine> result = policeLineMapper.selectByExample(example);
		return result;
	}

	@Override
	public List<PoliceLine> getPoliceLine() {
		List<PoliceLine> result = policeLineMapper.selectByExample(null);
		return result;
	}

	@Override
	public int insert(PoliceLine policeLine) {
		int result = policeLineMapper.insert(policeLine);
		return result;
	}

	@Override
	public int updateByPrimaryKey(PoliceLine policeLine) {
		int result = policeLineMapper.updateByPrimaryKey(policeLine);
		return result;
	}

	@Override
	public int deleteByPrimaryKey(String lineId) {
		int result = policeLineMapper.deleteByPrimaryKey(lineId);
		return result;
	}

	@Override
	public int batchDelete(List<String> lineIds) {
		int result = policeLineMapper.batchDelete(lineIds);
		return result;
	}
}
