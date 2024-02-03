package com.qfy.modules.dissension.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qfy.modules.dissension.entity.DissensionRecords;
import com.qfy.modules.dissension.service.IDissensionRecordsService;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.apache.shiro.authz.annotation.RequiresPermissions;

 /**
 * @Description: 矛盾纠纷处理记录表
 * @Author: jeecg-boot
 * @Date:   2023-12-26
 * @Version: V1.0
 */
@Api(tags="矛盾纠纷处理记录表")
@RestController
@RequestMapping("/dissension/dissensionRecords")
@Slf4j
public class DissensionRecordsController extends JeecgController<DissensionRecords, IDissensionRecordsService> {

	@Autowired
	private IDissensionRecordsService dissensionRecordsService;

	/**
	 * 分页列表查询
	 *
	 * @param dissensionRecords
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "矛盾纠纷处理记录表-分页列表查询")
	@ApiOperation(value="矛盾纠纷处理记录表-分页列表查询", notes="矛盾纠纷处理记录表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<DissensionRecords>> queryPageList(DissensionRecords dissensionRecords,
														  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
														  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
														  HttpServletRequest req) {
		QueryWrapper<DissensionRecords> queryWrapper = QueryGenerator.initQueryWrapper(dissensionRecords, req.getParameterMap());
		Page<DissensionRecords> page = new Page<DissensionRecords>(pageNo, pageSize);
		IPage<DissensionRecords> pageList = dissensionRecordsService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	/**
	 *   添加
	 *
	 * @param dissensionRecords
	 * @return
	 */
	@AutoLog(value = "矛盾纠纷处理记录表-添加")
	@ApiOperation(value="矛盾纠纷处理记录表-添加", notes="矛盾纠纷处理记录表-添加")
	@RequiresPermissions("dissension:qfy_dissension_records:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody DissensionRecords dissensionRecords) {
		dissensionRecordsService.save(dissensionRecords);
		return Result.OK("添加成功！");
	}

	/**
	 *  编辑
	 *
	 * @param dissensionRecords
	 * @return
	 */
	@AutoLog(value = "矛盾纠纷处理记录表-编辑")
	@ApiOperation(value="矛盾纠纷处理记录表-编辑", notes="矛盾纠纷处理记录表-编辑")
	@RequiresPermissions("dissension:qfy_dissension_records:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody DissensionRecords dissensionRecords) {
		dissensionRecordsService.updateById(dissensionRecords);
		return Result.OK("编辑成功!");
	}

	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "矛盾纠纷处理记录表-通过id删除")
	@ApiOperation(value="矛盾纠纷处理记录表-通过id删除", notes="矛盾纠纷处理记录表-通过id删除")
	@RequiresPermissions("dissension:qfy_dissension_records:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		dissensionRecordsService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "矛盾纠纷处理记录表-批量删除")
	@ApiOperation(value="矛盾纠纷处理记录表-批量删除", notes="矛盾纠纷处理记录表-批量删除")
	@RequiresPermissions("dissension:qfy_dissension_records:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.dissensionRecordsService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}


	 /**
	  * 通过id查询
	  *
	  * @param id
	  * @return
	  */
	 //@AutoLog(value = "矛盾纠纷处理记录表-通过id查询")
	 @ApiOperation(value="矛盾纠纷处理记录表-通过id查询", notes="矛盾纠纷处理记录表-通过id查询")
	 @GetMapping(value = "/queryById")
	 public Result<DissensionRecords> queryById(@RequestParam(name="id",required=true) String id) {
		 DissensionRecords dissensionRecords = dissensionRecordsService.getById(id);
		 if(dissensionRecords==null) {
			 return Result.error("未找到对应数据");
		 }
		 return Result.OK(dissensionRecords);
	 }

    /**
    * 导出excel
    *
    * @param request
    * @param dissensionRecords
    */
    @RequiresPermissions("dissension:qfy_dissension_records:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, DissensionRecords dissensionRecords) {
        return super.exportXls(request, dissensionRecords, DissensionRecords.class, "矛盾纠纷处理记录表");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequiresPermissions("dissension:qfy_dissension_records:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, DissensionRecords.class);
    }

}
