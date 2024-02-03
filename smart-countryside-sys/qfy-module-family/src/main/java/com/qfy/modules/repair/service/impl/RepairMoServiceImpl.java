package com.qfy.modules.repair.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jeecg.weibo.exception.BusinessException;
import com.qfy.modules.commons.SystemConstant;
import com.qfy.modules.commons.model.SystemUserSysDepartModel;
import com.qfy.modules.org.entity.SysOrg;
import com.qfy.modules.org.service.ISysOrgService;
import com.qfy.modules.repair.entity.HiddenDangerView;
import com.qfy.modules.repair.entity.RecordVo;
import com.qfy.modules.repair.entity.RepairMo;
import com.qfy.modules.repair.entity.RepairMoLog;
import com.qfy.modules.repair.enums.RepairMoStateEnum;
import com.qfy.modules.repair.mapper.RepairMoMapper;
import com.qfy.modules.repair.service.IHiddenDangerViewService;
import com.qfy.modules.repair.service.IRepairMoLogService;
import com.qfy.modules.repair.service.IRepairMoService;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.constant.CommonConstant;
import org.jeecg.common.exception.JeecgBootException;
import org.jeecg.common.system.api.ISysBaseAPI;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.common.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: 维修工单
 * @Author: jeecg-boot
 * @Date:   2021-11-02
 * @Version: V1.0
 */
@Service
@Transactional
public class RepairMoServiceImpl extends ServiceImpl<RepairMoMapper, RepairMo> implements IRepairMoService {

    public static final Logger logger = LoggerFactory.getLogger(RepairMoServiceImpl.class);

//    @Resource
//    private IAndonRecordService andonRecordService;

    @Resource
    private ISysBaseAPI sysBaseAPI;
    @Resource
    private ISysOrgService iSysOrgService;

    @Resource
    private RepairMoMapper repairMoMapper;

    @Resource
    private IRepairMoLogService repairMoLogService;

    @Resource
    private IHiddenDangerViewService dangerViewService;

