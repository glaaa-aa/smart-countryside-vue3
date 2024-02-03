package com.qfy.modules.smartform.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qfy.modules.smartform.entity.CheckItemType;
import com.qfy.modules.smartform.service.ICheckItemTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Arrays;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.YouBianCodeUtil;
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
 * @Description: 巡检项类型（检查项目类型）
 * @Author: jeecg-boot
 * @Date:   2022-04-08
 * @Version: V1.0
 */
@Api(tags="巡检项类型（检查项目类型）")
@RestController
@RequestMapping("/smartform/checkItemType")
@Slf4j
public class CheckItemTypeController extends JeecgController<CheckItemType, ICheckItemTypeService> {
	@Autowired
	private ICheckItemTypeService checkItemTypeService;

	/**
	 * 分页列表查询
	 *
	 * @param checkItemType
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "巡检项类型（检查项目类型）-分页列表查询")
	@ApiOperation(value="巡检项类型（检查项目类型）-分页列表查询", notes="巡检项类型（检查项目类型）-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(CheckItemType checkItemType,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<CheckItemType> queryWrapper = QueryGenerator.initQueryWrapper(checkItemType, req.getParameterMap());
		Page<CheckItemType> page = new Page<CheckItemType>(pageNo, pageSize);
		IPage<CheckItemType> pageList = checkItemTypeService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	/**
	 *   添加
	 *
	 * @param checkItemType
	 * @return
	 */
	@AutoLog(value = "巡检项类型（检查项目类型）-添加")
	@ApiOperation(value="巡检项类型（检查项目类型）-添加", notes="巡检项类型（检查项目类型）-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody CheckItemType checkItemType) {
		QueryWrapper<CheckItemType> wrapper = new QueryWrapper<>();
		wrapper.lambda().orderByDesc(CheckItemType::getCode);
		wrapper.last("limit 1");
		CheckItemType itemType = checkItemTypeService.getOne(wrapper);
		checkItemType.setCode(YouBianCodeUtil.getNextYouBianCode(Objects.isNull(itemType) ? null : itemType.getCode()));
		checkItemTypeService.save(checkItemType);
		return Result.OK("添加成功！");
	}

	/**
	 *  编辑
	 *
	 * @param checkItemType
	 * @return
	 */
	@AutoLog(value = "巡检项类型（检查项目类型）-编辑")
	@ApiOperation(value="巡检项类型（检查项目类型）-编辑", notes="巡检项类型（检查项目类型）-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody CheckItemType checkItemType) {
		checkItemTypeService.updateById(checkItemType);
		return Result.OK("编辑成功!");
	}

	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "巡检项类型（检查项目类型）-通过id删除")
	@ApiOperation(value="巡检项类型（检查项目类型）-通过id删除", notes="巡检项类型（检查项目类型）-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		checkItemTypeService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "巡检项类型（检查项目类型）-批量删除")
	@ApiOperation(value="巡检项类型（检查项目类型）-批量删除", notes="巡检项类型（检查项目类型）-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.checkItemTypeService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "巡检项类型（检查项目类型）-通过id查询")
	@ApiOperation(value="巡检项类型（检查项目类型）-通过id查询", notes="巡检项类型（检查项目类型）-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		CheckItemType checkItemType = checkItemTypeService.getById(id);
		if(checkItemType==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(checkItemType);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param checkItemType
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, CheckItemType checkItemType) {
        return super.exportXls(request, checkItemType, CheckItemType.class, "巡检项类型（检查项目类型）");
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
        return super.importExcel(request, response, CheckItemType.class);
    }

}
