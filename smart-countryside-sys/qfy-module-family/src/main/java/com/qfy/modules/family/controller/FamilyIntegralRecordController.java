package com.qfy.modules.family.controller;

import cn.hutool.core.thread.ThreadUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qfy.modules.family.entity.FamilyIntegral;
import com.qfy.modules.family.entity.FamilyIntegralRecord;
import com.qfy.modules.family.entity.IntegralRecordExport;
import com.qfy.modules.family.entity.IntegralRecordImport;
import com.qfy.modules.family.service.IFamilyIntegralRecordService;
import com.qfy.modules.family.service.IFamilyIntegralService;
import com.qfy.modules.job.PointsStocktakingJob;
import com.qfy.modules.smartform.entity.CheckItem;
import com.qfy.modules.smartform.service.ICheckItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.aspect.annotation.PermissionData;
import org.jeecg.common.constant.CommonConstant;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.common.util.oConvertUtils;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
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
 * @Description: 农户积分申请记录
 * @Author: jeecg-boot
 * @Date:   2022-12-01
 * @Version: V1.0
 */
@Api(tags="农户积分申请记录")
@RestController
@RequestMapping("/family/familyIntegralRecord")
@Slf4j
public class FamilyIntegralRecordController extends JeecgController<FamilyIntegralRecord, IFamilyIntegralRecordService> {

	public static final Logger logger = LoggerFactory.getLogger(FamilyIntegralRecordController.class);

	@Resource
	private IFamilyIntegralRecordService familyIntegralRecordService;
	@Resource
	private ICheckItemService checkItemService;
	@Resource
	private IFamilyIntegralService	integralService;
	@Resource
	private PointsStocktakingJob pointsStocktakingJob;

	/**
	 * 分页列表查询
	 *
	 * @param familyIntegralRecord
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "农户积分申请记录-分页列表查询")
	@ApiOperation(value="农户积分申请记录-分页列表查询", notes="农户积分申请记录-分页列表查询")
	@GetMapping(value = "/list")
	@PermissionData(pageComponent = "family/integral/FamilyIntegralRecordList")
	public Result<IPage<FamilyIntegralRecord>> queryPageList(FamilyIntegralRecord familyIntegralRecord,
															 @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
															 @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
															 HttpServletRequest req) {
		QueryWrapper<FamilyIntegralRecord> wrapper = QueryGenerator.initQueryWrapper(familyIntegralRecord,req.getParameterMap());
		String familyManager = req.getParameter("familyManager");
		String orgCode = req.getParameter("orgCode");

		if (StringUtils.isNotBlank(orgCode)){
			wrapper.or(w -> {
				w.lambda().eq(FamilyIntegralRecord::getVillage, orgCode);
				w.or();
				w.lambda().eq(FamilyIntegralRecord::getNaturalTun, orgCode);
				w.or();
				w.lambda().eq(FamilyIntegralRecord::getTeam, orgCode);
			});
		}

		if (StringUtils.isNotBlank(familyManager)){
			wrapper.or();
			wrapper.lambda().inSql(FamilyIntegralRecord::getFamilyManager,
					"select id_card from qfy_family_member where family_rel = 1 and name like '%" + familyManager +
							"%'");
		}
		Page<FamilyIntegralRecord> page = new Page<>(pageNo, pageSize);
		IPage<FamilyIntegralRecord> pageList = familyIntegralRecordService.page(page, wrapper);
		return Result.OK(pageList);
	}

	/**
	 *   添加
	 *
	 * @param record
	 * @return
	 */
	@SuppressWarnings({"unchecked", "rawtypes"})
	@AutoLog(value = "农户积分申请记录-添加")
	@ApiOperation(value="农户积分申请记录-添加", notes="农户积分申请记录-添加")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody FamilyIntegralRecord record) {
		QueryWrapper wrapper = new QueryWrapper<>();
		wrapper.clear();
		if (StringUtils.isNotBlank(record.getChkidPlus())){
			wrapper.eq("name",record.getChkidPlus());
		}
		if (StringUtils.isNotBlank(record.getChkidReduce())){
			wrapper.eq("name", record.getChkidReduce());
		}
		wrapper.last("limit 1");
		CheckItem item = checkItemService.getOne(wrapper);
		if (null != item){
			if (StringUtils.isNotBlank(record.getChkidPlus())){
				record.setChkidPlus(String.valueOf(item.getId()));
				record.setExt3("1");
			} else {
				record.setChkidReduce(String.valueOf(item.getId()));
				record.setExt3("2");
			}
		}

		familyIntegralRecordService.save(record);

		wrapper.clear();
		wrapper.eq("family_number", record.getFamilyNumber());
		wrapper.last("limit 1");
		FamilyIntegral integral = integralService.getOne(wrapper);
		if (null!= integral){
			// 加分
			if (StringUtils.equals(record.getExt3(), CommonConstant.SCORE_TYPE_PLUS)){
				integral.setTotal(integral.getTotal() + record.getScorePlus());
			} else {
				Float total = integral.getTotal() - record.getScoreReduce();
				integral.setTotal(total > 0 ? total : 0	);
			}
			integralService.updateById(integral);
		}

		return Result.OK("添加成功！");
	}

	/**
	 *  编辑
	 *
	 * @param record
	 * @return
	 */
	@AutoLog(value = "农户积分申请记录-编辑")
	@ApiOperation(value="农户积分申请记录-编辑", notes="农户积分申请记录-编辑")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody FamilyIntegralRecord record) {
		QueryWrapper<CheckItem> wrapper = new QueryWrapper<>();

		if (StringUtils.isNotBlank(record.getChkidPlus())){
			wrapper.lambda().eq(CheckItem::getName,record.getChkidPlus());
		}
		if (StringUtils.isNotBlank(record.getChkidReduce())){
			wrapper.lambda().eq(CheckItem::getName, record.getChkidReduce());
		}
		wrapper.last("limit 1");
		CheckItem item = checkItemService.getOne(wrapper);
		if (null != item){
			if (StringUtils.isNotBlank(record.getChkidPlus())){
				record.setChkidPlus(String.valueOf(item.getId()));
			} else {
				record.setChkidReduce(String.valueOf(item.getId()));
			}
		}
		familyIntegralRecordService.updateById(record);
//		ThreadUtil.execute(() -> {
//			try {
//				LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
//				pointsStocktakingJob.setOrgCode(loginUser.getDistrictCode());
//				pointsStocktakingJob.execute(null);
//			} catch (JobExecutionException e) {
//				logger.error("积分记录统计执行失败：", e);
//			}
//		});
		return Result.OK("编辑成功!");
	}

