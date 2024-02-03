package com.qfy.modules.city3d.service;

import java.util.List;

import com.qfy.modules.city3d.po.CompanyPanorama;

public interface CompanyPanoramaService {
	public List<CompanyPanorama> getCompanyPanoramaByCompanyId(String companyId);
	public List<CompanyPanorama> findByCompanyId(String companyId);
	public int save(CompanyPanorama entity);
	public int updateByPrimaryKey(CompanyPanorama entity);
	public int deleteByPrimaryKey(String id);
}
