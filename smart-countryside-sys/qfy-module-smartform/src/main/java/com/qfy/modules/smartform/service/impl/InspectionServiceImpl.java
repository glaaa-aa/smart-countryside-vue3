package com.qfy.modules.smartform.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qfy.modules.smartform.api.dto.CheckListDto;
import com.qfy.modules.smartform.api.dto.ChkGroupPoint;
import com.qfy.modules.smartform.api.dto.InspectionDto;
import com.qfy.modules.smartform.entity.CheckList;
import com.qfy.modules.smartform.entity.Inspection;
import com.qfy.modules.smartform.entity.InspectionPoint;
import com.qfy.modules.smartform.mapper.InspectionMapper;
import com.qfy.modules.smartform.service.ICheckListService;
import com.qfy.modules.smartform.service.IInspectionPointService;
import com.qfy.modules.smartform.service.IInspectionService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.jeecg.common.system.vo.LoginUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: 巡检结果-主表
 * @Author: jeecg-boot
 * @Date:   2022-04-08
 * @Version: V1.0
 */
@Service
public class InspectionServiceImpl extends ServiceImpl<InspectionMapper, Inspection> implements IInspectionService {

    public static final Logger logger = LoggerFactory.getLogger(InspectionServiceImpl.class);

    @Autowired
    private IInspectionPointService pointService;

    @Autowired
    private ICheckListService checkListService;

