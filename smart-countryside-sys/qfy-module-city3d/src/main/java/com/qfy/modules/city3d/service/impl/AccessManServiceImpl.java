package com.qfy.modules.city3d.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.qfy.modules.city3d.mapper.AccessManMapperC;
import com.qfy.modules.city3d.po.AccessMan;
import com.qfy.modules.city3d.service.AccessManService;
import org.springframework.stereotype.Service;

@Service
public class AccessManServiceImpl implements AccessManService {
	@Autowired
	private AccessManMapperC accessManMapper;

	@Override
	public List<AccessMan> getAccessMan() {
		List<AccessMan> result = accessManMapper.selectByExample(null);
		return result;
	}

	@Override
	public int deleteByPrimaryKey(String accessManId) {
		int result = accessManMapper.deleteByPrimaryKey(accessManId);
		return result;
	}

	@Override
	public int batchDelete(List<String> accessManIds) {
		int result = accessManMapper.batchDelete(accessManIds);
		return result;
	}
}
