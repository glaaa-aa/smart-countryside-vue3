package com.qfy.modules.city3d.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qfy.modules.city3d.po.EmergencyStore;
import com.qfy.modules.city3d.service.EmergencyStoreService;
import com.qfy.modules.city3d.util.CharacterUtils;

@Controller
@RequestMapping("/emergencyStore")
public class EmergencyStoreController extends CrossOriginController {
	@Autowired
	private EmergencyStoreService emergencyStoreService;

	@RequestMapping("/getEmergencyStore")
	public @ResponseBody List<HashMap<String, String>> getEmergencyStore() throws Exception {
		List<HashMap<String, String>> result = emergencyStoreService.getEmergencyStore();
		return result;
	}

	@RequestMapping("/getEmergencyStoreAll")
	public @ResponseBody List<EmergencyStore> getEmergencyStoreAll() throws Exception {
		List<EmergencyStore> result = emergencyStoreService.getEmergencyStoreAll();
		return result;
	}

	@RequestMapping("/getEmergencyStoreById")
	public @ResponseBody EmergencyStore getEmergencyStoreById(String storeId) throws Exception {
		EmergencyStore result = emergencyStoreService.getEmergencyStoreById(storeId);
		return result;
	}

	@RequestMapping("/queryEmergencyStore")
	public @ResponseBody List<EmergencyStore> queryEmergencyStore(@RequestBody EmergencyStore emergencyStore) throws Exception{
		List<EmergencyStore> result = emergencyStoreService.queryEmergencyStore(emergencyStore);
		return result;
	}

	@RequestMapping("/insert")
	public @ResponseBody int insert(@RequestBody EmergencyStore emergencyStore) throws Exception{
		CharacterUtils characterUtils = new CharacterUtils();
		String uid = characterUtils.getUUID();
		emergencyStore.setStoreId(uid);
		int result = emergencyStoreService.insert(emergencyStore);
		return result;
	}

	@RequestMapping("/update")
	public @ResponseBody int updateByPrimaryKey(@RequestBody EmergencyStore emergencyStore) throws Exception{
		int result = emergencyStoreService.updateByPrimaryKey(emergencyStore);
		return result;
	}

	@RequestMapping("/delete")
	public @ResponseBody int deleteByPrimaryKey(String storeId) throws Exception{
		int result = emergencyStoreService.deleteByPrimaryKey(storeId);
		return result;
	}
}
