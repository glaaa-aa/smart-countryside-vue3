package com.qfy.modules.smartform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qfy.modules.smartform.entity.CheckItem;
import com.qfy.modules.smartform.entity.CheckItemMultiFillblank;
import com.qfy.modules.smartform.entity.CheckItemScore;

import java.util.List;

/**
 * @Description: 评分题 行选项
 * @Author: jeecg-boot
 * @Date:   2024-01-23
 * @Version: V1.0
 */
public interface ICheckItemScoreService extends IService<CheckItemScore> {


    public List<CheckItemScore> findByQuId(String quId);

    public CheckItem addScore(CheckItem checkItem);

    public  void deleteScore(String quItemId);

}
