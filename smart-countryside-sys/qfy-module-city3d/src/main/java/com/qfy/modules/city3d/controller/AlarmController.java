package com.qfy.modules.city3d.controller;

import com.qfy.modules.city3d.po.Alarm;
import com.qfy.modules.city3d.service.AlarmService;
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
@RequestMapping("/alarm")
public class AlarmController extends CrossOriginController {
	@Autowired
	private AlarmService alarmService;

	@RequestMapping("/getAlarm")
	public @ResponseBody List<Alarm> getAlarm() throws Exception {
		List<Alarm> result = alarmService.getAlarm();
		return result;
	}

	@RequestMapping("/insert")
	public @ResponseBody int insert(@RequestBody Alarm alarm) throws Exception {
		CharacterUtils characterUtils = new CharacterUtils();
		String uid = characterUtils.getUUID();
		alarm.setAlarmId(uid);
		int result = alarmService.insert(alarm);
		return result;
	}

	@RequestMapping("/update")
	public @ResponseBody int updateByPrimaryKey(@RequestBody Alarm alarm) throws Exception {
		int result = alarmService.updateByPrimaryKey(alarm);
		return result;
	}

	@RequestMapping("/delete")
	public @ResponseBody int deleteByPrimaryKey(@RequestBody Alarm alarm) throws Exception{
		int result = alarmService.deleteByPrimaryKey(alarm.getAlarmId());
		return result;
	}

	@RequestMapping("/checkWallAlarm")
	public @ResponseBody Boolean checkWallAlarm(@RequestBody Alarm alarm) throws Exception{
		Boolean result = false;
		String[] poiArr = alarm.getAlarmType().split(",");
		Point2D.Double point = new Point2D.Double(Double.parseDouble(poiArr[0]), Double.parseDouble(poiArr[1]));
	    List<Point2D.Double> pts = new ArrayList<Point2D.Double>();
	    String[] wallpoiArr = alarm.getAlarmPoi().split(",");
    	for (int i = 0; i < wallpoiArr.length; i = i + 3) {
    		pts.add(new Point2D.Double(Double.parseDouble(wallpoiArr[i]), Double.parseDouble(wallpoiArr[i+1])));
		}
    	if(GeoTools.IsPtInPoly(point, pts)){
    		result = true;
    	}
		return result;
	}

	@RequestMapping("/batchDelete")
	public @ResponseBody int batchDelete(@RequestBody List<Alarm> alarms) throws Exception {
		List<String> alarmIds = new ArrayList();
		for(Alarm alarm : alarms) {
			alarmIds.add(alarm.getAlarmId());
		}
		int result = alarmService.batchDelete(alarmIds);
		return result;
	}
}
