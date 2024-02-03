package com.qfy.modules.subsidy.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qfy.modules.subsidy.entity.AssistUnit;
import com.qfy.modules.subsidy.service.IAssistUnitService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Arrays;
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
 * @Description: 帮扶单位
 * @Author: jeecg-boot
 * @Date:   2022-12-04
 * @Version: V1.0
 */
@Api(tags="帮扶单位")
@RestController
@RequestMapping("/family/assistUnit")
@Slf4j
public class AssistUnitController extends JeecgController<AssistUnit, IAssistUnitService> {
	@Autowired
	private IAssistUnitService assistUnitService;

	/**
	 * 分页列表查询
	 *
	 * @param assistUnit
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "帮扶单位-分页列表查询")
	@ApiOperation(value="帮扶单位-分页列表查询", notes="帮扶单位-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<AssistUnit>> queryPageList(AssistUnit assistUnit,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<AssistUnit> queryWrapper = QueryGenerator.initQueryWrapper(assistUnit, req.getParameterMap());
		Page<AssistUnit> page = new Page<AssistUnit>(pageNo, pageSize);
		IPage<AssistUnit> pageList = assistUnitService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	/**
	 *   添加
	 *
	 * @param assistUnit
	 * @return
	 */
	@AutoLog(value = "帮扶单位-添加")
	@ApiOperation(value="帮扶单位-添加", notes="帮扶单位-添加")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody AssistUnit assistUnit) {
		assistUnitService.save(assistUnit);
		return Result.OK("添加成功！");
	}

	/**
	 *  编辑
	 *
	 * @param assistUnit
	 * @return
	 */
	@AutoLog(value = "帮扶单位-编辑")
	@ApiOperation(value="帮扶单位-编辑", notes="帮扶单位-编辑")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody AssistUnit assistUnit) {
		assistUnitService.updateById(assistUnit);
		return Result.OK("编辑成功!");
	}

	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "帮扶单位-通过id删除")
	@ApiOperation(value="帮扶单位-通过id删除", notes="帮扶单位-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		assistUnitService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "帮扶单位-批量删除")
	@ApiOperation(value="帮扶单位-批量删除", notes="帮扶单位-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.assistUnitService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "帮扶单位-通过id查询")
	@ApiOperation(value="帮扶单位-通过id查询", notes="帮扶单位-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<AssistUnit> queryById(@RequestParam(name="id",required=true) String id) {
		AssistUnit assistUnit = assistUnitService.getById(id);
		if(assistUnit==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(assistUnit);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param assistUnit
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, AssistUnit assistUnit) {
        return super.exportXls(request, assistUnit, AssistUnit.class, "帮扶单位");
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
        return super.importExcel(request, response, AssistUnit.class);
    }

}
