package com.qfy.modules.city3d.service;

import java.util.List;

import com.qfy.modules.city3d.po.PeopleDrugDiscover;

public interface DrugDiscoverService {
	public List<PeopleDrugDiscover> findByPpId(String ppId);
	public int save(PeopleDrugDiscover entity);
	public int updateByPrimaryKey(PeopleDrugDiscover entity);
	public int deleteByPrimaryKey(String id);

}
