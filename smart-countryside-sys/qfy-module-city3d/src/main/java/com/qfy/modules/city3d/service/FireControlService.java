package com.qfy.modules.city3d.service;

import java.util.HashMap;
import java.util.List;

import com.qfy.modules.city3d.po.FireControl;

public interface FireControlService {
	public List<HashMap<String, String>> getFireControl();
	public List<FireControl> getFireControlNoPic();
	public FireControl getFireControlById(String fireCtlId);
	public List<FireControl> queryFireControl(FireControl fireControl);
	public int insert(FireControl fireControl);
	public int updateByPrimaryKey(FireControl fireControl);
	public int deleteByPrimaryKey(String fireCtlId);
}
