package com.qfy.modules.subsidy.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qfy.modules.family.entity.FamilyMember;
import com.qfy.modules.family.service.IFamilyMemberService;
import com.qfy.modules.subsidy.entity.*;
import com.qfy.modules.subsidy.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.common.util.oConvertUtils;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

 /**
 * @Description: 家庭成员管理
 * @Author: jeecg-boot
 * @Date:   2022-07-15
 * @Version: V1.0
 */
@Api(tags="家庭成员管理")
@RestController
@RequestMapping("/subsidy/familyMember")
@Slf4j
public class SubsidyMemberController extends JeecgController<FamilyMember, IFamilyMemberService> {

	@Autowired
	private IFamilyMemberService familyMemberService;

	@Autowired
	private ISubsidyIndustryService subsidyIndustryService;

	@Autowired
	private ISubsidySocietyService subsidySocietyService;

	@Autowired
	private ISubsidyFinanceService subsidyFinanceService;

	@Autowired
	private ISubsidyHealthyService subsidyHealthyService;

	@Autowired
	private ISubsidyEduService subsidyEduService;

	@Autowired
	private ISubsidyEmploymentService subsidyEmploymentService;

	@Autowired
	private ISubsidyCombinedService subsidyCombinedService;

	@Autowired
	private ISubsidyCompulsoryEduService subsidyCompulsoryEduService;


	/*---------------------------------主表处理-begin-------------------------------------*/

	/**
	 * 分页列表查询
	 * @param familyMember
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "家庭成员管理-分页列表查询")
	@ApiOperation(value="家庭成员管理-分页列表查询", notes="家庭成员管理-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<FamilyMember>> queryPageList(FamilyMember familyMember,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<FamilyMember> queryWrapper = QueryGenerator.initQueryWrapper(familyMember, req.getParameterMap());
		Page<FamilyMember> page = new Page<FamilyMember>(pageNo, pageSize);
		IPage<FamilyMember> pageList = familyMemberService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	/**
     *   添加
     * @param familyMember
     * @return
     */
    @AutoLog(value = "家庭成员管理-添加")
    @ApiOperation(value="家庭成员管理-添加", notes="家庭成员管理-添加")
    @PostMapping(value = "/add")
    public Result<String> add(@RequestBody FamilyMember familyMember) {
        familyMemberService.save(familyMember);
        return Result.OK("添加成功！");
    }

    /**
     *  编辑
     * @param familyMember
     * @return
     */
    @AutoLog(value = "家庭成员管理-编辑")
    @ApiOperation(value="家庭成员管理-编辑", notes="家庭成员管理-编辑")
    @RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
    public Result<String> edit(@RequestBody FamilyMember familyMember) {
        familyMemberService.updateById(familyMember);
        return Result.OK("编辑成功!");
    }

