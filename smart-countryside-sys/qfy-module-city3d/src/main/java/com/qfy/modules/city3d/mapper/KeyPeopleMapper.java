package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qfy.modules.city3d.po.KeyPeople;
import com.qfy.modules.city3d.po.KeyPeopleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@DS("postgresql")
public interface KeyPeopleMapper {
    int countByExample(KeyPeopleExample example);

    int deleteByExample(KeyPeopleExample example);

    int deleteByPrimaryKey(String keyId);

    int insert(KeyPeople record);

    int insertSelective(KeyPeople record);

    List<KeyPeople> selectByExample(KeyPeopleExample example);

    KeyPeople selectByPrimaryKey(String keyId);

    int updateByExampleSelective(@Param("record") KeyPeople record, @Param("example") KeyPeopleExample example);

    int updateByExample(@Param("record") KeyPeople record, @Param("example") KeyPeopleExample example);

    int updateByPrimaryKeySelective(KeyPeople record);

    int updateByPrimaryKey(KeyPeople record);
}
