package com.qfy.modules.city3d.service;

import java.util.List;

import com.qfy.modules.city3d.po.MapImage;

public interface MapImageService {
	public List<MapImage> getMapImage();
	public int insert(MapImage mapImage);
	public int deleteByPrimaryKey(String imageId);
	public int batchDelete(List<String> imageIds);
	public List<MapImage> getMapImageByPid(String imagePid);
}
