package com.qfy.modules.city3d.service;

import com.qfy.modules.city3d.po.BimCase;
import java.util.List;

public interface BimCaseService {
	public List<BimCase> getBimCase();
	public int insert(BimCase bimCase);
	public int updateByPrimaryKey(BimCase bimCase);
	public int deleteByPrimaryKey(String bimCaseId);
}
