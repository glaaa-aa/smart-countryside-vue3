package com.qfy.modules.subsidy.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qfy.modules.city3d.po.Address;
import com.qfy.modules.city3d.service.AddressService;
import com.qfy.modules.subsidy.entity.ElectronicCommerce;
import com.qfy.modules.subsidy.service.IElectronicCommerceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.aspect.annotation.PermissionData;
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
 * @Description: 电商营业点
 * @Author: lanls
 * @Date:   2022-08-26
 * @Version: V1.0
 */
@Api(tags="电商营业点")
@RestController
@RequestMapping("/subsidy/commerce")
@Slf4j
public class ElectronicCommerceController extends JeecgController<ElectronicCommerce, IElectronicCommerceService> {
	@Autowired
	private IElectronicCommerceService electronicCommerceService;

	 @Resource
	 private AddressService addressService;

	/**
	 * 分页列表查询
	 *
	 * @param electronicCommerce
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "电商营业点-分页列表查询")
	@ApiOperation(value="电商营业点-分页列表查询", notes="电商营业点-分页列表查询")
	@GetMapping(value = "/list")
	@PermissionData(pageComponent = "family/subsidy/ElectronicCommerceList")
	public Result<IPage<ElectronicCommerce>> queryPageList(ElectronicCommerce electronicCommerce,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<ElectronicCommerce> queryWrapper = QueryGenerator.initQueryWrapper(electronicCommerce, req.getParameterMap());
		Page<ElectronicCommerce> page = new Page<ElectronicCommerce>(pageNo, pageSize);
		IPage<ElectronicCommerce> pageList = electronicCommerceService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	/**
	 *   添加
	 *
	 * @param electronicCommerce
	 * @return
	 */
	@AutoLog(value = "电商营业点-添加")
	@ApiOperation(value="电商营业点-添加", notes="电商营业点-添加")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody ElectronicCommerce electronicCommerce) {
		electronicCommerceService.save(electronicCommerce);
		return Result.OK("添加成功！");
	}

	/**
	 *  编辑
	 *
	 * @param electronicCommerce
	 * @return
	 */
	@AutoLog(value = "电商营业点-编辑")
	@ApiOperation(value="电商营业点-编辑", notes="电商营业点-编辑")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody ElectronicCommerce electronicCommerce) {
		electronicCommerceService.updateById(electronicCommerce);
		return Result.OK("编辑成功!");
	}

	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "电商营业点-通过id删除")
	@ApiOperation(value="电商营业点-通过id删除", notes="电商营业点-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		electronicCommerceService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "电商营业点-批量删除")
	@ApiOperation(value="电商营业点-批量删除", notes="电商营业点-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.electronicCommerceService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "电商营业点-通过id查询")
	@ApiOperation(value="电商营业点-通过id查询", notes="电商营业点-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<ElectronicCommerce> queryById(@RequestParam(name="id",required=true) String id) {
		ElectronicCommerce electronicCommerce = electronicCommerceService.getById(id);
		if(electronicCommerce==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(electronicCommerce);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param electronicCommerce
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, ElectronicCommerce electronicCommerce) {
        return super.exportXls(request, electronicCommerce, ElectronicCommerce.class, "电商营业点");
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
        return super.importExcel(request, response, ElectronicCommerce.class);
    }


	 /**
	  * 分页查询电商网点及地图位置
	  *
	  * @param electronicCommerce
	  * @param pageNo
	  * @param pageSize
	  * @param req
	  * @return
	  */
	 //@AutoLog(value = "电商营业点-电商网点及地图位置列表查询")
	 @ApiOperation(value="电商营业点-电商网点及地图位置列表查询", notes="电商营业点-电商网点及地图位置列表查询")
	 @GetMapping(value = "/commerceAndAddressList")
	 public Result<List<ElectronicCommerce>> querycommerceAndAddressList(ElectronicCommerce electronicCommerce,
															@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
															@RequestParam(name="pageSize", defaultValue="100") Integer pageSize,
															HttpServletRequest req) {
		 QueryWrapper<ElectronicCommerce> queryWrapper = QueryGenerator.initQueryWrapper(electronicCommerce, req.getParameterMap());
//		 Page<ElectronicCommerce> page = new Page<ElectronicCommerce>(pageNo, pageSize);
//		 IPage<ElectronicCommerce> pageList = electronicCommerceService.page(page, queryWrapper);
		 List<ElectronicCommerce> list = electronicCommerceService.list(queryWrapper);
		 if (CollectionUtils.isNotEmpty(list)){
			 List<String> idList = list.stream().map(ElectronicCommerce::getAddId).collect(Collectors.toList());
			 List<String> ids = idList.stream().filter(id -> {
				 return StringUtils.isNotBlank(id);
			 }).collect(Collectors.toList());
			 QueryWrapper<Address> wrapper = new QueryWrapper<>();
			 wrapper.lambda().in(Address::getAddId, ids);
			 List<Address> addressList = addressService.list(wrapper);
			 if (CollectionUtils.isNotEmpty(addressList)){
				 for (ElectronicCommerce commerce : list) {
					 for (Address address : addressList) {
						 if (StringUtils.equals(address.getAddId(), commerce.getAddId())){
							 commerce.setMapAddress(address);
						 }
					 }
				 }
			 }
		 }
		 return Result.OK(list);
	 }

}
