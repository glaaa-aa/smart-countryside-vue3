package com.qfy.modules.smartform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qfy.modules.smartform.entity.CheckItem;
import com.qfy.modules.smartform.entity.CheckItemCheckbox;
import com.qfy.modules.smartform.entity.CheckItemMultiFillblank;

import java.util.List;

/**
 * @Description: 多项填空题
 * @Author: jeecg-boot
 * @Date:   2024-01-23
 * @Version: V1.0
 */
public interface ICheckItemMultiFillblankService extends IService<CheckItemMultiFillblank> {

    public List<CheckItemMultiFillblank> findByQuId(String quId);


    public CheckItem addMultiFillblank(CheckItem checkItem);

    public  void deleteMultiFillblank(String quItemId);



}
