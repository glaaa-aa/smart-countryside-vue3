package com.qfy.modules.city3d.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.qfy.modules.city3d.mapper.DutyMapper;
import com.qfy.modules.city3d.po.Duty;
import com.qfy.modules.city3d.service.DutyService;
import org.springframework.stereotype.Service;

@Service
public class DutyServiceImpl implements DutyService {
	@Autowired
	private DutyMapper dutyMapper;

	@Override
	public List<Duty> getDuty() {
		List<Duty> result = dutyMapper.selectByExample(null);
		return result;
	}
	@Override
	public Duty getDutyById(String dutyId) throws Exception {
		Duty result = dutyMapper.selectByPrimaryKey(dutyId);
		return result;
	}
	@Override
	public int insert(Duty duty) {
		int result = dutyMapper.insert(duty);
		return result;
	}

	@Override
	public int updateByPrimaryKey(Duty duty) {
		int result = dutyMapper.updateByPrimaryKey(duty);
		return result;
	}

	@Override
	public int deleteByPrimaryKey(String dutyId) {
		int result = dutyMapper.deleteByPrimaryKey(dutyId);
		return result;
	}
}
