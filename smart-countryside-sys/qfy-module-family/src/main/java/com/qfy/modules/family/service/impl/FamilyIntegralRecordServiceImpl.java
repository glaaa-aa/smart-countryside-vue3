package com.qfy.modules.family.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qfy.modules.family.entity.FamilyIntegralRecord;
import com.qfy.modules.family.mapper.FamilyIntegralRecordMapper;
import com.qfy.modules.family.service.IFamilyIntegralRecordService;
import com.qfy.modules.smartform.entity.CheckItem;
import com.qfy.modules.smartform.entity.CheckList;
import com.qfy.modules.smartform.mapper.CheckItemMapper;
import com.qfy.modules.smartform.mapper.CheckListMapper;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.vo.LoginUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: 农户积分申请记录
 * @Author: jeecg-boot
 * @Date:   2022-12-01
 * @Version: V1.0
 */
@Service
public class FamilyIntegralRecordServiceImpl extends ServiceImpl<FamilyIntegralRecordMapper, FamilyIntegralRecord> implements IFamilyIntegralRecordService {

    @Resource
    private CheckListMapper checkListMapper;
    @Resource
    private CheckItemMapper itemMapper;

//    @Override
//    @Transactional
//    public Result review(FamilyIntegralRecord record) {
//        LoginUser user = (LoginUser) SecurityUtils.getSubject().getPrincipal();
//        UpdateWrapper<FamilyIntegralRecord> update = new UpdateWrapper<>();
//        update.lambda().eq(FamilyIntegralRecord::getId, record.getId());
//        update.lambda().set(FamilyIntegralRecord::getChecker, user.getUsername());
//        update.lambda().set(FamilyIntegralRecord::getCheckRemarks, record.getCheckRemarks());
//        update.lambda().set(FamilyIntegralRecord::getCheckTime, new Date());
//        update.lambda().set(FamilyIntegralRecord::getState, record.getState());
//        baseMapper.update(record,update);
//        // 通过审核，根据加减分项目进行加分或者减分
//        if (record.getState().intValue() == FamilyIntegralRecord.PASS){
//            String itemId = "";
//            CheckList checkList = new CheckList();
//            if (StringUtils.isNotBlank(record.getChkidPlus())){
//                itemId = record.getChkidPlus();
//                checkList.setResults(record.getScorePlus().toString());
//                checkList.setScore(record.getScorePlus().doubleValue());
//                checkList.setScoreType(1);// 如 -1 为扣分类型， 1 为加分类型
//            } else {
//                itemId = record.getChkidReduce();
//                checkList.setResults(record.getScoreReduce().toString());
//                checkList.setScore(record.getScoreReduce().doubleValue());
//                checkList.setScoreType(-1);// 如 -1 为扣分类型， 1 为加分类型
//            }
//
//            CheckItem item = itemMapper.selectById(itemId);
//            checkList.setItemName(item.getName());
//            checkList.setChkid(item.getId());
//            checkList.setIsvalid(1);
//            checkList.setType(item.getFormtype());
//            checkList.setRemark(record.getCheckRemarks());
//            checkList.setBelong(record.getFamilyNumber());// 存储农户编号，数据归属于指定农户
//            // 存储年份，评分结果数据属于当年
//            checkList.setYears(String.valueOf(Calendar.getInstance(Locale.CHINA).get(Calendar.YEAR)));
//            checkListMapper.insert(checkList);
//        }
//        return Result.OK("审批完成!");
//    }

//    @Override
//    @Transactional
//    public Result cancel(FamilyIntegralRecord record) {
//        LoginUser user = (LoginUser) SecurityUtils.getSubject().getPrincipal();
//        UpdateWrapper<FamilyIntegralRecord> update = new UpdateWrapper<>();
//        update.lambda().eq(FamilyIntegralRecord::getId, record.getId());
//        update.lambda().set(FamilyIntegralRecord::getCancelOperator, user.getUsername());
//        update.lambda().set(FamilyIntegralRecord::getCancelRemarks, record.getCancelRemarks());
//        update.lambda().set(FamilyIntegralRecord::getCancelTime, new Date());
//        update.lambda().set(FamilyIntegralRecord::getState, FamilyIntegralRecord.CANCEL);
//        baseMapper.update(record,update);
//        // 撤销已审核通过的事项，根据加减分项目进行加分或者减分
//        if (record.getState().intValue() == FamilyIntegralRecord.CANCEL){
//            String itemId = "";
//            CheckList checkList = new CheckList();
//            // 如果是加分项目，则撤销加分，变成减分
//            if (StringUtils.isNotBlank(record.getChkidPlus())){
//                itemId = record.getChkidPlus();
//                checkList.setResults(record.getScorePlus().toString());
//                checkList.setScore(record.getScorePlus().doubleValue());
//                checkList.setScoreType(-1);// 如 -1 为扣分类型， 1 为加分类型
//            } else {
//                // 如果是减分项目，则撤销减分，变成加分
//                itemId = record.getChkidReduce();
//                checkList.setResults(record.getScoreReduce().toString());
//                checkList.setScore(record.getScoreReduce().doubleValue());
//                checkList.setScoreType(1);// 如 -1 为扣分类型， 1 为加分类型
//            }
//
//            CheckItem item = itemMapper.selectById(itemId);
//            checkList.setItemName(item.getName());
//            checkList.setChkid(item.getId());
//            checkList.setIsvalid(1);
//            checkList.setType(item.getFormtype());
//            checkList.setRemark(record.getCancelRemarks());
//            checkList.setBelong(record.getFamilyNumber());// 存储农户编号，数据归属于指定农户
//            // 存储年份，评分结果数据属于当年
//            checkList.setYears(String.valueOf(Calendar.getInstance(Locale.CHINA).get(Calendar.YEAR)));
//            checkListMapper.insert(checkList);
//        }
//        return Result.OK("撤销成功!");
//    }
}
