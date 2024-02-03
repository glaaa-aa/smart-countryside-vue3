package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qfy.modules.city3d.po.FaceIdentity;
import com.qfy.modules.city3d.po.FaceIdentityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@DS("postgresql")
public interface FaceIdentityMapper {
    int countByExample(FaceIdentityExample example);

    int deleteByExample(FaceIdentityExample example);

    int deleteByPrimaryKey(String faceId);

    int insert(FaceIdentity record);

    int insertSelective(FaceIdentity record);

    List<FaceIdentity> selectByExample(FaceIdentityExample example);

    FaceIdentity selectByPrimaryKey(String faceId);

    int updateByExampleSelective(@Param("record") FaceIdentity record, @Param("example") FaceIdentityExample example);

    int updateByExample(@Param("record") FaceIdentity record, @Param("example") FaceIdentityExample example);

    int updateByPrimaryKeySelective(FaceIdentity record);

    int updateByPrimaryKey(FaceIdentity record);
}
