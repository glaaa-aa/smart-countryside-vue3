package com.qfy.modules.city3d.service;

import com.qfy.modules.city3d.po.Attribute;
import java.util.List;

public interface AttributeService {
	public List<Attribute> getAttributeAll();
	public List<Attribute> getAttributeOnlyByCoverId(String coverageId);
	public int getAttributeMaxOrderNumByCoverageId(String coverageId);
	public int insert(Attribute attribute);
	public int updateByPrimaryKey(Attribute attribute);
	public int deleteByPrimaryKey(String id);
}
