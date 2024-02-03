package com.qfy.modules.city3d.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.qfy.modules.city3d.mapper.SecurityMapperC;
import com.qfy.modules.city3d.po.SecurityExample.Criteria;
import com.qfy.modules.city3d.po.Security;
import com.qfy.modules.city3d.po.SecurityExample;
import com.qfy.modules.city3d.service.SecuritySService;
import org.springframework.stereotype.Service;

@Service
public class SecuritySServiceImpl implements SecuritySService {
	@Autowired
	private SecurityMapperC securityMapperC;

	@Override
	public List<Security> getSecurityAll() {
		List<Security> result = securityMapperC.selectAll();
		return result;
	}

	@Override
	public Security getSecuritySById(String securityId) {
		Security result = securityMapperC.getSecurityById(securityId);
		return result;
	}

	@Override
	public List<Security> querySecurityS(Security security) {
		SecurityExample example = new SecurityExample();
		Criteria criteriaResult = example.or();
		if(security.getSecurityName()!=null){criteriaResult=criteriaResult.andSecurityNameLike("%"+security.getSecurityName()+"%");};
		if(security.getSecurityType()!=null){criteriaResult=criteriaResult.andSecurityTypeLike("%"+security.getSecurityType()+"%");};
		List<Security> result = securityMapperC.selectByExampleC(example);
		return result;
	}

	@Override
	public int insert(Security security){
		int result = securityMapperC.insert(security);
		return result;
	}

	@Override
	public int updateByPrimaryKey(Security security){
		int result = securityMapperC.updateByPrimaryKey(security);
		return result;
	}

	@Override
	public int deleteByPrimaryKey(String securityId){
		int result = securityMapperC.deleteByPrimaryKey(securityId);
		return result;
	}

	@Override
	public int batchDelete(List<String> securityIds) {
		int result = securityMapperC.batchDelete(securityIds);
		return result;
	}
}
