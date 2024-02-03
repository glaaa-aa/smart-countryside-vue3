package com.qfy.modules.city3d.service;

import java.util.List;

import com.qfy.modules.city3d.po.PeopleKnife;


public interface PeopleKnifeService {
	public List<PeopleKnife> getPeopleKnife();
	public List<PeopleKnife> findByPpId(String ppId);
	public int save(PeopleKnife peopleKnife);
	public int updateByPrimaryKey(PeopleKnife peopleKnife);
	public int deleteByPrimaryKey(String id);
	public int batchDelete(List<String> knifeIds);
}
