package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qfy.modules.city3d.po.BimWaterFlow;
import com.qfy.modules.city3d.po.BimWaterFlowExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@DS("postgresql")
public interface BimWaterFlowMapper {
    int countByExample(BimWaterFlowExample example);

    int deleteByExample(BimWaterFlowExample example);

    int deleteByPrimaryKey(String bimFlowId);

    int insert(BimWaterFlow record);

    int insertSelective(BimWaterFlow record);

    List<BimWaterFlow> selectByExample(BimWaterFlowExample example);

    BimWaterFlow selectByPrimaryKey(String bimFlowId);

    int updateByExampleSelective(@Param("record") BimWaterFlow record, @Param("example") BimWaterFlowExample example);

    int updateByExample(@Param("record") BimWaterFlow record, @Param("example") BimWaterFlowExample example);

    int updateByPrimaryKeySelective(BimWaterFlow record);

    int updateByPrimaryKey(BimWaterFlow record);
}
