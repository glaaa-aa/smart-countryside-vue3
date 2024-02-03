package com.qfy.modules.smartform.service.impl;


import com.qfy.modules.smartform.entity.*;
import com.qfy.modules.smartform.mapper.CheckItemRadioMapper;
import com.qfy.modules.smartform.mapper.CheckItemScoreMapper;
import com.qfy.modules.smartform.service.ICheckItemLogicService;
import com.qfy.modules.smartform.service.ICheckItemScoreService;
import com.qfy.modules.smartform.service.ICheckItemService;
import com.qfy.modules.smartform.utils.QuType;
import com.xkcoding.http.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 评分题 行选项
 * @Author: jeecg-boot
 * @Date:   2024-01-23
 * @Version: V1.0
 */
@Service
public class CheckItemScoreServiceImpl extends ServiceImpl<CheckItemScoreMapper, CheckItemScore> implements ICheckItemScoreService {


    @Autowired
    private ICheckItemLogicService checkItemLogicService;

    @Autowired
    private CheckItemRadioMapper checkItemRadioMapper;


    @Autowired
    @Lazy
    private ICheckItemService checkItemService;

    /**
     * 得到某一题的选项
     */
    @Override
    public List<CheckItemScore> findByQuId(String quId){
        List<CheckItemScore> checkItemRadioList=baseMapper.getCheckItemScoreList(quId);
        return checkItemRadioList;
    }


    @Override
    public CheckItem addScore(CheckItem checkItem){
        CheckItem checkItemnew=new CheckItem();
        if (StringUtil.isNotEmpty(checkItem.getId())){
            checkItemnew=checkItemService.getById(checkItem.getId());
        }
        checkItemnew.setTemplateid(checkItem.getTemplateid());
        checkItemnew.setQuTitle(checkItem.getQuTitle());
        checkItemnew.setQutag(checkItem.getQutag());
        checkItemnew.setIsPic(checkItem.getIsPic());
        checkItemnew.setRemarks(checkItem.getRemarks());
        checkItemnew.setOrderById(checkItem.getOrderById());
        checkItemnew.setCheckType(String.valueOf(QuType.SCORE.getIndex()));
        checkItemnew.setChkmethod(checkItem.getChkmethod());
        checkItemnew.setHelp(checkItem.getHelp());
        checkItemnew.setItemtype(checkItem.getItemtype());
        checkItemnew.setDefaultVal(checkItem.getDefaultVal());
        checkItemnew.setBgrade(checkItem.getBgrade());
        checkItemnew.setQualifiedscore(checkItem.getQualifiedscore());
        checkItemnew.setUnqualifiedscore(checkItem.getUnqualifiedscore());
        if (StringUtil.isEmpty(checkItem.getIsRequired())){
            checkItem.setIsRequired("0");
        }
        checkItemnew.setIsRequired(checkItem.getIsRequired());
        if (StringUtil.isEmpty(checkItem.getHv())){
            checkItem.setHv("0");
        }
        checkItemnew.setHv(checkItem.getHv());
        if (StringUtil.isEmpty(checkItem.getRandOrder())){
            checkItem.setRandOrder("0");
        }
        checkItemnew.setRandOrder(checkItem.getRandOrder());
        if (StringUtil.isEmpty(checkItem.getCellCount())){
            checkItem.setCellCount("0");
        }
        checkItemnew.setCellCount(checkItem.getCellCount());


        if (StringUtil.isEmpty(checkItem.getParamint01())){
            checkItem.setParamint01("0");
        }
        checkItemnew.setParamint01(checkItem.getParamint01());

        if (StringUtil.isEmpty(checkItem.getParamint02())){
            checkItem.setParamint02("0");
        }
        checkItemnew.setParamint02(checkItem.getParamint02());


        //添加项数据
        checkItemService.saveOrUpdate(checkItemnew);


        //处理项单项数据
        List<CheckItemScore> quScore=new ArrayList<CheckItemScore>();
        for (CheckItemScore checkItemScore : checkItem.getQuScores()) {
            CheckItemScore checkItemScorenew=new CheckItemScore();
            if (StringUtil.isNotEmpty(checkItemScore.getId())){
                checkItemScorenew=getById(checkItemScore.getId());
            }
            checkItemScorenew.setItemId(checkItemnew.getId());
            checkItemScorenew.setOptionName(checkItemScore.getOptionName());
            checkItemScorenew.setOrderBy(checkItemScore.getOrderBy());

            if (StringUtil.isEmpty(checkItemScore.getFraction())){
                checkItemScore.setFraction("0");
            }
            checkItemScorenew.setFraction(checkItemScore.getFraction());
            if (StringUtil.isEmpty(checkItemScore.getType())){
                checkItemScore.setType("0");
            }
            checkItemScorenew.setType(checkItemScore.getType());
            if (StringUtil.isEmpty(checkItemScore.getVisibility())){
                checkItemScore.setVisibility("0");
            }
            checkItemScorenew.setVisibility(checkItemScore.getVisibility());
            saveOrUpdate(checkItemScorenew);
            quScore.add(checkItemScorenew);
        }
        checkItemnew.setQuScores(quScore);

        //处理项题目逻辑问题
        List<CheckItemLogic> quLogics=new ArrayList<CheckItemLogic>();
        List<CheckItemLogic> quLogicsAddLIst=checkItem.getQuestionLogics();
        if (quLogicsAddLIst!=null && quLogicsAddLIst.size()>0){
            for (CheckItemLogic checkItemLogic : checkItem.getQuestionLogics()) {
                CheckItemLogic checkItemLogicnew=new CheckItemLogic();
                if (StringUtil.isNotEmpty(checkItemLogic.getId())){
                    checkItemLogicnew=checkItemLogicService.getById(checkItemLogic.getId());
                }
                checkItemLogicnew.setCgQuitemid(checkItemLogic.getCgQuitemid());
                checkItemLogicnew.setSkQuid(checkItemLogic.getSkQuid());
                if (StringUtil.isEmpty(checkItemLogic.getVisibility())){
                    checkItemLogic.setVisibility("1");
                }
                checkItemLogicnew.setVisibility(checkItemLogic.getVisibility());
                checkItemLogicnew.setTitle(checkItemLogic.getTitle());
                checkItemLogicnew.setLogicType(checkItemLogic.getLogicType());
                checkItemLogicnew.setCkQuid(checkItemnew.getId());
                checkItemLogicService.saveOrUpdate(checkItemLogicnew);
                quLogics.add(checkItemLogicnew);
            }
        }
        checkItemnew.setQuestionLogics(quLogics);
        return checkItemnew;
    }


    @Override
    public void deleteScore(String quItemId){
        CheckItemScore quScore =getById(quItemId);
        String quId=quScore.getItemId();
        removeById(quScore);
        List<CheckItemLogic> checkItemLogicList=checkItemRadioMapper.getlogicList(quItemId,quId);
        for (CheckItemLogic checkItemRadio : checkItemLogicList) {
            removeById(checkItemRadio);
        }
    }

}
