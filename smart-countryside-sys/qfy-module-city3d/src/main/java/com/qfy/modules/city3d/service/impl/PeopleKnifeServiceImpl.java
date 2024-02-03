package com.qfy.modules.city3d.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.qfy.modules.city3d.mapper.PeopleKnifeMapperC;
import com.qfy.modules.city3d.po.PeopleKnife;
import com.qfy.modules.city3d.po.PeopleKnifeExample;
import com.qfy.modules.city3d.service.PeopleKnifeService;
import org.springframework.stereotype.Service;

@Service
public class PeopleKnifeServiceImpl implements PeopleKnifeService {
	@Autowired
	private PeopleKnifeMapperC peopleKnifeMapper;

	@Override
	public List<PeopleKnife> getPeopleKnife() {
		List<PeopleKnife> result = peopleKnifeMapper.selectPeopleKnifeAll();
		return result;
	}

	@Override
	public List<PeopleKnife> findByPpId(String ppId) {
		PeopleKnifeExample example = new PeopleKnifeExample();
		example.createCriteria().andPpIdEqualTo(ppId);
		List<PeopleKnife> result = peopleKnifeMapper.selectByExample(example);
		return result;
	}

	@Override
	public int save(PeopleKnife peopleKnife) {
		int result = peopleKnifeMapper.insert(peopleKnife);
		return result;
	}

	@Override
	public int updateByPrimaryKey(PeopleKnife peopleKnife) {
		int result = peopleKnifeMapper.updateByPrimaryKey(peopleKnife);
		return result;
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		int result = peopleKnifeMapper.deleteByPrimaryKey(id);
		return result;
	}

	@Override
	public int batchDelete(List<String> knifeIds) {
		int result = peopleKnifeMapper.batchDelete(knifeIds);
		return result;
	}
}
