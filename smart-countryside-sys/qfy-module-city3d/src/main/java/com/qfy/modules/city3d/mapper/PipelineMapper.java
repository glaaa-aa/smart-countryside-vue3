package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qfy.modules.city3d.po.Pipeline;
import com.qfy.modules.city3d.po.PipelineExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@DS("postgresql")
public interface PipelineMapper {
    int countByExample(PipelineExample example);

    int deleteByExample(PipelineExample example);

    int deleteByPrimaryKey(String pipeId);

    int insert(Pipeline record);

    int insertSelective(Pipeline record);

    List<Pipeline> selectByExample(PipelineExample example);

    Pipeline selectByPrimaryKey(String pipeId);

    int updateByExampleSelective(@Param("record") Pipeline record, @Param("example") PipelineExample example);

    int updateByExample(@Param("record") Pipeline record, @Param("example") PipelineExample example);

    int updateByPrimaryKeySelective(Pipeline record);

    int updateByPrimaryKey(Pipeline record);
}
