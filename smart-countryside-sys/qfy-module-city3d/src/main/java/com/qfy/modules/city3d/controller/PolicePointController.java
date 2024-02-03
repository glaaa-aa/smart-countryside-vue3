package com.qfy.modules.city3d.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qfy.modules.city3d.po.PolicePoint;
import com.qfy.modules.city3d.service.PolicePointService;
import com.qfy.modules.city3d.util.CharacterUtils;

@Controller
@RequestMapping("/policePoint")
public class PolicePointController extends CrossOriginController {
	@Autowired
	private PolicePointService policePointService;

	@RequestMapping("/queryPolicePoint")
	public @ResponseBody List<PolicePoint> queryPolicePoint(@RequestBody PolicePoint policePoint) throws Exception{
		List<PolicePoint> result = policePointService.queryPolicePoint(policePoint);
		return result;
	}

	@RequestMapping("/getPolicePoint")
	public @ResponseBody List<PolicePoint> getPolicePoint() throws Exception{
		List<PolicePoint> result = policePointService.getPolicePoint();
		return result;
	}

	@RequestMapping("/getPolicePointById")
	public @ResponseBody PolicePoint getPolicePointById(String pId) throws Exception {
		PolicePoint result = policePointService.findPolicePointById(pId);
		return result;
	}

	@RequestMapping("/insert")
	public @ResponseBody int insert(@RequestBody PolicePoint policePoint) throws Exception{
		CharacterUtils characterUtils = new CharacterUtils();
		String uid = characterUtils.getUUID();
		policePoint.setpId(uid);
		int result = policePointService.insert(policePoint);
		return result;
	}

	@RequestMapping("/update")
	public @ResponseBody int updateByPrimaryKey(@RequestBody PolicePoint policePoint) throws Exception{
		int result = policePointService.updateByPrimaryKey(policePoint);
		return result;
	}

	@RequestMapping("/delete")
	public @ResponseBody int deleteByPrimaryKey(@RequestBody PolicePoint policePoint) throws Exception{
		int result = policePointService.deleteByPrimaryKey(policePoint.getpId());
		return result;
	}

	@RequestMapping("/batchDelete")
	public @ResponseBody int batchDelete(@RequestBody List<PolicePoint> policePoints) throws Exception {
		List<String> pIds = new ArrayList();
		for(PolicePoint policePoint : policePoints) {
			pIds.add(policePoint.getpId());
		}
		int result = policePointService.batchDelete(pIds);
		return result;
	}
}