    @Override
    public Result addRepairMoAndTask(RecordVo recordVo) throws BusinessException {
//        驾驶人	1   隐患宣导	5  定位打卡	6  随手拍	7  扣分记录	8  宣传阵地	9  车辆	2  道路	3  企业	4
        String [] ids= recordVo.getId().split(",");
        String [] callTypes=recordVo.getCallType().split(",");
        LoginUser user = new LoginUser();
        if(StringUtils.isNotEmpty( recordVo.getDutyManId())){
            user = sysBaseAPI.getUserByUserName(recordVo.getDutyManId());
        }

        if(user==null){
            user = new LoginUser();
        }
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
//        for(int i=0 ;i<callTypes.length; i++){
//            String id=ids[i];
//            RepairMo repairMo = new RepairMo();
//            QueryWrapper<RepairMo> wrapper = new QueryWrapper<>();
//            wrapper.clear();
//            Long count;
//            switch (callTypes[i]) {
//                case "1":
//                    AndonDriver andonDriver = andonDriverService.getById(id);
//                    wrapper.lambda().eq(RepairMo::getSrcDocCode, andonDriver.getCallCode());
//                    wrapper.lambda().eq(RepairMo::getCatalog,String.valueOf(RepairMoStateEnum.APPOINT.getValue()));
//                    wrapper.lambda().and(mo -> mo.ne(RepairMo::getDutyManId, "").or().isNotNull(RepairMo::getDutyManId));
//                    wrapper.last("limit 1");
//                    count = repairMoMapper.selectCount(wrapper);
//                    if(count > 0){
//                        return Result.OK("任务已分派，请勿重复分派！");
//                    }
//                    if(recordVo.getCallState()==2){
//                        andonDriver.setCallState(recordVo.getCallState());
//                    }else{
//                        andonDriver.setCallState(1);
//                    }
//                    if(StringUtils.isNotEmpty(recordVo.getDutyManId())){
//                        andonDriver.setDutyMan(user.getRealname());
//                        andonDriver.setDutyManId(user.getUsername());
//                    }
//                    if(StringUtils.isNotEmpty(recordVo.getWeixStdate())){
//                        andonDriver.setWeixStdate(recordVo.getWeixStdate());
//                    }
//                    if(StringUtils.isNotEmpty(recordVo.getWeixEddate())){
//                        andonDriver.setWeixEddate(recordVo.getWeixStdate());
//                    }else{
//                        andonDriver.setWeixEddate(DateUtils.formatDate());
//                    }
//                    andonDriver.setYhjzTime(andonDriver.getWeixEddate());
//                    /*if(StringUtils.isNotEmpty(recordVo.getAbnFiles())){
//                        andonDriver.setAbnFiles(recordVo.getAbnFiles());
//                    }*/
//                    if(StringUtils.isNotEmpty(recordVo.getRemarks())){
//                        andonDriver.setRemarks(recordVo.getRemarks());
//                    }
//
//                    if(StringUtils.isEmpty(andonDriver.getSignTime())){
//                        andonDriver.setSignTime(DateUtils.formatDateTime());
//                    }
//                    int andonDrivershu=DateUtils.formatDateTime().compareTo(andonDriver.getSignTime());
//                    if(andonDrivershu<=0){
//                        andonDriver.setIsXyjs(0);
//                    }else{
//                        andonDriver.setIsXyjs(1);
//                    }
//                    //处理隐患信息结束
//
//                    //绑定任务信息
//                    BeanUtils.copyProperties(andonDriver, repairMo);
//                    repairMo.setSrcDocCode(andonDriver.getCallCode());
//                    repairMo.setSrcDocType(AndonCallTypeEnum.driver.getValue());
//                    repairMo.setMoCode(AndonCallTypeEnum.driver.getPrefix()+"_"+andonDriver.getCallCode());
//                    //修改隐患信息
//                    andonDriverService.saveOrUpdate(andonDriver);
//                    break;
//                case "2":
//                    AndonCar andonCar = andonCarService.getById(id);
//                    wrapper.lambda().eq(RepairMo::getSrcDocCode, andonCar.getCallCode());
//                    wrapper.lambda().eq(RepairMo::getCatalog,String.valueOf(RepairMoStateEnum.APPOINT.getValue()));
//                    wrapper.lambda().and(mo -> mo.ne(RepairMo::getDutyManId, "").or().isNotNull(RepairMo::getDutyManId));
//                    wrapper.last("limit 1");
//                    count = repairMoMapper.selectCount(wrapper);
//                    if(count > 0){
//                        return Result.OK("任务已分派，请勿重复分派！");
//                    }
//                    if(recordVo.getCallState()==2){
//                        andonCar.setCallState(recordVo.getCallState());
//                    }else{
//                        andonCar.setCallState(1);
//                    }
//                    if(StringUtils.isNotEmpty(recordVo.getDutyManId())){
//                        andonCar.setDutyMan(user.getRealname());
//                        andonCar.setDutyManId(user.getUsername());
//                    }
//                    if(StringUtils.isNotEmpty(recordVo.getWeixStdate())){
//                        andonCar.setWeixStdate(recordVo.getWeixStdate());
//                    }
//                    if(StringUtils.isNotEmpty(recordVo.getWeixEddate())){
//                        andonCar.setWeixEddate(recordVo.getWeixStdate());
//                    }else{
//                        andonCar.setWeixEddate(DateUtils.formatDate());
//                    }
//                    andonCar.setYhjzTime(andonCar.getWeixEddate());
//                    /*if(StringUtils.isNotEmpty(recordVo.getAbnFiles())){
//                        andonCar.setAbnFiles(recordVo.getAbnFiles());
//                    }*/
//                    if(StringUtils.isNotEmpty(recordVo.getRemarks())){
//                        andonCar.setRemarks(recordVo.getRemarks());
//                    }
//                    if(StringUtils.isEmpty(andonCar.getSignTime())){
//                        andonCar.setSignTime(DateUtils.formatDateTime());
//                    }
//                    int andonCarres=DateUtils.formatDateTime().compareTo(andonCar.getSignTime());
//                    if(andonCarres<=0){
//                        andonCar.setIsXyjs(0);
//                    }else{
//                        andonCar.setIsXyjs(1);
//                    }
//                    //处理隐患信息结束
//
//                    //绑定任务信息
//                    BeanUtils.copyProperties(andonCar, repairMo);
//                    repairMo.setSrcDocCode(andonCar.getCallCode());
//                    repairMo.setSrcDocType(AndonCallTypeEnum.car.getValue());
//                    repairMo.setMoCode(AndonCallTypeEnum.car.getPrefix()+"_"+andonCar.getCallCode());
//                    //修改隐患信息
//                    andonCarService.saveOrUpdate(andonCar);
//                    break;
//                case "3":
//                    AndonRoad andonRoad = andonRoadService.getById(id);
//                    wrapper.lambda().eq(RepairMo::getSrcDocCode, andonRoad.getCallCode());
//                    wrapper.lambda().eq(RepairMo::getCatalog,String.valueOf(RepairMoStateEnum.APPOINT.getValue()));
//                    wrapper.lambda().and(mo -> mo.ne(RepairMo::getDutyManId, "").or().isNotNull(RepairMo::getDutyManId));
//                    wrapper.last("limit 1");
//                    count = repairMoMapper.selectCount(wrapper);
//                    if(count > 0){
//                        return Result.OK("任务已分派，请勿重复分派！");
//                    }
//                    if(recordVo.getCallState()==2){
//                        andonRoad.setCallState(recordVo.getCallState());
//                    }else{
//                        andonRoad.setCallState(1);
//                    }
//                    if(StringUtils.isNotEmpty(recordVo.getDutyManId())){
//                        andonRoad.setDutyMan(user.getRealname());
//                        andonRoad.setDutyManId(user.getUsername());
//                    }
//                    if(StringUtils.isNotEmpty(recordVo.getWeixStdate())){
//                        andonRoad.setWeixStdate(recordVo.getWeixStdate());
//                    }
//                    if(StringUtils.isNotEmpty(recordVo.getWeixEddate())){
//                        andonRoad.setWeixEddate(recordVo.getWeixStdate());
//                    }else{
//                        andonRoad.setWeixEddate(DateUtils.formatDate());
//                    }
//                    andonRoad.setYhjzTime(andonRoad.getWeixEddate());
//                    /*if(StringUtils.isNotEmpty(recordVo.getAbnFiles())){
//                        andonRoad.setAbnFiles(recordVo.getAbnFiles());
//                    }*/
//                    if(StringUtils.isNotEmpty(recordVo.getRemarks())){
//                        andonRoad.setRemarks(recordVo.getRemarks());
//                    }
//                    if(StringUtils.isEmpty(andonRoad.getS4())){
//                        andonRoad.setS4(DateUtils.formatDateTime());
//                    }
//                    int andonRoadres=DateUtils.formatDateTime().compareTo(andonRoad.getS4());
//                    if(andonRoadres<=0){
//                        andonRoad.setIsXyjs(0);
//                    }else{
//                        andonRoad.setIsXyjs(1);
//                    }
//                    //处理隐患信息结束
//
//                    //绑定任务信息
//                    BeanUtils.copyProperties(andonRoad, repairMo);
//                    repairMo.setSrcDocCode(andonRoad.getCallCode());
//                    repairMo.setSrcDocType(AndonCallTypeEnum.road.getValue());
//                    repairMo.setMoCode(AndonCallTypeEnum.road.getPrefix()+"_"+andonRoad.getCallCode());
//                    //修改隐患信息
//                    andonRoadService.saveOrUpdate(andonRoad);
//                    break;
//                case "4":
//                    AndonCompany andonCompany = andonCompanyService.getById(id);
//                    wrapper.lambda().eq(RepairMo::getSrcDocCode, andonCompany.getCallCode());
//                    wrapper.lambda().eq(RepairMo::getCatalog,String.valueOf(RepairMoStateEnum.APPOINT.getValue()));
//                    wrapper.lambda().and(mo -> mo.ne(RepairMo::getDutyManId, "").or().isNotNull(RepairMo::getDutyManId));
//                    wrapper.last("limit 1");
//                    count = repairMoMapper.selectCount(wrapper);
//                    if(count > 0){
//                        return Result.OK("任务已分派，请勿重复分派！");
//                    }
//                    if(recordVo.getCallState()==2){
//                        andonCompany.setCallState(recordVo.getCallState());
//                    }else{
//                        andonCompany.setCallState(1);
//                    }
//                    if(StringUtils.isNotEmpty(recordVo.getDutyManId())){
//                        andonCompany.setDutyMan(user.getRealname());
//                        andonCompany.setDutyManId(user.getUsername());
//                    }
//                    if(StringUtils.isNotEmpty(recordVo.getWeixStdate())){
//                        andonCompany.setWeixStdate(recordVo.getWeixStdate());
//                    }
//                    if(StringUtils.isNotEmpty(recordVo.getWeixEddate())){
//                        andonCompany.setWeixEddate(recordVo.getWeixStdate());
//                    }else{
//                        andonCompany.setWeixEddate(DateUtils.formatDate());
//                    }
//                    andonCompany.setYhjzTime(andonCompany.getWeixEddate());
//                    /*if(StringUtils.isNotEmpty(recordVo.getAbnFiles())){
//                        andonCompany.setAbnFiles(recordVo.getAbnFiles());
//                    }*/
//                    if(StringUtils.isNotEmpty(recordVo.getRemarks())){
//                        andonCompany.setRemarks(recordVo.getRemarks());
//                    }
//                    if(StringUtils.isEmpty(andonCompany.getSignTime())){
//                        andonCompany.setSignTime(DateUtils.formatDateTime());
//                    }
//                    int andonCompanyres=DateUtils.formatDateTime().compareTo(andonCompany.getSignTime());
//                    if(andonCompanyres<=0){
//                        andonCompany.setIsXyjs(0);
//                    }else{
//                        andonCompany.setIsXyjs(1);
//                    }
//                    //处理隐患信息结束
//
//                    //绑定任务信息
//                    BeanUtils.copyProperties(andonCompany, repairMo);
//                    repairMo.setSrcDocCode(andonCompany.getCallCode());
//                    repairMo.setSrcDocType(AndonCallTypeEnum.company.getValue());
//                    repairMo.setMoCode(AndonCallTypeEnum.company.getPrefix()+"_"+andonCompany.getCallCode());
//                    //修改隐患信息
//                    andonCompanyService.saveOrUpdate(andonCompany);
//                    break;
//                case "5":
//                    break;
//                case "6":
//                    break;
//                case "7":
//                    AndonRecord andonRecord = andonRecordService.getById(id);
//                    wrapper.lambda().eq(RepairMo::getSrcDocCode, andonRecord.getCallCode());
//                    wrapper.lambda().eq(RepairMo::getCatalog,String.valueOf(RepairMoStateEnum.APPOINT.getValue()));
//                    wrapper.lambda().and(mo -> mo.ne(RepairMo::getDutyManId, "").or().isNotNull(RepairMo::getDutyManId));
//                    wrapper.last("limit 1");
//                    count = repairMoMapper.selectCount(wrapper);
//                    if(count > 0){
//                        return Result.OK("任务已分派，请勿重复分派！");
//                    }
//                    if(recordVo.getCallState()==2){
//                        andonRecord.setCallState(recordVo.getCallState());
//                    }else{
//                        andonRecord.setCallState(1);
//                    }
//                    if(StringUtils.isNotEmpty(recordVo.getDutyManId())){
//                        andonRecord.setDutyMan(user.getRealname());
//                        andonRecord.setDutyManId(user.getUsername());
//                    }
//                    if(StringUtils.isNotEmpty(recordVo.getWeixStdate())){
//                        andonRecord.setWeixStdate(recordVo.getWeixStdate());
//                    }
//                    if(StringUtils.isNotEmpty(recordVo.getWeixEddate())){
//                        andonRecord.setWeixEddate(recordVo.getWeixStdate());
//                    }else{
//                        andonRecord.setWeixEddate(DateUtils.formatDate());
//                    }
//                    andonRecord.setYhjzTime(andonRecord.getWeixEddate());
//                    /*if(StringUtils.isNotEmpty(recordVo.getAbnFiles())){
//                        andonRecord.setAbnFiles(recordVo.getAbnFiles());
//                    }*/
//                    if(StringUtils.isNotEmpty(recordVo.getRemarks())){
//                        andonRecord.setRemarks(recordVo.getRemarks());
//                    }
//                    if(StringUtils.isEmpty(andonRecord.getS4())){
//                        andonRecord.setS4(DateUtils.formatDateTime());
//                    }
//                    int andonRecordres=DateUtils.formatDateTime().compareTo(andonRecord.getS4());
//                    if(andonRecordres<=0){
//                        andonRecord.setIsXyjs(0);
//                    }else{
//                        andonRecord.setIsXyjs(1);
//                    }
//                    //处理隐患信息结束
//
//                    //绑定任务信息
//                    BeanUtils.copyProperties(andonRecord, repairMo);
//                    repairMo.setSrcDocCode(andonRecord.getCallCode());
//                    repairMo.setSrcDocType(AndonCallTypeEnum.suishoupai.getValue());
//                    repairMo.setMoCode(AndonCallTypeEnum.suishoupai.getPrefix()+"_"+andonRecord.getCallCode());
//                    //修改隐患信息
//                    andonRecordService.saveOrUpdate(andonRecord);
//                    break;
//            }
//
//            wrapper.clear();
//            wrapper.lambda().eq(RepairMo::getMoCode, repairMo.getMoCode());
//            wrapper.lambda().eq(RepairMo::getCatalog,String.valueOf(RepairMoStateEnum.ONLINE.getValue()));
//            wrapper.lambda().orderByAsc(RepairMo::getCreateTime);
//            wrapper.last("limit 1");
//            RepairMo repair = repairMoMapper.selectOne(wrapper);
//            RepairMoLog moLog = null;
//            // 如不存在，则判定为尚未创建任务的旧数据，需重新补充一个任务及任务日志
//            if (null == repair){
//                repair = new RepairMo();
//                BeanUtils.copyProperties(repairMo, repair);
//                repair.setMoState(RepairMoStateEnum.NEW.getValue());
//                repair.setCatalog(String.valueOf(RepairMoStateEnum.ONLINE.getValue()));
//                Calendar cal = Calendar.getInstance();
//                repair.setWeixStdate(DateUtils.formatDate(cal, "yyyy-MM-dd HH:mm"));
//                cal.add(Calendar.YEAR,1);// 一年期限
//                repair.setResolutionTime(DateUtils.formatDate(cal, "yyyy-MM-dd HH:mm"));
//                repair.setWeixEddate(DateUtils.formatDate(cal, "yyyy-MM-dd HH:mm"));
//                repair.setId(null);
//                repair.setIsOuts(0);
//                saveOrUpdate(repair);
//
//                moLog = new RepairMoLog();
//                moLog.setMoCode(repairMo.getMoCode());
//                String name="{'id':'" + id + "','type':'" + repairMo.getSrcDocType() + "'}";
//                moLog.setOptDataJson(JSON.toJSON(name));
//                moLog.setOptId(repairMo.getNotifier());
//                moLog.setOptName(repairMo.getNotifierName());
//                moLog.setResultMsg("隐患上报");
//                moLog.setOptType("0");
//                moLog.setOptContent(repairMo.getNotifierName() + "上报了隐患信息，处理人待分派。");
//                moLog.setS1(String.valueOf(CommonConstant.STATE_1));
//                moLog.setCreateBy(repairMo.getCreateBy());
//                moLog.setCreateName(repairMo.getCreateName());
//                moLog.setCreateTime(repairMo.getCreateTime());
//                moLog.setS2(repairMo.getAbnFiles());
//                moLog.setDelFlag(CommonConstant.DEL_FLAG_0);
//                repairMoLogService.save(moLog);
//            }
//
//            // 现场处理
//            if (recordVo.getCallState() == 2){
//                repair.setCatalog(String.valueOf(RepairMoStateEnum.ONLINE.getValue()));
//                repair.setMoState(RepairMoStateEnum.REPAIR_FINISH.getValue());
//                repair.setIsOuts(0);
//                repair.setShenpTime(DateUtils.formatDateTime());
//                repair.setShenpMan(loginUser.getRealname());
//                repair.setShenpManId(loginUser.getUsername());
//                repair.setDutyMan(loginUser.getRealname());
//                repair.setDutyManId(loginUser.getUsername());
//                repair.setDutyDepart(loginUser.getOrgCode());
//                String shjz_date =  SystemConstant.sysMapValue.get("shjz_date");
//                String dateName= DateUtils.getAddTime(Integer.valueOf(shjz_date));
//                repair.setShenpjzTime(dateName);
//                saveOrUpdate(repair);
//                moLog = new RepairMoLog();
//                moLog.setMoCode(repair.getMoCode());
//                String name="{'id':'" + id + "','type':'" + repairMo.getSrcDocType() + "'}";
//                moLog.setOptDataJson(JSON.toJSON(name));
//                moLog.setOptId(user.getUsername());
//                moLog.setOptName(user.getRealname());
//                moLog.setResultMsg("整改完成");
//                moLog.setOptType("0");
//                moLog.setOptContent(loginUser.getRealname() + "完成了整改任务。");
//                moLog.setS1(String.valueOf(CommonConstant.STATE_8));
//                moLog.setS2(recordVo.getAbnFiles());
//                moLog.setDelFlag(CommonConstant.DEL_FLAG_0);
//                repairMoLogService.save(moLog);
//                chuliAndon(repair,2);
//            } else {
//
//                repairMo.setDutyMan(user.getRealname());
//                repairMo.setDutyManId(user.getUsername());
//                repairMo.setDutyDepart(user.getOrgCode());
//
//                if(StringUtils.isNotEmpty(recordVo.getWeixStdate())){
//                    repairMo.setWeixStdate(recordVo.getWeixStdate());
//                } else {
//                    repairMo.setWeixEddate(DateUtils.formatDate());
//                }
//
//                repairMo.setResolutionTime(recordVo.getWeixEddate());
//                repairMo.setWeixEddate(recordVo.getWeixEddate());
//                repairMo.setIsOuts(0);
//                repairMo.setMoState(RepairMoStateEnum.NEW.getValue());
//                repairMo.setCatalog(String.valueOf(RepairMoStateEnum.APPOINT.getValue()));
//                repairMo.setDelFlag(CommonConstant.DEL_FLAG_0);
//                saveOrUpdate(repairMo);
//                moLog = new RepairMoLog();
//                moLog.setMoCode(repairMo.getMoCode());
//                String name="{'id':'" + id + "','type':'"+callTypes[i] + "'}";
//                moLog.setOptDataJson(JSON.toJSON(name));
//                moLog.setOptId(loginUser.getUsername());
//                moLog.setOptName(loginUser.getRealname());
//                moLog.setResultMsg("分派任务");
//                moLog.setOptType("0");
//                moLog.setOptContent(loginUser.getRealname() + "将任务分派给" + user.getRealname() + "负责处理。");
//                moLog.setS1(String.valueOf(CommonConstant.STATE_2));
//                moLog.setDelFlag(CommonConstant.DEL_FLAG_0);
//                repairMoLogService.save(moLog);
//            }
//        }
        return Result.OK("任务分派成功！");
    }


