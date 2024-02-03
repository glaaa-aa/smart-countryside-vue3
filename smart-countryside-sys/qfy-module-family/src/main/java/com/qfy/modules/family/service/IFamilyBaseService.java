package com.qfy.modules.family.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qfy.modules.family.entity.FamilyBase;
import com.qfy.modules.family.entity.VillageRanking;
import com.qfy.modules.family.model.FamilyBasePage;
import com.qfy.modules.family.model.FamilyBaseResult;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.jeecg.common.exception.JeecgBootException;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Description: qfy_family_base
 * @Author: jeecg-boot
 * @Date:   2022-06-02
 * @Version: V1.0
 */
public interface IFamilyBaseService extends IService<FamilyBase> {

    //获取最大编号
    public String getMaxNumber();

    /**
     * 根据户编号和年查询数据
     * @param familyNumber
     * @param year
     * @return
     */
    public FamilyBasePage getFamilyByNumberAndYear(String familyNumber, String year);

//    public FamilyBasePage getFamilyById(String id);


    /**
     * 户信息添加
     * @param familyBase
     */
    public FamilyBase saveFamilyBase(FamilyBase familyBase) throws JeecgBootException;

    /**
     * 添加一对多
     * @param familyPage
     */
    public void saveMain(FamilyBasePage familyPage) throws JeecgBootException;

    /**
     * 修改一对多
     *
     * @param familyBasePage
     */
    public void updateMain(FamilyBasePage familyBasePage);


    public void updateCopyMain(FamilyBasePage familyBasePage);

    /**
     * 删除一对多
     * @param id
     */
    public void delMain (String id);

    /**
     * 批量删除一对多
     *
     * @param idList
     */
    public void delBatchMain (Collection<? extends Serializable> idList);


    FamilyBaseResult getFamilyBaseById(String id);

    ModelAndView detailExportXls(HttpServletRequest request, FamilyBase familyBase);

    /**
     * 方法描述：首页各项统计
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @author: lanls
     * @date: 2023/12/11 18:01
     */
    List<VillageRanking> selectIndexChartStatics();

    List<VillageRanking> selectIndexVillageRanking();
}
