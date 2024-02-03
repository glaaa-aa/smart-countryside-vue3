package com.qfy.modules.city3d.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qfy.modules.city3d.po.FireControl;
import com.qfy.modules.city3d.service.FireControlService;
import com.qfy.modules.city3d.util.CharacterUtils;

@Controller
@RequestMapping("/fireControl")
public class FireControlController extends CrossOriginController {
	@Autowired
	private FireControlService fireControlService;

	@RequestMapping("/getFireControl")
	public @ResponseBody List<HashMap<String, String>> getFireControl() throws Exception {
		List<HashMap<String, String>> result = fireControlService.getFireControl();
		return result;
	}

	@RequestMapping("/getFireControlNoPic")
	public @ResponseBody List<FireControl> getFireControlNoPic() throws Exception {
		List<FireControl> result = fireControlService.getFireControlNoPic();
		return result;
	}

	@RequestMapping("/getFireControlById")
	public @ResponseBody FireControl getFireControlById(String fcId) throws Exception{
		FireControl result = fireControlService.getFireControlById(fcId);
		return result;
	}

	@RequestMapping("/queryFireControl")
	public @ResponseBody List<FireControl> queryFireControl(@RequestBody FireControl fireControl) throws Exception{
		List<FireControl> result = fireControlService.queryFireControl(fireControl);
		return result;
	}

	@RequestMapping("/insert")
	public @ResponseBody int insert(@RequestBody FireControl fireControl) throws Exception{
		CharacterUtils characterUtils = new CharacterUtils();
		String uid = characterUtils.getUUID();
		fireControl.setFireCtlId(uid);
		int result = fireControlService.insert(fireControl);
		return result;
	}

	@RequestMapping("/update")
	public @ResponseBody int updateByPrimaryKey(@RequestBody FireControl fireControl) throws Exception{
		int result = fireControlService.updateByPrimaryKey(fireControl);
		return result;
	}

	@RequestMapping("/delete")
	public @ResponseBody int deleteByPrimaryKey(String fcId) throws Exception{
		int result = fireControlService.deleteByPrimaryKey(fcId);
		return result;
	}
}
