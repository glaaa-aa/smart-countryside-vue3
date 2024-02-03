package com.qfy.modules.city3d.service;

import java.util.List;

import com.qfy.modules.city3d.po.Live;

public interface LiveService {
	public List<Live> getLiveAll();
	public List<Live> selectByVideoId(String id);
	public int countByVideoId(String id);
	public int insert(Live live);
	public int deleteByPrimaryKey(String id);
}
