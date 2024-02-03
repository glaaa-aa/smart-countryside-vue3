package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qfy.modules.city3d.po.House;
import java.util.List;

@DS("postgresql")
public interface HouseMapperC extends HouseMapper {
	public House selectHouseById(String hId);
	public List<House> selectByExampleH(House house);
	public List<House> selectHouseByAddId(String addId);
	public int batchDelete(List<String> houseIds);
}
