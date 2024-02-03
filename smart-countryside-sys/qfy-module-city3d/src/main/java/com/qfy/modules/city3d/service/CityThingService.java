package com.qfy.modules.city3d.service;

import java.util.List;
import com.qfy.modules.city3d.po.CityThing;

public interface CityThingService {
	public List<CityThing> getCityThing();
	public List<CityThing> getCityThingByPid(String thingPid);
	public int insert(CityThing cityThing);
	public int updateByPrimaryKey(CityThing cityThing);
	public int deleteByPrimaryKey(String thingId);
	public int batchDelete(List<String> thingIds);
}
