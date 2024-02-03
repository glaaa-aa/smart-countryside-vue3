package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qfy.modules.city3d.po.PeopleMachine;
import com.qfy.modules.city3d.po.PeopleMachineExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@DS("postgresql")
public interface PeopleMachineMapper {
    int countByExample(PeopleMachineExample example);

    int deleteByExample(PeopleMachineExample example);

    int deleteByPrimaryKey(String machineId);

    int insert(PeopleMachine record);

    int insertSelective(PeopleMachine record);

    List<PeopleMachine> selectByExample(PeopleMachineExample example);

    PeopleMachine selectByPrimaryKey(String machineId);

    int updateByExampleSelective(@Param("record") PeopleMachine record, @Param("example") PeopleMachineExample example);

    int updateByExample(@Param("record") PeopleMachine record, @Param("example") PeopleMachineExample example);

    int updateByPrimaryKeySelective(PeopleMachine record);

    int updateByPrimaryKey(PeopleMachine record);

    List<PeopleMachine> selectPeopleMachineById(String ppId);
}