    @Override
    public Result updateByMo(RepairMo repairMo) throws BusinessException {
//        驾驶人	1   隐患宣导	5  定位打卡	6  随手拍	7  扣分记录	8  宣传阵地	9  车辆	2  道路	3  企业	4

        HiddenDangerView dangerV = dangerViewService.selectById(repairMo.getId());
        if (dangerV == null) {
            return Result.OK("该隐患不存在！");
        }

        QueryWrapper<RepairMo> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(RepairMo::getSrcDocCode, dangerV.getCallCode());
        wrapper.lambda().eq(RepairMo::getCatalog, RepairMoStateEnum.APPOINT.getValue().toString());
//        wrapper.and(i -> i.lambda().ne(RepairMo::getDutyManId,"").or().isNotNull(RepairMo::getDutyManId));
        List<RepairMo> repairMoList = list(wrapper);
        AtomicBoolean exist = new AtomicBoolean(false);
        RepairMo repair_mo = new RepairMo();
        if (CollectionUtils.isNotEmpty(repairMoList)){
            repairMoList.stream().forEach(mo -> {
                if (StringUtils.isNotBlank(mo.getDutyManId())){
                    exist.set(true);
                } else {
                    BeanUtils.copyProperties(mo, repair_mo);
                }
            });
        } else {
            BeanUtils.copyProperties(repairMo, repair_mo);
            repair_mo.setId(null);
            repair_mo.setSrcDocCode(dangerV.getCallCode());
            repair_mo.setSrcDocType(dangerV.getCallType());
            repair_mo.setRecDate(dangerV.getRecDate());
//            switch (dangerV.getCallType()){
//                case "1":
//                    repair_mo.setMoCode(AndonCallTypeEnum.driver.getPrefix() + "_" + dangerV.getCallCode());
//                    break;
//                case "2":
//                    repair_mo.setMoCode(AndonCallTypeEnum.car.getPrefix() + "_" + dangerV.getCallCode());
//                    break;
//                case "3":
//                    repair_mo.setMoCode(AndonCallTypeEnum.road.getPrefix() + "_" + dangerV.getCallCode());
//                    break;
//                case "4":
//                    repair_mo.setMoCode(AndonCallTypeEnum.company.getPrefix() + "_" + dangerV.getCallCode());
//                    break;
//                case "7":
//                    repair_mo.setMoCode(AndonCallTypeEnum.suishoupai.getPrefix() + "_" + dangerV.getCallCode());
//                    break;
//                default:
//                    break;
//            }
        }

        if (exist.get()) {
            return Result.error("该隐患已被分派，请勿重复分派！");
        }

        LoginUser user = new LoginUser();
        if (StringUtils.isNotEmpty(repairMo.getDutyManId())) {
            user = sysBaseAPI.getUserByUserName(repairMo.getDutyManId());
        }

        if (user == null) {
            user = new LoginUser();
        }

        repair_mo.setCatalog(String.valueOf(RepairMoStateEnum.APPOINT.getValue()));
        repair_mo.setMoState(RepairMoStateEnum.NEW.getValue());
        repair_mo.setDutyManId(repairMo.getDutyManId());
        repair_mo.setDutyMan(user.getRealname());
        repair_mo.setResolutionTime(repairMo.getWeixEddate());
        repair_mo.setWeixEddate(repairMo.getWeixEddate());
        repair_mo.setWeixStdate(repairMo.getWeixStdate());
        repair_mo.setDelFlag(CommonConstant.DEL_FLAG_0);
        chuliAndon(repair_mo, 0);
        saveOrUpdate(repair_mo);
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        RepairMoLog moLog = new RepairMoLog();
        moLog.setMoCode(repair_mo.getMoCode());
        String name = "{'id':'" + repair_mo.getId() + "','code':'" + repair_mo.getMoCode() + "'}";
        moLog.setOptDataJson(JSON.toJSON(name));
        moLog.setOptId(loginUser.getUsername());
        moLog.setOptName(loginUser.getRealname());
        moLog.setOptType("1");
        moLog.setResultMsg("分派任务");
        moLog.setS1(String.valueOf(CommonConstant.STATE_2));
        moLog.setOptContent(loginUser.getRealname() + "将任务分派给" + user.getRealname() + "负责处理。");
        moLog.setDelFlag(CommonConstant.DEL_FLAG_0);
        repairMoLogService.save(moLog);
        return Result.OK("任务分派成功！");
    }

    @Override
    public void acceptOrRefuseMo(RepairMo repairMo) throws BusinessException {
        RepairMo repair_mo = getById(repairMo.getId());
         if (repair_mo == null || !repair_mo.getMoState().equals(RepairMoStateEnum.NEW.getValue())) {
            throw new BusinessException("任务状态错误，不能操作");
        }
        RepairMoLog log = new RepairMoLog();
        LoginUser user = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        if (StringUtils.isEmpty(repairMo.getRemarks())) {
            repairMo.setRemarks("无");
        }
        if (repairMo.getMoState() == 1) { //接受
            repair_mo.setMoState(RepairMoStateEnum.CHECK_PASS.getValue());
            repair_mo.setRemarks(repairMo.getRemarks());
            repair_mo.setWeixStdate(DateUtils.formatDate());
            updateById(repair_mo);
            log.setResultMsg("接收任务");
            log.setOptType("2");
            log.setOptContent(repair_mo.getDutyMan() + "接收了分派任务。");
            log.setS1(String.valueOf(CommonConstant.STATE_4));
            chuliAndon(repair_mo, 1);
        } else if (repairMo.getMoState() == 2) { //拒绝 设置人员为空
            log.setResultMsg("拒绝任务");
            log.setOptType("3");
            log.setOptContent(repair_mo.getDutyMan() + "拒收了分派任务。");
            log.setS1(String.valueOf(CommonConstant.STATE_3));
            // 如果任务处于已分派状态，则取消分派状态。如果不是已分派状态，则不做任何操作。
            if (repair_mo.getMoState().equals(RepairMoStateEnum.NEW.getValue())){
                repair_mo.setMoState(RepairMoStateEnum.NEW.getValue());
                repair_mo.setDutyDepart("");
                repair_mo.setDutyManId("");
                repair_mo.setDutyMan("");
                repair_mo.setRemarks(repairMo.getRemarks());
                updateById(repair_mo);
            }
        }

        log.setS3(repairMo.getRemarks());
        //添加派工日志
        log.setOptId(user.getUsername());
        log.setOptName(user.getRealname());
        log.setMoCode(repair_mo.getMoCode());
        String name = "{'id':'" + repairMo.getId() + "','moState':'" + repairMo.getMoState() + "'}";
        log.setOptDataJson(JSON.toJSON(name));
        log.setDelFlag(CommonConstant.DEL_FLAG_0);
        repairMoLogService.save(log);
    }


