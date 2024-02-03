package com.qfy.modules.dissension.service.impl;


import com.qfy.modules.dissension.entity.DissensionRecords;
import com.qfy.modules.dissension.mapper.DissensionRecordsMapper;
import com.qfy.modules.dissension.service.IDissensionRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 矛盾纠纷处理记录表
 * @Author: jeecg-boot
 * @Date:   2023-12-26
 * @Version: V1.0
 */
@Service
public class DissensionRecordsServiceImpl extends ServiceImpl<DissensionRecordsMapper, DissensionRecords> implements IDissensionRecordsService {
    @Autowired
    private DissensionRecordsMapper recordsMapper;

    @Override
    public List<DissensionRecords> selectByCode(String code) {
        return recordsMapper.selectByCode(code);
    }
}
