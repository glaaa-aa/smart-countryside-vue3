package com.qfy.modules.city3d.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.qfy.modules.city3d.mapper.GpsHisDataInfoMapper;
import com.qfy.modules.city3d.po.GpsHisDataInfo;
import com.qfy.modules.city3d.service.GpsHisDataInfoService;
import org.springframework.stereotype.Service;

@Service
public class GpsHisDataInfoServiceImpl implements GpsHisDataInfoService {
	@Autowired
	private GpsHisDataInfoMapper gpsHisDataInfoMapper;

	@Override
	public List<GpsHisDataInfo> getTracehisdata2Days(Map<String, Object> map){
		List<GpsHisDataInfo> result = gpsHisDataInfoMapper.selectGpsInfosIn2Days(map);
		return result;
	}

	@Override
	public List<GpsHisDataInfo> getTracehisdata(Map<String, Object> map){
		List<GpsHisDataInfo> result = gpsHisDataInfoMapper.selectGpsInfosInOneDay(map);
		return result;
	}

	@Override
	public List<GpsHisDataInfo> getTracehisdataAll7Days(Map<String, Object> map){
		List<GpsHisDataInfo> result = gpsHisDataInfoMapper.getTracehisdataAll7Days(map);
		return result;
	}

	@Override
	public List<GpsHisDataInfo> getHisDataAllDemo(){
		List<GpsHisDataInfo> result = gpsHisDataInfoMapper.getHisDataAllDemo();
		return result;
	}
}
