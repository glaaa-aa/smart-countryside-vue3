package com.qfy.modules.smartform.service.impl;


import com.qfy.modules.smartform.entity.CheckItem;
import com.qfy.modules.smartform.entity.CheckItemCheckbox;
import com.qfy.modules.smartform.entity.CheckItemLogic;
import com.qfy.modules.smartform.entity.CheckItemRadio;
import com.qfy.modules.smartform.mapper.CheckItemCheckboxMapper;
import com.qfy.modules.smartform.mapper.CheckItemRadioMapper;
import com.qfy.modules.smartform.service.ICheckItemCheckboxService;
import com.qfy.modules.smartform.service.ICheckItemLogicService;
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
 * @Description: 多选填空题
 * @Author: jeecg-boot
 * @Date:   2024-01-23
 * @Version: V1.0
 */
@Service
public class CheckItemCheckboxServiceImpl extends ServiceImpl<CheckItemCheckboxMapper, CheckItemCheckbox> implements ICheckItemCheckboxService {



    @Autowired
    private ICheckItemLogicService checkItemLogicService;



    @Autowired
    @Lazy
    private ICheckItemService checkItemService;


    @Autowired
    private  CheckItemRadioMapper checkItemRadioMapper;

    /**
     * 得到某一题的选项
     */
    @Override
    public List<CheckItemCheckbox> findByQuId(String quId){
        List<CheckItemCheckbox> checkItemRadioList=baseMapper.getCheckItemCheckboxList(quId);
        return checkItemRadioList;
    }


    @Override
    public CheckItem addCheckbox(CheckItem checkItem){
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
        checkItemnew.setCheckType(String.valueOf(QuType.CHECKBOX.getIndex()));
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
        List<CheckItemCheckbox> quCheckbox=new ArrayList<CheckItemCheckbox>();
        for (CheckItemCheckbox checkItemCheckbox : checkItem.getQuCheckboxs()) {
            CheckItemCheckbox checkItemCheckboxnew=new CheckItemCheckbox();
            if (StringUtil.isNotEmpty(checkItemCheckbox.getId())){
                checkItemCheckboxnew=getById(checkItemCheckbox.getId());
            }
            checkItemCheckboxnew.setItemId(checkItemnew.getId());
            checkItemCheckboxnew.setOptionName(checkItemCheckbox.getOptionName());
            checkItemCheckboxnew.setOrderBy(checkItemCheckbox.getOrderBy());
            if (StringUtil.isEmpty(checkItemCheckbox.getIsNote())){
                checkItemCheckbox.setIsNote("0");
            }
            checkItemCheckboxnew.setIsNote(checkItemCheckbox.getIsNote());
            if (StringUtil.isEmpty(checkItemCheckbox.getCheckType())){
                checkItemCheckbox.setCheckType("NO");
            }
            checkItemCheckboxnew.setCheckType(checkItemCheckbox.getCheckType());
            if (StringUtil.isEmpty(checkItemCheckbox.getIsRequiredFill())){
                checkItemCheckbox.setIsRequiredFill("0");
            }
            checkItemCheckboxnew.setIsRequiredFill(checkItemCheckbox.getIsRequiredFill());
            if (StringUtil.isEmpty(checkItemCheckbox.getFraction())){
                checkItemCheckbox.setFraction("0");
            }
            checkItemCheckboxnew.setFraction(checkItemCheckbox.getFraction());
            if (StringUtil.isEmpty(checkItemCheckbox.getIsMr())){
                checkItemCheckbox.setIsMr("0");
            }
            checkItemCheckboxnew.setIsMr(checkItemCheckbox.getIsMr());
            if (StringUtil.isEmpty(checkItemCheckbox.getType())){
                checkItemCheckbox.setType("0");
            }
            checkItemCheckboxnew.setType(checkItemCheckbox.getType());
            if (StringUtil.isEmpty(checkItemCheckbox.getVisibility())){
                checkItemCheckbox.setVisibility("1");
            }
            checkItemCheckboxnew.setVisibility(checkItemCheckbox.getVisibility());
            saveOrUpdate(checkItemCheckboxnew);
            quCheckbox.add(checkItemCheckboxnew);
        }
        checkItemnew.setQuCheckboxs(quCheckbox);


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
    public void deleteCheckbox(String quItemId){
        CheckItemCheckbox quCheckbox =getById(quItemId);
        String quId=quCheckbox.getItemId();
        String orderById=quCheckbox.getOrderBy();
        removeById(quCheckbox);
        //修改排序号
//        quOrderByIdDel1(quId,orderById);

        List<CheckItemLogic> checkItemLogicList=checkItemRadioMapper.getlogicList(quItemId,quId);
        for (CheckItemLogic checkItemRadio : checkItemLogicList) {
            removeById(checkItemRadio);
        }
    }

//    public void quOrderByIdDel1(String quId,String orderById){
//        if(quId!=null && !"".equals(quId)){
//            List<CheckItemRadio> checkItemRadioList=checkItemRadioMapper.getRadioList(quId,orderById);
//            for (CheckItemRadio checkItemRadio : checkItemRadioList) {
//                Integer orderBY=Integer.valueOf(checkItemRadio.getOrderBy())-1;
//                checkItemRadio.setOrderBy(String.valueOf(orderBY));
//                saveOrUpdate(checkItemRadio);
//            }
//        }
//    }
}
