package com.qfy.modules.city3d.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.qfy.modules.city3d.mapper.VideoMapperC;
import com.qfy.modules.city3d.po.Video;
import com.qfy.modules.city3d.po.VideoExample;
import com.qfy.modules.city3d.service.VideoService;
import org.springframework.stereotype.Service;

@Service
public class VideoServiceImpl implements VideoService {
	@Autowired
	private VideoMapperC videoMapperC;

	@Override
	public List<Video> getVideoAndNodes() {
		VideoExample example = new VideoExample();
		String orderByClause = "id";
		example.setOrderByClause(orderByClause);
		List<Video> result = videoMapperC.selectByExample(example);
		return result;
	}

	@Override
	public int countCam() {
		VideoExample example = new VideoExample();
		example.or().andCamIsnodeNotEqualTo(true);
		int result = videoMapperC.countByExample(example);
		return result;
	}

	@Override
	public List<Video> getVideosIsNode() {
		VideoExample example = new VideoExample();
		example.or().andCamIsnodeEqualTo(true);
		List<Video> result = videoMapperC.selectByExample(example);
		return result;
	}

	@Override
	public Video getVideoById(String id) {
		Video result = videoMapperC.selectByPrimaryKey(id);
		return result;
	}

	@Override
	public List<Video> getRHPCVideo(String positionStr) {
		List<Video> result = videoMapperC.getRHPCVideo(positionStr);
		return result;
	}

	@Override
	public List<Video> getVideoNoNodes() {
		VideoExample example = new VideoExample();
		example.or().andCamIsnodeEqualTo(false);
		List<Video> result = videoMapperC.selectByExample(example);
		return result;
	}

	@Override
	public int insert(Video Video) {
		int result=videoMapperC.insert(Video);
		return result;
	}

	@Override
	public int updateByPrimaryKey(Video Video) {
		int result = videoMapperC.updateByPrimaryKey(Video);
		return result;
	}

	@Override
	public int deleteByPrimaryKey(String videoId) {
		int result = videoMapperC.deleteByPrimaryKey(videoId);
		return result;
	}

	@Override
	public Video getVideoByMacUrl(String macUrl) {
		VideoExample example = new VideoExample();
		example.createCriteria().andMacUrlEqualTo(macUrl);
		List<Video> result = videoMapperC.selectByExample(example);
		try {
			return result.get(0);
		} catch (Exception e) {
			return new Video();
		}

	}

	@Override
	public List<Video> getVideoListByMacUrl(String macUrl) {
		VideoExample example = new VideoExample();
		example.setOrderByClause("cam_add");
		example.createCriteria().andMacUrlEqualTo(macUrl);
		List<Video> result = videoMapperC.selectByExample(example);
		return result;
	}

	@Override
	public int countVideoByCamPort(String camPort) {
		VideoExample example = new VideoExample();
		example.createCriteria().andCamPortLike("%"+camPort);
		int result = videoMapperC.countByExample(example);
		return result;
	}
}
