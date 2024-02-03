package com.qfy.modules.city3d.service;

import java.util.List;

import com.qfy.modules.city3d.po.Pipeline;

public interface PipelineService {
	public List<Pipeline> getPipeline();
	public int insert(Pipeline pipeline);
	public int updateByPrimaryKey(Pipeline pipeline);
	public int deleteByPrimaryKey(String pipeId);
	public Pipeline getPipelineById(String pipeId);
	public List<Pipeline> getPipelineByPlName(Pipeline pipeline);
	public int batchDelete(List<String> pipeIds);
}
