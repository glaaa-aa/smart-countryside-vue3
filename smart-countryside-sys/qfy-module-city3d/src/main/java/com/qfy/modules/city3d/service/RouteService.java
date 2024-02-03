package com.qfy.modules.city3d.service;

import java.util.List;

import com.qfy.modules.city3d.po.PeopleRoute;

public interface RouteService {
	public List<PeopleRoute> getPeopleRoute() throws Exception;
	public PeopleRoute getRouteById(String routeId) throws Exception;
	public int insert(PeopleRoute peopleRoute);
	public int updateByPrimaryKey(PeopleRoute PeopleRoute);
	public int deleteByPrimaryKey(String routeId);
	public int iskpBe(String kpId);
}
