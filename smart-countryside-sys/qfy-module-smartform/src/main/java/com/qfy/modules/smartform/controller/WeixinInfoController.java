package com.qfy.modules.smartform.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import com.qfy.modules.smartform.entity.WeixinInfo;
import com.qfy.modules.smartform.service.IWeixinInfoService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

 /**
 * @Description: 微信信息
 * @Author: jeecg-boot
 * @Date:   2022-04-08
 * @Version: V1.0
 */
@Api(tags="微信信息")
@RestController
@RequestMapping("/smartform/weixinInfo")
@Slf4j
public class WeixinInfoController extends JeecgController<WeixinInfo, IWeixinInfoService> {
	@Autowired
	private IWeixinInfoService weixinInfoService;
	
	/**
	 * 分页列表查询
	 *
	 * @param weixinInfo
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "微信信息-分页列表查询")
	@ApiOperation(value="微信信息-分页列表查询", notes="微信信息-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(WeixinInfo weixinInfo,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<WeixinInfo> queryWrapper = QueryGenerator.initQueryWrapper(weixinInfo, req.getParameterMap());
		Page<WeixinInfo> page = new Page<WeixinInfo>(pageNo, pageSize);
		IPage<WeixinInfo> pageList = weixinInfoService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param weixinInfo
	 * @return
	 */
	@AutoLog(value = "微信信息-添加")
	@ApiOperation(value="微信信息-添加", notes="微信信息-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody WeixinInfo weixinInfo) {
		weixinInfoService.save(weixinInfo);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param weixinInfo
	 * @return
	 */
	@AutoLog(value = "微信信息-编辑")
	@ApiOperation(value="微信信息-编辑", notes="微信信息-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody WeixinInfo weixinInfo) {
		weixinInfoService.updateById(weixinInfo);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "微信信息-通过id删除")
	@ApiOperation(value="微信信息-通过id删除", notes="微信信息-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		weixinInfoService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "微信信息-批量删除")
	@ApiOperation(value="微信信息-批量删除", notes="微信信息-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.weixinInfoService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "微信信息-通过id查询")
	@ApiOperation(value="微信信息-通过id查询", notes="微信信息-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		WeixinInfo weixinInfo = weixinInfoService.getById(id);
		if(weixinInfo==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(weixinInfo);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param weixinInfo
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, WeixinInfo weixinInfo) {
        return super.exportXls(request, weixinInfo, WeixinInfo.class, "微信信息");
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
        return super.importExcel(request, response, WeixinInfo.class);
    }

}
