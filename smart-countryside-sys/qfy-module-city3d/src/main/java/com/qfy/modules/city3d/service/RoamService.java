package com.qfy.modules.city3d.service;

import java.util.List;

import com.qfy.modules.city3d.po.Roam;

public interface RoamService {
	public List<Roam> getRoam();
	public Roam getRoamById(String roamId);
	public int insert(Roam roam);
	public int updateByPrimaryKey(Roam roam);
	public int deleteByPrimaryKey(String roamId);
	public int batchDelete(List<String> roamIds);
}
