package com.qfy.modules.city3d.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.qfy.modules.city3d.mapper.WallMapper;
import com.qfy.modules.city3d.po.Wall;
import com.qfy.modules.city3d.service.WallService;
import org.springframework.stereotype.Service;

@Service
public class WallServiceImpl implements WallService {
	@Autowired
	private WallMapper wallMapper;
	@Override
	public List<Wall> getWall() {
		List<Wall> result = wallMapper.selectByExample(null);
		return result;
	}

	@Override
	public Wall getWallById(String wallId) {
		Wall result = wallMapper.selectByPrimaryKey(wallId);
		return result;
	}

	@Override
	public int insert(Wall wall) {
		int result = wallMapper.insert(wall);
		return result;
	}

	@Override
	public int updateByPrimaryKey(Wall wall) {
		int result = wallMapper.updateByPrimaryKey(wall);
		return result;
	}

	@Override
	public int deleteByPrimaryKey(String wallId) {
		int result = wallMapper.deleteByPrimaryKey(wallId);
		return result;
	}

}
