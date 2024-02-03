package com.qfy.modules.city3d.service;

import java.util.List;
import com.qfy.modules.city3d.po.PeopleIllegal;

public interface PeopleIllegalService {
	public List<PeopleIllegal> findByPpId(String ppId);
	public int save(PeopleIllegal entity);
	public int updateByPrimaryKey(PeopleIllegal entity);
	public int deleteById(String id);
}
