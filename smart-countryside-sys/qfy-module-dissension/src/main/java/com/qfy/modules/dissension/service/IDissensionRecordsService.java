package com.qfy.modules.dissension.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.qfy.modules.dissension.entity.DissensionRecords;

import java.util.List;

/**
 * @Description: 矛盾纠纷处理记录表
 * @Author: jeecg-boot
 * @Date:   2023-12-26
 * @Version: V1.0
 */
public interface IDissensionRecordsService extends IService<DissensionRecords> {

    List<DissensionRecords> selectByCode(String code);
}
