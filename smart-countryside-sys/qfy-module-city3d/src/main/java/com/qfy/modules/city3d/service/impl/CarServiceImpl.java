package com.qfy.modules.city3d.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.qfy.modules.city3d.mapper.CarMapperC;
import com.qfy.modules.city3d.po.Car;
import com.qfy.modules.city3d.po.CarExample;
import com.qfy.modules.city3d.service.CarService;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl implements CarService {
	@Autowired
	private CarMapperC carMapperC;

	@Override
	public List<Car> getPoliceCarList() {
		List<Car> result = carMapperC.selectByExample(null);
		return result;
	}

	@Override
	public List<Car> getPoliceCarListPart(String[] carLicenses) {
		List<Car> result = carMapperC.getPoliceCarListPart(carLicenses);
		return result;
	}

	@Override
	public int insert(Car car) {
		int result = carMapperC.insert(car);
		return result;
	}

	@Override
	public int updateByPrimaryKey(Car car) {
		int result = carMapperC.updateByPrimaryKey(car);
		return result;
	}

	@Override
	public int deleteByPrimaryKey(String carId) {
		int result = carMapperC.deleteByPrimaryKey(carId);
		return result;
	}

	@Override
	public Car getCarAndEquipmentsByPuid(String carId) {
		Car result = carMapperC.getCarAndEquipmentsByPuid(carId);
		return result;
	}

	@Override
	public List<Car> findCarByPuid(String carId) {
		CarExample example = new CarExample();
		example.or().andCarIdEqualTo(carId);
		List<Car> result =  carMapperC.selectByExample(example);
		return result;
	}
}
