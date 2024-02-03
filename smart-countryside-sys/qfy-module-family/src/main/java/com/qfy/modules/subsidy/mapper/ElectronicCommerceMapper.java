package com.qfy.modules.subsidy.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qfy.modules.family.entity.FamilyMember;
import com.qfy.modules.subsidy.entity.ElectronicCommerce;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * @Description: 电商营业点
 * @Author: lanls
 * @Date:   2022-08-26
 * @Version: V1.0
 */
public interface ElectronicCommerceMapper extends BaseMapper<ElectronicCommerce> {

}
