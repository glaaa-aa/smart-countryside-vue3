package com.qfy.modules.smartform.mapper;

import java.util.List;

import com.qfy.modules.smartform.entity.CheckItemLogic;
import com.qfy.modules.smartform.entity.CheckItemOrderby;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * @Description: 题目逻辑设置
 * @Author: jeecg-boot
 * @Date:   2024-01-23
 * @Version: V1.0
 */
public interface CheckItemLogicMapper extends BaseMapper<CheckItemLogic> {


    @Select("SELECT * from qfy_check_item_logic where ck_quid=#{quID} and visibility=1")
    public  List<CheckItemLogic> getCheckItemLogicList(String quID);

}
