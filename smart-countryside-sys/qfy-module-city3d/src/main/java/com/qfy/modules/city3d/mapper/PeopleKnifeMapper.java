package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qfy.modules.city3d.po.PeopleKnife;
import com.qfy.modules.city3d.po.PeopleKnifeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@DS("postgresql")
public interface PeopleKnifeMapper {
    int countByExample(PeopleKnifeExample example);

    int deleteByExample(PeopleKnifeExample example);

    int deleteByPrimaryKey(String knifeId);

    int insert(PeopleKnife record);

    int insertSelective(PeopleKnife record);

    List<PeopleKnife> selectByExample(PeopleKnifeExample example);

    PeopleKnife selectByPrimaryKey(String knifeId);

    int updateByExampleSelective(@Param("record") PeopleKnife record, @Param("example") PeopleKnifeExample example);

    int updateByExample(@Param("record") PeopleKnife record, @Param("example") PeopleKnifeExample example);

    int updateByPrimaryKeySelective(PeopleKnife record);

    int updateByPrimaryKey(PeopleKnife record);

}
