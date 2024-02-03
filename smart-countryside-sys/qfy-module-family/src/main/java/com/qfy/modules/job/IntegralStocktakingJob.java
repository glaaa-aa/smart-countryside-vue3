package com.qfy.modules.job;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qfy.modules.family.entity.FamilyIntegral;
import com.qfy.modules.family.entity.FamilyIntegralRecord;
import com.qfy.modules.family.entity.FamilyMember;
import com.qfy.modules.family.entity.IntegralCheckItem;
import com.qfy.modules.family.service.IFamilyIntegralService;
import com.qfy.modules.family.service.IFamilyMemberService;
import com.qfy.modules.family.service.IIntegralCheckItemService;
import com.qfy.modules.org.entity.SysOrg;
import com.qfy.modules.org.service.ISysOrgService;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.jeecg.common.constant.CommonConstant;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

/**
 * 每年积分盘点
 * @author lanls
 * @version v1.0.0
 * @since 2023/8/29 16:54
 */
public class IntegralStocktakingJob implements Job {

    public static final Logger logger = LoggerFactory.getLogger(IntegralStocktakingJob.class);

    @Resource
    private IFamilyIntegralService integralService;
    @Resource
    private ISysOrgService sysOrgService;
    @Resource
    private IIntegralCheckItemService itemService;
    @Resource
    private IFamilyMemberService memberService;
    @Value("${custom.config.tpl-id:''}")
    private String tplId; // 模板id

    // 年度遵守加分项
    private final String[] items = {"1-1","2-1","2-2","2-3","2-5","2-6","2-8","2-9","2-10","2-11","2-12","2-13","3-1"
            ,"3-2","3-3","3-4"};

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();
        String lastYear = String.valueOf(currentYear - 1);// 去年
        String beforeLastYear = String.valueOf(currentYear - 2);// 前年

        QueryWrapper<SysOrg> queryOrg = new QueryWrapper<>();
        queryOrg.lambda().eq(SysOrg::getPcode, "A02A15");// 查询拉烈镇下属20个村社区
        queryOrg.lambda().orderByAsc(SysOrg::getCode);
        queryOrg.lambda().eq(SysOrg::getTenantId, 1);
        List<SysOrg> orgList = sysOrgService.list(queryOrg);

        List<IntegralCheckItem> itemList = itemService.selectList(tplId);
        List<FamilyIntegralRecord> records = new ArrayList<>();

