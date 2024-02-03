package com.qfy.modules.repair.job;

import java.io.Serializable;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

/**
 * 同步核销数据
 * @author lanls
 * @version 1.0
 */
@Component
public class SyncEliminate implements Job, Serializable {

    private static final long serialVersionUID = 5693471992345498125L;

//    @Resource
//    private IDangerEliminateService eliminateService;
//    @Resource
//    private IAndonDriverService driverService;
//    @Resource
//    private IAndonCarService carService;
//    @Resource
//    private IAndonRoadService roadService;
//    @Resource
//    private IAndonCompanyService companyService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        // 查询出未同步的数据
//        QueryWrapper<DangerEliminate> wrapper = new QueryWrapper<>();
//        wrapper.lambda().eq(DangerEliminate::getDelFlag, CommonConstant.DEL_FLAG_0);
//        wrapper.lambda().eq(DangerEliminate::getIsRectify, CommonConstant.DEL_FLAG_0);
//        List<DangerEliminate> eliminateList = eliminateService.list(wrapper);
//
//        Map<String,List<DangerEliminate>> groups =
//                eliminateList.stream().collect(Collectors.groupingBy(DangerEliminate::getRectifyType));
//
//        List<DangerEliminate> driverEliminates = groups.get("1");// 驾驶人
//        List<DangerEliminate> carEliminates = groups.get("2");// 车辆
//        List<DangerEliminate> roadEliminates = groups.get("3");// 道路
//        List<DangerEliminate> companyEliminates = groups.get("4");// 企业
//
//        List<String> idNumbers =
//                driverEliminates.stream().map(DangerEliminate::getIdNumber).collect(Collectors.toList());
//        QueryWrapper query = new QueryWrapper();
//        query.in("deviceno", idNumbers);
//        List<AndonDriver> driverList = driverService.list(query);
        // TODO 未完待续
    }
}
