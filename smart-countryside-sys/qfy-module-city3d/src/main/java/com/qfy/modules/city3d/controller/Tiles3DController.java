package com.qfy.modules.city3d.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qfy.modules.city3d.po.Tiles3d;
import com.qfy.modules.city3d.service.Tiles3DService;
import com.qfy.modules.city3d.util.CharacterUtils;

@Controller
@RequestMapping("/tiles3d")
public class Tiles3DController extends CrossOriginController {
	@Autowired
	private Tiles3DService tiles3DService;

	@RequestMapping("/getTiles3D")
	public @ResponseBody List<Tiles3d> getTiles3D(@RequestBody Tiles3d tiles3d) throws Exception {
		List<Tiles3d> result = tiles3DService.getTiles3d();
		return result;
	}

	@RequestMapping("/getTiles3DById")
	public @ResponseBody Tiles3d getTiles3DById(String tilesId) throws Exception {
		Tiles3d result = tiles3DService.getTiles3DById(tilesId);
		return result;
	}

	@RequestMapping("/insert")
	public @ResponseBody int insert(@RequestBody Tiles3d tiles3d) throws Exception {
		CharacterUtils characterUtils = new CharacterUtils();
		String uid = characterUtils.getUUID();
		tiles3d.setTilesId(uid);
		int result = tiles3DService.insert(tiles3d);
		return result;
	}

	@RequestMapping("/update")
	public @ResponseBody int updateByPrimaryKey(@RequestBody Tiles3d tiles3d) throws Exception {
		int result = tiles3DService.updateByPrimaryKey(tiles3d);
		return result;
	}

	@RequestMapping("/delete")
	public @ResponseBody int deleteByPrimaryKey(@RequestBody Tiles3d tiles3d) throws Exception {
		int result = tiles3DService.deleteByPrimaryKey(tiles3d.getTilesId());
		return result;
	}
}
