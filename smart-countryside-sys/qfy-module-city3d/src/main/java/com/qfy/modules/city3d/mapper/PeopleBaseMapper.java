package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qfy.modules.city3d.po.PeopleBase;
import com.qfy.modules.city3d.po.PeopleBaseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@DS("postgresql")
public interface PeopleBaseMapper {
    int countByExample(PeopleBaseExample example);

    int deleteByExample(PeopleBaseExample example);

    int deleteByPrimaryKey(String ppId);

    int insert(PeopleBase record);

    int insertSelective(PeopleBase record);

    List<PeopleBase> selectByExample(PeopleBaseExample example);

    PeopleBase selectByPrimaryKey(String ppId);

    int updateByExampleSelective(@Param("record") PeopleBase record, @Param("example") PeopleBaseExample example);

    int updateByExample(@Param("record") PeopleBase record, @Param("example") PeopleBaseExample example);

    int updateByPrimaryKeySelective(PeopleBase record);

    int updateByPrimaryKey(PeopleBase record);
}
