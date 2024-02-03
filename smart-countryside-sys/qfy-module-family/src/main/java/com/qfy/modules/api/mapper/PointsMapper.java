package com.qfy.modules.api.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qfy.modules.api.domain.PointsHistory;
import com.qfy.modules.api.domain.PointsRanking;
import org.apache.ibatis.annotations.Param;

/**
 * @author lanls
 * @version v1.0.0
 * @since 2023/11/17 15:33
 */
public interface PointsMapper extends BaseMapper<PointsRanking> {

    IPage<PointsRanking> selectPointsRankings(IPage<PointsRanking> page, @Param("ew") Wrapper wrapper);

    IPage<PointsRanking> pointsRankings(IPage<PointsRanking> page,
                                        @Param("familyNo") String familyNo,
                                        @Param("orgCode") String orgCode,
                                        @Param("year") String year);

    IPage<PointsHistory> selectHistoryPages(IPage<PointsHistory> page, @Param("ew") Wrapper wrapper);
}
