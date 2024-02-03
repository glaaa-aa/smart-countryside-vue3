package com.qfy.modules.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qfy.modules.api.domain.Points;
import com.qfy.modules.api.domain.PointsHistory;
import com.qfy.modules.api.domain.PointsItem;
import com.qfy.modules.api.domain.PointsRanking;
import com.qfy.modules.api.domain.PointsRecord;
import com.qfy.modules.api.mapper.PointsMapper;
import com.qfy.modules.api.service.IPointsService;
import com.qfy.modules.family.entity.FamilyBase;
import com.qfy.modules.family.entity.FamilyIntegral;
import com.qfy.modules.family.entity.FamilyIntegralRecord;
import com.qfy.modules.family.mapper.FamilyBaseMapper;
import com.qfy.modules.family.mapper.FamilyIntegralMapper;
import com.qfy.modules.family.mapper.FamilyIntegralRecordMapper;
import com.qfy.modules.smartform.entity.CheckItem;
import com.qfy.modules.smartform.mapper.CheckItemMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.Resource;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author lanls
 * @version v1.0.0
 * @since 2023/11/17 11:48
 */
@Service
public class PointsServiceImpl implements IPointsService {

    public static final Logger logger = LoggerFactory.getLogger(PointsServiceImpl.class);

    @Resource
    private FamilyIntegralRecordMapper integralRecordMapper;
    @Resource
    private PointsMapper pointsMapper;
    @Resource
    private FamilyBaseMapper familyBaseMapper;
    @Resource
    private CheckItemMapper checkItemMapper;
    @Resource
    private FamilyIntegralMapper integralMapper;

    @Value("${custom.config.tpl-id}")
    private String templateId;

    @Override
    public IPage<PointsRanking> getPointsPages(IPage page, String realName,String familyNo,String telephone, String orgCode) {
        QueryWrapper wapper = new QueryWrapper<>();
        wapper.apply("a.family_number = b.family_number");
        wapper.likeRight("a.village", orgCode);
        wapper.eq("a.data_year", DateUtils.formatDate("yyyy"));
        if (Objects.nonNull(familyNo)){
            wapper.like("a.family_number", familyNo);
        }
        if (StringUtils.isNotBlank(realName)){
            wapper.like("b.name", realName);
        }
        if (StringUtils.isNotBlank(telephone)){
            wapper.like("b.telephone", telephone);
        }
        wapper.orderByDesc("a.total");
        IPage<PointsRanking> result = pointsMapper.selectPointsRankings(page,wapper);
        AtomicLong sort = new AtomicLong((page.getCurrent() - 1) * page.getSize());
        result.getRecords().forEach(item -> {
            sort.getAndIncrement();
            item.setSort(sort.get());
        });
        return result;
    }

