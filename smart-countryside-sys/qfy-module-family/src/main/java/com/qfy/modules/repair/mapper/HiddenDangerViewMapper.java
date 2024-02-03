package com.qfy.modules.repair.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qfy.modules.repair.entity.HiddenDangerView;
import com.qfy.modules.repair.entity.RepairMo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * @author lanls
 * @version v1.0.0
 * @since 2023/12/20 13:33
 */
public interface HiddenDangerViewMapper extends BaseMapper<HiddenDangerView> {
    List<HiddenDangerView> selectList(RepairMo repairMo);
    HiddenDangerView selectById(@Param("id") String id);
    Long selectCount(@Param("repairMo") RepairMo repairMo);
}
