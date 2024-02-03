package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qfy.modules.city3d.po.MapPhotos;
import com.qfy.modules.city3d.po.MapPhotosExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
@DS("postgresql")
public interface MapPhotosMapper {
    int countByExample(MapPhotosExample example);

    int deleteByExample(MapPhotosExample example);

    int deleteByPrimaryKey(String photoId);

    int insert(MapPhotos record);

    int insertSelective(MapPhotos record);

    List<MapPhotos> selectByExample(MapPhotosExample example);

    MapPhotos selectByPrimaryKey(String photoId);

    int updateByExampleSelective(@Param("record") MapPhotos record, @Param("example") MapPhotosExample example);

    int updateByExample(@Param("record") MapPhotos record, @Param("example") MapPhotosExample example);

    int updateByPrimaryKeySelective(MapPhotos record);

    int updateByPrimaryKey(MapPhotos record);
}
