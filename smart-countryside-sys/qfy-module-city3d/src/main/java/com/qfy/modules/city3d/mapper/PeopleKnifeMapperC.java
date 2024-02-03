package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qfy.modules.city3d.po.PeopleKnife;
import java.util.List;

@DS("postgresql")
public interface PeopleKnifeMapperC extends PeopleKnifeMapper {
	public List<PeopleKnife> selectPeopleKnifeAll();
	public int batchDelete(List<String> knifeIds);
}
