package com.qfy.modules.smartform.service.impl;


import com.qfy.modules.smartform.entity.CheckItemLogic;
import com.qfy.modules.smartform.entity.CheckItemOrderby;
import com.qfy.modules.smartform.entity.CheckItemScore;
import com.qfy.modules.smartform.mapper.CheckItemLogicMapper;
import com.qfy.modules.smartform.service.ICheckItemLogicService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 题目逻辑设置
 * @Author: jeecg-boot
 * @Date:   2024-01-23
 * @Version: V1.0
 */
@Service
public class CheckItemLogicServiceImpl extends ServiceImpl<CheckItemLogicMapper, CheckItemLogic> implements ICheckItemLogicService {



    /**
     * 得到某一题的选项
     */
    @Override
    public List<CheckItemLogic> findByCkQuId(String quId){
        List<CheckItemLogic> checkItemRadioList=baseMapper.getCheckItemLogicList(quId);
        return checkItemRadioList;
    }

    @Override
    public void delectLogic(String quItemId){
        CheckItemLogic quLogic =getById(quItemId);
        removeById(quLogic);
    }

}
