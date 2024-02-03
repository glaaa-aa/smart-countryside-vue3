package com.qfy.modules.commons.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qfy.modules.commons.entity.CmnDict;
import com.qfy.modules.commons.service.ICmnDictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.vo.DictModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: 关联类数据字典
 * @Author: jeecg-boot
 * @Date:   2023-04-14
 * @Version: V1.0
 */
@Api(tags="关联类数据字典")
@RestController
@RequestMapping("/common/sysDict")
@Slf4j
public class CmnDictController extends JeecgController<CmnDict, ICmnDictService> {
	@Autowired
	private ICmnDictService sysDictService;

	/**
	 * 分页列表查询
	 *
	 * @param sysDict
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "关联类数据字典-分页列表查询")
	@ApiOperation(value="关联类数据字典-分页列表查询", notes="关联类数据字典-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<CmnDict>> queryPageList(CmnDict sysDict,
												@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
												@RequestParam(name="pageSize", defaultValue="20") Integer pageSize,
												HttpServletRequest req) {

//		if(StringUtils.isEmpty(sysDict.getRefSrc())){
//			return Result.error("参数为空");
//		}
		if(StringUtils.isEmpty(sysDict.getRefData())){
			sysDict.setRefData("0");
		}
		QueryWrapper<CmnDict> queryWrapper = QueryGenerator.initQueryWrapper(sysDict, req.getParameterMap());
		Page<CmnDict> page = new Page<CmnDict>(pageNo, pageSize);
		IPage<CmnDict> pageList = sysDictService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	/**
	 * 获取字典数据 【接口签名验证】
	 * @param refSrc 字典code
	 * @param refData
	 * @return
	 */
	@RequestMapping(value = "/dictByRef", method = RequestMethod.GET)
	public Result<List<DictModel>> getDictItems(@RequestParam("refSrc") String refSrc, @RequestParam(value = "refData",required = false) String refData, HttpServletRequest request) {
		try {
			if(StringUtils.isEmpty(refSrc)){
				return Result.error("参数为空");
			}
			if(StringUtils.isEmpty(refData)){
				refData = "0";
			}
			List<DictModel> ls = sysDictService.dictByRef(refSrc,refData);
			return Result.ok(ls);
		} catch (Exception e) {
			log.error(e.getMessage(), e);

			return Result.error("请求错误");
		}
	}




	/**
	 *   添加
	 *
	 * @param sysDict
	 * @return
	 */
	@AutoLog(value = "关联类数据字典-添加")
	@ApiOperation(value="关联类数据字典-添加", notes="关联类数据字典-添加")
	//@RequiresPermissions("org.jeecg.modules.demo:qfy_system_dict:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody CmnDict sysDict) {
		if(StringUtils.isEmpty(sysDict.getRefData())){
			sysDict.setRefData("0");
		}
		long num = sysDictService.countDict(sysDict);
		if (num != 0) {
			// 该值不可用
			log.info("该值不可用，系统中已存在！");
			return Result.error("该值不可用，系统中已存在！");
		}
		if(sysDict.getStatus() == null){
			sysDict.setStatus(1);
		}
		sysDictService.save(sysDict);
		return Result.OK("添加成功！");
	}

	/**
	 *  编辑
	 *
	 * @param sysDict
	 * @return
	 */
	@AutoLog(value = "关联类数据字典-编辑")
	@ApiOperation(value="关联类数据字典-编辑", notes="关联类数据字典-编辑")
	//@RequiresPermissions("org.jeecg.modules.demo:qfy_system_dict:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody CmnDict sysDict) {
		if(StringUtils.isEmpty(sysDict.getRefData())){
			sysDict.setRefData("0");
		}

		long num = sysDictService.countDict(sysDict);
		if (num != 0) {
			// 该值不可用
			log.info("该值不可用，系统中已存在！");
			return Result.error("该值不可用，系统中已存在！");
		}
		if(sysDict.getStatus() == null){
			sysDict.setStatus(1);
		}
		sysDictService.updateById(sysDict);
		return Result.OK("编辑成功!");
	}

	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "关联类数据字典-通过id删除")
	@ApiOperation(value="关联类数据字典-通过id删除", notes="关联类数据字典-通过id删除")
	//@RequiresPermissions("org.jeecg.modules.demo:qfy_system_dict:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		sysDictService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "关联类数据字典-批量删除")
	@ApiOperation(value="关联类数据字典-批量删除", notes="关联类数据字典-批量删除")
	//@RequiresPermissions("org.jeecg.modules.demo:qfy_system_dict:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.sysDictService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "关联类数据字典-通过id查询")
	@ApiOperation(value="关联类数据字典-通过id查询", notes="关联类数据字典-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<CmnDict> queryById(@RequestParam(name="id",required=true) String id) {
		CmnDict sysDict = sysDictService.getById(id);
		if(sysDict==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(sysDict);
	}

	/**
	 * 校验数据是否在系统中是否存在
	 *
	 * @return
	 */
	@RequestMapping(value = "/doDuplicateCheck", method = RequestMethod.GET)
	@ApiOperation("重复校验接口")
	public Result<String> doDuplicateCheck(CmnDict sysDict, HttpServletRequest request) {

		// update-begin-author:【online报表】oracle 操作问题 录入弹框啥都不填直接保存 ①编码不是应该提示必填么？②报错也应该是具体文字提示，不是后台错误日志
		if(StringUtils.isEmpty(sysDict.getItemValue())){
			Result rs = new Result();
			rs.setCode(500);
			rs.setSuccess(true);
			rs.setMessage("数据为空,不作处理！");
			return rs;
		}
		if(StringUtils.isEmpty(sysDict.getRefSrc())){
			Result rs = new Result();
			rs.setCode(500);
			rs.setSuccess(true);
			rs.setMessage("参数为空,不作处理！");
			return rs;
		}

		if(StringUtils.isEmpty(sysDict.getRefData())){
			sysDict.setRefData("0");
		}

		long num = sysDictService.countDict(sysDict);

		if (num == 0) {
			// 该值可用
			return Result.ok("该值可用！");
		} else {
			 // 该值不可用
			log.info("该值不可用，系统中已存在！");
			return Result.error("该值不可用，系统中已存在！");
		}
	}



    /**
    * 导出excel
    *
    * @param request
    * @param sysDict
    */
    //@RequiresPermissions("org.jeecg.modules.demo:qfy_system_dict:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, CmnDict sysDict) {
        return super.exportXls(request, sysDict, CmnDict.class, "关联类数据字典");
    }

    /**
	* 通过excel导入数据
    * @param request
    * @param response
    * @return
    */
    //@RequiresPermissions("qfy_system_dict:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, CmnDict.class);
    }

}
