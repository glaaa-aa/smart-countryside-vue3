package com.qfy.modules.family.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qfy.modules.city3d.mapper.AddressMapper;
import com.qfy.modules.city3d.po.Address;
import com.qfy.modules.family.entity.FamilyBase;
import com.qfy.modules.family.entity.FamilyBaseHouse;
import com.qfy.modules.family.entity.FamilyBaseProduction;
import com.qfy.modules.family.entity.FamilyBaseWater;
import com.qfy.modules.family.entity.FamilyMember;
import com.qfy.modules.family.entity.VillageRanking;
import com.qfy.modules.family.mapper.FamilyBaseHouseMapper;
import com.qfy.modules.family.mapper.FamilyBaseMapper;
import com.qfy.modules.family.mapper.FamilyBaseProductionMapper;
import com.qfy.modules.family.mapper.FamilyBaseWaterMapper;
import com.qfy.modules.family.mapper.FamilyIntegralMapper;
import com.qfy.modules.family.mapper.FamilyIntegralRecordMapper;
import com.qfy.modules.family.mapper.FamilyMemberMapper;
import com.qfy.modules.family.model.FamilyBasePage;
import com.qfy.modules.family.model.FamilyBaseResult;
import com.qfy.modules.family.model.FamilyExport;
import com.qfy.modules.family.service.IFamilyBaseService;
import com.qfy.modules.org.entity.SysOrg;
import com.qfy.modules.org.service.ISysOrgService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.constant.CommonConstant;
import org.jeecg.common.exception.JeecgBootException;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.common.util.oConvertUtils;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Description: qfy_family_base
 * @Author: jeecg-boot
 * @Date:   2022-06-02
 * @Version: V1.0
 */
@Service
public class FamilyBaseServiceImpl extends ServiceImpl<FamilyBaseMapper, FamilyBase> implements IFamilyBaseService {

    @Resource
    private ISysOrgService sysOrgService;
    @Resource
    private FamilyBaseMapper familyBaseMapper;
    @Resource
    private FamilyBaseWaterMapper familyBaseWaterMapper;
    @Resource
    private FamilyBaseHouseMapper familyBaseHouseMapper;
    @Resource
    private FamilyBaseProductionMapper familyBaseProductionMapper;
    @Resource
    private FamilyIntegralMapper integralMapper;
    @Resource
    private FamilyIntegralRecordMapper integralRecordMapper;
    @Resource
    private AddressMapper addressMapper;
    @Resource
    private FamilyMemberMapper memberMapper;

    @Override
    public String getMaxNumber() {
        return familyBaseMapper.getMaxNumber();
    }

    @Override
    public FamilyBasePage getFamilyByNumberAndYear(String familyNumber, String year) {

        FamilyBasePage page = new FamilyBasePage();
        page.setFamilyNumber(familyNumber);
        page.setYear(year);

        FamilyBase familyBase = familyBaseMapper.getByfamilyNumber(familyNumber);
        page.setFamilyBase(familyBase);

        if(year != null){
            FamilyBaseWater water = familyBaseWaterMapper.queryWater(familyNumber,year);
            FamilyBaseHouse house = familyBaseHouseMapper.queryHouse(familyNumber,year);
            FamilyBaseProduction production = familyBaseProductionMapper.queryProduction(familyNumber,year);

            page.setFamilyHouse(house);
            page.setFamilyProduction(production);
            page.setFamilyWater(water);
        }
        return page;
    }

//    @Override
//    public FamilyBasePage getFamilyById(String id) {
//        FamilyBase familyBase = new FamilyBase();
//        familyBase.setId(id);
//        return familyBaseMapper.queryFamilyBy(familyBase);
//    }

    @Override
    public FamilyBase saveFamilyBase(FamilyBase familyBase) throws JeecgBootException {
        //获取机构完整信息
//        String[] orgArr = getParentOrg(familyBase.getOrgCode());
//        familyBase.setAturalName(orgArr[1]);
//        familyBase.setAturalCode(orgArr[0]);
        if(StringUtils.isNotEmpty(familyBase.getId()) ){
            //修改
            familyBaseMapper.updateById(familyBase);
        }else {
            //新增
//            String familyNumber = SerialNumberID.nextNO();
//            familyBase.setFamilyNumber(familyNumber);
            familyBase.setDelFlag(CommonConstant.DEL_FLAG_0);
            familyBase.setTotalPerson(1);
            familyBase.setTotalInSchool(0);
            familyBase.setTotalWorker(0);
            familyBaseMapper.insert(familyBase);
        }

        /*if (StringUtils.isNotBlank(familyBase.getS2())){
            FamilyMember member = memberMapper.selectById(familyBase.getS2());
            member.setFamilyNumber(familyBase.getFamilyNumber());
            memberMapper.updateById(member);
        }*/
        return familyBase;
    }

