package com.qfy.modules.repair.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qfy.modules.repair.entity.HiddenDangerView;
import com.qfy.modules.repair.entity.RepairMo;
import java.util.List;

/**
 * @author lanls
 * @version v1.0.0
 * @since 2023/12/20 13:31
 */
public interface IHiddenDangerViewService extends IService<HiddenDangerView> {
    List<HiddenDangerView> selectList(RepairMo repairMo);
    HiddenDangerView selectById(String id);
    Long selectCount(RepairMo repairMo);
}
