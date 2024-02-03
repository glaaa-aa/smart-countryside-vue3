package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qfy.modules.city3d.po.CompanyPanorama;
import com.qfy.modules.city3d.po.CompanyPanoramaExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@DS("postgresql")
public interface CompanyPanoramaMapper {
    int countByExample(CompanyPanoramaExample example);

    int deleteByExample(CompanyPanoramaExample example);

    int deleteByPrimaryKey(String panoramaId);

    int insert(CompanyPanorama record);

    int insertSelective(CompanyPanorama record);

    List<CompanyPanorama> selectByExample(CompanyPanoramaExample example);

    CompanyPanorama selectByPrimaryKey(String panoramaId);

    int updateByExampleSelective(@Param("record") CompanyPanorama record, @Param("example") CompanyPanoramaExample example);

    int updateByExample(@Param("record") CompanyPanorama record, @Param("example") CompanyPanoramaExample example);

    int updateByPrimaryKeySelective(CompanyPanorama record);

    int updateByPrimaryKey(CompanyPanorama record);

}
