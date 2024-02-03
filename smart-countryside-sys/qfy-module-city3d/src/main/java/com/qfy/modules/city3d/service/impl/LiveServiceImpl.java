package com.qfy.modules.city3d.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.qfy.modules.city3d.mapper.LiveMapper;
import com.qfy.modules.city3d.po.Live;
import com.qfy.modules.city3d.po.LiveExample;
import com.qfy.modules.city3d.service.LiveService;
import org.springframework.stereotype.Service;

@Service
public class LiveServiceImpl implements LiveService {
	@Autowired
	private LiveMapper liveMapper;

	@Override
	public List<Live> getLiveAll(){
		List<Live> result = liveMapper.selectByExample(null);
		return result;
	}

	@Override
	public List<Live> selectByVideoId(String id) {
		LiveExample example = new LiveExample();
		example.or().andVideoIdEqualTo(id);
		List<Live> result = liveMapper.selectByExample(example);
		return result;
	}

	@Override
	public int countByVideoId(String id) {
		LiveExample example = new LiveExample();
		example.or().andVideoIdEqualTo(id);
		int result = liveMapper.countByExample(example);
		return result;
	}

	@Override
	public int insert(Live live) {
		int result = liveMapper.insert(live);
		return result;
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		int result = liveMapper.deleteByPrimaryKey(id);
		return result;
	}
}