        QueryWrapper query = new QueryWrapper<>();
        for (SysOrg org : orgList) {// 各村盘点
            query.clear();
            query.eq("village",org.getCode());
            query.eq("data_year", lastYear);
            List<FamilyIntegral> integralList = integralService.list(query);// 查询各村去年所有农户列表

            // 没有查到农户，说明该村农户数据还未导入
            if (CollectionUtils.isEmpty(integralList)){
                continue;
            }

            for (FamilyIntegral integral : integralList) {

                // 去年的积分已盘点
                if (integral.getIsStockTake() > 0){
                    continue;
                }

                query.clear();
                List<String> years = new ArrayList<>();
                years.add(lastYear);// 去年数据
                years.add(beforeLastYear); // 前年数据
                query.eq("family_number", integral.getFamilyNumber() );
                query.in("year", years);
                List<FamilyMember> memberList = memberService.list(query);// 查询去年和前年的家庭成员信息
                Map<String,List<FamilyMember>> memberGroups = memberList.stream().collect(Collectors.groupingBy(FamilyMember::getYear));
                List<FamilyMember> members = memberGroups.get(lastYear);
                // 去年数据不存在，直接跳过
                if (CollectionUtils.isEmpty(members)){
                    continue;
                }
                // 处理年度遵守加分项
                for (String name : items) {
                    IntegralCheckItem  item = itemList.stream().filter( i -> StringUtils.equals(i.getName(),name))
                                                        .findFirst().orElse(null);
                    if (Objects.nonNull(item)){
                        FamilyIntegralRecord record = new FamilyIntegralRecord();
                        record.setChkidPlus(item.getId().toString());// 加分项id
                        record.setItemPlus(name + "，符合量化指标进行加分。"); // 加分项名称
                        record.setScorePlus(item.getValue().floatValue()); // 加分分值
                        record.setChkidReduce(null);// 减分项id
                        record.setScoreReduce(0f); // 减分分值
                        record.setItemReduce(null);// 减分项名称
                        record.setBaseReduce(0);// 基础分清零
                        record.setTotalClear(0); // 年度积分清零
                        record.setDataYear(lastYear);// 数据年份：去年
                        record.setDelFlag(CommonConstant.DEL_FLAG_0);
                        record.setFamilyManager(integral.getFamilyManager()); // 户主
                        record.setFamilyNumber(integral.getFamilyNumber()); // 农户编号
                        record.setVillage(integral.getVillage()); // 行政村
                        record.setTeam(integral.getTeam());// 自然屯
                        record.setNaturalTun(integral.getNaturalTun());// 片区
                        record.setState(1);// 已审核通过
                        record.setRemarks("年度遵守加分，当年（当月）积" + item.getValue().intValue() + "分。");

                        // 2-2按时完成城乡医疗保险缴费
                        if (StringUtils.equals("2-2",name)){
                            boolean yes = healthPay(members,record,lastYear,beforeLastYear);
                            if (!yes){
                                record.setItemReduce(name + "，超过6月底未完成整户缴纳。");
                                record.setChkidReduce(item.getId().toString());
                                record.setBaseReduce(1);
                                record.setScoreReduce(100f);
                                record.setItemPlus(null);
                                record.setChkidPlus(null);
                                record.setScorePlus(0f);
                                record.setRemarks("超时未完成整户缴纳，基础分清零，不予积分。");
                            }
                        }

                        // 2-3 积极完成农村居民养老保险缴费
                        if (StringUtils.equals("2-3",name)){
                            boolean yes = yanglaoPay(members, record);
                            if (!yes){
                                continue;
                            }
                        }

                        // 2-8 鼓励孩子升学，确保完成九年义务教育
                        if (StringUtils.equals("2-8",name)){
                            List<FamilyMember> beforeMembers = memberGroups.get(beforeLastYear);
                            if (CollectionUtils.isEmpty(beforeMembers)){
                                beforeMembers = new ArrayList<>();
                            }
                            boolean yes = educationValidate(members, record, beforeMembers);
                            // 未完成九年义务教育
                            if (!yes){
                                record.setTotalClear(1);// 年度积分清零
                                record.setScorePlus(0f);
                                record.setItemPlus(null);
                                record.setChkidPlus(null);
                                record.setChkidReduce(item.getId().toString());
                                record.setItemReduce(item.getName() + "，本年度有家庭成员未完成九年义务教育；当年积分清零。");
                                record.setScoreReduce(0f);
                                continue;
                            }
                        }

                        // 响应国家号召服兵役
                        if (StringUtils.equals("2-9", name)){
                            float scorePlus = 0f;
                            List<FamilyMember> beforeMembers = memberGroups.get(beforeLastYear);
                            for (FamilyMember member : beforeMembers) {
                                FamilyMember lastMember = members.stream().filter(m -> StringUtils.equals(m.getFamilyNumber(),member.getFamilyNumber()))
                                                            .findFirst().orElse(null);
                                if (Objects.isNull(lastMember)){
                                    continue;
                                }
                                // 上一年未服兵役，当年新入伍, 每人+300积分
                                if ((Objects.isNull(member.getEnlist()) || member.getEnlist() == 0)
                                        && (Objects.nonNull(lastMember.getEnlist()) && lastMember.getEnlist() == 1)){
                                    scorePlus += 300f;
                                }
                                // 连续多年在部队服役，每人+100积分
                                if ((Objects.nonNull(member.getEnlist()) && member.getEnlist() == 1) &&
                                        (Objects.nonNull(lastMember.getEnlist()) && lastMember.getEnlist() == 1)){
                                    scorePlus += 100f;
                                }
                            }
                            // 家庭成员没有服兵役情况，则不加份
                            if (scorePlus == 0f){
                                continue;
                            }
                        }

                        // 外出务工，勤劳致富
                        if (StringUtils.equals("2-10", name)){
                            try {
                                Calendar cal = Calendar.getInstance();
                                cal.add(Calendar.YEAR, -1);
                                cal.set(Calendar.MONTH, 11);
                                cal.set(Calendar.DAY_OF_MONTH,1);
                                Date endDate = cal.getTime();
                                float scorePlus = 0f;
                                for (FamilyMember member : members) {
                                    String workingDate = member.getWorkingDate();
                                    if (StringUtils.isNotBlank(workingDate)){
                                        Date beginDate = DateUtils.parseDate(workingDate,"yyyy-MM");
                                        long months = DateUtil.betweenMonth(beginDate,endDate,false);
                                        if (months >= 6){
                                            scorePlus += 20f;
                                        }
                                    }
                                }
                                if (scorePlus == 0f){
                                    continue;
                                }

                                record.setScorePlus(scorePlus);
                            } catch(ParseException e) {
                                logger.error("日期转换异常：", e);
                                continue;
                            }
                        }

                        records.add(record);
                    }
                }


            }
        }
    }

    /**
     * 方法描述：教育加分
     * @param lastMembers
     * @return: boolean
     * @author: lanls
     * @date: 2023/9/1 17:35
     */
    private boolean educationValidate(List<FamilyMember> lastMembers,FamilyIntegralRecord record,
                                      List<FamilyMember> beforeMembers){

        float scorePlus = 0f;
        boolean finished = true;
        for (FamilyMember member : beforeMembers) {
            // 上一年仍在读
            if (Objects.nonNull(member.getInSchool())){
                FamilyMember lastMember = lastMembers.stream().filter(f -> StringUtils.equals(member.getFamilyNumber(),
                        f.getFamilyNumber())).findFirst().orElse(null);

                if (Objects.nonNull(lastMember)){
                    // 未完成九年义务教育,已不在读
                    if (Objects.isNull(lastMember.getInSchool()) && member.getInSchool() < 6){
                        finished = false;
                        break;
                    }
                    // 已完成九年义务教育，未再继续读书
                    if (Objects.isNull(lastMember.getInSchool()) && member.getInSchool() == 6){
                        continue;
                    }
                    // 初中升到中职中专技校
                    if (member.getInSchool() == 6 && lastMember.getInSchool() == 7){
                        scorePlus += 30f;
                        continue;
                    }
                    // 初中升到高中
                    if (member.getInSchool() == 6 && lastMember.getInSchool() == 8){
                        scorePlus += 50f;
                        continue;
                    }
                    // 高中升到高职高专技师、大专
                    if (member.getInSchool() == 8 && (lastMember.getInSchool() == 9 || lastMember.getInSchool() == 10)){
                        scorePlus += 100f;
                        continue;
                    }
                    // 高中升到本科（一本）
                    if (member.getInSchool() == 8 && lastMember.getInSchool() == 11){
                        scorePlus += 300f;
                        continue;
                    }
                    // 高中升到本科（二本）
                    if (member.getInSchool() == 8 && lastMember.getInSchool() == 12){
                        scorePlus += 200f;
                        continue;
                    }
                    // 本科考上硕士研究生
                    if ((member.getInSchool() == 11 || member.getInSchool() == 12) && lastMember.getInSchool() == 13){
                        scorePlus += 600f;
                        continue;
                    }
                } else {
                    logger.error("数据不一致,【{}】不存在。", member.getName());
                }
            } else {
                FamilyMember lastMember = lastMembers.stream().filter(f -> StringUtils.equals(member.getFamilyNumber(),
                        f.getFamilyNumber())).findFirst().orElse(null);
                if (Objects.nonNull(lastMember)){
                    // 上一年未在读，今年考上研究生（硕士、博士）,+600分
                    if (Objects.nonNull(lastMember.getInSchool()) && lastMember.getInSchool() > 12){
                        scorePlus += 600f;
                    }
                } else {
                    logger.error("数据不一致,【{}】不存在。", member.getName());
                }
            }
        }

        if (finished){
            record.setScorePlus(scorePlus);
            record.setRemarks("本年度有家庭成员升学或者考上研究生。");
        }

        return finished;
    }

    /**
     * 方法描述：按时参加养老保险
     * @param members
     * @param record
     * @return: boolean
     * @author: lanls
     * @date: 2023/9/1 17:34
     */
    private boolean yanglaoPay(List<FamilyMember> members,FamilyIntegralRecord record){
        // 查找年龄大于等于16周岁的非在校生的家庭成员未参保情况
        long size = members.stream().filter(member -> {
            // 未填写参保情况
            if (Objects.isNull(member.getTownYanglao())){
                return true;
            }
            // 已缴纳
            if (member.getTownYanglao() == 1){
                return false;
            }
            // 仍是在校生
            if (Objects.nonNull(member.getInSchool()) && member.getInSchool() > 0){
                return false;
            }
            // 年龄少于16周岁
            if (Objects.nonNull(member.getS8()) && Integer.parseInt(member.getS8()) < 16){
                return false;
            }
            return true;
        }).count();

        if (size == 0){
            // 按时全员参保，+10分
            record.setScorePlus(10f);
            size = members.stream().filter(member -> {
                // 已缴纳并且是首次参保
                return member.getTownYanglao() == 1 && member.getFirstJoin() == 1;
            }).count();
            if (size > 0){
                // 年度有家庭成员首次参保，积20分
                record.setScorePlus(30f);
            }
            return true;
        }
        return false;
    }

    /**
     * 方法描述：按时完成医疗保险缴费
     * @param members
     * @param record
     * @param lastYear
     * @param beforeLastYear
     * @return: void
     * @author: lanls
     * @date: 2023/9/1 15:46
     */
    private boolean healthPay(List<FamilyMember> members, FamilyIntegralRecord record, String lastYear,
                         String beforeLastYear){
        long paySize;
        int size = members.size(); // 全户家庭成员数量
        // 判断是否是上年12月份底完成整户缴纳
        paySize = members.stream().filter(member -> {
            if (Objects.isNull(member.getS4())){
                return false;
            }
            return StringUtils.startsWith(member.getS4(),beforeLastYear);
        }).count();

        // 上年12月底完成整户缴纳,+35分
        if (size == paySize){
            record.setScorePlus(35f);
            return true;
        }

        // 判断是否每年1月底完成整户缴纳
        paySize = members.stream().filter(member -> {
            if (Objects.isNull(member.getS4())){
                return false;
            }
            return StringUtils.startsWith(member.getS4(),beforeLastYear) ||
                    StringUtils.startsWith(member.getS4(),lastYear) &&
                    StringUtils.endsWith(member.getS4(),"01");
        }).count();

        // 当年1月底完成整户缴纳,+30分
        if (size == paySize){
            record.setScorePlus(30f);
            return true;
        }

        // 判断是否每年2月底完成整户缴纳
        paySize = members.stream().filter(member -> {
            if (Objects.isNull(member.getS4())){
                return false;
            }
            return StringUtils.startsWith(member.getS4(),beforeLastYear) ||
                    StringUtils.startsWith(member.getS4(),lastYear) &&
                    StringUtils.endsWithAny(member.getS4(),new String[]{"01","02"});
        }).count();

        // 当年2月底完成整户缴纳,+25分
        if (size == paySize){
            record.setScorePlus(25f);
            return true;
        }

        // 判断是否每年3月底完成整户缴纳
        paySize = members.stream().filter(member -> {
            if (Objects.isNull(member.getS4())){
                return false;
            }
            return StringUtils.startsWith(member.getS4(),beforeLastYear) ||
                    StringUtils.startsWith(member.getS4(),lastYear) &&
                            StringUtils.endsWithAny(member.getS4(),new String[]{"01","02","03"});
        }).count();

        // 当年3月底完成整户缴纳,+20分
        if (size == paySize){
            record.setScorePlus(20f);
            return true;
        }

        // 判断是否每年4月底完成整户缴纳
        paySize = members.stream().filter(member -> {
            if (Objects.isNull(member.getS4())){
                return false;
            }
            return StringUtils.startsWith(member.getS4(),beforeLastYear) ||
                    StringUtils.startsWith(member.getS4(),lastYear) &&
                            StringUtils.endsWithAny(member.getS4(),new String[]{"01","02","03","04"});
        }).count();

        // 当年4月底完成整户缴纳,+15分
        if (size == paySize){
            record.setScorePlus(15f);
            return true;
        }

        // 判断是否每年5月底完成整户缴纳
        paySize = members.stream().filter(member -> {
            if (Objects.isNull(member.getS4())){
                return false;
            }
            return StringUtils.startsWith(member.getS4(),beforeLastYear) ||
                    StringUtils.startsWith(member.getS4(),lastYear) &&
                            StringUtils.endsWithAny(member.getS4(),new String[]{"01","02","03","04","05"});
        }).count();

        // 当年5月底完成整户缴纳,+10分
        if (size == paySize){
            record.setScorePlus(10f);
            return true;
        }

        // 判断是否每年6月底完成整户缴纳
        paySize = members.stream().filter(member -> {
            if (Objects.isNull(member.getS4())){
                return false;
            }
            return StringUtils.startsWith(member.getS4(),beforeLastYear) ||
                    StringUtils.startsWith(member.getS4(),lastYear) &&
                            StringUtils.endsWithAny(member.getS4(),new String[]{"01","02","03","04","05","06"});
        }).count();

        // 当年6月底完成整户缴纳,+5分
        if (size == paySize){
            record.setScorePlus(5f);
            return true;
        }

        return false;
    }
}
