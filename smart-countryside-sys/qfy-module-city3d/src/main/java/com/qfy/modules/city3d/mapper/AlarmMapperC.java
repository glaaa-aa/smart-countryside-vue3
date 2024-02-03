package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qfy.modules.city3d.po.Alarm;
import com.qfy.modules.city3d.po.AlarmExample;
import java.util.List;

@DS("postgresql")
public interface AlarmMapperC extends AlarmMapper {
    List<Alarm> selectAllByExample(AlarmExample example);
    public int batchDelete(List<String> alarmIds);
}
