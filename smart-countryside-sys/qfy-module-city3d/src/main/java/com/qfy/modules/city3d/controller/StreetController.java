package com.qfy.modules.city3d.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qfy.modules.city3d.po.Street;
import com.qfy.modules.city3d.service.StreetService;
import com.qfy.modules.city3d.util.CharacterUtils;

@Controller
@RequestMapping("/street")
public class StreetController extends CrossOriginController {
	@Autowired
	private StreetService streetService;

	@RequestMapping("/getStreetAll")
	public @ResponseBody List<Street> getStreetAll() throws Exception {
		List<Street> result = streetService.getStreetAll();
		return result;
	}

	@RequestMapping("/insert")
	public @ResponseBody int insert(@RequestBody Street street) throws Exception {
		CharacterUtils characterUtils = new CharacterUtils();
		String uid = characterUtils.getUUID();
		street.setStreetId(uid);
		int result = streetService.insert(street);
		return result;
	}

	@RequestMapping("/update")
	public @ResponseBody int updateByPrimaryKey(@RequestBody Street street) throws Exception {
		int result = streetService.updateByPrimaryKey(street);
		return result;
	}

	@RequestMapping("/delete")
	public @ResponseBody int deleteByPrimaryKey(@RequestBody Street street) throws Exception {
		int result = streetService.deleteByPrimaryKey(street.getStreetId());
		return result;
	}

	@RequestMapping("/batchDelete")
	public @ResponseBody int batchDelete(@RequestBody List<Street> streets) throws Exception {
		List<String> streetIds = new ArrayList();
		for(Street street : streets) {
			streetIds.add(street.getStreetId());
		}
		int result = streetService.batchDelete(streetIds);
		return result;
	}
}
