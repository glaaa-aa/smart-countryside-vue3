package com.qfy.modules.smartform.service.impl;

import com.qfy.modules.smartform.entity.*;
import com.qfy.modules.smartform.mapper.CheckItemMapper;
import com.qfy.modules.smartform.service.*;
import com.qfy.modules.smartform.utils.QuType;
import com.xkcoding.http.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 模板项
 * @Author: jeecg-boot
 * @Date:   2024-01-23
 * @Version: V1.0
 */
@Service
public class CheckItemServiceImpl extends ServiceImpl<CheckItemMapper, CheckItem> implements ICheckItemService {



    @Autowired
    private ICheckItemRadioService checkItemRadioService;
    @Autowired
    private ICheckItemCheckboxService checkItemCheckboxService;
    @Autowired
    private ICheckItemMultiFillblankService checkItemMultiFillblankService;
    @Autowired
    private ICheckItemScoreService checkItemScoreService;
    @Autowired
    private ICheckItemOrderbyService checkItemOrderbyService;

    @Autowired
    private ICheckItemLogicService checkItemLogicService;




    @Override
    public  List<CheckItem> getCheckItemList(String surveyId){
        List<CheckItem> checkItemList=baseMapper.getCheckItemList(surveyId);
        for (CheckItem question : checkItemList) {
            getQuestionOption(question);
        }
        return checkItemList;
    }
    /**
     * 得到某一题下面的选项，包含大题下面的小题
     * @param question
     */
    public void getQuestionOption(CheckItem question) {
        String quId=question.getId();
        Integer  checkType=Integer.valueOf(question.getCheckType());
        if(checkType==QuType.RADIO.getIndex() || checkType==QuType.COMPRADIO.getIndex()){
            question.setQuRadios(checkItemRadioService.findByQuId(quId));
        }else if(checkType==QuType.CHECKBOX.getIndex() || checkType==QuType.COMPCHECKBOX.getIndex()){
            question.setQuCheckboxs(checkItemCheckboxService.findByQuId(quId));
        }else if(checkType==QuType.MULTIFILLBLANK.getIndex()){
            question.setQuMultiFillblanks(checkItemMultiFillblankService.findByQuId(quId));
        }else if(checkType==QuType.BIGQU.getIndex()){
            //根据大题ID，找出所有小题
            String parentQuId=question.getId();
            List<CheckItem> childQuList=baseMapper.findByParentQuId(parentQuId);
            for (CheckItem childQu : childQuList) {
                getQuestionOption(childQu);
            }
            question.setQuestions(childQuList);
            //根据小题的类型，取选项
        }else if(checkType==QuType.SCORE.getIndex()){
            List<CheckItemScore> quScores=checkItemScoreService.findByQuId(quId);
            question.setQuScores(quScores);
        }else if(checkType==QuType.ORDERQU.getIndex()){
            List<CheckItemOrderby>	quOrderbys=checkItemOrderbyService.findByQuId(quId);
            question.setQuOrderbys(quOrderbys);
        }
        List<CheckItemLogic> questionLogics=checkItemLogicService.findByCkQuId(quId);
        question.setQuestionLogics(questionLogics);
    }


    @Override
    public  void del1CheckItem(String quId){
        CheckItem checkItem=getById(quId);
        Integer  checkType=Integer.valueOf(checkItem.getCheckType());
        if(checkType==QuType.RADIO.getIndex() || checkType==QuType.COMPRADIO.getIndex()){
            checkItemRadioService.removeBatchByIds(checkItemRadioService.findByQuId(quId));
        }else if(checkType==QuType.CHECKBOX.getIndex() || checkType==QuType.COMPCHECKBOX.getIndex()){
            checkItemCheckboxService.removeBatchByIds(checkItemCheckboxService.findByQuId(quId));
        }else if(checkType==QuType.MULTIFILLBLANK.getIndex()){
            checkItemMultiFillblankService.removeBatchByIds(checkItemMultiFillblankService.findByQuId(quId));
        }else if(checkType==QuType.BIGQU.getIndex()){
            removeBatchByIds(baseMapper.findByParentQuId(quId));
            //根据小题的类型，取选项
        }else if(checkType==QuType.SCORE.getIndex()){
            checkItemScoreService.removeBatchByIds(checkItemScoreService.findByQuId(quId));
        }else if(checkType==QuType.ORDERQU.getIndex()){
            checkItemOrderbyService.removeBatchByIds(checkItemOrderbyService.findByQuId(quId));
        }
        checkItemLogicService.removeBatchByIds(checkItemLogicService.findByCkQuId(quId));
        removeById(checkItem);
    }



