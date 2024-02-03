package com.qfy.modules.family.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qfy.modules.city3d.po.Address;
import com.qfy.modules.city3d.service.AddressService;
import com.qfy.modules.family.entity.FamilyBaseProduction;
import com.qfy.modules.family.mapper.FamilyBaseProductionMapper;
import com.qfy.modules.family.mapper.FamilyMemberMapper;
import com.qfy.modules.family.service.IFamilyBaseProductionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.query.QueryGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

 /**
 * @Description: 产业扶持
 * @Author: jeecg-boot
 * @Date:   2022-11-19
 * @Version: V1.0
 */
@Api(tags="产业扶持")
@RestController
@RequestMapping("/family/production")
@Slf4j
public class FamilyBaseProductionController extends JeecgController<FamilyBaseProduction, IFamilyBaseProductionService> {
	@Autowired
	private IFamilyBaseProductionService familyBaseProductionService;

	 @Autowired
	 private FamilyBaseProductionMapper FamilyBaseProductionMapper;

	 @Resource
	 private AddressService addressService;

	/**
	 * 分页列表查询
	 *
	 * @param familyBaseProduction
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "产业扶持-分页列表查询")
	@ApiOperation(value="产业扶持-分页列表查询", notes="产业扶持-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<FamilyBaseProduction>> queryPageList(FamilyBaseProduction familyBaseProduction,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<FamilyBaseProduction> queryWrapper = QueryGenerator.initQueryWrapper(familyBaseProduction, req.getParameterMap());
		Page<FamilyBaseProduction> page = new Page<FamilyBaseProduction>(pageNo, pageSize);
		IPage<FamilyBaseProduction> pageList = familyBaseProductionService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	/**
	 *   添加
	 *
	 * @param familyBaseProduction
	 * @return
	 */
	@AutoLog(value = "产业扶持-添加")
	@ApiOperation(value="产业扶持-添加", notes="产业扶持-添加")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody FamilyBaseProduction familyBaseProduction) {
		familyBaseProductionService.save(familyBaseProduction);
		return Result.OK("添加成功！");
	}

	/**
	 *  编辑
	 *
	 * @param familyBaseProduction
	 * @return
	 */
	@AutoLog(value = "产业扶持-编辑")
	@ApiOperation(value="产业扶持-编辑", notes="产业扶持-编辑")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody FamilyBaseProduction familyBaseProduction) {
		familyBaseProductionService.updateById(familyBaseProduction);
		return Result.OK("编辑成功!");
	}

	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "产业扶持-通过id删除")
	@ApiOperation(value="产业扶持-通过id删除", notes="产业扶持-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		familyBaseProductionService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "产业扶持-批量删除")
	@ApiOperation(value="产业扶持-批量删除", notes="产业扶持-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.familyBaseProductionService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "产业扶持-通过id查询")
	@ApiOperation(value="产业扶持-通过id查询", notes="产业扶持-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<FamilyBaseProduction> queryById(@RequestParam(name="id",required=true) String id) {
		FamilyBaseProduction familyBaseProduction = familyBaseProductionService.getById(id);
		if(familyBaseProduction==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(familyBaseProduction);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param familyBaseProduction
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, FamilyBaseProduction familyBaseProduction) {
        return super.exportXls(request, familyBaseProduction, FamilyBaseProduction.class, "产业扶持");
    }

	 @RequestMapping(value = "/example/exportXls")
	 public ModelAndView exampleExportXls(HttpServletRequest request, FamilyBaseProduction production) {
		 List<FamilyBaseProduction> productionList = new ArrayList<>(1);
		 production.setFamilyNumber("100xxx");
		 production.setYear("2022");
		 production.setCooperative("嘉豪公司");
		 production.setDryLandPlantArea(20d);
		 production.setEcologicalForestArea(70d);
		 production.setFruitArea(10d);
		 production.setGengdArea(20d);
		 production.setIdCard("451228xxxxx");
		 production.setLindArea(100d);
		 production.setLoanAmount(7500d);
		 production.setLoanType(1);
		 production.setName("张三");
		 production.setPaddyArea(15d);
		 production.setPaddyPlantArea(15d);
		 production.setTeaOilArea(150d);
		 production.setTotalCattle(5);
		 production.setTotalChicken(10);
		 production.setTotalOther("");
		 production.setTotalPig(3);
		 production.setTotalSheep(0);
		 productionList.add(production);
		 return super.exampleExportXls(productionList, FamilyBaseProduction.class, "产业扶持");
	 }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, FamilyBaseProduction.class);
    }

	 /**
	  * 查询鸡鸭牛猪及其他的数量
	  * @param familyBaseProduction
	  * @param pageNo
	  * @param pageSize
	  * @param req
	  * @return
	  */
	 //@AutoLog(value = "产业、就业--查询鸡鸭牛猪及其他的数量")
	 @ApiOperation(value="产业、就业--查询鸡鸭牛猪及其他的数量", notes="产业、就业--查询鸡鸭牛猪及其他的数量")
	 @GetMapping(value = "/IndustryCount")
	 public Result<FamilyBaseProduction> IndustryCount(FamilyBaseProduction familyBaseProduction,
													   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
													   @RequestParam(name="pageSize", defaultValue="20") Integer pageSize,
													   HttpServletRequest req) {
		 QueryWrapper<FamilyBaseProduction> queryWrapper = QueryGenerator.initQueryWrapper(familyBaseProduction, req.getParameterMap());
		 FamilyBaseProduction total = FamilyBaseProductionMapper.queryIndustryCount();
		 return Result.OK(total);
	 }

	 /**
	  * 根据条件查询产业+就业及base表部分数据
	  * @param familyBaseProduction
	  * @param pageNo
	  * @param pageSize
	  * @param req
	  * @return
	  */
	 //@AutoLog(value = "根据条件查询产业+就业及base表部分数据")
	 @ApiOperation(value="根据条件查询产业+就业及base表部分数据", notes="根据条件查询产业+就业及base表部分数据")
	 @GetMapping(value = "/IndustryAndBaselist")
	 public Result<List<FamilyBaseProduction>> queryIndustryAndBaselist(FamilyBaseProduction familyBaseProduction,
																 @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
																 @RequestParam(name="pageSize", defaultValue="100") Integer pageSize,
																 HttpServletRequest req) {
		 QueryWrapper<FamilyBaseProduction> queryWrapper = QueryGenerator.initQueryWrapper(familyBaseProduction, req.getParameterMap());
		 Page<FamilyBaseProduction> page = new Page<FamilyBaseProduction>(pageNo, pageSize);
		 //家畜(牛，羊，猪，其他)
		 String industryParam = req.getParameter("industryParam");
		 //人员姓名
		 String memberName = req.getParameter("memberName");
		 //人员身份证
		 String memberIdCard = req.getParameter("memberIdCard");
		 //人员姓名，因为时联表查询，name有多个，所以要标明表别名
		 if(StringUtils.isNotBlank(memberName)){
			 queryWrapper.and(wrapper ->wrapper.like("m.name", memberName));
		 }
		 //人员身份证，因为时联表查询，idCard有多个，所以要标明表别名
		 if(StringUtils.isNotBlank(memberIdCard)){
			 queryWrapper.and(wrapper ->wrapper.like("m.id_card", memberIdCard));
		 }
		 if(StringUtils.isNotBlank(industryParam)){
			 //牛
			 if(("cattle").equals(industryParam)){
				 queryWrapper.isNotNull("total_cattle");
			 }
			 //羊
			 if(("sheep").equals(industryParam)){
				 queryWrapper.isNotNull("total_sheep");
			 }
			 //猪
			 if(("pig").equals(industryParam)){
				 queryWrapper.isNotNull("total_pig");
			 }
			 //其他
			 if(("other").equals(industryParam)){
				 queryWrapper.isNotNull("total_other");
			 }
		 }
		 List<FamilyBaseProduction> produceList = FamilyBaseProductionMapper.productionAndAddresslist(queryWrapper);
		 if (CollectionUtils.isNotEmpty(produceList)){
			 List<String> idList = produceList.stream().map(FamilyBaseProduction::getAddId).collect(Collectors.toList());
			 List<String> ids = idList.stream().filter(id -> {
				 return StringUtils.isNotBlank(id);
			 }).collect(Collectors.toList());
			 //为0的话后面的程序会报错
			 if(ids.size()<=0){
				 return Result.OK(produceList);
			 }
			 QueryWrapper<Address> wrapper = new QueryWrapper<>();
			 wrapper.lambda().in(Address::getAddId, ids);
			 List<Address> addressList = addressService.list(wrapper);
			 if (CollectionUtils.isNotEmpty(addressList)){
				 for (FamilyBaseProduction water : produceList) {
					 for (Address address : addressList) {
						 if (StringUtils.equals(address.getAddId(), water.getAddId())){
							 water.setMapAddress(address);
						 }
					 }
				 }
			 }
		 }
		 return Result.OK(produceList);
	 }


}
