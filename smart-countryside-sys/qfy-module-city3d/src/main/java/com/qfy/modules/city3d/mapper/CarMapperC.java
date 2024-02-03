package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qfy.modules.city3d.po.Car;
import java.util.List;

@DS("postgresql")
public interface CarMapperC extends CarMapper{
	public List<Car> getPoliceCarListPart(String[] carLicenses);
	public Car getCarAndEquipmentsByPuid(String carId);
}
