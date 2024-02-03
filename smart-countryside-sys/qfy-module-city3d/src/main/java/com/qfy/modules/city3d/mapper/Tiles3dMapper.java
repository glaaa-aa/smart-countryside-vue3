package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qfy.modules.city3d.po.Tiles3d;
import com.qfy.modules.city3d.po.Tiles3dExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@DS("postgresql")
public interface Tiles3dMapper extends BaseMapper<Tiles3d> {
    int countByExample(Tiles3dExample example);

    int deleteByExample(Tiles3dExample example);

    int deleteByPrimaryKey(String tilesId);

//    int insert(Tiles3d record);

    int insertSelective(Tiles3d record);

    List<Tiles3d> selectByExample(Tiles3dExample example);

    Tiles3d selectByPrimaryKey(String tilesId);

    int updateByExampleSelective(@Param("record") Tiles3d record, @Param("example") Tiles3dExample example);

    int updateByExample(@Param("record") Tiles3d record, @Param("example") Tiles3dExample example);

    int updateByPrimaryKeySelective(Tiles3d record);

    int updateByPrimaryKey(Tiles3d record);
}
