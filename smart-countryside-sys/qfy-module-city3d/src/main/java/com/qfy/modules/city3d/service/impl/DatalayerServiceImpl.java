package com.qfy.modules.city3d.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.qfy.modules.city3d.mapper.DatalayerMapper;
import com.qfy.modules.city3d.po.Datalayer;
import com.qfy.modules.city3d.service.DatalayerService;
import org.springframework.stereotype.Service;

@Service
public class DatalayerServiceImpl implements DatalayerService {
	@Autowired
	DatalayerMapper datalayerMapper;

	@Override
	public List<Datalayer> getDatalayer() {
		List<Datalayer> result = datalayerMapper.selectByExample(null);
		return result;
	}

	@Override
	public int insert(Datalayer datalayer) {
		int result = datalayerMapper.insert(datalayer);
		return result;
	}

	@Override
	public int updateByPrimaryKey(Datalayer datalayer) {
		int result = datalayerMapper.updateByPrimaryKey(datalayer);
		return result;
	}

	@Override
	public int deleteByPrimaryKey(String layerId) {
		int result = datalayerMapper.deleteByPrimaryKey(layerId);
		return result;
	}
}
