package com.qfy.modules.city3d.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.qfy.modules.city3d.mapper.PeopleRouteMapperC;
import com.qfy.modules.city3d.po.PeopleRoute;
import com.qfy.modules.city3d.po.PeopleRouteExample;
import com.qfy.modules.city3d.service.RouteService;
import org.springframework.stereotype.Service;

@Service
public class RouteServiceImpl implements RouteService {
	@Autowired
	private PeopleRouteMapperC peopleRouteMapperC;
	@Override
	public List<PeopleRoute> getPeopleRoute() throws Exception {
		List<PeopleRoute> result = peopleRouteMapperC.selectPeopleRoute();
		return result;
	}

	@Override
	public PeopleRoute getRouteById(String routeId) throws Exception {
		PeopleRoute result =  peopleRouteMapperC.selectByPrimaryKey(routeId);
		return result;
	}

	@Override
	public int insert(PeopleRoute PeopleRoute) {
		int result = peopleRouteMapperC.insert(PeopleRoute);
		return result;
	}

	@Override
	public int updateByPrimaryKey(PeopleRoute PeopleRoute) {
		int result = peopleRouteMapperC.updateByPrimaryKey(PeopleRoute);
		return result;
	}

	@Override
	public int deleteByPrimaryKey(String routeId) {
		int result = peopleRouteMapperC.deleteByPrimaryKey(routeId);
		return result;
	}

	@Override
	public int iskpBe(String kpId){
		PeopleRouteExample example=new PeopleRouteExample();
		example.or().andKeyIdEqualTo(kpId);
		int result = peopleRouteMapperC.countByExample(example);
		return result;
	}
}
