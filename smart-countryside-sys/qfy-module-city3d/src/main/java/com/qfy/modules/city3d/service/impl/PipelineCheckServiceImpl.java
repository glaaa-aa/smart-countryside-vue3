package com.qfy.modules.city3d.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.qfy.modules.city3d.mapper.PipelineCheckMapperC;
import com.qfy.modules.city3d.po.PipelineCheck;
import com.qfy.modules.city3d.po.PipelineCheckExample;
import com.qfy.modules.city3d.service.PipelineCheckService;
import org.springframework.stereotype.Service;

@Service
public class PipelineCheckServiceImpl implements PipelineCheckService {
	@Autowired
	private PipelineCheckMapperC pipelineCheckMapper;

	@Override
	public List<PipelineCheck> getPipelineCheck() {
		List<PipelineCheck> result = pipelineCheckMapper.selectByExample(null);
		return result;
	}

	@Override
	public int insert(PipelineCheck pipelineCheck) {
		int result = pipelineCheckMapper.insert(pipelineCheck);
		return result;
	}

	@Override
	public int updateByPrimaryKey(PipelineCheck pipelineCheck) {
		int result = pipelineCheckMapper.updateByPrimaryKey(pipelineCheck);
		return result;
	}

	@Override
	public int deleteByPrimaryKey(String checkId) {
		int result = pipelineCheckMapper.deleteByPrimaryKey(checkId);
		return result;
	}

	@Override
	public List<PipelineCheck> getPipelineCheckByAdd(PipelineCheck pipelineCheck) {
		PipelineCheckExample example = new PipelineCheckExample();
		example.or().andCheckAddEqualTo(pipelineCheck.getCheckAdd());
		List<PipelineCheck> result = pipelineCheckMapper.selectByExample(example);
		return result;
	}

	@Override
	public int batchDelete(List<String> checkIds) {
		int result = pipelineCheckMapper.batchDelete(checkIds);
		return result;
	}
}
