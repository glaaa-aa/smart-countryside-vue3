package com.qfy.modules.city3d.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.qfy.modules.city3d.mapper.PeopleIllegalMapper;
import com.qfy.modules.city3d.po.PeopleIllegal;
import com.qfy.modules.city3d.po.PeopleIllegalExample;
import com.qfy.modules.city3d.service.PeopleIllegalService;
import org.springframework.stereotype.Service;

@Service
public class PeopleIllegalServiceImpl implements PeopleIllegalService {
	@Autowired
	private PeopleIllegalMapper peopleIllegalServiceMapper;

	@Override
	public List<PeopleIllegal> findByPpId(String ppId) {
		PeopleIllegalExample example = new PeopleIllegalExample();
		example.createCriteria().andPpIdEqualTo(ppId);
		List<PeopleIllegal> result = peopleIllegalServiceMapper.selectByExample(example);
		return result;
	}

	@Override
	public int save(PeopleIllegal entity) {
		int result = peopleIllegalServiceMapper.insert(entity);
		return result;
	}

	@Override
	public int updateByPrimaryKey(PeopleIllegal entity) {
		int result = peopleIllegalServiceMapper.updateByPrimaryKey(entity);
		return result;
	}

	@Override
	public int deleteById(String id) {
		int result = peopleIllegalServiceMapper.deleteByPrimaryKey(id);
		return result;
	}
}
