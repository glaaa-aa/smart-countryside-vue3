package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qfy.modules.city3d.po.AccessCar;
import com.qfy.modules.city3d.po.AccessCarExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@DS("postgresql")
public interface AccessCarMapper {
    int countByExample(AccessCarExample example);

    int deleteByExample(AccessCarExample example);

    int deleteByPrimaryKey(String accessCarId);

    int insert(AccessCar record);

    int insertSelective(AccessCar record);

    List<AccessCar> selectByExample(AccessCarExample example);

    AccessCar selectByPrimaryKey(String accessCarId);

    int updateByExampleSelective(@Param("record") AccessCar record, @Param("example") AccessCarExample example);

    int updateByExample(@Param("record") AccessCar record, @Param("example") AccessCarExample example);

    int updateByPrimaryKeySelective(AccessCar record);

    int updateByPrimaryKey(AccessCar record);
}
