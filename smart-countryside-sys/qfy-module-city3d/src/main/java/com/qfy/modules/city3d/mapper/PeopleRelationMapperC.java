package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qfy.modules.city3d.po.PeopleRelation;
import java.util.List;

@DS("postgresql")
public interface PeopleRelationMapperC extends PeopleRelationMapper{
	List<PeopleRelation> selectPeopleRelationById(String ppId);
}
