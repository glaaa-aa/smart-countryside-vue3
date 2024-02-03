package com.qfy.modules.smartform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qfy.modules.smartform.entity.CheckItemLogic;
import com.qfy.modules.smartform.entity.CheckItemOrderby;

import java.util.List;

/**
 * @Description: 题目逻辑设置
 * @Author: jeecg-boot
 * @Date:   2024-01-23
 * @Version: V1.0
 */
public interface ICheckItemLogicService extends IService<CheckItemLogic> {

    public List<CheckItemLogic> findByCkQuId(String quId);



    public  void delectLogic(String quId);

}
