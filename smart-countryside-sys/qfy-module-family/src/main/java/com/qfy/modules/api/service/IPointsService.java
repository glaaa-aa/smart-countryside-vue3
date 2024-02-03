package com.qfy.modules.api.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qfy.modules.api.domain.Points;
import com.qfy.modules.api.domain.PointsHistory;
import com.qfy.modules.api.domain.PointsRanking;
import com.qfy.modules.api.domain.PointsRecord;
import org.jeecg.common.api.vo.Result;

/**
 * @author lanls
 * @version v1.0.0
 * @since 2023/11/17 11:47
 */
public interface IPointsService {
    IPage<PointsRanking> getPointsPages(IPage page, String realName,String familyNo,String telephone,String orgCode);
    IPage<PointsRecord> getPointsDetails(IPage page, String familyNo, String month, Integer category);
    IPage<PointsRanking> getPointsRankings(IPage page,String familyNo, String orgCode);
    Result addPoints(Points points);
//    Result getPointsItems(Integer category);
    IPage<PointsHistory> getHistoryPages(IPage page, String realName, String familyNo, String telephone, String orgCode);
    Result getPointsRecordDetails(String id);
}