//	/**
//	 *  审核
//	 *
//	 * @param record
//	 * @return
//	 */
//	@AutoLog(value = "农户积分申请-审核")
////	@ApiOperation(value="农户积分申请记录-审核", notes="农户积分申请记录-审核")
//	@RequestMapping(value = "/review", method = {RequestMethod.PUT,RequestMethod.POST})
//	public Result<String> review(@RequestBody FamilyIntegralRecord record) {
//		try {
//			Result result = service.review(record);
//			return result;
//		} catch(Exception e) {
//		    logger.error("审核失败：", e);
//		    return Result.error("审核失败！请联系管理员处理。");
//		}
//	}
//
//	/**
//	 *  撤销
//	 *
//	 * @param record
//	 * @return
//	 */
//	@AutoLog(value = "农户积分申请-撤销")
////	@ApiOperation(value="农户积分申请记录-审核", notes="农户积分申请记录-审核")
//	@RequestMapping(value = "/cancel", method = {RequestMethod.PUT,RequestMethod.POST})
//	public Result<String> cancel(@RequestBody FamilyIntegralRecord record) {
//		try {
//			Result result = service.cancel(record);
//			return result;
//		} catch(Exception e) {
//			logger.error("撤销失败：", e);
//			return Result.error("撤销失败！请联系管理员处理。");
//		}
//	}

	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "农户积分申请记录-通过id删除")
	@ApiOperation(value="农户积分申请记录-通过id删除", notes="农户积分申请记录-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		familyIntegralRecordService.removeById(id);
		ThreadUtil.execute(() -> {
			try {
				LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
				pointsStocktakingJob.setOrgCode(loginUser.getDistrictCode());
				pointsStocktakingJob.execute(null);
			} catch (JobExecutionException e) {
				logger.error("积分记录统计执行失败：", e);
			}
		});
		return Result.OK("删除成功!");
	}

	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "农户积分申请记录-批量删除")
	@ApiOperation(value="农户积分申请记录-批量删除", notes="农户积分申请记录-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.familyIntegralRecordService.removeByIds(Arrays.asList(ids.split(",")));
		ThreadUtil.execute(() -> {
			try {
				LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
				pointsStocktakingJob.setOrgCode(loginUser.getDistrictCode());
				pointsStocktakingJob.execute(null);
			} catch (JobExecutionException e) {
				logger.error("积分记录统计执行失败：", e);
			}
		});
		return Result.OK("批量删除成功!");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "农户积分申请记录-通过id查询")
	@ApiOperation(value="农户积分申请记录-通过id查询", notes="农户积分申请记录-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<FamilyIntegralRecord> queryById(@RequestParam(name="id",required=true) String id) {
		FamilyIntegralRecord familyIntegralRecord = familyIntegralRecordService.getById(id);
		if(familyIntegralRecord==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(familyIntegralRecord);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param record
    */
    @RequestMapping(value = "/exportXls")
	@PermissionData
    public ModelAndView exportXls(HttpServletRequest request, FamilyIntegralRecord record) {
		// Step.1 组装查询条件
		QueryWrapper<FamilyIntegralRecord> queryWrapper = QueryGenerator.initQueryWrapper(record, request.getParameterMap());
		LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

		String selections = request.getParameter("selections");
		if (oConvertUtils.isNotEmpty(selections)) {
			List<String> selectionList = Arrays.asList(selections.split(","));
			queryWrapper.in("id", selectionList);
		}

		// Step.2 获取导出数据
		List<FamilyIntegralRecord> pageList = service.list(queryWrapper);
		List<IntegralRecordExport> exportList = new ArrayList<>();

		// 过滤选中数据
		if (oConvertUtils.isNotEmpty(pageList)) {
			pageList.forEach(item -> {
				IntegralRecordExport export = new IntegralRecordExport();
				export.setId(item.getId());
				export.setFamilyNumber(item.getFamilyNumber());
				export.setCategory(item.getExt3());
				export.setChkId(StringUtils.isBlank(item.getChkidPlus()) ? item.getChkidReduce() : item.getChkidPlus());
				export.setFamilyManager(item.getFamilyManager());
				export.setDataYear(item.getDataYear());
				export.setReason(StringUtils.isBlank(item.getItemPlus()) ? item.getItemReduce() : item.getItemPlus());
				export.setVillage(item.getVillage());
				export.setNaturalTun(item.getNaturalTun());
				export.setTeam(item.getTeam());
				export.setScore((item.getScorePlus() == null || item.getScorePlus() == 0) ? item.getScoreReduce() :
						item.getScorePlus());
				export.setEntryDate(item.getCreateTime());
				exportList.add(export);
			});
		}

		// Step.3 AutoPoi 导出Excel
		ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		//此处设置的filename无效 ,前端会重更新设置一下
		mv.addObject(NormalExcelConstants.FILE_NAME, "居民积分登记记录");
		mv.addObject(NormalExcelConstants.CLASS, IntegralRecordExport.class);
		ExportParams exportParams=new ExportParams("居民积分登记记录报表", "导出人:" + sysUser.getRealname(), "居民积分登记记录");
		mv.addObject(NormalExcelConstants.PARAMS,exportParams);
		mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
		return mv;
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
				List<IntegralRecordImport> list = ExcelImportUtil.importExcel(file.getInputStream(), IntegralRecordImport.class, params);
				if (CollectionUtils.isEmpty(list)){
					continue;
				}

				List<CheckItem> itemList = checkItemService.list();

				List<FamilyIntegralRecord> records = new ArrayList<>();
				for (IntegralRecordImport item : list) {
					CheckItem checkItem = itemList.stream().filter(i -> item.getChkId().equals(i.getId().toString()))
											.findFirst().orElse(null);
					if (checkItem == null){
						return Result.error("请检查数据,找不到对应的检查项");
					}
					// 过滤掉每年评分1次、每月评论1次的积分项，按年评分、按月评分的积分项由系统自动添加录入。
					if ((StringUtils.equals(checkItem.getItemtype(),CommonConstant.FOR_EVERY_YEAR) ||
							StringUtils.equals(checkItem.getItemtype(),CommonConstant.FOR_EVERY_MONTH)) &&
									StringUtils.equals(item.getCategory(),CommonConstant.SCORE_TYPE_PLUS)){
						continue;
					}
					FamilyIntegralRecord record = new FamilyIntegralRecord();
					record.setFamilyNumber(item.getFamilyNumber());
					record.setExt3(item.getCategory());
					if (StringUtils.equals(item.getCategory(), CommonConstant.SCORE_TYPE_PLUS)){
						record.setChkidPlus(item.getChkId());
						record.setItemPlus(item.getReason());
						record.setScorePlus(item.getScore());
						record.setScoreReduce(0f);
					} else {
						record.setChkidReduce(item.getChkId());
						record.setItemReduce(item.getReason());
						record.setScoreReduce(item.getScore());
						record.setScorePlus(0f);
					}
					record.setFamilyManager(item.getFamilyManager());
					record.setVillage(item.getVillage());
					record.setNaturalTun(item.getNaturalTun());
					record.setTeam(item.getTeam());
					record.setDataYear(item.getDataYear());
					record.setCreateTime(item.getEntryDate());
					record.setRemarks(item.getRemarks());
					record.setDelFlag(CommonConstant.DEL_FLAG_0);
					record.setState(CommonConstant.DEL_FLAG_1);
					records.add(record);
				}

				long start = System.currentTimeMillis();
				service.saveBatch(records);
				log.info("消耗时间" + (System.currentTimeMillis() - start) + "毫秒");
				ThreadUtil.execute(() -> {
					LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
					try {
						pointsStocktakingJob.setOrgCode(loginUser.getDistrictCode());
						pointsStocktakingJob.execute(null);
					} catch (JobExecutionException e) {
						log.error(e.getMessage(), e);
					}
				});
				return Result.ok("文件导入成功！数据行数：" + records.size());
			} catch (Exception e) {
				String msg = e.getMessage();
				log.error(msg, e);
				if(msg!=null && msg.indexOf("Duplicate entry")>=0){
					return Result.error("文件导入失败:有重复数据！");
				}else{
					return Result.error("文件导入失败:" + e.getMessage());
				}
			} finally {
				try {
					file.getInputStream().close();
				} catch (IOException e) {
					log.error(e.getMessage(), e);
				}
			}
		}
		return Result.error("文件导入失败！");
    }

}
