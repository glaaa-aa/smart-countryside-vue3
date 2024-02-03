package com.qfy.modules.city3d.controller;

import com.qfy.modules.city3d.po.Bim;
import com.qfy.modules.city3d.service.BimService;
import com.qfy.modules.city3d.util.CharacterUtils;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/bim")
public class BimController extends CrossOriginController {
	@Autowired
	private BimService bimService;

	@RequestMapping("/getBim")
	public @ResponseBody List<Bim> getBim() throws Exception {
		List<Bim> result = bimService.getBim();
		return result;
	}

	@RequestMapping("/insert")
	public @ResponseBody int insert(@RequestBody Bim bim) throws Exception {
		CharacterUtils characterUtils = new CharacterUtils();
		String uid = characterUtils.getUUID();
		bim.setTilesId(uid);
		int result = bimService.insert(bim);
		return result;
	}

	@RequestMapping("/update")
	public @ResponseBody int updateByPrimaryKey(@RequestBody Bim bim) throws Exception {
		int result = bimService.updateByPrimaryKey(bim);
		return result;
	}

	@RequestMapping("/delete")
	public @ResponseBody int deleteByPrimaryKey(@RequestBody Bim bim) throws Exception {
		int result = bimService.deleteByPrimaryKey(bim.getTilesId());
		return result;
	}

	@RequestMapping("/batchDelete")
	public @ResponseBody int batchDelete(@RequestBody List<Bim> bims) throws Exception {
		List<String> tilesIds = new ArrayList();
		for(Bim bim : bims) {
			tilesIds.add(bim.getTilesId());
		}
		int result = bimService.batchDelete(tilesIds);
		return result;
	}
}
