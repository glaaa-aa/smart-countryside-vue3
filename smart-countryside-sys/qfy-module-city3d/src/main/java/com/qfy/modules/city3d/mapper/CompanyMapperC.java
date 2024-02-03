package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qfy.modules.city3d.po.Company;
import java.util.List;

@DS("postgresql")
public interface CompanyMapperC extends CompanyMapper{
	public List<Company> queryCompanyData(Company company);
	public Company selectCompanyById(String companyId);
	public List<Company> getCompanyByAddId(String addId);
	public List<Company> getCompanyByComName(String community);
	public int batchDelete(List<String> companyIds);
}
