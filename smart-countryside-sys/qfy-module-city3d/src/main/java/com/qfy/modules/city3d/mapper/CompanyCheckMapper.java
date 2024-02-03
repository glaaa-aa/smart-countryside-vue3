package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qfy.modules.city3d.po.CompanyCheck;
import com.qfy.modules.city3d.po.CompanyCheckExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@DS("postgresql")
public interface CompanyCheckMapper {
    int countByExample(CompanyCheckExample example);

    int deleteByExample(CompanyCheckExample example);

    int deleteByPrimaryKey(String companyCheckId);

    int insert(CompanyCheck record);

    int insertSelective(CompanyCheck record);

    List<CompanyCheck> selectByExample(CompanyCheckExample example);

    CompanyCheck selectByPrimaryKey(String companyCheckId);

    int updateByExampleSelective(@Param("record") CompanyCheck record, @Param("example") CompanyCheckExample example);

    int updateByExample(@Param("record") CompanyCheck record, @Param("example") CompanyCheckExample example);

    int updateByPrimaryKeySelective(CompanyCheck record);

    int updateByPrimaryKey(CompanyCheck record);
}
