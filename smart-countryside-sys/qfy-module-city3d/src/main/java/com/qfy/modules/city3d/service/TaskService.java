package com.qfy.modules.city3d.service;

import com.qfy.modules.city3d.po.City3dTask;
import java.util.List;

public interface TaskService {
	public List<City3dTask> selectTaskAll();
	public City3dTask selectTaskById(String id);
	public int insertTask(City3dTask task);
	public int updateTaskByPrimaryKey(City3dTask task);
	public int deleteTaskByPrimaryKey(String id);
	public List<City3dTask> selectTaskByCycleDay(String date);
	public List<City3dTask> selectTaskByCycleWeek(String date, String weekDate);
	public List<City3dTask> selectTaskByCycleMonth(String date, String datemStr);
	public int selectTaskCountToday();
	public List<City3dTask> selectTaskToday();
	//public List<City3dTask> selectTaskNextDays(String dayt,String dayn);
	public List<City3dTask> selectTaskNextDays(String dateStr, String weekStr);
}
