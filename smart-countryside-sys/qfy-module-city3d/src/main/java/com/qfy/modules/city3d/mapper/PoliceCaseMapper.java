package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qfy.modules.city3d.po.PoliceCase;
import com.qfy.modules.city3d.po.PoliceCaseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@DS("postgresql")
public interface PoliceCaseMapper {
    int countByExample(PoliceCaseExample example);

    int deleteByExample(PoliceCaseExample example);

    int deleteByPrimaryKey(String caseId);

    int insert(PoliceCase record);

    int insertSelective(PoliceCase record);

    List<PoliceCase> selectByExample(PoliceCaseExample example);

    PoliceCase selectByPrimaryKey(String caseId);

    int updateByExampleSelective(@Param("record") PoliceCase record, @Param("example") PoliceCaseExample example);

    int updateByExample(@Param("record") PoliceCase record, @Param("example") PoliceCaseExample example);

    int updateByPrimaryKeySelective(PoliceCase record);

    int updateByPrimaryKey(PoliceCase record);
}
