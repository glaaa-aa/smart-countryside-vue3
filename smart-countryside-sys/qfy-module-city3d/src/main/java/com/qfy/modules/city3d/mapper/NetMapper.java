package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qfy.modules.city3d.po.Net;
import com.qfy.modules.city3d.po.NetExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@DS("postgresql")
public interface NetMapper extends BaseMapper<Net> {
    int countByExample(NetExample example);

    int deleteByExample(NetExample example);

    int deleteByPrimaryKey(String netId);

//    int insert(Net record);

    int insertSelective(Net record);

    List<Net> selectByExample(NetExample example);

    Net selectByPrimaryKey(String netId);

    int updateByExampleSelective(@Param("record") Net record, @Param("example") NetExample example);

    int updateByExample(@Param("record") Net record, @Param("example") NetExample example);

    int updateByPrimaryKeySelective(Net record);

    int updateByPrimaryKey(Net record);

    int batchDelete(List<String> netIds);
}
