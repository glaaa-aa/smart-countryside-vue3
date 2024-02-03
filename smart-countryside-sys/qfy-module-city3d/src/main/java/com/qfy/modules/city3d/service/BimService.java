package com.qfy.modules.city3d.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qfy.modules.city3d.po.Bim;
import java.util.List;

public interface BimService extends IService<Bim> {
	public List<Bim> getBim();
	public int insert(Bim bim);
	public int updateByPrimaryKey(Bim bim);
	public int deleteByPrimaryKey(String tilesId);
	public int batchDelete(List<String> tilesIds);
}