    /**
     * 新增巡检记录
     *
     * @param dto
     * @throws Exception
     */
    @Override
    @Transactional
    public void addMain(InspectionDto dto, LoginUser user) throws Exception {

        Integer inspecId = dto.getTaskid();
        dto.setSubmittime(new Date());
        // 第一步、判断并获取巡检记录主表信息
        Inspection inspection = null;
        if (null != inspecId) {
            inspection = baseMapper.selectById(inspecId);
        } else {
            inspection = new Inspection();
            BeanUtil.copyProperties(dto, inspection, true);
        }

        // 无须用户登录，即匿名用户
        if (null == user){
            inspection.setCreateBy(dto.getSubmitter());
        } else {
            inspection.setSysOrgCode(user.getOrgCode());
            inspection.setCreateBy(user.getUsername());
        }

        // 保存巡检记录主表
        if (inspection.getId() == null) {
            inspection.setCreateTime(new Date());
            inspection.setState(1);
            inspection.setInspectype("other");
            inspection.setIsSchedule(0);
            inspection.setScore(0d);
            inspecId = baseMapper.insert(inspection);
        } else {
            inspection.setUpdateTime(new Date());
            saveOrUpdate(inspection);
            inspecId = inspection.getId();
        }

        // 第二步、实例化巡检记录附表（巡检点记录）信息
        InspectionPoint point = new InspectionPoint();
        BeanUtil.copyProperties(dto, point);
        point.setSerialno(System.currentTimeMillis() + "");
        point.setIsvalid(1);
        point.setState(3);
        point.setStatus(1);
        point.setBaseScore(0);
        point.setChecktime(dto.getSubmittime());
        point.setInspectionId(inspection.getId().toString());
        point.setTemplateId(dto.getTemplateId());

        pointService.save(point);
        // 获取所有巡检项
        List<CheckListDto> checkListDtoList = new ArrayList<CheckListDto>();
        for (ChkGroupPoint group : dto.getGroups()) {
            checkListDtoList.addAll(group.getCheckListDtoList());
        }

        // 第三步、实例化巡检记录附表（表单项记录）信息
        List<CheckList> checkLists = new ArrayList<>(checkListDtoList.size());
        for (CheckListDto checkListDto : checkListDtoList) {
            CheckList checkList = new CheckList();
            BeanUtil.copyProperties(checkListDto, checkList, true);
            checkList.setScoreType(1);
            checkList.setIsvalid(1);
            // 巡检项记录业务逻辑处理
            checkList.setInspecPoiId(point.getId());
            String results = checkList.getResults();
            // 检查结果如果以",，"逗号结尾，则剔除掉
            if (StringUtils.endsWithAny(results, ",", "，")) {
                results = results.substring(0, results.length() - 1);
                checkList.setResults(results);
            }
            checkLists.add(checkList);
        }
        checkListService.saveBatch(checkLists);


        // --------------------------旧巡检代码逻辑-START--------------------------------------

//        Integer inspecId = dto.getTaskid();
//        dto.setSubmittime(new Date());
//        // 第一步、判断并获取巡检记录主表信息
//        Inspection inspection = null;
//        if (null != inspecId) {
//            inspection = getInspection(dto,  inspecId);
//        } else {
//            inspection = getHistoryInspection(dto);
//        }

        // 第一步、判断并获取巡检记录主表信息
//		InspectionEntity inspection = getHistoryInspection(dto);
        // 第二步、实例化巡检记录主表信息
//		Object inspectionId = null;
        // 保存巡检记录主表
//        if (inspection.getId() == null) {
//            inspecId = save(inspection, user);
//        } else {
//            saveOrUpdate(inspection, user);
//            inspecId = inspection.getId();
//        }

//        Integer scoreType = null;// 分值类型
//        Integer baseScore = 0;// 基础分值

        // 与巡检点相关联
//        if (null != dto.getChkPoiId()) {
//            // 获取巡检点
//            CheckPointEntity checkPoint = get(CheckPointEntity.class, dto.getChkPoiId());
//            if (null == checkPoint) {
//                LOGGER.error("未找到相应的巡检点[ID={}]", dto.getChkPoiId());
//                throw new Exception("未找到相应的巡检点。");
//            }
//            scoreType = checkPoint.getScoreType();
//            baseScore = null == checkPoint.getBaseScore() ? 0 : checkPoint.getBaseScore();
//        }

        // 从模板生成的巡检记录
//        if (StringUtils.isNotBlank(dto.getTemplateId())) {
//            // 获取模板
//            CheckTemplateEntity checkTemplate = get(CheckTemplateEntity.class, dto.getTemplateId());
//            if (null == checkTemplate) {
//                LOGGER.error("未找到相应的模板[ID={}]", dto.getTemplateId());
//                throw new Exception("未找到相应的模板。");
//            }
//            scoreType = checkTemplate.getScoreType();
//            baseScore = StringUtils.isBlank(checkTemplate.getBaseScore()) ? 0 : Integer.parseInt(checkTemplate.getBaseScore());
//        }

        // 第三步、实例化巡检记录附表（巡检点记录）信息
//        InspectionPointEntity inspectionPointEntity = new InspectionPointEntity();
//        MyBeanUtils.copyBeanNotNull2Bean(dto, inspectionPointEntity);
//
//        // 获取所有巡检项
//        List<CheckListDto> checkListDtoList = new ArrayList<CheckListDto>();
//        for (ChkGroupPoint group : dto.getGroups()) {
//            checkListDtoList.addAll(group.getCheckListDtoList());
//        }

        // 评分处理
//        boolean isValid = true;// 巡检点是否合格
//        Double score = 0d;// 巡检点得分
        // List<CheckListDto> checkListDtoList = dto.getCheckListDtoList();
//        for (CheckListDto checkListDto : checkListDtoList) {
//            // 巡检项得分计算
//            double itemScore = checkListDto.getScore().doubleValue();
//            if ("number".equals(checkListDto.getType())) {
//                CheckItemNumberEntity checkItemNumberEntity = findUniqueByProperty(CheckItemNumberEntity.class, "chkid", checkListDto.getChkid());
//                if (null != scoreType && scoreType.intValue() == 1) { // 加分项
//                    // 数字类型巡检项 根据打分判断是否合格，并获取预设的合格评分
//
//                    if (itemScore >= checkItemNumberEntity.getUlqualified().doubleValue()) { // 大于等于合格值下限
//                        checkListDto.setScore(itemScore);
//                        checkListDto.setIsvalid(1); // 合格
//                    } else if (itemScore < checkItemNumberEntity.getLlqualified().doubleValue()) {// 小于合格值下限
//                        checkListDto.setIsvalid(0);// 不合格
//                        checkListDto.setScore(itemScore);
//                    }
//
//                } else if (null != scoreType && scoreType.intValue() != 1) {// 扣分项
//                    if (itemScore <= checkItemNumberEntity.getLlqualified().doubleValue()) { // 小于下限 应该为合格
//                        checkListDto.setScore(itemScore);
//                        checkListDto.setIsvalid(1);
//                    } else if (itemScore > checkItemNumberEntity.getLlqualified().doubleValue()) { // 大于合格值下限
//                        checkListDto.setIsvalid(0);
//                        checkListDto.setScore(itemScore);
//                    }
//
//                }
//
//            } else if ("multiple".equals(checkListDto.getType())) {
//                // 多选巡检项 得分则为选中各项的和 - app已计算
//            } else if ("single".equals(checkListDto.getType())) {
//                // 单选、得分为该项的值，-- app已计算
//            } else {
//                // 日期、文本、图片、根据是否合格来获取预设得分
//                CheckItemEntity checkItemEntity = getEntity(CheckItemEntity.class, checkListDto.getChkid());
//                if (checkListDto.getIsvalid().intValue() == 0) {
//                    checkListDto.setScore(checkItemEntity.getUnqualifiedscore());
//                } else {
//                    checkListDto.setScore(checkItemEntity.getQualifiedscore());
//                }
//            }
//            // 只要有一项不合格，则该点不合格
//            if (isValid && checkListDto.getIsvalid().intValue() == 0) {
//                isValid = false;
//            }
//            // 计算总和
//            score += checkListDto.getScore().doubleValue();
//        }

        // 如果巡检点不合格，则将该点状态改为0 待处理 合格直接把状态改为 3 已完成
//        if (!isValid) {
//            inspectionPointEntity.setIsvalid(0);
//        } else {
//            inspectionPointEntity.setIsvalid(1);
//            inspectionPointEntity.setState(3);// 已完成
//        }
//
//        if (null != scoreType) {
//            if (scoreType.intValue() == 1) {
//                // 加分类型
//                inspectionPointEntity.setScore(score);
//            } else {
//                // 扣分类型
//                inspectionPointEntity.setScore(baseScore - score);
//                if (inspectionPointEntity.getScore() < 0) {
//                    inspectionPointEntity.setScore(0d);
//                }
//            }
//        }
//
//        // 保存巡检记录点表
//        inspectionPointEntity.setChecktime(dto.getSubmittime());
//        inspectionPointEntity.setInspectionId(inspecId.toString());
//        inspectionPointEntity.setBaseScore(baseScore);
//        Object inspectionPointId = save(inspectionPointEntity);
//
//        // 第四步、实例化巡检记录附表（巡检项记录）信息
//        for (CheckListDto checkListDto : checkListDtoList) {
//            CheckListEntity checkListEntity = new CheckListEntity();
//            MyBeanUtils.copyBeanNotNull2Bean(checkListDto, checkListEntity);
//            checkListEntity.setScoreType(scoreType);
//            // 巡检项记录业务逻辑处理
//            checkListEntity.setInspecPoiId((Integer) inspectionPointId);
//            String results = checkListEntity.getResults();
//            // 检查结果如果以",，"逗号结尾，则剔除掉
//            if (StringUtils.endsWithAny(results, ",", "，")) {
//                results = results.substring(0, results.length() - 1);
//                checkListEntity.setResults(results);
//            }
//            save(checkListEntity);
//        }
        // --------------------------旧巡检代码逻辑-END--------------------------------------
    }

