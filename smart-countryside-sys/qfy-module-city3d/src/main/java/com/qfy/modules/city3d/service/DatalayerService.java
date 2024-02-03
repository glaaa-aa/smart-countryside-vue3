package com.qfy.modules.city3d.service;

import java.util.List;
import com.qfy.modules.city3d.po.Datalayer;

public interface DatalayerService {
	public List<Datalayer> getDatalayer();
	public int insert(Datalayer datalayer);
	public int updateByPrimaryKey(Datalayer datalayer);
	public int deleteByPrimaryKey(String layerId);
}
