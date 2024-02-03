package com.qfy.modules.city3d.controller;

import com.qfy.modules.city3d.po.BimCase;
import com.qfy.modules.city3d.service.BimCaseService;
import com.qfy.modules.city3d.util.CharacterUtils;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/bimCase")
public class BimCaseController extends CrossOriginController {
	@Autowired
	private BimCaseService bimCaseService;

	@RequestMapping("/getBimCase")
	public @ResponseBody List<BimCase> getBimCase() throws Exception {
		List<BimCase> result = bimCaseService.getBimCase();
		return result;
	}

	@RequestMapping("/insert")
	public @ResponseBody int insert(@RequestBody BimCase bimCase) throws Exception {
		CharacterUtils characterUtils = new CharacterUtils();
		String uid = characterUtils.getUUID();
		bimCase.setBimCaseId(uid);
		int result = bimCaseService.insert(bimCase);
		return result;
	}

//	放置后期需要更新，此接口暂留
	@RequestMapping("/update")
	public @ResponseBody int update(@RequestBody BimCase bimCase) throws Exception {
		int result = bimCaseService.updateByPrimaryKey(bimCase);
		return result;
	}

	@RequestMapping("/delete")
	public @ResponseBody int deleteByPrimaryKey(@RequestBody BimCase bimCase) throws Exception {
		int result = bimCaseService.deleteByPrimaryKey(bimCase.getBimCaseId());
		return result;
	}
}
