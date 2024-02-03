package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qfy.modules.city3d.po.PeopleRoute;
import com.qfy.modules.city3d.po.PeopleRouteExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@DS("postgresql")
public interface PeopleRouteMapper {
    int countByExample(PeopleRouteExample example);

    int deleteByExample(PeopleRouteExample example);

    int deleteByPrimaryKey(String routeId);

    int insert(PeopleRoute record);

    int insertSelective(PeopleRoute record);

    List<PeopleRoute> selectByExample(PeopleRouteExample example);

    PeopleRoute selectByPrimaryKey(String routeId);

    int updateByExampleSelective(@Param("record") PeopleRoute record, @Param("example") PeopleRouteExample example);

    int updateByExample(@Param("record") PeopleRoute record, @Param("example") PeopleRouteExample example);

    int updateByPrimaryKeySelective(PeopleRoute record);

    int updateByPrimaryKey(PeopleRoute record);
}
