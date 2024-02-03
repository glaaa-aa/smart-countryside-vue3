package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qfy.modules.city3d.po.PeopleIllegal;
import com.qfy.modules.city3d.po.PeopleIllegalExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@DS("postgresql")
public interface PeopleIllegalMapper {
    int countByExample(PeopleIllegalExample example);

    int deleteByExample(PeopleIllegalExample example);

    int deleteByPrimaryKey(String illegalId);

    int insert(PeopleIllegal record);

    int insertSelective(PeopleIllegal record);

    List<PeopleIllegal> selectByExample(PeopleIllegalExample example);

    PeopleIllegal selectByPrimaryKey(String illegalId);

    int updateByExampleSelective(@Param("record") PeopleIllegal record, @Param("example") PeopleIllegalExample example);

    int updateByExample(@Param("record") PeopleIllegal record, @Param("example") PeopleIllegalExample example);

    int updateByPrimaryKeySelective(PeopleIllegal record);

    int updateByPrimaryKey(PeopleIllegal record);
}
