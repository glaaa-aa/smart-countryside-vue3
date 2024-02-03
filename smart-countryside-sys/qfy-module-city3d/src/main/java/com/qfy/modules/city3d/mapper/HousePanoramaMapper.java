package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qfy.modules.city3d.po.HousePanorama;
import com.qfy.modules.city3d.po.HousePanoramaExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@DS("postgresql")
public interface HousePanoramaMapper {
    int countByExample(HousePanoramaExample example);

    int deleteByExample(HousePanoramaExample example);

    int deleteByPrimaryKey(String panoramaId);

    int insert(HousePanorama record);

    int insertSelective(HousePanorama record);

    List<HousePanorama> selectByExample(HousePanoramaExample example);

    HousePanorama selectByPrimaryKey(String panoramaId);

    int updateByExampleSelective(@Param("record") HousePanorama record, @Param("example") HousePanoramaExample example);

    int updateByExample(@Param("record") HousePanorama record, @Param("example") HousePanoramaExample example);

    int updateByPrimaryKeySelective(HousePanorama record);

    int updateByPrimaryKey(HousePanorama record);
}
