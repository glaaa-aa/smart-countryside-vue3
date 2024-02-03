package com.qfy.modules.city3d.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.qfy.modules.city3d.mapper.AttributeDataMapperC;
import com.qfy.modules.city3d.po.AttributeData;
import com.qfy.modules.city3d.service.AttributeDataService;
import org.springframework.stereotype.Service;

@Service
public class AttributeDataServiceImpl implements AttributeDataService {
	@Autowired
	private AttributeDataMapperC attributeDataMapperC;

	@Override
	public List<AttributeData> getAttributeDataAndCoverageByCoverageId(String coverageId) {
		List<AttributeData> result = attributeDataMapperC.getAttributeDataAndCoverageByCoverageId(coverageId);
		return result;
	}

	@Override
	public int insert(AttributeData attributeData) {
		int result = attributeDataMapperC.insert(attributeData);
		return result;
	}

	@Override
	public int updateByPrimaryKey(AttributeData attributeData) {
		int result = attributeDataMapperC.updateByPrimaryKey(attributeData);
		return result;
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		int result = attributeDataMapperC.deleteByPrimaryKey(id);
		return result;
	}

	@Override
	public AttributeData getAttributeDataAndAddressById(String id) {
		AttributeData result = attributeDataMapperC.selectByPrimaryKey(id);
		return result;
	}

	@Override
	public List<AttributeData> getAttributeDataAddressByCoverageIdAndAddName(String coverageId,String addName) {
		List<AttributeData> result = attributeDataMapperC.getAttributeDataAddressByCoverageIdAndAddName(coverageId,addName);
		return result;
	}

}