    /**
     * 根据巡检记录ID获取并初始化巡检主表
     *
     * @param dto
     * @return
     * @throws Exception
     */
    private Inspection getInspection(InspectionDto dto, Integer inspectId) throws Exception {
        logger.debug("===>getInspection(dto,{})", inspectId);
        Integer chkPlanId = dto.getChkPlanId();
        Integer chkRouteId = dto.getChkRouteId();
        Double fenzhi = dto.getScore();
        Integer isSchedule = 1;// 是否逾期， 0 为否 1为是
        int inspectionState = 9;// 巡检记录状态，默认已完成
        String inspectype = "plan";// 巡检类型

//        InspectionEntity inspection = this.getEntity(InspectionEntity.class, inspectId);
//        if (null == inspection || inspection.getState().intValue() == 9) {
//            inspection = new InspectionEntity();
//            MyBeanUtils.copyBeanNotNull2Bean(dto, inspection);
//        }
//
//        // 计划外路线巡检或者计划巡检
//        if (StringUtil.isNotEmpty(chkRouteId)) {
//            if (StringUtil.isNotEmpty(chkPlanId)) {// 计划巡检
//                inspectype = "plan";
//                CheckPlanEntity planEntity = this.getEntity(CheckPlanEntity.class, chkPlanId);
//                if ("month".equals(planEntity.getPlantype())) {
//                    isSchedule = PlanUtils.isSchedule(planEntity.getSttime(), planEntity.getEdtime());
//                }
//            } else {
//                // 计划外路线巡检
//                inspectype = "route";
//            }
//
//            // 特殊处理 如果路线所有点已经巡检完成 则发起的记录
//            if (inspection.getId() != null) {
//                List<InspectionPointEntity> list1 = this.findByProperty(InspectionPointEntity.class, "inspectionId", String.valueOf(inspection.getId()));
//                List<CheckRoutePointEntity> list2 = this.findByProperty(CheckRoutePointEntity.class, "chkRouteId", inspection.getChkRouteId());
//                if (list1.size() + 1 < list2.size()) {// 巡检点数量小于巡检配置数量
//                    inspectionState = 2;// 标记为处理中
//                }
//            } else {
//                // 查询巡检点巡检情况,如果只有一个巡检点将状态改为已完成，或者是移动巡检
//                if (StringUtil.isNotEmpty(chkRouteId)) {
//                    List<CheckRoutePointEntity> list2 = this.findByProperty(CheckRoutePointEntity.class, "chkRouteId", inspection.getChkRouteId());
//                    if (list2.size() > 1) {
//                        inspectionState = 2;// 标记为处理中
//                    }
//                }
//            }
//        } else {// 计划外移动巡检 直接发起新的记录
//            inspectype = "move";
//        }
//
//        if (StringUtil.isEmpty(inspection.getPlanDate())) {
//            inspection.setPlanDate(DateUtils.formatDate(new Date(), "yyyy-MM-dd"));
//        }
//        inspection.setState(inspectionState);
//        inspection.setInspectype(inspectype);
//        inspection.setIsSchedule(isSchedule);
//        inspection.setScore(fenzhi);
//        inspection.setSubmitTime(dto.getSubmittime());// 最后提交时间
//
//        if (StringUtil.isEmpty(inspection.getSn())) {
//            String sn = DateUtils.getDataString(DateUtils.yyyymmddhhmmss);// 自动生成编号
//            inspection.setSn(sn);
//        }
//        return inspection;
        return null;
    }

