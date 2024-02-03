package com.qfy.modules.city3d.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.qfy.modules.city3d.mapper.PeopleRouteMapper;
import com.qfy.modules.city3d.po.PeopleRoute;
import com.qfy.modules.city3d.po.PeopleRouteExample;
import com.qfy.modules.city3d.service.PeopleRouteService;
import org.springframework.stereotype.Service;

@Service
public class PeopleRouteServiceImpl implements PeopleRouteService {
	@Autowired
	private PeopleRouteMapper peopleRouteMapper;

	@Override
	public List<PeopleRoute> getPeopleRoute(String keyId) {
		PeopleRouteExample example = new PeopleRouteExample();
		example.or().andKeyIdEqualTo(keyId);
		List<PeopleRoute> result = peopleRouteMapper.selectByExample(example);
		return result;
	}

}
