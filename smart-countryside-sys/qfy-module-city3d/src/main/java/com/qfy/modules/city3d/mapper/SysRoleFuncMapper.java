package com.qfy.modules.city3d.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qfy.modules.city3d.po.SysRoleFunc;
import com.qfy.modules.city3d.po.SysRoleFuncExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

//@DS("postgresql")
public interface SysRoleFuncMapper  extends BaseMapper<SysRoleFunc> {
    int countByExample(SysRoleFuncExample example);

    int deleteByExample(SysRoleFuncExample example);

    int deleteByPrimaryKey(String roleFuncId);

//    int insert(SysRoleFunc record);

    int insertSelective(SysRoleFunc record);

    List<SysRoleFunc> selectByExample(SysRoleFuncExample example);

    SysRoleFunc selectByPrimaryKey(String roleFuncId);

    int updateByExampleSelective(@Param("record") SysRoleFunc record, @Param("example") SysRoleFuncExample example);

    int updateByExample(@Param("record") SysRoleFunc record, @Param("example") SysRoleFuncExample example);

    int updateByPrimaryKeySelective(SysRoleFunc record);

    int updateByPrimaryKey(SysRoleFunc record);
}
