package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qfy.modules.city3d.po.Bim;
import com.qfy.modules.city3d.po.BimExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Primary;

@DS("postgresql")
@Primary
public interface BimMapper extends BaseMapper<Bim> {
    int countByExample(BimExample example);

    int deleteByExample(BimExample example);

    int deleteByPrimaryKey(String tilesId);

//    int insert(Bim record);

    int insertSelective(Bim record);

    List<Bim> selectByExample(BimExample example);

    Bim selectByPrimaryKey(String tilesId);

    int updateByExampleSelective(@Param("record") Bim record, @Param("example") BimExample example);

    int updateByExample(@Param("record") Bim record, @Param("example") BimExample example);

    int updateByPrimaryKeySelective(Bim record);

    int updateByPrimaryKey(Bim record);

    int batchDelete(List<String> tilesIds);
}
