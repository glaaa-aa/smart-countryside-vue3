package com.qfy.modules.smartform.mapper;

import java.util.List;

import com.qfy.modules.smartform.entity.CheckTemplate;
import org.apache.ibatis.annotations.Param;
import com.qfy.modules.smartform.entity.CheckItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * @Description: 检查项
 * @Author: jeecg-boot
 * @Date:   2022-04-08
 * @Version: V1.0
 */
public interface CheckItemMapper extends BaseMapper<CheckItem> {


    @Select("SELECT * from qfy_check_item where templateid=#{surveyId} and qutag!=3 ORDER BY order_by_id asc")
    public  List<CheckItem> getCheckItemList(String surveyId);



    @Select("SELECT * from qfy_check_item where parent_qu_id=#{quId} ORDER BY order_by_id asc")
    public  List<CheckItem> findByParentQuId(String quId);


}
