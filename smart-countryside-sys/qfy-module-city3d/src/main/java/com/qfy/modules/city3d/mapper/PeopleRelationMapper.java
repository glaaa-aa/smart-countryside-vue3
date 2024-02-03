package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qfy.modules.city3d.po.PeopleRelation;
import com.qfy.modules.city3d.po.PeopleRelationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@DS("postgresql")
public interface PeopleRelationMapper {
    int countByExample(PeopleRelationExample example);

    int deleteByExample(PeopleRelationExample example);

    int deleteByPrimaryKey(String relationId);

    int insert(PeopleRelation record);

    int insertSelective(PeopleRelation record);

    List<PeopleRelation> selectByExample(PeopleRelationExample example);

    PeopleRelation selectByPrimaryKey(String relationId);

    int updateByExampleSelective(@Param("record") PeopleRelation record, @Param("example") PeopleRelationExample example);

    int updateByExample(@Param("record") PeopleRelation record, @Param("example") PeopleRelationExample example);

    int updateByPrimaryKeySelective(PeopleRelation record);

    int updateByPrimaryKey(PeopleRelation record);

}
