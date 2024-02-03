package com.qfy.modules.city3d.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.qfy.modules.city3d.mapper.AttributeMapperC;
import com.qfy.modules.city3d.po.Attribute;
import com.qfy.modules.city3d.po.AttributeExample;
import com.qfy.modules.city3d.service.AttributeService;
import org.springframework.stereotype.Service;

@Service
public class AttributeServiceImpl implements AttributeService {
	@Autowired
	private AttributeMapperC attributeMapperC;

	@Override
	public List<Attribute> getAttributeAll() {
		List<Attribute> result = attributeMapperC.getAttributeAll();
		return result;
	}

	@Override
	public List<Attribute> getAttributeOnlyByCoverId(String coverageId) {
		AttributeExample example = new AttributeExample();
		example.createCriteria().andCoverageIdEqualTo(coverageId);
		List<Attribute> result = attributeMapperC.selectByExample(example);
		return result;
	}

	@Override
	public int getAttributeMaxOrderNumByCoverageId(String coverageId) {
		AttributeExample example = new AttributeExample();
		example.createCriteria().andCoverageIdEqualTo(coverageId);
		int result = attributeMapperC.countByExample(example);
		return result;
	}

	@Override
	public int insert(Attribute attribute) {
		int result = attributeMapperC.insert(attribute);
		return result;
	}

	@Override
	public int updateByPrimaryKey(Attribute attribute) {
		int result = attributeMapperC.updateByPrimaryKey(attribute);
		return result;
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		int result = attributeMapperC.deleteByPrimaryKey(id);
		return result;
	}

}
