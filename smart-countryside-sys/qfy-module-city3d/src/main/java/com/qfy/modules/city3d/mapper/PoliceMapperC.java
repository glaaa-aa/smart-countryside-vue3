package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qfy.modules.city3d.po.Police;
import java.util.List;

@DS("postgresql")
public interface PoliceMapperC extends PoliceMapper{
	public List<Police> getPoliceRealDateWithManPos();
	public Police getPoliceAndEquipmentsByPuid(String deviceNum);
}
