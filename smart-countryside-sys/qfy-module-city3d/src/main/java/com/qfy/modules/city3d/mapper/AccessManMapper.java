package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qfy.modules.city3d.po.AccessMan;
import com.qfy.modules.city3d.po.AccessManExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@DS("postgresql")
public interface AccessManMapper {
    int countByExample(AccessManExample example);

    int deleteByExample(AccessManExample example);

    int deleteByPrimaryKey(String accessManId);

    int insert(AccessMan record);

    int insertSelective(AccessMan record);

    List<AccessMan> selectByExample(AccessManExample example);

    AccessMan selectByPrimaryKey(String accessManId);

    int updateByExampleSelective(@Param("record") AccessMan record, @Param("example") AccessManExample example);

    int updateByExample(@Param("record") AccessMan record, @Param("example") AccessManExample example);

    int updateByPrimaryKeySelective(AccessMan record);

    int updateByPrimaryKey(AccessMan record);
}
