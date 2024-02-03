package com.qfy.modules.subsidy.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qfy.modules.subsidy.entity.WaterTank;
import com.qfy.modules.subsidy.service.IWaterTankService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.aspect.annotation.PermissionData;
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
 * @Description: 集中供水水柜
 * @Author: lanls
 * @Date:   2022-08-26
 * @Version: V1.0
 */
@Api(tags="集中供水水柜")
@RestController
@RequestMapping("/subsidy/water")
@Slf4j
public class WaterTankController extends JeecgController<WaterTank, IWaterTankService> {
	@Autowired
	private IWaterTankService waterTankService;

	/**
	 * 分页列表查询
	 *
	 * @param waterTank
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "集中供水水柜-分页列表查询")
	@ApiOperation(value="集中供水水柜-分页列表查询", notes="集中供水水柜-分页列表查询")
	@GetMapping(value = "/list")
	@PermissionData(pageComponent = "family/subsidy/WaterTankList")
	public Result<IPage<WaterTank>> queryPageList(WaterTank waterTank,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<WaterTank> queryWrapper = QueryGenerator.initQueryWrapper(waterTank, req.getParameterMap());
		Page<WaterTank> page = new Page<WaterTank>(pageNo, pageSize);
		IPage<WaterTank> pageList = waterTankService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	/**
	 *   添加
	 *
	 * @param waterTank
	 * @return
	 */
	@AutoLog(value = "集中供水水柜-添加")
	@ApiOperation(value="集中供水水柜-添加", notes="集中供水水柜-添加")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody WaterTank waterTank) {
		waterTankService.save(waterTank);
		return Result.OK("添加成功！");
	}

	/**
	 *  编辑
	 *
	 * @param waterTank
	 * @return
	 */
	@AutoLog(value = "集中供水水柜-编辑")
	@ApiOperation(value="集中供水水柜-编辑", notes="集中供水水柜-编辑")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody WaterTank waterTank) {
		waterTankService.updateById(waterTank);
		return Result.OK("编辑成功!");
	}

	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "集中供水水柜-通过id删除")
	@ApiOperation(value="集中供水水柜-通过id删除", notes="集中供水水柜-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		waterTankService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "集中供水水柜-批量删除")
	@ApiOperation(value="集中供水水柜-批量删除", notes="集中供水水柜-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.waterTankService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "集中供水水柜-通过id查询")
	@ApiOperation(value="集中供水水柜-通过id查询", notes="集中供水水柜-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<WaterTank> queryById(@RequestParam(name="id",required=true) String id) {
		WaterTank waterTank = waterTankService.getById(id);
		if(waterTank==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(waterTank);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param waterTank
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, WaterTank waterTank) {
        return super.exportXls(request, waterTank, WaterTank.class, "集中供水水柜");
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
        return super.importExcel(request, response, WaterTank.class);
    }

}
