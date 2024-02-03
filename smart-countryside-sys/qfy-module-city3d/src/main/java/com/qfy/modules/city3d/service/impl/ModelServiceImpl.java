package com.qfy.modules.city3d.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.qfy.modules.city3d.mapper.ModelMapper;
import com.qfy.modules.city3d.po.Model;
import com.qfy.modules.city3d.service.ModelService;
import org.springframework.stereotype.Service;

@Service
public class ModelServiceImpl implements ModelService {
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<Model> getModel() {
		List<Model> result = modelMapper.selectByExample(null);
		return result;
	}

	@Override
	public int insert(Model model) {
		int result = modelMapper.insert(model);
		return result;
	}

	@Override
	public int updateByPrimaryKey(Model model) {
		int result = modelMapper.updateByPrimaryKey(model);
		return result;
	}

	@Override
	public int deleteByPrimaryKey(String modelId) {
		int result = modelMapper.deleteByPrimaryKey(modelId);
		return result;
	}

}
