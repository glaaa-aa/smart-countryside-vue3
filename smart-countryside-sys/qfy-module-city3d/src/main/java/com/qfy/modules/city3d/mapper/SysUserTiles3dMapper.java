package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qfy.modules.city3d.po.SysUserTiles3d;
import com.qfy.modules.city3d.po.SysUserTiles3dExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@DS("postgresql")
public interface SysUserTiles3dMapper {
    int countByExample(SysUserTiles3dExample example);

    int deleteByExample(SysUserTiles3dExample example);

    int deleteByPrimaryKey(String userTilesId);

    int insert(SysUserTiles3d record);

    int insertSelective(SysUserTiles3d record);

    List<SysUserTiles3d> selectByExample(SysUserTiles3dExample example);

    SysUserTiles3d selectByPrimaryKey(String userTilesId);

    int updateByExampleSelective(@Param("record") SysUserTiles3d record, @Param("example") SysUserTiles3dExample example);

    int updateByExample(@Param("record") SysUserTiles3d record, @Param("example") SysUserTiles3dExample example);

    int updateByPrimaryKeySelective(SysUserTiles3d record);

    int updateByPrimaryKey(SysUserTiles3d record);
}
