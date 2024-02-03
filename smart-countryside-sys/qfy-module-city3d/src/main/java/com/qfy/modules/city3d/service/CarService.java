package com.qfy.modules.city3d.service;

import java.util.List;
import com.qfy.modules.city3d.po.Car;

public interface CarService {
	public List<Car> getPoliceCarList();
	public List<Car> getPoliceCarListPart(String[] carLicenses);
	public int insert(Car car);
	public int updateByPrimaryKey(Car car);
	public int deleteByPrimaryKey(String carId);
	public Car getCarAndEquipmentsByPuid(String carId);
	public List<Car> findCarByPuid(String carId);
}
