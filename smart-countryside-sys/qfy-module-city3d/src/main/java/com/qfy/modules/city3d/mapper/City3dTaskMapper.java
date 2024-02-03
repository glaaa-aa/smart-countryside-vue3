package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qfy.modules.city3d.po.City3dTask;
import com.qfy.modules.city3d.po.TaskExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@DS("postgresql")
public interface City3dTaskMapper {
    int countByExample(TaskExample example);

    int deleteByExample(TaskExample example);

    int deleteByPrimaryKey(String id);

    int insert(City3dTask record);

    int insertSelective(City3dTask record);

    List<City3dTask> selectByExample(TaskExample example);

    City3dTask selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") City3dTask record, @Param("example") TaskExample example);

    int updateByExample(@Param("record") City3dTask record, @Param("example") TaskExample example);

    int updateByPrimaryKeySelective(City3dTask record);

    int updateByPrimaryKey(City3dTask record);
}
