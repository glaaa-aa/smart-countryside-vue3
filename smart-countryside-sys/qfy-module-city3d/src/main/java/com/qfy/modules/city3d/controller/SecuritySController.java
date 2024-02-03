package com.qfy.modules.city3d.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qfy.modules.city3d.po.Security;
import com.qfy.modules.city3d.service.SecuritySService;
import com.qfy.modules.city3d.util.CharacterUtils;

@Controller
@RequestMapping("/securityS")
public class SecuritySController extends CrossOriginController {
	@Autowired
	private SecuritySService securitySService;

	@RequestMapping("/getSecurityAll")
	public @ResponseBody List<Security> getSecurityAll() throws Exception {
		List<Security> result = securitySService.getSecurityAll();
		return result;
	}

	@RequestMapping("/getSecuritySById")
	public @ResponseBody Security getSecuritySById(@RequestBody Security security) throws Exception {
		Security result = securitySService.getSecuritySById(security.getSecurityId());
		return result;
	}

	@RequestMapping("/querySecurityS")
	public @ResponseBody List<Security> querySecurityS(@RequestBody Security security) throws Exception{
		List<Security> result = securitySService.querySecurityS(security);
		return result;
	}

	@RequestMapping("/insert")
	public @ResponseBody int insert(@RequestBody Security security) throws Exception{
		CharacterUtils characterUtils = new CharacterUtils();
		String uid = characterUtils.getUUID();
		security.setSecurityId(uid);
		int result = securitySService.insert(security);
		return result;
	}

	@RequestMapping("/update")
	public @ResponseBody int updateByPrimaryKey(@RequestBody Security security) throws Exception{
		int result = securitySService.updateByPrimaryKey(security);
		return result;
	}

	@RequestMapping("/delete")
	public @ResponseBody int deleteByPrimaryKey(@RequestBody Security security) throws Exception{
		int result = securitySService.deleteByPrimaryKey(security.getSecurityId());
		return result;
	}

	@RequestMapping("/batchDelete")
	public @ResponseBody int batchDelete(@RequestBody List<Security> securitys) throws Exception {
		List<String> securityIds = new ArrayList();
		for(Security security : securitys) {
			securityIds.add(security.getSecurityId());
		}
		int result = securitySService.batchDelete(securityIds);
		return result;
	}
}
