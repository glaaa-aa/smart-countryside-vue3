package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qfy.modules.city3d.po.PeopleMachine;
import java.util.List;

@DS("postgresql")
public interface PeopleMachineMapperC extends PeopleMachineMapper {
	public List<PeopleMachine> selectPeopleMachineAll();
    public int batchDelete(List<String> machineIds);
}
