package com.qfy.modules.city3d.controller;

import com.qfy.modules.city3d.po.BimDevice;
import com.qfy.modules.city3d.service.BimDeviceService;
import com.qfy.modules.city3d.util.CharacterUtils;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/bimDevice")
public class BimDeviceController extends CrossOriginController{
	@Autowired
	private BimDeviceService bimDeviceService;

	@RequestMapping("/getBimDevice")
	public @ResponseBody List<BimDevice> getBimDevice() throws Exception {
		List<BimDevice> result = bimDeviceService.getBimDevice();
		return result;
	}

	@RequestMapping("/getBimDeviceById")
	public @ResponseBody BimDevice getBimDeviceById(@RequestBody BimDevice bimDevice) throws Exception {
		BimDevice result = bimDeviceService.getBimDeviceById(bimDevice.getDeviceId());
		return result;
	}

	@RequestMapping("/insert")
	public @ResponseBody int insert(@RequestBody BimDevice bimDevice) throws Exception {
		CharacterUtils characterUtils = new CharacterUtils();
		String uid = characterUtils.getUUID();
		bimDevice.setDeviceId(uid);
		int result = bimDeviceService.insert(bimDevice);
		return result;
	}

	@RequestMapping("/update")
	public @ResponseBody int updateByPrimaryKey(@RequestBody BimDevice bimDevice) throws Exception {
		int result = bimDeviceService.updateByPrimaryKey(bimDevice);
		return result;
	}

	@RequestMapping("/delete")
	public @ResponseBody int deleteByPrimaryKey(@RequestBody BimDevice bimDevice) throws Exception {
		int result = bimDeviceService.deleteByPrimaryKey(bimDevice.getDeviceId());
		return result;
	}

	@RequestMapping("/batchDelete")
	public @ResponseBody int batchDelete(@RequestBody List<BimDevice> bimDevices) throws Exception {
		List<String> deviceIds = new ArrayList();
		for(BimDevice bimDevice : bimDevices) {
			deviceIds.add(bimDevice.getDeviceId());
		}
		int result = bimDeviceService.batchDelete(deviceIds);
		return result;
	}
}
