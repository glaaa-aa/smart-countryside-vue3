package com.qfy.modules.city3d.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.qfy.modules.city3d.mapper.HousePanoramaMapper;
import com.qfy.modules.city3d.po.HousePanorama;
import com.qfy.modules.city3d.po.HousePanoramaExample;
import com.qfy.modules.city3d.service.HousePanoramaService;
import org.springframework.stereotype.Service;

@Service
public class HousePanoramaServiceImpl implements HousePanoramaService {
	@Autowired
	private HousePanoramaMapper housePanoramaMapper;

	@Override
	public List<HousePanorama> getHousePanoramaByHosueId(String houseId) {
		HousePanoramaExample example = new HousePanoramaExample();
		example.createCriteria().andHouseIdEqualTo(houseId);
		List<HousePanorama> result = housePanoramaMapper.selectByExample(example);
		return result;
	}

	@Override
	public int insert(HousePanorama housePanorama) {
		int result = housePanoramaMapper.insert(housePanorama);
		return result;
	}

	@Override
	public int deleteByPrimaryKey(String panoramaId) {
		int result = housePanoramaMapper.deleteByPrimaryKey(panoramaId);
		return result;
	}

}
