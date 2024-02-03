package com.qfy.modules.family.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qfy.modules.family.entity.FamilyBase;
import com.qfy.modules.family.entity.FamilyIntegral;
import com.qfy.modules.family.entity.FamilyIntegralRecord;
import com.qfy.modules.family.service.IFamilyBaseService;
import com.qfy.modules.family.service.IFamilyIntegralRecordService;
import com.qfy.modules.family.service.IFamilyIntegralService;
import com.qfy.modules.org.entity.SysOrg;
import com.qfy.modules.org.service.ISysOrgService;
import com.qfy.modules.smartform.entity.CheckItem;
import com.qfy.modules.smartform.entity.CheckItemGroup;
import com.qfy.modules.smartform.service.ICheckItemGroupService;
import com.qfy.modules.smartform.service.ICheckItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.constant.CommonConstant;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.common.util.oConvertUtils;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.params.ExcelExportEntity;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecgframework.poi.util.PoiPublicUtil;
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
import org.springframework.web.servlet.ModelAndView;

/**
 * @Description: 农户积分手册
 * @Author: jeecg-boot
 * @Date:   2022-12-01
 * @Version: V1.0
 */
@Api(tags="农户积分手册")
@RestController
@RequestMapping("/family/familyIntegral")
@Slf4j
public class FamilyIntegralController extends JeecgController<FamilyIntegral, IFamilyIntegralService> {

	public static final Logger logger = LoggerFactory.getLogger(FamilyIntegralController.class);

