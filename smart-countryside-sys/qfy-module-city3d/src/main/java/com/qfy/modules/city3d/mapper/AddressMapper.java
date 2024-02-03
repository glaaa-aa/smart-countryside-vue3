package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.qfy.modules.city3d.po.Address;
import com.qfy.modules.city3d.po.AddressExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@DS("postgresql")
public interface AddressMapper extends BaseMapper<Address> {
    int countByExample(AddressExample example);

    int deleteByExample(AddressExample example);

    int deleteByPrimaryKey(String addId);

//    int insert(Address record);

    int insertSelective(Address record);

    List<Address> selectByExample(AddressExample example);

    Address selectByPrimaryKey(String addId);

    int updateByExampleSelective(@Param("record") Address record, @Param("example") AddressExample example);

    int updateByExample(@Param("record") Address record, @Param("example") AddressExample example);

    int updateByPrimaryKeySelective(Address record);

    int updateByPrimaryKey(Address record);

    List<Address> getAddressAll(String addType);
	int batchDelete(List<String> addIds);
	List<Address> queryAddressByPolygon(Address address);

    /**
     * 统计网格下所有地址
     * @param wrapper
     * @return: java.util.List<com.qfy.modules.city3d.po.Address>
     * @author: lanls
     * @date: 2022/9/6 18:00
     * @description:
     */
    @Select("select a.* from address a left join net b on a.pid = b.net_id ${ew.customSqlSegment}")
    List<Address> queryByNet(@Param(Constants.WRAPPER) Wrapper wrapper);
}
