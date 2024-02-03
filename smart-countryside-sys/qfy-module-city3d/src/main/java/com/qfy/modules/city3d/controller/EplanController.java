package com.qfy.modules.city3d.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qfy.modules.city3d.po.Eplan;
import com.qfy.modules.city3d.service.EplanService;
import com.qfy.modules.city3d.util.CharacterUtils;

@Controller
@RequestMapping("/eplan")
public class EplanController extends CrossOriginController {
	@Autowired
	private EplanService eplanService;

	@RequestMapping("/getEplan")
	public @ResponseBody List<Eplan> getEplan() throws Exception {
		List<Eplan> result = eplanService.getEplan();
		return result;
	}

	@RequestMapping("/insert")
	public @ResponseBody int insert(@RequestBody Eplan eplan) throws Exception{
		CharacterUtils characterUtils = new CharacterUtils();
		String uid = characterUtils.getUUID();
		eplan.setEplanId(uid);
		int result = eplanService.insert(eplan);
		return result;
	}

	@RequestMapping("/update")
	public @ResponseBody int updateByPrimaryKey(@RequestBody Eplan eplan) throws Exception{
		int result = eplanService.updateByPrimaryKey(eplan);
		return result;
	}

	@RequestMapping("/delete")
	public @ResponseBody int deleteByPrimaryKey(@RequestBody Eplan eplan) throws Exception {
		int result = eplanService.deleteByPrimaryKey(eplan.getEplanId());
		return result;
	}
}
