package com.qfy.modules.smartform.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.qfy.modules.smartform.api.common.CheckItemGroupTreeNode;
import com.qfy.modules.smartform.entity.CheckTemplate;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;

/**
 * @Description: 检查模板
 * @Author: jeecg-boot
 * @Date:   2022-04-08
 * @Version: V1.0
 */
public interface CheckTemplateMapper extends BaseMapper<CheckTemplate> {

//    @Select("SELECT a.*,b.`name` AS ttype_name FROM qfy_check_template a " +
//            "LEFT JOIN qfy_check_types b ON a.`ttype` = b.`serialno` WHERE a.id = #{id}")
//    Map<String, Object> leftJoinTypes(String id);
//
//    @Select("SELECT id,name,parentid FROM qfy_check_item_group WHERE template_id = #{id} order by id asc limit 30")
//    List<CheckItemGroupTreeNode> findFromItemGroup(String templateId);
//
//    @Select("SELECT a.*,b.name AS ttypeName FROM qfy_check_template a LEFT JOIN qfy_check_types b ON a.`ttype` = b.`serialno` "
//            + " ${ew.customSqlSegment}")
//    List<CheckTemplate> queryByPage(@Param(Constants.WRAPPER) Wrapper wrapper);

    @Select("SELECT * from qfy_check_template where sid=#{sId}")
    public CheckTemplate getSurveyBySid(String sId);

}
