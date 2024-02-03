package com.qfy.modules.subsidy.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qfy.modules.subsidy.entity.AssistRel;
import com.qfy.modules.subsidy.service.IAssistRelService;
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
 * @Description: 帮扶结对关系
 * @Author: jeecg-boot
 * @Date:   2022-12-05
 * @Version: V1.0
 */
@Api(tags="帮扶结对关系")
@RestController
@RequestMapping("/family/assistRel")
@Slf4j
public class AssistRelController extends JeecgController<AssistRel, IAssistRelService> {
	@Autowired
	private IAssistRelService assistRelService;

	/**
	 * 分页列表查询
	 *
	 * @param assistRel
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "帮扶结对关系-分页列表查询")
	@ApiOperation(value="帮扶结对关系-分页列表查询", notes="帮扶结对关系-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<AssistRel>> queryPageList(AssistRel assistRel,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<AssistRel> queryWrapper = QueryGenerator.initQueryWrapper(assistRel, req.getParameterMap());
		Page<AssistRel> page = new Page<AssistRel>(pageNo, pageSize);
		IPage<AssistRel> pageList = assistRelService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	/**
	 *   添加
	 *
	 * @param assistRel
	 * @return
	 */
	@AutoLog(value = "帮扶结对关系-添加")
	@ApiOperation(value="帮扶结对关系-添加", notes="帮扶结对关系-添加")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody AssistRel assistRel) {
		assistRelService.save(assistRel);
		return Result.OK("添加成功！");
	}

	/**
	 *  编辑
	 *
	 * @param assistRel
	 * @return
	 */
	@AutoLog(value = "帮扶结对关系-编辑")
	@ApiOperation(value="帮扶结对关系-编辑", notes="帮扶结对关系-编辑")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody AssistRel assistRel) {
		assistRelService.updateById(assistRel);
		return Result.OK("编辑成功!");
	}

	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "帮扶结对关系-通过id删除")
	@ApiOperation(value="帮扶结对关系-通过id删除", notes="帮扶结对关系-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		assistRelService.removeById(id);
		return Result.OK("删除成功!");
	}

	 /**
	  *   通过id删除
	  *
	  * @param number
	  * @return
	  */
	 @AutoLog(value = "帮扶结对关系-通过id删除")
	 @ApiOperation(value="帮扶结对关系-通过id删除", notes="帮扶结对关系-通过id删除")
	 @DeleteMapping(value = "/delRel")
	 public Result<String> deleteRel(@RequestParam("familyNumber") String number,
									 @RequestParam("userId") String userId) {
		 UpdateWrapper<AssistRel> wrapper = new UpdateWrapper<>();
		 wrapper.lambda().eq(AssistRel::getFamilyNumber, number);
		 wrapper.lambda().eq(AssistRel::getUserId, userId);
		 assistRelService.remove(wrapper);
		 return Result.OK("删除成功!");
	 }

	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "帮扶结对关系-批量删除")
	@ApiOperation(value="帮扶结对关系-批量删除", notes="帮扶结对关系-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.assistRelService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "帮扶结对关系-通过id查询")
	@ApiOperation(value="帮扶结对关系-通过id查询", notes="帮扶结对关系-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<AssistRel> queryById(@RequestParam(name="id",required=true) String id) {
		AssistRel assistRel = assistRelService.getById(id);
		if(assistRel==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(assistRel);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param assistRel
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, AssistRel assistRel) {
        return super.exportXls(request, assistRel, AssistRel.class, "帮扶结对关系");
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
        return super.importExcel(request, response, AssistRel.class);
    }

}
