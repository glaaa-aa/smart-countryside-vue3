package com.qfy.modules.city3d.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qfy.modules.city3d.po.SysRole;
import com.qfy.modules.city3d.po.SysRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

//@DS("postgresql")
public interface SysRoleMapperB extends BaseMapper<SysRole> {
    int countByExample(SysRoleExample example);

    int deleteByExample(SysRoleExample example);

    int deleteByPrimaryKey(String roleId);

//    int insert(SysRole record);

    int insertSelective(SysRole record);

    List<SysRole> selectByExample(SysRoleExample example);

    SysRole selectByPrimaryKey(String roleId);

    int updateByExampleSelective(@Param("record") SysRole record, @Param("example") SysRoleExample example);

    int updateByExample(@Param("record") SysRole record, @Param("example") SysRoleExample example);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

    List<SysRole> getAllandFunc();
    SysRole getAllandFuncById(String roleId);
    SysRole getAllandFuncByIdandSrc(SysRole sysRole);
    SysRole getAllandTiles3dById(String roleId);
    SysRole getAllandBimById(String roleId);
}
