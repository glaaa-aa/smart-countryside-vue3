package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qfy.modules.city3d.po.Wall;
import com.qfy.modules.city3d.po.WallExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@DS("postgresql")
public interface WallMapper {
    int countByExample(WallExample example);

    int deleteByExample(WallExample example);

    int deleteByPrimaryKey(String wallId);

    int insert(Wall record);

    int insertSelective(Wall record);

    List<Wall> selectByExample(WallExample example);

    Wall selectByPrimaryKey(String wallId);

    int updateByExampleSelective(@Param("record") Wall record, @Param("example") WallExample example);

    int updateByExample(@Param("record") Wall record, @Param("example") WallExample example);

    int updateByPrimaryKeySelective(Wall record);

    int updateByPrimaryKey(Wall record);
}