    @Override
    public void updateSub(RepairMo repairMo) throws BusinessException {
        RepairMo repair_mo =getById(repairMo.getId());
        if(repair_mo == null || !repair_mo.getMoState().equals(RepairMoStateEnum.CHECK_PASS.getValue())){
            throw new BusinessException("任务状态错误，不能接受");
        }
        if(StringUtils.isEmpty(repairMo.getRemarks())){
            repairMo.setRemarks("无");
        }
        RepairMoLog log = new RepairMoLog();
        LoginUser user = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        repair_mo.setMoState(RepairMoStateEnum.REPAIR_SUBMIT.getValue());
//        repair_mo.setAbnFiles(repairMo.getAbnFiles());
        repair_mo.setWeixEddate(DateUtils.formatDate());
        repair_mo.setRemarks(repairMo.getRemarks());
        String shjz_date =  SystemConstant.sysMapValue.get("shjz_date");
        String dateName=DateUtils.getAddTime(Integer.valueOf(shjz_date));
        repair_mo.setShenpjzTime(dateName);
        saveOrUpdate(repair_mo);
        log.setResultMsg("提交任务");
        log.setOptContent(repair_mo.getDutyMan()+"完成了整改任务，等待审核。");
        log.setOptType("4");
        log.setS1(String.valueOf(CommonConstant.STATE_5));
        log.setS2(repairMo.getAbnFiles());
        log.setRemarks(repairMo.getRemarks());
        //添加派工日志
        log.setOptId(user.getUsername());
        log.setOptName(user.getRealname());
        log.setMoCode(repair_mo.getMoCode());
        String name="{'id':'"+repairMo.getId() + "'}";
        log.setOptDataJson(JSON.toJSON(name));
        log.setDelFlag(CommonConstant.DEL_FLAG_0);
        repairMoLogService.save(log);
    }


    @Override
    public void updateClose(RepairMo repairMo) throws BusinessException {
        RepairMo repair_mo = getById(repairMo.getId());
        if (repair_mo == null || !repair_mo.getMoState().equals(RepairMoStateEnum.REPAIR_FINISH_WAITTING_CLOSE.getValue())) {
            throw new BusinessException("任务状态错误，不能接受");
        }
        if (StringUtils.isEmpty(repairMo.getRemarks())) {
            repairMo.setRemarks("无");
        }
        RepairMoLog moLog = new RepairMoLog();
        LoginUser user = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        repair_mo.setMoState(RepairMoStateEnum.REPAIR_CLIOSE.getValue());
        saveOrUpdate(repair_mo);
        moLog.setResultMsg("隐患核销");
        moLog.setOptContent(user.getRealname() + "已审核该隐患整改完成情况，确认隐患消除。");
        moLog.setOptType("5");
        moLog.setS1(String.valueOf(CommonConstant.STATE_10));
//        添加派工日志
        moLog.setOptId(user.getUsername());
        moLog.setOptName(user.getRealname());
        moLog.setMoCode(repair_mo.getMoCode());
        String name = "{'id':'" + repairMo.getId() + "'}";
        moLog.setOptDataJson(JSON.toJSON(name));
        moLog.setDelFlag(CommonConstant.DEL_FLAG_0);
        repairMoLogService.save(moLog);
        chuliAndon(repair_mo, 3);
    }

    @Override
    @Transactional(rollbackFor = JeecgBootException.class)
    public void doRepairMo(RecordVo recordVo) throws BusinessException {

//        AndonRecord andonRecord = andonRecordService.getById(recordVo.getId());
//        //盘点是否生成任务单
//        LambdaQueryWrapper<RepairMo> queryWrapper = new LambdaQueryWrapper();
//        queryWrapper.eq(RepairMo::getSrcDocCode,andonRecord.getCallCode());
//        RepairMo repairMo = getOne(queryWrapper);
//        if(repairMo == null || !repairMo.getMoState().equals(RepairMoStateEnum.REPAIR_DOING.getValue())){
//            throw new BusinessException("派工任务不存在或已完成");
//        }
//
//        repairMo.setWeixContent(recordVo.getFinishMethod());
//        repairMo.setWeixEddate(recordVo.getWeixEddate());
//        repairMo.setMoState(RepairMoStateEnum.REPAIR_FINISH.getValue());
//        repairMo.setWeixFiles(recordVo.getWeixFiles());
//        repairMo.setIsFinish(0);
//        saveOrUpdate(repairMo);
//
//        andonRecord.setFinishMethod(recordVo.getFinishMethod());
//        andonRecord.setWeixEddate(recordVo.getWeixEddate());
//        andonRecord.setCallState(RepairMoStateEnum.REPAIR_FINISH.getValue());
//        andonRecordService.saveOrUpdate(andonRecord);
//
//        //添加派工日志
//        RepairMoLog log = new RepairMoLog();
//        log.setOptType("mo");
//        log.setMoCode(repairMo.getMoCode());
//        log.setOptDataJson(JSON.toJSONString(recordVo));
//        log.setResultMsg("任务执行");
//        log.setOptContent("任务执行:"+repairMo.getDutyMan());
//        log.setOptId(repairMo.getDutyManId());
//        log.setOptName(repairMo.getDutyMan());
//        log.setDelFlag(CommonConstant.DEL_FLAG_0);
//        repairMoLogService.save(log);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void finishRepairMo(RepairMo repairMo) throws BusinessException {

        RepairMo repair_mo =getById(repairMo.getId());
        RepairMoLog log = new RepairMoLog();
        if(repair_mo == null || !repair_mo.getMoState().equals(RepairMoStateEnum.REPAIR_SUBMIT.getValue())){
            throw new BusinessException("任务状态错误，不能审核");
        }
        LoginUser user = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        repair_mo.setShenpContent(repairMo.getShenpContent());
        if(StringUtils.isEmpty(repairMo.getShenpContent())){
            repairMo.setShenpContent("无");
        }
        repair_mo.setShenpTime(DateUtils.formatDateTime());
        if(repairMo.getMoState()==1){
            repair_mo.setMoState(RepairMoStateEnum.REPAIR_FINISH.getValue());
            repair_mo.setShenpResult("审核通过");
            log.setResultMsg("任务审核");
            log.setOptContent(user.getRealname()+"审核通过了" + repair_mo.getDutyMan() + "提交的任务。");
            log.setS1(String.valueOf(CommonConstant.STATE_7));
            log.setS3(repairMo.getShenpContent());
            chuliAndon(repair_mo,2);
        }else{
            repair_mo.setMoState(RepairMoStateEnum.CHECK_PASS.getValue());
//            repair_mo.setShenpjzTime("");
            repair_mo.setShenpResult("审核驳回");
            log.setResultMsg("任务审核");
            log.setOptContent(user.getRealname()+"驳回了" + repair_mo.getDutyMan() + "提交的任务。");
            log.setS1(String.valueOf(CommonConstant.STATE_6));
            log.setS3(repairMo.getShenpContent());
        }
        repair_mo.setShenpManId(user.getUsername());
        repair_mo.setShenpMan(user.getRealname());
        repair_mo.setShenpTime(DateUtils.formatDateTime());
        saveOrUpdate(repair_mo);
        //添加派工日志
        log.setMoCode(repair_mo.getMoCode());
        String name="{'id':'"+repairMo.getId() + "'}";
        log.setOptDataJson(JSON.toJSON(name));
        log.setOptId(user.getUsername());
        log.setOptName(user.getRealname());
        log.setRemarks(repairMo.getRemarks());
        log.setDelFlag(CommonConstant.DEL_FLAG_0);
        repairMoLogService.save(log);

        // 完成劝导任务
        if (repairMo.getMoState()==1){
            log.setResultMsg("整改完成");
            log.setOptContent("经过审核，" + repair_mo.getDutyMan() + "完成了整改任务。");
            log.setS1(String.valueOf(CommonConstant.STATE_8));
            log.setId(null);
            repairMoLogService.save(log);
        }
    }

