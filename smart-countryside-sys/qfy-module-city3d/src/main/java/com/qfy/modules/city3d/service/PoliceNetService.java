package com.qfy.modules.city3d.service;

import java.util.List;
import com.qfy.modules.city3d.po.PoliceNet;

public interface PoliceNetService {
	public List<PoliceNet> getPoliceNet();
	public List<PoliceNet> queryPoliceNet(PoliceNet policeNet);
	public int insert(PoliceNet policeNet);
	public int updateByPrimaryKey(PoliceNet policeNet);
	public int deleteByPrimaryKey(String netId);
	public int batchDelete(List<String> netIds);
}
