package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import java.util.List;

@DS("postgresql")
public interface AlertMapperC extends AlertMapper {
	public int batchDelete(List<String> ids);
}
