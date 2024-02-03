package com.qfy.modules.city3d.service;

import java.util.List;

import com.qfy.modules.city3d.po.PeopleRelation;


public interface PeopleRelationService {
	List<PeopleRelation> selectPeopleRelationById(String ppId);

	public List<PeopleRelation> findByPpId(String ppId);

	public int save(PeopleRelation peopleRelation);

	public int deleteByPrimaryKey(String id);

}
