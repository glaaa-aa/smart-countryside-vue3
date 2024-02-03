package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qfy.modules.city3d.po.BimCase;
import com.qfy.modules.city3d.po.BimCaseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@DS("postgresql")
public interface BimCaseMapper {
    int countByExample(BimCaseExample example);

    int deleteByExample(BimCaseExample example);

    int deleteByPrimaryKey(String bimCaseId);

    int insert(BimCase record);

    int insertSelective(BimCase record);

    List<BimCase> selectByExample(BimCaseExample example);

    BimCase selectByPrimaryKey(String bimCaseId);

    int updateByExampleSelective(@Param("record") BimCase record, @Param("example") BimCaseExample example);

    int updateByExample(@Param("record") BimCase record, @Param("example") BimCaseExample example);

    int updateByPrimaryKeySelective(BimCase record);

    int updateByPrimaryKey(BimCase record);
}
