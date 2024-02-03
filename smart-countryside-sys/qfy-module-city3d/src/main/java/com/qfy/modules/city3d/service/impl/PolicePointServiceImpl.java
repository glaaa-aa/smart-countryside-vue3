package com.qfy.modules.city3d.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.qfy.modules.city3d.mapper.PolicePointMapperC;
import com.qfy.modules.city3d.po.PolicePoint;
import com.qfy.modules.city3d.po.PolicePointExample;
import com.qfy.modules.city3d.po.PolicePointExample.Criteria;
import com.qfy.modules.city3d.service.PolicePointService;
import org.springframework.stereotype.Service;

@Service
public class PolicePointServiceImpl implements PolicePointService {
	@Autowired
	private PolicePointMapperC policePointMapper;

	@Override
	public List<PolicePoint> queryPolicePoint(PolicePoint policePoint) {
		PolicePointExample example = new PolicePointExample();
		Criteria criteriaResult = example.or();
		if(policePoint.getpName()!=null){criteriaResult=criteriaResult.andPNameLike("%"+policePoint.getpName()+"%");}
		if(policePoint.getpType()!=null){criteriaResult=criteriaResult.andPTypeLike("%"+policePoint.getpType()+"%");}
		List<PolicePoint> result = policePointMapper.selectByExample(example);
		return result;
	}

	@Override
	public List<PolicePoint> getPolicePoint() {
		List<PolicePoint> result = policePointMapper.selectByExample(null);
		return result;
	}

	@Override
	public PolicePoint findPolicePointById(String poiId) {
		PolicePoint result = policePointMapper.selectByPrimaryKey(poiId);
		return result;
	}

	@Override
	public int insert(PolicePoint policePoint) {
		int result = policePointMapper.insert(policePoint);
		return result;
	}

	@Override
	public int updateByPrimaryKey(PolicePoint policePoint) {
		int result = policePointMapper.updateByPrimaryKey(policePoint);
		return result;
	}

	@Override
	public int deleteByPrimaryKey(String poiId) {
		int result = policePointMapper.deleteByPrimaryKey(poiId);
		return result;
	}

	@Override
	public int batchDelete(List<String> poiIds) {
		int result = policePointMapper.batchDelete(poiIds);
		return result;
	}
}
