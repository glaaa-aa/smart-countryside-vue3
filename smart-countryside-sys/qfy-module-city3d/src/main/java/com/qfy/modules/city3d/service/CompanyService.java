package com.qfy.modules.city3d.service;

import java.util.List;

import com.qfy.modules.city3d.po.Company;

public interface CompanyService {
	public List<Company> queryCompanyData(Company company);
	public Company selectCompanyById(String companyId);
	public List<Company> getCompanyByAddId(String addId);
	public List<Company> getCompanyByComName(String community);
	public int save(Company entity);
	public int updateByPrimaryKey(Company entity);
	public int deleteById(String id);
	public int batchDelete(List<String> companyIds);
}
