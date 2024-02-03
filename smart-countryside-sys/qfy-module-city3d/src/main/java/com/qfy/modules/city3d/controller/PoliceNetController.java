package com.qfy.modules.city3d.controller;

import com.qfy.modules.city3d.po.PoliceNet;
import com.qfy.modules.city3d.service.PoliceNetService;
import com.qfy.modules.city3d.util.CharacterUtils;
import com.qfy.modules.city3d.util.GeoTools;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/policeNet")
public class PoliceNetController extends CrossOriginController {
	@Autowired
	private PoliceNetService policeNetService;

	@RequestMapping("/getPoliceNet")
	public @ResponseBody List<PoliceNet> getPoliceNet() throws Exception {
		List<PoliceNet> result = policeNetService.getPoliceNet();
		return result;
	}

	@RequestMapping("/queryPoliceNet")
	public @ResponseBody List<PoliceNet> queryPoliceNet(@RequestBody PoliceNet policeNet) throws Exception {
		List<PoliceNet> result = policeNetService.queryPoliceNet(policeNet);
		return result;
	}

	@RequestMapping("/getNetByContains")
	public @ResponseBody PoliceNet getNetByContains(String position) {
		PoliceNet result = new PoliceNet();
		String[] poiArr = position.split(",");
		Point2D.Double point = new Point2D.Double(Double.parseDouble(poiArr[0]), Double.parseDouble(poiArr[1]));
	    List<Point2D.Double> pts = new ArrayList<Point2D.Double>();
	    List<PoliceNet> netList = policeNetService.getPoliceNet();
	    for (PoliceNet policeNet : netList) {
	    	String[] netPolygenArr = policeNet.getNetPolygen().split(",");
	    	for (int i = 0; i < netPolygenArr.length; i = i + 3) {
	    		pts.add(new Point2D.Double(Double.parseDouble(netPolygenArr[i]), Double.parseDouble(netPolygenArr[i+1])));
			}
	    	if(GeoTools.IsPtInPoly(point, pts)){
	    		result = policeNet;
	    		break;
	    	}
		}
		return result;
	}

	@RequestMapping("/insert")
	public @ResponseBody int insert(@RequestBody PoliceNet policeNet) throws Exception {
		CharacterUtils characterUtils = new CharacterUtils();
		String uid = characterUtils.getUUID();
		policeNet.setNetId(uid);
		int result = policeNetService.insert(policeNet);
		return result;
	}

	@RequestMapping("/update")
	public @ResponseBody int updateByPrimaryKey(@RequestBody PoliceNet policeNet) throws Exception {
		int result = policeNetService.updateByPrimaryKey(policeNet);
		return result;
	}

	@RequestMapping("/delete")
	public @ResponseBody int deleteByPrimaryKey(@RequestBody PoliceNet policeNet) throws Exception {
		int result = policeNetService.deleteByPrimaryKey(policeNet.getNetId());
		return result;
	}

	@RequestMapping("/batchDelete")
	public @ResponseBody int batchDelete(@RequestBody List<PoliceNet> policeNets) throws Exception {
		List<String> netIds = new ArrayList();
		for(PoliceNet policeNet : policeNets) {
			netIds.add(policeNet.getNetId());
		}
		int result = policeNetService.batchDelete(netIds);
		return result;
	}
}
