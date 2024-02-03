package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qfy.modules.city3d.po.GpsHisDataInfo;
import java.util.List;
import java.util.Map;
@DS("postgresql")
public interface GpsHisDataInfoMapper {
	List<GpsHisDataInfo> selectGpsInfosIn2Days(Map<String, Object> map);

    List<GpsHisDataInfo> selectGpsInfosInOneDay(Map<String, Object> map);

    List<GpsHisDataInfo> getTracehisdataAll7Days(Map<String, Object> map);

    List<GpsHisDataInfo> getHisDataAllDemo();
}
