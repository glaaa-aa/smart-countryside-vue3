package com.qfy.modules.smartform.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qfy.modules.smartform.entity.CheckItemGroup;
import com.qfy.modules.smartform.service.ICheckItemGroupService;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

 /**
 * @Description: 检查项分组-容器
 * @Author: jeecg-boot
 * @Date:   2022-04-08
 * @Version: V1.0
 */
@Api(tags="检查项分组-容器")
@RestController
@RequestMapping("/smartform/checkItemGroup")
@Slf4j
public class CheckItemGroupController extends JeecgController<CheckItemGroup, ICheckItemGroupService> {
	@Autowired
	private ICheckItemGroupService checkItemGroupService;

	/**
	 * 分页列表查询
	 *
	 * @param checkItemGroup
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "检查项分组-容器-分页列表查询")
	@ApiOperation(value="检查项分组-容器-分页列表查询", notes="检查项分组-容器-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(CheckItemGroup checkItemGroup,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<CheckItemGroup> queryWrapper = QueryGenerator.initQueryWrapper(checkItemGroup, req.getParameterMap());
		Page<CheckItemGroup> page = new Page<CheckItemGroup>(pageNo, pageSize);
		IPage<CheckItemGroup> pageList = checkItemGroupService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	/**
	 *   添加
	 *
	 * @param checkItemGroup
	 * @return
	 */
	@AutoLog(value = "检查项分组-容器-添加")
	@ApiOperation(value="检查项分组-容器-添加", notes="检查项分组-容器-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody CheckItemGroup checkItemGroup) {
		if (null == checkItemGroup.getParentid()){
			checkItemGroup.setParentid(0);
		}
		checkItemGroupService.save(checkItemGroup);
		return Result.OK("添加成功！");
	}

	/**
	 *  编辑
	 *
	 * @param checkItemGroup
	 * @return
	 */
	@AutoLog(value = "检查项分组-容器-编辑")
	@ApiOperation(value="检查项分组-容器-编辑", notes="检查项分组-容器-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody CheckItemGroup checkItemGroup) {
		checkItemGroupService.updateById(checkItemGroup);
		return Result.OK("编辑成功!");
	}

	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "检查项分组-容器-通过id删除")
	@ApiOperation(value="检查项分组-容器-通过id删除", notes="检查项分组-容器-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		checkItemGroupService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "检查项分组-容器-批量删除")
	@ApiOperation(value="检查项分组-容器-批量删除", notes="检查项分组-容器-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.checkItemGroupService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "检查项分组-容器-通过id查询")
	@ApiOperation(value="检查项分组-容器-通过id查询", notes="检查项分组-容器-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		CheckItemGroup checkItemGroup = checkItemGroupService.getById(id);
		if(checkItemGroup==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(checkItemGroup);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param checkItemGroup
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, CheckItemGroup checkItemGroup) {
        return super.exportXls(request, checkItemGroup, CheckItemGroup.class, "检查项分组-容器");
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
        return super.importExcel(request, response, CheckItemGroup.class);
    }

}
