package com.qfy.modules.city3d.service;

import java.util.List;

import com.qfy.modules.city3d.po.Duty;

public interface DutyService {
	public List<Duty> getDuty();
	public Duty getDutyById(String dutyId) throws Exception;
	public int insert(Duty duty);
	public int updateByPrimaryKey(Duty duty);
	public int deleteByPrimaryKey(String dutyId);
}
