package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qfy.modules.city3d.po.ManPos;
import java.util.List;
import java.util.Map;
@DS("postgresql")
public interface ManPosMapperC extends ManPosMapper{
	public List<ManPos> getManPosWithNameAndOrigin(String[] deviceNums);
	public List<ManPos> getTraceManPos2Days(Map<String, String> params);
	public List<ManPos> getTraceManPosInOneDay(Map<String, String> params);
}
