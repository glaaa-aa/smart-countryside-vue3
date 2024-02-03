package com.qfy.modules.city3d.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.qfy.modules.city3d.mapper.MapImageMapperC;
import com.qfy.modules.city3d.po.MapImage;
import com.qfy.modules.city3d.po.MapImageExample;
import com.qfy.modules.city3d.service.MapImageService;
import org.springframework.stereotype.Service;

@Service
public class MapImageServiceImpl implements MapImageService {
	@Autowired
	private MapImageMapperC mapImageMapper;

	@Override
	public List<MapImage> getMapImage() {
		MapImageExample example = new MapImageExample();
		example.or().andImagePidEqualTo("0");
		example.setOrderByClause("image_time");
		List<MapImage> result = mapImageMapper.selectByExample(example);
		return result;
	}

	@Override
	public int insert(MapImage mapImage) {
		int result = mapImageMapper.insert(mapImage);
		return result;
	}

	@Override
	public int deleteByPrimaryKey(String imageId) {
		int result = mapImageMapper.deleteByPrimaryKey(imageId);
		return result;
	}

	@Override
	public int batchDelete(List<String> imageIds) {
		int result = mapImageMapper.batchDelete(imageIds);
		return result;
	}

	@Override
	public List<MapImage> getMapImageByPid(String imagePid) {
		MapImageExample example = new MapImageExample();
		example.or().andImagePidEqualTo(imagePid);
		List<MapImage> result = mapImageMapper.selectByExample(example);
		return result;
	}
}
