package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qfy.modules.city3d.po.PeopleDrugDiscover;
import com.qfy.modules.city3d.po.PeopleDrugDiscoverExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@DS("postgresql")
public interface PeopleDrugDiscoverMapper {
    int countByExample(PeopleDrugDiscoverExample example);

    int deleteByExample(PeopleDrugDiscoverExample example);

    int deleteByPrimaryKey(String drugDiscoverId);

    int insert(PeopleDrugDiscover record);

    int insertSelective(PeopleDrugDiscover record);

    List<PeopleDrugDiscover> selectByExample(PeopleDrugDiscoverExample example);

    PeopleDrugDiscover selectByPrimaryKey(String drugDiscoverId);

    int updateByExampleSelective(@Param("record") PeopleDrugDiscover record, @Param("example") PeopleDrugDiscoverExample example);

    int updateByExample(@Param("record") PeopleDrugDiscover record, @Param("example") PeopleDrugDiscoverExample example);

    int updateByPrimaryKeySelective(PeopleDrugDiscover record);

    int updateByPrimaryKey(PeopleDrugDiscover record);
}
