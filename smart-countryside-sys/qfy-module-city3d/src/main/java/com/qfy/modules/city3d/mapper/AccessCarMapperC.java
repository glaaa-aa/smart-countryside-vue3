package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import java.util.List;

@DS("postgresql")
public interface AccessCarMapperC extends AccessCarMapper {
	public int batchDelete(List<String> accessCarIds);
}
