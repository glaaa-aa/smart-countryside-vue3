package com.qfy.modules.city3d.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qfy.modules.city3d.po.PoliceLine;
import com.qfy.modules.city3d.service.PoliceLineService;
import com.qfy.modules.city3d.util.CharacterUtils;

@Controller
@RequestMapping("/policeLine")
public class PoliceLineController extends CrossOriginController {
	@Autowired
	private PoliceLineService policeLineService;

	@RequestMapping("/queryPoliceLine")
	public @ResponseBody List<PoliceLine> queryPoliceLine(@RequestBody PoliceLine policeLine) throws Exception{
		List<PoliceLine> result = policeLineService.queryPoliceLine(policeLine);
		return result;
	}

	@RequestMapping("/getPoliceLine")
	public @ResponseBody List<PoliceLine>  getPoliceLine() throws Exception{
		List<PoliceLine> result = policeLineService.getPoliceLine();
		return result;
	}

	@RequestMapping("/insert")
	public @ResponseBody int insert(@RequestBody PoliceLine policeLine) throws Exception{
		CharacterUtils characterUtils = new CharacterUtils();
		String uid = characterUtils.getUUID();
		policeLine.setlId(uid);
		int result = policeLineService.insert(policeLine);
		return result;
	}

	@RequestMapping("/update")
	public @ResponseBody int updateByPrimaryKey(@RequestBody PoliceLine policeLine) throws Exception{
		int result = policeLineService.updateByPrimaryKey(policeLine);
		return result;
	}

	@RequestMapping("/delete")
	public @ResponseBody int deleteByPrimaryKey(@RequestBody PoliceLine policeLine) throws Exception{
		int result = policeLineService.deleteByPrimaryKey(policeLine.getlId());
		return result;
	}

	@RequestMapping("/batchDelete")
	public @ResponseBody int batchDelete(@RequestBody List<PoliceLine> policeLines) throws Exception {
		List<String> lineIds = new ArrayList();
		for(PoliceLine policeLine : policeLines) {
			lineIds.add(policeLine.getlId());
		}
		int result = policeLineService.batchDelete(lineIds);
		return result;
	}
}
