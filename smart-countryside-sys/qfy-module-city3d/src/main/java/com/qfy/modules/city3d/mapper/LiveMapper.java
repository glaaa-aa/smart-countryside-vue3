package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qfy.modules.city3d.po.Live;
import com.qfy.modules.city3d.po.LiveExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@DS("postgresql")
public interface LiveMapper {
    int countByExample(LiveExample example);

    int deleteByExample(LiveExample example);

    int deleteByPrimaryKey(String pid);

    int insert(Live record);

    int insertSelective(Live record);

    List<Live> selectByExample(LiveExample example);

    Live selectByPrimaryKey(String pid);

    int updateByExampleSelective(@Param("record") Live record, @Param("example") LiveExample example);

    int updateByExample(@Param("record") Live record, @Param("example") LiveExample example);

    int updateByPrimaryKeySelective(Live record);

    int updateByPrimaryKey(Live record);
}
