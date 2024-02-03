package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qfy.modules.city3d.po.Attribute;
import java.util.List;

@DS("postgresql")
public interface AttributeMapperC extends AttributeMapper {
	public List<Attribute> getAttributeAll();
}
