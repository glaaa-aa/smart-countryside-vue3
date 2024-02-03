package com.qfy.modules.city3d.service;

import java.util.List;

import com.qfy.modules.city3d.po.PeopleBase;

public interface PeopleBaseService {
	public List<PeopleBase> getPeopleBaseAll();
	public List<PeopleBase> queryPeoplebasebyExample(PeopleBase peopleBase);
	public List<PeopleBase> queryPeoplebasebyPartExample(PeopleBase peopleBase);
	public PeopleBase getPeopleBaseById(String ppId);
	public List<PeopleBase> queryPeoplebaseByPolygon(PeopleBase peopleBase);
	public int updateByPrimaryKey(PeopleBase peopleBase);
	public int insert(PeopleBase peopleBase);
	public int deleteByPrimaryKey(String ppId);
	public List<PeopleBase> queryPeopleByAddId(String addId);
	public int batchDelete(List<String> ppIds);
}
