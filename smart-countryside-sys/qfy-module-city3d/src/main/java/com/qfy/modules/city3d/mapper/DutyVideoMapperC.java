package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qfy.modules.city3d.po.DutyVideo;
import java.util.List;

@DS("postgresql")
public interface DutyVideoMapperC extends DutyVideoMapper {
	List<DutyVideo> getDutyVideoAll();
}
