package com.qfy.modules.dissension.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qfy.modules.dissension.entity.AndonRepairMoMan;
import com.qfy.modules.dissension.service.AndonRepairMoManService;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

/**
 * @Description: 纠纷负责处理人员表
 * @Author: jeecg-boot
 * @Date:   2023-12-28
 * @Version: V1.0
 */
@Api(tags="纠纷负责处理人员表")
@RestController
@RequestMapping("/dissension/dissensionResponsibleUser")
@Slf4j
public class AndonRepairMoManController extends JeecgController<AndonRepairMoMan, AndonRepairMoManService> {
	@Autowired
	private AndonRepairMoManService dissensionManService;
	
	/**
	 * 分页列表查询
	 *
	 * @param andonRepairMoMan
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "纠纷负责处理人员表-分页列表查询")
	@ApiOperation(value="纠纷负责处理人员表-分页列表查询", notes="纠纷负责处理人员表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<AndonRepairMoMan>> queryPageList(AndonRepairMoMan andonRepairMoMan,
														 @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
														 @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
														 HttpServletRequest req) {
		QueryWrapper<AndonRepairMoMan> queryWrapper = QueryGenerator.initQueryWrapper(andonRepairMoMan, req.getParameterMap());
		Page<AndonRepairMoMan> page = new Page<AndonRepairMoMan>(pageNo, pageSize);
		IPage<AndonRepairMoMan> pageList = dissensionManService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param andonRepairMoMan
	 * @return
	 */
	@AutoLog(value = "纠纷负责处理人员表-添加")
	@ApiOperation(value="纠纷负责处理人员表-添加", notes="纠纷负责处理人员表-添加")
//	@RequiresPermissions("dissension:qfy_dissension_responsible_user:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody AndonRepairMoMan andonRepairMoMan) {
		dissensionManService.save(andonRepairMoMan);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param andonRepairMoMan
	 * @return
	 */
	@AutoLog(value = "纠纷负责处理人员表-编辑")
	@ApiOperation(value="纠纷负责处理人员表-编辑", notes="纠纷负责处理人员表-编辑")
//	@RequiresPermissions("dissension:qfy_dissension_responsible_user:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody AndonRepairMoMan andonRepairMoMan) {
		dissensionManService.updateById(andonRepairMoMan);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "纠纷负责处理人员表-通过id删除")
	@ApiOperation(value="纠纷负责处理人员表-通过id删除", notes="纠纷负责处理人员表-通过id删除")
//	@RequiresPermissions("dissension:qfy_dissension_responsible_user:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		dissensionManService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "纠纷负责处理人员表-批量删除")
	@ApiOperation(value="纠纷负责处理人员表-批量删除", notes="纠纷负责处理人员表-批量删除")
//	@RequiresPermissions("dissension:qfy_dissension_responsible_user:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.dissensionManService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "纠纷负责处理人员表-通过id查询")
	@ApiOperation(value="纠纷负责处理人员表-通过id查询", notes="纠纷负责处理人员表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<AndonRepairMoMan> queryById(@RequestParam(name="id",required=true) String id) {
		AndonRepairMoMan andonRepairMoMan = dissensionManService.getById(id);
		if(andonRepairMoMan ==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(andonRepairMoMan);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param andonRepairMoMan
    */
//    @RequiresPermissions("dissension:qfy_dissension_responsible_user:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, AndonRepairMoMan andonRepairMoMan) {
        return super.exportXls(request, andonRepairMoMan, AndonRepairMoMan.class, "纠纷负责处理人员表");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
//    @RequiresPermissions("dissension:qfy_dissension_responsible_user:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, AndonRepairMoMan.class);
    }

}
