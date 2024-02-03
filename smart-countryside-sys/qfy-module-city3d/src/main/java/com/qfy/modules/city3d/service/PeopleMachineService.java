package com.qfy.modules.city3d.service;

import java.util.List;

import com.qfy.modules.city3d.po.PeopleMachine;

public interface PeopleMachineService {
	public List<PeopleMachine> selectPeopleMachineAll();
	public List<PeopleMachine> selectPeopleMachineById(String ppId);
	public int save(PeopleMachine entity);
	public int updateByPrimaryKey(PeopleMachine entity);
	public int deleteByPrimaryKey(String id);
	public int batchDelete(List<String> machineIds);
}
