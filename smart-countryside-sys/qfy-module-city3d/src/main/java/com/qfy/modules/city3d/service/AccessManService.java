package com.qfy.modules.city3d.service;

import com.qfy.modules.city3d.po.AccessMan;
import java.util.List;

public interface AccessManService {
	public List<AccessMan> getAccessMan();
	public int deleteByPrimaryKey(String accessManId);
	public int batchDelete(List<String> accessManIds);
}
