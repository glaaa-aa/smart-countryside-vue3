package com.qfy.modules.city3d.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qfy.modules.city3d.po.CityThing;
import com.qfy.modules.city3d.service.CityThingService;
import com.qfy.modules.city3d.util.CharacterUtils;

@Controller
@RequestMapping("/cityThing")
public class CityThingController extends CrossOriginController {
	@Autowired
	private CityThingService cityThingService;

	@RequestMapping("/getCityThing")
	public @ResponseBody List<CityThing> getCityThing() throws Exception {
		List<CityThing> result = cityThingService.getCityThing();
		return result;
	}

	@RequestMapping("/getCityThingByPid")
	public @ResponseBody List<CityThing> getCityThingByPid(@RequestBody CityThing cityThing) throws Exception {
		List<CityThing> result = cityThingService.getCityThingByPid(cityThing.getThingPid());
		return result;
	}

	@RequestMapping("/insert")
	public @ResponseBody int insert(@RequestBody CityThing cityThing) throws Exception {
		CharacterUtils characterUtils = new CharacterUtils();
		String uid = characterUtils.getUUID();
		cityThing.setThingId(uid);
		int result = cityThingService.insert(cityThing);
		return result;
	}

	@RequestMapping("/update")
	public @ResponseBody int update(@RequestBody CityThing cityThing) throws Exception {
		int result = cityThingService.updateByPrimaryKey(cityThing);
		return result;
	}

	@RequestMapping("/delete")
	public @ResponseBody int delete(@RequestBody CityThing cityThing) throws Exception {
		int result = cityThingService.deleteByPrimaryKey(cityThing.getThingId());
		return result;
	}

	@RequestMapping("/batchDelete")
	public @ResponseBody int batchDelete(@RequestBody List<CityThing> cityThings) throws Exception {
		List<String> thingIds = new ArrayList();
		for(CityThing cityThing : cityThings) {
			thingIds.add(cityThing.getThingId());
		}
		int result = cityThingService.batchDelete(thingIds);
		return result;
	}
}
