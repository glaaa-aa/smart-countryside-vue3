package com.qfy.modules.city3d.service;

import java.util.List;

import com.qfy.modules.city3d.po.CompanyCheck;

public interface CompanyCheckService {
	 public List<CompanyCheck> findByCompanyId(String companyId);
	 public int save(CompanyCheck entity);
	 public int updateByPrimaryKey(CompanyCheck entity);
	 public int deleteByPrimaryKey(String id);
}