    //操作任务处理对应隐患信息
    public void chuliAndon(RepairMo repairMo,Integer callState){
        String callTypes=repairMo.getSrcDocType();
//        switch (callTypes) {
//            case "1":
//                AndonDriver andonDriver =andonDriverService.queryParam(repairMo.getSrcDocCode());
//                andonDriver.setCallState(callState);
//                if(repairMo.getMoState().equals(RepairMoStateEnum.REPAIR_FINISH.getValue())){
//                    int weixEddate=repairMo.getWeixEddate().compareTo(repairMo.getResolutionTime());
//                    if(weixEddate<=0){
//                        andonDriver.setIsCljs(0);
//                    }else{
//                        andonDriver.setIsCljs(1);
//                    }
//
//                    int shenpjz=DateUtils.formatDateTime().compareTo(repairMo.getShenpjzTime());
//                    if(shenpjz<=0){
//                        andonDriver.setIsShjs(0);
//                    }else{
//                        andonDriver.setIsShjs(1);
//                    }
//                    andonDriver.setWeixEddate(repairMo.getWeixEddate());
//                }
//                if(repairMo.getMoState().equals(RepairMoStateEnum.CHECK_PASS.getValue())){
//                    andonDriver.setDutyManId(repairMo.getDutyManId());
//                    andonDriver.setDutyMan(repairMo.getDutyMan());
//                    andonDriver.setWeixStdate(repairMo.getWeixStdate());
//                }
//
//                if(repairMo.getMoState().equals(RepairMoStateEnum.NEW.getValue())){
//                    andonDriver.setYhjzTime(repairMo.getResolutionTime());
////                    andonDriver.setWeixEddate(repairMo.getWeixEddate());
//                }
//                andonDriverService.saveOrUpdate(andonDriver);
//                break;
//            case "2":
//                AndonCar andonCar = andonCarService.queryParam(repairMo.getSrcDocCode());
//                andonCar.setCallState(callState);
//
//                if(repairMo.getMoState().equals(RepairMoStateEnum.REPAIR_FINISH.getValue())){
//
//                    int weixEddate=repairMo.getWeixEddate().compareTo(repairMo.getResolutionTime());
//                    if(weixEddate<=0){
//                        andonCar.setIsCljs(0);
//                    }else{
//                        andonCar.setIsCljs(1);
//                    }
//
//                    int shenpjz=DateUtils.formatDateTime().compareTo(repairMo.getShenpjzTime());
//                    if(shenpjz<=0){
//                        andonCar.setIsShjs(0);
//                    }else{
//                        andonCar.setIsShjs(1);
//                    }
//                    andonCar.setWeixEddate(repairMo.getWeixEddate());
//                }
//
//                if(repairMo.getMoState().equals(RepairMoStateEnum.CHECK_PASS.getValue())){
//                    andonCar.setDutyManId(repairMo.getDutyManId());
//                    andonCar.setDutyMan(repairMo.getDutyMan());
//                    andonCar.setWeixStdate(repairMo.getWeixStdate());
//                }
//                if(repairMo.getMoState().equals(RepairMoStateEnum.NEW.getValue())){
//                    andonCar.setYhjzTime(repairMo.getResolutionTime());
////                    andonCar.setWeixEddate(repairMo.getWeixEddate());
//                }
//                //修改隐患信息
//                andonCarService.saveOrUpdate(andonCar);
//                break;
//            case "3":
//                AndonRoad andonRoad =andonRoadService.queryParam(repairMo.getSrcDocCode());
//                andonRoad.setCallState(callState);
//
//                if(repairMo.getMoState().equals(RepairMoStateEnum.REPAIR_FINISH.getValue())){
//
//                    int weixEddate=repairMo.getWeixEddate().compareTo(repairMo.getResolutionTime());
//                    if(weixEddate<=0){
//                        andonRoad.setIsCljs(0);
//                    }else{
//                        andonRoad.setIsCljs(1);
//                    }
//
//                    int shenpjz=DateUtils.formatDateTime().compareTo(repairMo.getShenpjzTime());
//                    if(shenpjz<=0){
//                        andonRoad.setIsShjs(0);
//                    }else{
//                        andonRoad.setIsShjs(1);
//                    }
//                    andonRoad.setWeixEddate(repairMo.getWeixEddate());
//                }
//
//                if(repairMo.getMoState().equals(RepairMoStateEnum.CHECK_PASS.getValue())){
//                    andonRoad.setDutyManId(repairMo.getDutyManId());
//                    andonRoad.setDutyMan(repairMo.getDutyMan());
//                    andonRoad.setWeixStdate(repairMo.getWeixStdate());
//                }
//                if(repairMo.getMoState().equals(RepairMoStateEnum.NEW.getValue())){
//                    andonRoad.setYhjzTime(repairMo.getResolutionTime());
////                    andonRoad.setWeixEddate(repairMo.getWeixEddate());
//                }
//                //修改隐患信息
//                andonRoadService.saveOrUpdate(andonRoad);
//                break;
//            case "4":
//                AndonCompany andonCompany =andonCompanyService.queryParam(repairMo.getSrcDocCode());
//                andonCompany.setCallState(callState);
//
//                if(repairMo.getMoState().equals(RepairMoStateEnum.REPAIR_FINISH.getValue())){
//
//                    int weixEddate=repairMo.getWeixEddate().compareTo(repairMo.getResolutionTime());
//                    if(weixEddate<=0){
//                        andonCompany.setIsCljs(0);
//                    }else{
//                        andonCompany.setIsCljs(1);
//                    }
//
//                    int shenpjz=DateUtils.formatDateTime().compareTo(repairMo.getShenpjzTime());
//                    if(shenpjz<=0){
//                        andonCompany.setIsShjs(0);
//                    }else{
//                        andonCompany.setIsShjs(1);
//                    }
//                    andonCompany.setWeixEddate(repairMo.getWeixEddate());
//                }
//
//                if(repairMo.getMoState().equals(RepairMoStateEnum.CHECK_PASS.getValue())){
//                    andonCompany.setDutyManId(repairMo.getDutyManId());
//                    andonCompany.setDutyMan(repairMo.getDutyMan());
//                    andonCompany.setWeixStdate(repairMo.getWeixStdate());
//                }
//
//                if(repairMo.getMoState().equals(RepairMoStateEnum.NEW.getValue())){
//                    andonCompany.setYhjzTime(repairMo.getResolutionTime());
////                    andonCompany.setWeixEddate(repairMo.getWeixEddate());
//                }
//                //修改隐患信息
//                andonCompanyService.saveOrUpdate(andonCompany);
//                break;
//            case "5":
//                break;
//            case "6":
//                break;
//            case "7":
//                AndonRecord andonRecord =andonRecordService.queryParam(Integer.valueOf(repairMo.getSrcDocType()),repairMo.getSrcDocCode());
//                andonRecord.setCallState(callState);
//
//                if(repairMo.getMoState().equals(RepairMoStateEnum.REPAIR_FINISH.getValue())){
//
//                    int weixEddate=repairMo.getWeixEddate().compareTo(repairMo.getResolutionTime());
//                    if(weixEddate<=0){
//                        andonRecord.setIsCljs(0);
//                    }else{
//                        andonRecord.setIsCljs(1);
//                    }
//
//                    int shenpjz=DateUtils.formatDateTime().compareTo(repairMo.getShenpjzTime());
//                    if(shenpjz<=0){
//                        andonRecord.setIsShjs(0);
//                    }else{
//                        andonRecord.setIsShjs(1);
//                    }
//                    andonRecord.setWeixEddate(repairMo.getWeixEddate());
//                }
//
//                if(repairMo.getMoState().equals(RepairMoStateEnum.CHECK_PASS.getValue())){
//                    andonRecord.setDutyManId(repairMo.getDutyManId());
//                    andonRecord.setDutyMan(repairMo.getDutyMan());
//                    andonRecord.setWeixStdate(repairMo.getWeixStdate());
//                }
//
//                if(repairMo.getMoState().equals(RepairMoStateEnum.NEW.getValue())){
//                    andonRecord.setYhjzTime(repairMo.getResolutionTime());
////                    andonRecord.setWeixEddate(repairMo.getWeixEddate());
//                }
//                //修改隐患信息
//                andonRecordService.saveOrUpdate(andonRecord);
//                break;
//        }
    }

    @Override
    public Page<RepairMo> queryRepairMoParam(Page<RepairMo> page, RepairMo repairMo) {
        List<String> wcCode=new ArrayList<String>();
        if(StringUtils.isNotEmpty(repairMo.getWcCode())){
            String [] wc_code=repairMo.getWcCode().split(",");
            for( String name:wc_code){
                wcCode.add(name);
            }
        }
        repairMo.setWcCodeList(wcCode);
        return page.setRecords(repairMoMapper.queryRepairMoList(page,repairMo));
    }
    @Override
    public List<SystemUserSysDepartModel> queryPersonneList(String orgCode, String jurisdictions) {

        QueryWrapper<SysOrg> wrapper = new QueryWrapper<>();
        List<SysOrg> sysOrgList = new ArrayList<>();
        String jurisdiction = "";
        // 如果该隐患所属行政区划为村级，则查询该村级人员及乡镇级人员
        if (jurisdictions.length() == 12){
            String town = jurisdictions.substring(0,9);
            jurisdiction = town + "," + jurisdictions;
            wrapper.likeRight("code",town);
            sysOrgList = iSysOrgService.list(wrapper);
        } else if (jurisdictions.length() == 9){// 如果该隐患所属行政区划为乡镇级，则查询乡镇级人员及全部村级人员
            wrapper.likeRight("code",jurisdictions);
            sysOrgList = iSysOrgService.list(wrapper);
            List<String> orgCodeList = sysOrgList.stream().map(SysOrg::getCode).collect(Collectors.toList());
            jurisdiction = StringUtils.join(orgCodeList,",");
        } else if (jurisdictions.length() == 6){
            // 如果该隐患所属行政区划为县区级，则查询县区级人员及全部乡镇级人员
            wrapper.likeRight("code",jurisdictions);
            wrapper.apply("LENGTH(code) = 9");
            sysOrgList = iSysOrgService.list(wrapper);
            List<String> orgCodeList = sysOrgList.stream().map(SysOrg::getCode).collect(Collectors.toList());
            jurisdiction = StringUtils.join(orgCodeList,",");
        }

        List<SystemUserSysDepartModel> userlist = new ArrayList<>();
        List<Map<String, Object>> mapList = repairMoMapper.queryUserList(jurisdiction);
        for (Map<String, Object> map : mapList) {
            SysOrg sysOrg =
                    sysOrgList.stream().filter(s -> s.getCode().equals(map.get("jurisdictions").toString())).findFirst().orElse(null);
            SystemUserSysDepartModel userModel = new SystemUserSysDepartModel();
            userModel.setId(map.get("id").toString());
            String realname = map.get("realname") == null ? "" : map.get("realname").toString();
            userModel.setUserName(map.get("username") == null ? "" : map.get("username").toString());
            userModel.setRealname(sysOrg.getName() + " " + realname);
            userModel.setWorkNo(map.get("work_no") == null ? "" : map.get("work_no").toString());
            userModel.setPost(map.get("post") == null ? "" : map.get("post").toString());
            userModel.setTelephone(map.get("telephone") == null ? "" : map.get("telephone").toString());
            userModel.setEmail(map.get("email") == null ? "" : map.get("email").toString());
            userModel.setPhone(map.get("phone") == null ? "" : map.get("phone").toString());
            userModel.setUserIdentity(Integer.valueOf(map.get("user_identity").toString()));
//            userModel.setJurisdictions(map.get("jurisdictions") == null ? "" : map.get("jurisdictions").toString());
            userlist.add(0, userModel);
        }

//        userlist = userlist.stream().sorted(Comparator.comparing(SystemUserSysDepartModel::getJurisdictions)).collect(Collectors.toList());
        return userlist;
    }

