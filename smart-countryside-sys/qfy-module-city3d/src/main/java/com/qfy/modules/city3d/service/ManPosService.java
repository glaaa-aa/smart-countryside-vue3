package com.qfy.modules.city3d.service;

import java.util.List;
import java.util.Map;
import com.qfy.modules.city3d.po.ManPos;

public interface ManPosService {
	public List<ManPos> getManPosAll();
	public List<ManPos> getManPosByDn(ManPos manPos);
	public List<ManPos> getManPosWithNameAndOrigin(String[] deviceNums);
	public List<ManPos> getTraceManPos2Days(Map<String, String> params);
	public List<ManPos> getTraceManPosInOneDay(Map<String, String> params);
}
