package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qfy.modules.city3d.po.BimDevice;
import com.qfy.modules.city3d.po.BimDeviceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@DS("postgresql")
public interface BimDeviceMapper {
    int countByExample(BimDeviceExample example);

    int deleteByExample(BimDeviceExample example);

    int deleteByPrimaryKey(String deviceId);

    int insert(BimDevice record);

    int insertSelective(BimDevice record);

    List<BimDevice> selectByExample(BimDeviceExample example);

    BimDevice selectByPrimaryKey(String deviceId);

    int updateByExampleSelective(@Param("record") BimDevice record, @Param("example") BimDeviceExample example);

    int updateByExample(@Param("record") BimDevice record, @Param("example") BimDeviceExample example);

    int updateByPrimaryKeySelective(BimDevice record);

    int updateByPrimaryKey(BimDevice record);
}
