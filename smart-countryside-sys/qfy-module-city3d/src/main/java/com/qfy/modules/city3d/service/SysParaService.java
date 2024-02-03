package com.qfy.modules.city3d.service;

import java.util.List;

import com.qfy.modules.city3d.po.SysPara;

public interface SysParaService {
	public List<SysPara> getSysPara();
	public SysPara findSysParaById(String sysId);
	public List<SysPara> querySysParabyType(SysPara sysPara);
	public int insert(SysPara sysPara);
	public int updateByPrimaryKey(SysPara sysPara);
	public int deleteByPrimaryKey(String sysId);
}
