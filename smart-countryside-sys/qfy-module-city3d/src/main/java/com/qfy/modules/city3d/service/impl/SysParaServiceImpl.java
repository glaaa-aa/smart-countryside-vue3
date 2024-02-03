package com.qfy.modules.city3d.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.qfy.modules.city3d.mapper.SysParaMapper;
import com.qfy.modules.city3d.po.SysPara;
import com.qfy.modules.city3d.po.SysParaExample;
import com.qfy.modules.city3d.po.SysParaExample.Criteria;
import com.qfy.modules.city3d.service.SysParaService;
import org.springframework.stereotype.Service;

@Service
public class SysParaServiceImpl implements SysParaService {
	@Autowired
	private SysParaMapper sysParaMapper;

	@Override
	public List<SysPara> getSysPara() {
		List<SysPara> result = sysParaMapper.selectByExample(null);
		return result;
	}

	@Override
	public SysPara findSysParaById(String sysId) {
		SysPara result = sysParaMapper.selectByPrimaryKey(sysId);
		return result;
	}

	@Override
	public List<SysPara> querySysParabyType(SysPara sysPara) {
		SysParaExample example = new SysParaExample();
		Criteria criteriaResult = example.or();
		if (sysPara.getSysType() != "") {
			criteriaResult = criteriaResult.andSysTypeEqualTo(sysPara.getSysType());
		}
		List<SysPara> result = sysParaMapper.selectByExample(example);
		return result;
	}

	@Override
	public int insert(SysPara sysPara) {
		int result = sysParaMapper.insert(sysPara);
		return result;
	}

	@Override
	public int updateByPrimaryKey(SysPara sysPara) {
		int result = sysParaMapper.updateByPrimaryKey(sysPara);
		return result;
	}

	@Override
	public int deleteByPrimaryKey(String sysId) {
		int result = sysParaMapper.deleteByPrimaryKey(sysId);
		return result;
	}
}
