package com.qfy.modules.family.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qfy.modules.family.entity.FamilyBaseHouse;
import com.qfy.modules.family.service.IFamilyBaseHouseService;
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
 * @Description: 住房安全
 * @Author: jeecg-boot
 * @Date:   2022-11-19
 * @Version: V1.0
 */
@Api(tags="住房安全")
@RestController
@RequestMapping("/family/house")
@Slf4j
public class FamilyBaseHouseController extends JeecgController<FamilyBaseHouse, IFamilyBaseHouseService> {
	@Autowired
	private IFamilyBaseHouseService familyBaseHouseService;

	/**
	 * 分页列表查询
	 *
	 * @param familyBaseHouse
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "住房安全-分页列表查询")
	@ApiOperation(value="住房安全-分页列表查询", notes="住房安全-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<FamilyBaseHouse>> queryPageList(FamilyBaseHouse familyBaseHouse,
														@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
														@RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
														HttpServletRequest req) {
		QueryWrapper<FamilyBaseHouse> queryWrapper = QueryGenerator.initQueryWrapper(familyBaseHouse, req.getParameterMap());
		Page<FamilyBaseHouse> page = new Page<FamilyBaseHouse>(pageNo, pageSize);
		IPage<FamilyBaseHouse> pageList = familyBaseHouseService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	/**
	 *   添加
	 *
	 * @param familyBaseHouse
	 * @return
	 */
	@AutoLog(value = "住房安全-添加")
	@ApiOperation(value="住房安全-添加", notes="住房安全-添加")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody FamilyBaseHouse familyBaseHouse) {
		familyBaseHouseService.save(familyBaseHouse);
		return Result.OK("添加成功！");
	}

	/**
	 *  编辑
	 *
	 * @param familyBaseHouse
	 * @return
	 */
	@AutoLog(value = "住房安全-编辑")
	@ApiOperation(value="住房安全-编辑", notes="住房安全-编辑")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody FamilyBaseHouse familyBaseHouse) {
		familyBaseHouseService.updateById(familyBaseHouse);
		return Result.OK("编辑成功!");
	}

	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "住房安全-通过id删除")
	@ApiOperation(value="住房安全-通过id删除", notes="住房安全-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		familyBaseHouseService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "住房安全-批量删除")
	@ApiOperation(value="住房安全-批量删除", notes="住房安全-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.familyBaseHouseService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "住房安全-通过id查询")
	@ApiOperation(value="住房安全-通过id查询", notes="住房安全-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<FamilyBaseHouse> queryById(@RequestParam(name="id",required=true) String id) {
		FamilyBaseHouse familyBaseHouse = familyBaseHouseService.getById(id);
		if(familyBaseHouse==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(familyBaseHouse);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param familyBaseHouse
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, FamilyBaseHouse familyBaseHouse) {
        return super.exportXls(request, familyBaseHouse, FamilyBaseHouse.class, "住房安全");
    }

	 /**
	  * 导出示例数据
	  *
	  * @param request
	  * @param house
	  */
	 @RequestMapping(value = "/example/exportXls")
	 public ModelAndView exampleExportXls(HttpServletRequest request, FamilyBaseHouse house) {
		 List<FamilyBaseHouse> houseList = new ArrayList<>(1);
		 house.setFamilyNumber("100xx");
		 house.setIdCard("451228xxxxx");
		 house.setIsReform(1);
		 house.setReformAmount("25000");
		 house.setReformDate("2020-05-20");
		 house.setYear("2022");
		 house.setZfArea(70d);
		 house.setZfFloor(1);
		 house.setZfLevel(4);
		 house.setName("张三");
		 house.setS1("2020-10-12");
		 house.setS2("1");
		 houseList.add(house);
		 return super.exampleExportXls(houseList, FamilyBaseHouse.class, "住房安全");
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
        return super.importExcel(request, response, FamilyBaseHouse.class);
    }

}
