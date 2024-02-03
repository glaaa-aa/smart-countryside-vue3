package com.qfy.modules.city3d.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.qfy.modules.city3d.mapper.MapPhotosMapperC;
import com.qfy.modules.city3d.po.MapPhotos;
import com.qfy.modules.city3d.po.MapPhotosExample;
import com.qfy.modules.city3d.service.MapPhotosService;
import org.springframework.stereotype.Service;

@Service
public class MapPhotosServiceImpl implements MapPhotosService {
	@Autowired
	private MapPhotosMapperC mapPhotosMapper;

	@Override
	public List<MapPhotos> getMapPhotos() {
		MapPhotosExample example = new MapPhotosExample();
		example.setOrderByClause("photo_time");
		List<MapPhotos> result = mapPhotosMapper.selectByExample(example);
		return result;
	}

	@Override
	public List<MapPhotos> getMapPhotosByTime(String startTime, String endTime) {
		MapPhotosExample example = new MapPhotosExample();
		example.or().andPhotoTimeBetween(startTime, endTime);
		example.setOrderByClause("photo_time");
		List<MapPhotos> result = mapPhotosMapper.selectByExample(example);
		return result;
	}

	@Override
	public int insert(MapPhotos mapPhotos) {
		int result = mapPhotosMapper.insert(mapPhotos);
		return result;
	}

	@Override
	public int deleteByPrimaryKey(String photoId) {
		int result = mapPhotosMapper.deleteByPrimaryKey(photoId);
		return result;
	}

	@Override
	public int batchDelete(List<String> photoIds) {
		int result = mapPhotosMapper.batchDelete(photoIds);
		return result;
	}
}
