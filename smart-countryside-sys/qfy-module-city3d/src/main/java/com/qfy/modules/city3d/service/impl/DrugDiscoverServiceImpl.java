package com.qfy.modules.city3d.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.qfy.modules.city3d.mapper.PeopleDrugDiscoverMapper;
import com.qfy.modules.city3d.po.PeopleDrugDiscover;
import com.qfy.modules.city3d.po.PeopleDrugDiscoverExample;
import com.qfy.modules.city3d.service.DrugDiscoverService;
import org.springframework.stereotype.Service;

@Service
public class DrugDiscoverServiceImpl implements DrugDiscoverService{
	@Autowired
	private PeopleDrugDiscoverMapper peopleDrugDiscoverMapper;

	@Override
	public List<PeopleDrugDiscover> findByPpId(String ppId) {
		PeopleDrugDiscoverExample example = new PeopleDrugDiscoverExample();
		example.createCriteria().andPpIdEqualTo(ppId);
		List<PeopleDrugDiscover> result = peopleDrugDiscoverMapper.selectByExample(example);
		return result;
	}

	@Override
	public int save(PeopleDrugDiscover entity) {
		int result = peopleDrugDiscoverMapper.insert(entity);
		return result;
	}

	@Override
	public int updateByPrimaryKey(PeopleDrugDiscover entity) {
		int result = peopleDrugDiscoverMapper.updateByPrimaryKey(entity);
		return result;
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		int result = peopleDrugDiscoverMapper.deleteByPrimaryKey(id);
		return result;
	}
}
