package com.qfy.modules.lt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qfy.modules.lt.entity.Hymc;
import com.qfy.modules.lt.mapper.HymcMapper;
import com.qfy.modules.lt.service.IHymcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 会议管理
 * @Author: jeecg-boot
 * @Date:   2022-04-05
 * @Version: V1.0
 */
@Service
public class HymcServiceImpl extends ServiceImpl<HymcMapper, Hymc> implements IHymcService {
    @Autowired
    private HymcMapper hymcMapper;
    /**
     * 获取当前生效的会议
     */
    public Hymc getCurrentHy() {
        Hymc rtn = new Hymc();
        QueryWrapper<Hymc> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("hydate");   // 按会议日期倒序，
        List<Hymc> list = hymcMapper.selectList(wrapper);

        if(list.size() > 0) {
            rtn = list.get(0); // 先取第一个实例
            for(Hymc bean : list) {
                if(bean.getBmState().intValue() == 0) { // 取倒序的第一个未关闭的会议，结束循环
                    rtn = bean;
                    break;
                }
            }
        }

        return rtn;
    }
}
