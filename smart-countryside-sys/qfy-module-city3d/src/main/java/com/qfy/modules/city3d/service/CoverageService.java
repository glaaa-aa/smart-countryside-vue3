package com.qfy.modules.city3d.service;

import java.util.List;

import com.qfy.modules.city3d.po.Coverage;

public interface CoverageService {
	public List<Coverage> getCoverageAll();
	public int insert(Coverage coverage);
	public int updateByPrimaryKey(Coverage coverage);
	public int deleteByPrimaryKey(String id);
}
