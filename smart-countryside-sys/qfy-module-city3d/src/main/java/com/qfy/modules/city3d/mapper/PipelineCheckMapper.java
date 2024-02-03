package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qfy.modules.city3d.po.PipelineCheck;
import com.qfy.modules.city3d.po.PipelineCheckExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@DS("postgresql")
public interface PipelineCheckMapper {
    int countByExample(PipelineCheckExample example);

    int deleteByExample(PipelineCheckExample example);

    int deleteByPrimaryKey(String checkId);

    int insert(PipelineCheck record);

    int insertSelective(PipelineCheck record);

    List<PipelineCheck> selectByExample(PipelineCheckExample example);

    PipelineCheck selectByPrimaryKey(String checkId);

    int updateByExampleSelective(@Param("record") PipelineCheck record, @Param("example") PipelineCheckExample example);

    int updateByExample(@Param("record") PipelineCheck record, @Param("example") PipelineCheckExample example);

    int updateByPrimaryKeySelective(PipelineCheck record);

    int updateByPrimaryKey(PipelineCheck record);
}
