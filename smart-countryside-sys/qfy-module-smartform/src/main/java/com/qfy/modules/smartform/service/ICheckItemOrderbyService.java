package com.qfy.modules.smartform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qfy.modules.smartform.entity.CheckItem;
import com.qfy.modules.smartform.entity.CheckItemOrderby;
import com.qfy.modules.smartform.entity.CheckItemScore;

import java.util.List;

/**
 * @Description: 排序题
 * @Author: jeecg-boot
 * @Date:   2024-01-23
 * @Version: V1.0
 */
public interface ICheckItemOrderbyService extends IService<CheckItemOrderby> {


    public List<CheckItemOrderby> findByQuId(String quId);


    public CheckItem addOrderby(CheckItem checkItem);

    public  void deleteOrderby(String quItemId);
}
