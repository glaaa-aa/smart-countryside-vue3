package com.qfy.modules.city3d.service;

import com.qfy.modules.city3d.po.BimWaterFlow;
import java.util.List;

public interface BimWaterFlowService {
	public List<BimWaterFlow> getBimWaterFlow();
	public int insert(BimWaterFlow bimWaterFlow);
	public int updateByPrimaryKey(BimWaterFlow bimWaterFlow);
	public int deleteByPrimaryKey(String bimFlowId);
}
