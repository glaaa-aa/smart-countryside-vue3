package com.qfy.modules.city3d.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.qfy.modules.city3d.mapper.CompanyCheckMapper;
import com.qfy.modules.city3d.po.CompanyCheck;
import com.qfy.modules.city3d.po.CompanyCheckExample;
import com.qfy.modules.city3d.service.CompanyCheckService;
import org.springframework.stereotype.Service;

@Service
public class CompanyCheckServiceImpl implements CompanyCheckService{
	@Autowired
	private CompanyCheckMapper companyCheckMapper;

	@Override
	public List<CompanyCheck> findByCompanyId(String companyId) {
		CompanyCheckExample example = new CompanyCheckExample();
		example.createCriteria().andCompanyIdEqualTo(companyId);
		List<CompanyCheck> result = companyCheckMapper.selectByExample(example);
		return result;
	}

	@Override
	public int save(CompanyCheck entity) {
		int result = companyCheckMapper.insert(entity);
		return result;
	}

	@Override
	public int updateByPrimaryKey(CompanyCheck entity) {
		int result = companyCheckMapper.updateByPrimaryKey(entity);
		return result;
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		int result = companyCheckMapper.deleteByPrimaryKey(id);
		return result;
	}

}
