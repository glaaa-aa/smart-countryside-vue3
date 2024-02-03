package com.qfy.modules.subsidy.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qfy.modules.family.entity.FamilyBase;
import com.qfy.modules.family.service.IFamilyBaseService;
import com.qfy.modules.org.entity.SysOrg;
import com.qfy.modules.org.service.ISysOrgService;
import com.qfy.modules.subsidy.entity.AssistEnterpriseRel;
import com.qfy.modules.subsidy.entity.AssistPerson;
import com.qfy.modules.subsidy.entity.CooperativeEnterprises;
import com.qfy.modules.subsidy.service.IAssistEnterpriseRelService;
import com.qfy.modules.subsidy.service.IAssistPersonService;
import com.qfy.modules.subsidy.service.ICooperativeEnterprisesService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
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
public class AssistLinkCardController extends JeecgController<FamilyBase,IFamilyBaseService> {

    public static final Logger logger = LoggerFactory.getLogger(AssistLinkCardController.class);

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

    @RequestMapping("/linkCard/exportXls")
    public ModelAndView exportExcel(HttpServletRequest request) throws Exception{
        String id = request.getParameter("id");
        FamilyBase family = familyBaseService.getById(id);
        QueryWrapper wrapper = new QueryWrapper<>();
        wrapper.eq("family_number", family.getFamilyNumber());
        List<AssistEnterpriseRel> rels = enterpriseRelService.list(wrapper);
        List<String> eids = rels.stream().map(AssistEnterpriseRel::getEid).collect(Collectors.toList());
        List<CooperativeEnterprises> enterprisesList = Collections.emptyList();
        if (CollectionUtils.isNotEmpty(eids)){
            wrapper.clear();
            wrapper.in("id", eids);
            enterprisesList = enterprisesService.list(wrapper);
        }

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

        List<Map<String, Object>> list = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(enterprisesList)){
            List<DictModel> dictModelList = sysBaseAPI.getDictItems("assistMethod");
            int index = 1;
            for (CooperativeEnterprises enterprises : enterprisesList) {
                Map<String, Object> item = new HashMap<>();
                item.put("num", index);
                item.put("name", enterprises.getName());
                item.put("manager", enterprises.getLegalPerson());
                item.put("telephone", enterprises.getTelephone());
                item.put("address", enterprises.getAddress());
                item.put("scope", enterprises.getBusinessScope());
                AssistEnterpriseRel rel = rels.stream().filter(r -> {
                    return StringUtils.equals(r.getEid(), enterprises.getId());
                }).findFirst().orElse(null);

                DictModel model = dictModelList.stream().filter(m -> {
                    return StringUtils.equals(m.getValue(), rel.getAssistMethod().toString());
                }).findFirst().orElse(null);
                item.put("method", model.getText());
                list.add(item);
                index ++;
            }
        }
        data.put("list",list);

        StaticConfig config = SpringContextUtils.getBean(StaticConfig.class);
        TemplateExportParams params = new TemplateExportParams();
        params.setTemplateUrl(config.getLinkCardExcelPath());

        ModelAndView mav = new ModelAndView(new JeecgTemplateExcelView());
        mav.addObject(TemplateExcelConstants.FILE_NAME, "联农带农联系卡");
        mav.addObject(TemplateExcelConstants.PARAMS, params);
        mav.addObject(TemplateExcelConstants.MAP_DATA, data);
        return mav;
    }
}
