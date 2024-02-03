package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qfy.modules.city3d.po.Duty;
import com.qfy.modules.city3d.po.DutyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@DS("postgresql")
public interface DutyMapper {
    int countByExample(DutyExample example);

    int deleteByExample(DutyExample example);

    int deleteByPrimaryKey(String dutyId);

    int insert(Duty record);

    int insertSelective(Duty record);

    List<Duty> selectByExample(DutyExample example);

    Duty selectByPrimaryKey(String dutyId);

    int updateByExampleSelective(@Param("record") Duty record, @Param("example") DutyExample example);

    int updateByExample(@Param("record") Duty record, @Param("example") DutyExample example);

    int updateByPrimaryKeySelective(Duty record);

    int updateByPrimaryKey(Duty record);
}
