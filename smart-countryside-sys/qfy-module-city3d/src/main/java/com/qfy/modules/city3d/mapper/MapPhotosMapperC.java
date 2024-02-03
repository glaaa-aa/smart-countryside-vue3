package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import java.util.List;
@DS("postgresql")
public interface MapPhotosMapperC extends MapPhotosMapper {
	public int batchDelete(List<String> photoIds);
}
