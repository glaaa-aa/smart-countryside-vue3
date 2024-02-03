package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qfy.modules.city3d.po.Address;
import com.qfy.modules.city3d.po.PeopleBase;
import java.util.List;

@DS("postgresql")
public interface PeopleBaseMapperC extends PeopleBaseMapper {
	public List<PeopleBase> selectByExampleC(PeopleBase peopleBase);
	public List<PeopleBase> selectByExampleCPart(PeopleBase peopleBase);
	public PeopleBase selectPeopleBaseById(String ppId);
	public List<PeopleBase> selectPeopleBaseByAddId(String addId);
	public List<Address> queryPeopleByPolygon(PeopleBase peopleBase);
	public int batchDelete(List<String> ppIds);
}
