package com.qfy.modules.family.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qfy.modules.family.entity.IntegralCheckItem;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author lanls
 * @version v1.0.0
 * @since 2023/8/30 11:04
 */
public interface IntegralCheckItemMapper extends BaseMapper<IntegralCheckItem> {

    @Select("SELECT a.id,a.llvalidnumb AS value,b.name,b.name AS title,b.remark AS text FROM qfy_check_item_number a," +
            "(SELECT id,name,remark FROM qfy_check_item WHERE chk_item_group_id IN " +
            "(SELECT id FROM qfy_check_item_group WHERE template_id = #{tplId} ORDER BY seq_rank ASC)) b" +
            " WHERE a.chkid = b.id")
    List<IntegralCheckItem> selectList(@Param("tplId") String tplId);
}
