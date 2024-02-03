package com.qfy.modules.city3d.controller;

import com.qfy.modules.city3d.po.AccessCar;
import com.qfy.modules.city3d.service.AccessCarService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/accessCar")
public class AccessCarController extends CrossOriginController{
	@Autowired
	private AccessCarService accessCarService;

	@RequestMapping("/getAccessCar")
	public @ResponseBody List<AccessCar> getAccessCar() throws Exception {
		List<AccessCar> result = accessCarService.getAccessCar();
		return result;
	}

	@RequestMapping("/delete")
	public @ResponseBody int deleteByPrimaryKey(@RequestBody AccessCar accessCar) throws Exception {
		int result = accessCarService.deleteByPrimaryKey(accessCar.getAccessCarId());
		return result;
	}

	@RequestMapping("/batchDelete")
	public @ResponseBody int batchDelete(@RequestBody List<AccessCar> accessCars) throws Exception {
		List<String> accessCarIds = new ArrayList();
		for(AccessCar accessCar : accessCars) {
			accessCarIds.add(accessCar.getAccessCarId());
		}
		int result = accessCarService.batchDelete(accessCarIds);
		return result;
	}
}
