package com.qfy.modules.city3d.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.qfy.modules.city3d.mapper.PlanMapper;
import com.qfy.modules.city3d.po.Plan;
import com.qfy.modules.city3d.service.PlanService;
import org.springframework.stereotype.Service;

@Service
public class PlanServiceImpl implements PlanService {
	@Autowired
	private PlanMapper planMapper;

	@Override
	public List<Plan> getPlan() {
		List<Plan> result = planMapper.selectByExample(null);
		return result;
	}

	@Override
	public int deleteByPrimaryKey(String planId) {
		int result = planMapper.deleteByPrimaryKey(planId);
		return result;
	}

	@Override
	public int insert(Plan plan) {
		int result = planMapper.insert(plan);
		return result;
	}
}
