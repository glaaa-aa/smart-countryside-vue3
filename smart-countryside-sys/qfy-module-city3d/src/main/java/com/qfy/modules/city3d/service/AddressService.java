package com.qfy.modules.city3d.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qfy.modules.city3d.po.Address;
import java.util.List;

public interface AddressService extends IService<Address> {
	public List<Address> getAddress(String addType);
	public Address getAddressById(String addId);
	public List<Address> getAddressByType(String addType);
	public List<Address> getAddressByComName(String comName);
	public List<Address> queryAddress(Address address);
	public List<String> queryAddressByPolygon(Address address);
	public List<Object> getNetAllByAddId(String addId);
	public int insert(Address address);
	public int updateByPrimaryKey(Address address);
	public int deleteByPrimaryKey(String addId);
	public int batchDelete(List<String> addIds);
	public List<Address> getAddressDirectory(String pid);
	public List<Address> getAddressByPid(String pid);
}
