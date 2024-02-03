package com.qfy.modules.subsidy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qfy.modules.subsidy.entity.TrafficRoad;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * @Description: 道路交通
 * @Author: lanls
 * @Date:   2022-08-26
 * @Version: V1.0
 */
public interface TrafficRoadMapper extends BaseMapper<TrafficRoad> {

    /**
     * 查询已硬化道路及护栏和电商网点数量
     * @return
     */
    @Select(" select sum(road_length) AS roadLength,sum(s3) AS guardrailLength , (select count(*)from qfy_electronic_commerce) as commerce from qfy_traffic_road" +
            " where build_material =1 and s2 =1")
    Map<String, Integer> queryRoadLengthAndCommerceTotal();

}
