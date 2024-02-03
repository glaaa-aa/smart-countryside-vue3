package com.qfy.modules.city3d.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.qfy.modules.city3d.mapper.BimWaterFlowMapper;
import com.qfy.modules.city3d.po.BimWaterFlow;
import com.qfy.modules.city3d.service.BimWaterFlowService;
import org.springframework.stereotype.Service;

@Service
public class BimWaterFlowServiceImpl implements BimWaterFlowService {
	@Autowired
	private BimWaterFlowMapper bimWaterFlowMapper;

	@Override
	public List<BimWaterFlow> getBimWaterFlow() {
		List<BimWaterFlow> result = bimWaterFlowMapper.selectByExample(null);
		return result;
	}

	@Override
	public int insert(BimWaterFlow bimWaterFlow) {
		int result = bimWaterFlowMapper.insert(bimWaterFlow);
		return result;
	}

	@Override
	public int updateByPrimaryKey(BimWaterFlow bimWaterFlow) {
		int result = bimWaterFlowMapper.updateByPrimaryKey(bimWaterFlow);
		return result;
	}

	@Override
	public int deleteByPrimaryKey(String bimFlowId) {
		int result = bimWaterFlowMapper.deleteByPrimaryKey(bimFlowId);
		return result;
	}

}
