package com.qfy.modules.family.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qfy.modules.family.entity.FamilyBase;
import com.qfy.modules.family.entity.FamilyIntegral;
import com.qfy.modules.family.mapper.FamilyIntegralMapper;
import com.qfy.modules.family.service.IFamilyBaseService;
import com.qfy.modules.family.service.IFamilyIntegralService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.jeecg.common.constant.CommonConstant;
import org.jeecg.common.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * @Description: 农户积分手册
 * @Author: jeecg-boot
 * @Date:   2022-12-01
 * @Version: V1.0
 */
@Service
public class FamilyIntegralServiceImpl extends ServiceImpl<FamilyIntegralMapper, FamilyIntegral> implements IFamilyIntegralService {

    public static final Logger logger = LoggerFactory.getLogger(FamilyIntegralServiceImpl.class);

    @Resource
    private IFamilyBaseService familyBaseService;

    @Value("${custom.config.tpl-id:''}")
    private String tplId; // 模板id

    @Override
    public void sync(Map<String,Object> params) {

        logger.info("开始同步农户基础积分...");
        List<FamilyBase> familyList;
        if (params.containsKey("familyList")){
            familyList = (List<FamilyBase>)params.get("familyList");
        } else {
            QueryWrapper<FamilyBase> wrapper = new QueryWrapper<>();
            wrapper.notExists("SELECT id FROM qfy_family_integral " +
                    " WHERE qfy_family_base.family_number = qfy_family_integral.family_number");
            familyList = familyBaseService.list(wrapper);
        }

        if (!CollectionUtils.isEmpty(familyList)){
            int size = familyList.size();
            List<FamilyIntegral> integralList = new ArrayList<>(size);

            for (FamilyBase family : familyList) {
                FamilyIntegral integral = new FamilyIntegral();
                integral.setBase(100f); // 基础积分
                integral.setTotal(100f); // 总积分
                integral.setFamilyManager(family.getIdCard()); // 户主身份证号
                integral.setExt1(family.getName()); // 户主姓名
                integral.setTeam(family.getAturalCode());// 自然屯
                integral.setVillage(family.getOrgCode());// 自然村
                integral.setNaturalTun(family.getS1());// 片区
                integral.setDataYear(DateUtils.formatDate("yyyy")); // 数据年份
                integral.setFamilyNumber(family.getFamilyNumber()); // 户编号
                integral.setTemplateId(tplId); // 积分模板ID
//                integral.setCreateBy("admin");
//                integral.setCreateTime(new Date());
                integral.setDelFlag(CommonConstant.DEL_FLAG_0);
                integralList.add(integral);
            }
            logger.info("同步农户基础积分共{}户...", size);
            saveBatch(integralList,size);
        }
        logger.info("同步农户基础积分结束...");
    }
}
