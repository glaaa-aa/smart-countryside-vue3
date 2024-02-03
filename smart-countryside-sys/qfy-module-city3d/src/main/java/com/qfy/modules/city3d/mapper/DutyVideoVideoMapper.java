package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qfy.modules.city3d.po.DutyVideoVideo;
import com.qfy.modules.city3d.po.DutyVideoVideoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@DS("postgresql")
public interface DutyVideoVideoMapper {
    int countByExample(DutyVideoVideoExample example);

    int deleteByExample(DutyVideoVideoExample example);

    int deleteByPrimaryKey(String dutyVideoVideoId);

    int insert(DutyVideoVideo record);

    int insertSelective(DutyVideoVideo record);

    List<DutyVideoVideo> selectByExample(DutyVideoVideoExample example);

    DutyVideoVideo selectByPrimaryKey(String dutyVideoVideoId);

    int updateByExampleSelective(@Param("record") DutyVideoVideo record, @Param("example") DutyVideoVideoExample example);

    int updateByExample(@Param("record") DutyVideoVideo record, @Param("example") DutyVideoVideoExample example);

    int updateByPrimaryKeySelective(DutyVideoVideo record);

    int updateByPrimaryKey(DutyVideoVideo record);
}
