package com.qfy.modules.city3d.service.impl;

import com.qfy.modules.city3d.po.City3dTask;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.qfy.modules.city3d.mapper.TaskMapperC;
import com.qfy.modules.city3d.po.TaskExample;
import com.qfy.modules.city3d.service.TaskService;
import org.springframework.stereotype.Service;

@Service("city3dTaskServiceImpl")
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskMapperC taskMapperC;

	@Override
	public List<City3dTask> selectTaskAll() {
		TaskExample example = new TaskExample();
		List<City3dTask> result = taskMapperC.selectByExample(example);
		return result;
	}

	@Override
	public City3dTask selectTaskById(String id) {
		City3dTask result = taskMapperC.selectByPrimaryKey(id);
		return result;
	}

	@Override
	public int insertTask(City3dTask task) {
		int result = taskMapperC.insert(task);
		return result;
	}

	@Override
	public int updateTaskByPrimaryKey(City3dTask task) {
		int result = taskMapperC.updateByPrimaryKey(task);
		return result;
	}

	@Override
	public int deleteTaskByPrimaryKey(String id) {
		int result = taskMapperC.deleteByPrimaryKey(id);
		return result;
	}

	@Override
	public List<City3dTask> selectTaskByCycleDay(String date) {
		TaskExample example = new TaskExample();
		example.createCriteria().andCycleEqualTo("day").andDateLessThanOrEqualTo(date);
		example.setOrderByClause("date");
		List<City3dTask> result = taskMapperC.selectByExample(example);
		return result;
	}

	@Override
	public List<City3dTask> selectTaskByCycleWeek(String date, String weekDate) {
		TaskExample example = new TaskExample();
		example.createCriteria().andCycleEqualTo("week,"+weekDate).andDateLessThanOrEqualTo(date);
		example.setOrderByClause("date");
		List<City3dTask> result = taskMapperC.selectByExample(example);
		return result;
	}

	@Override
	public List<City3dTask> selectTaskByCycleMonth(String date, String datemStr) {
		TaskExample example = new TaskExample();
		example.createCriteria().andCycleEqualTo("month,"+datemStr).andDateLessThanOrEqualTo(date);
		example.setOrderByClause("date");
		List<City3dTask> result = taskMapperC.selectByExample(example);
		return result;
	}

	@Override
	public int selectTaskCountToday() {
		int result = taskMapperC.selectTaskCountToday();
		return result;
	}

	@Override
	public List<City3dTask> selectTaskToday() {
		List<City3dTask> result = taskMapperC.selectTaskToday();
		return result;
	}

	/*@Override
	public List<City3dTask> selectTaskNextDays(String dayt,String dayn) {
		System.out.println(dayt);
		System.out.println(dayn);
		List<City3dTask> result = taskMapperC.selectTaskNextDays(dayt,dayn);
		return result;
	}*/

	@Override
	public List<City3dTask> selectTaskNextDays(String dateStr, String weekStr) {
		List<City3dTask> result = taskMapperC.selectTaskNextDays(dateStr,weekStr);
		return result;
	}

}
