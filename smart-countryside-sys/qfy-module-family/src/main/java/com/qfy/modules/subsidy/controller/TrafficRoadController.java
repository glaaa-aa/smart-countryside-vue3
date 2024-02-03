package com.qfy.modules.subsidy.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qfy.modules.city3d.po.Address;
import com.qfy.modules.city3d.service.AddressService;
import com.qfy.modules.subsidy.entity.TrafficRoad;
import com.qfy.modules.subsidy.mapper.TrafficRoadMapper;
import com.qfy.modules.subsidy.service.ITrafficRoadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
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
 * @Description: 道路交通
 * @Author: lanls
 * @Date:   2022-08-26
 * @Version: V1.0
 */
@Api(tags="道路交通")
@RestController
@RequestMapping("/subsidy/road")
@Slf4j
public class TrafficRoadController extends JeecgController<TrafficRoad, ITrafficRoadService> {
	@Autowired
	private ITrafficRoadService trafficRoadService;

	@Autowired
	TrafficRoadMapper trafficRoadMapper;

	 @Resource
	 private AddressService addressService;

	/**
	 * 分页列表查询
	 *
	 * @param trafficRoad
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "道路交通-分页列表查询")
	@ApiOperation(value="道路交通-分页列表查询", notes="道路交通-分页列表查询")
	@GetMapping(value = "/list")
	@PermissionData(pageComponent = "family/subsidy/TrafficRoadList")
	public Result<IPage<TrafficRoad>> queryPageList(TrafficRoad trafficRoad,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<TrafficRoad> queryWrapper = QueryGenerator.initQueryWrapper(trafficRoad, req.getParameterMap());
		Page<TrafficRoad> page = new Page<TrafficRoad>(pageNo, pageSize);
		IPage<TrafficRoad> pageList = trafficRoadService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	/**
	 *   添加
	 *
	 * @param trafficRoad
	 * @return
	 */
	@AutoLog(value = "道路交通-添加")
	@ApiOperation(value="道路交通-添加", notes="道路交通-添加")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody TrafficRoad trafficRoad) {
		trafficRoadService.save(trafficRoad);
		return Result.OK("添加成功！");
	}

	/**
	 *  编辑
	 *
	 * @param trafficRoad
	 * @return
	 */
	@AutoLog(value = "道路交通-编辑")
	@ApiOperation(value="道路交通-编辑", notes="道路交通-编辑")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody TrafficRoad trafficRoad) {
		trafficRoadService.updateById(trafficRoad);
		return Result.OK("编辑成功!");
	}

	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "道路交通-通过id删除")
	@ApiOperation(value="道路交通-通过id删除", notes="道路交通-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		trafficRoadService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "道路交通-批量删除")
	@ApiOperation(value="道路交通-批量删除", notes="道路交通-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.trafficRoadService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "道路交通-通过id查询")
	@ApiOperation(value="道路交通-通过id查询", notes="道路交通-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<TrafficRoad> queryById(@RequestParam(name="id",required=true) String id) {
		TrafficRoad trafficRoad = trafficRoadService.getById(id);
		if(trafficRoad==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(trafficRoad);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param trafficRoad
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, TrafficRoad trafficRoad) {
        return super.exportXls(request, trafficRoad, TrafficRoad.class, "道路交通");
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
        return super.importExcel(request, response, TrafficRoad.class);
    }

	 /**
	  * 查询已硬化道路及护栏和电商网点数量
	  * @param trafficRoad
	  * @param req
	  * @return
	  */
	 //@AutoLog(value = "查询已硬化道路及护栏长度(米)和电商网点数量")
	 @ApiOperation(value="查询已硬化道路及护栏长度(米)和电商网点数量", notes="查询已硬化道路及护栏长度(米)和电商网点数量")
	 @GetMapping(value = "/roadLengthAndCommerceTotal")
	 public Result<Map<String,Integer>> getRoadLengthAndCommerceTotal(TrafficRoad trafficRoad,
													   HttpServletRequest req) {
		 QueryWrapper<TrafficRoad> queryWrapper = QueryGenerator.initQueryWrapper(trafficRoad, req.getParameterMap());
		 Map<String,Integer> total = trafficRoadMapper.queryRoadLengthAndCommerceTotal();
		 return Result.OK(total);
	 }

	 /**
	  * 分页查询道路交通及地图位置
	  *
	  * @param trafficRoad
	  * @param pageNo
	  * @param pageSize
	  * @param req
	  * @return
	  */
	 //@AutoLog(value = "道路交通-分页查询道路交通及地图位置")
	 @ApiOperation(value="道路交通-分页查询道路交通及地图位置", notes="道路交通-分页查询道路交通及地图位置")
	 @GetMapping(value = "/roadAndAddressList")
	 public Result<IPage<TrafficRoad>> queryRoadAndAddressList(TrafficRoad trafficRoad,
													 @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
													 @RequestParam(name="pageSize", defaultValue="100") Integer pageSize,
													 HttpServletRequest req) {
		 QueryWrapper<TrafficRoad> queryWrapper = QueryGenerator.initQueryWrapper(trafficRoad, req.getParameterMap());
		 Page<TrafficRoad> page = new Page<TrafficRoad>(pageNo, pageSize);
		 IPage<TrafficRoad> pageList = trafficRoadService.page(page, queryWrapper);
		 if (CollectionUtils.isNotEmpty(pageList.getRecords())){
			 List<String> idList = pageList.getRecords().stream().map(TrafficRoad::getAddId).collect(Collectors.toList());
			 List<String> ids = idList.stream().filter(id -> {
				 return StringUtils.isNotBlank(id);
			 }).collect(Collectors.toList());
			 QueryWrapper<Address> wrapper = new QueryWrapper<>();
			 wrapper.lambda().in(Address::getAddId, ids);
			 List<Address> addressList = addressService.list(wrapper);
			 if (CollectionUtils.isNotEmpty(addressList)){
				 for (TrafficRoad road : pageList.getRecords()) {
					 for (Address address : addressList) {
						 if (StringUtils.equals(address.getAddId(), road.getAddId())){
							 road.setMapAddress(address);
						 }
					 }
				 }
			 }
		 }

		 return Result.OK(pageList);
	 }

}
