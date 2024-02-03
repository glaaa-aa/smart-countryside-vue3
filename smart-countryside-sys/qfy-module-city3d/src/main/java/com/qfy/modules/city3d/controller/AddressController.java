package com.qfy.modules.city3d.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qfy.modules.city3d.openmodule.service.FamilyModuleService;
import com.qfy.modules.city3d.po.Address;
import com.qfy.modules.city3d.service.AddressService;
import com.qfy.modules.city3d.util.CharacterUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.api.ISysBaseAPI;
import com.qfy.modules.city3d.openmodule.model.ElectronicCommerceModel;
import com.qfy.modules.city3d.openmodule.model.FamilyBaseModel;
import com.qfy.modules.city3d.openmodule.model.TrafficRoadModel;
import com.qfy.modules.city3d.openmodule.model.WaterTankModel;
import org.jeecg.common.system.query.QueryGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/address")
public class AddressController extends CrossOriginController{

	public static final Logger logger = LoggerFactory.getLogger(AddressController.class);

	@Autowired
	private AddressService addressService;
	@Resource
	private ISysBaseAPI sysBaseAPI;
	@Autowired
	private FamilyModuleService openModuleService;

	@RequestMapping("/getAddress")
	public @ResponseBody List<Address> getAddress() throws Exception {
		List<Address> result = addressService.getAddress("地点");
		return result;
	}

	@GetMapping("/list")
	@ResponseBody
	public Result<IPage<Address>> list(Address address,
				@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
				@RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
				HttpServletRequest request) throws Exception {
		Map<String,String[]> paramMaps = request.getParameterMap();
		Map<String,String[]> parameters = new HashMap<>();
		for (String key : paramMaps.keySet()) {
			if (StringUtils.equals("column",key)){
				parameters.put("column",new String[]{"add_date"});
			} else {
				parameters.put(key,paramMaps.get(key));
			}
		}

		QueryWrapper<Address> wrapper = QueryGenerator.initQueryWrapper(address, parameters);
//		wrapper.lambda().eq(Address::getAddType, "住宅");
		Page<Address> page = new Page<>(pageNo, pageSize);
		IPage<Address> list = addressService.page(page,wrapper);
		return Result.OK(list);
	}

	@RequestMapping("/getAddressById")
	public @ResponseBody Address getAddressById(@RequestBody Address address) throws Exception {
		Address result = addressService.getAddressById(address.getAddId());
		QueryWrapper wrapper = new QueryWrapper();
		wrapper.eq("add_id", address.getAddId());
		// 查询该地址是否关联水柜
		List<WaterTankModel> waterTankList = openModuleService.queryWaterTank(wrapper);
		if (CollectionUtils.isNotEmpty(waterTankList)){
			result.setWaterTank(waterTankList.get(0));
		}
		// 查询该地址是否关联道路交通
		List<TrafficRoadModel> roadList = openModuleService.queryTrafficRoad(wrapper);
		if (CollectionUtils.isNotEmpty(roadList)){
			result.setRoad(roadList.get(0));
		}
		// 查询该地址是否关联家庭户
		List<FamilyBaseModel> familyBaseList = openModuleService.queryFamilyBase(wrapper);
		if (CollectionUtils.isNotEmpty(familyBaseList)){
			result.setFamilyBase(familyBaseList.get(0));
		}

		// 查询该地址是否关联电商网点
		List<ElectronicCommerceModel> commerceList = openModuleService.queryEcommerce(wrapper);
		if (CollectionUtils.isNotEmpty(commerceList)){
			result.setCommerce(commerceList.get(0));
		}
		return result;
	}

	@RequestMapping("/getAddressByType")
	public @ResponseBody List<Address> getAddressByType() throws Exception {
		List<Address> result = addressService.getAddressByType("行政");
		List<Address> result1 = addressService.getAddressByType("地点");
		result.addAll(result1);
		return result;
	}

	@RequestMapping("/getAddressByComName")
	public @ResponseBody List<Address> getAddressByComName(@RequestBody Address address) throws Exception {
		List<Address> result = addressService.getAddressByComName(address.getCommunityName());
		return result;
	}

	@RequestMapping("/queryAddress")
	public @ResponseBody List<Address> queryAddress(@RequestBody Address address) throws Exception {
		List<Address> result = addressService.queryAddress(address);
		return result;
	}

	@RequestMapping("/queryAddressByPolygon")
	public @ResponseBody List<String> queryAddressByPolygon(@RequestBody Address address) throws Exception {
		List<String> result = addressService.queryAddressByPolygon(address);
		return result;
	}

	@RequestMapping("/getNetAllByAddId")
	public @ResponseBody List<Object> getNetAllByAddId(@RequestBody Address address) throws Exception {
		List<Object> result = addressService.getNetAllByAddId(address.getAddId());
		return result;
	}

	@RequestMapping("/insert")
	public @ResponseBody int insert(@RequestBody Address address) throws Exception {
		List<Address> AddressList = addressService.getAddressByPid(address.getPid());
		List<String> addNames = new ArrayList();
		for(Address add : AddressList){
			addNames.add(add.getAddName());
		}
		if(addNames.contains(address.getAddName())){
			return 2;
		}else{
			CharacterUtils characterUtils = new CharacterUtils();
			String uid = characterUtils.getUUID();
			address.setAddId(uid);
			int result = addressService.insert(address);
			return result;
		}
	}

	@RequestMapping("/update")
	public @ResponseBody int updateByPrimaryKey(@RequestBody Address address) throws Exception {
		int result = addressService.updateByPrimaryKey(address);
		return result;
	}

	@RequestMapping("/delete")
	public @ResponseBody int deleteByPrimaryKey(@RequestBody Address address) throws Exception {
		int result = addressService.deleteByPrimaryKey(address.getAddId());
		return result;
	}

	@RequestMapping("/batchDelete")
	public @ResponseBody int batchDelete(@RequestBody List<Address> addresses) throws Exception {
		List<String> addIds = new ArrayList();
		for(Address address : addresses) {
			addIds.add(address.getAddId());
		}
		int result = addressService.batchDelete(addIds);
		return result;
	}

	@RequestMapping("/getAddressDirectory")
	public @ResponseBody List<Address> getAddressDirectory(@RequestBody Address address) throws Exception {
		List<Address> result = addressService.getAddressDirectory(address.getPid());
		return result;
	}
}
