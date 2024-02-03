package com.qfy.modules.city3d.service;

import java.util.List;

import com.qfy.modules.city3d.po.Video;

public interface VideoService {

	public List<Video> getVideoAndNodes();

	public int countCam();

	public List<Video> getVideosIsNode();

	public Video getVideoById(String id);

	public List<Video> getRHPCVideo(String positionStr);

	public List<Video> getVideoNoNodes();

	public int insert(Video Video);

	public int updateByPrimaryKey(Video Video);

	public int deleteByPrimaryKey(String videoId);

	public Video getVideoByMacUrl(String macUrl);

	public List<Video> getVideoListByMacUrl(String macUrl);

	public int countVideoByCamPort(String camPort);
}
