package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qfy.modules.city3d.po.VehicleRealData;
import com.qfy.modules.city3d.po.VehicleRealDataExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@DS("postgresql")
public interface VehicleRealDataMapper {
	int countByExample(VehicleRealDataExample example);

    int deleteByExample(VehicleRealDataExample example);

    int deleteByPrimaryKey(Integer iD);

    int insert(VehicleRealData record);

    int insertSelective(VehicleRealData record);

    List<VehicleRealData> selectByExample(VehicleRealDataExample example);

    VehicleRealData selectByPrimaryKey(Integer iD);

    int updateByExampleSelective(@Param("record") VehicleRealData record, @Param("example") VehicleRealDataExample example);

    int updateByExample(@Param("record") VehicleRealData record, @Param("example") VehicleRealDataExample example);

    int updateByPrimaryKeySelective(VehicleRealData record);

    int updateByPrimaryKey(VehicleRealData record);
}
