package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qfy.modules.city3d.po.Eplan;
import com.qfy.modules.city3d.po.EplanExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
@DS("postgresql")
public interface EplanMapper {
    int countByExample(EplanExample example);

    int deleteByExample(EplanExample example);

    int deleteByPrimaryKey(String eplanId);

    int insert(Eplan record);

    int insertSelective(Eplan record);

    List<Eplan> selectByExample(EplanExample example);

    Eplan selectByPrimaryKey(String eplanId);

    int updateByExampleSelective(@Param("record") Eplan record, @Param("example") EplanExample example);

    int updateByExample(@Param("record") Eplan record, @Param("example") EplanExample example);

    int updateByPrimaryKeySelective(Eplan record);

    int updateByPrimaryKey(Eplan record);
}
