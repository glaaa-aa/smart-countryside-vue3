package com.qfy.modules.smartform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qfy.modules.smartform.entity.CheckItem;
import com.qfy.modules.smartform.entity.CheckItemRadio;

import java.util.List;

/**
 * @Description: 单选题/下拉题
 * @Author: jeecg-boot
 * @Date:   2024-01-23
 * @Version: V1.0
 */
public interface ICheckItemRadioService extends IService<CheckItemRadio> {


    public List<CheckItemRadio> findByQuId(String quId);

    public  CheckItem addRadios(CheckItem checkItem);

    public  void deleteRadios(String quItemId);

}
