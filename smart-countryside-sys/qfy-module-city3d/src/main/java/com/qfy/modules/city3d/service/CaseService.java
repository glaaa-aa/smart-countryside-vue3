package com.qfy.modules.city3d.service;

import java.util.List;

import com.qfy.modules.city3d.po.PoliceCase;

public interface CaseService {
	public List<PoliceCase> getCase();

	public List<PoliceCase> getCaseAll();

	public PoliceCase getCaseandNetById(String caseId);

	public int countByNet(String netId);

	public int insertEmergencyCase(PoliceCase policeCase);

	public int insert(PoliceCase policeCase);

	public int updateByPrimaryKey(PoliceCase policeCase);

	public int deleteByPrimaryKey(String caseId);

	public List<PoliceCase> queryPoliceCase(PoliceCase policeCase);
}
