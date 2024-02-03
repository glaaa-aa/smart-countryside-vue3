package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qfy.modules.city3d.po.Alert;
import com.qfy.modules.city3d.po.AlertExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@DS("postgresql")
public interface AlertMapper {
    int countByExample(AlertExample example);

    int deleteByExample(AlertExample example);

    int deleteByPrimaryKey(String id);

    int insert(Alert record);

    int insertSelective(Alert record);

    List<Alert> selectByExample(AlertExample example);

    Alert selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Alert record, @Param("example") AlertExample example);

    int updateByExample(@Param("record") Alert record, @Param("example") AlertExample example);

    int updateByPrimaryKeySelective(Alert record);

    int updateByPrimaryKey(Alert record);
}
