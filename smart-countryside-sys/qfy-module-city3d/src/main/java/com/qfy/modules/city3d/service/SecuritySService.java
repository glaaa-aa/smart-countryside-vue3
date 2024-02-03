package com.qfy.modules.city3d.service;

import java.util.List;

import com.qfy.modules.city3d.po.Security;

public interface SecuritySService {
	public List<Security> getSecurityAll();
	public Security getSecuritySById(String securityId);
	public List<Security> querySecurityS(Security security);
	public int insert(Security security);
	public int updateByPrimaryKey(Security security);
	public int deleteByPrimaryKey(String securityId);
	public int batchDelete(List<String> securityIds);
}
