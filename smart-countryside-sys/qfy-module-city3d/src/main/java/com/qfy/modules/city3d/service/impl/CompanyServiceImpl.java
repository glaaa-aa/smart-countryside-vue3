package com.qfy.modules.city3d.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.qfy.modules.city3d.mapper.CompanyMapperC;
import com.qfy.modules.city3d.po.Company;
import com.qfy.modules.city3d.service.CompanyService;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements CompanyService {
	@Autowired
	private CompanyMapperC companyMapperC;

	@Override
	public List<Company> queryCompanyData(Company company) {
		List<Company> result = companyMapperC.queryCompanyData(company);
		return result;
	}

	@Override
	public Company selectCompanyById(String companyId) {
		Company result = companyMapperC.selectCompanyById(companyId);
		return result;
	}

	@Override
	public List<Company> getCompanyByAddId(String addId) {
		List<Company> result = companyMapperC.getCompanyByAddId(addId);
		return result;
	}

	@Override
	public List<Company> getCompanyByComName(String community) {
		List<Company> result = companyMapperC.getCompanyByComName(community);
		return result;
	}

	@Override
	public int save(Company entity) {
		int result = companyMapperC.insert(entity);
		return result;
	}

	@Override
	public int updateByPrimaryKey(Company entity) {
		int result = companyMapperC.updateByPrimaryKey(entity);
		return result;
	}

	@Override
	public int deleteById(String id) {
		int result = companyMapperC.deleteByPrimaryKey(id);
		return result;
	}

	@Override
	public int batchDelete(List<String> companyIds) {
		int result = companyMapperC.batchDelete(companyIds);
		return result;
	}
}
