package com.qfy.modules.city3d.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.qfy.modules.city3d.mapper.PeopleBaseMapperC;
import com.qfy.modules.city3d.po.Address;
import com.qfy.modules.city3d.po.PeopleBase;
import com.qfy.modules.city3d.po.PeopleBaseExample;
import com.qfy.modules.city3d.po.PeopleBaseExample.Criteria;
import com.qfy.modules.city3d.service.PeopleBaseService;
import org.springframework.stereotype.Service;

@Service
public class PeopleBaseServiceImpl implements PeopleBaseService {
	@Autowired
	private PeopleBaseMapperC peopleBaseMapperC;

	@Override
	public List<PeopleBase> getPeopleBaseAll() {
		List<PeopleBase> result = peopleBaseMapperC.selectByExample(null);
		return result;
	}

	@Override
	public List<PeopleBase> queryPeoplebasebyExample(PeopleBase peopleBase) {
		PeopleBaseExample example = new PeopleBaseExample();
		Criteria criteriaResult = example.or();
		if(peopleBase.getIdentity()!=null && peopleBase.getIdentity()!=""){criteriaResult=criteriaResult.andIdentityLike("%"+peopleBase.getIdentity()+"%");};
		if(peopleBase.getName()!=null && peopleBase.getName()!=""){criteriaResult=criteriaResult.andNameLike("%"+peopleBase.getName()+"%");};
		if(peopleBase.getOnceName()!=null && peopleBase.getOnceName()!=""){criteriaResult=criteriaResult.andOnceNameLike("%"+peopleBase.getOnceName()+"%");};
		if(peopleBase.getGender()!=null && peopleBase.getGender()!=""){criteriaResult=criteriaResult.andGenderLike("%"+peopleBase.getGender()+"%");};
		if(peopleBase.getHeight()!=null && peopleBase.getHeight()!=""){criteriaResult=criteriaResult.andHeightLike("%"+peopleBase.getHeight()+"%");};
		if(peopleBase.getBloodType()!=null && peopleBase.getBloodType()!=""){criteriaResult=criteriaResult.andBloodTypeLike("%"+peopleBase.getBloodType()+"%");};
		if(peopleBase.getNation()!=null && peopleBase.getNation()!=""){criteriaResult=criteriaResult.andNationLike("%"+peopleBase.getNation()+"%");};
		if(peopleBase.getPeopleType()!=null && peopleBase.getPeopleType()!=""){criteriaResult=criteriaResult.andPeopleTypeLike("%"+peopleBase.getPeopleType()+"%");};
		if(peopleBase.getCalture()!=null && peopleBase.getCalture()!=""){criteriaResult=criteriaResult.andCaltureLike("%"+peopleBase.getCalture()+"%");};
		if(peopleBase.getTel()!=null && peopleBase.getTel()!=""){criteriaResult=criteriaResult.andTelLike("%"+peopleBase.getTel()+"%");};
		if(peopleBase.getRegion()!=null && peopleBase.getRegion()!=""){criteriaResult=criteriaResult.andRegionLike("%"+peopleBase.getRegion()+"%");};
		if(peopleBase.getPolitical()!=null && peopleBase.getPolitical()!=""){criteriaResult=criteriaResult.andPoliticalLike("%"+peopleBase.getPolitical()+"%");};
		if(peopleBase.getMarriage()!=null && peopleBase.getMarriage()!=""){criteriaResult=criteriaResult.andMarriageLike("%"+peopleBase.getMarriage()+"%");};
		if(peopleBase.getUnitName()!=null && peopleBase.getUnitName()!=""){criteriaResult=criteriaResult.andUnitNameLike("%"+peopleBase.getUnitName()+"%");};
		if(peopleBase.getJob()!=null && peopleBase.getJob()!=""){criteriaResult=criteriaResult.andJobLike("%"+peopleBase.getJob()+"%");};
		if(peopleBase.getHouseholdNature()!=null && peopleBase.getHouseholdNature()!=""){criteriaResult=criteriaResult.andHouseholdNatureLike("%"+peopleBase.getHouseholdNature()+"%");};
		if(peopleBase.getHouseholdAddress()!=null && peopleBase.getHouseholdAddress()!=""){criteriaResult=criteriaResult.andHouseholdAddressLike("%"+peopleBase.getHouseholdAddress()+"%");};
		if(peopleBase.getFatherName()!=null && peopleBase.getFatherName()!=""){criteriaResult=criteriaResult.andFatherNameLike("%"+peopleBase.getFatherName()+"%");};
		if(peopleBase.getFatherIdentity()!=null && peopleBase.getFatherIdentity()!=""){criteriaResult=criteriaResult.andFatherIdentityLike("%"+peopleBase.getFatherIdentity()+"%");};
		if(peopleBase.getMotherName()!=null && peopleBase.getMotherName()!=""){criteriaResult=criteriaResult.andMotherNameLike("%"+peopleBase.getMotherName()+"%");};
		if(peopleBase.getMotherIdentity()!=null && peopleBase.getMotherIdentity()!=""){criteriaResult=criteriaResult.andMotherIdentityLike("%"+peopleBase.getMotherIdentity()+"%");};
		if(peopleBase.getMateName()!=null && peopleBase.getMateName()!=""){criteriaResult=criteriaResult.andMateNameLike("%"+peopleBase.getMateName()+"%");};
		if(peopleBase.getMotherIdentity()!=null && peopleBase.getMotherIdentity()!=""){criteriaResult=criteriaResult.andMotherIdentityLike("%"+peopleBase.getMotherIdentity()+"%");};
		if(peopleBase.getServeArmy()!=null && peopleBase.getServeArmy()!=""){criteriaResult=criteriaResult.andServeArmyLike("%"+peopleBase.getServeArmy()+"%");};
		List<PeopleBase> result = peopleBaseMapperC.selectByExampleC(peopleBase);
		return result;
	}

