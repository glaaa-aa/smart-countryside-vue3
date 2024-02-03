package com.qfy.modules.city3d.service;

import java.util.List;

import com.qfy.modules.city3d.po.MapPhotos;

public interface MapPhotosService {
	public List<MapPhotos> getMapPhotos();
	public List<MapPhotos> getMapPhotosByTime(String startTime, String endTime);
	public int insert(MapPhotos mapPhotos);
	public int deleteByPrimaryKey(String photoId);
	public int batchDelete(List<String> photoIds);
}
