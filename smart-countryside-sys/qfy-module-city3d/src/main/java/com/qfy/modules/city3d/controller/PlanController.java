package com.qfy.modules.city3d.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qfy.modules.city3d.po.Plan;
import com.qfy.modules.city3d.service.PlanService;
import com.qfy.modules.city3d.util.CharacterUtils;

@Controller
@RequestMapping("/plan")
public class PlanController extends CrossOriginController {
	@Autowired
	private PlanService planService;

	@RequestMapping("/getPlan")
	public @ResponseBody List<Plan> getPlan() throws Exception {
		List<Plan> result = planService.getPlan();
		return result;
	}

	@RequestMapping("/delete")
	public @ResponseBody int delete(@RequestBody Plan plan) throws Exception {
		int result = planService.deleteByPrimaryKey(plan.getPlanId());
		return result;
	}

	@RequestMapping("/insert")
	public @ResponseBody int insert(@RequestBody Plan plan) throws Exception {
		CharacterUtils characterUtils = new CharacterUtils();
		String uid = characterUtils.getUUID();
		plan.setPlanId(uid);
		int result = planService.insert(plan);
		return result;
	}
}