	@Override
	public List<PeopleBase> queryPeoplebasebyPartExample(PeopleBase peopleBase) {
		PeopleBaseExample example = new PeopleBaseExample();
		Criteria criteriaResult = example.or();
		if(peopleBase.getIdentity()!=null && peopleBase.getIdentity()!=""){criteriaResult=criteriaResult.andIdentityLike("%"+peopleBase.getIdentity()+"%");};
		if(peopleBase.getName()!=null && peopleBase.getName()!=""){criteriaResult=criteriaResult.andNameLike("%"+peopleBase.getName()+"%");};
		List<PeopleBase> result = peopleBaseMapperC.selectByExampleCPart(peopleBase);
		return result;
	}

	@Override
	public List<PeopleBase> queryPeoplebaseByPolygon(PeopleBase peopleBase) {
		List<PeopleBase> result = new ArrayList<>();
		Map map = new HashMap();
		List<Address> addresses = peopleBaseMapperC.queryPeopleByPolygon(peopleBase);
		List<PeopleBase> peopleBases = peopleBaseMapperC.selectByExampleC(peopleBase);
		for(int i=0;i<peopleBases.size();i++){
			String add_id = peopleBases.get(i).getAddId();
			map.put(add_id, peopleBases.get(i));
		}
		for(int i=0;i<addresses.size();i++){
			String add_id = addresses.get(i).getAddId().toString();
			if(map.containsKey(add_id)){
				result.add((PeopleBase)map.get(add_id));
			}
		}
		return result;
	}

	@Override
	public PeopleBase getPeopleBaseById(String ppId) {
		PeopleBase result = peopleBaseMapperC.selectPeopleBaseById(ppId);
		return result;
	}

	@Override
	public int updateByPrimaryKey(PeopleBase peopleBase) {
		int result = peopleBaseMapperC.updateByPrimaryKey(peopleBase);
		return result;
	}

	@Override
	public int insert(PeopleBase peopleBase) {
		int result = peopleBaseMapperC.insert(peopleBase);
		return result;
	}

	@Override
	public int deleteByPrimaryKey(String ppId){
		int result = peopleBaseMapperC.deleteByPrimaryKey(ppId);
		return result;
	}

	@Override
	public List<PeopleBase> queryPeopleByAddId(String addId) {
		List<PeopleBase> result = peopleBaseMapperC.selectPeopleBaseByAddId(addId);
		return result;
	}

	@Override
	public int batchDelete(List<String> ppIds) {
		int result = peopleBaseMapperC.batchDelete(ppIds);
		return result;
	}
}
