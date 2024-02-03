package com.qfy.modules.city3d.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.qfy.modules.city3d.mapper.DutyVideoMapperC;
import com.qfy.modules.city3d.po.DutyVideo;
import com.qfy.modules.city3d.service.DutyVideoService;
import org.springframework.stereotype.Service;

@Service
public class DutyVideoServiceImpl implements DutyVideoService {
	@Autowired
	private DutyVideoMapperC dutyVideoMapper;

	@Override
	public List<DutyVideo> getDutyVideo() {
		List<DutyVideo> result = dutyVideoMapper.getDutyVideoAll();
		return result;
	}

	@Override
	public int insert(DutyVideo dutyVideo) {
		int result = dutyVideoMapper.insert(dutyVideo);
		return result;
	}

	@Override
	public int updateByPrimaryKey(DutyVideo dutyVideo) {
		int result = dutyVideoMapper.updateByPrimaryKey(dutyVideo);
		return result;
	}

	@Override
	public int deleteByPrimaryKey(String dutyVideoId) {
		int result = dutyVideoMapper.deleteByPrimaryKey(dutyVideoId);
		return result;
	}

}
