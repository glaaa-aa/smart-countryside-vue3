package com.qfy.modules.city3d.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qfy.modules.city3d.po.Datalayer;
import com.qfy.modules.city3d.service.DatalayerService;
import com.qfy.modules.city3d.util.CharacterUtils;

@Controller
@RequestMapping("/datalayer")
public class DatalayerController extends CrossOriginController {
	@Autowired
	private DatalayerService datalayerService;

	@RequestMapping("/getDatalayer")
	public @ResponseBody List<Datalayer> getDatalayer() throws Exception {
		List<Datalayer> result = datalayerService.getDatalayer();
		return result;
	}

	@RequestMapping("/insert")
	public @ResponseBody int insert(@RequestBody Datalayer datalayer) throws Exception {
		CharacterUtils characterUtils = new CharacterUtils();
		String uid = characterUtils.getUUID();
		datalayer.setLayerId(uid);
		int result = datalayerService.insert(datalayer);
		return result;
	}

	@RequestMapping("/update")
	public @ResponseBody int update(@RequestBody Datalayer datalayer) throws Exception {
		int result = datalayerService.updateByPrimaryKey(datalayer);
		return result;
	}

	@RequestMapping("/delete")
	public @ResponseBody int delete(@RequestBody Datalayer datalayer) throws Exception {
		int result = datalayerService.deleteByPrimaryKey(datalayer.getLayerId());
		return result;
	}
}
