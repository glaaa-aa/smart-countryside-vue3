package com.qfy.modules.city3d.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qfy.modules.city3d.mapper.AddressMapper;
import com.qfy.modules.city3d.mapper.CompanyMapper;
import com.qfy.modules.city3d.mapper.HouseMapper;
import com.qfy.modules.city3d.mapper.PeopleBaseMapper;
import com.qfy.modules.city3d.po.Address;
import com.qfy.modules.city3d.po.AddressExample;
import com.qfy.modules.city3d.po.AddressExample.Criteria;
import com.qfy.modules.city3d.po.CompanyExample;
import com.qfy.modules.city3d.po.HouseExample;
import com.qfy.modules.city3d.po.PeopleBaseExample;
import com.qfy.modules.city3d.service.AddressService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements AddressService {

	@Autowired
	private PeopleBaseMapper peopleBaseMapper;
	@Autowired
	private HouseMapper houseMapper;
	@Autowired
	private CompanyMapper companyMapper;

	@Override
	public List<Address> getAddress(String addType) {
		List<Address> result = baseMapper.getAddressAll(addType);
		return result;
	}
	@Override
	public Address getAddressById(String addId) {
		Address result = baseMapper.selectByPrimaryKey(addId);
		return result;
	}
	@Override
	public List<Address> getAddressByType(String addType) {
		AddressExample example = new AddressExample();
		Criteria criteriaResult = example.or();
		if(addType!=""){criteriaResult=criteriaResult.andAddTypeEqualTo(addType);};
		List<Address> result = baseMapper.selectByExample(example);
		return result;
	}
	@Override
	public List<Address> getAddressByComName(String comName) {
		AddressExample example = new AddressExample();
		example.or().andPidEqualTo(comName);
		List<Address> result = baseMapper.selectByExample(example);
		return result;
	}
	@Override
	public List<Address> queryAddress(Address address) {
		AddressExample example = new AddressExample();
		Criteria criteriaResult = example.or();
		if(address.getAddName()!=""){criteriaResult=criteriaResult.andAddNameLike("%"+address.getAddName()+"%");};
		if(address.getCommunities()!=""){criteriaResult=criteriaResult.andCommunitiesLike("%"+address.getCommunities()+"%");};
		if(address.getStreet()!=""){criteriaResult=criteriaResult.andStreetLike("%"+address.getStreet()+"%");};
		if(address.getCommunityName()!=""){criteriaResult=criteriaResult.andCommunityNameLike("%"+address.getCommunityName()+"%");};
		if(address.getAddType()!=""){criteriaResult=criteriaResult.andAddTypeLike("%"+address.getAddType()+"%");};
		List<Address> result = baseMapper.selectByExample(example);
		return result;
	}
	@Override
	public List<String> queryAddressByPolygon(Address address) {
		String[] poiArr = address.getPoi().split(",");
		String poi = "";
		for (int i = 0; i < poiArr.length; i = i + 2) {
			poi += poiArr[i] + " " + poiArr[i+1] + ",";
		}
		poi += poiArr[0] + " " + poiArr[1];
		address.setPoi(poi);
		List<Address> resultArr = baseMapper.queryAddressByPolygon(address);
		List<String> result = new ArrayList();
		for(Address add : resultArr) {
			result.add(add.getAddId());
		}
		return result;
	}
	@Override
	public List<Object> getNetAllByAddId(String addId) {
		List<Object> resultList = new ArrayList<Object>();

		AddressExample addressExample = new AddressExample();
		PeopleBaseExample peopleExample = new PeopleBaseExample();
		HouseExample houseExample = new HouseExample();
		CompanyExample companyExample = new CompanyExample();

		addressExample.or().andAddIdEqualTo(addId);
		resultList.add(baseMapper.selectByExample(addressExample));

		peopleExample.or().andAddIdEqualTo(addId);
		resultList.add(peopleBaseMapper.selectByExample(peopleExample));

		houseExample.or().andAddIdEqualTo(addId);
		resultList.add(houseMapper.selectByExample(houseExample));

		companyExample.or().andAddIdEqualTo(addId);
		resultList.add(companyMapper.selectByExample(companyExample));

		return resultList;
	}
	@Override
	public int insert(Address address) {
		int result = baseMapper.insert(address);
		return result;
	}
	@Override
	public int updateByPrimaryKey(Address address) {
		int result = baseMapper.updateByPrimaryKey(address);
		return result;
	}
	@Override
	public int deleteByPrimaryKey(String addId) {
		int result = baseMapper.deleteByPrimaryKey(addId);
		return result;
	}
	@Override
	public int batchDelete(List<String> addIds) {
		int result = baseMapper.batchDelete(addIds);
		return result;
	}
	@Override
	public List<Address> getAddressDirectory(String pid) {
		AddressExample example = new AddressExample();
		example.setOrderByClause("add_name");
		example.createCriteria().andPidEqualTo(pid);
		List<Address> result = baseMapper.selectByExample(example);
		return result;
	}
	@Override
	public List<Address> getAddressByPid(String pid) {
		AddressExample example = new AddressExample();
		example.createCriteria().andPidEqualTo(pid);
		List<Address> result = baseMapper.selectByExample(example);
		return result;
	}
}
