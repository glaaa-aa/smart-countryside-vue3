package com.qfy.modules.city3d.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.qfy.modules.city3d.mapper.EplanMapper;
import com.qfy.modules.city3d.po.Eplan;
import com.qfy.modules.city3d.service.EplanService;
import org.springframework.stereotype.Service;

@Service
public class EplanServiceImpl implements EplanService {
	@Autowired
	private EplanMapper eplanMapper;

	@Override
	public List<Eplan> getEplan() {
		List<Eplan> result = eplanMapper.selectByExample(null);
		return result;
	}

	@Override
	public int insert(Eplan eplan) {
		int result = eplanMapper.insert(eplan);
		return result;
	}

	@Override
	public int updateByPrimaryKey(Eplan eplan) {
		int result = eplanMapper.updateByPrimaryKey(eplan);
		return result;
	}

	@Override
	public int deleteByPrimaryKey(String eplanId) {
		int result = eplanMapper.deleteByPrimaryKey(eplanId);
		return result;
	}
}
