package com.qfy.modules.smartform.mapper;

import java.util.List;

import com.qfy.modules.smartform.entity.CheckItemCheckbox;
import com.qfy.modules.smartform.entity.CheckItemRadio;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * @Description: 多选填空题
 * @Author: jeecg-boot
 * @Date:   2024-01-23
 * @Version: V1.0
 */
public interface CheckItemCheckboxMapper extends BaseMapper<CheckItemCheckbox> {



    @Select("SELECT * from qfy_check_item_checkbox where item_id=#{quID} and visibility=1 ORDER BY order_by asc")
    public  List<CheckItemCheckbox> getCheckItemCheckboxList(String quID);
}
