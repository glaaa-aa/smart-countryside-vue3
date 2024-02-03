package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qfy.modules.city3d.po.AttributeData;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@DS("postgresql")
public interface AttributeDataMapperC extends AttributeDataMapper {
	public List<AttributeData> getAttributeDataAndCoverageByCoverageId(String coverageId);
	public List<AttributeData> getAttributeDataAddressByCoverageIdAndAddName(@Param("coverageId")String coverageId,@Param("addName")String addName);
}
