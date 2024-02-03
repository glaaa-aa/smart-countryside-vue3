package com.qfy.modules.dissension.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qfy.modules.dissension.entity.Dissension;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Description: 矛盾纠纷
 * @Author: jeecg-boot
 * @Date:   2023-12-26
 * @Version: V1.0
 */
@Repository
public interface DissensionMapper extends BaseMapper<Dissension> {

    Integer getMaxDissensionCode(String prefix);

    Map<String, Object> queryDissensionCount(String state);

}
