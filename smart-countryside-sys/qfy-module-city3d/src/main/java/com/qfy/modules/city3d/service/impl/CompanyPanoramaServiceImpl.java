package com.qfy.modules.city3d.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.qfy.modules.city3d.mapper.CompanyPanoramaMapper;
import com.qfy.modules.city3d.po.CompanyPanorama;
import com.qfy.modules.city3d.po.CompanyPanoramaExample;
import com.qfy.modules.city3d.service.CompanyPanoramaService;
import org.springframework.stereotype.Service;

@Service
public class CompanyPanoramaServiceImpl implements CompanyPanoramaService {
	@Autowired
	private CompanyPanoramaMapper companyPanoramaMapper;

	@Override
	public List<CompanyPanorama> getCompanyPanoramaByCompanyId(String companyId) {
		CompanyPanoramaExample example = new CompanyPanoramaExample();
		example.createCriteria().andCompanyIdEqualTo(companyId);
		List<CompanyPanorama> result = companyPanoramaMapper.selectByExample(example);
		return result;
	}

	@Override
	public List<CompanyPanorama> findByCompanyId(String companyId) {
		CompanyPanoramaExample example = new CompanyPanoramaExample();
		example.createCriteria().andCompanyIdEqualTo(companyId);
		List<CompanyPanorama> result = companyPanoramaMapper.selectByExample(example);
		return result;
	}

	@Override
	public int save(CompanyPanorama entity) {
		int result = companyPanoramaMapper.insert(entity);
		return result;
	}

	@Override
	public int updateByPrimaryKey(CompanyPanorama entity) {
		int result = companyPanoramaMapper.updateByPrimaryKey(entity);
		return result;
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		int result = companyPanoramaMapper.deleteByPrimaryKey(id);
		return result;
	}

}
