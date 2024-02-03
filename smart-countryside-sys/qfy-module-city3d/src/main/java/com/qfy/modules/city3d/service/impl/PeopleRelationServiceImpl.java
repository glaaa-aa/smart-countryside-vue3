package com.qfy.modules.city3d.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.qfy.modules.city3d.mapper.PeopleRelationMapperC;
import com.qfy.modules.city3d.po.PeopleRelation;
import com.qfy.modules.city3d.po.PeopleRelationExample;
import com.qfy.modules.city3d.service.PeopleRelationService;
import org.springframework.stereotype.Service;

@Service
public class PeopleRelationServiceImpl implements PeopleRelationService {
	@Autowired
	private PeopleRelationMapperC peopleRelationMapperC;

	@Override
	public List<PeopleRelation> selectPeopleRelationById(String ppId) {
		List<PeopleRelation> result = peopleRelationMapperC.selectPeopleRelationById(ppId);
		return result;
	}

	@Override
	public List<PeopleRelation> findByPpId(String ppId) {
		PeopleRelationExample example = new PeopleRelationExample();
		example.createCriteria().andPpIdEqualTo(ppId);
		List<PeopleRelation> result = peopleRelationMapperC.selectByExample(example);
		return result;
	}

	@Override
	public int save(PeopleRelation peopleRelation) {
		int result = peopleRelationMapperC.insert(peopleRelation);
		return result;
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		int result = peopleRelationMapperC.deleteByPrimaryKey(id);
		return result;
	}
}