    @Override
    public Map<String,Object> queryCountList(RepairMo repairMo){

        List<Map<String,Object>> litmap=new ArrayList<Map<String,Object>>();
        List<String> wcCode=new ArrayList<String>();
        if(StringUtils.isNotEmpty(repairMo.getWcCode())){
            String [] wc_code=repairMo.getWcCode().split(",");
            for( String name:wc_code){
                wcCode.add(name);
            }
        }
        LoginUser user = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        RepairMo repaircx=new RepairMo();
        repaircx.setWcCode(repairMo.getWcCode());
        repaircx.setWcCodeList(wcCode);
        repaircx.setWeixTimes(0);
//        Integer fpdfp=repairMoMapper.queryCountList(repaircx);  //任务分派-待分派
        Long fpdfp = dangerViewService.selectCount(repairMo);
//        repaircx=new RepairMo();
//        repaircx.setWcCode(repairMo.getWcCode());
//        repaircx.setWcCodeList(wcCode);
//        repaircx.setMoState(40);
//        repaircx.setStopTimes(0);
//        repaircx.setCatalog("2");
//        Integer fpdgb=repairMoMapper.queryCountList(repaircx);  //任务分派-待关闭
//        repaircx=new RepairMo();
//        repaircx.setWcCode(repairMo.getWcCode());
//        repaircx.setWcCodeList(wcCode);
//        repaircx.setMoState(40);
//        repaircx.setStopTimes(1);
//        Integer fpywc=repairMoMapper.queryCountList(repaircx);  //任务分派-已完成
        repaircx=new RepairMo();
        repaircx.setDutyManId(user.getUsername());
        repaircx.setMoState(0);
        repaircx.setStopTimes(0);
        repaircx.setCatalog("2");
        Integer zxdjs=repairMoMapper.queryCountList(repaircx);  //任务执行-待接收
        repaircx=new RepairMo();
        repaircx.setDutyManId(user.getUsername());
        repaircx.setMoState(10);
        repaircx.setStopTimes(0);
        repaircx.setCatalog("2");
        Integer zxdcl=repairMoMapper.queryCountList(repaircx);  //任务执行-待处理
        repaircx=new RepairMo();
        repaircx.setDutyManId(user.getUsername());
        repaircx.setMoState(40);
        repaircx.setStopTimes(1);
        Integer zxywc=repairMoMapper.queryCountList(repaircx);  //任务执行-已处理/已完成
        repaircx=new RepairMo();
        repaircx.setWcCode(repairMo.getWcCode());
        repaircx.setWcCodeList(wcCode);
        repaircx.setMoState(30);
        repaircx.setStopTimes(0);
        repaircx.setCatalog("2");
        Integer shdsh=repairMoMapper.queryCountList(repaircx);  //任务审核-待审核
        repaircx=new RepairMo();
        repaircx.setShenpManId(user.getUsername());
        repaircx.setMoState(40);
        repaircx.setStopTimes(1);
        repaircx.setCatalog("2");
        Integer shwsh=repairMoMapper.queryCountList(repaircx);  //任务审核-已审核

        repaircx = new RepairMo();
        repaircx.setWcCode(repairMo.getWcCode());
        repaircx.setWcCodeList(wcCode);
        repaircx.setMoState(41);
        repaircx.setStopTimes(0);
        Integer hxdhx = repairMoMapper.queryCountList(repaircx);  //任务核销-待核销

        repaircx = new RepairMo();
        repaircx.setWcCode(repairMo.getWcCode());
        repaircx.setWcCodeList(wcCode);
        repaircx.setMoState(50);
        repaircx.setStopTimes(0);
        Integer hxyhx = repairMoMapper.queryCountList(repaircx);  //任务核销-已核销

        Map<String,Object> map=new HashMap<String,Object>();
        map.put("fpywc",0);// 任务分派-已完成
        map.put("fpdfp",fpdfp);// 任务分派-待分派
        map.put("fpdgb",0);// 任务分派-待关闭
        map.put("zxdjs",zxdjs);// 任务执行-待接收
        map.put("zxdcl",zxdcl);// 任务执行-待处理
        map.put("zxywc",zxywc);// 任务执行-已处理/已完成
        map.put("shdsh",shdsh);// 任务审核-待审核
        map.put("shwsh",shwsh);// 任务审核-已审核
        map.put("hxdhx",hxdhx);// 任务核销-待核销
        map.put("hxyhx",hxyhx);// 任务核销-已核销

        repaircx=new RepairMo();
        repaircx.setWcCode(repairMo.getWcCode());
        repaircx.setWcCodeList(wcCode);
        List<Map<String,Object>> maplist=repairMoMapper.queryQyName(repaircx);

        if(CollectionUtils.isEmpty(maplist)){
            map.put("wcName","");
        }else{
            String wcName="";
            for (Map<String,Object> maps:maplist){
                wcName=wcName+maps.get("name").toString()+",";
            }
            map.put("wcName",wcName.substring(0,wcName.length()-1));
        }
        return map;
    }



    @Override
    public Map<String,Object> queryCarList(RepairMo repairMo){
        Map<String,Object> map=new HashMap<String,Object>();
        List<String> wcCode=new ArrayList<String>();
        if(StringUtils.isNotEmpty(repairMo.getWcCode())){
            String [] wc_code=repairMo.getWcCode().split(",");
            for( String name:wc_code){
                wcCode.add(name);
            }
        }
        repairMo.setWcCodeList(wcCode);
//        List <Map<String,Object>> litmap=repairMoMapper.queryCarList(repairMo);  //查询待分配
        List<Map<String,Object>> returnList=new ArrayList<Map<String,Object>>();
//        Map<String,Object> map1=new HashMap<String,Object>();
//        map1.put("type",AndonCallTypeEnum.driver.getValue());
//        map1.put("name","驾驶人");
//        returnList.add(map1);
//        map1=new HashMap<String,Object>();
//        map1.put("type",AndonCallTypeEnum.car.getValue());
//        map1.put("name","机动车");
//        returnList.add(map1);
//        map1=new HashMap<String,Object>();
//        map1.put("type",AndonCallTypeEnum.road.getValue());
//        map1.put("name","道路");
//        returnList.add(map1);
//        map1=new HashMap<String,Object>();
//        map1.put("type",AndonCallTypeEnum.company.getValue());
//        map1.put("name","企业");
//        returnList.add(map1);
//        map1=new HashMap<String,Object>();
//        map1.put("type",AndonCallTypeEnum.suishoupai.getValue());
//        map1.put("name","随手拍");
//        returnList.add(map1);
//        for (Map<String,Object> mapadd:returnList){
//            Integer shu=0;
//            for (Map<String,Object> maps:litmap){
//                //驾驶人	1   隐患宣导	5  定位打卡	6  随手拍	7  扣分记录	8  宣传阵地	9  车辆	2  道路	3  企业	4
//                if(mapadd.get("type").toString().equals(maps.get("call_type").toString())){
//                    shu=Integer.valueOf(maps.get("count").toString());
//                    break;
//                }
//            }
//            mapadd.put("value",shu);
//        }
        map.put("returnList",returnList);
        return map;
    }



    @Override
    public Map<String,Object> queryCarCountList(RepairMo repairMo){

        List<Map<String,Object>> litmap=new ArrayList<Map<String,Object>>();
        List<String> wcCode=new ArrayList<String>();
        if(StringUtils.isNotEmpty(repairMo.getWcCode())){
            String [] wc_code=repairMo.getWcCode().split(",");
            for( String name:wc_code){
                wcCode.add(name);
            }
        }
        RepairMo repaircx=new RepairMo();
        repaircx.setWcCode(repairMo.getWcCode());
        repaircx.setWcCodeList(wcCode);
        Integer yhzs=repairMoMapper.queryCarCountList(repaircx);  //查询隐患总数
        repaircx=new RepairMo();
        repaircx.setWcCode(repairMo.getWcCode());
        repaircx.setWcCodeList(wcCode);
        repaircx.setMoState(1);
        repaircx.setStopTimes(0);
        Integer yhwzl=repairMoMapper.queryCarCountList(repaircx);  //查询未治理
        repaircx=new RepairMo();
        repaircx.setWcCode(repairMo.getWcCode());
        repaircx.setWcCodeList(wcCode);
        repaircx.setMoState(2);
        repaircx.setStopTimes(1);
        Integer yhyzl=repairMoMapper.queryCarCountList(repaircx);  //查询已治理
        repaircx=new RepairMo();
        repaircx.setWcCode(repairMo.getWcCode());
        repaircx.setWcCodeList(wcCode);
        repaircx.setWeixTimes(0);
        Integer yhyq=repairMoMapper.queryCarCountList(repaircx);  //查询已逾期的

        repaircx=new RepairMo();
        repaircx.setWcCode(repairMo.getWcCode());
        repaircx.setWcCodeList(wcCode);
        repaircx.setWeixTimes(1);
        Integer yhxyjs=repairMoMapper.queryCarCountList(repaircx);  //查询响应及时数

        repaircx=new RepairMo();
        repaircx.setWcCode(repairMo.getWcCode());
        repaircx.setWcCodeList(wcCode);
        repaircx.setWeixTimes(2);
        Integer yhcljs=repairMoMapper.queryCarCountList(repaircx);  //查询处理及时数

        repaircx=new RepairMo();
        repaircx.setWcCode(repairMo.getWcCode());
        repaircx.setWcCodeList(wcCode);
        repaircx.setWeixTimes(3);
        Integer yhshjs=repairMoMapper.queryCarCountList(repaircx);  //查询审核及时数

        Map<String,Object> map=new HashMap<String,Object>();
        map.put("yhzs",yhzs);
        map.put("yhwzl",yhwzl);
        map.put("yhyzl",yhyzl);
        map.put("yhyq",yhyq);

        map.put("yhxyjs",yhxyjs);
        map.put("yhcljs",yhcljs);
        map.put("yhshjs",yhshjs);
        return map;
    }


