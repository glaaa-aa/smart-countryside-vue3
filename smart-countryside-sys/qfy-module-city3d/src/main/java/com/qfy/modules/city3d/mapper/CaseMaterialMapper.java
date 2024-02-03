package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qfy.modules.city3d.po.CaseMaterial;
import com.qfy.modules.city3d.po.CaseMaterialExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@DS("postgresql")
public interface CaseMaterialMapper {
    int countByExample(CaseMaterialExample example);

    int deleteByExample(CaseMaterialExample example);

    int deleteByPrimaryKey(String caseMaterialId);

    int insert(CaseMaterial record);

    int insertSelective(CaseMaterial record);

    List<CaseMaterial> selectByExample(CaseMaterialExample example);

    CaseMaterial selectByPrimaryKey(String caseMaterialId);

    int updateByExampleSelective(@Param("record") CaseMaterial record, @Param("example") CaseMaterialExample example);

    int updateByExample(@Param("record") CaseMaterial record, @Param("example") CaseMaterialExample example);

    int updateByPrimaryKeySelective(CaseMaterial record);

    int updateByPrimaryKey(CaseMaterial record);
}
