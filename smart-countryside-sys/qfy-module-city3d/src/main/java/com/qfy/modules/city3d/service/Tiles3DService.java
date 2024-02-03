package com.qfy.modules.city3d.service;

import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

import com.qfy.modules.city3d.po.Tiles3d;

public interface Tiles3DService extends IService<Tiles3d> {
	public List<Tiles3d> getTiles3d();
	public Tiles3d getTiles3DById(String tilesId);
	public int insert(Tiles3d tiles3d);
	public int updateByPrimaryKey(Tiles3d tiles3d);
	public int deleteByPrimaryKey(String tilesId);
}
