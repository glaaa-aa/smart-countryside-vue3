package com.qfy.modules.city3d.service;

import com.qfy.modules.city3d.po.AttributeData;
import java.util.List;

public interface AttributeDataService {
	public List<AttributeData> getAttributeDataAndCoverageByCoverageId(String coverageId);
	public AttributeData getAttributeDataAndAddressById(String id);
	public List<AttributeData> getAttributeDataAddressByCoverageIdAndAddName(String coverageId,String addName);
	public int insert(AttributeData attributeData);
	public int updateByPrimaryKey(AttributeData attributeData);
	public int deleteByPrimaryKey(String id);
}
