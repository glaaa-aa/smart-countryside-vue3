package com.qfy.modules.family.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qfy.modules.family.entity.FamilyLog;
import com.qfy.modules.family.service.IFamilyLogService;
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
 * @Description: qfy_family_log
 * @Author: jeecg-boot
 * @Date:   2022-06-02
 * @Version: V1.0
 */
@Api(tags="qfy_family_log")
@RestController
@RequestMapping("/family/familyLog")
@Slf4j
public class FamilyLogController extends JeecgController<FamilyLog, IFamilyLogService> {
	@Autowired
	private IFamilyLogService familyLogService;
	
	/**
	 * 分页列表查询
	 *
	 * @param familyLog
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "qfy_family_log-分页列表查询")
	@ApiOperation(value="qfy_family_log-分页列表查询", notes="qfy_family_log-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<FamilyLog>> queryPageList(FamilyLog familyLog,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<FamilyLog> queryWrapper = QueryGenerator.initQueryWrapper(familyLog, req.getParameterMap());
		Page<FamilyLog> page = new Page<FamilyLog>(pageNo, pageSize);
		IPage<FamilyLog> pageList = familyLogService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param familyLog
	 * @return
	 */
	@AutoLog(value = "qfy_family_log-添加")
	@ApiOperation(value="qfy_family_log-添加", notes="qfy_family_log-添加")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody FamilyLog familyLog) {
		familyLogService.save(familyLog);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param familyLog
	 * @return
	 */
	@AutoLog(value = "qfy_family_log-编辑")
	@ApiOperation(value="qfy_family_log-编辑", notes="qfy_family_log-编辑")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody FamilyLog familyLog) {
		familyLogService.updateById(familyLog);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "qfy_family_log-通过id删除")
	@ApiOperation(value="qfy_family_log-通过id删除", notes="qfy_family_log-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		familyLogService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "qfy_family_log-批量删除")
	@ApiOperation(value="qfy_family_log-批量删除", notes="qfy_family_log-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.familyLogService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "qfy_family_log-通过id查询")
	@ApiOperation(value="qfy_family_log-通过id查询", notes="qfy_family_log-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<FamilyLog> queryById(@RequestParam(name="id",required=true) String id) {
		FamilyLog familyLog = familyLogService.getById(id);
		if(familyLog==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(familyLog);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param familyLog
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, FamilyLog familyLog) {
        return super.exportXls(request, familyLog, FamilyLog.class, "qfy_family_log");
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
        return super.importExcel(request, response, FamilyLog.class);
    }

}
