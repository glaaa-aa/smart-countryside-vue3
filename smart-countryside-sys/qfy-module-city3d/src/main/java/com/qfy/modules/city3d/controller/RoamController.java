package com.qfy.modules.city3d.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qfy.modules.city3d.po.Roam;
import com.qfy.modules.city3d.service.RoamService;
import com.qfy.modules.city3d.util.CharacterUtils;

@Controller
@RequestMapping("/roam")
public class RoamController extends CrossOriginController {
	@Autowired
	private RoamService roamService;

	@RequestMapping("/getRoam")
	public @ResponseBody List<Roam> getRoam() throws Exception{
		List<Roam> result = roamService.getRoam();
		return result;
	}

	@RequestMapping("/getRoamById")
	public @ResponseBody Roam getRoamById(String roamId) throws Exception{
		Roam result = roamService.getRoamById(roamId);
		return result;
	}

	@RequestMapping("/insert")
	public @ResponseBody int insert(@RequestBody Roam roam) throws Exception{
		CharacterUtils characterUtils = new CharacterUtils();
		String uid = characterUtils.getUUID();
		roam.setRoamId(uid);
		int result = roamService.insert(roam);
		return result;
	}

	@RequestMapping("/update")
	public @ResponseBody int updateByPrimaryKey(@RequestBody Roam roam) throws Exception{
		int result = roamService.updateByPrimaryKey(roam);
		return result;
	}

	@RequestMapping("/delete")
	public @ResponseBody int deleteByPrimaryKey(@RequestBody Roam roam) throws Exception{
		int result = roamService.deleteByPrimaryKey(roam.getRoamId());
		return result;
	}

	@RequestMapping("/batchDelete")
	public @ResponseBody int batchDelete(@RequestBody List<Roam> roams) throws Exception {
		List<String> roamIds = new ArrayList();
		for(Roam roam : roams) {
			roamIds.add(roam.getRoamId());
		}
		int result = roamService.batchDelete(roamIds);
		return result;
	}
}
