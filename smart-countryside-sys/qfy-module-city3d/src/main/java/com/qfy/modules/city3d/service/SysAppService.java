package com.qfy.modules.city3d.service;

import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

import com.qfy.modules.city3d.po.SysApp;

public interface SysAppService  extends IService<SysApp> {
	public List<SysApp> getSysApp();
	public int insert(SysApp sysApp);
	public int updateByPrimaryKey(SysApp sysApp);
	public int deleteByPrimaryKey(String appId);
	public SysApp getAppById(String appId);
}
