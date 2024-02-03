package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qfy.modules.city3d.po.NetMan;
import com.qfy.modules.city3d.po.NetManExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@DS("postgresql")
public interface NetManMapper {
    int countByExample(NetManExample example);

    int deleteByExample(NetManExample example);

    int deleteByPrimaryKey(String netManId);

    int insert(NetMan record);

    int insertSelective(NetMan record);

    List<NetMan> selectByExample(NetManExample example);

    NetMan selectByPrimaryKey(String netManId);

    int updateByExampleSelective(@Param("record") NetMan record, @Param("example") NetManExample example);

    int updateByExample(@Param("record") NetMan record, @Param("example") NetManExample example);

    int updateByPrimaryKeySelective(NetMan record);

    int updateByPrimaryKey(NetMan record);
}
