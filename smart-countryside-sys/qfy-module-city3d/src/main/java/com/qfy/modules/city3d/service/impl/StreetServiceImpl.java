package com.qfy.modules.city3d.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.qfy.modules.city3d.mapper.StreetMapperC;
import com.qfy.modules.city3d.po.Street;
import com.qfy.modules.city3d.po.StreetExample;
import com.qfy.modules.city3d.service.StreetService;
import org.springframework.stereotype.Service;

@Service
public class StreetServiceImpl implements StreetService {

	@Autowired
	private StreetMapperC streetMapper;

	@Override
	public List<Street> getStreetAll() {
		StreetExample example = new StreetExample();
		List<Street> result = streetMapper.selectByExample(example);
		return result;
	}

	@Override
	public int insert(Street street) {
		int result = streetMapper.insert(street);
		return result;
	}

	@Override
	public int updateByPrimaryKey(Street street) {
		int result = streetMapper.updateByPrimaryKey(street);
		return result;
	}

	@Override
	public int deleteByPrimaryKey(String streetId) {
		int result = streetMapper.deleteByPrimaryKey(streetId);
		return result;
	}

	@Override
	public int batchDelete(List<String> streetIds) {
		int result = streetMapper.batchDelete(streetIds);
		return result;
	}
}
