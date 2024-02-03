package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qfy.modules.city3d.po.City3dTask;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@DS("postgresql")
public interface TaskMapperC extends City3dTaskMapper {
	public int selectTaskCountToday();
	public List<City3dTask> selectTaskToday();
	//public List<City3dTask> selectTaskNextDays(@Param("dayt") String dayt,@Param("dayn") String dayn);
	public List<City3dTask> selectTaskNextDays(@Param("dateStr")String dateStr, @Param("weekStr")String weekStr);
}
