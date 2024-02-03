package com.qfy.modules.city3d.service;

import java.util.List;
import java.util.Map;

import com.qfy.modules.city3d.po.GpsHisDataInfo;

public interface GpsHisDataInfoService {
	public List<GpsHisDataInfo> getTracehisdata(Map<String, Object> map);
	public List<GpsHisDataInfo> getTracehisdata2Days(Map<String, Object> map);
	public List<GpsHisDataInfo> getTracehisdataAll7Days(Map<String, Object> map);
	public List<GpsHisDataInfo> getHisDataAllDemo();
}
