package com.qfy.modules.city3d.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.qfy.modules.city3d.po.SysFunc;
import com.qfy.modules.city3d.po.SysFuncExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

//@DS("postgresql")
public interface SysFuncMapper extends BaseMapper<SysFunc> {
    int countByExample(SysFuncExample example);

    int deleteByExample(SysFuncExample example);

    int deleteByPrimaryKey(String funcId);

//    int insert(SysFunc record);

    int insertSelective(SysFunc record);

    List<SysFunc> selectByExample(SysFuncExample example);

    SysFunc selectByPrimaryKey(String funcId);

    int updateByExampleSelective(@Param("record") SysFunc record, @Param("example") SysFuncExample example);

    int updateByExample(@Param("record") SysFunc record, @Param("example") SysFuncExample example);

    int updateByPrimaryKeySelective(SysFunc record);

    int updateByPrimaryKey(SysFunc record);

    @Select("select a.* from sys_func a left join sys_role_func b on a.func_id = b.func_id ${ew.customSqlSegment}")
    List<SysFunc> queryFuncByRoleId(@Param(Constants.WRAPPER) Wrapper wrapper);
}
