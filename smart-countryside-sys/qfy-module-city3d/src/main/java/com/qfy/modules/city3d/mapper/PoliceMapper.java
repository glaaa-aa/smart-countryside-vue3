package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qfy.modules.city3d.po.Police;
import com.qfy.modules.city3d.po.PoliceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@DS("postgresql")
public interface PoliceMapper {
    int countByExample(PoliceExample example);

    int deleteByExample(PoliceExample example);

    int deleteByPrimaryKey(String policeId);

    int insert(Police record);

    int insertSelective(Police record);

    List<Police> selectByExample(PoliceExample example);

    Police selectByPrimaryKey(String policeId);

    int updateByExampleSelective(@Param("record") Police record, @Param("example") PoliceExample example);

    int updateByExample(@Param("record") Police record, @Param("example") PoliceExample example);

    int updateByPrimaryKeySelective(Police record);

    int updateByPrimaryKey(Police record);
}
