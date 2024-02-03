package com.qfy.modules.city3d.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.qfy.modules.city3d.mapper.PeopleMachineMapperC;
import com.qfy.modules.city3d.po.PeopleMachine;
import com.qfy.modules.city3d.service.PeopleMachineService;
import org.springframework.stereotype.Service;

@Service
public class PeopleMachineServiceImpl implements PeopleMachineService {
	@Autowired
	private PeopleMachineMapperC peopleMachineMapper;

	@Override
	public List<PeopleMachine> selectPeopleMachineAll() {
		List<PeopleMachine> result = peopleMachineMapper.selectPeopleMachineAll();
		return result;
	}

	@Override
	public List<PeopleMachine> selectPeopleMachineById(String ppId) {
		List<PeopleMachine> result = peopleMachineMapper.selectPeopleMachineById(ppId);
		return result;
	}

	@Override
	public int updateByPrimaryKey(PeopleMachine entity) {
		int result = peopleMachineMapper.updateByPrimaryKey(entity);
		return result;
	}

	@Override
	public int save(PeopleMachine entity) {
		int result = peopleMachineMapper.insert(entity);
		return result;
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		int result = peopleMachineMapper.deleteByPrimaryKey(id);
		return result;
	}

	@Override
	public int batchDelete(List<String> machineIds) {
		int result = peopleMachineMapper.batchDelete(machineIds);
		return result;
	}
}
