package com.qfy.modules.city3d.service;

import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

import com.qfy.modules.city3d.po.Net;

public interface NetService extends IService<Net> {
	public List<Object> getNetAndCount(Net net);
	public List<Object> getNetByExample(Net net);
	public List<Object> getNetAndCountHouse(Net net);
	public List<Object> getNetHouseByExample(Net net);
	public List<Object> getNetCompanyByExample(Net net);
	public List<Object> getNetAndCountAddress(Net net);
	public List<Object> getNetAddressByExample(Net net);
	public List<Object> getNetAndCountAll(Net net);
	public List<Net> queryNetData(Net net);
	public List<Object> getNetById(String netId);
	public List<Object> getNetAndCountAllN(Net net);
	public int insertNet(Net net);
	public int deleteByPrimaryKey(String netId);
	public int updateByPrimaryKey(Net net);
	public int batchDelete(List<String> netIds);
}
