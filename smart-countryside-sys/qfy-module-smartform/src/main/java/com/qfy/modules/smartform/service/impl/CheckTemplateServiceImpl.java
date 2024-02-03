package com.qfy.modules.smartform.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qfy.modules.smartform.api.common.CheckItemGroupTreeNode;
import com.qfy.modules.smartform.entity.*;
import com.qfy.modules.smartform.mapper.*;
import com.qfy.modules.smartform.service.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.qfy.modules.smartform.utils.QuType;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.simpleframework.xml.Root;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: 检查模板
 * @Author: jeecg-boot
 * @Date:   2022-04-08
 * @Version: V1.0
 */
@Service
public class CheckTemplateServiceImpl extends ServiceImpl<CheckTemplateMapper, CheckTemplate> implements ICheckTemplateService {

    public static final Logger logger = LoggerFactory.getLogger(CheckTemplateServiceImpl.class);

    @Autowired
    private ICheckItemService checkItemService;

    @Autowired
    private CheckItemMapper checkItemMapper;



    //新智能表单 查询问卷详情
    @Override
    public  CheckTemplate  buildSurvey(String surveyId,String sid) {
        CheckTemplate surveyDirectory = null;

//        checkItemRadioService.
        if(StringUtils.isEmpty(surveyId) && StringUtils.isNotEmpty(sid)){
            surveyDirectory =baseMapper.getSurveyBySid(sid);
        }else{
            surveyDirectory= getById(surveyId);
        }
//        User user= accountManager.getCurUser();
//        String userId=user.getId();
//        if(user!=null){
//            if(!userId.equals(surveyDirectory.getUserId())){
//                return HttpResult.FAILURE_MSG("未登录或没有相应数据权限");
//            }
//        }else{
//            return HttpResult.FAILURE_MSG("未登录或没有相应数据权限");
//        }
        if(surveyDirectory!=null){
            List<CheckItem> questions=checkItemService.getCheckItemList(surveyDirectory.getId());
//            List<CheckItem> questionsAdd=checkItemService.findDetails(questions);
            surveyDirectory.setQuestions(questions);
            surveyDirectory.setSurveyQunum(String.valueOf(questions.size()));
//            surveyDirectoryManager.save(surveyDirectory);
        }
        return surveyDirectory;
    }






}
