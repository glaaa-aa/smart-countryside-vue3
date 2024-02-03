package com.qfy.modules.city3d.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qfy.modules.city3d.po.PeopleRoute;
import com.qfy.modules.city3d.service.RouteService;
import com.qfy.modules.city3d.util.CharacterUtils;

@Controller
@RequestMapping("/route")
public class RouteController extends CrossOriginController {
	@Autowired
	RouteService routeService;

	@RequestMapping("/getRoute")
	public @ResponseBody List<PeopleRoute> getRoute() throws Exception {
		List<PeopleRoute> result = routeService.getPeopleRoute();
		return result;
	}

	@RequestMapping("/getRouteById")
	public @ResponseBody PeopleRoute getCarById(String routeId) throws Exception{
		PeopleRoute result = routeService.getRouteById(routeId);
		return result;
	}

	@RequestMapping("/insert")
	public @ResponseBody int insert(@RequestBody PeopleRoute PeopleRoute) throws Exception{
		CharacterUtils characterUtils = new CharacterUtils();
		String uid = characterUtils.getUUID();
		PeopleRoute.setRouteId(uid);
		int result = routeService.insert(PeopleRoute);
		return result;
	}

	@RequestMapping("/update")
	public @ResponseBody int updateByPrimaryKey(@RequestBody PeopleRoute PeopleRoute) throws Exception{
		int result = routeService.updateByPrimaryKey(PeopleRoute);
		return result;
	}

	@RequestMapping("/delete")
	public @ResponseBody int deleteByPrimaryKey(String routeId) throws Exception{
		int result = routeService.deleteByPrimaryKey(routeId);
		return result;
	}

	@RequestMapping("/iskpBe")
	public @ResponseBody int iskpBe(String kpId) throws Exception{
		int result = routeService.iskpBe(kpId);
		return result;
	}
}
