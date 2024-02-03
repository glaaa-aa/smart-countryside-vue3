package com.qfy.modules.smartform.service.impl;

import com.qfy.modules.smartform.entity.CheckItem;
import com.qfy.modules.smartform.entity.CheckItemLogic;
import com.qfy.modules.smartform.entity.CheckItemRadio;
import com.qfy.modules.smartform.mapper.CheckItemRadioMapper;
import com.qfy.modules.smartform.service.ICheckItemLogicService;
import com.qfy.modules.smartform.service.ICheckItemRadioService;
import com.qfy.modules.smartform.service.ICheckItemService;
import com.qfy.modules.smartform.utils.QuType;
import com.xkcoding.http.util.StringUtil;
import org.simpleframework.xml.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 单选题/下拉题
 * @Author: jeecg-boot
 * @Date:   2024-01-23
 * @Version: V1.0
 */
@Service
public class CheckItemRadioServiceImpl extends ServiceImpl<CheckItemRadioMapper, CheckItemRadio> implements ICheckItemRadioService {




    @Autowired
    private ICheckItemLogicService checkItemLogicService;

    @Autowired
    @Lazy
    private ICheckItemService checkItemService;

    /**
     * 得到某一题的选项
     */
    @Override
    public List<CheckItemRadio> findByQuId(String quId){
        List<CheckItemRadio> checkItemRadioList=baseMapper.getCheckItemRadioList(quId);
        return checkItemRadioList;
    }


    @Override
    public CheckItem addRadios(CheckItem checkItem){
        CheckItem checkItemnew=new CheckItem();
        if (StringUtil.isNotEmpty(checkItem.getId())){
            checkItemnew=checkItemService.getById(checkItem.getId());
        }
        checkItemnew.setTemplateid(checkItem.getTemplateid());
        checkItemnew.setQuTitle(checkItem.getQuTitle());
        checkItemnew.setQutag(checkItem.getQutag());
//        checkItemnew.setRemarks(checkItem.getRemarks());
        checkItemnew.setIsPic(checkItem.getIsPic());
        checkItemnew.setRemarks(checkItem.getRemarks());
        checkItemnew.setOrderById(checkItem.getOrderById());
        checkItemnew.setCheckType(String.valueOf(QuType.RADIO.getIndex()));
        checkItemnew.setChkmethod(checkItem.getChkmethod());
        checkItemnew.setHelp(checkItem.getHelp());
        checkItemnew.setItemtype(checkItem.getItemtype());
        checkItemnew.setDefaultVal(checkItem.getDefaultVal());
        checkItemnew.setBgrade(checkItem.getBgrade());
        checkItemnew.setQualifiedscore(checkItem.getQualifiedscore());
        checkItemnew.setUnqualifiedscore(checkItem.getUnqualifiedscore());
//        checkItemnew.setContactsAttr(Integer.parseInt(contactsAttr));
//        checkItemnew.setContactsField(contactsField);
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
        //添加项数据
        checkItemService.saveOrUpdate(checkItemnew);


        //处理项单项数据
        List<CheckItemRadio> quRadio=new ArrayList<CheckItemRadio>();
        for (CheckItemRadio checkItemRadio : checkItem.getQuRadios()) {
            CheckItemRadio checkItemRadionew=new CheckItemRadio();
            if (StringUtil.isNotEmpty(checkItemRadio.getId())){
                checkItemRadionew=getById(checkItemRadio.getId());
            }
//            checkItemRadionew.setq(checkItemnew.getId());
            checkItemRadionew.setItemId(checkItemnew.getId());
            checkItemRadionew.setOptionName(checkItemRadio.getOptionName());
            checkItemRadionew.setOrderBy(checkItemRadio.getOrderBy());
            if (StringUtil.isEmpty(checkItemRadio.getIsNote())){
                checkItemRadio.setIsNote("0");
            }
            checkItemRadionew.setIsNote(checkItemRadio.getIsNote());
            if (StringUtil.isEmpty(checkItemRadio.getCheckType())){
                checkItemRadio.setCheckType("NO");
            }
            checkItemRadionew.setCheckType(checkItemRadio.getCheckType());
            if (StringUtil.isEmpty(checkItemRadio.getIsRequiredFill())){
                checkItemRadio.setIsRequiredFill("0");
            }
            checkItemRadionew.setIsRequiredFill(checkItemRadio.getIsRequiredFill());
            if (StringUtil.isEmpty(checkItemRadio.getFraction())){
                checkItemRadio.setFraction("0");
            }
            checkItemRadionew.setFraction(checkItemRadio.getFraction());
            if (StringUtil.isEmpty(checkItemRadio.getIsMr())){
                checkItemRadio.setIsMr("0");
            }
            checkItemRadionew.setIsMr(checkItemRadio.getIsMr());
            if (StringUtil.isEmpty(checkItemRadio.getType())){
                checkItemRadio.setType("0");
            }
            checkItemRadionew.setType(checkItemRadio.getType());
            if (StringUtil.isEmpty(checkItemRadio.getVisibility())){
                checkItemRadio.setVisibility("1");
            }
            checkItemRadionew.setVisibility(checkItemRadio.getVisibility());
            saveOrUpdate(checkItemRadionew);
            quRadio.add(checkItemRadionew);
        }
        checkItemnew.setQuRadios(quRadio);


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
    public void deleteRadios(String quItemId){
        CheckItemRadio quRadio=getById(quItemId);
        String quId=quRadio.getItemId();
        String orderById=quRadio.getOrderBy();
        removeById(quRadio);
        //修改排序号
       quOrderByIdDel1(quId,orderById);

        List<CheckItemLogic> checkItemLogicList=baseMapper.getlogicList(quId,quItemId);
        for (CheckItemLogic checkItemRadio : checkItemLogicList) {
            removeById(checkItemRadio);
        }
    }

    public void quOrderByIdDel1(String quID,String orderById){
        if(quID!=null && !"".equals(quID)){
            List<CheckItemRadio> checkItemRadioList=baseMapper.getRadioList(quID,orderById);
            for (CheckItemRadio checkItemRadio : checkItemRadioList) {
                Integer orderBY=Integer.valueOf(checkItemRadio.getOrderBy())-1;
                checkItemRadio.setOrderBy(String.valueOf(orderBY));
                saveOrUpdate(checkItemRadio);
            }
        }
    }



}
