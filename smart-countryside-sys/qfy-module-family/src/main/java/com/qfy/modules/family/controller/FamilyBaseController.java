package com.qfy.modules.family.controller;

import cn.hutool.core.thread.ThreadUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qfy.modules.city3d.po.Address;
import com.qfy.modules.city3d.service.AddressService;
import com.qfy.modules.family.entity.FamilyBase;
import com.qfy.modules.family.entity.FamilyTemp;
import com.qfy.modules.family.model.FamilyBasePage;
import com.qfy.modules.family.model.FamilyBaseResult;
import com.qfy.modules.family.service.IFamilyBaseService;
import com.qfy.modules.family.service.IFamilyIntegralService;
import com.qfy.modules.family.service.IFamilyTempService;
import com.qfy.modules.family.util.SerialNumberID;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.aspect.annotation.PermissionData;
import org.jeecg.common.constant.CommonConstant;
import org.jeecg.common.exception.JeecgBootException;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ImportParams;
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
 * @Description: qfy_family_base
 * @Author: jeecg-boot
 * @Date:   2022-06-02
 * @Version: V1.0
 */
@Api(tags="住户管理")
@RestController
@RequestMapping("/family/familyBase")
@Slf4j
public class FamilyBaseController extends JeecgController<FamilyBase, IFamilyBaseService> {

	public static final Logger logger = LoggerFactory.getLogger(FamilyBaseController.class);
	@Resource
	private IFamilyBaseService familyBaseService;
	@Resource
	private AddressService addressService;
	@Resource
	private IFamilyTempService familyTempService;
	@Resource
	private IFamilyIntegralService integralService;

	/**
	 * 分页列表查询
	 *
	 * @param familyBase
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "住户管理-分页列表查询")
	@ApiOperation(value="住户管理-分页列表查询", notes="住户管理-分页列表查询")
	@GetMapping(value = "/list")
//	@PermissionData(pageComponent = "family/base/FamilyBaseList")
	@PermissionData
	public Result<IPage<FamilyBase>> queryPageList(FamilyBase familyBase,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<FamilyBase> queryWrapper = QueryGenerator.initQueryWrapper(familyBase, req.getParameterMap());
		Page<FamilyBase> page = new Page<>(pageNo, pageSize);
		String familyNumbers = req.getParameter("familyNumbers");
		String orgCode = req.getParameter("orgCode");
		if (StringUtils.isNotBlank(familyNumbers)){
			String[] numbers = familyNumbers.split(",");
			queryWrapper.lambda().in(FamilyBase::getFamilyNumber, Arrays.asList(numbers));
		}

		if (StringUtils.isNotBlank(orgCode)){
			queryWrapper.or(w -> {
				w.lambda().eq(FamilyBase::getAturalCode, orgCode);
				w.lambda().or();
				w.lambda().eq(FamilyBase::getS1, orgCode);
			});
		}
		IPage<FamilyBase> pageList = familyBaseService.page(page, queryWrapper);
		if (CollectionUtils.isNotEmpty(pageList.getRecords())){
			List<String> idList = pageList.getRecords().stream().map(FamilyBase::getAddId).collect(Collectors.toList());
			List<String> ids = idList.stream().filter(id -> {
				return StringUtils.isNotBlank(id);
			}).collect(Collectors.toList());
			if (CollectionUtils.isNotEmpty(ids)){
				QueryWrapper<Address> wrapper = new QueryWrapper<>();
				wrapper.lambda().in(Address::getAddId, ids);
				List<Address> addressList = addressService.list(wrapper);
				if (CollectionUtils.isNotEmpty(addressList)){
					for (FamilyBase family : pageList.getRecords()) {
						for (Address address : addressList) {
							if (StringUtils.equals(address.getAddId(), family.getAddId())){
								family.setMapAddress(address);
							}
						}
					}
				}
			}
		}
		return Result.OK(pageList);
	}

	/**
	 * 获取家庭户基本信息
	 * @param id
	 * @return: org.jeecg.common.api.vo.Result<?>
	 * @author: lanls
	 * @date: 2022/8/8 15:59
	 * @description:
	 */
	@GetMapping("/getFamilyBaseById")
	public Result getFamilyBaseById(@RequestParam("id") String id){
		FamilyBaseResult result = familyBaseService.getFamilyBaseById(id);
		return Result.OK(result);
	}

