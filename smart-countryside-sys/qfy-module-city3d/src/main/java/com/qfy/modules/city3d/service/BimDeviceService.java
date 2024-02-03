package com.qfy.modules.city3d.service;

import com.qfy.modules.city3d.po.BimDevice;
import java.util.List;

public interface BimDeviceService {
	public List<BimDevice> getBimDevice();
	public BimDevice getBimDeviceById(String deviceId);
	public int insert(BimDevice bimDevice);
	public int updateByPrimaryKey(BimDevice bimDevice);
	public int deleteByPrimaryKey(String deviceId);
	public int batchDelete(List<String> deviceIds);
}
