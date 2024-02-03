package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qfy.modules.city3d.po.Datalayer;
import com.qfy.modules.city3d.po.DatalayerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@DS("postgresql")
public interface DatalayerMapper {
    int countByExample(DatalayerExample example);

    int deleteByExample(DatalayerExample example);

    int deleteByPrimaryKey(String layerId);

    int insert(Datalayer record);

    int insertSelective(Datalayer record);

    List<Datalayer> selectByExample(DatalayerExample example);

    Datalayer selectByPrimaryKey(String layerId);

    int updateByExampleSelective(@Param("record") Datalayer record, @Param("example") DatalayerExample example);

    int updateByExample(@Param("record") Datalayer record, @Param("example") DatalayerExample example);

    int updateByPrimaryKeySelective(Datalayer record);

    int updateByPrimaryKey(Datalayer record);
}
