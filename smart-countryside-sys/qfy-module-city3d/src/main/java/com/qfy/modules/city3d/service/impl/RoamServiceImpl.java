package com.qfy.modules.city3d.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.qfy.modules.city3d.mapper.RoamMapperC;
import com.qfy.modules.city3d.po.Roam;
import com.qfy.modules.city3d.service.RoamService;
import org.springframework.stereotype.Service;

@Service
public class RoamServiceImpl implements RoamService {
	@Autowired
	private RoamMapperC roamMapper;

	@Override
	public List<Roam> getRoam() {
		List<Roam> result = roamMapper.selectByExample(null);
		return result;
	}

	@Override
	public Roam getRoamById(String roamId) {
		Roam result = roamMapper.selectByPrimaryKey(roamId);
		return result;
	}

	@Override
	public int insert(Roam roam) {
		int result = roamMapper.insert(roam);
		return result;
	}

	@Override
	public int updateByPrimaryKey(Roam roam) {
		int result = roamMapper.updateByPrimaryKey(roam);
		return result;
	}

	@Override
	public int deleteByPrimaryKey(String roamId) {
		int result = roamMapper.deleteByPrimaryKey(roamId);
		return result;
	}

	@Override
	public int batchDelete(List<String> roamIds) {
		int result = roamMapper.batchDelete(roamIds);
		return result;
	}
}
