package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import java.util.List;

@DS("postgresql")
public interface BuildingMapperC extends BuildingMapper {
	public int batchDelete(List<String> buildingIds);
}
