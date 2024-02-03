package com.qfy.modules.city3d.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.qfy.modules.city3d.mapper.AlarmMapperC;
import com.qfy.modules.city3d.po.Alarm;
import com.qfy.modules.city3d.service.AlarmService;
import org.springframework.stereotype.Service;

@Service
public class AlarmServiceImpl implements AlarmService {
	@Autowired
	private AlarmMapperC alarmMapper;

	@Override
	public List<Alarm> getAlarm() {
		List<Alarm> result = alarmMapper.selectAllByExample(null);
		return result;
	}

	@Override
	public int insert(Alarm alarm) {
		int result = alarmMapper.insert(alarm);
		return result;
	}

	@Override
	public int updateByPrimaryKey(Alarm alarm) {
		int result = alarmMapper.updateByPrimaryKey(alarm);
		return result;
	}

	@Override
	public int deleteByPrimaryKey(String alarmId) {
		int result = alarmMapper.deleteByPrimaryKey(alarmId);
		return result;
	}

	@Override
	public int batchDelete(List<String> alarmIds) {
		int result = alarmMapper.batchDelete(alarmIds);
		return result;
	}
}
