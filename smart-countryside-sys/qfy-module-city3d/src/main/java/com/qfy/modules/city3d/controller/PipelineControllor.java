package com.qfy.modules.city3d.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qfy.modules.city3d.po.Pipeline;
import com.qfy.modules.city3d.service.PipelineService;
import com.qfy.modules.city3d.util.CharacterUtils;

@Controller
@RequestMapping("/pipeline")
public class PipelineControllor extends CrossOriginController {
	@Autowired
	private PipelineService pipelineService;

	@RequestMapping("/getPipeline")
	public @ResponseBody List<Pipeline> getPipeline() throws Exception{
		List<Pipeline> result = pipelineService.getPipeline();
		return result;
	}

	@RequestMapping("/insert")
	public @ResponseBody int insert(@RequestBody Pipeline pipeline) throws Exception{
		CharacterUtils characterUtils = new CharacterUtils();
		String uid = characterUtils.getUUID();
		pipeline.setPipeId(uid);
		int result = pipelineService.insert(pipeline);
		return result;
	}

	@RequestMapping("/update")
	public @ResponseBody int updateByPrimaryKey(@RequestBody Pipeline pipeline) throws Exception{
		int result = pipelineService.updateByPrimaryKey(pipeline);
		return result;
	}

	@RequestMapping("/delete")
	public @ResponseBody int deleteByPrimaryKey(@RequestBody Pipeline pipeline) throws Exception{
		int result = pipelineService.deleteByPrimaryKey(pipeline.getPipeId());
		return result;
	}

	@RequestMapping("/getPipelineById")
	public @ResponseBody Pipeline getPipelineById(@RequestBody Pipeline pipeline) throws Exception{
		Pipeline result = pipelineService.getPipelineById(pipeline.getPipeId());
		return result;
	}

	@RequestMapping("/getPipelineByPlName")
	public @ResponseBody List<Pipeline> getPipelineByPlName(@RequestBody Pipeline pipeline) throws Exception{
		List<Pipeline> result = pipelineService.getPipelineByPlName(pipeline);
		return result;
	}

	@RequestMapping("/batchDelete")
	public @ResponseBody int batchDelete(@RequestBody List<Pipeline> pipelines) throws Exception {
		List<String> pipeIds = new ArrayList();
		for(Pipeline pipeline : pipelines) {
			pipeIds.add(pipeline.getPipeId());
		}
		int result = pipelineService.batchDelete(pipeIds);
		return result;
	}
}