    private String[] getParentOrg(String orgCode){
        SysOrg org = sysOrgService.queryByCode(orgCode);
        String orgNameBuffer = "";
        String orgCodeBuffer = "";
        while (org != null){
            orgCodeBuffer = org.getCode() +"/"+ orgCodeBuffer;
            orgNameBuffer = org.getName() +"/"+ orgNameBuffer;
            org = sysOrgService.queryByCode(org.getPcode());
        }
        String[] result = new String[2];
        result[0] = StringUtils.endsWith(orgCodeBuffer,"/") ?
                orgCodeBuffer.substring(0, orgCodeBuffer.length() - 1) : orgCodeBuffer;
        result[1] = StringUtils.endsWith(orgNameBuffer,"/") ?
                orgNameBuffer.substring(0, orgNameBuffer.length() - 1) : orgNameBuffer;
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveMain(FamilyBasePage familyPage) throws JeecgBootException{

        if(familyPage.getHasBase()){
            saveFamilyBase(familyPage.getFamilyBase());
        }

        if (familyPage.getHasHouse()) {
            //判断新增还是修改
            familyPage.getFamilyHouse().setFamilyNumber(familyPage.getFamilyBase().getFamilyNumber());
            familyPage.getFamilyHouse().setIdCard(familyPage.getFamilyBase().getIdCard());
            familyPage.getFamilyHouse().setName(familyPage.getFamilyBase().getName());
            familyPage.getFamilyHouse().setYear(familyPage.getYear());
            if(StringUtils.isEmpty(familyPage.getFamilyHouse().getId())){
                familyPage.getFamilyHouse().setDelFlag(CommonConstant.DEL_FLAG_0);
                //新增 并添加新增日志
                familyBaseHouseMapper.insert(familyPage.getFamilyHouse());
            }else {
                familyBaseHouseMapper.updateById(familyPage.getFamilyHouse());
            }

        }

        if (familyPage.getHasWater()) {
            familyPage.getFamilyWater().setFamilyNumber(familyPage.getFamilyBase().getFamilyNumber());
            familyPage.getFamilyWater().setIdCard(familyPage.getFamilyBase().getIdCard());
            familyPage.getFamilyWater().setName(familyPage.getFamilyBase().getName());
            familyPage.getFamilyWater().setYear(familyPage.getYear());
            if(StringUtils.isEmpty(familyPage.getFamilyWater().getId())){
                familyPage.getFamilyWater().setDelFlag(CommonConstant.DEL_FLAG_0);
                familyBaseWaterMapper.insert(familyPage.getFamilyWater());
            } else {
                familyBaseWaterMapper.updateById(familyPage.getFamilyWater());
            }
        }
        if (familyPage.getHasProduction()) {
            familyPage.getFamilyProduction().setFamilyNumber(familyPage.getFamilyBase().getFamilyNumber());
            familyPage.getFamilyProduction().setIdCard(familyPage.getFamilyBase().getIdCard());
            familyPage.getFamilyProduction().setName(familyPage.getFamilyBase().getName());
            familyPage.getFamilyProduction().setYear(familyPage.getYear());
            if(StringUtils.isEmpty(familyPage.getFamilyProduction().getId())){
                familyPage.getFamilyProduction().setDelFlag(CommonConstant.DEL_FLAG_0);
                familyBaseProductionMapper.insert(familyPage.getFamilyProduction());
            } else {
                familyBaseProductionMapper.updateById(familyPage.getFamilyProduction());
            }

        }
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateMain(FamilyBasePage familyBasePage) {
//        familyBaseMapper.updateById(familyBase);

        //1.先删除子表数据
        //1.先删除子表数据
//        familyBaseHouseMapper.deleteByMainId(familyBase.getFamilyNumber());
//        familyBaseWaterMapper.deleteByMainId(familyBase.getFamilyNumber());
//        familyBaseProductionMapper.deleteByMainId(familyBase.getFamilyNumber());
//        //2.子表数据重新插入
//        if (familyBaseHouse != null) {
//            familyBaseHouse.setFamilyNumber(familyBase.getFamilyNumber());
//            familyBaseHouseMapper.insert(familyBaseHouse);
//        }
//
//        if (familyBaseWater != null) {
//            familyBaseWater.setFamilyNumber(familyBase.getFamilyNumber());
//            familyBaseWaterMapper.insert(familyBaseWater);
//        }
//
//        if (familyBaseProduction != null) {
//            familyBaseProduction.setFamilyNumber(familyBase.getFamilyNumber());
//            familyBaseProductionMapper.insert(familyBaseProduction);
//        }
    }


    @Override
    public void updateCopyMain(FamilyBasePage familyBasePage) {

//        familyBaseMapper.updateById(familyBase);
//
//        // 先查询子表数据库
//        FamilyBaseHouse _familyBaseHouse = familyBaseHouseMapper.selectById(familyBaseHouse.getId());
//        if(_familyBaseHouse == null){
//            // 当传过来的id数据库不存在时，说明数据库没有，走新增逻辑
//            familyBaseHouse.setFamilyNumber(familyBase.getFamilyNumber());
//            familyBaseHouseMapper.insert(familyBaseHouse);
//        }else {
//            BeanUtils.copyProperties(familyBaseHouse,_familyBaseHouse);
//            familyBaseHouseMapper.updateById(_familyBaseHouse);
//        }
//
//         // 先查询子表数据库
//        FamilyBaseWater _familyBaseWater = familyBaseWaterMapper.selectById(familyBaseWater.getId());
//        if(_familyBaseWater == null){
//            // 当传过来的id数据库不存在时，说明数据库没有，走新增逻辑
//            familyBaseWater.setFamilyNumber(familyBase.getFamilyNumber());
//            familyBaseWaterMapper.insert(familyBaseWater);
//        }else {
//            BeanUtils.copyProperties(familyBaseWater,_familyBaseWater);
//            familyBaseWaterMapper.updateById(_familyBaseWater);
//        }
//
//
//         // 先查询子表数据库
//        FamilyBaseProduction _familyBaseProduction = familyBaseProductionMapper.selectById(familyBaseProduction.getId());
//        if(_familyBaseProduction == null){
//            // 当传过来的id数据库不存在时，说明数据库没有，走新增逻辑
//            familyBaseProduction.setFamilyNumber(familyBase.getFamilyNumber());
//            familyBaseProductionMapper.insert(familyBaseProduction);
//        }else {
//            BeanUtils.copyProperties(familyBaseProduction,_familyBaseProduction);
//            familyBaseProductionMapper.updateById(_familyBaseProduction);
//        }

        // 循环前台传过来的数据
//        for (FamilyBaseHouse house:familyBaseHouseList){
//            // 先查询子表数据库
//            FamilyBaseHouse familyBaseHouse = familyBaseHouseMapper.selectById(house.getId());
//            if(familyBaseHouse == null){
//                // 当传过来的id数据库不存在时，说明数据库没有，走新增逻辑
//                familyBaseHouse.setFamilyNumber(familyBase.getFamilyNumber());
//                familyBaseHouseMapper.insert(familyBaseHouse);
//                break;
//            }
//            if(familyBaseHouse.getId().equals(house.getId())){
//                // 传过来的id和数据库id一至时，说明数据库存在该数据，走更新逻辑
//                familyBaseHouseMapper.updateById(house);
//            }
//        }
//
//        for (FamilyBaseWater water:familyBaseWaterList){
//            // 先查询子表数据库
//            FamilyBaseWater familyBaseWater = familyBaseWaterMapper.selectById(water.getId());
//            if(familyBaseWater == null){
//                // 当传过来的id数据库不存在时，说明数据库没有，走新增逻辑
//                water.setFamilyNumber(familyBase.getFamilyNumber());
//                familyBaseWaterMapper.insert(water);
//                break;
//            }
//            if(familyBaseWater.getId().equals(water.getId())){
//                //TODO 传过来的id和数据库id一至时，说明数据库存在该数据，走更新逻辑
//                familyBaseWaterMapper.updateById(water);
//            }
//        }
//
//        for (FamilyBaseProduction production:familyBaseProductionList){
//            // 先查询子表数据库
//            FamilyBaseProduction familyBaseProduction = familyBaseProductionMapper.selectById(production.getId());
//            if(familyBaseProduction == null){
//                // 当传过来的id数据库不存在时，说明数据库没有，走新增逻辑
//                production.setFamilyNumber(familyBase.getFamilyNumber());
//                familyBaseProductionMapper.insert(production);
//                break;
//            }
//            if(familyBaseProduction.getId().equals(production.getId())){
//                //TODO 传过来的id和数据库id一至时，说明数据库存在该数据，走更新逻辑
//                familyBaseProductionMapper.updateById(production);
//            }
//        }
//
//
//        // 当跟新和删除之后取差集， 当传过来的id不存在，而数据库存在时，说明已删除，走删除逻辑
//        List<FamilyBaseHouse> houseList = familyBaseHouseMapper.selectHouseByMainId(familyBase.getFamilyNumber());
//
//        List<FamilyBaseHouse> houseCollect = houseList.stream()
//                .filter(item -> !familyBaseHouseList.stream()
//                        .map(e -> e.getId())
//                        .collect(Collectors.toList())
//                        .contains(item.getId()))
//                .collect(Collectors.toList());
//        // for循环删除id
//        for (FamilyBaseHouse c :houseCollect){
//            familyBaseHouseMapper.deleteById(c.getId());
//        }
//
//        List<FamilyBaseWater> waterList = familyBaseWaterMapper.selectWaterByMainId(familyBase.getFamilyNumber());
//        List<FamilyBaseWater> waterCollect = waterList.stream()
//                .filter(item -> !familyBaseWaterList.stream()
//                        .map(e -> e.getId())
//                        .collect(Collectors.toList())
//                        .contains(item.getId()))
//                .collect(Collectors.toList());
//        //TODO for循环删除id
//        for (FamilyBaseWater c:waterCollect){
//            familyBaseWaterMapper.deleteById(c.getId());
//        }
//
//        List<FamilyBaseProduction> productionList = familyBaseProductionMapper.selectProductionByMainId(familyBase.getFamilyNumber());
//        List<FamilyBaseProduction> productionCollect = productionList.stream()
//                .filter(item -> !familyBaseProductionList.stream()
//                        .map(e -> e.getId())
//                        .collect(Collectors.toList())
//                        .contains(item.getId()))
//                .collect(Collectors.toList());
//        //TODO for循环删除id
//        for (FamilyBaseProduction c:productionCollect){
//            familyBaseProductionMapper.deleteById(c.getId());
//        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delMain(String id) {
        FamilyBase base = getById(id);
        familyBaseMapper.deleteById(id);
        familyBaseHouseMapper.deleteByMainId(base.getFamilyNumber());
        familyBaseWaterMapper.deleteByMainId(base.getFamilyNumber());
        familyBaseProductionMapper.deleteByMainId(base.getFamilyNumber());
        integralMapper.deleteByMainId(base.getFamilyNumber());
        integralRecordMapper.deleteByMainId(base.getFamilyNumber());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delBatchMain(Collection<? extends Serializable> idList) {
        for(Serializable id:idList) {
            FamilyBase base = getById(id);
            familyBaseMapper.deleteById(id);
            familyBaseHouseMapper.deleteByMainId(base.getFamilyNumber());
            familyBaseWaterMapper.deleteByMainId(base.getFamilyNumber());
            familyBaseProductionMapper.deleteByMainId(base.getFamilyNumber());
        }
    }

    @Override
    public FamilyBaseResult getFamilyBaseById(String id) {

        FamilyBaseResult result = new FamilyBaseResult();
        FamilyBase base = baseMapper.selectById(id);
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("add_id", base.getAddId());
        // 家庭地址-->对应实景三维地图上的坐标地址
        Address address = addressMapper.selectOne(wrapper);
        base.setMapAddress(address);
        result.setFamilyBase(base);

        wrapper.clear();
        wrapper.eq("family_number", base.getFamilyNumber());
        wrapper.orderByDesc("year");
        wrapper.last("limit 1");
        // 房屋信息
        FamilyBaseHouse house = familyBaseHouseMapper.selectOne(wrapper);
        result.setHouse(Objects.nonNull(house) ? house : new FamilyBaseHouse());

        // 饮水信息
        FamilyBaseWater water = familyBaseWaterMapper.selectOne(wrapper);
        result.setWater(Objects.nonNull(water) ? water : new FamilyBaseWater());

        // 产业信息
        FamilyBaseProduction production = familyBaseProductionMapper.selectOne(wrapper);
        result.setProduction(Objects.nonNull(production) ? production : new FamilyBaseProduction());

        // 家庭成员
        List<FamilyMember> members = memberMapper.selectByGroupByIdCard(base.getFamilyNumber());
        result.setMembers(members);
        return result;
    }

    @Override
    public ModelAndView detailExportXls(HttpServletRequest request, FamilyBase familyBase) {

        // Step.1 组装查询条件
		QueryWrapper<FamilyBase> queryWrapper = QueryGenerator.initQueryWrapper(familyBase, request.getParameterMap());
		//Step.2 AutoPoi 导出Excel
		ModelAndView modelView = new ModelAndView(new JeecgEntityExcelView());
		//获取当前用户
		LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		List<FamilyExport> pageList = new ArrayList<>();

        // 过滤选中数据
        String selections = request.getParameter("selections");
        if (oConvertUtils.isNotEmpty(selections)) {
            List<String> selectionList = Arrays.asList(selections.split(","));
            queryWrapper.lambda().in(FamilyBase::getId, selectionList);
        }
		List<FamilyBase> familyBaseList = familyBaseMapper.selectList(queryWrapper);
		List<String> numbers = familyBaseList.stream().map(FamilyBase::getFamilyNumber).collect(Collectors.toList());
		int year = Calendar.getInstance().get(Calendar.YEAR);// 只导出当年数据
		QueryWrapper wrapper = new QueryWrapper();
		wrapper.in("family_number", numbers);
		wrapper.eq("year", year);
		List<FamilyBaseHouse> houseList = familyBaseHouseMapper.selectList(wrapper);// 查询住房信息
		List<FamilyBaseWater> waterList = familyBaseWaterMapper.selectList(wrapper);// 查询饮水信息
		List<FamilyBaseProduction> productionList = familyBaseProductionMapper.selectList(wrapper);// 查询产业信息
		for (FamilyBase base : familyBaseList) {
            FamilyExport export = new FamilyExport();
			export.getFamilyBaseList().add(base);

			if (CollectionUtils.isNotEmpty(houseList)){
				FamilyBaseHouse house = houseList.stream().filter(h -> {
					return StringUtils.equals(h.getFamilyNumber(), base.getFamilyNumber());
				}).findFirst().orElse(null);
				if (null != house){
				    export.getHouseList().add(house);
                }
			}

			if (CollectionUtils.isNotEmpty(waterList)){
				FamilyBaseWater water = waterList.stream().filter(h -> {
					return StringUtils.equals(h.getFamilyNumber(), base.getFamilyNumber());
				}).findFirst().orElse(null);
				if (null != water){
				    export.getWaterList().add(water);
                }
			}

			if (CollectionUtils.isNotEmpty(productionList)){
				FamilyBaseProduction production = productionList.stream().filter(h -> {
					return StringUtils.equals(h.getFamilyNumber(), base.getFamilyNumber());
				}).findFirst().orElse(null);
				if (null != production){
				    export.getProductionList().add(production);
                }
			}

			pageList.add(export);
		}

        // 导出文件名称
		modelView.addObject(NormalExcelConstants.FILE_NAME, "家庭户基本信息");
		// 注解对象Class
		modelView.addObject(NormalExcelConstants.CLASS, FamilyExport.class);
		// 自定义表格参数
		modelView.addObject(NormalExcelConstants.PARAMS, new ExportParams("家庭户基本信息导出", "导出人:" + sysUser.getRealname(),
				"家庭户基本信息"));
		// 导出数据列表
		modelView.addObject(NormalExcelConstants.DATA_LIST, pageList);

        return modelView;
    }

    @Override
    public List<VillageRanking> selectIndexChartStatics() {
        return baseMapper.selectIndexChartStatics();
    }

    @Override
    public List<VillageRanking> selectIndexVillageRanking() {
        return baseMapper.selectIndexVillageRanking();
    }
}
