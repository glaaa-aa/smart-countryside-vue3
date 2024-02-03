package com.qfy.modules.city3d.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.qfy.modules.city3d.po.SysPara;
import com.qfy.modules.city3d.service.SysParaService;
import com.qfy.modules.city3d.util.CharacterUtils;

@Controller
@RequestMapping("/sys")
public class SysParaController extends CrossOriginController {
	@Autowired
	private SysParaService sysParaService;

	@RequestMapping("/getSys")
	public @ResponseBody List<SysPara> getSys() throws Exception {
		List<SysPara> result = sysParaService.getSysPara();
		return result;
	}

	@RequestMapping("/getSysById")
	public @ResponseBody SysPara getSysById(String sysId) throws Exception {
		SysPara result = sysParaService.findSysParaById(sysId);
		return result;
	}

	@RequestMapping("/querySysParabyType")
	public @ResponseBody List<SysPara> querySysParabyType(@RequestBody SysPara sysPara) throws Exception {
		List<SysPara> result = sysParaService.querySysParabyType(sysPara);
		return result;
	}

	@RequestMapping("/insert")
	public @ResponseBody int insert(@RequestBody SysPara sysPara) throws Exception{
		CharacterUtils characterUtils = new CharacterUtils();
		String uid = characterUtils.getUUID();
		sysPara.setSysId(uid);
		int result = sysParaService.insert(sysPara);
		return result;
	}

	@RequestMapping("/update")
	public @ResponseBody int updateByPrimaryKey(@RequestBody SysPara sysPara) throws Exception{
		int result = sysParaService.updateByPrimaryKey(sysPara);
		return result;
	}

	@RequestMapping("/delete")
	public @ResponseBody int deleteByPrimaryKey(@RequestBody SysPara sysPara) throws Exception{
		int result = sysParaService.deleteByPrimaryKey(sysPara.getSysId());
		return result;
	}
}
