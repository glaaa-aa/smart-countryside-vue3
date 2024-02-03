package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import java.util.List;

@DS("postgresql")
public interface StreetMapperC extends StreetMapper {
	public int batchDelete(List<String> streetIds);
}
