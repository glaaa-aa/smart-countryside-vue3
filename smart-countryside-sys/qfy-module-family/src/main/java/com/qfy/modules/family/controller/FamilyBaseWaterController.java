package com.qfy.modules.family.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qfy.modules.family.entity.FamilyBaseWater;
import com.qfy.modules.family.service.IFamilyBaseWaterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
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
 * @Description: 饮水安全
 * @Author: jeecg-boot
 * @Date:   2022-11-19
 * @Version: V1.0
 */
@Api(tags="饮水安全")
@RestController
@RequestMapping("/family/water")
@Slf4j
public class FamilyBaseWaterController extends JeecgController<FamilyBaseWater, IFamilyBaseWaterService> {
	@Autowired
	private IFamilyBaseWaterService familyBaseWaterService;

	/**
	 * 分页列表查询
	 *
	 * @param familyBaseWater
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "饮水安全-分页列表查询")
	@ApiOperation(value="饮水安全-分页列表查询", notes="饮水安全-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<FamilyBaseWater>> queryPageList(FamilyBaseWater familyBaseWater,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<FamilyBaseWater> queryWrapper = QueryGenerator.initQueryWrapper(familyBaseWater, req.getParameterMap());
		Page<FamilyBaseWater> page = new Page<FamilyBaseWater>(pageNo, pageSize);
		IPage<FamilyBaseWater> pageList = familyBaseWaterService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	/**
	 *   添加
	 *
	 * @param familyBaseWater
	 * @return
	 */
	@AutoLog(value = "饮水安全-添加")
	@ApiOperation(value="饮水安全-添加", notes="饮水安全-添加")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody FamilyBaseWater familyBaseWater) {
		familyBaseWaterService.save(familyBaseWater);
		return Result.OK("添加成功！");
	}

	/**
	 *  编辑
	 *
	 * @param familyBaseWater
	 * @return
	 */
	@AutoLog(value = "饮水安全-编辑")
	@ApiOperation(value="饮水安全-编辑", notes="饮水安全-编辑")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody FamilyBaseWater familyBaseWater) {
		familyBaseWaterService.updateById(familyBaseWater);
		return Result.OK("编辑成功!");
	}

	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "饮水安全-通过id删除")
	@ApiOperation(value="饮水安全-通过id删除", notes="饮水安全-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		familyBaseWaterService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "饮水安全-批量删除")
	@ApiOperation(value="饮水安全-批量删除", notes="饮水安全-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.familyBaseWaterService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "饮水安全-通过id查询")
	@ApiOperation(value="饮水安全-通过id查询", notes="饮水安全-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<FamilyBaseWater> queryById(@RequestParam(name="id",required=true) String id) {
		FamilyBaseWater familyBaseWater = familyBaseWaterService.getById(id);
		if(familyBaseWater==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(familyBaseWater);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param familyBaseWater
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, FamilyBaseWater familyBaseWater) {
        return super.exportXls(request, familyBaseWater, FamilyBaseWater.class, "饮水安全");
    }

	 /**
	  * 导出示例模板
	  *
	  * @param request
	  * @param water
	  */
	 @RequestMapping(value = "/example/exportXls")
	 public ModelAndView exampleExportXls(HttpServletRequest request, FamilyBaseWater water) {
		 List<FamilyBaseWater> waterList = new ArrayList<>(1);
		 water.setFamilyNumber("100xxx");
		 water.setIsWaterSafe(0);
		 water.setWaterSource(1);
		 water.setIdCard("451228xxxxx");
		 water.setIsSubsidy(1);
		 water.setName("张三");
		 water.setSubsidyAmount(70000d);
		 water.setSubsidyDate("2022-05-20");
		 water.setYear("2022");
		 waterList.add(water);
		 return super.exampleExportXls(waterList, FamilyBaseWater.class, "饮水安全");
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
        return super.importExcel(request, response, FamilyBaseWater.class);
    }

}
