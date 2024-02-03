package com.qfy.modules.city3d.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.qfy.modules.city3d.mapper.PoliceCaseMapperC;
import com.qfy.modules.city3d.po.PoliceCase;
import com.qfy.modules.city3d.po.PoliceCaseExample;
import com.qfy.modules.city3d.po.PoliceCaseExample.Criteria;
import com.qfy.modules.city3d.service.CaseService;
import org.springframework.stereotype.Service;

@Service
public class CaseServiceImpl implements CaseService {
	@Autowired
	private PoliceCaseMapperC policeCaseMapperC;

	@Override
	public List<PoliceCase> getCase() {
		List<PoliceCase> result = policeCaseMapperC.getCase();
		return result;
	}

	@Override
	public List<PoliceCase> getCaseAll() {
		List<PoliceCase> result = policeCaseMapperC.selectByExample(null);
		return result;
	}

	@Override
	public PoliceCase getCaseandNetById(String caseId) {
		PoliceCase result = policeCaseMapperC.selectCaseandNetById(caseId);
		return result;
	}

	@Override
	public int countByNet(String netId) {
		PoliceCaseExample example = new PoliceCaseExample();
		example.or().andNetIdEqualTo(netId);
		int result = policeCaseMapperC.countByExample(example);
		return result;
	}

	@Override
	public int insertEmergencyCase(PoliceCase policeCase) {
		int result = policeCaseMapperC.insert(policeCase);
		return result;
	}

	@Override
	public int insert(PoliceCase policeCase) {
		int result = policeCaseMapperC.insert(policeCase);
		return result;
	}

	@Override
	public int updateByPrimaryKey(PoliceCase policeCase) {
		int result = policeCaseMapperC.updateByPrimaryKey(policeCase);
		return result;
	}

	@Override
	public int deleteByPrimaryKey(String caseId) {
		int result = policeCaseMapperC.deleteByPrimaryKey(caseId);
		return result;
	}

	@Override
	public List<PoliceCase> queryPoliceCase(PoliceCase policeCase) {
		PoliceCaseExample example = new PoliceCaseExample();
		Criteria criteria = example.createCriteria();
		if(policeCase.getCaseName() != null && !policeCase.getCaseName().equals("")){
			criteria.andCaseNameLike("%"+policeCase.getCaseName()+"%");
		}
		if(policeCase.getCaseType() != null && !policeCase.getCaseType().equals("")){
			criteria.andCaseTypeLike("%"+policeCase.getCaseType()+"%");
		}
		if(policeCase.getCaseSource() != null && !policeCase.getCaseSource().equals("")){
			criteria.andCaseSourceEqualTo(policeCase.getCaseSource());
		}
		List<PoliceCase> result = policeCaseMapperC.selectByExample(example);
		return result;
	}
}
