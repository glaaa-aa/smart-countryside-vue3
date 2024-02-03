package com.qfy.modules.city3d.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.qfy.modules.city3d.mapper.NetManMapperC;
import com.qfy.modules.city3d.po.NetMan;
import com.qfy.modules.city3d.po.NetManExample;
import com.qfy.modules.city3d.service.NetManService;
import org.springframework.stereotype.Service;

@Service
public class NetManServiceImpl implements NetManService {
	@Autowired
	private NetManMapperC netManMapper;

	@Override
	public List<NetMan> getNetMan() {
		List<NetMan> result = netManMapper.selectByExample(null);
		return result;
	}

	@Override
	public NetMan getNetManById(String netManId) {
		NetMan result = netManMapper.selectByPrimaryKey(netManId);
		return result;
	}

	public List<NetMan> getNetManByType(String type) {
		NetManExample example = new NetManExample();
		example.or().andTypeEqualTo(type);
		List<NetMan> result = netManMapper.selectByExample(example);
		return result;
	}

	@Override
	public int insert(NetMan netMan) {
		int result = netManMapper.insert(netMan);
		return result;
	}

	@Override
	public int updateByPrimaryKey(NetMan netMan) {
		int result = netManMapper.updateByPrimaryKey(netMan);
		return result;
	}

	@Override
	public int deleteByPrimaryKey(String netManId) {
		int result = netManMapper.deleteByPrimaryKey(netManId);
		return result;
	}

	@Override
	public int batchDelete(List<String> netManIds) {
		int result = netManMapper.batchDelete(netManIds);
		return result;
	}
}