	/**
	 * 户信息添加
	 * @param familyBase
	 * @return
	 */
	@AutoLog(value = "户信息-添加")
	@ApiOperation(value="住户管理-添加", notes="住户管理-添加")
	@PostMapping(value = "/addBase")
	public Result<String> addBase(@RequestBody FamilyBase familyBase) {
		try {
			if (StringUtils.isBlank(familyBase.getTuopinYear())){
				familyBase.setTuopinYear("");
			}
			familyBaseService.saveFamilyBase(familyBase);
			if(StringUtils.isEmpty(familyBase.getId())){
				ThreadUtil.execute(() -> {
					List<FamilyBase> list = new ArrayList<>(1);
					list.add(familyBase);
					Map<String, Object> params = new HashMap<>();
					params.put("familyList", list);
					integralService.sync(params);
				});
			}
			return Result.OK("操作成功！");
		}catch (Exception e){
			log.error("保存户信息异常：", e);
			return Result.error("操作失败");
		}

	}


	/**
	 *添加
	 * @param familyBasePage
	 * @return
	 */
	@AutoLog(value = "住户管理-添加")
	@ApiOperation(value="住户管理-添加", notes="住户管理-添加")
	@PostMapping(value = "/addDetail")
	public Result<String> addDetail(@RequestBody FamilyBasePage familyBasePage) {
		try {
			familyBaseService.saveMain(familyBasePage);
			return Result.OK("编辑成功！");
		}catch (JeecgBootException e){
			log.info(e.getMessage());
			return Result.error("操作失败");
		}

	}

