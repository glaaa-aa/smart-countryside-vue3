package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qfy.modules.city3d.po.DutyVideo;
import com.qfy.modules.city3d.po.DutyVideoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@DS("postgresql")
public interface DutyVideoMapper {
    int countByExample(DutyVideoExample example);

    int deleteByExample(DutyVideoExample example);

    int deleteByPrimaryKey(String dutyVideoId);

    int insert(DutyVideo record);

    int insertSelective(DutyVideo record);

    List<DutyVideo> selectByExample(DutyVideoExample example);

    DutyVideo selectByPrimaryKey(String dutyVideoId);

    int updateByExampleSelective(@Param("record") DutyVideo record, @Param("example") DutyVideoExample example);

    int updateByExample(@Param("record") DutyVideo record, @Param("example") DutyVideoExample example);

    int updateByPrimaryKeySelective(DutyVideo record);

    int updateByPrimaryKey(DutyVideo record);
}
