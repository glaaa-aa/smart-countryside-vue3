package com.qfy.modules.city3d.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.qfy.modules.city3d.mapper.CaseMaterialMapper;
import com.qfy.modules.city3d.po.CaseMaterial;
import com.qfy.modules.city3d.po.CaseMaterialExample;
import com.qfy.modules.city3d.po.CaseMaterialExample.Criteria;
import com.qfy.modules.city3d.service.CaseMaterialService;
import org.springframework.stereotype.Service;

@Service
public class CaseMaterialServiceImpl implements CaseMaterialService {
	@Autowired
	private CaseMaterialMapper caseMaterialMapper;

	@Override
	public int uploadImg(CaseMaterial caseMaterial) {
		int result = caseMaterialMapper.insert(caseMaterial);
		return result;
	}

	@Override
	public List<CaseMaterial> getCaseMaterialByIdAndType(CaseMaterial caseMaterial) {
		CaseMaterialExample example = new CaseMaterialExample();
		Criteria criteriaResult = example.or();
		if(caseMaterial.getCaseId()!=null) {criteriaResult=criteriaResult.andCaseIdEqualTo(caseMaterial.getCaseId());}
		if(caseMaterial.getMaterialType()!=null) {criteriaResult=criteriaResult.andMaterialTypeEqualTo(caseMaterial.getMaterialType());}
		List<CaseMaterial> result = caseMaterialMapper.selectByExample(example);
		return result;
	}

	@Override
	public int deleteCaseMaterial(String caseMaterialId) {
		int result = caseMaterialMapper.deleteByPrimaryKey(caseMaterialId);
		return result;
	}
}
