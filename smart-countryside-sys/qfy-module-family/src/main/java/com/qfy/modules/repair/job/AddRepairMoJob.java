package com.qfy.modules.repair.job;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.qfy.modules.repair.entity.RepairMo;
import com.qfy.modules.repair.entity.RepairMoLog;
import com.qfy.modules.repair.enums.RepairMoStateEnum;
import com.qfy.modules.repair.service.IDangerAddJobLogService;
import com.qfy.modules.repair.service.IRepairMoLogService;
import com.qfy.modules.repair.service.IRepairMoService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.jeecg.common.constant.CommonConstant;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

/**
 * @author lanls
 * @version 1.0
 * @date 2023/5/16 18:37
 */
public class AddRepairMoJob implements Job, Serializable {

    public static final Logger logger = LoggerFactory.getLogger(AddRepairMoJob.class);

    private static final long serialVersionUID = 6817937137754678595L;

//    @Resource
//    private IAndonDriverService driverService;
//    @Resource
//    private IAndonCarService carService;
//    @Resource
//    private IAndonRoadService roadService;
//    @Resource
//    private IAndonCompanyService companyService;
    @Resource
    private IRepairMoService repairMoService;
    @Resource
    private IRepairMoLogService repairMoLogService;
    @Resource
    private IDangerAddJobLogService addJobLogService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

//        String driverSql = "1=1 and (SELECT count(id) FROM qfy_andon_repair_mo b WHERE " +
//                "qfy_andon_call_driver.call_code = b.src_doc_code) = 0";
//        QueryWrapper wrapper = new QueryWrapper();
////        wrapper.notInSql("call_code", "SELECT src_doc_code FROM qfy_andon_repair_mo WHERE del_flag = 0");
//        wrapper.apply(driverSql);
//        wrapper.eq("del_flag", "0");
//        List<AndonDriver> driverList = driverService.list(wrapper); // 尚未生成隐患任务的驾驶人隐患信息
//        wrapper.clear();
//        String carSql = "1=1 and (SELECT count(id) FROM qfy_andon_repair_mo b WHERE " +
//                "qfy_andon_call_car.call_code = b.src_doc_code) = 0";
//        wrapper.apply(carSql);
//        wrapper.eq("del_flag", "0");
//        List<AndonCar> carList = carService.list(wrapper);// 尚未生成隐患任务的车辆隐患信息
//        wrapper.clear();
//        String roadSql = "1=1 and (SELECT count(id) FROM qfy_andon_repair_mo b WHERE " +
//                "qfy_andon_call_road.call_code = b.src_doc_code) = 0";
//        wrapper.apply(roadSql);
//        wrapper.eq("del_flag", "0");
//        List<AndonRoad> roadList = roadService.list(wrapper);// 尚未生成隐患任务的道路隐患信息
//        wrapper.clear();
//        String companySql = "1=1 and (SELECT count(id) FROM qfy_andon_repair_mo b WHERE " +
//                "qfy_andon_call_company.call_code = b.src_doc_code) = 0";
//        wrapper.apply(companySql);
//        wrapper.eq("del_flag", "0");
//        List<AndonCompany> companyList = companyService.list(wrapper);// 尚未生成隐患任务的企业隐患信息
//
//        List<RepairMo> repairMoList = new ArrayList<>();
//        List<RepairMoLog> repairMoLogList = new ArrayList<>();
//        List<HiddenDanger> dangerList = new ArrayList<>();
//
//        for (AndonDriver driver : driverList) {
//            HiddenDanger danger = new HiddenDanger();
//            BeanUtils.copyProperties(driver,danger);
//            dangerList.add(danger);
//        }
//
//        for (AndonCar car : carList) {
//            HiddenDanger danger = new HiddenDanger();
//            BeanUtils.copyProperties(car,danger);
//            dangerList.add(danger);
//        }
//
//        for (AndonRoad road : roadList) {
//            HiddenDanger danger = new HiddenDanger();
//            BeanUtils.copyProperties(road,danger);
//            dangerList.add(danger);
//        }
//
//        for (AndonCompany company : companyList) {
//            HiddenDanger danger = new HiddenDanger();
//            BeanUtils.copyProperties(company,danger);
//            dangerList.add(danger);
//        }
//
//        // 生成驾驶人、车辆、道路、企业隐患信息任务及上报日志
//        for (HiddenDanger danger : dangerList) {
//            RepairMo mo = new RepairMo();
//            BeanUtils.copyProperties(danger, mo);
//            mo.setSrcDocCode(danger.getCallCode());
//            switch (danger.getCallType()){
//                case "1":
//                    mo.setSrcDocType(AndonCallTypeEnum.driver.getValue());
//                    mo.setMoCode(AndonCallTypeEnum.driver.getPrefix()+"_"+ danger.getCallCode());
//                    break;
//                case "2":
//                    mo.setSrcDocType(AndonCallTypeEnum.car.getValue());
//                    mo.setMoCode(AndonCallTypeEnum.car.getPrefix()+"_"+ danger.getCallCode());
//                    break;
//                case "3":
//                    mo.setSrcDocType(AndonCallTypeEnum.road.getValue());
//                    mo.setMoCode(AndonCallTypeEnum.road.getPrefix()+"_"+ danger.getCallCode());
//                    break;
//                case "4":
//                    mo.setSrcDocType(AndonCallTypeEnum.company.getValue());
//                    mo.setMoCode(AndonCallTypeEnum.company.getPrefix()+"_"+ danger.getCallCode());
//                    break;
//            }
//
//            mo.setMoState(RepairMoStateEnum.NEW.getValue());
//            mo.setCatalog(String.valueOf(RepairMoStateEnum.ONLINE.getValue()));
//            Calendar cal = Calendar.getInstance();
//            cal.setTime(danger.getCreateTime());
//            mo.setWeixStdate(DateUtils.formatDate(cal, "yyyy-MM-dd HH:mm"));
//            cal.add(Calendar.YEAR,1);// 一年期限
//            mo.setResolutionTime(DateUtils.formatDate(cal, "yyyy-MM-dd HH:mm"));
//            mo.setWeixEddate(DateUtils.formatDate(cal, "yyyy-MM-dd HH:mm"));
//            mo.setId(null);
//            mo.setIsOuts(0);
//            mo.setUpdateBy(null);
//            mo.setUpdateTime(null);
//            repairMoList.add(mo);
//
//            RepairMoLog moLog = new RepairMoLog();
//            moLog.setMoCode(mo.getMoCode());
//            String name="{'id':'" + danger.getId() + "','type':'" + danger.getCallType() + "'}";
//            moLog.setOptDataJson(JSON.toJSON(name));
//            moLog.setOptId(danger.getNotifier());
//            moLog.setOptName(danger.getNotifierName());
//            moLog.setResultMsg("隐患上报");
//            moLog.setOptType("0");
//            moLog.setOptContent(danger.getNotifierName() + "上报了隐患信息，处理人待分派。");
//            moLog.setS1(String.valueOf(CommonConstant.STATE_1));
//            moLog.setCreateTime(danger.getCreateTime());
//
//            repairMoLogList.add(moLog);
//        }
//
//        if (CollectionUtils.isNotEmpty(repairMoList)){
//            repairMoService.saveBatch(repairMoList);// 批量保存隐患任务
//            repairMoLogService.saveBatch(repairMoLogList);// 批量保存隐患任务初始日志
//
//            List<DangerAddJobLog> jobLogList = new ArrayList<>();
//            Date date = new Date();
//            for (RepairMo mo : repairMoList) {
//                DangerAddJobLog jobLog = new DangerAddJobLog();
//                jobLog.setJobId(mo.getId());
//                jobLog.setType("1");
//                jobLog.setCreateTime(date);
//                jobLogList.add(jobLog);
//            }
//
//            for (RepairMoLog moLog : repairMoLogList) {
//                DangerAddJobLog jobLog = new DangerAddJobLog();
//                jobLog.setLogId(moLog.getId());
//                jobLog.setType("2");
//                jobLog.setCreateTime(date);
//                jobLogList.add(jobLog);
//            }
//
//            addJobLogService.saveBatch(jobLogList);
//
//        }

