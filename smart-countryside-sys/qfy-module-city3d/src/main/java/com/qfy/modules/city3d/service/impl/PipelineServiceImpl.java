package com.qfy.modules.city3d.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.qfy.modules.city3d.mapper.PipelineMapperC;
import com.qfy.modules.city3d.po.Pipeline;
import com.qfy.modules.city3d.po.PipelineExample;
import com.qfy.modules.city3d.service.PipelineService;
import org.springframework.stereotype.Service;

@Service
public class PipelineServiceImpl implements PipelineService {
	@Autowired
	private PipelineMapperC pipelineMapper;

	@Override
	public List<Pipeline> getPipeline() {
		List<Pipeline> result = pipelineMapper.selectByExample(null);
		return result;
	}

	@Override
	public int insert(Pipeline pipeline) {
		int result = pipelineMapper.insert(pipeline);
		return result;
	}

	@Override
	public int updateByPrimaryKey(Pipeline pipeline) {
		int result = pipelineMapper.updateByPrimaryKey(pipeline);
		return result;
	}

	@Override
	public int deleteByPrimaryKey(String pipeId) {
		int result = pipelineMapper.deleteByPrimaryKey(pipeId);
		return result;
	}

	@Override
	public Pipeline getPipelineById(String pipeId) {
		Pipeline result = pipelineMapper.selectByPrimaryKey(pipeId);
		return result;
	}

	@Override
	public List<Pipeline> getPipelineByPlName(Pipeline pipeline) {
		PipelineExample example = new PipelineExample();
		example.or().andPlNameEqualTo(pipeline.getPlName());
		List<Pipeline> result = pipelineMapper.selectByExample(example);
		return result;
	}

	@Override
	public int batchDelete(List<String> pipeIds) {
		int result = pipelineMapper.batchDelete(pipeIds);
		return result;
	}
}
