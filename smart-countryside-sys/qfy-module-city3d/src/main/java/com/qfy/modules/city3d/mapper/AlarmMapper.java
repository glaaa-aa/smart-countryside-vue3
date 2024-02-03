package com.qfy.modules.city3d.mapper;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.qfy.modules.city3d.po.Alarm;
import com.qfy.modules.city3d.po.AlarmExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@DS("postgresql")
public interface AlarmMapper {
    int countByExample(AlarmExample example);

    int deleteByExample(AlarmExample example);

    int deleteByPrimaryKey(String alarmId);

    int insert(Alarm record);

    int insertSelective(Alarm record);

    List<Alarm> selectByExample(AlarmExample example);

    Alarm selectByPrimaryKey(String alarmId);

    int updateByExampleSelective(@Param("record") Alarm record, @Param("example") AlarmExample example);

    int updateByExample(@Param("record") Alarm record, @Param("example") AlarmExample example);

    int updateByPrimaryKeySelective(Alarm record);

    int updateByPrimaryKey(Alarm record);
}
