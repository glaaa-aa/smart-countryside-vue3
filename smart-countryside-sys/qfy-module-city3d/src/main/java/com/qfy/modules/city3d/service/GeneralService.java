package com.qfy.modules.city3d.service;

import java.util.HashMap;
import java.util.List;

import com.qfy.modules.city3d.po.SysPara;

public interface GeneralService {
	public List<HashMap<String, String>> countPeople();
	public List<HashMap<String, String>> countAdd();
	public List<HashMap<String, String>> countDep();
	public List<HashMap<String, String>> countPatrol();
	public List<HashMap<String, String>> countCam();
	public List<HashMap<String, String>> countMan();
	public List<SysPara> getMain();
	public List<Object> countGeneralByExample();
}
