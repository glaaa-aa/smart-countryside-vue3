package com.qfy.modules.city3d.service;

import java.util.List;

import com.qfy.modules.city3d.po.Eplan;

public interface EplanService {
	public List<Eplan> getEplan();
	public int insert(Eplan eplan);
	public int updateByPrimaryKey(Eplan eplan);
	public int deleteByPrimaryKey(String eplanId);
}