	@Resource
	private IFamilyIntegralService familyIntegralService;
	@Resource
	private ICheckItemGroupService itemGroupService;
	@Resource
	private ICheckItemService itemService;
	@Resource
	private IFamilyIntegralRecordService integralRecordService;
	@Resource
	private IFamilyBaseService baseService;
	@Resource
	private ISysOrgService sysOrgService;

//	/**
//	 * 分页列表查询
//	 *
//	 * @param pageNo
//	 * @param pageSize
//	 * @param request
//	 * @return
//	 */
//	//@AutoLog(value = "农户积分手册-分页列表查询")
//	@ApiOperation(value="农户积分手册-分页列表查询", notes="农户积分手册-分页列表查询")
//	@GetMapping(value = "/list")
//	public Result<IPage<FamilyIntegral>> queryPageList(FamilyIntegral familyIntegral,
//														@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
//													   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
//													   HttpServletRequest request) {
//		QueryWrapper<FamilyIntegral> wrapper =  QueryGenerator.initQueryWrapper(familyIntegral, request.getParameterMap());
//		Page<FamilyIntegral> page = new Page<>(pageNo, pageSize);
//		String familyManager = request.getParameter("name");
//		String orgCode = request.getParameter("orgCode");
//
//		if (StringUtils.isNotBlank(orgCode)){
//			wrapper.lambda().likeRight(FamilyIntegral::getVillage, orgCode);
//		}
//
//		if (StringUtils.isNotBlank(familyManager)){
//			wrapper.lambda().inSql(FamilyIntegral::getFamilyManager,
//					"select id_card from qfy_family_member where family_rel = 1 " +
//							"and name like '%" + familyManager + "%'");
//		}
//
//		IPage<FamilyIntegral> pageList = familyIntegralService.page(page, wrapper);
//
//		List<FamilyIntegral> integralList = pageList.getRecords();
//		if (CollectionUtils.isNotEmpty(integralList)){
//			integralList = addCheckItem(integralList);
//			pageList.setRecords(integralList);
//		}
//
//		return Result.OK(pageList);
//	}

//	@SuppressWarnings({"unchecked", "rawtypes"})
//	private List<FamilyIntegral> addCheckItem(List<FamilyIntegral> integralList){
//		// 根据模板查询各容器
//		QueryWrapper wrapper = new QueryWrapper();
//		wrapper.clear();
//		wrapper.eq("template_id", integralList.get(0).getTemplateId());
//		wrapper.orderByAsc("seq_rank");
//		List<CheckItemGroup> itemGroups = itemGroupService.list(wrapper);
//		List<String> itemGroupIds = itemGroups.stream().map(CheckItemGroup::getId).collect(Collectors.toList());
//
//		wrapper.clear();
//		wrapper.in("chk_item_group_id",itemGroupIds);
//		List<CheckItem> itemList = itemService.list(wrapper); // 根据容器id获取全部表单项
//
//		wrapper.clear();
//		wrapper.eq("data_year", integralList.get(0).getDataYear());
//		wrapper.in("family_number", integralList.stream().map(FamilyIntegral::getFamilyNumber).collect(Collectors.toList()));
//		List<FamilyIntegralRecord> recordList = integralRecordService.list(wrapper);
//
//		for (FamilyIntegral integral : integralList) {
//			List<CheckItemGroup> itemGroupList = new ArrayList<>(itemGroups.size());
//			for (CheckItemGroup itemGroup : itemGroups) {
//				CheckItemGroup group = new CheckItemGroup();
//				group.setId(itemGroup.getId());
//				group.setName(itemGroup.getName());
//				List<CheckItem> items = itemList.stream().filter(i -> StringUtils.equals(i.getChkItemGroupId(),
//						itemGroup.getId())).collect(Collectors.toList());
//				List<CheckItem> checkItemList = new ArrayList<>(itemList.size());
//				// 计算各项目得分
//				for (CheckItem item : items) {
//					CheckItem newItem = new CheckItem();
//					newItem.setId(item.getId());
//					newItem.setName(item.getName());
//					newItem.setDefaultVal("0");// 默认分值
//					if (CollectionUtils.isNotEmpty(recordList)){
//						AtomicReference<Float> total = new AtomicReference<>(0f);
//						recordList.forEach(record -> {
//							if ((StringUtils.equals(record.getChkidPlus(), item.getId().toString())
//									|| StringUtils.equals(record.getChkidReduce(), item.getId().toString()))
//									&& StringUtils.equals(record.getFamilyNumber(), integral.getFamilyNumber())) {
//								// 加分
//								if (StringUtils.equals(record.getExt3(), CommonConstant.SCORE_TYPE_PLUS)) {
//									total.updateAndGet(v -> v + record.getScorePlus());
//								} else {
//									// 扣分
//									total.updateAndGet(v -> v - record.getScoreReduce());
//								}
//							}
//						});
//						newItem.setDefaultVal(String.valueOf(total.get().intValue()));
//					}
//					checkItemList.add(newItem);
//				}
//				group.setItemList(checkItemList);
//				itemGroupList.add(group);
//			}
//
//			integral.setGroupList(itemGroupList);
//
//		}
//		return integralList;
//	}

//	/**
//	 *   添加
//	 *
//	 * @param familyIntegral
//	 * @return
//	 */
//	@AutoLog(value = "农户积分手册-添加")
//	@ApiOperation(value="农户积分手册-添加", notes="农户积分手册-添加")
//	@PostMapping(value = "/add")
//	public Result<String> add(@RequestBody FamilyIntegral familyIntegral) {
//		familyIntegral.setDelFlag(CommonConstant.DEL_FLAG_0);
//		familyIntegralService.save(familyIntegral);
//		return Result.OK("添加成功！");
//	}
//
//	/**
//	 *  编辑
//	 *
//	 * @param familyIntegral
//	 * @return
//	 */
//	@AutoLog(value = "农户积分手册-编辑")
//	@ApiOperation(value="农户积分手册-编辑", notes="农户积分手册-编辑")
//	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
//	public Result<String> edit(@RequestBody FamilyIntegral familyIntegral) {
//		familyIntegralService.updateById(familyIntegral);
//		return Result.OK("编辑成功!");
//	}
//
//	/**
//	 *   通过id删除
//	 *
//	 * @param id
//	 * @return
//	 */
//	@AutoLog(value = "农户积分手册-通过id删除")
//	@ApiOperation(value="农户积分手册-通过id删除", notes="农户积分手册-通过id删除")
//	@DeleteMapping(value = "/delete")
//	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
//		familyIntegralService.removeById(id);
//		return Result.OK("删除成功!");
//	}
//
//	/**
//	 *  批量删除
//	 *
//	 * @param ids
//	 * @return
//	 */
//	@AutoLog(value = "农户积分手册-批量删除")
//	@ApiOperation(value="农户积分手册-批量删除", notes="农户积分手册-批量删除")
//	@DeleteMapping(value = "/deleteBatch")
//	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
//		this.familyIntegralService.removeByIds(Arrays.asList(ids.split(",")));
//		return Result.OK("批量删除成功!");
//	}
//
//	/**
//	 * 通过id查询
//	 *
//	 * @param id
//	 * @return
//	 */
//	//@AutoLog(value = "农户积分手册-通过id查询")
//	@ApiOperation(value="农户积分手册-通过id查询", notes="农户积分手册-通过id查询")
//	@GetMapping(value = "/queryById")
//	public Result<FamilyIntegral> queryById(@RequestParam(name="id",required=true) String id) {
//		FamilyIntegral familyIntegral = familyIntegralService.getById(id);
//		if(familyIntegral==null) {
//			return Result.error("未找到对应数据");
//		}
//		return Result.OK(familyIntegral);
//	}
//
//	 /**
//	  * 导出excel模板
//	  *
//	  * @param request
//	  * @param familyIntegral
//	  */
//	 @RequestMapping(value = "/tplExportXls")
//	 public ModelAndView tplExportXls(HttpServletRequest request, FamilyIntegral familyIntegral) {
//		 String title = "农户积分登记模板";
//		 QueryWrapper<FamilyIntegral> wrapper = new QueryWrapper<>();
//		 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
//		 wrapper.last("limit 1");
//		 List<FamilyIntegral> pageList = service.list(wrapper);
//		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
//		 mv.addObject(NormalExcelConstants.FILE_NAME, title);
//		 mv.addObject(NormalExcelConstants.CLASS, FamilyIntegral.class);
//		 ExportParams exportParams = new ExportParams(title, "导出人:" + sysUser.getRealname(), title);
//		 mv.addObject(NormalExcelConstants.PARAMS, exportParams);
//		 mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
//		 return mv;
//	 }
//
//    /**
//    * 导出excel数据
//    *
//    * @param request
//    * @param familyIntegral
//    */
//    @SuppressWarnings({"unchecked", "rawtypes"})
//    @RequestMapping(value = "/exportXls")
//    public void exportXls(HttpServletRequest request, FamilyIntegral familyIntegral, HttpServletResponse response) throws Exception{
//
//		// Step.1 组装查询条件
//		QueryWrapper<FamilyIntegral> wrapper = new QueryWrapper<>();
//		LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
//		String familyNumber = request.getParameter("familyNumber");
//		String familyManager = request.getParameter("name");
//		String village = request.getParameter("orgCode");
//
//		if (StringUtils.isNotBlank(familyNumber)){
//			wrapper.lambda().eq(FamilyIntegral::getFamilyNumber, familyNumber);
//		}
//
//		if (StringUtils.isNotBlank(familyManager)){
//			wrapper.lambda().inSql(FamilyIntegral::getFamilyManager,
//					"select id_card from qfy_family_member where name like '%" + familyManager + "%'");
//		}
//
//		if (StringUtils.isNotBlank(village)){
//			wrapper.lambda().likeRight(FamilyIntegral::getVillage,village);
//		}
//
//		// 过滤选中数据
//		String selections = request.getParameter("selections");
//		if (oConvertUtils.isNotEmpty(selections)) {
//			List<String> selectionList = Arrays.asList(selections.split(","));
//			wrapper.lambda().in(FamilyIntegral::getId, selectionList);
//		}
//
//		// Step.2 获取导出数据
//		List<FamilyIntegral> integralList = service.list(wrapper);
//		List<String> familyNumbers =
//				integralList.stream().map(FamilyIntegral::getFamilyNumber).collect(Collectors.toList());
//		QueryWrapper<FamilyBase> query = new QueryWrapper();
//		query.lambda().in(FamilyBase::getFamilyNumber, familyNumbers);
//		List<FamilyBase> memberList = baseService.list(query);
//		List<SysOrg> orgList = sysOrgService.list();
//
//		integralList = addCheckItem(integralList);
//
//		List<Map<String, Object>> dataList = new ArrayList<>();
//		List<ExcelExportEntity> entityList = new ArrayList<>();// 表头设置
//		Field[] fileds = PoiPublicUtil.getClassFields(FamilyIntegral.class);
//
//		int index = 0;
//		for (FamilyIntegral integral : integralList) {
//
//			// 由于字典_dictText方式无法使用，只能单独设置各个字典映射字段
//			FamilyBase member = memberList.stream().filter(m -> StringUtils.equals(m.getFamilyNumber(), integral.getFamilyNumber())).findFirst().orElse(null);
//			integral.setFamilyManager(member == null ? "" : member.getName());
//
//			SysOrg org = orgList.stream().filter(o -> StringUtils.equals(o.getCode(), integral.getVillage())).findFirst().orElse(null);
//			integral.setVillage(org != null ? org.getName() : integral.getVillage());
//
//			org = orgList.stream().filter(o -> StringUtils.equals(o.getCode(), integral.getNaturalTun())).findFirst().orElse(null);
//			integral.setNaturalTun(org != null ? org.getName() : integral.getNaturalTun());
//
//			org = orgList.stream().filter(o -> StringUtils.equals(o.getCode(), integral.getTeam())).findFirst().orElse(null);
//			integral.setTeam(org != null ? org.getName() : integral.getTeam());
//
//			// 先将全部excel注解的字段取出，放到导出数据集
//			Map<String,Object> data = new HashMap<>();
//			for (Field field : fileds) {
//				Excel excel = field.getAnnotation(Excel.class);
//				if (null != excel) {
//					field.setAccessible(true);
//					data.put(field.getName(),field.get(integral));
//					if (index > 0) continue;
//					entityList.add(new ExcelExportEntity(excel.name(),field.getName()));
//				}
//			}
//
//			List<CheckItemGroup> groupList = integral.getGroupList();
//			for (CheckItemGroup group : groupList) {
//				if (StringUtils.equals("基础分", group.getName()) || StringUtils.equals("总积分", group.getName())){
//					continue;
//				}
//				List<CheckItem> itemList = group.getItemList();
//				List<String> subColumnList = new ArrayList<>();
//				for (CheckItem item : itemList) {
//					String name = "item" + item.getId();
//					data.put(name,item.getDefaultVal());
//					if (index > 0) continue;
//					entityList.add(new ExcelExportEntity(item.getName(),name,true));
//					subColumnList.add(name);
//				}
//				if (index > 0) continue;
//				ExcelExportEntity entity = new ExcelExportEntity(group.getName(),group.getId(),true);
//				entity.setSubColumnList(subColumnList);
//				entityList.add(entity);
//			}
//			dataList.add(data);
//			index ++;
//		}
//
//		Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(null,"积分台账"), entityList, dataList);
//		response.setCharacterEncoding("UTF-8");
//		response.setHeader("content-Type", "application/vnd.ms-excel");
//		response.setHeader("Content-Disposition","attachment;filename=" + URLEncoder.encode("农户积分台账",
//				"UTF-8"));
//		ServletOutputStream out = response.getOutputStream();
//		workbook.write(out);
//		out.flush();
//    }
//
//	 /**
//      * 通过excel导入数据
//    *
//    * @param request
//    * @param response
//    * @return
//    */
//    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
//    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
//        return super.importExcel(request, response, FamilyIntegral.class);
//    }
//
//	 @Override
//	 public List<FamilyIntegral> beforeImport(List<FamilyIntegral> list) {
//		 for (FamilyIntegral integral : list) {
//			 integral.setDelFlag(CommonConstant.DEL_FLAG_0);
//			 integral.setTotal(integral.getBase());// 默认基础分
//		 }
//		 return list;
//	 }
 }
