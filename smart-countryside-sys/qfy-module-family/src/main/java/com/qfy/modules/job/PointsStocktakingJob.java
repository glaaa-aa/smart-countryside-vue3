package com.qfy.modules.job;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qfy.modules.family.entity.FamilyBase;
import com.qfy.modules.family.entity.FamilyIntegral;
import com.qfy.modules.family.entity.FamilyIntegralRecord;
import com.qfy.modules.family.service.IFamilyBaseService;
import com.qfy.modules.family.service.IFamilyIntegralRecordService;
import com.qfy.modules.family.service.IFamilyIntegralService;
import com.qfy.modules.org.entity.SysOrg;
import com.qfy.modules.org.service.ISysOrgService;
import com.qfy.modules.smartform.entity.CheckItem;
import com.qfy.modules.smartform.service.ICheckItemService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.jeecg.common.constant.CommonConstant;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 积分盘点
 * @author lanls
 * @version v1.0.0
 * @since 2023/11/24 16:54
 */
@Component
public class PointsStocktakingJob implements Job {

    public static final Logger logger = LoggerFactory.getLogger(PointsStocktakingJob.class);

    @Resource
    private IFamilyIntegralService integralService;
    @Resource
    private IFamilyIntegralRecordService integralRecordService;
    @Resource
    private ISysOrgService sysOrgService;
    @Resource
    private ICheckItemService itemService;
    @Resource
    private IFamilyBaseService familyBaseService;
    @Value("${custom.config.tpl-id:''}")
    private String tplId; // 模板id
    private String orgCode;

