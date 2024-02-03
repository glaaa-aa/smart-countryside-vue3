package com.qfy.modules.city3d.service;

import com.qfy.modules.city3d.po.Alarm;
import java.util.List;

public interface AlarmService {
	public List<Alarm> getAlarm();
	public int insert(Alarm alarm);
	public int updateByPrimaryKey(Alarm alarm);
	public int deleteByPrimaryKey(String alarmId);
	public int batchDelete(List<String> alarmIds);
}
