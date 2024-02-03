package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qfy.modules.city3d.po.EmergencyStore;
import com.qfy.modules.city3d.po.EmergencyStoreExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@DS("postgresql")
public interface EmergencyStoreMapper {
    int countByExample(EmergencyStoreExample example);

    int deleteByExample(EmergencyStoreExample example);

    int deleteByPrimaryKey(String storeId);

    int insert(EmergencyStore record);

    int insertSelective(EmergencyStore record);

    List<EmergencyStore> selectByExample(EmergencyStoreExample example);

    EmergencyStore selectByPrimaryKey(String storeId);

    int updateByExampleSelective(@Param("record") EmergencyStore record, @Param("example") EmergencyStoreExample example);

    int updateByExample(@Param("record") EmergencyStore record, @Param("example") EmergencyStoreExample example);

    int updateByPrimaryKeySelective(EmergencyStore record);

    int updateByPrimaryKey(EmergencyStore record);
}
