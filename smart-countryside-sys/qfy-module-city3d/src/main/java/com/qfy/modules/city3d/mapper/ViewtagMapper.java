package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qfy.modules.city3d.po.Viewtag;
import com.qfy.modules.city3d.po.ViewtagExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@DS("postgresql")
public interface ViewtagMapper {
    int countByExample(ViewtagExample example);

    int deleteByExample(ViewtagExample example);

    int deleteByPrimaryKey(String tagId);

    int insert(Viewtag record);

    int insertSelective(Viewtag record);

    List<Viewtag> selectByExample(ViewtagExample example);

    Viewtag selectByPrimaryKey(String tagId);

    int updateByExampleSelective(@Param("record") Viewtag record, @Param("example") ViewtagExample example);

    int updateByExample(@Param("record") Viewtag record, @Param("example") ViewtagExample example);

    int updateByPrimaryKeySelective(Viewtag record);

    int updateByPrimaryKey(Viewtag record);
}
