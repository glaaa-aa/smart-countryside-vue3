package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qfy.modules.city3d.po.SysPara;
import com.qfy.modules.city3d.po.SysParaExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@DS("postgresql")
public interface SysParaMapper {
    int countByExample(SysParaExample example);

    int deleteByExample(SysParaExample example);

    int deleteByPrimaryKey(String sysId);

    int insert(SysPara record);

    int insertSelective(SysPara record);

    List<SysPara> selectByExample(SysParaExample example);

    SysPara selectByPrimaryKey(String sysId);

    int updateByExampleSelective(@Param("record") SysPara record, @Param("example") SysParaExample example);

    int updateByExample(@Param("record") SysPara record, @Param("example") SysParaExample example);

    int updateByPrimaryKeySelective(SysPara record);

    int updateByPrimaryKey(SysPara record);
}
