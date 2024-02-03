package com.qfy.modules.city3d.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qfy.modules.city3d.mapper.AddressMapper;
import com.qfy.modules.city3d.mapper.CompanyMapperC;
import com.qfy.modules.city3d.mapper.HouseMapperC;
import com.qfy.modules.city3d.mapper.NetMapper;
import com.qfy.modules.city3d.mapper.PeopleBaseMapper;
import com.qfy.modules.city3d.po.AddressExample;
import com.qfy.modules.city3d.po.CompanyExample;
import com.qfy.modules.city3d.po.HouseExample;
import com.qfy.modules.city3d.po.Net;
import com.qfy.modules.city3d.po.NetExample;
import com.qfy.modules.city3d.po.NetExample.Criteria;
import com.qfy.modules.city3d.po.PeopleBaseExample;
import com.qfy.modules.city3d.service.NetService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NetServiceImpl extends ServiceImpl<NetMapper, Net> implements NetService {
	@Autowired
	private NetMapper netMapper;
	@Autowired
	private PeopleBaseMapper peopleBaseMapper;
	@Autowired
	private AddressMapper addressMapper;
	@Autowired
	private HouseMapperC houseMapperC;
	@Autowired
	private CompanyMapperC companyMapperC;

	@Override
	public List<Object> getNetAndCount(Net net) {
		NetExample netExample = new NetExample();
		netExample.or().andNetLevelEqualTo(net.getNetLevel());
		List<Net> net3List = netMapper.selectByExample(netExample);
		netExample.clear();
		List<Object> resultList = new ArrayList<Object>();
		resultList.add(net3List);
		PeopleBaseExample example = new PeopleBaseExample();
		AddressExample addressExample = new AddressExample();
		for (int i = 0; i < net3List.size(); i++) {
			netExample.or().andNetPidEqualTo(net3List.get(i).getNetId());
			List<Net> net4List = netMapper.selectByExample(netExample);
			netExample.clear();

			long peopleBaseCount = 0;
			for (int j = 0; j < net4List.size(); j++) {
				example.or().andCommunityCodeEqualTo(net4List.get(j).getNetId());
				peopleBaseCount += peopleBaseMapper.countByExample(example);
				example.clear();
			}
			resultList.add(peopleBaseCount);

			long addressCount = 0;
			for (int j = 0; j < net4List.size(); j++) {
				addressExample.or().andPidEqualTo(net4List.get(j).getNetId());
				addressCount += addressMapper.countByExample(addressExample);
				addressExample.clear();
			}
			resultList.add(addressCount);

			netExample.or().andNetPidEqualTo(net3List.get(i).getNetId());
			resultList.add(netMapper.countByExample(netExample));
			netExample.clear();
		}
		return resultList;
	}

	@Override
	public List<Object> getNetByExample(Net net) {
		NetExample netExample = new NetExample();
		Criteria criteriaResult = netExample.or();
		if(net.getNetLevel()!=null) {criteriaResult=criteriaResult.andNetLevelEqualTo(net.getNetLevel());};
		if(net.getNetPid()!=null) {criteriaResult=criteriaResult.andNetPidEqualTo(net.getNetPid());};
		List<Net> net4List = netMapper.selectByExample(netExample);
		netExample.clear();
		List<Object> resultList = new ArrayList<Object>();
		resultList.add(net4List);
		PeopleBaseExample example = new PeopleBaseExample();
		AddressExample addressExample = new AddressExample();
		for (int i = 0; i < net4List.size(); i++) {
			example.or().andCommunityCodeEqualTo(net4List.get(i).getNetId());
			resultList.add(peopleBaseMapper.countByExample(example));
			example.clear();

			addressExample.or().andPidEqualTo(net4List.get(i).getNetId());
			resultList.add(addressMapper.countByExample(addressExample));
			addressExample.clear();
		}
		return resultList;
	}

	@Override
	public List<Object> getNetAndCountHouse(Net net) {
		NetExample netExample = new NetExample();
		netExample.or().andNetLevelEqualTo(net.getNetLevel());
		List<Net> net3List = netMapper.selectByExample(netExample);
		netExample.clear();
		List<Object> resultList = new ArrayList<Object>();
		resultList.add(net3List);
		HouseExample example = new HouseExample();
		AddressExample addressExample = new AddressExample();
		for (int i = 0; i < net3List.size(); i++) {
			netExample.or().andNetPidEqualTo(net3List.get(i).getNetId());
			List<Net> net4List = netMapper.selectByExample(netExample);
			netExample.clear();

			long houseCount = 0;
			for (int j = 0; j < net4List.size(); j++) {
				example.or().andHouseUrlEqualTo(net4List.get(j).getNetId());
				houseCount += houseMapperC.countByExample(example);
				example.clear();
			}
			resultList.add(houseCount);

			long addressCount = 0;
			for (int j = 0; j < net4List.size(); j++) {
				addressExample.or().andPidEqualTo(net4List.get(j).getNetId());
				addressCount += addressMapper.countByExample(addressExample);
				addressExample.clear();
			}
			resultList.add(addressCount);

			netExample.or().andNetPidEqualTo(net3List.get(i).getNetId());
			resultList.add(netMapper.countByExample(netExample));
			netExample.clear();
		}
		return resultList;
	}

	@Override
	public List<Object> getNetHouseByExample(Net net) {
		NetExample netExample = new NetExample();
		Criteria criteriaResult = netExample.or();
		if(net.getNetLevel()!=null) {criteriaResult=criteriaResult.andNetLevelEqualTo(net.getNetLevel());};
		if(net.getNetPid()!=null) {criteriaResult=criteriaResult.andNetPidEqualTo(net.getNetPid());};
		List<Net> net4List = netMapper.selectByExample(netExample);
		netExample.clear();
		List<Object> resultList = new ArrayList<Object>();
		resultList.add(net4List);
		HouseExample example = new HouseExample();
		AddressExample addressExample = new AddressExample();
		for (int i = 0; i < net4List.size(); i++) {
			example.or().andHouseUrlEqualTo(net4List.get(i).getNetId());
			resultList.add(houseMapperC.countByExample(example));
			example.clear();

			addressExample.or().andPidEqualTo(net4List.get(i).getNetId());
			resultList.add(addressMapper.countByExample(addressExample));
			addressExample.clear();
		}
		return resultList;
	}

	@Override
	public List<Object> getNetCompanyByExample(Net net) {
		NetExample netExample = new NetExample();
		netExample.or().andNetLevelEqualTo(net.getNetLevel());
		List<Net> result = netMapper.selectByExample(netExample);
		netExample.clear();
		List<Object> resultList = new ArrayList<Object>();
		resultList.add(result);
		CompanyExample example = new CompanyExample();
		AddressExample addressExample = new AddressExample();
		for (int i = 0; i < result.size(); i++) {
			example.or().andCommunityEqualTo(result.get(i).getNetId());
			resultList.add(companyMapperC.countByExample(example));
			example.clear();

			addressExample.or().andPidEqualTo(result.get(i).getNetId());
			resultList.add(addressMapper.countByExample(addressExample));
			addressExample.clear();
		}
		return resultList;
	}

	@Override
	public List<Object> getNetAndCountAddress(Net net) {
		NetExample netExample = new NetExample();
		netExample.or().andNetLevelEqualTo(net.getNetLevel());
		List<Net> result = netMapper.selectByExample(netExample);
		netExample.clear();
		List<Object> resultList = new ArrayList<Object>();
		resultList.add(result);
		AddressExample addressExample = new AddressExample();
		for (int i = 0; i < result.size(); i++) {
			netExample.or().andNetPidEqualTo(result.get(i).getNetId());
			List<Net> net4List = netMapper.selectByExample(netExample);
			netExample.clear();

			addressExample.or().andPidEqualTo(result.get(i).getNetId());
			long addressPreCount = addressMapper.countByExample(addressExample);
			addressExample.clear();

			long addressCount = 0;
			for (int j = 0; j < net4List.size(); j++) {
				addressExample.or().andPidEqualTo(net4List.get(j).getNetId());
				addressCount += addressMapper.countByExample(addressExample);
				addressExample.clear();
			}
			resultList.add(addressCount + addressPreCount);

			netExample.or().andNetPidEqualTo(result.get(i).getNetId());
			resultList.add(netMapper.countByExample(netExample));
			netExample.clear();
		}
		return resultList;
	}

	@Override
	public List<Object> getNetAddressByExample(Net net){
		NetExample netExample = new NetExample();
		Criteria criteriaResult = netExample.or();
		if(net.getNetLevel()!=null) {criteriaResult=criteriaResult.andNetLevelEqualTo(net.getNetLevel());};
		if(net.getNetPid()!=null) {criteriaResult=criteriaResult.andNetPidEqualTo(net.getNetPid());};
		List<Net> result = netMapper.selectByExample(netExample);
		netExample.clear();
		List<Object> resultList = new ArrayList<Object>();
		resultList.add(result);
		AddressExample addressExample = new AddressExample();
		HouseExample example = new HouseExample();
		for (int i = 0; i < result.size(); i++) {
			addressExample.or().andPidEqualTo(result.get(i).getNetId());
			resultList.add(addressMapper.countByExample(addressExample));
			addressExample.clear();

			example.or().andHouseUrlEqualTo(result.get(i).getNetId());
			resultList.add(houseMapperC.countByExample(example));
			example.clear();
		}
		return resultList;
	}

	@Override
	public List<Object> getNetAndCountAll(Net net){
		NetExample netExample = new NetExample();
		netExample.or().andNetLevelEqualTo(net.getNetLevel());
		List<Net> net3List = netMapper.selectByExample(netExample);
		netExample.clear();
		List<Object> resultList = new ArrayList<Object>();
		resultList.add(net3List);
		AddressExample addressExample = new AddressExample();
		PeopleBaseExample example = new PeopleBaseExample();
		HouseExample houseExample = new HouseExample();
		CompanyExample companyExample = new CompanyExample();
		for (int i = 0; i < net3List.size(); i++) {
			netExample.or().andNetPidEqualTo(net3List.get(i).getNetId());
			List<Net> net4List = netMapper.selectByExample(netExample);
			netExample.clear();

			long addressPreCount = 0;
			addressExample.or().andPidEqualTo(net3List.get(i).getNetId());
			addressPreCount = addressMapper.countByExample(addressExample);
			addressExample.clear();

			long addressCount = 0;
			for (int j = 0; j < net4List.size(); j++) {
				addressExample.or().andPidEqualTo(net4List.get(j).getNetId());
				addressCount += addressMapper.countByExample(addressExample);
				addressExample.clear();
			}
			resultList.add(addressCount + addressPreCount);

			long peopleBaseCount = 0;
			for (int j = 0; j < net4List.size(); j++) {
				example.or().andCommunityCodeEqualTo(net4List.get(j).getNetId());
				peopleBaseCount += peopleBaseMapper.countByExample(example);
				example.clear();
			}
			resultList.add(peopleBaseCount);

			long houseCount = 0;
			for (int j = 0; j < net4List.size(); j++) {
				houseExample.or().andHouseUrlEqualTo(net4List.get(j).getNetId());
				houseCount += houseMapperC.countByExample(houseExample);
				houseExample.clear();
			}
			resultList.add(houseCount);

			companyExample.or().andCommunityEqualTo(net3List.get(i).getNetId());
			resultList.add(companyMapperC.countByExample(companyExample));
			companyExample.clear();

			netExample.or().andNetPidEqualTo(net3List.get(i).getNetId());
			resultList.add(netMapper.countByExample(netExample));
			netExample.clear();
		}
		return resultList;
	}

	@Override
	public List<Net> queryNetData(Net net){
		NetExample netExample = new NetExample();
		Criteria criteriaResult = netExample.or();
		if(net.getNetLevel()!=null&&net.getNetLevel()!="") {criteriaResult=criteriaResult.andNetLevelEqualTo(net.getNetLevel());};
		if(net.getNetName()!=null&&net.getNetName()!="") {criteriaResult=criteriaResult.andNetNameLike("%"+net.getNetName()+"%");};
		List<Net> result = netMapper.selectByExample(netExample);
		return result;
	}

	@Override
	public List<Object> getNetById(String netId){
		Net net3 = netMapper.selectByPrimaryKey(netId);
		List<Object> resultList = new ArrayList<Object>();
		resultList.add(net3);
		AddressExample addressExample = new AddressExample();
		PeopleBaseExample peopleExample = new PeopleBaseExample();
		HouseExample houseExample = new HouseExample();
		CompanyExample example = new CompanyExample();
		NetExample netExample = new NetExample();
		if(net3.getNetLevel().equals("3")) {
			netExample.or().andNetPidEqualTo(net3.getNetId());
			List<Net> net4List = netMapper.selectByExample(netExample);
			netExample.clear();

			long addressCount = 0;
			for (int j = 0; j < net4List.size(); j++) {
				addressExample.or().andPidEqualTo(net4List.get(j).getNetId());
				addressCount += addressMapper.countByExample(addressExample);
				addressExample.clear();
			}
			resultList.add(addressCount);

			long peopleBaseCount = 0;
			for (int j = 0; j < net4List.size(); j++) {
				peopleExample.or().andCommunityCodeEqualTo(net4List.get(j).getNetId());
				peopleBaseCount += peopleBaseMapper.countByExample(peopleExample);
				peopleExample.clear();
			}
			resultList.add(peopleBaseCount);

			long houseCount = 0;
			for (int j = 0; j < net4List.size(); j++) {
				houseExample.or().andHouseUrlEqualTo(net4List.get(j).getNetId());
				houseCount += houseMapperC.countByExample(houseExample);
				houseExample.clear();
			}
			resultList.add(houseCount);

			example.or().andCommunityEqualTo(net3.getNetId());
			resultList.add(companyMapperC.countByExample(example));
			example.clear();

			netExample.or().andNetPidEqualTo(net3.getNetId());
			resultList.add(netMapper.countByExample(netExample));
			netExample.clear();
		}else if(net3.getNetLevel().equals("4")){
			addressExample.or().andPidEqualTo(net3.getNetId());
			resultList.add(addressMapper.countByExample(addressExample));
			addressExample.clear();

			peopleExample.or().andCommunityCodeEqualTo(net3.getNetId());
			resultList.add(peopleBaseMapper.countByExample(peopleExample));
			peopleExample.clear();

			houseExample.or().andHouseUrlEqualTo(net3.getNetId());
			resultList.add(houseMapperC.countByExample(houseExample));
			houseExample.clear();

			example.or().andCommunityEqualTo(net3.getNetId());
			resultList.add(companyMapperC.countByExample(example));
			example.clear();

			netExample.or().andNetPidEqualTo(net3.getNetId());
			resultList.add(netMapper.countByExample(netExample));
			netExample.clear();
		}
		return resultList;
	}

	@Override
	public List<Object> getNetAndCountAllN(Net net) {
		NetExample netExample = new NetExample();
		netExample.or().andNetPidEqualTo(net.getNetPid());
		List<Net> result = netMapper.selectByExample(netExample);
		netExample.clear();
		List<Object> resultList = new ArrayList<Object>();
		resultList.add(result);
		AddressExample addressExample = new AddressExample();
		PeopleBaseExample peopleExample = new PeopleBaseExample();
		HouseExample houseExample = new HouseExample();
		CompanyExample example = new CompanyExample();
		for (int i = 0; i < result.size(); i++) {
			addressExample.or().andPidEqualTo(result.get(i).getNetId());
			resultList.add(addressMapper.countByExample(addressExample));
			addressExample.clear();

			peopleExample.or().andCommunityCodeEqualTo(result.get(i).getNetId());
			resultList.add(peopleBaseMapper.countByExample(peopleExample));
			peopleExample.clear();

			houseExample.or().andHouseUrlEqualTo(result.get(i).getNetId());
			resultList.add(houseMapperC.countByExample(houseExample));
			houseExample.clear();

			example.or().andCommunityEqualTo(result.get(i).getNetId());
			resultList.add(companyMapperC.countByExample(example));
			example.clear();

			netExample.or().andNetPidEqualTo(result.get(i).getNetId());
			resultList.add(netMapper.countByExample(netExample));
			netExample.clear();
		}
		return resultList;
	}

	@Override
	public int insertNet(Net net) {
		int result = netMapper.insert(net);
		return result;
	}

	@Override
	public int deleteByPrimaryKey(String netId) {
		int result = netMapper.deleteByPrimaryKey(netId);
		return result;
	}

	@Override
	public int batchDelete(List<String> netIds) {
		int result = netMapper.batchDelete(netIds);
		return result;
	}

	@Override
	public int updateByPrimaryKey(Net net) {
		int result = netMapper.updateByPrimaryKey(net);
		return result;
	}


}
