package com.qfy.modules.city3d.service;

import java.util.List;

import com.qfy.modules.city3d.po.PoliceLine;

public interface PoliceLineService {
	public List<PoliceLine> queryPoliceLine(PoliceLine policeLine);
	public List<PoliceLine> getPoliceLine();
	public int insert(PoliceLine policeLine);
	public int updateByPrimaryKey(PoliceLine policeLine);
	public int deleteByPrimaryKey(String lineId);
	public int batchDelete(List<String> lineIds);
}