    /**
     * 通过id删除
     * @param id
     * @return
     */
    @AutoLog(value = "家庭成员管理-通过id删除")
    @ApiOperation(value="家庭成员管理-通过id删除", notes="家庭成员管理-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<String> delete(@RequestParam(name="id",required=true) String id) {
//        familyMemberService.delMain(id);
        return Result.OK("删除成功!");
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @AutoLog(value = "家庭成员管理-批量删除")
    @ApiOperation(value="家庭成员管理-批量删除", notes="家庭成员管理-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
//        this.familyMemberService.delBatchMain(Arrays.asList(ids.split(",")));
        return Result.OK("批量删除成功!");
    }

    /**
     * 导出
     * @return
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, FamilyMember familyMember) {
        return super.exportXls(request, familyMember, FamilyMember.class, "家庭成员管理");
    }

    /**
     * 导入
     * @return
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, FamilyMember.class);
    }
	/*---------------------------------主表处理-end-------------------------------------*/


    /*--------------------------------子表处理-产业扶持信息表-begin----------------------------------------------*/
	/**
	 * 通过主表ID查询
	 * @return
	 */
	//@AutoLog(value = "产业扶持信息表-通过主表ID查询")
	@ApiOperation(value="产业扶持信息表-通过主表ID查询", notes="产业扶持信息表-通过主表ID查询")
	@GetMapping(value = "/listSubsidyIndustryByMainId")
    public Result<IPage<SubsidyIndustry>> listSubsidyIndustryByMainId(SubsidyIndustry subsidyIndustry,
																	  @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
																	  @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
																	  HttpServletRequest req) {
        QueryWrapper<SubsidyIndustry> queryWrapper = QueryGenerator.initQueryWrapper(subsidyIndustry, req.getParameterMap());
        Page<SubsidyIndustry> page = new Page<SubsidyIndustry>(pageNo, pageSize);
        IPage<SubsidyIndustry> pageList = subsidyIndustryService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

	/**
	 * 添加
	 * @param subsidyIndustry
	 * @return
	 */
	@AutoLog(value = "产业扶持信息表-添加")
	@ApiOperation(value="产业扶持信息表-添加", notes="产业扶持信息表-添加")
	@PostMapping(value = "/addSubsidyIndustry")
	public Result<String> addSubsidyIndustry(@RequestBody SubsidyIndustry subsidyIndustry) {
		subsidyIndustryService.save(subsidyIndustry);
		return Result.OK("添加成功！");
	}

    /**
	 * 编辑
	 * @param subsidyIndustry
	 * @return
	 */
	@AutoLog(value = "产业扶持信息表-编辑")
	@ApiOperation(value="产业扶持信息表-编辑", notes="产业扶持信息表-编辑")
	@RequestMapping(value = "/editSubsidyIndustry", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> editSubsidyIndustry(@RequestBody SubsidyIndustry subsidyIndustry) {
		subsidyIndustryService.updateById(subsidyIndustry);
		return Result.OK("编辑成功!");
	}

	/**
	 * 通过id删除
	 * @param id
	 * @return
	 */
	@AutoLog(value = "产业扶持信息表-通过id删除")
	@ApiOperation(value="产业扶持信息表-通过id删除", notes="产业扶持信息表-通过id删除")
	@DeleteMapping(value = "/deleteSubsidyIndustry")
	public Result<String> deleteSubsidyIndustry(@RequestParam(name="id",required=true) String id) {
		subsidyIndustryService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "产业扶持信息表-批量删除")
	@ApiOperation(value="产业扶持信息表-批量删除", notes="产业扶持信息表-批量删除")
	@DeleteMapping(value = "/deleteBatchSubsidyIndustry")
	public Result<String> deleteBatchSubsidyIndustry(@RequestParam(name="ids",required=true) String ids) {
	    this.subsidyIndustryService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

    /**
     * 导出
     * @return
     */
    @RequestMapping(value = "/exportSubsidyIndustry")
    public ModelAndView exportSubsidyIndustry(HttpServletRequest request, SubsidyIndustry subsidyIndustry) {
		 // Step.1 组装查询条件
		 QueryWrapper<SubsidyIndustry> queryWrapper = QueryGenerator.initQueryWrapper(subsidyIndustry, request.getParameterMap());
		 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

		 // Step.2 获取导出数据
		 List<SubsidyIndustry> pageList = subsidyIndustryService.list(queryWrapper);
		 List<SubsidyIndustry> exportList = null;

		 // 过滤选中数据
		 String selections = request.getParameter("selections");
		 if (oConvertUtils.isNotEmpty(selections)) {
			 List<String> selectionList = Arrays.asList(selections.split(","));
			 exportList = pageList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
		 } else {
			 exportList = pageList;
		 }

		 // Step.3 AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 //此处设置的filename无效,前端会重更新设置一下
		 mv.addObject(NormalExcelConstants.FILE_NAME, "产业扶持信息表");
		 mv.addObject(NormalExcelConstants.CLASS, SubsidyIndustry.class);
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("产业扶持信息表报表", "导出人:" + sysUser.getRealname(), "产业扶持信息表"));
		 mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
		 return mv;
    }

    /**
     * 导入
     * @return
     */
    @RequestMapping(value = "/importSubsidyIndustry/{mainId}")
    public Result<?> importSubsidyIndustry(HttpServletRequest request, HttpServletResponse response, @PathVariable("mainId") String mainId) {
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
				 List<SubsidyIndustry> list = ExcelImportUtil.importExcel(file.getInputStream(), SubsidyIndustry.class, params);
				 for (SubsidyIndustry temp : list) {
                    temp.setIdCard(mainId);
				 }
				 long start = System.currentTimeMillis();
				 subsidyIndustryService.saveBatch(list);
				 log.info("消耗时间" + (System.currentTimeMillis() - start) + "毫秒");
				 return Result.OK("文件导入成功！数据行数：" + list.size());
			 } catch (Exception e) {
				 log.error(e.getMessage(), e);
				 return Result.error("文件导入失败:" + e.getMessage());
			 } finally {
				 try {
					 file.getInputStream().close();
				 } catch (IOException e) {
					 e.printStackTrace();
				 }
			 }
		 }
		 return Result.error("文件导入失败！");
    }

    /*--------------------------------子表处理-产业扶持信息表-end----------------------------------------------*/

    /*--------------------------------子表处理-社会帮扶-begin----------------------------------------------*/
	/**
	 * 通过主表ID查询
	 * @return
	 */
	//@AutoLog(value = "社会帮扶-通过主表ID查询")
	@ApiOperation(value="社会帮扶-通过主表ID查询", notes="社会帮扶-通过主表ID查询")
	@GetMapping(value = "/listSubsidySocietyByMainId")
    public Result<IPage<SubsidySociety>> listSubsidySocietyByMainId(SubsidySociety subsidySociety,
																	@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
																	@RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
																	HttpServletRequest req) {
        QueryWrapper<SubsidySociety> queryWrapper = QueryGenerator.initQueryWrapper(subsidySociety, req.getParameterMap());
        Page<SubsidySociety> page = new Page<SubsidySociety>(pageNo, pageSize);
        IPage<SubsidySociety> pageList = subsidySocietyService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

	/**
	 * 添加
	 * @param subsidySociety
	 * @return
	 */
	@AutoLog(value = "社会帮扶-添加")
	@ApiOperation(value="社会帮扶-添加", notes="社会帮扶-添加")
	@PostMapping(value = "/addSubsidySociety")
	public Result<String> addSubsidySociety(@RequestBody SubsidySociety subsidySociety) {
		subsidySocietyService.save(subsidySociety);
		return Result.OK("添加成功！");
	}

    /**
	 * 编辑
	 * @param subsidySociety
	 * @return
	 */
	@AutoLog(value = "社会帮扶-编辑")
	@ApiOperation(value="社会帮扶-编辑", notes="社会帮扶-编辑")
	@RequestMapping(value = "/editSubsidySociety", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> editSubsidySociety(@RequestBody SubsidySociety subsidySociety) {
		subsidySocietyService.updateById(subsidySociety);
		return Result.OK("编辑成功!");
	}

	/**
	 * 通过id删除
	 * @param id
	 * @return
	 */
	@AutoLog(value = "社会帮扶-通过id删除")
	@ApiOperation(value="社会帮扶-通过id删除", notes="社会帮扶-通过id删除")
	@DeleteMapping(value = "/deleteSubsidySociety")
	public Result<String> deleteSubsidySociety(@RequestParam(name="id",required=true) String id) {
		subsidySocietyService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "社会帮扶-批量删除")
	@ApiOperation(value="社会帮扶-批量删除", notes="社会帮扶-批量删除")
	@DeleteMapping(value = "/deleteBatchSubsidySociety")
	public Result<String> deleteBatchSubsidySociety(@RequestParam(name="ids",required=true) String ids) {
	    this.subsidySocietyService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

    /**
     * 导出
     * @return
     */
    @RequestMapping(value = "/exportSubsidySociety")
    public ModelAndView exportSubsidySociety(HttpServletRequest request, SubsidySociety subsidySociety) {
		 // Step.1 组装查询条件
		 QueryWrapper<SubsidySociety> queryWrapper = QueryGenerator.initQueryWrapper(subsidySociety, request.getParameterMap());
		 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

		 // Step.2 获取导出数据
		 List<SubsidySociety> pageList = subsidySocietyService.list(queryWrapper);
		 List<SubsidySociety> exportList = null;

		 // 过滤选中数据
		 String selections = request.getParameter("selections");
		 if (oConvertUtils.isNotEmpty(selections)) {
			 List<String> selectionList = Arrays.asList(selections.split(","));
			 exportList = pageList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
		 } else {
			 exportList = pageList;
		 }

		 // Step.3 AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 //此处设置的filename无效,前端会重更新设置一下
		 mv.addObject(NormalExcelConstants.FILE_NAME, "社会帮扶");
		 mv.addObject(NormalExcelConstants.CLASS, SubsidySociety.class);
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("社会帮扶报表", "导出人:" + sysUser.getRealname(), "社会帮扶"));
		 mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
		 return mv;
    }

    /**
     * 导入
     * @return
     */
    @RequestMapping(value = "/importSubsidySociety/{mainId}")
    public Result<?> importSubsidySociety(HttpServletRequest request, HttpServletResponse response, @PathVariable("mainId") String mainId) {
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
				 List<SubsidySociety> list = ExcelImportUtil.importExcel(file.getInputStream(), SubsidySociety.class, params);
				 for (SubsidySociety temp : list) {
                    temp.setIdCard(mainId);
				 }
				 long start = System.currentTimeMillis();
				 subsidySocietyService.saveBatch(list);
				 log.info("消耗时间" + (System.currentTimeMillis() - start) + "毫秒");
				 return Result.OK("文件导入成功！数据行数：" + list.size());
			 } catch (Exception e) {
				 log.error(e.getMessage(), e);
				 return Result.error("文件导入失败:" + e.getMessage());
			 } finally {
				 try {
					 file.getInputStream().close();
				 } catch (IOException e) {
					 e.printStackTrace();
				 }
			 }
		 }
		 return Result.error("文件导入失败！");
    }

    /*--------------------------------子表处理-社会帮扶-end----------------------------------------------*/

    /*--------------------------------子表处理-医疗信息表-begin----------------------------------------------*/
	/**
	 * 通过主表ID查询
	 * @return
	 */
	//@AutoLog(value = "医疗信息表-通过主表ID查询")
	@ApiOperation(value="医疗信息表-通过主表ID查询", notes="医疗信息表-通过主表ID查询")
	@GetMapping(value = "/listSubsidyFinanceByMainId")
    public Result<IPage<SubsidyFinance>> listSubsidyFinanceByMainId(SubsidyFinance subsidyFinance,
																	@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
																	@RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
																	HttpServletRequest req) {
        QueryWrapper<SubsidyFinance> queryWrapper = QueryGenerator.initQueryWrapper(subsidyFinance, req.getParameterMap());
        Page<SubsidyFinance> page = new Page<SubsidyFinance>(pageNo, pageSize);
        IPage<SubsidyFinance> pageList = subsidyFinanceService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

	/**
	 * 添加
	 * @param subsidyFinance
	 * @return
	 */
	@AutoLog(value = "医疗信息表-添加")
	@ApiOperation(value="医疗信息表-添加", notes="医疗信息表-添加")
	@PostMapping(value = "/addSubsidyFinance")
	public Result<String> addSubsidyFinance(@RequestBody SubsidyFinance subsidyFinance) {
		subsidyFinanceService.save(subsidyFinance);
		return Result.OK("添加成功！");
	}

    /**
	 * 编辑
	 * @param subsidyFinance
	 * @return
	 */
	@AutoLog(value = "医疗信息表-编辑")
	@ApiOperation(value="医疗信息表-编辑", notes="医疗信息表-编辑")
	@RequestMapping(value = "/editSubsidyFinance", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> editSubsidyFinance(@RequestBody SubsidyFinance subsidyFinance) {
		subsidyFinanceService.updateById(subsidyFinance);
		return Result.OK("编辑成功!");
	}

	/**
	 * 通过id删除
	 * @param id
	 * @return
	 */
	@AutoLog(value = "医疗信息表-通过id删除")
	@ApiOperation(value="医疗信息表-通过id删除", notes="医疗信息表-通过id删除")
	@DeleteMapping(value = "/deleteSubsidyFinance")
	public Result<String> deleteSubsidyFinance(@RequestParam(name="id",required=true) String id) {
		subsidyFinanceService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "医疗信息表-批量删除")
	@ApiOperation(value="医疗信息表-批量删除", notes="医疗信息表-批量删除")
	@DeleteMapping(value = "/deleteBatchSubsidyFinance")
	public Result<String> deleteBatchSubsidyFinance(@RequestParam(name="ids",required=true) String ids) {
	    this.subsidyFinanceService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

    /**
     * 导出
     * @return
     */
    @RequestMapping(value = "/exportSubsidyFinance")
    public ModelAndView exportSubsidyFinance(HttpServletRequest request, SubsidyFinance subsidyFinance) {
		 // Step.1 组装查询条件
		 QueryWrapper<SubsidyFinance> queryWrapper = QueryGenerator.initQueryWrapper(subsidyFinance, request.getParameterMap());
		 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

		 // Step.2 获取导出数据
		 List<SubsidyFinance> pageList = subsidyFinanceService.list(queryWrapper);
		 List<SubsidyFinance> exportList = null;

		 // 过滤选中数据
		 String selections = request.getParameter("selections");
		 if (oConvertUtils.isNotEmpty(selections)) {
			 List<String> selectionList = Arrays.asList(selections.split(","));
			 exportList = pageList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
		 } else {
			 exportList = pageList;
		 }

		 // Step.3 AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 //此处设置的filename无效,前端会重更新设置一下
		 mv.addObject(NormalExcelConstants.FILE_NAME, "医疗信息表");
		 mv.addObject(NormalExcelConstants.CLASS, SubsidyFinance.class);
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("医疗信息表报表", "导出人:" + sysUser.getRealname(), "医疗信息表"));
		 mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
		 return mv;
    }

    /**
     * 导入
     * @return
     */
    @RequestMapping(value = "/importSubsidyFinance/{mainId}")
    public Result<?> importSubsidyFinance(HttpServletRequest request, HttpServletResponse response, @PathVariable("mainId") String mainId) {
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
				 List<SubsidyFinance> list = ExcelImportUtil.importExcel(file.getInputStream(), SubsidyFinance.class, params);
				 for (SubsidyFinance temp : list) {
                    temp.setIdCard(mainId);
				 }
				 long start = System.currentTimeMillis();
				 subsidyFinanceService.saveBatch(list);
				 log.info("消耗时间" + (System.currentTimeMillis() - start) + "毫秒");
				 return Result.OK("文件导入成功！数据行数：" + list.size());
			 } catch (Exception e) {
				 log.error(e.getMessage(), e);
				 return Result.error("文件导入失败:" + e.getMessage());
			 } finally {
				 try {
					 file.getInputStream().close();
				 } catch (IOException e) {
					 e.printStackTrace();
				 }
			 }
		 }
		 return Result.error("文件导入失败！");
    }

    /*--------------------------------子表处理-医疗信息表-end----------------------------------------------*/

    /*--------------------------------子表处理-健康扶持信息表-begin----------------------------------------------*/
	/**
	 * 通过主表ID查询
	 * @return
	 */
	//@AutoLog(value = "健康扶持信息表-通过主表ID查询")
	@ApiOperation(value="健康扶持信息表-通过主表ID查询", notes="健康扶持信息表-通过主表ID查询")
	@GetMapping(value = "/listSubsidyHealthyByMainId")
    public Result<IPage<SubsidyHealthy>> listSubsidyHealthyByMainId(SubsidyHealthy subsidyHealthy,
																	@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
																	@RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
																	HttpServletRequest req) {
        QueryWrapper<SubsidyHealthy> queryWrapper = QueryGenerator.initQueryWrapper(subsidyHealthy, req.getParameterMap());
        Page<SubsidyHealthy> page = new Page<SubsidyHealthy>(pageNo, pageSize);
        IPage<SubsidyHealthy> pageList = subsidyHealthyService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

	/**
	 * 添加
	 * @param subsidyHealthy
	 * @return
	 */
	@AutoLog(value = "健康扶持信息表-添加")
	@ApiOperation(value="健康扶持信息表-添加", notes="健康扶持信息表-添加")
	@PostMapping(value = "/addSubsidyHealthy")
	public Result<String> addSubsidyHealthy(@RequestBody SubsidyHealthy subsidyHealthy) {
		subsidyHealthyService.save(subsidyHealthy);
		return Result.OK("添加成功！");
	}

    /**
	 * 编辑
	 * @param subsidyHealthy
	 * @return
	 */
	@AutoLog(value = "健康扶持信息表-编辑")
	@ApiOperation(value="健康扶持信息表-编辑", notes="健康扶持信息表-编辑")
	@RequestMapping(value = "/editSubsidyHealthy", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> editSubsidyHealthy(@RequestBody SubsidyHealthy subsidyHealthy) {
		subsidyHealthyService.updateById(subsidyHealthy);
		return Result.OK("编辑成功!");
	}

	/**
	 * 通过id删除
	 * @param id
	 * @return
	 */
	@AutoLog(value = "健康扶持信息表-通过id删除")
	@ApiOperation(value="健康扶持信息表-通过id删除", notes="健康扶持信息表-通过id删除")
	@DeleteMapping(value = "/deleteSubsidyHealthy")
	public Result<String> deleteSubsidyHealthy(@RequestParam(name="id",required=true) String id) {
		subsidyHealthyService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "健康扶持信息表-批量删除")
	@ApiOperation(value="健康扶持信息表-批量删除", notes="健康扶持信息表-批量删除")
	@DeleteMapping(value = "/deleteBatchSubsidyHealthy")
	public Result<String> deleteBatchSubsidyHealthy(@RequestParam(name="ids",required=true) String ids) {
	    this.subsidyHealthyService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

    /**
     * 导出
     * @return
     */
    @RequestMapping(value = "/exportSubsidyHealthy")
    public ModelAndView exportSubsidyHealthy(HttpServletRequest request, SubsidyHealthy subsidyHealthy) {
		 // Step.1 组装查询条件
		 QueryWrapper<SubsidyHealthy> queryWrapper = QueryGenerator.initQueryWrapper(subsidyHealthy, request.getParameterMap());
		 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

		 // Step.2 获取导出数据
		 List<SubsidyHealthy> pageList = subsidyHealthyService.list(queryWrapper);
		 List<SubsidyHealthy> exportList = null;

		 // 过滤选中数据
		 String selections = request.getParameter("selections");
		 if (oConvertUtils.isNotEmpty(selections)) {
			 List<String> selectionList = Arrays.asList(selections.split(","));
			 exportList = pageList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
		 } else {
			 exportList = pageList;
		 }

		 // Step.3 AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 //此处设置的filename无效,前端会重更新设置一下
		 mv.addObject(NormalExcelConstants.FILE_NAME, "健康扶持信息表");
		 mv.addObject(NormalExcelConstants.CLASS, SubsidyHealthy.class);
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("健康扶持信息表报表", "导出人:" + sysUser.getRealname(), "健康扶持信息表"));
		 mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
		 return mv;
    }

    /**
     * 导入
     * @return
     */
    @RequestMapping(value = "/importSubsidyHealthy/{mainId}")
    public Result<?> importSubsidyHealthy(HttpServletRequest request, HttpServletResponse response, @PathVariable("mainId") String mainId) {
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
				 List<SubsidyHealthy> list = ExcelImportUtil.importExcel(file.getInputStream(), SubsidyHealthy.class, params);
				 for (SubsidyHealthy temp : list) {
                    temp.setIdCard(mainId);
				 }
				 long start = System.currentTimeMillis();
				 subsidyHealthyService.saveBatch(list);
				 log.info("消耗时间" + (System.currentTimeMillis() - start) + "毫秒");
				 return Result.OK("文件导入成功！数据行数：" + list.size());
			 } catch (Exception e) {
				 log.error(e.getMessage(), e);
				 return Result.error("文件导入失败:" + e.getMessage());
			 } finally {
				 try {
					 file.getInputStream().close();
				 } catch (IOException e) {
					 e.printStackTrace();
				 }
			 }
		 }
		 return Result.error("文件导入失败！");
    }

    /*--------------------------------子表处理-健康扶持信息表-end----------------------------------------------*/

    /*--------------------------------子表处理-教育扶持信息表-begin----------------------------------------------*/
	/**
	 * 通过主表ID查询
	 * @return
	 */
	//@AutoLog(value = "教育扶持信息表-通过主表ID查询")
	@ApiOperation(value="教育扶持信息表-通过主表ID查询", notes="教育扶持信息表-通过主表ID查询")
	@GetMapping(value = "/listSubsidyEduByMainId")
    public Result<IPage<SubsidyEdu>> listSubsidyEduByMainId(SubsidyEdu subsidyEdu,
															@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
															@RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
															HttpServletRequest req) {
        QueryWrapper<SubsidyEdu> queryWrapper = QueryGenerator.initQueryWrapper(subsidyEdu, req.getParameterMap());
        Page<SubsidyEdu> page = new Page<SubsidyEdu>(pageNo, pageSize);
        IPage<SubsidyEdu> pageList = subsidyEduService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

	/**
	 * 添加
	 * @param subsidyEdu
	 * @return
	 */
	@AutoLog(value = "教育扶持信息表-添加")
	@ApiOperation(value="教育扶持信息表-添加", notes="教育扶持信息表-添加")
	@PostMapping(value = "/addSubsidyEdu")
	public Result<String> addSubsidyEdu(@RequestBody SubsidyEdu subsidyEdu) {
		subsidyEduService.save(subsidyEdu);
		return Result.OK("添加成功！");
	}

    /**
	 * 编辑
	 * @param subsidyEdu
	 * @return
	 */
	@AutoLog(value = "教育扶持信息表-编辑")
	@ApiOperation(value="教育扶持信息表-编辑", notes="教育扶持信息表-编辑")
	@RequestMapping(value = "/editSubsidyEdu", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> editSubsidyEdu(@RequestBody SubsidyEdu subsidyEdu) {
		subsidyEduService.updateById(subsidyEdu);
		return Result.OK("编辑成功!");
	}

	/**
	 * 通过id删除
	 * @param id
	 * @return
	 */
	@AutoLog(value = "教育扶持信息表-通过id删除")
	@ApiOperation(value="教育扶持信息表-通过id删除", notes="教育扶持信息表-通过id删除")
	@DeleteMapping(value = "/deleteSubsidyEdu")
	public Result<String> deleteSubsidyEdu(@RequestParam(name="id",required=true) String id) {
		subsidyEduService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "教育扶持信息表-批量删除")
	@ApiOperation(value="教育扶持信息表-批量删除", notes="教育扶持信息表-批量删除")
	@DeleteMapping(value = "/deleteBatchSubsidyEdu")
	public Result<String> deleteBatchSubsidyEdu(@RequestParam(name="ids",required=true) String ids) {
	    this.subsidyEduService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

    /**
     * 导出
     * @return
     */
    @RequestMapping(value = "/exportSubsidyEdu")
    public ModelAndView exportSubsidyEdu(HttpServletRequest request, SubsidyEdu subsidyEdu) {
		 // Step.1 组装查询条件
		 QueryWrapper<SubsidyEdu> queryWrapper = QueryGenerator.initQueryWrapper(subsidyEdu, request.getParameterMap());
		 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

		 // Step.2 获取导出数据
		 List<SubsidyEdu> pageList = subsidyEduService.list(queryWrapper);
		 List<SubsidyEdu> exportList = null;

		 // 过滤选中数据
		 String selections = request.getParameter("selections");
		 if (oConvertUtils.isNotEmpty(selections)) {
			 List<String> selectionList = Arrays.asList(selections.split(","));
			 exportList = pageList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
		 } else {
			 exportList = pageList;
		 }

		 // Step.3 AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 //此处设置的filename无效,前端会重更新设置一下
		 mv.addObject(NormalExcelConstants.FILE_NAME, "教育扶持信息表");
		 mv.addObject(NormalExcelConstants.CLASS, SubsidyEdu.class);
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("教育扶持信息表报表", "导出人:" + sysUser.getRealname(), "教育扶持信息表"));
		 mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
		 return mv;
    }

    /**
     * 导入
     * @return
     */
    @RequestMapping(value = "/importSubsidyEdu/{mainId}")
    public Result<?> importSubsidyEdu(HttpServletRequest request, HttpServletResponse response, @PathVariable("mainId") String mainId) {
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
				 List<SubsidyEdu> list = ExcelImportUtil.importExcel(file.getInputStream(), SubsidyEdu.class, params);
				 for (SubsidyEdu temp : list) {
                    temp.setIdCard(mainId);
				 }
				 long start = System.currentTimeMillis();
				 subsidyEduService.saveBatch(list);
				 log.info("消耗时间" + (System.currentTimeMillis() - start) + "毫秒");
				 return Result.OK("文件导入成功！数据行数：" + list.size());
			 } catch (Exception e) {
				 log.error(e.getMessage(), e);
				 return Result.error("文件导入失败:" + e.getMessage());
			 } finally {
				 try {
					 file.getInputStream().close();
				 } catch (IOException e) {
					 e.printStackTrace();
				 }
			 }
		 }
		 return Result.error("文件导入失败！");
    }

    /*--------------------------------子表处理-教育扶持信息表-end----------------------------------------------*/

    /*--------------------------------子表处理-就业扶持信息表-begin----------------------------------------------*/
	/**
	 * 通过主表ID查询
	 * @return
	 */
	//@AutoLog(value = "就业扶持信息表-通过主表ID查询")
	@ApiOperation(value="就业扶持信息表-通过主表ID查询", notes="就业扶持信息表-通过主表ID查询")
	@GetMapping(value = "/listSubsidyEmploymentByMainId")
    public Result<IPage<SubsidyEmployment>> listSubsidyEmploymentByMainId(SubsidyEmployment subsidyEmployment,
                                                    @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                    @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                    HttpServletRequest req) {
        QueryWrapper<SubsidyEmployment> queryWrapper = QueryGenerator.initQueryWrapper(subsidyEmployment, req.getParameterMap());
        Page<SubsidyEmployment> page = new Page<SubsidyEmployment>(pageNo, pageSize);
        IPage<SubsidyEmployment> pageList = subsidyEmploymentService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

	/**
	 * 添加
	 * @param subsidyEmployment
	 * @return
	 */
	@AutoLog(value = "就业扶持信息表-添加")
	@ApiOperation(value="就业扶持信息表-添加", notes="就业扶持信息表-添加")
	@PostMapping(value = "/addSubsidyEmployment")
	public Result<String> addSubsidyEmployment(@RequestBody SubsidyEmployment subsidyEmployment) {
		subsidyEmploymentService.save(subsidyEmployment);
		return Result.OK("添加成功！");
	}

    /**
	 * 编辑
	 * @param subsidyEmployment
	 * @return
	 */
	@AutoLog(value = "就业扶持信息表-编辑")
	@ApiOperation(value="就业扶持信息表-编辑", notes="就业扶持信息表-编辑")
	@RequestMapping(value = "/editSubsidyEmployment", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> editSubsidyEmployment(@RequestBody SubsidyEmployment subsidyEmployment) {
		subsidyEmploymentService.updateById(subsidyEmployment);
		return Result.OK("编辑成功!");
	}

	/**
	 * 通过id删除
	 * @param id
	 * @return
	 */
	@AutoLog(value = "就业扶持信息表-通过id删除")
	@ApiOperation(value="就业扶持信息表-通过id删除", notes="就业扶持信息表-通过id删除")
	@DeleteMapping(value = "/deleteSubsidyEmployment")
	public Result<String> deleteSubsidyEmployment(@RequestParam(name="id",required=true) String id) {
		subsidyEmploymentService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "就业扶持信息表-批量删除")
	@ApiOperation(value="就业扶持信息表-批量删除", notes="就业扶持信息表-批量删除")
	@DeleteMapping(value = "/deleteBatchSubsidyEmployment")
	public Result<String> deleteBatchSubsidyEmployment(@RequestParam(name="ids",required=true) String ids) {
	    this.subsidyEmploymentService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

    /**
     * 导出
     * @return
     */
    @RequestMapping(value = "/exportSubsidyEmployment")
    public ModelAndView exportSubsidyEmployment(HttpServletRequest request, SubsidyEmployment subsidyEmployment) {
		 // Step.1 组装查询条件
		 QueryWrapper<SubsidyEmployment> queryWrapper = QueryGenerator.initQueryWrapper(subsidyEmployment, request.getParameterMap());
		 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

		 // Step.2 获取导出数据
		 List<SubsidyEmployment> pageList = subsidyEmploymentService.list(queryWrapper);
		 List<SubsidyEmployment> exportList = null;

		 // 过滤选中数据
		 String selections = request.getParameter("selections");
		 if (oConvertUtils.isNotEmpty(selections)) {
			 List<String> selectionList = Arrays.asList(selections.split(","));
			 exportList = pageList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
		 } else {
			 exportList = pageList;
		 }

		 // Step.3 AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 //此处设置的filename无效,前端会重更新设置一下
		 mv.addObject(NormalExcelConstants.FILE_NAME, "就业扶持信息表");
		 mv.addObject(NormalExcelConstants.CLASS, SubsidyEmployment.class);
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("就业扶持信息表报表", "导出人:" + sysUser.getRealname(), "就业扶持信息表"));
		 mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
		 return mv;
    }

    /**
     * 导入
     * @return
     */
    @RequestMapping(value = "/importSubsidyEmployment/{mainId}")
    public Result<?> importSubsidyEmployment(HttpServletRequest request, HttpServletResponse response, @PathVariable("mainId") String mainId) {
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
				 List<SubsidyEmployment> list = ExcelImportUtil.importExcel(file.getInputStream(), SubsidyEmployment.class, params);
				 for (SubsidyEmployment temp : list) {
                    temp.setIdCard(mainId);
				 }
				 long start = System.currentTimeMillis();
				 subsidyEmploymentService.saveBatch(list);
				 log.info("消耗时间" + (System.currentTimeMillis() - start) + "毫秒");
				 return Result.OK("文件导入成功！数据行数：" + list.size());
			 } catch (Exception e) {
				 log.error(e.getMessage(), e);
				 return Result.error("文件导入失败:" + e.getMessage());
			 } finally {
				 try {
					 file.getInputStream().close();
				 } catch (IOException e) {
					 e.printStackTrace();
				 }
			 }
		 }
		 return Result.error("文件导入失败！");
    }

    /*--------------------------------子表处理-就业扶持信息表-end----------------------------------------------*/

    /*--------------------------------子表处理-综合保障扶持信息-begin----------------------------------------------*/
	/**
	 * 通过主表ID查询
	 * @return
	 */
	//@AutoLog(value = "综合保障扶持信息-通过主表ID查询")
	@ApiOperation(value="综合保障扶持信息-通过主表ID查询", notes="综合保障扶持信息-通过主表ID查询")
	@GetMapping(value = "/listSubsidyCombinedByMainId")
    public Result<IPage<SubsidyCombined>> listSubsidyCombinedByMainId(SubsidyCombined subsidyCombined,
                                                    @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                    @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                    HttpServletRequest req) {
        QueryWrapper<SubsidyCombined> queryWrapper = QueryGenerator.initQueryWrapper(subsidyCombined, req.getParameterMap());
        Page<SubsidyCombined> page = new Page<SubsidyCombined>(pageNo, pageSize);
        IPage<SubsidyCombined> pageList = subsidyCombinedService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

	/**
	 * 添加
	 * @param subsidyCombined
	 * @return
	 */
	@AutoLog(value = "综合保障扶持信息-添加")
	@ApiOperation(value="综合保障扶持信息-添加", notes="综合保障扶持信息-添加")
	@PostMapping(value = "/addSubsidyCombined")
	public Result<String> addSubsidyCombined(@RequestBody SubsidyCombined subsidyCombined) {
		subsidyCombinedService.save(subsidyCombined);
		return Result.OK("添加成功！");
	}

    /**
	 * 编辑
	 * @param subsidyCombined
	 * @return
	 */
	@AutoLog(value = "综合保障扶持信息-编辑")
	@ApiOperation(value="综合保障扶持信息-编辑", notes="综合保障扶持信息-编辑")
	@RequestMapping(value = "/editSubsidyCombined", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> editSubsidyCombined(@RequestBody SubsidyCombined subsidyCombined) {
		subsidyCombinedService.updateById(subsidyCombined);
		return Result.OK("编辑成功!");
	}

	/**
	 * 通过id删除
	 * @param id
	 * @return
	 */
	@AutoLog(value = "综合保障扶持信息-通过id删除")
	@ApiOperation(value="综合保障扶持信息-通过id删除", notes="综合保障扶持信息-通过id删除")
	@DeleteMapping(value = "/deleteSubsidyCombined")
	public Result<String> deleteSubsidyCombined(@RequestParam(name="id",required=true) String id) {
		subsidyCombinedService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "综合保障扶持信息-批量删除")
	@ApiOperation(value="综合保障扶持信息-批量删除", notes="综合保障扶持信息-批量删除")
	@DeleteMapping(value = "/deleteBatchSubsidyCombined")
	public Result<String> deleteBatchSubsidyCombined(@RequestParam(name="ids",required=true) String ids) {
	    this.subsidyCombinedService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

    /**
     * 导出
     * @return
     */
    @RequestMapping(value = "/exportSubsidyCombined")
    public ModelAndView exportSubsidyCombined(HttpServletRequest request, SubsidyCombined subsidyCombined) {
		 // Step.1 组装查询条件
		 QueryWrapper<SubsidyCombined> queryWrapper = QueryGenerator.initQueryWrapper(subsidyCombined, request.getParameterMap());
		 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

		 // Step.2 获取导出数据
		 List<SubsidyCombined> pageList = subsidyCombinedService.list(queryWrapper);
		 List<SubsidyCombined> exportList = null;

		 // 过滤选中数据
		 String selections = request.getParameter("selections");
		 if (oConvertUtils.isNotEmpty(selections)) {
			 List<String> selectionList = Arrays.asList(selections.split(","));
			 exportList = pageList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
		 } else {
			 exportList = pageList;
		 }

		 // Step.3 AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 //此处设置的filename无效,前端会重更新设置一下
		 mv.addObject(NormalExcelConstants.FILE_NAME, "综合保障扶持信息");
		 mv.addObject(NormalExcelConstants.CLASS, SubsidyCombined.class);
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("综合保障扶持信息报表", "导出人:" + sysUser.getRealname(), "综合保障扶持信息"));
		 mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
		 return mv;
    }

    /**
     * 导入
     * @return
     */
    @RequestMapping(value = "/importSubsidyCombined/{mainId}")
    public Result<?> importSubsidyCombined(HttpServletRequest request, HttpServletResponse response, @PathVariable("mainId") String mainId) {
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
				 List<SubsidyCombined> list = ExcelImportUtil.importExcel(file.getInputStream(), SubsidyCombined.class, params);
				 for (SubsidyCombined temp : list) {
                    temp.setIdCard(mainId);
				 }
				 long start = System.currentTimeMillis();
				 subsidyCombinedService.saveBatch(list);
				 log.info("消耗时间" + (System.currentTimeMillis() - start) + "毫秒");
				 return Result.OK("文件导入成功！数据行数：" + list.size());
			 } catch (Exception e) {
				 log.error(e.getMessage(), e);
				 return Result.error("文件导入失败:" + e.getMessage());
			 } finally {
				 try {
					 file.getInputStream().close();
				 } catch (IOException e) {
					 e.printStackTrace();
				 }
			 }
		 }
		 return Result.error("文件导入失败！");
    }

    /*--------------------------------子表处理-综合保障扶持信息-end----------------------------------------------*/

    /*--------------------------------子表处理-义务教育扶持信息表-begin----------------------------------------------*/
	/**
	 * 通过主表ID查询
	 * @return
	 */
	//@AutoLog(value = "义务教育扶持信息表-通过主表ID查询")
	@ApiOperation(value="义务教育扶持信息表-通过主表ID查询", notes="义务教育扶持信息表-通过主表ID查询")
	@GetMapping(value = "/listSubsidyCompulsoryEduByMainId")
    public Result<IPage<SubsidyCompulsoryEdu>> listSubsidyCompulsoryEduByMainId(SubsidyCompulsoryEdu subsidyCompulsoryEdu,
                                                    @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                    @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                    HttpServletRequest req) {
        QueryWrapper<SubsidyCompulsoryEdu> queryWrapper = QueryGenerator.initQueryWrapper(subsidyCompulsoryEdu, req.getParameterMap());
        Page<SubsidyCompulsoryEdu> page = new Page<SubsidyCompulsoryEdu>(pageNo, pageSize);
        IPage<SubsidyCompulsoryEdu> pageList = subsidyCompulsoryEduService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

	/**
	 * 添加
	 * @param subsidyCompulsoryEdu
	 * @return
	 */
	@AutoLog(value = "义务教育扶持信息表-添加")
	@ApiOperation(value="义务教育扶持信息表-添加", notes="义务教育扶持信息表-添加")
	@PostMapping(value = "/addSubsidyCompulsoryEdu")
	public Result<String> addSubsidyCompulsoryEdu(@RequestBody SubsidyCompulsoryEdu subsidyCompulsoryEdu) {
		subsidyCompulsoryEduService.save(subsidyCompulsoryEdu);
		return Result.OK("添加成功！");
	}

    /**
	 * 编辑
	 * @param subsidyCompulsoryEdu
	 * @return
	 */
	@AutoLog(value = "义务教育扶持信息表-编辑")
	@ApiOperation(value="义务教育扶持信息表-编辑", notes="义务教育扶持信息表-编辑")
	@RequestMapping(value = "/editSubsidyCompulsoryEdu", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> editSubsidyCompulsoryEdu(@RequestBody SubsidyCompulsoryEdu subsidyCompulsoryEdu) {
		subsidyCompulsoryEduService.updateById(subsidyCompulsoryEdu);
		return Result.OK("编辑成功!");
	}

	/**
	 * 通过id删除
	 * @param id
	 * @return
	 */
	@AutoLog(value = "义务教育扶持信息表-通过id删除")
	@ApiOperation(value="义务教育扶持信息表-通过id删除", notes="义务教育扶持信息表-通过id删除")
	@DeleteMapping(value = "/deleteSubsidyCompulsoryEdu")
	public Result<String> deleteSubsidyCompulsoryEdu(@RequestParam(name="id",required=true) String id) {
		subsidyCompulsoryEduService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "义务教育扶持信息表-批量删除")
	@ApiOperation(value="义务教育扶持信息表-批量删除", notes="义务教育扶持信息表-批量删除")
	@DeleteMapping(value = "/deleteBatchSubsidyCompulsoryEdu")
	public Result<String> deleteBatchSubsidyCompulsoryEdu(@RequestParam(name="ids",required=true) String ids) {
	    this.subsidyCompulsoryEduService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

    /**
     * 导出
     * @return
     */
    @RequestMapping(value = "/exportSubsidyCompulsoryEdu")
    public ModelAndView exportSubsidyCompulsoryEdu(HttpServletRequest request, SubsidyCompulsoryEdu subsidyCompulsoryEdu) {
		 // Step.1 组装查询条件
		 QueryWrapper<SubsidyCompulsoryEdu> queryWrapper = QueryGenerator.initQueryWrapper(subsidyCompulsoryEdu, request.getParameterMap());
		 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

		 // Step.2 获取导出数据
		 List<SubsidyCompulsoryEdu> pageList = subsidyCompulsoryEduService.list(queryWrapper);
		 List<SubsidyCompulsoryEdu> exportList = null;

		 // 过滤选中数据
		 String selections = request.getParameter("selections");
		 if (oConvertUtils.isNotEmpty(selections)) {
			 List<String> selectionList = Arrays.asList(selections.split(","));
			 exportList = pageList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
		 } else {
			 exportList = pageList;
		 }

		 // Step.3 AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 //此处设置的filename无效,前端会重更新设置一下
		 mv.addObject(NormalExcelConstants.FILE_NAME, "义务教育扶持信息表");
		 mv.addObject(NormalExcelConstants.CLASS, SubsidyCompulsoryEdu.class);
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("义务教育扶持信息表报表", "导出人:" + sysUser.getRealname(), "义务教育扶持信息表"));
		 mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
		 return mv;
    }

    /**
     * 导入
     * @return
     */
    @RequestMapping(value = "/importSubsidyCompulsoryEdu/{mainId}")
    public Result<?> importSubsidyCompulsoryEdu(HttpServletRequest request, HttpServletResponse response, @PathVariable("mainId") String mainId) {
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
				 List<SubsidyCompulsoryEdu> list = ExcelImportUtil.importExcel(file.getInputStream(), SubsidyCompulsoryEdu.class, params);
				 for (SubsidyCompulsoryEdu temp : list) {
                    temp.setIdCard(mainId);
				 }
				 long start = System.currentTimeMillis();
				 subsidyCompulsoryEduService.saveBatch(list);
				 log.info("消耗时间" + (System.currentTimeMillis() - start) + "毫秒");
				 return Result.OK("文件导入成功！数据行数：" + list.size());
			 } catch (Exception e) {
				 log.error(e.getMessage(), e);
				 return Result.error("文件导入失败:" + e.getMessage());
			 } finally {
				 try {
					 file.getInputStream().close();
				 } catch (IOException e) {
					 e.printStackTrace();
				 }
			 }
		 }
		 return Result.error("文件导入失败！");
    }

    /*--------------------------------子表处理-义务教育扶持信息表-end----------------------------------------------*/




}
