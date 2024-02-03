package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qfy.modules.city3d.po.ManPos;
import com.qfy.modules.city3d.po.ManPosExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
@DS("postgresql")
public interface ManPosMapper {
    int countByExample(ManPosExample example);

    int deleteByExample(ManPosExample example);

    int deleteByPrimaryKey(String realId);

    int insert(ManPos record);

    int insertSelective(ManPos record);

    List<ManPos> selectByExample(ManPosExample example);

    ManPos selectByPrimaryKey(String realId);

    int updateByExampleSelective(@Param("record") ManPos record, @Param("example") ManPosExample example);

    int updateByExample(@Param("record") ManPos record, @Param("example") ManPosExample example);

    int updateByPrimaryKeySelective(ManPos record);

    int updateByPrimaryKey(ManPos record);
}
