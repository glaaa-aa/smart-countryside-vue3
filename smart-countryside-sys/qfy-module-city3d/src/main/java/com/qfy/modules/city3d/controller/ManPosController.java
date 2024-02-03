package com.qfy.modules.city3d.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qfy.modules.city3d.po.ManPos;
import com.qfy.modules.city3d.service.ManPosService;

@Controller
@RequestMapping("/manPos")
public class ManPosController extends CrossOriginController{
	@Autowired
	private ManPosService manPosService;

	@RequestMapping("/getHisDataByTime")
	public @ResponseBody List<ManPos> getHisDataByTime(@RequestBody Map<String,String> manPosMap) throws Exception {
		Map<String, String> params = new HashMap<String, String>();
		String startTime = manPosMap.get("startTime");
		String endTime = manPosMap.get("endTime");
		params.put("deviceNum", manPosMap.get("deviceNum"));
		params.put("startTime", startTime);
		params.put("endTime", endTime);

		String substartTime = startTime.substring(0,10).replace("-","");
		String subendTime = endTime.substring(0,10).replace("-","");
		String today = (new SimpleDateFormat("yyyyMMdd")).format(new Date());
		if(subendTime.compareTo(today) > 0)
			subendTime = today;
		String tableName1 = "man_pos_"+startTime.substring(0,10).replace("-","_");
		params.put("tableName1", tableName1);
		String tableName2 = "man_pos_"+endTime.substring(0,10).replace("-","_");
		params.put("tableName2", tableName2);

		if(tableName1.equals(tableName2) == false)
		{
			List<ManPos> result = manPosService.getTraceManPos2Days(params);
			return result;
		}else {
			List<ManPos> result = manPosService.getTraceManPosInOneDay(params);
			return result;
		}
	}
}
