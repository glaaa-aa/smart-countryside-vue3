package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qfy.modules.city3d.po.Security;
import com.qfy.modules.city3d.po.SecurityExample;
import java.util.List;

@DS("postgresql")
public interface SecurityMapperC extends SecurityMapper {
	Security selectSecurityById(String sId);
	Security getSecurityById(String sId);
	List<Security> selectByExampleC(SecurityExample example);
	List<Security> selectAll();
	public int batchDelete(List<String> sIds);
}
