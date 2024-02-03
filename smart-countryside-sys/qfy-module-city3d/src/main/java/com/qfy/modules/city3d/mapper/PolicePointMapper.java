package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qfy.modules.city3d.po.PolicePoint;
import com.qfy.modules.city3d.po.PolicePointExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@DS("postgresql")
public interface PolicePointMapper {
    int countByExample(PolicePointExample example);

    int deleteByExample(PolicePointExample example);

    int deleteByPrimaryKey(String pId);

    int insert(PolicePoint record);

    int insertSelective(PolicePoint record);

    List<PolicePoint> selectByExample(PolicePointExample example);

    PolicePoint selectByPrimaryKey(String pId);

    int updateByExampleSelective(@Param("record") PolicePoint record, @Param("example") PolicePointExample example);

    int updateByExample(@Param("record") PolicePoint record, @Param("example") PolicePointExample example);

    int updateByPrimaryKeySelective(PolicePoint record);

    int updateByPrimaryKey(PolicePoint record);
}
