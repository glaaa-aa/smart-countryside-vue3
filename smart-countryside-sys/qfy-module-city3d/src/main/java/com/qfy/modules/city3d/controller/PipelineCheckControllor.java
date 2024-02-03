package com.qfy.modules.city3d.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qfy.modules.city3d.po.PipelineCheck;
import com.qfy.modules.city3d.service.PipelineCheckService;
import com.qfy.modules.city3d.util.CharacterUtils;

@Controller
@RequestMapping("/pipelineCheck")
public class PipelineCheckControllor extends CrossOriginController {
	@Autowired
	private PipelineCheckService pipelineCheckService;

	@RequestMapping("/getPipelineCheck")
	public @ResponseBody List<PipelineCheck> getPipelineCheck() throws Exception{
		List<PipelineCheck> result = pipelineCheckService.getPipelineCheck();
		return result;
	}

	@RequestMapping("/insert")
	public @ResponseBody int insert(@RequestBody PipelineCheck pipelineCheck) throws Exception{
		CharacterUtils characterUtils = new CharacterUtils();
		String uid = characterUtils.getUUID();
		pipelineCheck.setCheckId(uid);
		int result = pipelineCheckService.insert(pipelineCheck);
		return result;
	}

	@RequestMapping("/update")
	public @ResponseBody int updateByPrimaryKey(@RequestBody PipelineCheck pipelineCheck) throws Exception{
		int result = pipelineCheckService.updateByPrimaryKey(pipelineCheck);
		return result;
	}

	@RequestMapping("/delete")
	public @ResponseBody int deleteByPrimaryKey(@RequestBody PipelineCheck pipelineCheck) throws Exception{
		int result = pipelineCheckService.deleteByPrimaryKey(pipelineCheck.getCheckId());
		return result;
	}

	@RequestMapping("/getPipelineCheckByAdd")
	public @ResponseBody List<PipelineCheck> getPipelineCheckByAdd(@RequestBody PipelineCheck pipelineCheck) throws Exception{
		List<PipelineCheck> result = pipelineCheckService.getPipelineCheckByAdd(pipelineCheck);
		return result;
	}

	@RequestMapping("/batchDelete")
	public @ResponseBody int batchDelete(@RequestBody List<PipelineCheck> pipelineChecks) throws Exception {
		List<String> checkIds = new ArrayList();
		for(PipelineCheck pipelineCheck : pipelineChecks) {
			checkIds.add(pipelineCheck.getCheckId());
		}
		int result = pipelineCheckService.batchDelete(checkIds);
		return result;
	}
}