    @Override
    public CheckItem addFillblank(CheckItem checkItem){
        CheckItem checkItemnew=new CheckItem();
        if (StringUtil.isNotEmpty(checkItem.getId())){
            checkItemnew=getById(checkItem.getId());
        }
        checkItemnew.setTemplateid(checkItem.getTemplateid());
        checkItemnew.setQuTitle(checkItem.getQuTitle());
        checkItemnew.setIsPic(checkItem.getIsPic());
        checkItemnew.setRemarks(checkItem.getRemarks());
        checkItemnew.setOrderById(checkItem.getOrderById());
        checkItemnew.setCheckType(String.valueOf(QuType.FILLBLANK.getIndex()));
        checkItemnew.setChkmethod(checkItem.getChkmethod());
        checkItemnew.setHelp(checkItem.getHelp());
        checkItemnew.setItemtype(checkItem.getItemtype());
        checkItemnew.setDefaultVal(checkItem.getDefaultVal());
        checkItemnew.setBgrade(checkItem.getBgrade());
        checkItemnew.setQualifiedscore(checkItem.getQualifiedscore());
        checkItemnew.setUnqualifiedscore(checkItem.getUnqualifiedscore());

        if (StringUtil.isEmpty(checkItem.getAnswerinputwidth())){
            checkItem.setAnswerinputwidth("300");
        }
        checkItemnew.setAnswerinputwidth(checkItem.getAnswerinputwidth());


        if (StringUtil.isEmpty(checkItem.getAnswerinputrow())){
            checkItem.setAnswerinputrow("1");
        }
        checkItemnew.setAnswerinputrow(checkItem.getAnswerinputrow());


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

        //添加项数据
        saveOrUpdate(checkItemnew);

//        //处理项题目逻辑问题
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
    public CheckItem addPagetag(CheckItem checkItem){
        CheckItem checkItemnew=new CheckItem();
        if (StringUtil.isNotEmpty(checkItem.getId())){
            checkItemnew=getById(checkItem.getId());
        }
        checkItemnew.setTemplateid(checkItem.getTemplateid());
        checkItemnew.setQuTitle(checkItem.getQuTitle());
        checkItemnew.setQutag(checkItem.getQutag());
        checkItemnew.setIsPic(checkItem.getIsPic());
        checkItemnew.setRemarks(checkItem.getRemarks());
        checkItemnew.setOrderById(checkItem.getOrderById());
        checkItemnew.setCheckType(String.valueOf(QuType.PAGETAG.getIndex()));
        checkItemnew.setChkmethod(checkItem.getChkmethod());
        checkItemnew.setHelp(checkItem.getHelp());
        checkItemnew.setItemtype(checkItem.getItemtype());
        checkItemnew.setDefaultVal(checkItem.getDefaultVal());
        checkItemnew.setBgrade(checkItem.getBgrade());
        checkItemnew.setQualifiedscore(checkItem.getQualifiedscore());
        checkItemnew.setUnqualifiedscore(checkItem.getUnqualifiedscore());

        if (StringUtil.isEmpty(checkItem.getAnswerinputwidth())){
            checkItem.setAnswerinputwidth("300");
        }
        checkItemnew.setAnswerinputwidth(checkItem.getAnswerinputwidth());


        if (StringUtil.isEmpty(checkItem.getAnswerinputrow())){
            checkItem.setAnswerinputrow("1");
        }
        checkItemnew.setAnswerinputrow(checkItem.getAnswerinputrow());


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

        //添加项数据
        saveOrUpdate(checkItemnew);

//        //处理项题目逻辑问题
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
    public CheckItem addParagraph(CheckItem checkItem){
        CheckItem checkItemnew=new CheckItem();
        if (StringUtil.isNotEmpty(checkItem.getId())){
            checkItemnew=getById(checkItem.getId());
        }
        checkItemnew.setTemplateid(checkItem.getTemplateid());
        checkItemnew.setQuTitle(checkItem.getQuTitle());
        checkItemnew.setQutag(checkItem.getQutag());
        checkItemnew.setIsPic(checkItem.getIsPic());
        checkItemnew.setRemarks(checkItem.getRemarks());
        checkItemnew.setOrderById(checkItem.getOrderById());
        checkItemnew.setCheckType(String.valueOf(QuType.PARAGRAPH.getIndex()));
        checkItemnew.setChkmethod(checkItem.getChkmethod());
        checkItemnew.setHelp(checkItem.getHelp());
        checkItemnew.setItemtype(checkItem.getItemtype());
        checkItemnew.setDefaultVal(checkItem.getDefaultVal());
        checkItemnew.setBgrade(checkItem.getBgrade());
        checkItemnew.setQualifiedscore(checkItem.getQualifiedscore());
        checkItemnew.setUnqualifiedscore(checkItem.getUnqualifiedscore());

        if (StringUtil.isEmpty(checkItem.getAnswerinputwidth())){
            checkItem.setAnswerinputwidth("300");
        }
        checkItemnew.setAnswerinputwidth(checkItem.getAnswerinputwidth());


        if (StringUtil.isEmpty(checkItem.getAnswerinputrow())){
            checkItem.setAnswerinputrow("1");
        }
        checkItemnew.setAnswerinputrow(checkItem.getAnswerinputrow());


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

        //添加项数据
        saveOrUpdate(checkItemnew);

//        //处理项题目逻辑问题
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
    public CheckItem addDigit(CheckItem checkItem){
        CheckItem checkItemnew=new CheckItem();
        if (StringUtil.isNotEmpty(checkItem.getId())){
            checkItemnew=getById(checkItem.getId());
        }
        checkItemnew.setTemplateid(checkItem.getTemplateid());
        checkItemnew.setQuTitle(checkItem.getQuTitle());
        checkItemnew.setQutag(checkItem.getQutag());
        checkItemnew.setIsPic(checkItem.getIsPic());
        checkItemnew.setRemarks(checkItem.getRemarks());
        checkItemnew.setOrderById(checkItem.getOrderById());
        checkItemnew.setCheckType(String.valueOf(QuType.DIGIT.getIndex()));
        checkItemnew.setChkmethod(checkItem.getChkmethod());
        checkItemnew.setHelp(checkItem.getHelp());
        checkItemnew.setItemtype(checkItem.getItemtype());
        checkItemnew.setDefaultVal(checkItem.getDefaultVal());
        checkItemnew.setBgrade(checkItem.getBgrade());
        checkItemnew.setQualifiedscore(checkItem.getQualifiedscore());
        checkItemnew.setUnqualifiedscore(checkItem.getUnqualifiedscore());

        if (StringUtil.isEmpty(checkItem.getAnswerinputwidth())){
            checkItem.setAnswerinputwidth("300");
        }
        checkItemnew.setAnswerinputwidth(checkItem.getAnswerinputwidth());


        if (StringUtil.isEmpty(checkItem.getAnswerinputrow())){
            checkItem.setAnswerinputrow("1");
        }
        checkItemnew.setAnswerinputrow(checkItem.getAnswerinputrow());


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
        checkItemnew.setYxSx(checkItem.getYxSx());
        checkItemnew.setYxXx(checkItem.getYxXx());
        checkItemnew.setHgSx(checkItem.getHgSx());
        checkItemnew.setHgXx(checkItem.getHgXx());
        checkItemnew.setPoint(checkItem.getPoint());
        //添加项数据
        saveOrUpdate(checkItemnew);
//        //处理项题目逻辑问题
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
    public CheckItem addUploadFile(CheckItem checkItem){
        CheckItem checkItemnew=new CheckItem();
        if (StringUtil.isNotEmpty(checkItem.getId())){
            checkItemnew=getById(checkItem.getId());
        }
        checkItemnew.setTemplateid(checkItem.getTemplateid());
        checkItemnew.setQuTitle(checkItem.getQuTitle());
        checkItemnew.setQutag(checkItem.getQutag());
        checkItemnew.setIsPic(checkItem.getIsPic());
        checkItemnew.setRemarks(checkItem.getRemarks());
        checkItemnew.setOrderById(checkItem.getOrderById());
        checkItemnew.setCheckType(String.valueOf(QuType.UPLOADFILE.getIndex()));
        checkItemnew.setChkmethod(checkItem.getChkmethod());
        checkItemnew.setHelp(checkItem.getHelp());
        checkItemnew.setItemtype(checkItem.getItemtype());
        checkItemnew.setDefaultVal(checkItem.getDefaultVal());
        checkItemnew.setBgrade(checkItem.getBgrade());
        checkItemnew.setQualifiedscore(checkItem.getQualifiedscore());
        checkItemnew.setUnqualifiedscore(checkItem.getUnqualifiedscore());

        if (StringUtil.isEmpty(checkItem.getAnswerinputwidth())){
            checkItem.setAnswerinputwidth("300");
        }
        checkItemnew.setAnswerinputwidth(checkItem.getAnswerinputwidth());


        if (StringUtil.isEmpty(checkItem.getAnswerinputrow())){
            checkItem.setAnswerinputrow("1");
        }
        checkItemnew.setAnswerinputrow(checkItem.getAnswerinputrow());


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
        saveOrUpdate(checkItemnew);

//        //处理项题目逻辑问题
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


}
