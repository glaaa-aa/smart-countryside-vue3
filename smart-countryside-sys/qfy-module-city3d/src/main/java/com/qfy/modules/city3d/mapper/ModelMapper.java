package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qfy.modules.city3d.po.Model;
import com.qfy.modules.city3d.po.ModelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@DS("postgresql")
public interface ModelMapper {
    int countByExample(ModelExample example);

    int deleteByExample(ModelExample example);

    int deleteByPrimaryKey(String modelId);

    int insert(Model record);

    int insertSelective(Model record);

    List<Model> selectByExample(ModelExample example);

    Model selectByPrimaryKey(String modelId);

    int updateByExampleSelective(@Param("record") Model record, @Param("example") ModelExample example);

    int updateByExample(@Param("record") Model record, @Param("example") ModelExample example);

    int updateByPrimaryKeySelective(Model record);

    int updateByPrimaryKey(Model record);
}
