package com.qfy.modules.city3d.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qfy.modules.city3d.po.Wall;
import com.qfy.modules.city3d.service.WallService;
import com.qfy.modules.city3d.util.CharacterUtils;

@Controller
@RequestMapping("/wall")
public class WallController extends CrossOriginController {
	@Autowired
	private WallService wallService;

	@RequestMapping("/getWall")
	public @ResponseBody List<Wall> getWall() throws Exception {
		List<Wall> result = wallService.getWall();
		return result;
	}

	@RequestMapping("/getWallById")
	public @ResponseBody Wall getWallById(String wallId) throws Exception {
		Wall result = wallService.getWallById(wallId);
		return result;
	}

	@RequestMapping("/insert")
	public @ResponseBody int insert(@RequestBody Wall wall) throws Exception {
		CharacterUtils characterUtils = new CharacterUtils();
		String uid = characterUtils.getUUID();
		wall.setWallId(uid);
		int result = wallService.insert(wall);
		return result;
	}

	@RequestMapping("/update")
	public @ResponseBody int updateByPrimaryKey(@RequestBody Wall wall) throws Exception {
		int result = wallService.updateByPrimaryKey(wall);
		return result;
	}

	@RequestMapping("/delete")
	public @ResponseBody int deleteByPrimaryKey(@RequestBody Wall wall) throws Exception {
		int result = wallService.deleteByPrimaryKey(wall.getWallId());
		return result;
	}
}