    @Override
    public Map<String,Object> queryMoCount(RepairMo repairMo){

        List<Map<String,Object>> litmap=new ArrayList<Map<String,Object>>();
        List<String> wcCode=new ArrayList<String>();
        if(StringUtils.isNotEmpty(repairMo.getWcCode())){
            String [] wc_code=repairMo.getWcCode().split(",");
            for( String name:wc_code){
                wcCode.add(name);
            }
        }
        RepairMo repaircx=new RepairMo();
        repaircx.setWcCode(repairMo.getWcCode());
        repaircx.setWcCodeList(wcCode);
        if(StringUtils.isNotEmpty(repairMo.getWeixStdate())){
            repaircx.setWeixStdate(repairMo.getWeixStdate());
        }
        if(StringUtils.isNotEmpty(repairMo.getWeixEddate())){
            repaircx.setWeixEddate(repairMo.getWeixEddate());
        }
        Integer yhzs=repairMoMapper.queryAllCount(repaircx);  //查询隐患总数
        repaircx=new RepairMo();
        repaircx.setWcCode(repairMo.getWcCode());
        repaircx.setWcCodeList(wcCode);
        repaircx.setMoState(0);// 已完成整改
        if(StringUtils.isNotEmpty(repairMo.getWeixStdate())){
            repaircx.setWeixStdate(repairMo.getWeixStdate());
        }
        if(StringUtils.isNotEmpty(repairMo.getWeixEddate())){
            repaircx.setWeixEddate(repairMo.getWeixEddate());
        }
        Integer yhyzl=repairMoMapper.queryAllCount(repaircx);  //查询已治理
        repaircx=new RepairMo();
        repaircx.setWcCode(repairMo.getWcCode());
        repaircx.setWcCodeList(wcCode);
        repaircx.setMoState(1); // 待分派、已分配任务
        if(StringUtils.isNotEmpty(repairMo.getWeixStdate())){
            repaircx.setWeixStdate(repairMo.getWeixStdate());
        }
        if(StringUtils.isNotEmpty(repairMo.getWeixEddate())){
            repaircx.setWeixEddate(repairMo.getWeixEddate());
        }
        Integer yhzlz=repairMoMapper.queryAllCount(repaircx);  //查询治理中
        repaircx=new RepairMo();
        repaircx.setWcCode(repairMo.getWcCode());
        repaircx.setWcCodeList(wcCode);
        repaircx.setMoState(2);
        if(StringUtils.isNotEmpty(repairMo.getWeixStdate())){
            repaircx.setWeixStdate(repairMo.getWeixStdate());
        }
        if(StringUtils.isNotEmpty(repairMo.getWeixEddate())){
            repaircx.setWeixEddate(repairMo.getWeixEddate());
        }
        Integer yhdsh=repairMoMapper.queryAllCount(repaircx);  //查询待审核
        repaircx=new RepairMo();
        repaircx.setWcCode(repairMo.getWcCode());
        repaircx.setWcCodeList(wcCode);
        repaircx.setWeixTimes(0);
        if(StringUtils.isNotEmpty(repairMo.getWeixStdate())){
            repaircx.setWeixStdate(repairMo.getWeixStdate());
        }
        if(StringUtils.isNotEmpty(repairMo.getWeixEddate())){
            repaircx.setWeixEddate(repairMo.getWeixEddate());
        }
        Integer yhyq=repairMoMapper.queryMoCount(repaircx);  //查询已逾期的
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("yhzs",yhzs);
        map.put("yhyzl",yhyzl);
        map.put("yhzlz",yhzlz);
        map.put("yhdsh",yhdsh);
        map.put("yhyq",yhyq);
        NumberFormat numberFormat = NumberFormat.getInstance();
        // 设置精确到小数点后2位
        numberFormat.setMaximumFractionDigits(2);
        if(yhyzl==0 || yhzs==0){
            map.put("zgl",0);
        }else{
            String zgl = numberFormat.format((float) yhyzl /(float) yhzs);
            map.put("zgl",zgl);
        }
        return map;
    }

    @Override
    public Map<String,Object> queryQxCOUNT(RepairMo repairMo){
        List<Map<String,Object>> mapList=repairMoMapper.queryQxCOUNT();  //查询任务总数
        List<Map<String,Object>> subList=repairMoMapper.querySubCOUNT();  //查询任务总数
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("mapList",mapList);
        map.put("subList",subList);
        return map;
    }



    @Override
    public Map<String,Object> queryCarOrMoList(RepairMo repairMo){
        Map<String,Object> map=new HashMap<String,Object>();
        Map<String,Object> mapcar= getCarMap(repairMo);
        map.put("carMap",mapcar);

        RepairMo mo=repairMoMapper.queryMoParn(repairMo.getSrcDocType(),repairMo.getSrcDocCode());
        if(mo!=null){
            map.put("moMap",mo);
            List<RepairMoLog> mologList=repairMoMapper.queryMoLogParn(mo.getMoCode());
            map.put("moLogList",mologList);
        }else{
            map.put("moMap","");
            map.put("moLogList","");
        }
        return map;
    }

    public Map<String,Object> getCarMap(RepairMo repairMo){

        Map<String,Object> map=new HashMap<String,Object>();
        String callTypes=repairMo.getSrcDocType();
//        switch (callTypes) {
//            case "1":
//                map =andonDriverService.queryMapParam(repairMo.getSrcDocCode());
//                break;
//            case "2":
//                map= andonCarService.queryMapParam(repairMo.getSrcDocCode());
//                //修改隐患信息
//                break;
//            case "3":
//                map =andonRoadService.queryMapParam(repairMo.getSrcDocCode());
//                break;
//            case "4":
//                map =andonCompanyService.queryMapParam(repairMo.getSrcDocCode());
//                break;
//            case "5":
//                break;
//            case "6":
//                break;
//            case "7":
//                map=andonRecordService.queryMapParam(Integer.valueOf(repairMo.getSrcDocType()),repairMo.getSrcDocCode());
//                break;
//        }
        return map;
    }

    @Override
    public Map<String,Object> queryDeductPh(RepairMo repairMo){
        Map<String,Object> map=new HashMap<String,Object>();
        List<String> wcCode=new ArrayList<String>();
        if(StringUtils.isNotEmpty(repairMo.getWcCode())){
            String [] wc_code=repairMo.getWcCode().split(",");
            for( String name:wc_code){
                wcCode.add(name);
            }
        }
//        repairMo.setWcCodeList(wcCode);
//        String monthdq=DateUtils.getReduceMonth(0);
//        String month=DateUtils.getReduceMonth(6);
//        repairMo.setWeixStdate(month);
//        repairMo.setWeixEddate(monthdq);
//
//        List<Map<String,Object>> xcjdListmap=repairMoMapper.queryDeductPhist(repairMo);
//        List<Map<String,Object>> qddklitmap=repairMoMapper.queryPersuadePhist(repairMo);
//        List<String> monthList=DateUtils.getReduceMonthListDq(6);
//        List<Integer> xcjdhList=new ArrayList<Integer>();
//        List<Integer> qddkList=new ArrayList<Integer>();
//        for (String name:monthList){
//            Integer shuaqxd=0;
//            for (Map<String,Object> maps:xcjdListmap){
//                if(name.equals(maps.get("sutime").toString())){
//                    shuaqxd=Integer.valueOf(maps.get("count").toString());
//                    break;
//                }
//            }
//            xcjdhList.add(shuaqxd);
//
//            Integer shuqddk=0;
//            for (Map<String,Object> maps:qddklitmap){
//                if(name.equals(maps.get("sutime").toString())){
//                    shuqddk=Integer.valueOf(maps.get("count").toString());
//                    break;
//                }
//            }
//            qddkList.add(shuqddk);
//        }
//        List<Map<String,Object>> reLit=new ArrayList<Map<String,Object>>();
//        Map<String,Object> xcjd=new HashMap<String,Object>();
//        xcjd.put("name","安全宣导");
//        xcjd.put("data",xcjdhList);
//        reLit.add(xcjd);
//        xcjd=new HashMap<String,Object>();
//        xcjd.put("name","劝导打卡");
//        xcjd.put("data",qddkList);
//        reLit.add(xcjd);
//        map.put("categories",monthList);
//        map.put("series",reLit);
        return  map;
    }