        /**
         * 第一步，先查所有没有分类的任务，即catalog为空
         * 第二步，创建分类任务，区分两种情况：一、未分派的任务，更新为现场任务。二、已分派的任务，更新为指派任务，并且重新添加一条现场处理任务。
         * 第三步，给所有没有分类的任务，增加任务日志。
         */

        QueryWrapper<RepairMo> query = new QueryWrapper<>();
        query.lambda().eq(RepairMo::getDelFlag, CommonConstant.DEL_FLAG_0);
        query.lambda().isNull(RepairMo::getCatalog);
        QueryWrapper<RepairMo> cloneQuery = query.clone(); // 克隆相同的查询条件
        query.lambda().inSql(RepairMo::getSrcDocCode,"SELECT src_doc_code FROM qfy_andon_repair_mo " +
                "WHERE del_flag = 0 GROUP BY src_doc_code HAVING count(src_doc_code) = 3");
        List<RepairMo> list = repairMoService.list(query);
        if (CollectionUtils.isNotEmpty(list)){
            UpdateWrapper<RepairMo> update = new UpdateWrapper<>();
            update.lambda().set(RepairMo::getDelFlag, CommonConstant.DEL_FLAG_1);
            update.lambda().in(RepairMo::getId,list.stream().map(RepairMo::getId).collect(Collectors.toList()));
            repairMoService.update(update);// 将多余的无效任务删除掉。
        }

