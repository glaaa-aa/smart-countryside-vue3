package com.qfy.modules.city3d.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qfy.modules.city3d.po.SysRoleTiles3d;
import com.qfy.modules.city3d.po.SysRoleTiles3dExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

//@DS("postgresql")
public interface SysRoleTiles3dMapper extends BaseMapper<SysRoleTiles3d> {
    int countByExample(SysRoleTiles3dExample example);

    int deleteByExample(SysRoleTiles3dExample example);

    int deleteByPrimaryKey(String roleTilesId);

//    int insert(SysRoleTiles3d record);

    int insertSelective(SysRoleTiles3d record);

    List<SysRoleTiles3d> selectByExample(SysRoleTiles3dExample example);

    SysRoleTiles3d selectByPrimaryKey(String roleTilesId);

    int updateByExampleSelective(@Param("record") SysRoleTiles3d record, @Param("example") SysRoleTiles3dExample example);

    int updateByExample(@Param("record") SysRoleTiles3d record, @Param("example") SysRoleTiles3dExample example);

    int updateByPrimaryKeySelective(SysRoleTiles3d record);

    int updateByPrimaryKey(SysRoleTiles3d record);
}
