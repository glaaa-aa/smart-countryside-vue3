package com.qfy.modules.city3d.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.qfy.modules.city3d.mapper.AccessCarMapperC;
import com.qfy.modules.city3d.po.AccessCar;
import com.qfy.modules.city3d.service.AccessCarService;
import org.springframework.stereotype.Service;

@Service
public class AccessCarServiceImpl implements AccessCarService {
	@Autowired
	private AccessCarMapperC accessCarMapper;

	@Override
	public List<AccessCar> getAccessCar() {
		List<AccessCar> result = accessCarMapper.selectByExample(null);
		return result;
	}

	@Override
	public int deleteByPrimaryKey(String accessCarId) {
		int result = accessCarMapper.deleteByPrimaryKey(accessCarId);
		return result;
	}

	@Override
	public int batchDelete(List<String> accessCarIds) {
		int result = accessCarMapper.batchDelete(accessCarIds);
		return result;
	}
}
