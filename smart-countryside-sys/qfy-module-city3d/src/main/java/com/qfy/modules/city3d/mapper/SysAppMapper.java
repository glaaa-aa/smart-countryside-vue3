package com.qfy.modules.city3d.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qfy.modules.city3d.po.SysApp;
import com.qfy.modules.city3d.po.SysAppExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

//@DS("postgresql")
public interface SysAppMapper extends BaseMapper<SysApp> {
    int countByExample(SysAppExample example);

    int deleteByExample(SysAppExample example);

    int deleteByPrimaryKey(String appId);

    int insert(SysApp record);

    int insertSelective(SysApp record);

    List<SysApp> selectByExample(SysAppExample example);

    SysApp selectByPrimaryKey(String appId);

    int updateByExampleSelective(@Param("record") SysApp record, @Param("example") SysAppExample example);

    int updateByExample(@Param("record") SysApp record, @Param("example") SysAppExample example);

    int updateByPrimaryKeySelective(SysApp record);

    int updateByPrimaryKey(SysApp record);
}
