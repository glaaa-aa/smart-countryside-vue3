package com.qfy.modules.city3d.service;

import com.qfy.modules.city3d.po.Model;
import java.util.List;

public interface ModelService {
	public List<Model> getModel();
	public int insert(Model model);
	public int updateByPrimaryKey(Model model);
	public int deleteByPrimaryKey(String modelId);
}
