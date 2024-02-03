package com.qfy.modules.dissension.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qfy.modules.dissension.entity.DissensionRecords;

import java.util.List;

/**
 * @Description: 矛盾纠纷处理记录表
 * @Author: jeecg-boot
 * @Date:   2023-12-26
 * @Version: V1.0
 */
public interface DissensionRecordsMapper extends BaseMapper<DissensionRecords> {

    List<DissensionRecords> selectByCode(String code);
}
