package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qfy.modules.city3d.po.Security;
import com.qfy.modules.city3d.po.SecurityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@DS("postgresql")
public interface SecurityMapper {
    int countByExample(SecurityExample example);

    int deleteByExample(SecurityExample example);

    int deleteByPrimaryKey(String securityId);

    int insert(Security record);

    int insertSelective(Security record);

    List<Security> selectByExample(SecurityExample example);

    Security selectByPrimaryKey(String securityId);

    int updateByExampleSelective(@Param("record") Security record, @Param("example") SecurityExample example);

    int updateByExample(@Param("record") Security record, @Param("example") SecurityExample example);

    int updateByPrimaryKeySelective(Security record);

    int updateByPrimaryKey(Security record);
}
