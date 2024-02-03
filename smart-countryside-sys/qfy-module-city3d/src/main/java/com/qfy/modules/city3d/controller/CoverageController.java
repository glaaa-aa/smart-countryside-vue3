package com.qfy.modules.city3d.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qfy.modules.city3d.po.Coverage;
import com.qfy.modules.city3d.service.CoverageService;
import com.qfy.modules.city3d.util.CharacterUtils;

@Controller
@RequestMapping("/coverage")
public class CoverageController extends CrossOriginController {
	@Autowired
	private CoverageService coverageService;

	@RequestMapping("/getCoverageAll")
	public @ResponseBody List<Coverage> getCoverageAll() throws Exception {
		List<Coverage> result = coverageService.getCoverageAll();
		return result;
	}

	@RequestMapping("/insert")
	public @ResponseBody int insert(@RequestBody Coverage coverage) throws Exception {
		CharacterUtils characterUtils = new CharacterUtils();
		String uid = characterUtils.getUUID();
		coverage.setId(uid);
		int result = coverageService.insert(coverage);
		return result;
	}

	@RequestMapping("/update")
	public @ResponseBody int update(@RequestBody Coverage coverage) throws Exception {
		int result = coverageService.updateByPrimaryKey(coverage);
		return result;
	}

	@RequestMapping("/delete")
	public @ResponseBody int delete(@RequestBody Coverage coverage) throws Exception {
		int result = coverageService.deleteByPrimaryKey(coverage.getId());
		return result;
	}
}
