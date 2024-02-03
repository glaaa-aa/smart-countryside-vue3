package com.qfy.modules.smartform.mapper;

import java.util.List;

import com.qfy.modules.smartform.entity.CheckItemMultiFillblank;
import com.qfy.modules.smartform.entity.CheckItemScore;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * @Description: 评分题 行选项
 * @Author: jeecg-boot
 * @Date:   2024-01-23
 * @Version: V1.0
 */
public interface CheckItemScoreMapper extends BaseMapper<CheckItemScore> {

    @Select("SELECT * from qfy_check_item_score where item_id=#{quID} and visibility=1 ORDER BY order_by asc")
    public  List<CheckItemScore> getCheckItemScoreList(String quID);

}
