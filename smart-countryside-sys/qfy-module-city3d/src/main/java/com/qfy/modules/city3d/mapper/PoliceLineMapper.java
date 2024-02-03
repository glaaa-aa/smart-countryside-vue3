package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qfy.modules.city3d.po.PoliceLine;
import com.qfy.modules.city3d.po.PoliceLineExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@DS("postgresql")
public interface PoliceLineMapper {
    int countByExample(PoliceLineExample example);

    int deleteByExample(PoliceLineExample example);

    int deleteByPrimaryKey(String lId);

    int insert(PoliceLine record);

    int insertSelective(PoliceLine record);

    List<PoliceLine> selectByExample(PoliceLineExample example);

    PoliceLine selectByPrimaryKey(String lId);

    int updateByExampleSelective(@Param("record") PoliceLine record, @Param("example") PoliceLineExample example);

    int updateByExample(@Param("record") PoliceLine record, @Param("example") PoliceLineExample example);

    int updateByPrimaryKeySelective(PoliceLine record);

    int updateByPrimaryKey(PoliceLine record);
}
