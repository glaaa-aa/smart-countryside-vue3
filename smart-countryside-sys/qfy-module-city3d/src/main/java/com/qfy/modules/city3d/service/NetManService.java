package com.qfy.modules.city3d.service;

import java.util.List;

import com.qfy.modules.city3d.po.NetMan;

public interface NetManService {
	public List<NetMan> getNetMan();
	public NetMan getNetManById(String netManId);
	public List<NetMan> getNetManByType(String type);
	public int insert(NetMan netMan);
	public int updateByPrimaryKey(NetMan netMan);
	public int deleteByPrimaryKey(String netManId);
	public int batchDelete(List<String> netManIds);
}
