package com.qfy.modules.city3d.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.qfy.modules.city3d.mapper.DutyVideoVideoMapper;
import com.qfy.modules.city3d.po.DutyVideoVideo;
import com.qfy.modules.city3d.po.DutyVideoVideoExample;
import com.qfy.modules.city3d.service.DutyVideoVideoService;
import org.springframework.stereotype.Service;

@Service
public class DutyVideoVideoServiceImpl implements DutyVideoVideoService {
	@Autowired
	private DutyVideoVideoMapper dutyVideoVideoMapper;

	@Override
	public int deleteDutyVideoVideo(String dutyVideoId) {
		DutyVideoVideoExample example = new DutyVideoVideoExample();
		example.or().andDutyVideoIdEqualTo(dutyVideoId);
		int result = dutyVideoVideoMapper.deleteByExample(example);
		return result;
	}

	@Override
	public int insertDutyVideoVideo(DutyVideoVideo dutyVideoVideo) {
		int result = dutyVideoVideoMapper.insert(dutyVideoVideo);
		return result;
	}

}
