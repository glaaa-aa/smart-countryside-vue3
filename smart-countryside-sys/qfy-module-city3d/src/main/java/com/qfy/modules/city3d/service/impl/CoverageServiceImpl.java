package com.qfy.modules.city3d.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.qfy.modules.city3d.mapper.CoverageMapper;
import com.qfy.modules.city3d.po.Coverage;
import com.qfy.modules.city3d.po.CoverageExample;
import com.qfy.modules.city3d.service.CoverageService;
import org.springframework.stereotype.Service;

@Service
public class CoverageServiceImpl implements CoverageService {
	@Autowired
	private CoverageMapper coverageMapper;

	@Override
	public List<Coverage> getCoverageAll() {
		CoverageExample example = new CoverageExample();
		example.setOrderByClause("name");
		List<Coverage> result = coverageMapper.selectByExample(example);
		return result;
	}

	@Override
	public int insert(Coverage coverage) {
		int result = coverageMapper.insert(coverage);
		return result;
	}

	@Override
	public int updateByPrimaryKey(Coverage coverage) {
		int result = coverageMapper.updateByPrimaryKey(coverage);
		return result;
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		int result = coverageMapper.deleteByPrimaryKey(id);
		return result;
	}
}
