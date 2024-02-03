package com.qfy.modules.smartform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qfy.modules.smartform.entity.CheckItem;
import com.qfy.modules.smartform.entity.CheckItemCheckbox;
import com.qfy.modules.smartform.entity.CheckItemRadio;

import java.util.List;

/**
 * @Description: 多选填空题
 * @Author: jeecg-boot
 * @Date:   2024-01-23
 * @Version: V1.0
 */
public interface ICheckItemCheckboxService extends IService<CheckItemCheckbox> {


    public List<CheckItemCheckbox> findByQuId(String quId);


    public CheckItem addCheckbox(CheckItem checkItem);

    public  void deleteCheckbox(String quItemId);
}
