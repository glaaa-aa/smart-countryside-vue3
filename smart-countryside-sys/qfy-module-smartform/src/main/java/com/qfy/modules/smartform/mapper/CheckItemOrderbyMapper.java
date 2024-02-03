package com.qfy.modules.smartform.mapper;

import java.util.List;

import com.qfy.modules.smartform.entity.CheckItemOrderby;
import com.qfy.modules.smartform.entity.CheckItemScore;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * @Description: 排序题
 * @Author: jeecg-boot
 * @Date:   2024-01-23
 * @Version: V1.0
 */
public interface CheckItemOrderbyMapper extends BaseMapper<CheckItemOrderby> {

    @Select("SELECT * from qfy_check_item_orderby where item_id=#{quID} and visibility=1 ORDER BY order_by asc")
    public  List<CheckItemOrderby> getCheckItemOrderbyList(String quID);

}
