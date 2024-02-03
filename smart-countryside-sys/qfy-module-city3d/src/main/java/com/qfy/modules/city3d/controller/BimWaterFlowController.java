package com.qfy.modules.city3d.controller;

import com.qfy.modules.city3d.po.BimWaterFlow;
import com.qfy.modules.city3d.service.BimWaterFlowService;
import com.qfy.modules.city3d.util.CharacterUtils;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/bimWaterFlow")
public class BimWaterFlowController extends CrossOriginController {
	@Autowired
	private BimWaterFlowService bimWaterFlowService;

	@RequestMapping("/getBimWaterFlow")
	public @ResponseBody List<BimWaterFlow> getBimWaterFlow() throws Exception {
		List<BimWaterFlow> result = bimWaterFlowService.getBimWaterFlow();
		return result;
	}

	@RequestMapping("/insert")
	public @ResponseBody int insert(@RequestBody BimWaterFlow bimWaterFlow) throws Exception {
		CharacterUtils characterUtils = new CharacterUtils();
		String uid = characterUtils.getUUID();
		bimWaterFlow.setBimFlowId(uid);
		int result = bimWaterFlowService.insert(bimWaterFlow);
		return result;
	}

	@RequestMapping("/update")
	public @ResponseBody int updateByPrimaryKey(@RequestBody BimWaterFlow bimWaterFlow) throws Exception {
		int result = bimWaterFlowService.updateByPrimaryKey(bimWaterFlow);
		return result;
	}

	@RequestMapping("/delete")
	public @ResponseBody int deleteByPrimaryKey(@RequestBody BimWaterFlow bimWaterFlow) throws Exception {
		int result = bimWaterFlowService.deleteByPrimaryKey(bimWaterFlow.getBimFlowId());
		return result;
	}
}
