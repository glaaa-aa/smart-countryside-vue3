package com.qfy.modules.city3d.controller;

import com.qfy.modules.city3d.po.AccessMan;
import com.qfy.modules.city3d.service.AccessManService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/accessMan")
public class AccessManController extends CrossOriginController{
	@Autowired
	private AccessManService accessManService;

	@RequestMapping("/getAccessMan")
	public @ResponseBody List<AccessMan> getAccessMan() throws Exception {
		List<AccessMan> result = accessManService.getAccessMan();
		return result;
	}

	@RequestMapping("/delete")
	public @ResponseBody int deleteByPrimaryKey(@RequestBody AccessMan accessMan) throws Exception {
		int result = accessManService.deleteByPrimaryKey(accessMan.getAccessManId());
		return result;
	}

	@RequestMapping("/batchDelete")
	public @ResponseBody int batchDelete(@RequestBody List<AccessMan> accessMans) throws Exception {
		List<String> accessManIds = new ArrayList();
		for(AccessMan accessMan : accessMans) {
			accessManIds.add(accessMan.getAccessManId());
		}
		int result = accessManService.batchDelete(accessManIds);
		return result;
	}
}
