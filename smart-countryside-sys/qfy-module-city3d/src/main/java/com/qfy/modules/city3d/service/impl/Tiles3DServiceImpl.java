package com.qfy.modules.city3d.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.qfy.modules.city3d.mapper.Tiles3dMapper;
import com.qfy.modules.city3d.po.Tiles3d;
import com.qfy.modules.city3d.service.Tiles3DService;
import org.springframework.stereotype.Service;

@Service
public class Tiles3DServiceImpl extends ServiceImpl<Tiles3dMapper, Tiles3d> implements Tiles3DService {
	@Autowired
	private Tiles3dMapper tiles3dMapper;

	@Override
	public List<Tiles3d> getTiles3d() {
		List<Tiles3d> result = tiles3dMapper.selectByExample(null);
		return result;
	}

	@Override
	public Tiles3d getTiles3DById(String tilesId) {
		Tiles3d result = tiles3dMapper.selectByPrimaryKey(tilesId);
		return result;
	}

	@Override
	public int insert(Tiles3d tiles3d) {
		int result = tiles3dMapper.insert(tiles3d);
		return result;
	}

	@Override
	public int updateByPrimaryKey(Tiles3d tiles3d) {
		int result = tiles3dMapper.updateByPrimaryKey(tiles3d);
		return result;
	}

	@Override
	public int deleteByPrimaryKey(String tilesId) {
		int result = tiles3dMapper.deleteByPrimaryKey(tilesId);
		return result;
	}

}
