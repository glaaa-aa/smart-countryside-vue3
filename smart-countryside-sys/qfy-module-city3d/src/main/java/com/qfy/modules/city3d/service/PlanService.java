package com.qfy.modules.city3d.service;

import java.util.List;

import com.qfy.modules.city3d.po.Plan;

public interface PlanService {
	public List<Plan> getPlan();
	public int deleteByPrimaryKey(String planId);
	public int insert(Plan plan);
}