	/**
	 *  编辑
	 *
	 * @param familyBasePage
	 * @return
	 */
	@AutoLog(value = "住户管理-编辑")
	@ApiOperation(value="住户管理-编辑", notes="住户管理-编辑")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody FamilyBasePage familyBasePage) {
		FamilyBase familyBase = new FamilyBase();
//		BeanUtils.copyProperties(familyBasePage, familyBase);
		familyBaseService.updateCopyMain(familyBasePage);
		return Result.OK("编辑成功!");
	}

	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "住户管理-通过id删除")
	@ApiOperation(value="住户管理-通过id删除", notes="住户管理-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id") String id) {
		familyBaseService.delMain(id);
		return Result.OK("删除成功!");
	}

	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "住户管理-批量删除")
	@ApiOperation(value="住户管理-批量删除", notes="住户管理-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.familyBaseService.delBatchMain(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "住户管理-通过id查询")
	@ApiOperation(value="户管理-通过id查询", notes="户管理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<FamilyBase> queryById(@RequestParam(name="id",required=true) String id) {
		FamilyBase familyBase = familyBaseService.getById(id);
		if(familyBase==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(familyBase);
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
//	@ApiOperation(value="户管理-通过id查询综合查询", notes="住户管理-通过id查询综合查询")
//	@GetMapping(value = "/queryFamliyById")
//	public Result<FamilyBasePage> queryFamliyById(@RequestParam(name="id",required=true) String id) {
//		FamilyBasePage familyPage = familyBaseService.getFamilyById(id);
//		if(familyPage==null) {
//			return Result.error("未找到对应数据");
//		}
//		return Result.OK(familyPage);
//	}

	/**
	 * 通过id查询
	 * @param familyNumber
	 * @param year;
	 * @return
	 */
	@ApiOperation(value="户管理-通过id查询综合查询", notes="住户管理-通过id查询综合查询")
	@GetMapping(value = "/queryByFamilyAndYear")
	public Result<FamilyBasePage> queryByFamilyAndYear(@RequestParam(name="familyNumber",required=true) String familyNumber,String year) {
		FamilyBasePage familyPage = familyBaseService.getFamilyByNumberAndYear(familyNumber,year);
		if(familyPage == null || familyPage.getFamilyBase() == null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(familyPage);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param familyBase
    */
	@ApiOperation(value="导出家庭户基本信息", notes="导出家庭户基本信息")
	@AutoLog(value = "导出家庭户基本信息",operateType = 6)
    @RequestMapping(value = "/exportXls")
	@PermissionData
    public ModelAndView exportXls(HttpServletRequest request, FamilyBase familyBase) {
		return super.exportXls(request,familyBase,FamilyBase.class,"家庭户基本信息");

    }

	/**
	 * 导出excel
	 *
	 * @param request
	 * @param familyBase
	 */
	@ApiOperation(value="导出家庭户详情信息", notes="导出家庭户详情信息")
	@AutoLog(value = "导出家庭户详情信息",operateType = 6)
	@RequestMapping(value = "/detailExportXls")
	@PermissionData
	public ModelAndView detailExportXls(HttpServletRequest request, FamilyBase familyBase) {
		return familyBaseService.detailExportXls(request,familyBase);
	}

    /**
      * 通过excel导入数据
    * @param request
    * @param response
    * @return
    */
	@ApiOperation(value="导入家庭户基本信息", notes="导入家庭户基本信息")
	@AutoLog(value = "导入家庭户基本信息",operateType = 5)
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
		return super.importExcel(request, response, FamilyBase.class);
    }

	@Override
	public List<FamilyBase> beforeImport(List<FamilyBase> list) {
		List<FamilyBase> newList = new ArrayList<>(list.size());
		for (FamilyBase base : list) {
			if (StringUtils.isBlank(base.getFamilyNumber())){
				String familyNumber = SerialNumberID.nextNO();
				base.setFamilyNumber(familyNumber);
			}

			base.setDelFlag(CommonConstant.DEL_FLAG_0);
			base.setTotalPerson(null == base.getTotalPerson() ? 1 : base.getTotalPerson() );
			base.setTotalInSchool(null == base.getTotalInSchool() ? 0 : base.getTotalInSchool());
			base.setTotalWorker(null == base.getTotalWorker() ? 0 : base.getTotalWorker());
			newList.add(base);
		}

		return newList;
	}

	@Override
	public void afterImport(List<FamilyBase> list) {
		ThreadUtil.execute(() -> {
			Map<String, Object> params = new HashMap<>();
			params.put("familyList", list);
			integralService.sync(params);
		});
	}

	/**
	 * 通过excel导入数据到临时表,再批量更新
	 *
	 * @param request
	 * @return
	 */
	@AutoLog(value = "批量更新家庭户基本信息",operateType = 3)
	@RequestMapping(value = "/batchUpdate", method = RequestMethod.POST)
	public Result batchUpdate(HttpServletRequest request) {

		LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		MultipartFile file = null;
		try {
			// 获取上传文件对象
			file = fileMap.get("file");
			ImportParams params = new ImportParams();
			params.setTitleRows(2);
			params.setHeadRows(1);
			params.setNeedSave(true);

			List<FamilyTemp> list = ExcelImportUtil.importExcel(file.getInputStream(), FamilyTemp.class, params);
			long start = System.currentTimeMillis();
			familyTempService.saveBatch(list);
			logger.info("消耗时间" + (System.currentTimeMillis() - start) + "毫秒");
			// 将数据从临时表批量更新到正式表
			familyTempService.batchUpdateByUsername(loginUser.getUsername());
			// 更新完成之后将临时表数据进行删除掉
			familyTempService.removeBatchByIds(list.stream().map(FamilyTemp::getId).collect(Collectors.toList()));
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
				if (null != file){
					file.getInputStream().close();
				}
			} catch (IOException e) {
				logger.error("关闭输入流异常：", e);
			}
		}
	}
}
