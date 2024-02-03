package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qfy.modules.city3d.po.AttributeData;
import com.qfy.modules.city3d.po.AttributeDataExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@DS("postgresql")
public interface AttributeDataMapper {
    int countByExample(AttributeDataExample example);

    int deleteByExample(AttributeDataExample example);

    int deleteByPrimaryKey(String id);

    int insert(AttributeData record);

    int insertSelective(AttributeData record);

    List<AttributeData> selectByExample(AttributeDataExample example);

    AttributeData selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") AttributeData record, @Param("example") AttributeDataExample example);

    int updateByExample(@Param("record") AttributeData record, @Param("example") AttributeDataExample example);

    int updateByPrimaryKeySelective(AttributeData record);

    int updateByPrimaryKey(AttributeData record);
}
