package com.qfy.modules.city3d.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qfy.modules.city3d.po.Duty;
import com.qfy.modules.city3d.service.DutyService;
import com.qfy.modules.city3d.util.CharacterUtils;

@Controller
@RequestMapping("/duty")
public class DutyController extends CrossOriginController {
	@Autowired
	private DutyService dutyService;

	@RequestMapping("/getDutyInfoData")
	public @ResponseBody List<Duty> getDutyInfoData() throws Exception{
		List<Duty> result = dutyService.getDuty();
		return result;
	}

	@RequestMapping("/getDutyById")
	public @ResponseBody Duty getDutyById(String dutyId) throws Exception{
		Duty result = dutyService.getDutyById(dutyId);
		return result;
	}

	@RequestMapping("/insert")
	public @ResponseBody int insert(@RequestBody Duty duty) throws Exception{
		CharacterUtils characterUtils = new CharacterUtils();
		String uid = characterUtils.getUUID();
		duty.setDutyId(uid);
		int result = dutyService.insert(duty);
		return result;
	}

	@RequestMapping("/update")
	public @ResponseBody int updateByPrimaryKey(@RequestBody Duty duty) throws Exception{
		int result = dutyService.updateByPrimaryKey(duty);
		return result;
	}

	@RequestMapping("/delete")
	public @ResponseBody int deleteByPrimaryKey(String dutyId) throws Exception{
		int result = dutyService.deleteByPrimaryKey(dutyId);
		return result;
	}
}
