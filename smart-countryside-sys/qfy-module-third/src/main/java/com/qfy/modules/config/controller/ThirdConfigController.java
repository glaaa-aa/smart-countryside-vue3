package com.qfy.modules.config.controller;

import com.qfy.modules.config.entity.ThirdConfig;
import com.qfy.modules.config.service.IThirdConfigService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

 /**
 * @Description: 第三方对接参数设置
 * @Author: jeecg-boot
 * @Date:   2022-12-08
 * @Version: V1.0
 */
@Api(tags="第三方对接参数设置")
@RestController
@RequestMapping("/common/thirdConfig")
@Slf4j
public class ThirdConfigController extends JeecgController<ThirdConfig, IThirdConfigService> {
	@Autowired
	private IThirdConfigService thirdConfigService;
	/**
	 * 分页列表查询
	 * @param thirdConfig
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "第三方对接参数设置-分页列表查询")
//	@ApiOperation(value="第三方对接参数设置-分页列表查询", notes="第三方对接参数设置-分页列表查询")
//	@GetMapping(value = "/list")
//	public Result<IPage<ThirdConfig>> queryPageList(ThirdConfig thirdConfig,
//													@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
//													@RequestParam(name="pageSize", defaultValue="20") Integer pageSize,
//													HttpServletRequest req) {
//		QueryWrapper<ThirdConfig> queryWrapper = QueryGenerator.initQueryWrapper(thirdConfig, req.getParameterMap());
//		Page<ThirdConfig> page = new Page<ThirdConfig>(pageNo, pageSize);
//		IPage<ThirdConfig> pageList = thirdConfigService.page(page, queryWrapper);
//		return Result.OK(pageList);
//	}

	/**
	 *   添加
	 *
	 * @param thirdConfig
	 * @return
	 */
//	@AutoLog(value = "第三方对接参数设置-添加")
//	@ApiOperation(value="第三方对接参数设置-添加", notes="第三方对接参数设置-添加")
//	//@RequiresPermissions("org.jeecg.modules.demo:qfy_common_third_config:add")
//	@PostMapping(value = "/add")
//	public Result<String> add(@RequestBody ThirdConfig thirdConfig) {
//		thirdConfigService.save(thirdConfig);
//		return Result.OK("添加成功！");
//	}

	/**
	 *  编辑
	 *
	 * @param thirdConfig
	 * @return
	 */
//	@AutoLog(value = "第三方对接参数设置-编辑")
//	@ApiOperation(value="第三方对接参数设置-编辑", notes="第三方对接参数设置-编辑")
//	//@RequiresPermissions("org.jeecg.modules.demo:qfy_common_third_config:edit")
//	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
//	public Result<String> edit(@RequestBody ThirdConfig thirdConfig) {
//		thirdConfigService.updateById(thirdConfig);
//		return Result.OK("编辑成功!");
//	}

//	/**
//	 *   通过id删除
//	 *
//	 * @param id
//	 * @return
//	 */
//	@AutoLog(value = "第三方对接参数设置-通过id删除")
//	@ApiOperation(value="第三方对接参数设置-通过id删除", notes="第三方对接参数设置-通过id删除")
//	//@RequiresPermissions("org.jeecg.modules.demo:qfy_common_third_config:delete")
//	@DeleteMapping(value = "/delete")
//	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
//		thirdConfigService.removeById(id);
//		return Result.OK("删除成功!");
//	}

//	/**
//	 *  批量删除
//	 *
//	 * @param ids
//	 * @return
//	 */
//	@AutoLog(value = "第三方对接参数设置-批量删除")
//	@ApiOperation(value="第三方对接参数设置-批量删除", notes="第三方对接参数设置-批量删除")
//	//@RequiresPermissions("org.jeecg.modules.demo:qfy_common_third_config:deleteBatch")
//	@DeleteMapping(value = "/deleteBatch")
//	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
//		this.thirdConfigService.removeByIds(Arrays.asList(ids.split(",")));
//		return Result.OK("批量删除成功!");
//	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "第三方对接参数设置-通过id查询")
//	@ApiOperation(value="第三方对接参数设置-通过id查询", notes="第三方对接参数设置-通过id查询")
//	@GetMapping(value = "/queryById")
//	public Result<ThirdConfig> queryById(@RequestParam(name="id",required=true) String id) {
//		ThirdConfig cmnThirdConfig = thirdConfigService.getById(id);
//		if(cmnThirdConfig==null) {
//			return Result.error("未找到对应数据");
//		}
//		return Result.OK(cmnThirdConfig);
//	}

//    /**
//    * 导出excel
//    *
//    * @param request
//    * @param cmnThirdConfig
//    */
//    //@RequiresPermissions("org.jeecg.modules.demo:qfy_common_third_config:exportXls")
//    @RequestMapping(value = "/exportXls")
//    public ModelAndView exportXls(HttpServletRequest request, ThirdConfig thirdConfig) {
//        return super.exportXls(request, cmnThirdConfig, ThirdConfig.class, "第三方对接参数设置");
//    }

//    /**
//      * 通过excel导入数据
//    * @param request
//    * @param response
//    * @return
//    */
//    //@RequiresPermissions("qfy_common_third_config:importExcel")
//    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
//    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
//        return super.importExcel(request, response, ThirdConfig.class);
//    }

}