    /**
     * 根据巡检信息获取并初始化巡检主表
     *
     * @param dto
     * @return
     * @throws Exception
     */
    private Inspection getHistoryInspection(InspectionDto dto) throws Exception {
        logger.debug("===>getHistoryInspection(dto,{})", dto.getTaskid());
        Integer chkPlanId = dto.getChkPlanId();
        Integer chkRouteId = dto.getChkRouteId();
        Integer sortno = dto.getSortno();
        Double fenzhi = dto.getScore();
        Integer isSchedule = 1;// 是否逾期， 0 为否 1为是
        int inspectionState = 9;// 巡检记录状态，默认已完成
        String inspectype = "plan";// 巡检类型

//        InspectionEntity inspection = null;
//        // 前端传递taskid(巡检记录id)
//        if (null != dto.getTaskid()) {
//            inspection = this.getEntity(InspectionEntity.class, dto.getTaskid());
//            if (null == inspection) {
//                inspection = new InspectionEntity();
//            }
//        } else {
//            inspection = new InspectionEntity();
//        }
//
//        CriteriaQuery cq = new CriteriaQuery(InspectionEntity.class);
//        if (StringUtil.isNotEmpty(chkRouteId)) {
//            String planDate = DateUtils.formatDate();
//            if (StringUtil.isNotEmpty(chkPlanId)) {// 计划巡检
//                inspectype = "plan";
//                cq.add(Property.forName("chkPlanId").eq(chkPlanId));
//                cq.add(Property.forName("sortno").eq(sortno));
//                // 根据inspectype + chkPlanId + chkRouteId + sortno + 日期（创建时间） 获取记录表信息，方便更新记录
//                CheckPlanEntity planEntity = this.getEntity(CheckPlanEntity.class, chkPlanId);
//                if ("month".equals(planEntity.getPlantype())) {
//                    isSchedule = PlanUtils.isSchedule(planEntity.getSttime(), planEntity.getEdtime());
//                    cq.add(Property.forName("createDate").gt(PlanUtils.monthDay()));
//                } else {
//                    cq.add(Property.forName("planDate").eq(planDate));
//                }
//            } else {// 计划外路线巡检
//                inspectype = "route";
//                cq.add(Property.forName("planDate").eq(planDate));
//            }
//            cq.add(Property.forName("chkRouteId").eq(chkRouteId));
//            cq.add(Property.forName("inspectype").eq(inspectype));
//            cq.addOrder("createDate", SortDirection.desc);
//            List<InspectionEntity> list = this.getListByCriteriaQuery(cq, false);
//            if (list != null && list.size() > 0) {
//                InspectionEntity inspectionEntity = list.get(0);
//                if (inspectionEntity.getState().intValue() != 9) {
//                    inspection = list.get(0);
//                } else {
//                    MyBeanUtils.copyBeanNotNull2Bean(dto, inspection);
//                }
//            } else {
//                MyBeanUtils.copyBeanNotNull2Bean(dto, inspection);
//            }
//            // 特殊处理 如果路线所有点已经巡检完成 则发起的记录
//            if (inspection.getId() != null) {
//                List<InspectionPointEntity> list1 = this.findByProperty(InspectionPointEntity.class, "inspectionId", String.valueOf(inspection.getId()));
//                List<CheckRoutePointEntity> list2 = this.findByProperty(CheckRoutePointEntity.class, "chkRouteId", inspection.getChkRouteId());
//                if (list1.size() + 1 < list2.size()) {// 巡检点数量小于巡检配置数量
//                    inspectionState = 2;// 标记为处理中
//                }
//            } else {
//                // 查询巡检点巡检情况,如果只有一个巡检点将状态改为已完成，或者是移动巡检
//                if (StringUtil.isNotEmpty(chkRouteId)) {
//                    List<CheckRoutePointEntity> list2 = this.findByProperty(CheckRoutePointEntity.class, "chkRouteId", inspection.getChkRouteId());
//                    if (list2.size() > 1) {
//                        inspectionState = 2;// 标记为处理中
//                    }
//                }
//            }
//
//        } else {// 计划外移动巡检 直接发起新的记录
//            inspectype = "move";
//        }
//        if (StringUtil.isEmpty(inspection.getPlanDate())) {
//            inspection.setPlanDate(DateUtils.formatDate(new Date(), "yyyy-MM-dd"));
//        }
//        inspection.setState(inspectionState);
//        inspection.setInspectype(inspectype);
//        inspection.setIsSchedule(isSchedule);
//        inspection.setScore(fenzhi);
//        inspection.setSubmitTime(dto.getSubmittime());// 最后提交时间
//        inspection.setSortno(sortno);
//        if (StringUtil.isEmpty(inspection.getSn())) {
//            String sn = DateUtils.getDataString(DateUtils.yyyymmddhhmmss);// 自动生成编号
//            inspection.setSn(sn);
//        }
//        return inspection;
        return null;
    }
}
