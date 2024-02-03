package com.qfy.modules.commons.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qfy.modules.commons.entity.DataImport;
import com.qfy.modules.commons.service.IDataImportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.query.QueryGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

 /**
 * @Description: 数据导入
 * @Author: jeecg-boot
 * @Date:   2023-05-23
 * @Version: V1.0
 */
@Api(tags="数据导入")
@RestController
@RequestMapping("/commons/dataImport")
@Slf4j
public class DataImportController extends JeecgController<DataImport, IDataImportService> {
	@Autowired
	private IDataImportService dataImportService;

	/**
	 * 分页列表查询
	 * @param dataImport
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "数据导入-分页列表查询")
	@ApiOperation(value="数据导入-分页列表查询", notes="数据导入-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<DataImport>> queryPageList(DataImport dataImport,
												   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
												   @RequestParam(name="pageSize", defaultValue="20") Integer pageSize,
												   HttpServletRequest req) {
		QueryWrapper<DataImport> queryWrapper = QueryGenerator.initQueryWrapper(dataImport, req.getParameterMap());
		Page<DataImport> page = new Page<DataImport>(pageNo, pageSize);
		IPage<DataImport> pageList = dataImportService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	/**
	 *   添加
	 *
	 * @param dataImport
	 * @return
	 */
	@AutoLog(value = "数据导入-添加")
	@ApiOperation(value="数据导入-添加", notes="数据导入-添加")
	//@RequiresPermissions("org.jeecg.modules.demo:qfy_system_data_import:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody DataImport dataImport) {
		dataImportService.save(dataImport);
		return Result.OK("添加成功！");
	}

	/**
	 *  编辑
	 *
	 * @param dataImport
	 * @return
	 */
	@AutoLog(value = "数据导入-编辑")
	@ApiOperation(value="数据导入-编辑", notes="数据导入-编辑")
	//@RequiresPermissions("org.jeecg.modules.demo:qfy_system_data_import:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody DataImport dataImport) {
		dataImportService.updateById(dataImport);
		return Result.OK("编辑成功!");
	}

	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "数据导入-通过id删除")
	@ApiOperation(value="数据导入-通过id删除", notes="数据导入-通过id删除")
	//@RequiresPermissions("org.jeecg.modules.demo:qfy_system_data_import:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		dataImportService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "数据导入-批量删除")
	@ApiOperation(value="数据导入-批量删除", notes="数据导入-批量删除")
	//@RequiresPermissions("org.jeecg.modules.demo:qfy_system_data_import:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.dataImportService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

	/**
	 * 通过id查询
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "数据导入-通过id查询")
	@ApiOperation(value="数据导入-通过id查询", notes="数据导入-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<DataImport> queryById(@RequestParam(name="id",required=true) String id) {
		DataImport dataImport = dataImportService.getById(id);
		if(dataImport==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(dataImport);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param dataImport
    */
    //@RequiresPermissions("org.jeecg.modules.demo:qfy_system_data_import:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, DataImport dataImport) {
        return super.exportXls(request, dataImport, DataImport.class, "数据导入");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    //@RequiresPermissions("qfy_system_data_import:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, DataImport.class);
    }

}
