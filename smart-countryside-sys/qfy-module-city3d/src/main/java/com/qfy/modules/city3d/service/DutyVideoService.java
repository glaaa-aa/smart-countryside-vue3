package com.qfy.modules.city3d.service;

import java.util.List;

import com.qfy.modules.city3d.po.DutyVideo;

public interface DutyVideoService {
	public List<DutyVideo> getDutyVideo();
	public int insert(DutyVideo dutyVideo);
	public int updateByPrimaryKey(DutyVideo dutyVideo);
	public int deleteByPrimaryKey(String dutyVideoId);
}
