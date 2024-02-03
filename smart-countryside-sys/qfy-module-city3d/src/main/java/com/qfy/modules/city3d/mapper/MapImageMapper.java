package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qfy.modules.city3d.po.MapImage;
import com.qfy.modules.city3d.po.MapImageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@DS("postgresql")
public interface MapImageMapper {
    int countByExample(MapImageExample example);

    int deleteByExample(MapImageExample example);

    int deleteByPrimaryKey(String imageId);

    int insert(MapImage record);

    int insertSelective(MapImage record);

    List<MapImage> selectByExample(MapImageExample example);

    MapImage selectByPrimaryKey(String imageId);

    int updateByExampleSelective(@Param("record") MapImage record, @Param("example") MapImageExample example);

    int updateByExample(@Param("record") MapImage record, @Param("example") MapImageExample example);

    int updateByPrimaryKeySelective(MapImage record);

    int updateByPrimaryKey(MapImage record);
}