        List<RepairMo> moList = repairMoService.list(cloneQuery);// 先查所有没有分类的任务，即catalog为空
        if (CollectionUtils.isEmpty(moList)){
            return;
        }
        List<RepairMo> newMoList = new ArrayList<>();
        List<RepairMoLog> moLogList = new ArrayList<>();
        for (RepairMo mo : moList) {
            RepairMoLog moLog = new RepairMoLog();
            moLog.setMoCode(mo.getMoCode());
            String name="{'id':'" + mo.getId() + "','type':'" + mo.getSrcDocType() + "'}";
            moLog.setOptDataJson(JSON.toJSON(name));
            moLog.setOptId(mo.getNotifier());
            moLog.setOptName(mo.getNotifierName());
            // 未分派的任务，更新为现场处理任务
            if (StringUtils.isBlank(mo.getDutyManId())){
                mo.setCatalog(String.valueOf(RepairMoStateEnum.ONLINE.getValue()));
                // 隐患上报日志
                moLog.setResultMsg("隐患上报");
                moLog.setOptType("0");
                moLog.setOptContent(mo.getNotifierName() + "上报了隐患信息，处理人待分派。");
            } else {
                mo.setCatalog(String.valueOf(RepairMoStateEnum.APPOINT.getValue()));
                RepairMo repair = new RepairMo();
                BeanUtils.copyProperties(mo, repair);
                repair.setId(null);
                repair.setDutyManId(null);
                repair.setDutyMan(null);
                repair.setResolutionTime(null);
                repair.setShenpManId(null);
                repair.setShenpTime(null);
                repair.setShenpMan(null);
                repair.setShenpjzTime(null);
                repair.setAbnFiles(null);
                repair.setMoState(0);
                repair.setShenpResult(null);
                repair.setShenpContent(null);
                repair.setCatalog(String.valueOf(RepairMoStateEnum.ONLINE.getValue()));
                repair.setDelFlag(CommonConstant.DEL_FLAG_0);
                newMoList.add(repair);

                RepairMoLog fpLog = new RepairMoLog();
                fpLog.setMoCode(mo.getMoCode());
                String dataJson = "{'id':'" + mo.getId() + "','type':'" + mo.getSrcDocType() + "'}";
                fpLog.setOptDataJson(JSON.toJSON(dataJson));
                fpLog.setOptId(mo.getNotifier());
                fpLog.setOptName(mo.getNotifierName());
                fpLog.setResultMsg("分派任务");
                fpLog.setOptType("0");
                fpLog.setOptContent(mo.getNotifierName() + "将任务分派给" + mo.getDutyMan() + "负责处理。");
                fpLog.setS1(String.valueOf(CommonConstant.STATE_2));
                fpLog.setCreateBy(mo.getCreateBy());
                fpLog.setCreateName(mo.getCreateName());
                fpLog.setCreateTime(mo.getCreateTime());
                fpLog.setS2(mo.getAbnFiles());
                fpLog.setDelFlag(CommonConstant.DEL_FLAG_0);
                moLogList.add(fpLog);
            }

            moLog.setS1(String.valueOf(CommonConstant.STATE_1));
            moLog.setCreateBy(mo.getCreateBy());
            moLog.setCreateName(mo.getCreateName());
            moLog.setCreateTime(mo.getCreateTime());
            moLog.setS2(mo.getAbnFiles());
            moLog.setDelFlag(CommonConstant.DEL_FLAG_0);
            moLogList.add(moLog);
        }

        repairMoService.updateBatchById(moList);// 更新隐患任务分类
        repairMoService.saveBatch(newMoList); // 添加未分派的现场处理任务
        repairMoLogService.saveBatch(moLogList); // 添加隐患上报日志和隐患任务分派日志
    }
}
