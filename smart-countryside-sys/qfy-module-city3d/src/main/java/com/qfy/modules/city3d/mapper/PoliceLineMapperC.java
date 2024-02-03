package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import java.util.List;

@DS("postgresql")
public interface PoliceLineMapperC extends PoliceLineMapper {
	public int batchDelete(List<String> lIds);
}
