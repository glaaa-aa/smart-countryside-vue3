package com.qfy.modules.smartform.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qfy.modules.smartform.entity.CheckTemplate;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 检查模板
 * @Author: jeecg-boot
 * @Date:   2022-04-08
 * @Version: V1.0
 */
public interface ICheckTemplateService extends IService<CheckTemplate> {

//    Map<String, Object> queryById(String id);
//
//    List<CheckTemplate> queryByPage(@Param(Constants.WRAPPER) Wrapper wrapper);
//



    //新智能表单 查询问卷详情
    CheckTemplate buildSurvey(String surveyId,String sid);

}