    @Override
    public IPage<PointsRecord> getPointsDetails(IPage page, String familyNo, String month, Integer category) {
        QueryWrapper<FamilyIntegralRecord> wapper = new QueryWrapper<>();
        wapper.lambda().eq(FamilyIntegralRecord::getFamilyNumber, familyNo);
        wapper.lambda().orderByAsc(FamilyIntegralRecord::getCreateTime);
        if (Objects.nonNull(category) && category != 0){
            wapper.lambda().eq(FamilyIntegralRecord::getExt3, category.toString());
        }
        if (StringUtils.isNotBlank(month)){
            wapper.eq("date_format(create_time,'%Y-%m')", month);
        }
        IPage<FamilyIntegralRecord> result = integralRecordMapper.selectPage(page,wapper);
        List<PointsRecord> records = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(result.getRecords())){
            result.getRecords().forEach(item -> {
                PointsRecord record = new PointsRecord();
                record.setId(item.getId());
                record.setFamilyNo(item.getFamilyNumber());
                record.setDate(item.getCreateTime());
                if (StringUtils.isNotBlank(item.getChkidPlus())){
                    record.setChkId(item.getChkidPlus());
                    record.setCategoryName("加分");
                    record.setScore(item.getScorePlus());
                    record.setReason(item.getItemPlus());
                } else {
                    record.setChkId(item.getChkidReduce());
                    record.setCategoryName("扣分");
                    record.setScore(item.getScoreReduce());
                    record.setReason(item.getItemReduce());
                }
                if (StringUtils.isNotBlank(item.getChkidReduce())){
                    record.setChkId(item.getChkidReduce());
                    record.setCategoryName("扣分");
                    record.setScore(item.getScoreReduce());
                    record.setReason(item.getItemReduce());
                } else {
                    record.setChkId(item.getChkidPlus());
                    record.setCategoryName("加分");
                    record.setScore(item.getScorePlus());
                    record.setReason(item.getItemPlus());
                }
                record.setCategory(Integer.valueOf(item.getExt3() == null? "0":item.getExt3() )); //0 查询所有数据
                records.add(record);
            });
        }
        IPage<PointsRecord> resultPage = new Page<>(page.getCurrent(), page.getSize(), result.getTotal());
        resultPage.setRecords(records);
        return resultPage;
    }

    @Override
    public IPage<PointsRanking> getPointsRankings(IPage page, String familyNo, String orgCode) {

        String year = DateUtils.formatDate("yyyy");
        IPage<PointsRanking> result = pointsMapper.pointsRankings(page,familyNo,orgCode,year);
        AtomicLong sort = new AtomicLong((page.getCurrent() - 1) * page.getSize());
        result.getRecords().forEach(item -> {
            sort.getAndIncrement();
            item.setSort(sort.get());
        });
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result addPoints(Points points) {
        QueryWrapper<FamilyBase> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(FamilyBase::getFamilyNumber, points.getFamilyNo());
        wrapper.last("limit 1");
        FamilyBase family = familyBaseMapper.selectOne(wrapper);

        QueryWrapper<FamilyIntegral> queryWrapper = new QueryWrapper<FamilyIntegral>().eq("family_number", points.getFamilyNo());

        FamilyIntegral familyIntegral = integralMapper.selectOne(queryWrapper);

        FamilyIntegralRecord record = new FamilyIntegralRecord();
        record.setFamilyNumber(points.getFamilyNo());
        record.setVillage(family.getOrgCode());
        record.setNaturalTun(family.getS1());
        record.setTeam(family.getAturalCode());
        // 加分
        if (StringUtils.equals("1",points.getCategory())){
            record.setScorePlus(Float.parseFloat(points.getScore()));
            record.setItemPlus(points.getReason());
            record.setChkidPlus(points.getCkId());
            familyIntegral.setTotal(familyIntegral.getTotal()+ record.getScorePlus().longValue());
            integralMapper.updateById(familyIntegral);
        } else {
            record.setScoreReduce(Float.parseFloat(points.getScore()));
            record.setItemReduce(points.getReason());
            record.setChkidReduce(points.getCkId());
            familyIntegral.setTotal(familyIntegral.getTotal()-record.getScoreReduce().longValue());
            integralMapper.updateById(familyIntegral);
        }

        record.setFamilyManager(points.getUid());
        record.setRemarks(points.getCkName());
        record.setExt1(points.getRealname());
        if (StringUtils.isBlank(points.getYear())){
            record.setDataYear(DateUtils.formatDate("yyyy"));
        } else if (StringUtils.endsWith(points.getYear(),"年")){
            record.setDataYear(points.getYear().substring(0,points.getYear().length()-1));
        }
        record.setState(1);
        record.setCheckRemarks(points.getPhone());// 暂存联系电话
        record.setExt2(points.getWorkImgs()); // 图片等附件路径
        record.setExt3(points.getCategory());// 评分类别： 1 加分 2 扣分
        try {
            integralRecordMapper.insert(record);
        } catch (Exception e){
            logger.error("添加积分记录失败：",e);
            return Result.error("积分操作失败");
        }
        return Result.OK();
    }

//    @Override
//    public Result getPointsItems(Integer category) {
//        QueryWrapper<CheckItem> wapper = new QueryWrapper<>();
////        wapper.lambda().eq(CheckItem::getCategory, category);// 暂时无法分类
//        wapper.lambda().inSql(CheckItem::getChkItemGroupId,"SELECT id FROM qfy_check_item_group " +
//                "WHERE template_id = '" + templateId + "'");
//        wapper.lambda().orderByAsc(CheckItem::getId);
//        List<CheckItem> itemList = checkItemMapper.selectList(wapper);
//        List<PointsItem> items = new ArrayList<>();
//        if (CollectionUtils.isNotEmpty(itemList)){
//            itemList.forEach(item -> {
//                PointsItem pointsItem = new PointsItem();
//                pointsItem.setChkId(item.getId().toString());
//                pointsItem.setName(item.getName());
//                pointsItem.setAlias(item.getDefaultVal());
//                // 加分
//                if (category == 1){
//                    pointsItem.setScore(Objects.isNull(item.getQualifiedscore()) ? 0 : item.getQualifiedscore().floatValue());
//                    pointsItem.setContent(item.getVtandardPic());
//                } else {
//                    pointsItem.setScore(Objects.isNull(item.getUnqualifiedscore())? 0 : item.getUnqualifiedscore().floatValue());
//                    pointsItem.setContent(item.getViolationPic());
//                }
//                pointsItem.setCategory(category);
//                pointsItem.setRemark(item.getRemark());
//                items.add(pointsItem);
//            });
//        }
//        return Result.OK(items);
//    }

    @Override
    public IPage<PointsHistory> getHistoryPages(IPage page, String realName, String familyNo, String telephone, String orgCode) {
        QueryWrapper<FamilyIntegralRecord> wrapper = new QueryWrapper<>();
        wrapper.apply("a.family_number = b.family_number");
        wrapper.and(w -> w.apply("a.chkid_plus = c.id").or().apply("a.chkid_reduce = c.id"));
        if(StringUtils.isNotBlank(familyNo)){
            wrapper.like("a.family_number", familyNo);
        }
        if (StringUtils.isNotBlank(realName)){
            wrapper.like("b.name", realName);
        }
        if (StringUtils.isNotBlank(telephone)){
            wrapper.like("b.telephone", telephone);
        }
        if (StringUtils.isNotBlank(orgCode)){
            wrapper.eq("b.org_code", orgCode);
        }
        wrapper.orderByDesc("a.create_time");
        return pointsMapper.selectHistoryPages(page,wrapper);
    }

    @Override
    public Result getPointsRecordDetails(String id) {
        return null;
    }
}
