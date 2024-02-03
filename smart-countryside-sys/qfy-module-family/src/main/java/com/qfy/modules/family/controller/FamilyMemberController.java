package com.qfy.modules.family.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qfy.modules.city3d.po.Address;
import com.qfy.modules.city3d.service.AddressService;
import com.qfy.modules.commons.entity.SystemUser;
import com.qfy.modules.commons.service.ISystemUserService;
import com.qfy.modules.family.entity.FamilyBaseWater;
import com.qfy.modules.family.entity.FamilyMember;
import com.qfy.modules.family.entity.FamilyMemberTemp;
import com.qfy.modules.family.mapper.FamilyMemberMapper;
import com.qfy.modules.family.model.FamilyBaseVo;
import com.qfy.modules.family.service.IFamilyMemberService;
import com.qfy.modules.family.service.IFamilyMemberTempService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.aspect.annotation.PermissionData;
import org.jeecg.common.constant.CommonConstant;
import org.jeecg.common.system.api.ISysBaseAPI;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.common.system.vo.SysPermissionDataRuleModel;
import org.jeecg.common.util.DateUtils;
import org.jeecg.common.util.PasswordUtil;
import org.jeecg.common.util.oConvertUtils;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Description: 家庭成员管理
 * @Author: jeecg-boot
 * @Date:   2022-06-02
 * @Version: V1.0
 */
@Api(tags="家庭成员管理")
@RestController
@RequestMapping("/family/familyMember")
@Slf4j
public class FamilyMemberController extends JeecgController<FamilyMember, IFamilyMemberService> {

	public static final Logger logger = LoggerFactory.getLogger(FamilyMemberController.class);

	@Resource
	private IFamilyMemberService familyMemberService;
	@Resource
	private ISysBaseAPI sysBaseAPI;
	@Resource
	private FamilyMemberMapper familyMemberMapper;
	@Resource
	private AddressService addressService;
	@Resource
	private IFamilyMemberTempService memberTempService;
	@Resource
	private ISystemUserService systemUserService;