    @Override
    public Map<String,Object> queryRank(RepairMo repairMo){

        Map<String,Object> map=new HashMap<String,Object>();
//        List<Map<String,Object>> maplist=repairMoMapper.queryRank(repairMo);
//        List<String> orgCodeList=Arrays.asList("1","10","20");
//        List<String> title=new ArrayList<String>();
//        List<Integer> data=new ArrayList<Integer>();
//        Integer ljshu=0;
//        for(String name:orgCodeList){
//            Integer shu=0;
//            String nameLe="";
//            for(Map<String,Object> maps:maplist){
//                if(name.equals(maps.get("critical_level").toString())){
//                    shu=Integer.valueOf(maps.get("count").toString());
//                 }
//            }
//            if(name.equals("1")){
//                nameLe="三级隐患";
//                map.put("ybyh",shu);
//            }else if(name.equals("10")){
//                nameLe="二级隐患";
//                map.put("jdyh",shu);
//            }else if(name.equals("20")){
//                nameLe="一级隐患";
//                map.put("zdyh",shu);
//            }
//            ljshu=ljshu+shu;
//            title.add(nameLe);
//            data.add(shu);
//        }
//        map.put("zs",ljshu);
//        map.put("data",data);
//        map.put("title",title);
        return map;
    }


    @Override
    public Map<String,Object> queryAdministerWc(RepairMo repairMo){
        Map<String,Object> map=new HashMap<String,Object>();
//        repairMo.setStopTimes(repairMo.getWcCode().length()+3);
//        List<SysOrg> list=sysOrgMapper.queryPcCode(repairMo.getWcCode());
//        List<Map<String,Object>> maplistzs=repairMoMapper.queryRegionalZl(repairMo); //总数
//        repairMo.setMoState(2);
//        List<Map<String,Object>> maplistwcs=repairMoMapper.queryRegionalZl(repairMo); //总数完成数
//
//        List<String> title=new ArrayList<String>();
//        List<Integer> data=new ArrayList<Integer>();
//        List<Integer> data_zs=new ArrayList<Integer>();
//        List<Integer> data_cs=new ArrayList<Integer>();
//        for(SysOrg sys:list){
//            Integer datashu=0;
//            for (Map<String,Object> mapzs:maplistwcs){
//                if( mapzs.get("subs")!=null){
//                    if(sys.getCode().equals(mapzs.get("subs").toString())){
//                        datashu=Integer.valueOf(mapzs.get("count").toString());
//                        break;
//                    }
//                }
//            }
//            data.add(datashu);
//            Integer datazsshu=0;
//            for (Map<String,Object> mapzs:maplistzs){
//                if( mapzs.get("subs")!=null){
//                    if(sys.getCode().equals(mapzs.get("subs").toString())){
//                        datazsshu=Integer.valueOf(mapzs.get("count").toString());
//                        break;
//                    }
//                }
//            }
//            data_zs.add(datazsshu);
//            data_cs.add(datazsshu-datashu);
//            title.add(sys.getName());
//        }
//        map.put("title",title);
//        map.put("data",data);
//        map.put("data_zs",data_zs);
//        map.put("data_cs",data_cs);
        return map;
    }


    @Override
    public Map<String,Object> queryInterestCar(RepairMo repairMo){
//        List<Map<String,Object>> returnList=repairMoMapper.queryInterestCar(repairMo);  //查询任务总数
        Map<String,Object> map=new HashMap<String,Object>();
//        map.put("returnList",returnList);
        return map;
    }



    @Override
    public Map<String,Object> queryClassify(RepairMo repairMo){
        Map<String,Object> map=new HashMap<String,Object>();

//        List <Map<String,Object>> litmap=repairMoMapper.queryClassify(repairMo);  //查询待分配
//        List<Map<String,Object>> returnList=new ArrayList<Map<String,Object>>();
//        Map<String,Object> map1=new HashMap<String,Object>();
//        map1.put("type",AndonCallTypeEnum.driver.getValue());
//        map1.put("name","驾驶人");
//        returnList.add(map1);
//        map1=new HashMap<String,Object>();
//        map1.put("type",AndonCallTypeEnum.car.getValue());
//        map1.put("name","机动车");
//        returnList.add(map1);
//        map1=new HashMap<String,Object>();
//        map1.put("type",AndonCallTypeEnum.road.getValue());
//        map1.put("name","道路");
//        returnList.add(map1);
//        map1=new HashMap<String,Object>();
//        map1.put("type",AndonCallTypeEnum.company.getValue());
//        map1.put("name","企业");
//        returnList.add(map1);
        // 已关闭随手拍功能
//        map1=new HashMap<String,Object>();
//        map1.put("type",AndonCallTypeEnum.suishoupai.getValue());
//        map1.put("name","随手拍");
//        returnList.add(map1);
//        List<String> title=new ArrayList<String>();
//        List<Integer> data=new ArrayList<Integer>();
//        for (Map<String,Object> mapadd:returnList){
//            Integer shu=0;
//            for (Map<String,Object> maps:litmap){
//                //驾驶人	1   隐患宣导	5  定位打卡	6  随手拍	7  扣分记录	8  宣传阵地	9  车辆	2  道路	3  企业	4
//                if(mapadd.get("type").toString().equals(maps.get("call_type").toString())){
//                    shu=Integer.valueOf(maps.get("count").toString());
//                    break;
//                }
//            }
//            title.add(mapadd.get("name").toString());
//            data.add(shu);
//        }
//        map.put("title",title);
//        map.put("data",data);
        return map;
    }



    @Override
    public Map<String,Object> queryDiagram(RepairMo repairMo){
        Map<String,Object> map=new HashMap<String,Object>();
//        List<Map<String,Object>> returnList=repairMoMapper.queryDiagram(repairMo);  //查询任务总数
//        List<Object> reList=new ArrayList<Object>();
////        for(int i=0;i<30;i++){
//            for(Map<String,Object> maps:returnList){
//                String s2=maps.get("s2").toString();
//
//                reList.add(s2.split(","));
//            }
////        }
//        map.put("data",reList);
        return map;
    }



    @Override
    public Map<String,Object> queryQuantity(RepairMo repairMo){
        Map<String,Object> map=new HashMap<String,Object>();
//        List<Map<String,Object>> litmap=new ArrayList<Map<String,Object>>();
//        List<String> wcCode=new ArrayList<String>();
//        if(StringUtils.isNotEmpty(repairMo.getWcCode())){
//            String [] wc_code=repairMo.getWcCode().split(",");
//            for( String name:wc_code){
//                wcCode.add(name);
//            }
//        }
//        repairMo.setWcCodeList(wcCode);
//        Integer cxrc=repairMoMapper.queryQuantityR(repairMo);  //查询人次
//
//
//        RepairMo repair_mo=new RepairMo();
//        repair_mo.setWcCode(repairMo.getWcCode());
//        repair_mo.setWeixEddate(repairMo.getWeixEddate());
//        repair_mo.setWeixStdate(repairMo.getWeixStdate());
//        repair_mo.setWcCodeList(wcCode);
//        repair_mo.setStopTimes(0);
//        Integer cxr=repairMoMapper.queryQuantityR(repair_mo);  //查询人
//
//
//        Integer yhzs=repairMoMapper.queryQuantityYh(repairMo);  //查询隐患总数
//
//
//        repair_mo=new RepairMo();
//        repair_mo.setWcCode(repairMo.getWcCode());
//        repair_mo.setWeixEddate(repairMo.getWeixEddate());
//        repair_mo.setWeixStdate(repairMo.getWeixStdate());
//        repair_mo.setWcCodeList(wcCode);
//        repair_mo.setMoState(2);
//        Integer zgzs=repairMoMapper.queryQuantityYh(repair_mo);  //查询整改总数
//
//        Integer dkzs=repairMoMapper.queryQuantityDk(repairMo);  //查询整改总数
//        Integer xdzs=repairMoMapper.queryQuantityXd(repairMo);  //查询整改总数
//
//        map.put("cxrc",cxrc);
//        map.put("cxr",cxr);
//        map.put("yhzs",yhzs);
//        map.put("zgzs",zgzs);
//        map.put("dkzs",dkzs);
//        map.put("xdzs",xdzs);
        return map;
    }

    @Override
    public Page<RepairMo> queryByWaitForAssign(Page<RepairMo> page, RepairMo repairMo) {
        List<RepairMo> moList = new ArrayList<>();
        List<String> wcCode = new ArrayList<>();
        if(StringUtils.isNotEmpty(repairMo.getWcCode())){
            String [] wc_code = repairMo.getWcCode().split(",");
            for( String name:wc_code){
                wcCode.add(name);
            }
        }
        repairMo.setWcCodeList(wcCode);
//        List<DangerAllVoV2> list = baseMapper.queryByWaitForAssign(page, repairMo);
//        List<FaultCause> causeList = faultCauseService.list();// 查询隐患类型
//        list.stream().forEach(item -> {
//            RepairMo mo = new RepairMo();
//            mo.setId(item.getId());
//            mo.setCatalog(String.valueOf(RepairMoStateEnum.APPOINT.getValue()));
//            mo.setMoState(item.getCallState());
//            mo.setSrcDocType(item.getCallType());
//            mo.setSrcDocCode(item.getCallCode());
//            mo.setDeviceno(item.getDeviceno());
//            mo.setDevName(item.getDevName());
//            mo.setWcCode(item.getWcCode());
//            mo.setWcName(item.getWcName());
//            mo.setLocation(item.getLocation());
//            mo.setRecDate(item.getRecDate());
//            mo.setFaultCode(item.getFaultCode());
//            mo.setSignManId(item.getSignManId());
//            mo.setWeixStdate(item.getWeixStdate());
//            mo.setWeixEddate(item.getWeixEddate());
//            String faultCode = item.getFaultCode();
//            if (StringUtils.isNotBlank(faultCode)){
//                String resons = "";
//                for (FaultCause cause : causeList) {
//                    if (faultCode.contains(cause.getFaultCode())){
//                        resons += cause.getFaultResons() + ",";
//                    }
//                }
//                mo.setAbnContent(resons);
//            }
//            moList.add(mo);
//        });
        return page.setRecords(moList);
    }
}
