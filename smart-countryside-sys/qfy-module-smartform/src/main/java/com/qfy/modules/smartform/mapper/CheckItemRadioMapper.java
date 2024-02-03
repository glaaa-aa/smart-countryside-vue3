package com.qfy.modules.smartform.mapper;

import java.util.List;

import com.qfy.modules.smartform.entity.CheckItem;
import com.qfy.modules.smartform.entity.CheckItemLogic;
import com.qfy.modules.smartform.entity.CheckItemRadio;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * @Description: 单选题/下拉题
 * @Author: jeecg-boot
 * @Date:   2024-01-23
 * @Version: V1.0
 */
public interface CheckItemRadioMapper extends BaseMapper<CheckItemRadio> {


    @Select("SELECT * from qfy_check_item_radio where item_id=#{quID} and visibility=1 ORDER BY order_by asc")
    public  List<CheckItemRadio> getCheckItemRadioList(String quID);



    @Select("SELECT * from qfy_check_item_radio where item_id=#{quID} and  order_by>=#{orderBy}")
    public  List<CheckItemRadio> getRadioList(String quID,String orderBy);


    @Select("SELECT * from qfy_check_item_logic where ck_quid=#{ckQuid} and  cg_quitemid=#{cgQuitemid}")
    public  List<CheckItemLogic> getlogicList(String ckQuid, String cgQuitemid);
}