	/**
	 * 分页列表查询
	 *
	 * @param familyMember
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "家庭成员管理-分页列表查询")
	@ApiOperation(value = "家庭成员管理-分页列表查询", notes = "家庭成员管理-分页列表查询")
	@GetMapping(value = "/list")
	@PermissionData( pageComponent = "family/family/FamilyMemberList")
	public Result<IPage<FamilyMember>> queryPageList(FamilyMember familyMember,
													 @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
													 @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
													 HttpServletRequest req) {
//		QueryWrapper<FamilyMember> queryWrapper = QueryGenerator.initQueryWrapper(familyMember, req.getParameterMap());

		QueryWrapper<FamilyMember> wrapper = new QueryWrapper<>();
		wrapper.apply("a.family_number = b.family_number");
		String familyNumber = req.getParameter("familyNumber");
		String name = req.getParameter("name");
		String idCard = req.getParameter("idCard");
		String year = req.getParameter("year");
		String orgCode = req.getParameter("orgCode");
		String familyRel = req.getParameter("familyRel");

		// 默认数据年份为当年
		if (StringUtils.isBlank(year)){
			year = DateUtils.formatDate("yyyy");
		}
		if (StringUtils.isNotBlank(familyNumber)){
			wrapper.like("a.family_number", familyNumber);
		}
		if (StringUtils.isNotBlank(name)){
			wrapper.like("a.name", StringUtils.endsWith(name,",")? name.substring(0, name.length()-1) : name);
		}
		if (StringUtils.isNotBlank(idCard)){
			wrapper.like("a.id_card", StringUtils.endsWith(idCard,",") ? idCard.substring(0, idCard.length()-1) : idCard);
		}
		if (StringUtils.isNotBlank(orgCode)){
			wrapper.or(w -> {
				w.eq("b.org_code", orgCode);
				w.or();
				w.eq("b.atural_code", orgCode);
				w.or();
				w.eq("b.s1", orgCode);
			});
		}
		wrapper.eq("a.year", year);
		if (StringUtils.isNotBlank(familyRel)){
			wrapper.eq("a.family_rel", familyRel);
		}

		Map<String, SysPermissionDataRuleModel> ruleMap = QueryGenerator.getRuleMap();
		//权限规则自定义SQL表达式
		for (String c : ruleMap.keySet()) {
			if(oConvertUtils.isNotEmpty(c) && c.startsWith(QueryGenerator.SQL_RULES_COLUMN)){
				wrapper.and(i ->i.apply(QueryGenerator.getSqlRuleValue(ruleMap.get(c).getRuleValue())));
			}
		}

		Page<FamilyMember> page = new Page<>(pageNo, pageSize);
		IPage<FamilyMember> pageList = familyMemberService.page(page, wrapper);
		return Result.OK(pageList);
	}

	/**
	 * 添加
	 *
	 * @param familyBaseVo
	 * @return
	 */
	@AutoLog(value = "qfy_family_base-添加")
	@ApiOperation(value = "qfy_family_base-添加", notes = "qfy_family_base-添加")
	@RequestMapping(value = "/addMain", method = {RequestMethod.PUT, RequestMethod.POST})
	public Result<String> add(@RequestBody FamilyBaseVo familyBaseVo) {
//		 FamilyBase familyBase = new FamilyBase();
//		 BeanUtils.copyProperties(familyBaseVo, familyBase);
		familyMemberService.saveMain(familyBaseVo, familyBaseVo.getFamilyMemberList());
		return Result.OK("添加成功！");
	}

//	 /**
//	  *  编辑
//	  * @param familyBaseVo
//	  * @return
//	  */
//	 @AutoLog(value = "qfy_family_base-编辑")
//	 @ApiOperation(value="qfy_family_base-编辑", notes="qfy_family_base-编辑")
//	 @RequestMapping(value = "/updateMain", method = {RequestMethod.PUT,RequestMethod.POST})
//	 public Result<String> updateMain(@RequestBody FamilyBaseVo familyBaseVo) {
//		 FamilyBase familyBase = new FamilyBase();
//		 BeanUtils.copyProperties(familyBaseVo, familyBase);
////		 FamilyBase familyBaseEntity = familyMemberService.getById(familyBase.getId());
////		 if(familyBaseEntity==null) {
////			 return Result.error("未找到对应数据");
////		 }
//		 familyMemberService.updateMain(familyBase, familyBaseVo.getFamilyMemberList());
//		 return Result.OK("编辑成功!");
//	 }

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "家庭成员管理通过主表ID查询")
	@ApiOperation(value = "家庭成员管理主表ID查询", notes = "家庭成员管理-通主表ID查询")
	@GetMapping(value = "/queryFamilyMemberByMainId")
	public Result<List<FamilyMember>> queryFamilyMemberListByMainId(@RequestParam(name = "id") String id,
																	@RequestParam(name = "newest", required = false) boolean newest,
																	@RequestParam(name = "year", required = false) String year) {
		QueryWrapper<FamilyMember> wrapper = new QueryWrapper<>();
		wrapper.lambda().eq(FamilyMember::getFamilyNumber, id);
		wrapper.lambda().eq(FamilyMember::getDelFlag, CommonConstant.DEL_FLAG_0);
		List<FamilyMember> familyMemberList;
		if (newest) {
			year = String.valueOf(Calendar.getInstance(Locale.CHINA).get(Calendar.YEAR));
		}
		wrapper.lambda().eq(FamilyMember::getYear, year);
		familyMemberList = familyMemberService.list(wrapper);
		return Result.OK(familyMemberList);
	}

	/**
	 * 添加
	 *
	 * @param familyMember
	 * @return
	 */
	@AutoLog(value = "家庭成员管理-添加")
	@ApiOperation(value = "家庭成员管理-添加", notes = "家庭成员管理-添加")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody FamilyMember familyMember) {
		familyMemberService.save(familyMember);
		return Result.OK("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param familyMember
	 * @return
	 */
	@AutoLog(value = "家庭成员管理-编辑")
	@ApiOperation(value = "家庭成员管理-编辑", notes = "家庭成员管理-编辑")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT, RequestMethod.POST})
	public Result<String> edit(@RequestBody FamilyMember familyMember) {
		familyMemberService.updateById(familyMember);
		return Result.OK("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "家庭成员管理-通过id删除")
	@ApiOperation(value = "家庭成员管理-通过id删除", notes = "家庭成员管理-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name = "id", required = true) String id) {
		familyMemberService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "家庭成员管理-批量删除")
	@ApiOperation(value = "家庭成员管理-批量删除", notes = "家庭成员管理-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
		this.familyMemberService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "家庭成员管理-通过id查询")
	@ApiOperation(value = "家庭成员管理-通过id查询", notes = "家庭成员管理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<FamilyMember> queryById(@RequestParam(name = "id", required = true) String id) {
		FamilyMember familyMember = familyMemberService.getById(id);
		if (familyMember == null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(familyMember);

	}


	/**
	 * 导出excel
	 *
	 * @param request
	 * @param familyMember
	 */
	@ApiOperation(value = "导出家庭成员信息", notes = "导出家庭成员信息")
	@RequestMapping(value = "/exportXls")
	@AutoLog(value = "导出家庭成员信息",operateType = 6)
	@PermissionData
	public ModelAndView exportXls(HttpServletRequest request, FamilyMember familyMember) {
		//获取当前登录人
		LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		//权限配置列导出示例
		List<String> noAuthList = new ArrayList<>();
		List<String> exportFieldsList = new ArrayList<>();
		//1.此前缀必须与列表字段权限控制前缀一致
		String permsPrefix = "familyMember:export:";
		List<String> allAuth = sysBaseAPI.queryPermissionsByPrefix(permsPrefix);
		//查询已授权字段
		Set<String> userAuth = sysBaseAPI.queryUserAuths(sysUser.getUsername());
		//查询已授权字段
		for (String perms : allAuth) {
			if (!userAuth.contains(perms)) {
				noAuthList.add(perms.substring(permsPrefix.length()));
			}
		}
		//实体类中字段与未授权字段比较，列出需导出字段
		Field[] fileds = FamilyMember.class.getDeclaredFields();
		List<Field> list = new ArrayList(Arrays.asList(fileds));
		for (Field field : list) {
			if (!noAuthList.contains(field.getName())) {
				exportFieldsList.add(field.getName());
			}
		}
		String exportFields = CollectionUtils.isNotEmpty(exportFieldsList) ? String.join(",", exportFieldsList) : "";

		QueryWrapper<FamilyMember> wrapper = QueryGenerator.initQueryWrapper(familyMember,request.getParameterMap());

		// 此处扩展更多查询条件或者其它处理逻辑
		String selections = request.getParameter("selections");
		// 如果指定导出数据,则缩小查询范围
		if (StringUtils.isNotBlank(selections)){
			wrapper.and(w -> {
				w.lambda().in(FamilyMember::getId,Arrays.asList(selections.split(",")));
				w.or();
				w.lambda().in(FamilyMember::getFamilyNumber,Arrays.asList(selections.split(",")));
			});
		}

		// Step.2 获取导出数据
		List<FamilyMember> pageList = service.list(wrapper);

		// Step.3 AutoPoi 导出Excel
		ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		//此处设置的filename无效 ,前端会重更新设置一下
		mv.addObject(NormalExcelConstants.FILE_NAME, "家庭成员信息报表");
		mv.addObject(NormalExcelConstants.CLASS, FamilyMember.class);
		ExportParams  exportParams=new ExportParams("家庭成员信息报表", "导出人:" + sysUser.getRealname(), "家庭成员信息报表");
		mv.addObject(NormalExcelConstants.PARAMS,exportParams);
		mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
		mv.addObject(NormalExcelConstants.EXPORT_FIELDS,exportFields);
		return mv;
	}

	/**
	 * 导出示例数据模板
	 *
	 * @param member
	 */
	@ApiOperation(value = "导出家庭成员信息模板", notes = "导出家庭成员信息模板")
	@AutoLog(value = "导出家庭成员信息模板",operateType = 6)
	@RequestMapping(value = "/example/exportXls")
	public ModelAndView exampleExportXls(FamilyMemberTemp member) {
		List<FamilyMemberTemp> memberList = new ArrayList<>(1);
		member.setCityYanglao(0);
		member.setFamilyRel("1");
		member.setFamilyNumber("100xxx");
		member.setCityYiliao(0);
		member.setDabingYiliao(1);
		member.setDisability("");
		member.setHealth(1);
		member.setIdCard("451228xxxxx");
//		member.setInSchool(0);
		member.setLaborSkills("3");
		member.setMinBaozhengDate("2020-10-10");
		member.setMinBaozhengLevel(3);
		member.setName("张三");
		member.setNation("3");
		member.setSupportDate("2020-10-10");
		member.setSupportMember(1);
		member.setTelephone("150xxxxx");
		member.setTownMinBaozheng(1);
		member.setTownYanglao(1);
		member.setTownYiliao(1);
		member.setWenhuaCc("3");
		member.setWorkingAddress("xx县xx镇");
		member.setWorkingDate("2020-01-15");
		member.setWorkingDepart("XX建筑公司");
		member.setYear("2022");
		member.setZhengzhiMm("2");
		member.setSex("1");
		member.setS1("2000-12-13");
		member.setS2("1");
		member.setS3("");
		member.setS4("2022-01-15");
		member.setS5("");
		member.setS6("2022-01-15");
		member.setS7("");
		member.setS8("20");
		member.setS9("0");
		member.setMarriage(0);
		member.setEnlist(0);
		member.setMoveOut(0);
		member.setFirstJoin(0);
		memberList.add(member);
		LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		// Step.3 AutoPoi 导出Excel
		ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		//此处设置的filename无效 ,前端会重更新设置一下
		mv.addObject(NormalExcelConstants.FILE_NAME, "家庭成员导入模板");
		mv.addObject(NormalExcelConstants.CLASS, FamilyMemberTemp.class);
		ExportParams exportParams=new ExportParams("家庭成员导入模板", "导出人:" + sysUser.getRealname(), "家庭成员导入模板");
		mv.addObject(NormalExcelConstants.PARAMS,exportParams);
		mv.addObject(NormalExcelConstants.DATA_LIST, memberList);
		return mv;
	}

	/**
	 * 通过excel导入数据
	 *
	 * @param request
	 * @param response
	 * @return
	 */
	@ApiOperation(value = "导入家庭成员信息", notes = "导入家庭成员信息")
	@AutoLog(value = "导入家庭成员信息",operateType = 5)
	@RequestMapping(value = "/importExcel", method = RequestMethod.POST)
	public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
		return super.importExcel(request, response, FamilyMember.class);
	}

	@Override
	public List<FamilyMember> beforeImport(List<FamilyMember> list) {
		for (FamilyMember member : list) {
			member.setDelFlag(CommonConstant.DEL_FLAG_0);
		}
		return list;
	}

	/**
	 * 根据条件查询成员及base表部分数据
	 *
	 * @param familyMember
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "根据条件查询成员及base表部分数据")
	@ApiOperation(value = "根据条件查询成员及base表部分数据", notes = "根据条件查询成员及base表部分数据")
	@GetMapping(value = "/memberAndBaselist")
	public Result<IPage<FamilyMember>> queryPageListByParam(FamilyMember familyMember,
															@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
															@RequestParam(name = "pageSize", defaultValue = "100") Integer pageSize,
															HttpServletRequest req) {
		QueryWrapper<FamilyMember> queryWrapper = QueryGenerator.initQueryWrapper(familyMember, req.getParameterMap());
		//在校生情况
		String inSchoolType = req.getParameter("inSchoolType");
		//社会保障(医疗+养老)是否参保
		String healthType = req.getParameter("healthType");
		//是否区外就业
		String workType = req.getParameter("workType");
		//人员姓名
		String memberName = req.getParameter("memberName");
		//人员身份证
		String memberIdCard = req.getParameter("memberIdCard");

		if (StringUtils.isNotBlank(inSchoolType)) {
			//小学
			if (("primarySchool").equals(inSchoolType)) {
				queryWrapper.eq("in_school", 3);
			}
			//中学
			if (("middleSchool").equals(inSchoolType)) {
//				queryWrapper.between("in_school", 4, 9).or(wrapper -> wrapper.between("in_school", 14, 19));
				queryWrapper.and(wrapper -> wrapper.between("in_school", 4, 9).or(wrapper2 -> wrapper2.between("in_school", 14, 19)));
			}
			//大学
			if (("university").equals(inSchoolType)) {
//				queryWrapper.between("in_school", 10, 12).or(wrapper -> wrapper.between("in_school", 20, 25));
				queryWrapper.and(wrapper -> wrapper.between("in_school", 10, 12).or(wrapper2 -> wrapper2.between("in_school", 20, 25)));
			}
		}

		if (StringUtils.isNotBlank(healthType)) {
			//总人数
			if (("memberTotal").equals(healthType)) {

			}
			//医疗未参保人员
			if (("healthUnInsured").equals(healthType)) {
				queryWrapper.eq("town_yiliao", 0).eq("city_yiliao", 0);
			}
			//养老未参保人员
			if (("endowmentUnInsured").equals(healthType)) {
				queryWrapper.eq("town_yanglao", 0).eq("city_yanglao", 0);
			}
			//医疗已参保
			if (("healthInsured").equals(healthType)) {
				//queryWrapper.eq("town_yiliao", 1).or(wrapper -> wrapper.eq("city_yiliao", 1));
				queryWrapper.and(wrapper -> wrapper.eq("town_yiliao", 1).or(wrapper2 -> wrapper2.eq("city_yiliao", 1)));
			}
			//养老已参保
			if (("endowmentInsured").equals(healthType)) {
//				queryWrapper.eq("town_yanglao", 1).or(wrapper -> wrapper.eq("city_yanglao", 1));
				queryWrapper.and(wrapper -> wrapper.eq("town_yanglao", 1).or(wrapper2 -> wrapper2.eq("city_yanglao", 1)));
			}
		}
		//是否区外就业 因为时联表查询，s9有多个，所以要标明表别名
		if(StringUtils.isNotBlank(workType)){
			queryWrapper.and(wrapper ->wrapper.eq("m.s9", Integer.parseInt(workType)));
		}
		//人员姓名，因为时联表查询，name有多个，所以要标明表别名
		if(StringUtils.isNotBlank(memberName)){
			queryWrapper.and(wrapper ->wrapper.like("m.name", memberName));
//			queryWrapper.like("m.name", stuName);
		}
		//人员身份证，因为时联表查询，idCard有多个，所以要标明表别名
		if(StringUtils.isNotBlank(memberIdCard)){
			queryWrapper.and(wrapper ->wrapper.like("m.id_card", memberIdCard));
//			queryWrapper.like("m.id_card", '%'+stuIdCard+'%');
		}
		Page<FamilyMember> page = new Page<FamilyMember>(pageNo, pageSize);
		IPage<FamilyMember> pageList = familyMemberMapper.queryPageListByParam(page, queryWrapper);

		if (CollectionUtils.isNotEmpty(pageList.getRecords())) {
			List<String> idList = pageList.getRecords().stream().map(FamilyMember::getAddId).collect(Collectors.toList());
			List<String> ids = idList.stream().filter(id -> {
				return StringUtils.isNotBlank(id);
			}).collect(Collectors.toList());
			//为0的话后面的程序会报错
			if(ids.size()<=0){
				return Result.OK(pageList);
			}
			QueryWrapper<Address> wrapper = new QueryWrapper<>();
			wrapper.lambda().in(Address::getAddId, ids);
			List<Address> addressList = addressService.list(wrapper);
			if (CollectionUtils.isNotEmpty(addressList)) {
				for (FamilyMember family : pageList.getRecords()) {
					for (Address address : addressList) {
						if (StringUtils.equals(address.getAddId(), family.getAddId())) {
							family.setMapAddress(address);
						}
					}
				}
			}
		}
		return Result.OK(pageList);
	}

	/**
	 * 根据条件查询饮水安全保障及base表部分数据
	 *
	 * @param familyBaseWater
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "根据条件查询饮水安全保障及base表部分数据")
	@ApiOperation(value = "根据条件查询饮水安全保障及base表部分数据", notes = "根据条件查询饮水安全保障及base表部分数据")
	@GetMapping(value = "/waterAndBaselist")
	public Result<IPage<FamilyBaseWater>> waterAndBaselist(FamilyBaseWater familyBaseWater,
														   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
														   @RequestParam(name = "pageSize", defaultValue = "100") Integer pageSize,
														   HttpServletRequest req) {
		QueryWrapper<FamilyBaseWater> queryWrapper = QueryGenerator.initQueryWrapper(familyBaseWater, req.getParameterMap());

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
		Page<FamilyBaseWater> page = new Page<>(pageNo, pageSize);
		IPage<FamilyBaseWater> pageList = familyMemberMapper.queryWaterAndBasePageList(page, queryWrapper);

		if (CollectionUtils.isNotEmpty(pageList.getRecords())) {
			List<String> idList = pageList.getRecords().stream().map(FamilyBaseWater::getAddId).collect(Collectors.toList());
			List<String> ids = idList.stream().filter(id -> {
				return StringUtils.isNotBlank(id);
			}).collect(Collectors.toList());
			QueryWrapper<Address> wrapper = new QueryWrapper<>();
			//为0的话后面的程序会报错
			if(ids.size()<=0){
				return Result.OK(pageList);
			}
			wrapper.lambda().in(Address::getAddId, ids);
			List<Address> addressList = addressService.list(wrapper);
			if (CollectionUtils.isNotEmpty(addressList)) {
				for (FamilyBaseWater water : pageList.getRecords()) {
					for (Address address : addressList) {
						if (StringUtils.equals(address.getAddId(), water.getAddId())) {
							water.setMapAddress(address);
						}
					}
				}
			}
		}
		return Result.OK(pageList);
	}

	/**
	 * 通过excel导入数据到临时表,再批量更新
	 *
	 * @param request
	 * @return
	 */
	@ApiOperation(value = "批量更新家庭成员信息", notes = "批量更新家庭成员信息")
	@AutoLog(value = "批量更新家庭成员信息",operateType = 3)
	@RequestMapping(value = "/batchUpdate", method = RequestMethod.POST)
	public Result<?> batchUpdate(HttpServletRequest request) {

		LoginUser loginUser = (LoginUser)SecurityUtils.getSubject().getPrincipal();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			// 获取上传文件对象
			MultipartFile file = entity.getValue();
			ImportParams params = new ImportParams();
			params.setTitleRows(2);
			params.setHeadRows(1);
			params.setNeedSave(true);
			try {
				List<FamilyMemberTemp> list = ExcelImportUtil.importExcel(file.getInputStream(), FamilyMemberTemp.class, params);
				long start = System.currentTimeMillis();
				memberTempService.saveBatch(list);
				logger.info("消耗时间" + (System.currentTimeMillis() - start) + "毫秒");
				// 将数据从临时表批量更新到正式表
				memberTempService.batchUpdateByUsername(loginUser.getUsername());
				// 更新完成之后将临时表数据进行删除掉
				memberTempService.removeBatchByIds(list.stream().map(FamilyMemberTemp::getId).collect(Collectors.toList()));
				return Result.ok("批量更新成功！数据行数：" + list.size());
			} catch (Exception e) {
				String msg = e.getMessage();
				logger.error(msg, e);
				if(msg!=null && msg.indexOf("Duplicate entry")>=0){
					return Result.error("文件导入失败:有重复数据！");
				}else{
					return Result.error("文件导入失败:" + e.getMessage());
				}
			} finally {
				try {
					file.getInputStream().close();
				} catch (IOException e) {
					logger.error("关闭输入流异常：", e);
				}
			}
		}
		return Result.error("文件导入失败！");
	}

	/**
	 * 方法描述：导出家庭成员数据前进行密码校验
	 * @param password
	 * @return: org.jeecg.common.api.vo.Result
	 * @author: lanls
	 * @date: 2023/8/26 16:48
	 */
	@GetMapping("/verifyPassword")
	public Result verifyPassword(@RequestParam("password") String password){

		LoginUser loginUser = (LoginUser)SecurityUtils.getSubject().getPrincipal();
		SystemUser user = systemUserService.getUserByName(loginUser.getUsername());
		//校验用户密码是否正确
		String userpwd = PasswordUtil.encrypt(user.getUsername(), password, user.getSalt());
		String syspwd = user.getPassword();
		if (!syspwd.equals(userpwd)) {
			return Result.error("密码输入错误！");
		}

		return Result.OK("密码正确！");
	}

}
