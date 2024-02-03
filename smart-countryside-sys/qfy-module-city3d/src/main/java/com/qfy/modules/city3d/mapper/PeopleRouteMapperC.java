package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qfy.modules.city3d.po.PeopleRoute;
import java.util.List;

@DS("postgresql")
public interface PeopleRouteMapperC extends PeopleRouteMapper{
    List<PeopleRoute> selectPeopleRoute();
}
