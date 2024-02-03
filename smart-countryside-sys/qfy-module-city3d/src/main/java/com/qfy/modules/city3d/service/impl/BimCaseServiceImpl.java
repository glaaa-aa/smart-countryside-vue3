package com.qfy.modules.city3d.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.qfy.modules.city3d.mapper.BimCaseMapper;
import com.qfy.modules.city3d.po.BimCase;
import com.qfy.modules.city3d.service.BimCaseService;
import org.springframework.stereotype.Service;

@Service
public class BimCaseServiceImpl implements BimCaseService {
	@Autowired
	private BimCaseMapper bimCaseMapper;

	@Override
	public List<BimCase> getBimCase() {
		List<BimCase> result = bimCaseMapper.selectByExample(null);
		return result;
	}

	@Override
	public int insert(BimCase bimCase) {
		int result = bimCaseMapper.insert(bimCase);
		return result;
	}

	@Override
	public int updateByPrimaryKey(BimCase bimCase) {
		int result = bimCaseMapper.updateByPrimaryKey(bimCase);
		return result;
	}

	@Override
	public int deleteByPrimaryKey(String bimCaseId) {
		int result = bimCaseMapper.deleteByPrimaryKey(bimCaseId);
		return result;
	}

}
