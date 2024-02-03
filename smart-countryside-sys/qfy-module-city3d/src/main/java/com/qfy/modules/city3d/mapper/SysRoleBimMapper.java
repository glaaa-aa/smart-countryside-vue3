package com.qfy.modules.city3d.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qfy.modules.city3d.po.SysRoleBim;
import com.qfy.modules.city3d.po.SysRoleBimExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

//@DS("postgresql")
public interface SysRoleBimMapper extends BaseMapper<SysRoleBim> {
    int countByExample(SysRoleBimExample example);

    int deleteByExample(SysRoleBimExample example);

    int deleteByPrimaryKey(String roleBimId);

//    int insert(SysRoleBim record);

    int insertSelective(SysRoleBim record);

    List<SysRoleBim> selectByExample(SysRoleBimExample example);

    SysRoleBim selectByPrimaryKey(String roleBimId);

    int updateByExampleSelective(@Param("record") SysRoleBim record, @Param("example") SysRoleBimExample example);

    int updateByExample(@Param("record") SysRoleBim record, @Param("example") SysRoleBimExample example);

    int updateByPrimaryKeySelective(SysRoleBim record);

    int updateByPrimaryKey(SysRoleBim record);
}
