package com.qfy.modules.city3d.service;

import java.util.List;

import com.qfy.modules.city3d.po.Wall;

public interface WallService {
	public List<Wall> getWall();
	public Wall getWallById(String wallId);
	public int insert(Wall wall);
	public int updateByPrimaryKey(Wall wall);
	public int deleteByPrimaryKey(String wallId);
}
