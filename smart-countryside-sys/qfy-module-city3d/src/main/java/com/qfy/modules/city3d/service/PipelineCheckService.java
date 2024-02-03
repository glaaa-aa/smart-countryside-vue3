package com.qfy.modules.city3d.service;

import java.util.List;

import com.qfy.modules.city3d.po.PipelineCheck;

public interface PipelineCheckService {
	public List<PipelineCheck> getPipelineCheck();
	public int insert(PipelineCheck pipelineCheck);
	public int updateByPrimaryKey(PipelineCheck pipelineCheck);
	public int deleteByPrimaryKey(String checkId);
	public List<PipelineCheck> getPipelineCheckByAdd(PipelineCheck pipelineCheck);
	public int batchDelete(List<String> checkIds);
}