    @SuppressWarnings({"unchecked","rawtypes"})
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        int thisYear = DateUtil.thisYear();
        QueryWrapper<SysOrg> queryOrg = new QueryWrapper<>();
        if (StringUtils.isNotBlank(orgCode)){
            queryOrg.lambda().eq(SysOrg::getCode, orgCode);
        } else {
            queryOrg.lambda().eq(SysOrg::getPcode, "A02A15");// 查询拉烈镇下属20个村社区
        }
        queryOrg.lambda().orderByAsc(SysOrg::getCode);
        List<SysOrg> orgList = sysOrgService.list(queryOrg);
        QueryWrapper wrapper = new QueryWrapper<>();
        wrapper.clear();
        wrapper.inSql("chk_item_group_id","SELECT id FROM qfy_check_item_group WHERE template_id = '" + tplId + "'");
        wrapper.orderByAsc("name");
        List<CheckItem> itemList = itemService.list(wrapper);
        for (SysOrg org : orgList) {// 各村盘点
            wrapper.clear();
            wrapper.eq("org_code",org.getCode());
            List<FamilyBase> familyList = familyBaseService.list(wrapper);// 查询该村所有农户

            // 该村农户数据还未导入
            if (CollectionUtils.isEmpty(familyList)){
                continue;
            }

            wrapper.clear();
            wrapper.eq("village",org.getCode());
            wrapper.eq("data_year", thisYear);
            List<FamilyIntegralRecord> recordList = integralRecordService.list(wrapper);//查询当年该村所有积分记录

            familyList.forEach(family -> {
                List<FamilyIntegralRecord> records = new ArrayList<>();
                // 对该村所有家庭户当年积分进行统计盘点
                itemList.forEach(item -> {
                    String id = item.getId().toString();
                    List<FamilyIntegralRecord> integralRecordList = recordList.stream()
                                                                    .filter(f -> StringUtils.equals(f.getFamilyNumber(),
                                                family.getFamilyNumber()) && (StringUtils.equals(f.getChkidPlus(), id) ||
                                                StringUtils.equals(f.getChkidReduce(),id))).collect(Collectors.toList());
                    // 该家庭户当年积分未录入，分不同情况处理
                    if (CollectionUtils.isEmpty(integralRecordList)){
                        String[] itemTypes = {CommonConstant.FOR_EVERY_YEAR,CommonConstant.FOR_EVERY_QUARTER,CommonConstant.FOR_EVERY_MONTH,CommonConstant.FOR_EVERY_DAY};
                        // 按每年加分1次、按每季度加分1次、按每月加分1次、按每日加分1次；由系统自动生成默认的加分记录
                        if (ArrayUtils.contains(itemTypes,item.getItemtype())){
                            FamilyIntegralRecord newRecord = new FamilyIntegralRecord();
                            newRecord.setFamilyNumber(family.getFamilyNumber());// 家庭户编号
                            newRecord.setChkidPlus(id);// 加分项id
                            newRecord.setItemPlus(item.getVtandardPic());// 加分事由
                            newRecord.setScorePlus(item.getQualifiedscore().floatValue());// 加分分值
                            newRecord.setFamilyManager(family.getIdCard()); // 户主身份证号
                            newRecord.setVillage(family.getOrgCode());// 行政村编码
                            newRecord.setNaturalTun(family.getS1()); // 片区编码
                            newRecord.setTeam(family.getAturalCode()); // 自然屯/队编码
                            newRecord.setState(1); // 状态 1：已审核
                            newRecord.setExt1(family.getName());// 类别：1：加分；2：扣分
                            newRecord.setExt3("1");// 类别：1：加分；2：扣分
                            newRecord.setDataYear(String.valueOf(thisYear));// 数据年份
                            newRecord.setCreateBy(family.getCreateBy());
                            newRecord.setCreateTime(new Date());
                            newRecord.setDelFlag(0);
                            newRecord.setSysOrgCode(family.getSysOrgCode());
                            records.add(newRecord);
                        }
                        // 按单次评分、按不同等级情况评分，由用户手工录入
                    } else {
                        // 按每年加分1次
                        if (StringUtils.equals(item.getItemtype(), CommonConstant.FOR_EVERY_YEAR)){
                            AtomicBoolean exist = new AtomicBoolean(false);
                            integralRecordList.forEach(f -> {
                                // 该年度已录入加分记录
                                if (StringUtils.equals(f.getExt3(),CommonConstant.SCORE_TYPE_PLUS)){
                                    exist.set(true);
                                }
                            });
                            // 该年度未录入，则生成默认的加分记录
                            if (!exist.get()){
                                FamilyIntegralRecord newRecord = new FamilyIntegralRecord();
                                newRecord.setFamilyNumber(family.getFamilyNumber());// 家庭户编号
                                newRecord.setChkidPlus(id);// 加分项id
                                newRecord.setItemPlus(item.getVtandardPic());// 加分事由
                                newRecord.setScorePlus(item.getQualifiedscore().floatValue());// 加分分值
                                newRecord.setFamilyManager(family.getIdCard()); // 户主身份证号
                                newRecord.setVillage(family.getOrgCode());// 行政村编码
                                newRecord.setNaturalTun(family.getS1()); // 片区编码
                                newRecord.setTeam(family.getAturalCode()); // 自然屯/队编码
                                newRecord.setState(1); // 状态 1：已审核
                                newRecord.setExt3(CommonConstant.SCORE_TYPE_PLUS);// 类别：1：加分；2：扣分
                                newRecord.setDataYear(String.valueOf(thisYear));// 数据年份
                                newRecord.setCreateBy(family.getCreateBy());
                                newRecord.setSysOrgCode(family.getSysOrgCode());
                                newRecord.setCreateTime(new Date());
                                newRecord.setDelFlag(0);
                                records.add(newRecord);
                            }
                        }
                        // 按每月加分1次、按每日加分1次
                        if (StringUtils.equals(item.getItemtype(), CommonConstant.FOR_EVERY_MONTH)){
                            AtomicBoolean exist = new AtomicBoolean(false);
                            integralRecordList.forEach(f -> {
                                String month = DateUtil.format(f.getCreateTime(),"yyyyMM");// 获取月份
                                String monthNow = DateUtil.format(new Date(),"yyyyMM");
                                // 该月份已录入加分记录
                                if (StringUtils.equals(month,monthNow) &&
                                        StringUtils.equals(f.getExt3(),CommonConstant.SCORE_TYPE_PLUS)){
                                    exist.set(true);
                                }
                            });
                            // 该月份未录入，则生成默认的加分记录
                            if (!exist.get()){
                                FamilyIntegralRecord newRecord = new FamilyIntegralRecord();
                                newRecord.setFamilyNumber(family.getFamilyNumber());// 家庭户编号
                                newRecord.setChkidPlus(id);// 加分项id
                                newRecord.setItemPlus(item.getVtandardPic());// 加分事由
                                newRecord.setScorePlus(item.getQualifiedscore().floatValue());// 加分分值
                                newRecord.setFamilyManager(family.getIdCard()); // 户主身份证号
                                newRecord.setVillage(family.getOrgCode());// 行政村编码
                                newRecord.setNaturalTun(family.getS1()); // 片区编码
                                newRecord.setTeam(family.getAturalCode()); // 自然屯/队编码
                                newRecord.setState(1); // 状态 1：已审核
                                newRecord.setExt3(CommonConstant.SCORE_TYPE_PLUS);// 类别：1：加分；2：扣分
                                newRecord.setDataYear(String.valueOf(thisYear));// 数据年份
                                newRecord.setCreateBy(family.getCreateBy());
                                newRecord.setSysOrgCode(family.getSysOrgCode());
                                newRecord.setCreateTime(new Date());
                                newRecord.setDelFlag(0);
                                records.add(newRecord);
                            }
                        }
                    }
                });
                // 保存积分记录
                if (!CollectionUtils.isEmpty(records)){
                    integralRecordService.saveBatch(records);
                }

                // 统计该家庭户当年总积分
                QueryWrapper query = new QueryWrapper<>();
                query.clear();
                query.eq("family_number",family.getFamilyNumber());
                query.eq("data_year", String.valueOf(thisYear));
                List<FamilyIntegralRecord> integralRecords = integralRecordService.list(query);
                if (CollectionUtils.isNotEmpty(integralRecords)){
                    query.clear();
                    query.eq("family_number",family.getFamilyNumber());
                    query.eq("data_year", String.valueOf(thisYear));
                    query.last("limit 1");
                    FamilyIntegral integral = integralService.getOne(query);
                    if (integral == null){
                        integral = new FamilyIntegral();
                    }
                    integral.setFamilyNumber(family.getFamilyNumber());
                    integral.setDataYear(String.valueOf(thisYear));
                    integral.setDelFlag(0);
                    integral.setVillage(family.getOrgCode());
                    integral.setNaturalTun(family.getS1());
                    integral.setTeam(family.getAturalCode());
                    integral.setBase(100f);
                    integral.setCreateBy(family.getCreateBy());
                    integral.setSysOrgCode(family.getSysOrgCode());
                    integral.setIsStockTake(1);
                    integral.setTemplateId(tplId);
                    integral.setFamilyManager(family.getIdCard());
                    float totalScore = 100f;// 总积分默认=100，即为基础分
                    for (FamilyIntegralRecord record : integralRecords) {
                        // 加分
                        if (StringUtils.equals(record.getExt3(),CommonConstant.SCORE_TYPE_PLUS)){
                            totalScore += record.getScorePlus();
                        } else {
                            // 扣分
                            totalScore -= record.getScoreReduce();
                        }
                    }
                    integral.setTotal(totalScore < 0 ? 0 : totalScore);
                    integralService.saveOrUpdate(integral);
                }
            });
        }
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }
}
