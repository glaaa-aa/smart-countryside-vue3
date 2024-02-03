package com.qfy.modules.city3d.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.qfy.modules.city3d.mapper.BimDeviceMapperC;
import com.qfy.modules.city3d.po.BimDevice;
import com.qfy.modules.city3d.service.BimDeviceService;
import org.springframework.stereotype.Service;

@Service
public class BimDeviceServiceImpl implements BimDeviceService {
	@Autowired
	private BimDeviceMapperC bimDeviceMapper;

	@Override
	public List<BimDevice> getBimDevice() {
		List<BimDevice> result = bimDeviceMapper.selectByExample(null);
		return result;
	}

	@Override
	public BimDevice getBimDeviceById(String deviceId) {
		BimDevice result = bimDeviceMapper.selectByPrimaryKey(deviceId);
		return result;
	}

	@Override
	public int insert(BimDevice bimDevice) {
		int result = bimDeviceMapper.insert(bimDevice);
		return result;
	}

	@Override
	public int updateByPrimaryKey(BimDevice bimDevice) {
		int result = bimDeviceMapper.updateByPrimaryKey(bimDevice);
		return result;
	}

	@Override
	public int deleteByPrimaryKey(String deviceId) {
		int result = bimDeviceMapper.deleteByPrimaryKey(deviceId);
		return result;
	}

	@Override
	public int batchDelete(List<String> deviceIds) {
		int result = bimDeviceMapper.batchDelete(deviceIds);
		return result;
	}
}
