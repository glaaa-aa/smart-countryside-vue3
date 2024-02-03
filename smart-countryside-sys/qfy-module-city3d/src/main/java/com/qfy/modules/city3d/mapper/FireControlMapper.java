package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qfy.modules.city3d.po.FireControl;
import com.qfy.modules.city3d.po.FireControlExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
@DS("postgresql")
public interface FireControlMapper {
    int countByExample(FireControlExample example);

    int deleteByExample(FireControlExample example);

    int deleteByPrimaryKey(String fireCtlId);

    int insert(FireControl record);

    int insertSelective(FireControl record);

    List<FireControl> selectByExample(FireControlExample example);

    FireControl selectByPrimaryKey(String fireCtlId);

    int updateByExampleSelective(@Param("record") FireControl record, @Param("example") FireControlExample example);

    int updateByExample(@Param("record") FireControl record, @Param("example") FireControlExample example);

    int updateByPrimaryKeySelective(FireControl record);

    int updateByPrimaryKey(FireControl record);
}
