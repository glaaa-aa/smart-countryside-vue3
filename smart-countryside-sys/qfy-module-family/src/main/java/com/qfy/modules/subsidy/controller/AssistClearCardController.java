package com.qfy.modules.subsidy.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qfy.modules.family.entity.FamilyBase;
import com.qfy.modules.family.entity.FamilyBaseProduction;
import com.qfy.modules.family.service.IFamilyBaseProductionService;
import com.qfy.modules.family.service.IFamilyBaseService;
import com.qfy.modules.org.entity.SysOrg;
import com.qfy.modules.org.service.ISysOrgService;
import com.qfy.modules.subsidy.entity.AssistPerson;
import com.qfy.modules.subsidy.service.IAssistEnterpriseRelService;
import com.qfy.modules.subsidy.service.IAssistPersonService;
import com.qfy.modules.subsidy.service.ICooperativeEnterprisesService;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.jeecg.common.system.api.ISysBaseAPI;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.vo.DictModel;
import org.jeecg.common.util.DateUtils;
import org.jeecg.common.util.SpringContextUtils;
import org.jeecg.config.StaticConfig;
import org.jeecgframework.poi.excel.def.TemplateExcelConstants;
import org.jeecgframework.poi.excel.entity.TemplateExportParams;
import org.jeecgframework.poi.excel.view.JeecgTemplateExcelView;
import org.jeecgframework.poi.util.PoiPublicUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author lanls
 * @version 1.0
 * @description:
 * @date 2022/12/5 18:08
 */
@RestController
@RequestMapping("/family/assist")
public class AssistClearCardController extends JeecgController<FamilyBase,IFamilyBaseService> {

    public static final Logger logger = LoggerFactory.getLogger(AssistClearCardController.class);

    @Resource
    private IFamilyBaseService familyBaseService;
    @Resource
    private IAssistEnterpriseRelService enterpriseRelService;
    @Resource
    private ICooperativeEnterprisesService enterprisesService;
    @Resource
    private IAssistPersonService assistPersonService;
    @Resource
    private ISysOrgService sysOrgService;
    @Resource
    private ISysBaseAPI sysBaseAPI;
    @Resource
    private IFamilyBaseProductionService baseProductionService;

    @RequestMapping("/clearCard/exportXls")
    public ModelAndView exportExcel(HttpServletRequest request) throws Exception{
        String id = request.getParameter("id");
        FamilyBase family = familyBaseService.getById(id);
        QueryWrapper wrapper = new QueryWrapper<>();
        wrapper.eq("family_number", family.getFamilyNumber());
        wrapper.last("limit 1");
        FamilyBaseProduction production = baseProductionService.getOne(wrapper);

        wrapper.clear();
        wrapper.eq("c.family_number", family.getFamilyNumber());
        wrapper.last("limit 1");
        AssistPerson person = assistPersonService.selectOne(wrapper);
        List<SysOrg> orgList = sysOrgService.list();
        SysOrg village = orgList.stream().filter(o -> {
                    return StringUtils.equals(o.getCode(),family.getOrgCode());
                }).findFirst().orElse(null);
        SysOrg atural = orgList.stream().filter(o -> {
            return StringUtils.equals(o.getCode(),family.getAturalCode());
        }).findFirst().orElse(null);
        Map<String, Object> data = new HashMap<>();
        data.put("householder",family.getName());
        data.put("holderTelephone",family.getTelephone());
        data.put("linkName",person.getRealname());
        data.put("unit",person.getExt1());
        data.put("linkTelephone",person.getTelephone());
        data.put("village",null == village ? "" : village.getName());
        data.put("team", null == atural ? "" : atural.getName());
        data.put("houseNumber", family.getHouseNumber());
        data.put("isEliminate","");
        data.put("tuopinYear","");
//        data.put("symbol","\u221A");
        if (StringUtils.isNotBlank(family.getTuopinYear())){
            data.put("tuopinYear", "脱贫户（" + family.getTuopinYear() + "年）");
        }

        if (StringUtils.isBlank(family.getTuopinYear()) && null != family.getIsEliminate()){
            String isEliminate = "监测对象（是否已消除风险：";
            data.put("isEliminate", family.getIsEliminate().intValue() == 0 ? isEliminate + "否）" : isEliminate + "是）");
        }

        data.put("bringIntoDate", "");
        String bringIntoDate = family.getDiscernDate();
        if (StringUtils.isNotBlank(bringIntoDate)){
            bringIntoDate = DateUtils.formatDate(DateUtils.parseDate(bringIntoDate,"yyyy-MM-dd"),"yyyy年MM月dd日");
            data.put("bringIntoDate", bringIntoDate);
        }

        data.put("clearDate", "");
        String clearDate = family.getEliminateDate();
        if (StringUtils.isNotBlank(clearDate)){
            clearDate = DateUtils.formatDate(DateUtils.parseDate(clearDate,"yyyy-MM-dd"),"yyyy年MM月dd日");
            data.put("clearDate", clearDate);
        }

        if (null != production){
            String[] dictCodeList = {"product_finance","product_sale","product_plantBreed","product_technicalTraining",
                    "product_varietyImprovement","product_socializationService","product_facilityConstruction",
                    "product_rewardCompensate","product_stockDividends",
                    "product_incomeDistribution","product_inWorks","product_land","product_trade","product_others"};
            Map<String,List<DictModel>> dictItemMap = sysBaseAPI.getManyDictItems(Arrays.asList(dictCodeList));
            Field[] fields = PoiPublicUtil.getClassFields(FamilyBaseProduction.class);
            for (String dictCode : dictCodeList) {
                List<DictModel> dictModels = dictItemMap.get(dictCode);
                String name = dictCode.replace("product_","");
                if (CollectionUtils.isNotEmpty(dictModels)){
                    Field field = Arrays.stream(fields).filter(f -> {
                        return StringUtils.equals(f.getName().toLowerCase(),name.toLowerCase());
                    }).findFirst().orElse(null);
                    field.setAccessible(true);
                    String value = null == field ? "" : field.get(production).toString();
                    DictModel model = dictModels.stream().filter(m -> {
                        return StringUtils.equals(m.getValue(), value);
                    }).findFirst().orElse(null);
                    data.put(name, null == model ? "无" : model.getText());
                } else {
                    data.put(name, "无");
                }
            }
        }

        StaticConfig config = SpringContextUtils.getBean(StaticConfig.class);
        TemplateExportParams params = new TemplateExportParams();
        params.setTemplateUrl(config.getClearCardExcelPath());

        ModelAndView mav = new ModelAndView(new JeecgTemplateExcelView());
        mav.addObject(TemplateExcelConstants.FILE_NAME, "产业帮扶明白卡");
        mav.addObject(TemplateExcelConstants.PARAMS, params);
        mav.addObject(TemplateExcelConstants.MAP_DATA, data);
        return mav;
    }
}
