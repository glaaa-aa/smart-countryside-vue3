package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qfy.modules.city3d.po.Coverage;
import com.qfy.modules.city3d.po.CoverageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@DS("postgresql")
public interface CoverageMapper {
    int countByExample(CoverageExample example);

    int deleteByExample(CoverageExample example);

    int deleteByPrimaryKey(String id);

    int insert(Coverage record);

    int insertSelective(Coverage record);

    List<Coverage> selectByExample(CoverageExample example);

    Coverage selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Coverage record, @Param("example") CoverageExample example);

    int updateByExample(@Param("record") Coverage record, @Param("example") CoverageExample example);

    int updateByPrimaryKeySelective(Coverage record);

    int updateByPrimaryKey(Coverage record);
}
